//Arthur

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jframe.telainicial;

import jframe.fabricacao.FabricacaoRead;
import jframe.fabricante.FabricanteRead;
import jframe.veiculo.VeiculoRead;

/**
 *
 * @author Arthur
 */
public class Home extends javax.swing.JFrame {

    public Home() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        veiculo = new javax.swing.JButton();
        Fabricante1 = new javax.swing.JButton();
        fabricacao = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cadastro de Veículos");
        setBackground(new java.awt.Color(204, 204, 204));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(350, 450));
        setResizable(false);

        veiculo.setBackground(new java.awt.Color(204, 255, 204));
        veiculo.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        veiculo.setText("Veículo");
        veiculo.setActionCommand("veiculo");
        veiculo.setAlignmentX(0.5F);
        veiculo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        veiculo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        veiculo.setPreferredSize(new java.awt.Dimension(350, 450));
        veiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                veiculoActionPerformed(evt);
            }
        });

        Fabricante1.setBackground(new java.awt.Color(204, 255, 204));
        Fabricante1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        Fabricante1.setText("Fabricante");
        Fabricante1.setActionCommand("veiculo");
        Fabricante1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Fabricante1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Fabricante1.setPreferredSize(new java.awt.Dimension(350, 450));
        Fabricante1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Fabricante1ActionPerformed(evt);
            }
        });

        fabricacao.setBackground(new java.awt.Color(255, 255, 204));
        fabricacao.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        fabricacao.setText("Fabricação");
        fabricacao.setActionCommand("veiculo");
        fabricacao.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        fabricacao.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        fabricacao.setPreferredSize(new java.awt.Dimension(350, 450));
        fabricacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fabricacaoActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Cadastro de Veículos");

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Arthur Cristovão");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(Fabricante1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(veiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(fabricacao, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(fabricacao, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(veiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Fabricante1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jLabel2)
                .addGap(29, 29, 29))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void veiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_veiculoActionPerformed
        setVisible(false);
        new VeiculoRead().setVisible(true);
    }//GEN-LAST:event_veiculoActionPerformed

    private void fabricacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fabricacaoActionPerformed
        setVisible(false);
        new FabricacaoRead().setVisible(true);
    }//GEN-LAST:event_fabricacaoActionPerformed

    private void Fabricante1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Fabricante1ActionPerformed
        setVisible(false);
        new FabricanteRead().setVisible(true);
    }//GEN-LAST:event_Fabricante1ActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Fabricante1;
    private javax.swing.JButton fabricacao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton veiculo;
    // End of variables declaration//GEN-END:variables

    public static class setVisible {

        public setVisible() {
        }

        public setVisible(boolean b) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }
}
