package br.ufrgs.inf.model;

import org.jfugue.pattern.Pattern;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.LinkedList;

public class Converter {

    // - Attributes:
    private HashMap<Character, String> mapping = new HashMap<Character, String>();

    final int MAX_VOLUME = 127;
    final int MAX_OCTAVE = 9;
    final int MIN_OCTAVE = 0;
    final int MAX_INSTRUMENT = 127;

    final String SILENCE = "R";
    final String EMPTY_COMPONENT = "";

    final String DEFAULT_MAPPING_PATH = "utilfiles//mapping.txt";

    // - Constructors:
    public Converter() {
        this.loadMapping(DEFAULT_MAPPING_PATH);
    }

    public Converter(HashMap<Character, String> customMapping) {
        this.mapping = customMapping;
    }

    public Converter(String mappingPath) {
        this.loadMapping(mappingPath);
    }

    // -  Methods:
    public Pattern parse(Song song) {

        AtomicInteger bpm = new AtomicInteger(song.getInitBPM());
        AtomicInteger volume = new AtomicInteger(song.getInitVolume());
        AtomicInteger octave = new AtomicInteger(song.getInitOctave());
        AtomicInteger instrument = new AtomicInteger(song.getInitinstrument());

        String lastComponent = EMPTY_COMPONENT;
        String songDescription = descriptionSetup(song);
        String command = EMPTY_COMPONENT;

        for (Character letter : song.getText().toCharArray()) {
            if (this.mapping.containsKey(letter)) {
                command = this.mapping.get(letter);
            } else {
                command = this.mapping.get(null);
            }
            lastComponent = this.toStaccato(command, letter, lastComponent, volume, octave, instrument, song);

            songDescription = songDescription + lastComponent + " ";
        }

        System.out.println(songDescription);

        return new Pattern(songDescription);
    }

    private String toStaccato(String command, Character letter, String lastComponent, AtomicInteger volume, AtomicInteger octave, AtomicInteger instrument, Song song) {
        String songComponent = EMPTY_COMPONENT;

        switch (command) {
            case "last or silence":
                songComponent = lastOrSilence(lastComponent);
                break;
            case "double volume":
                songComponent = doubleVolume(volume, song.getInitVolume());
                break;
            case "increase octave":
                songComponent = increaseOctave(octave, song.getInitOctave());
                break;
            case "decrease octave":
                songComponent = decreaseOctave(octave, song.getInitOctave());
                break;
            case "change instrument":
                songComponent = changeInstrument(instrument, letter);
                break;
            default:
                songComponent = formatNote(command, octave);
        }
        return songComponent;
    }

    private String lastOrSilence(String lastComponent) {
        if (this.isNote(lastComponent)) {
            return lastComponent;
        } else {
            return this.SILENCE;
        }
    }

    private boolean isNote(String command) {
        if (command == EMPTY_COMPONENT) {
            return false;
        }
        switch (command.charAt(0)) {
            case 'A':
                return true;
            case 'B':
                return true;
            case 'C':
                return true;
            case 'D':
                return true;
            case 'E':
                return true;
            case 'F':
                return true;
            case 'G':
                return true;
            default:
                return false;

        }
    }

    private String doubleVolume(AtomicInteger volume, int initVolume) {
        if (2 * volume.get() > this.MAX_VOLUME) {
            volume.set(initVolume);
        } else {
            volume.set(2 * volume.get());
        }
        return MessageFormat.format(":CE(7,{0})", volume.get());
    }

    private String descriptionSetup(Song song) {
        return (MessageFormat.format(" T{0,number,#}  I{1,number,#} :CE(7,{2,number,#}) ", song.getInitBPM(), song.getInitinstrument(), song.getInitVolume()));
    }

    private String increaseOctave(AtomicInteger octave, int initOctave) {
        if (octave.get() + 1 > this.MAX_OCTAVE) {
            octave.set(initOctave);
        } else {
            octave.set(octave.get() + 1);
        }
        return "";
    }

    private String decreaseOctave(AtomicInteger octave, int initOctave) {
        if (octave.get() - 1 <= this.MIN_OCTAVE) {
            octave.set(initOctave);
        } else {
            octave.set(octave.get() - 1);
        }
        return "";
    }

    private String changeInstrument(AtomicInteger instrument, char letter) {

        int step = Integer.parseInt(String.valueOf(letter));
        if (instrument.get() + step > this.MAX_INSTRUMENT) {
            instrument.set(instrument.get() + step - this.MAX_INSTRUMENT);
        } else {
            instrument.set(instrument.get() + step);
        }
        return MessageFormat.format("I{0} ", instrument.get());
    }

    private String formatNote(String note, AtomicInteger octave) {
        return MessageFormat.format(note, octave);
    }

    private boolean loadMapping(String mappingPath) {

        MyFile file = new MyFile(mappingPath);

        if (!(file.openFile(file))) {

            return false;

        }

        this.mapping = new HashMap<Character, String>();

        String contentFile = file.getContent();

        String contentFileInArray[];
        contentFileInArray = contentFile.split("\n");

        LinkedList<String> predefinedMapping;
        predefinedMapping = new LinkedList<>(Arrays.asList(contentFileInArray));

        for (String conversion : predefinedMapping) {

            String letter = conversion.split("->")[0];
            String command = conversion.split("->")[1];

            if (letter.equals("default")) {
                this.mapping.put(null, command.replace("\"", ""));
            } else {
                this.mapping.put(letter.replace("\\n", "\n").charAt(1), command.replace("\"", ""));
            }

        }

        return true;
    }
}
