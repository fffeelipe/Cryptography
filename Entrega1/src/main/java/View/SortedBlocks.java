/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Logic.Bloque;
import java.util.Vector;
import javax.swing.JFrame;

/**
 *
 * @author fffeelipe
 */
public class SortedBlocks extends javax.swing.JFrame {
    Vector<Bloque> v;
    /**
     * Creates new form SortedBlocks
     */
    public SortedBlocks(Vector<Bloque> v, int mode) {
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.v = v;
        initComponents();
        String[][] v2 = null;
        String[] titles = null;
        if(mode == 1){
            v2 = new String[v.size()][2];
            int i = 0;
            for(Bloque b : v){
                v2[i][0]=String.valueOf(b.frecuencia) ;
                v2[i][1]=b.id;
                ++i;
            }
            titles = new String[] {"Frequency", "Block"};
        }
        if(mode == 2){
            v2 = new String[v.size()][3];
            int i = 0;
            for(Bloque b : v){
                v2[i][0]=String.valueOf(b.transicionBN);
                v2[i][1]=String.valueOf(b.transicionNB);
                v2[i][2]=b.id;
                ++i;
            }
            titles = new String[] {"transitionBW","transitionWB", "Block"};
        }
        if(mode == 3){
            v2 = new String[v.size()][3];
            int i = 0;
            for(Bloque b : v){
                v2[i][0]=String.valueOf(b.cicloB);
                v2[i][1]=String.valueOf(b.cicloN);
                v2[i][2]=b.id;
                ++i;
            }
            titles = new String[] {"White Cycle","Black Cycle", "Block"};
        }
        if(mode == 4){
            v2 = new String[v.size()][2];
            int i = 0;
            for(Bloque b : v){
                v2[i][0]=String.valueOf(b.gcd) ;
                v2[i][1]=b.id;
                ++i;
            }
            titles = new String[] {"GCD", "Block"};
        }
        
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
             v2, titles
    ));
        
 } 

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
