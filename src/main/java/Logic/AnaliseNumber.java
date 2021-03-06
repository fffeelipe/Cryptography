/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.TreeMap;
import java.util.Vector;

/**
 *
 * @author gabriel
 */
public class AnaliseNumber {
    Vector<Bloque> bloque= new  Vector<Bloque>();
    TreeMap<String,Integer> occurrence = new TreeMap<>();
    int[] imageIndex;
    char[] image;
    String generador;
    
    
    public AnaliseNumber(String s){
        generador=s;
        String acumulado="";
        Bloque foo;
        int cnt=0, seen=0;
        for (int i=0;i< s.length();++i){
            if ( s.charAt(i)=='1'  && acumulado.length()!=0){
                if ( occurrence.containsKey(acumulado)){
                    bloque.get(occurrence.get(acumulado)).update(acumulado,  i-acumulado.length(), cnt );
                }else{
                    foo=new Bloque(acumulado,i-acumulado.length(),cnt);
                    bloque.add(foo);
                    occurrence.put(acumulado, seen);
                    seen++;
                }
                acumulado="1";
                cnt++;
                continue;
            }
            acumulado+=s.charAt(i);
            
        }
        if (acumulado.length()!=0){
            if ( occurrence.containsKey(acumulado)){
                bloque.get(occurrence.get(acumulado)).update(acumulado,  s.length()-acumulado.length(), cnt );
            }else{
                foo=new Bloque(acumulado,s.length()-acumulado.length(),cnt);
                bloque.add(foo);
                occurrence.put(acumulado, cnt);
            }   
            
        }
        
    }
    public Vector<Bloque> masGrande(){
        Vector<Bloque> grande=new Vector<>();
        int mx= 0;
        for (int i=0;i<bloque.size();++i){
            if (bloque.elementAt(i).tamano == mx){
                grande.add(bloque.elementAt(i));
            }
            if (bloque.elementAt(i).tamano > mx){
                grande.clear();
                grande.add(bloque.elementAt(i));
                mx=bloque.elementAt(i).tamano;
            }
            
        }
        return grande;
    } 
    public Vector<Bloque> masPequeno(){
        Vector<Bloque> pequeno=new Vector<>();
        int mn= 100000000;
        for (int i=0;i<bloque.size();++i){
            if (bloque.elementAt(i).tamano == mn){
                pequeno.add(bloque.elementAt(i));
            }
            if (bloque.elementAt(i).tamano < mn){
                pequeno.clear();
                pequeno.add(bloque.elementAt(i));
                mn=bloque.elementAt(i).tamano;
            }
            
        }
        return pequeno;
    }
    
    //Hacemos un orden por frecuencias
    public void ordenarFrecuencias(){
        Collections.sort(bloque, new BloqueComparadorPorFrecuencia() );
        ajustarOccurrence();
    }
    public void ordenarTransiciones(){
        Collections.sort(bloque, new BloqueComparadorPorTransiciones() );
        ajustarOccurrence();
    }
    public void ordenarLoops(){
        Collections.sort(bloque, new BloqueComparadorPorLoops() );
        ajustarOccurrence();
    }
    private void ajustarOccurrence() {
        occurrence.clear();
        for (int i=0;i<bloque.size();++i){
            occurrence.put(bloque.elementAt(i).id, i);
        }
    }
    
    public Vector<Bloque> getBlocks(){
        return bloque;
    }
    //Creates image from already given string S
    //image is saved in image as array
    //imageIndex[i] is the index of the block in bloque for which the i element belongs to
    public void generateImage(){
        this.image=new char[this.generador.length()];
        this.imageIndex= new int[this.generador.length()];
        TreeMap<Integer,Integer> blockFromIndex = new TreeMap<>();
        for (int i=0;i<bloque.size();++i){
            for (int j=0;j<bloque.get(i).seen_idx.size();++j){
                blockFromIndex.put(bloque.get(i).seen_idx.elementAt(j), i);
            }
        }
        int blocks_seen=0;
        for (int i=0;i<generador.length();++i){
            if (generador.charAt(i)=='1'){
                blocks_seen++;
                this.imageIndex[i]=blockFromIndex.get(i);
            }else{
                this.imageIndex[i]=this.imageIndex[Math.max(i-1, 0)];
            }
            this.image[i]=(blocks_seen%2==0?'n':'b');

        }
        
    }
    
    
    
    
}
