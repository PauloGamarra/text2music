package br.ufrgs.inf.application;

import br.ufrgs.inf.controller.Controller;
import br.ufrgs.inf.model.MyFile;
import br.ufrgs.inf.model.Converter;
import br.ufrgs.inf.model.Song;
import br.ufrgs.inf.view.Text2Music;
import org.jfugue.player.Player;
import org.jfugue.pattern.Pattern;


public class MyApp {  

    public static void main(String[] args) {
        Song song = new Song();
        Converter converter = new Converter();
        Player player = new Player ();     
        Pattern jFugueSong = null;

        Text2Music userInterface = new Text2Music();
        userInterface.setVisible(true);
        MyFile file = new MyFile();
        Controller controller = new Controller(converter, file, jFugueSong, player, song, userInterface);
        userInterface.setController(controller);
    }
}
