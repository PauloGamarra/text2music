/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrgs.inf.model;

import org.jfugue.pattern.Pattern;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.text.MessageFormat;


/**
 *
 * @author Paulo
 */
public class Converter {
    
    // - Attributes:
    public HashMap<Character, String> mapping = new HashMap<Character, String>();
    
    final int MAX_VOLUME = 127;
    final int MAX_OCTAVE = 9;
    final int MAX_INSTRUMENT = 127;

    // - Constructors:
    public Converter(){
        this.mapping.put('A', "A{0}");
        this.mapping.put('B', "B{0}");
        this.mapping.put('C', "C{0}");
        this.mapping.put('D', "D{0}");
        this.mapping.put('E', "E{0}");
        this.mapping.put('F', "F{0}");
        this.mapping.put('G', "G{0}");

        this.mapping.put('a', "anterior ou silencio");
        this.mapping.put('b', "anterior ou silencio");
        this.mapping.put('c', "anterior ou silencio");
        this.mapping.put('d', "anterior ou silencio");
        this.mapping.put('e', "anterior ou silencio");
        this.mapping.put('f', "anterior ou silencio");
        this.mapping.put('G', "anterior ou silencio");
        this.mapping.put('g', "anterior ou silencio");
        
        this.mapping.put(' ', "dobra volume");

        this.mapping.put('!', "I113");

        this.mapping.put('I', "I6");
        this.mapping.put('i', "I6");
        this.mapping.put('O', "I6");
        this.mapping.put('o', "I6");
        this.mapping.put('U', "I6");
        this.mapping.put('u', "I6");
        
        
        this.mapping.put('H', "anterior ou silencio");
        this.mapping.put('h', "anterior ou silencio");
        this.mapping.put('J', "anterior ou silencio");
        this.mapping.put('j', "anterior ou silencio");
        this.mapping.put('K', "anterior ou silencio");
        this.mapping.put('k', "anterior ou silencio");
        this.mapping.put('L', "anterior ou silencio");
        this.mapping.put('l', "anterior ou silencio");
        this.mapping.put('M', "anterior ou silencio");
        this.mapping.put('m', "anterior ou silencio");
        this.mapping.put('N', "anterior ou silencio");
        this.mapping.put('n', "anterior ou silencio");
        this.mapping.put('P', "anterior ou silencio");
        this.mapping.put('p', "anterior ou silencio");
        this.mapping.put('Q', "anterior ou silencio");
        this.mapping.put('q', "anterior ou silencio");
        this.mapping.put('R', "anterior ou silencio");
        this.mapping.put('r', "anterior ou silencio");
        this.mapping.put('S', "anterior ou silencio");
        this.mapping.put('s', "anterior ou silencio");
        this.mapping.put('T', "anterior ou silencio");
        this.mapping.put('t', "anterior ou silencio");
        this.mapping.put('V', "anterior ou silencio");
        this.mapping.put('v', "anterior ou silencio");
        this.mapping.put('W', "anterior ou silencio");
        this.mapping.put('w', "anterior ou silencio");
        this.mapping.put('X', "anterior ou silencio");
        this.mapping.put('x', "anterior ou silencio");
        this.mapping.put('Y', "anterior ou silencio");
        this.mapping.put('y', "anterior ou silencio");
        this.mapping.put('Z', "anterior ou silencio");
        this.mapping.put('z', "anterior ou silencio");
        
        this.mapping.put('0', "troca instrumento");
        this.mapping.put('1', "troca instrumento");
        this.mapping.put('2', "troca instrumento");
        this.mapping.put('3', "troca instrumento");
        this.mapping.put('4', "troca instrumento");
        this.mapping.put('5', "troca instrumento");
        this.mapping.put('6', "troca instrumento");
        this.mapping.put('7', "troca instrumento");
        this.mapping.put('8', "troca instrumento");
        this.mapping.put('9', "troca instrumento");
        
        this.mapping.put('?', "aumenta oitava");
        this.mapping.put('.', "aumenta oitava");
        
        this.mapping.put('\n', "I14");

        this.mapping.put(';', "I75");
        
        this.mapping.put(',', "I19");

        this.mapping.put(null, "anterior ou silencio");
    }
    
    public Converter(HashMap<Character, String> customMapping){
        this.mapping = customMapping;
    }

    
    // -  Methods:
    public Pattern parse(String inputText, int initBPM, int initInstrument, int initVolume, int initOctave){
        
        int bpm = initBPM;
        AtomicInteger volume = new AtomicInteger(initVolume);
        AtomicInteger octave = new AtomicInteger(initOctave);
        AtomicInteger instrument = new AtomicInteger(initInstrument);
        
        String lastComponent = "";
        String songDescription = descriptionSetup(initBPM, initInstrument, initVolume);
        String command = "";
        
        for(Character letter: inputText.toCharArray()){
            if (this.mapping.containsKey(letter)){
                command = this.mapping.get(letter);
            }
            else{
                command = this.mapping.get(null);
            }
            lastComponent = this.toStaccato(command, letter, lastComponent, initVolume, volume, initOctave, octave, instrument);
            System.out.println(lastComponent);
            songDescription = songDescription + lastComponent + " ";
        }
        System.out.println(songDescription);

        return new Pattern(songDescription);
    }
    
    private String toStaccato(String command, Character letter, String lastComponent,int initVolume, AtomicInteger volume, int initOctave, AtomicInteger octave, AtomicInteger instrument){
        String songComponent = "";
        
        switch(command){
            case "anterior ou silencio":
                songComponent = lastOrSilence(lastComponent);
                break;
            case "dobra volume":
                songComponent = doubleVolume(volume, initVolume);
                break;
            case "aumenta oitava":
                songComponent = increaseOctave(octave, initOctave);
                break;
            case "troca instrumento":
                songComponent = changeInstrument(instrument, letter);
                break;
            default:
                songComponent = formatNote(command, octave);
        }
        return songComponent;
    }
    
    private String lastOrSilence(String lastComponent){
        if (this.isNote(lastComponent)){
                    return lastComponent;
                }
                else{
                    return "R";
                }
    }
    
    private boolean isNote(String command){
        if (command == ""){
            return false;
        }
        switch(command.charAt(0)){
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
    
    private String doubleVolume(AtomicInteger volume, int initVolume){
        if (2 * volume.get() > this.MAX_VOLUME){
            volume.set(initVolume);
        }
        else{
            volume.set(2 * volume.get());
        }
        return MessageFormat.format(":CE(7,{0})", volume.get());
    }

    private String descriptionSetup(int initBPM, int initInstrument, int initVolume){
        return(MessageFormat.format(" T{0,number,#}  I{1,number,#} :CE(7,{2,number,#}) ", initBPM, initInstrument, initVolume));
    }
    
    private String increaseOctave(AtomicInteger octave, int initOctave){
        if (octave.get() + 1 > this.MAX_OCTAVE){
            octave.set(initOctave);
        }
        else{
            octave.set(octave.get() + 1);
        }
        return "";
    }
    
    private String changeInstrument(AtomicInteger instrument, char letter){
       
        int step = Integer.parseInt(String.valueOf(letter));
        if (instrument.get() + step> this.MAX_INSTRUMENT){
            instrument.set(instrument.get() + step - this.MAX_INSTRUMENT);
        }
        else{
            instrument.set(instrument.get() + step);
        }
        return MessageFormat.format("I{0} ", instrument.get());
    }
    
    private String formatNote(String note, AtomicInteger octave){
        return MessageFormat.format(note, octave);
    }
}
