package br.ufrgs.inf.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;

public class MyFile {

    // - Attributes:
    private final JFileChooser openFileDialog, saveFileDialog;
    private File openedFile, savedFile;
    private String path;
    private String content;

    // - Constructors:
    public MyFile() {
        openFileDialog = new JFileChooser();
        openFileDialog.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
        saveFileDialog = new JFileChooser();
    }

    public MyFile(String path) {
        openFileDialog = new JFileChooser();
        openFileDialog.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
        saveFileDialog = new JFileChooser();
        this.path = path;
    }

    // -  Gets & Sets:
    public String getPath() {
        return path;
    }

    // -  Methods:
    public String getContent() {
        return content;
    }

    public boolean openFile() {
        int returnValue = openFileDialog.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            openedFile = openFileDialog.getSelectedFile();
            path = openedFile.getAbsolutePath();
            content = usingBufferedReader(path);
            return true;
        } else {
            return false;
        }
    }

    public boolean openFile(MyFile file) {
        String buffer = "";
        String contentFile = "";

        try {
            FileReader fileReader = new FileReader(file.path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((buffer = bufferedReader.readLine()) != null) {
                contentFile += (buffer + "\n");
            }

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

        this.content = contentFile;
        return true;
    }

    public boolean saveFile(String content) {
        int returnValue = saveFileDialog.showSaveDialog(null);
        this.content = content;
        //System.out.println(content);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {

                savedFile = saveFileDialog.getSelectedFile();
                path = savedFile.getAbsolutePath();
                Files.write(Paths.get(path), content.getBytes());
            } catch (IOException ex) {

            }
        }
        return true;
    }

    public boolean saveMIDI(Pattern pattern) throws IOException {
        int returnValue = saveFileDialog.showSaveDialog(null);
        savedFile = saveFileDialog.getSelectedFile();
        MidiFileManager.savePatternToMidi(pattern, savedFile);
        return true;
    }

    private static String usingBufferedReader(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                contentBuilder.append(sCurrentLine).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }

}
