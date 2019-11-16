package br.ufrgs.inf.application;

import br.ufrgs.inf.controller.Controller;
import br.ufrgs.inf.model.MyFile;
import br.ufrgs.inf.model.Converter;
import br.ufrgs.inf.view.Text2Music;
import org.jfugue.player.Player;
import org.jfugue.pattern.Pattern;


public class MyApp {  

    public static void main(String[] args) {
        
<<<<<<< HEAD
        String text = "Mais popula?res que Jesus\" (?do inglês \"Mo?re popular than ?Jesus\")[nota 1] foi pa/?rte de um?a observaçã?o feita pel?o mús?ico John Lenno?n, dos ?Beatles, em uma? entrevist?a de março de 19?66, na qual e?le argumentou ??que o público estava mais apaixonado pela banda do que p?or Jesus, e que a fé cristã? estava declinando na medida em que? era superada pelo rock. Suas opiniões não causa?ram controvérsia quando publicadas originalmente no jornal londri?no The Evening Standard, mas provo?caram reações rai?vosas das comunidade?s cristãs quando rep?ublicadas nos Estados Unidos em? julho.".toUpperCase();
        
        Converter converter = new Converter();
        
        Player player = new Player ();
        
        Pattern song = converter.parse(text, 120, 1, 127, 0);
        player.play(song);
=======
        Text2Music text2music = new Text2Music();
        text2music.setVisible(true);
        MyFile file = new MyFile();
        Controller controller = new Controller(file);
        text2music.setController(controller);
>>>>>>> origin/master
    }
}
