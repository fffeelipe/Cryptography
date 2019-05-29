/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.util.Vector;

/**
 *
 * @author gabriel
 */
public class Bloque {
    public String id;
    public Vector<Integer> seen_idx=new Vector<>(), seen_block=new Vector<>();
    public Vector<String> color=new Vector<>();
    public int cicloN=0, cicloB=0, transicionNB=0, transicionBN=0, tamano, frecuencia=1;
    
    Bloque(String numero, int idx, int block_idx){
        this.id=numero;
        this.tamano=numero.length();
        this.seen_idx.add(idx);
        this.seen_block.add(block_idx);
        this.color.add(idx%2==0?"n":"b");
    }
    public void update (String numero, int idx, int block_idx){
        this.frecuencia++;
        String current_color=(idx%2==0?"n":"b");
        if ( this.color.lastElement() == current_color){
            if (current_color=="b"){
                this.cicloB++;
            }else{
                this.cicloN++;
            }
        }else{
            if ( current_color=="n"){
                this.transicionBN++;
            }else{
                this.transicionNB++;
            }
        }
        this.color.add(current_color);
        this.seen_idx.add(idx);
        this.seen_block.add(block_idx);
    }
}
