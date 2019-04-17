/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

/**
 *
 * @author gabriel
 */
public class Decrypt {
    private boolean [][]master;
    private boolean [][]encripted;
    private boolean [][]decripted;
    private int n;
    boolean NEGRO=true, BLANCO=false;

    public Decrypt(boolean[][] master, boolean[][] encripted, int n) {
        this.master = master;
        this.encripted = encripted;
        this.n = n;
        this.decripted=new boolean [n][n];
        for (int i=0;i<n;++i){
            for (int j=0;j<n;++j){
                if (encripted[i][j]==this.BLANCO && master[i][j]==this.BLANCO){
                    decripted[i][j]= this.BLANCO;
                }else{
                    decripted[i][j]=this.NEGRO;
                }
            }
        }
    }

    public boolean[][] getDecripted() {
        return decripted;
    }
    
    
}
