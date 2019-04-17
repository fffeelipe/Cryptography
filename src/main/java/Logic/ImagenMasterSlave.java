/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.util.TreeMap;

/**
 *
 * @author gabriel
 */
public class ImagenMasterSlave {
    private boolean [][]master;
    private boolean [][]result;
    private boolean [][]slave;
    private int n;
    private boolean [][]background;
    private int []parent;
    private int []rank;
    private int [] moves=new int[2];
    boolean NEGRO=false, BLANCO=true;
    TreeMap<Integer,Integer> acumulado = new TreeMap<>();
    
    
    
    public ImagenMasterSlave(boolean[][] master, boolean[][] result, int n) {
        this.master = master;
        this.result = result;   
        this.n = n;
        System.out.println(n);
        this.background=new boolean[n][n];
        this.parent=new int [n];
        this.rank=new int[n];
        this.moves[0]=-1;this.moves[0]=1;
        for (int i=0;i<n;++i){
            this.rank[i]=1;
            this.parent[i]=i;
        }
        for (int i=0;i<n;++i){
            for (int j=0;j<n;++j){
                expand(i,j);
            }
        }
        for (int i=0;i<n*n;++i){
            if (acumulado.containsKey(i)){
                acumulado.replace(find(i), acumulado.get(find(i))+1);
            }else{
                acumulado.put(find(i), 1);
            }
        }
        for (int i=0;i<n;++i){
            for (int j=0;j<n;++j){
                background[i][j]= (10000<=acumulado.get(find(cartesianToIndex(i,j))));
            }
        }
        for (int i=0;i<n;++i){
            for (int j=0;j<n;++j){
                slave[i][j]= choose(master[i][j], result[i][j], i, j);
            }
        }
    }

    public boolean[][] getSlave() {
        return slave;
    }
    
    private boolean valid (int x, int y){
        if (0<=x && x<n && 0<=y && y<n){
            return true;
        }
        return false;
    }
    private int cartesianToIndex(int x, int y){
        return x*n+y;
    }
    private int find(int i){
        if (parent[i]==i){
            return i;
        }
        return parent[i] = find(parent[i]);
    }
    private void union (int i, int j){
        int root1 = find(i);
        int root2 = find(j);

        if (root2 == root1) return;

        if (rank[root1] > rank[root2]) {
          parent[root2] = root1;
        } else if (rank[root2] > rank[root1]) {
          parent[root1] = root2;
        } else {
          parent[root2] = root1;
          rank[root1]++;
        }
    }
    private void expand (int x,int y){
        if (result[x][y]==this.BLANCO ){
            return;
        }
        for (int i=0;i<2;++i){
            for (int j=0;j<2;++j){
                if (valid(x+moves[i], y+moves[j])  && result[x+moves[i]][y+moves[j]]==this.NEGRO){
                    union(cartesianToIndex(x,y), cartesianToIndex(x+moves[i], y+moves[j])); 
                }
            }
        }
    }
    private boolean choose_background(boolean m, boolean s){
        if (m==this.BLANCO  && s == this.NEGRO){
            return this.BLANCO;
        }
         if (m==this.BLANCO  && s == this.BLANCO){
            return this.NEGRO;
        }
          if (m==this.NEGRO  && s == this.BLANCO){
            return this.BLANCO;
        }
        return this.BLANCO;
    }
    private boolean choose_figure(boolean m, boolean s){
        if (m==this.BLANCO  && s == this.NEGRO){
            return this.BLANCO;
        }
         if (m==this.BLANCO  && s == this.BLANCO){
            return this.NEGRO;
        }
          if (m==this.NEGRO  && s == this.BLANCO){
            return this.BLANCO;
        }
        return this.NEGRO;
    }
    private boolean choose (boolean m , boolean s, int i, int j){
        if (background[i][j]==true){
            return choose_background (m, s);
        }
        return choose_figure (m, s);
    }
}
