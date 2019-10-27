package br.ufrgs.inf.application;

import br.ufrgs.inf.model.MyFile;
import br.ufrgs.inf.view.Text2Music;

public class MyApp {

    public static void main(String[] args) {
        
        new Text2Music().setVisible(true);
        /*
        String pathzao = "C:\\Users\\Richard\\Desktop\\test.bin";
        MyFile file = new MyFile(pathzao);
        
        if (file.isTxt()) {
            if (file.readFile()) {
                System.out.println(file.getContent());
            }

            file.setContent(file.getContent() + "NoisEhTop");
            file.saveFile();

            file = new MyFile("C:\\Users\\Richard\\Desktop\\test.txt");
            if (file.readFile()) {
                System.out.println(file.getContent());
            }

        } else {
            System.out.println("Nao deu seu merda");
        }*/
    }
}
