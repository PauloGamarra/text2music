package br.ufrgs.inf.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class MyFile {

    // - Attributes:
    private String path;
    private String content;

    // - Constructors:
    public MyFile() {
    }

    public MyFile(String path) {
        this.path = path;
    }

    // -  Gets & Sets:
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // -  Methods:
    public String getContent() {
        return content;
    }

    public boolean readFile() {

        if (isTxt()) {
            String buffer = "";
            String contentFile = "";

            try {
                FileReader fileReader = new FileReader(this.path);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                while ((buffer = bufferedReader.readLine()) != null) {
                    contentFile += (buffer + "\n");
                }

                fileReader.close();
            } catch (Exception e) {
                System.out.println(e);
                return false;
            }

            this.content = contentFile;
            return true;
        }
        
        return false;
    }

    public boolean saveFile() {

        if (isTxt()) {
            try {
                FileWriter fileWriter = new FileWriter(this.path);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                bufferedWriter.write(this.content);

                bufferedWriter.close();
                fileWriter.close();

            } catch (Exception e) {
                System.out.println(e);
                return false;
            }

            return true;
        }
        
        return false;
    }

    public boolean isTxt() {
        return this.path.endsWith(".txt");
    }

}
