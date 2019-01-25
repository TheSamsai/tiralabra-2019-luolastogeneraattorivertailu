/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

/**
 *
 * @author sami
 */
public class ConsoleIO implements IO {

    @Override
    public void print(String string) {
        System.out.print(string);
    }

    @Override
    public void println(String string) {
        System.out.println(string);
    }

    @Override
    public String read() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
