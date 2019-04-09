/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author fffeelipe
 */
public class Decoder {

    public static void main(String[] args) {
        BufferedReader objReader = null;
        
        String number = "";
        
        try {

            objReader = new BufferedReader(new FileReader("material/number1.txt"));
            
            number = objReader.readLine();

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {
                if (objReader != null) {
                    objReader.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
        
        //System.out.println(number.substring(0, 10));
        
        String[] chunks = number.split("1", 0);
        TreeMap<String,Integer> occurrences = new TreeMap<>();
        for(String s : chunks){
            if(occurrences.containsKey(s)){
                occurrences.put(s, occurrences.get(s)+1);
            }else{
                occurrences.put(s, 1);
            }
        }
        for(Map.Entry<String,Integer> entry : occurrences.entrySet()) {
            System.out.println(entry.getKey());
        }

        

    }
}
