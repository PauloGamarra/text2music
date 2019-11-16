/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrgs.inf.controller;

import br.ufrgs.inf.model.MyFile;
import br.ufrgs.inf.view.Text2Music;

/**
 *
 * @author leodroves
 */
public class Controller{
    MyFile file;
    
    public Controller(MyFile file){
        this.file = file;
    }
    
    public void openFile(Text2Music text2music){
        if(!file.openFile(text2music)){
            
        }      
    }
    
    public void saveFile(Text2Music text2music){
        if(!file.saveFile(text2music)){
            
        }
    }
}
