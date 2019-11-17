package br.ufrgs.inf.model;

import br.ufrgs.inf.view.Text2Music;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MyFile {

    // - Attributes:
    private final JFileChooser openFileChooser, saveFileChooser;
    private File openedFile, savedFile;
    private String path;
    private String content;

    // - Constructors:
    public MyFile() {
        openFileChooser = new JFileChooser();
        openFileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
        saveFileChooser = new JFileChooser();
    }

    public MyFile(String path) {
        openFileChooser = new JFileChooser();
        openFileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
        saveFileChooser = new JFileChooser();
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

    public boolean openFile(Text2Music userInterface) {
        int returnValue = openFileChooser.showOpenDialog(userInterface);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            openedFile = openFileChooser.getSelectedFile();
            path = openedFile.getAbsolutePath();
            content = usingBufferedReader(path);
            userInterface.setText(content);
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

    public boolean saveFile(Text2Music userInterface) {
        int returnValue = saveFileChooser.showSaveDialog(null);
        content = userInterface.getText();
        System.out.println(content);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {

                savedFile = saveFileChooser.getSelectedFile();
                path = savedFile.getAbsolutePath();
                System.out.println(path);
                Files.write(Paths.get(path), content.getBytes());
            } catch (IOException ex) {

            }
        }
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
