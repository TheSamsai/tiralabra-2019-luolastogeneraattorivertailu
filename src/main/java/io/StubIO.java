/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sami
 */
public class StubIO implements IO {
    ArrayList<String> output;
    ArrayList<String> input;
    
    public StubIO() {
        output = new ArrayList<>();
        input = new ArrayList<>();
    }

    public List<String> getOutput() {
        return output;
    }
    
    public List<String> getInput() {
        return input;
    }
    
    public void addInput(String string) {
        input.add(string);
    }
    
    @Override
    public void print(String string) {
        output.add(string);
    }

    @Override
    public void println(String string) {
        output.add(string);
    }

    @Override
    public String read() {
        return input.remove(0);
    }
}
