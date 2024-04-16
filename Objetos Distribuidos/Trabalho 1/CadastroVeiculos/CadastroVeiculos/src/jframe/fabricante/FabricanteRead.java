/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jframe.fabricante;

import fabricante.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import jframe.veiculo.*;
import jframe.telainicial.Home;

/**
 *
 * @author Arthur
 */
public class FabricanteRead extends javax.swing.JFrame {

    /**
     * Creates new form veiculoRead
     */
    public FabricanteRead() {
        initComponents();
        listarFabricantes();
        desativarBotoes();
        
    }

    private void desativarBotoes(){
        fabricanteRead.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    // Verifica se algum item da tabela está selecionado
                    if (fabricanteRead.getSelectedRow() == -1) {
                        // Nenhum item selecionado, desativa os botões de editar e excluir
                        editar.setEnabled(false);
                        apagar.setEnabled(false);
                    } else {
                        editar.setEnabled(true);
                        apagar.setEnabled(true);
                    }
                }
            }
        });

        editar.setEnabled(false);
        apagar.setEnabled(false);
    }
    
    private void listarFabricantes() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // Obtém a lista de fabricantes do banco de dados
                FabricanteDAO fabricanteDAO = new FabricanteDAO();
                ArrayList<Fabricante> fabricantesList = fabricanteDAO.list();

                // Configura o modelo da tabela
                DefaultTableModel model = (DefaultTableModel) fabricanteRead.getModel();
                model.setRowCount(0); // Limpa a tabela antes de adicionar novos dados

                // Adiciona os fabricantes à tabela
                for (Fabricante fabricante : fabricantesList) {
                    Object[] rowData = {fabricante.getIdFabricante(), fabricante.getNome(), fabricante.getPaisOrigem()};
                    model.addRow(rowData);
                }
            }
        }, 0, 10 * 1000); // 10 segundos em milissegundos
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        voltar = new javax.swing.JButton();
        cadastrar = new javax.swing.JButton();
        apagar = new javax.swing.JButton();
        tebela = new javax.swing.JScrollPane();
        fabricanteRead = new javax.swing.JTable();
        botaoPesquisar = new javax.swing.JButton();
        campoDePesquisa = new javax.swing.JTextField();
        editar = new javax.swing.JButton();
        titulo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 255, 204));
        setMinimumSize(new java.awt.Dimension(800, 450));

        voltar.setBackground(new java.awt.Color(102, 204, 255));
        voltar.setText("Voltar");
        voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarActionPerformed(evt);
            }
        });

        cadastrar.setBackground(new java.awt.Color(204, 255, 204));
        cadastrar.setText("Cadastrar");
        cadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadastrarActionPerformed(evt);
            }
        });

        apagar.setBackground(new java.awt.Color(255, 153, 153));
        apagar.setText("Apagar");
        apagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apagarActionPerformed(evt);
            }
        });

        fabricanteRead.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Pais de Origem"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        fabricanteRead.setToolTipText("");
        fabricanteRead.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        fabricanteRead.setDropMode(javax.swing.DropMode.INSERT_ROWS);
        fabricanteRead.setUpdateSelectionOnSort(false);
        tebela.setViewportView(fabricanteRead);

        botaoPesquisar.setText("Pesquisar");
        botaoPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoPesquisarActionPerformed(evt);
            }
        });

        campoDePesquisa.setText("Digite para pesquisar...");
        campoDePesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoDePesquisaActionPerformed(evt);
            }
        });

        editar.setBackground(new java.awt.Color(255, 255, 204));
        editar.setText("Editar");
        editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarActionPerformed(evt);
            }
        });

        titulo.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        titulo.setText("Fabricante");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(apagar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(voltar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titulo)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(tebela, javax.swing.GroupLayout.PREFERRED_SIZE, 746, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(campoDePesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 615, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(botaoPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(titulo)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botaoPesquisar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoDePesquisa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tebela, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(editar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(apagar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addComponent(voltar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadastrarActionPerformed
        setVisible(false);
        new FabricanteCreate().setVisible(true);
    }//GEN-LAST:event_cadastrarActionPerformed

    private void apagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apagarActionPerformed
        // Obtém a linha selecionada na tabela
        int selectedRow = fabricanteRead.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um fabricante para apagar.");
            return;
        }

        // Obtém o ID do fabricante selecionado
        int idFabricanteSelecionado = (int) fabricanteRead.getValueAt(selectedRow, 0);

        // Exibe um JOptionPane de confirmação
        int confirmacao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja apagar este fabricante?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);

        if (confirmacao == JOptionPane.YES_OPTION) {
            // Cria e inicia uma nova thread para realizar a exclusão do fabricante no banco de dados
            Thread thread = new Thread(() -> {
                // Apaga o fabricante do banco de dados
                FabricanteDAO fabricanteDAO = new FabricanteDAO();
                int rowCount = fabricanteDAO.delete(idFabricanteSelecionado);

                // Atualiza a interface do usuário com base no resultado da exclusão
                SwingUtilities.invokeLater(() -> {
                    if (rowCount > 0) {
                        JOptionPane.showMessageDialog(this, "Fabricante apagado com sucesso.");
                        listarFabricantes();
                    } else {
                        JOptionPane.showMessageDialog(this, "Falha ao apagar fabricante.");
                    }
                });
            });
            thread.start();
        }
    }//GEN-LAST:event_apagarActionPerformed

    private void voltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarActionPerformed
        setVisible(false);
        new Home().setVisible(true);
    }//GEN-LAST:event_voltarActionPerformed

    private void botaoPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPesquisarActionPerformed
         // Obtém o termo de pesquisa
        String termoPesquisa = campoDePesquisa.getText().trim();

        // Valida se o termo de pesquisa está vazio
        if (termoPesquisa.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, digite um termo para pesquisar.");
            return;
        }

        // Cria um novo DAO e busca os fabricantes
        FabricanteDAO fabricanteDAO = new FabricanteDAO();
        ArrayList<Fabricante> fabricantesList = fabricanteDAO.searchByTerm(termoPesquisa);

        // Atualiza a tabela com os resultados da pesquisa
        DefaultTableModel model = (DefaultTableModel) fabricanteRead.getModel();
        model.setRowCount(0);

        for (Fabricante fabricante : fabricantesList) {
            Object[] rowData = { fabricante.getIdFabricante(), fabricante.getNome(), fabricante.getPaisOrigem() };
            model.addRow(rowData);
        }
    }//GEN-LAST:event_botaoPesquisarActionPerformed

    private void campoDePesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoDePesquisaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoDePesquisaActionPerformed

    private void editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarActionPerformed
        // Obtém a linha selecionada
        int selectedRow = fabricanteRead.getSelectedRow();

        // Se nenhuma linha estiver selecionada
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um fabricante para editar.");
            return;
        }

        // Obtém o ID do fabricante
        int idFabricante = (int) fabricanteRead.getValueAt(selectedRow, 0);

        // Obtém o nome e o país de origem
        String nome = (String) fabricanteRead.getValueAt(selectedRow, 1);
        String paisOrigem = (String) fabricanteRead.getValueAt(selectedRow, 2);

        // Cria um novo objeto Fabricante
        Fabricante fabricanteSelecionado = new Fabricante(idFabricante, nome, paisOrigem);

        
        setVisible(false);
        new FabricanteUpdate(fabricanteSelecionado).setVisible(true);
    }//GEN-LAST:event_editarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FabricanteRead.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FabricanteRead.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FabricanteRead.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FabricanteRead.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FabricanteRead().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton apagar;
    private javax.swing.JButton botaoPesquisar;
    private javax.swing.JButton cadastrar;
    private javax.swing.JTextField campoDePesquisa;
    private javax.swing.JButton editar;
    private javax.swing.JTable fabricanteRead;
    private javax.swing.JScrollPane tebela;
    private javax.swing.JLabel titulo;
    private javax.swing.JButton voltar;
    // End of variables declaration//GEN-END:variables
}
