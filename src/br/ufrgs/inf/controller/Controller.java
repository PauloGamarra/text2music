/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrgs.inf.controller;

import br.ufrgs.inf.model.Converter;
import br.ufrgs.inf.model.MyFile;
import br.ufrgs.inf.model.Song;
import br.ufrgs.inf.view.Text2Music;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

/**
 *
 * @author leodroves
 */
public class Controller{
    MyFile file;
    Song song;
    Converter converter;
    Player player;
    Pattern pattern;
    Text2Music userInterface;
    
    public Controller(Converter converter, MyFile file, Pattern pattern, Player player, Song song, Text2Music userInterface){
        this.file = file;
        this.song = song;
        this.converter = converter;
        this.player = player;
        this.pattern = pattern;
        this.userInterface = userInterface;
    }
    
    public void openFile(){
        file.openFile();
        System.out.println(file.getContent());
        userInterface.setText(file.getContent());
    }
    
    public void saveFile(){
        if(!file.saveFile(userInterface.getText())){
            userInterface.setStatus("Not able to save this file");
        }
    }

    public void convertSong() {
        song.setInitBPM(userInterface.getBPM());
        song.setInitOctave(9);
        song.setInitVolume(userInterface.getVolume());
        song.setInitinstrument(userInterface.getInstrument());
        song.setText(userInterface.getText());
        pattern = converter.parse(song);
    }

    public void playSong() {
        player.play(pattern);
    }
}
