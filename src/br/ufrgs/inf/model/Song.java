/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrgs.inf.model;

public class Song {
    // - Attributes:
    private String text = new String();
    private int initBPM;
    private int initInstrument;
    private int initVolume;
    private int initOctave;
    
    public Song(){
        
    }
    
    public Song(String text, int initBPM, int initInstrument, int initVolume, int initOctave){
        this.text = text;
        this.initBPM = initBPM;
        this.initInstrument = initInstrument;
        this.initVolume = initVolume;
        this.initOctave = initOctave;
    }
    
    
    public void setText(String text){
        this.text = text;
    }
    
    public String getText(){
        return this.text;
    }
    
    public void setInitBPM(int bpm){
        this.initBPM = bpm;
    }
    
    public int getInitBPM(){
        return this.initBPM;
    }
    
    public void setInitinstrument(int instrument){
        this.initInstrument = instrument;
    }
    
    public int getInitinstrument(){
        return this.initInstrument;
    }
    
    public void setInitVolume(int volume){
        this.initVolume = volume;
    }
    
    public int getInitVolume(){
        return this.initVolume;
    }
    
    public void setInitOctave(int octave){
        this.initOctave = octave;
    } 
    
    public int getInitOctave(){
        return this.initOctave;
    }
}
