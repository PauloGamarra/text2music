package br.ufrgs.inf.application;

import br.ufrgs.inf.controller.Controller;
import br.ufrgs.inf.model.MyFile;
import br.ufrgs.inf.view.Text2Music;

public class MyApp {  

    public static void main(String[] args) {
        
        Text2Music text2music = new Text2Music();
        text2music.setVisible(true);
        MyFile file = new MyFile();
        Controller controller = new Controller(file);
        text2music.setController(controller);
    }
}
