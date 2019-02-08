/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import java.util.Scanner;

/**
 *
 * @author sami
 */
public class ConsoleIO implements IO {
    
    Scanner scan;
    
    public ConsoleIO() {
        scan = new Scanner(System.in);
    }
    
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
        return scan.nextLine();
    }
    
}
