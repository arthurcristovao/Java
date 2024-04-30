/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jframe.veiculo;

import fabricacao.Fabricacao;
import fabricacao.FabricacaoDAO;
import java.awt.List;
import java.awt.Point;
import java.util.ArrayList;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.util.Timer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import jframe.telainicial.Home;
import veiculo.*;

/**
 *
 * @author Arthur
 */
public class VeiculoRead extends javax.swing.JFrame {
    private boolean pesquisaAtiva = false;
    private Timer timer;
    private int tempoAtualização = 10; //Segundos

    public VeiculoRead() {
        initComponents();
        iniciarTimer();
        desativarBotoes();  
    }
    
    private void desativarBotoes(){
        veiculoRead.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    
                    if (veiculoRead.getSelectedRow() == -1) {
                        
                        editar.setEnabled(false);
                        apagar.setEnabled(false);
                    } else {
                        editar.setEnabled(true);
                        apagar.setEnabled(true);
                    }
                }
            }
        });
   
        botaoLimpar.setEnabled(false);
        editar.setEnabled(false);
        apagar.setEnabled(false);
    }
    
    private void iniciarTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!pesquisaAtiva) {
                    listarVeiculos();
                }
            }
        }, 0, tempoAtualização * 1000);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        voltar = new javax.swing.JButton();
        cadastrar = new javax.swing.JButton();
        apagar = new javax.swing.JButton();
        tabela = new javax.swing.JScrollPane();
        veiculoRead = new javax.swing.JTable();
        botaoPesquisar = new javax.swing.JButton();
        campoDePesquisa = new javax.swing.JTextField();
        editar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        botaoLimpar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Veículo - Principal");
        setBackground(new java.awt.Color(153, 255, 204));
        setMinimumSize(new java.awt.Dimension(800, 450));
        setResizable(false);

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

        veiculoRead.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Cor", "Modelo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        veiculoRead.setToolTipText("");
        tabela.setViewportView(veiculoRead);
        if (veiculoRead.getColumnModel().getColumnCount() > 0) {
            veiculoRead.getColumnModel().getColumn(0).setPreferredWidth(30);
        }

        botaoPesquisar.setBackground(new java.awt.Color(204, 255, 204));
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

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel4.setText("Fabricante");

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel5.setText("Veículo");

        botaoLimpar.setBackground(new java.awt.Color(102, 204, 255));
        botaoLimpar.setText("Limpar");
        botaoLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoLimparActionPerformed(evt);
            }
        });

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5)
                    .addComponent(tabela, javax.swing.GroupLayout.PREFERRED_SIZE, 746, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(campoDePesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoLimpar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(51, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(443, 443, 443)
                    .addComponent(jLabel4)
                    .addContainerGap(443, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(editar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(apagar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(96, 96, 96)
                        .addComponent(voltar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(campoDePesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botaoPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tabela, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap(23, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(209, 209, 209)
                    .addComponent(jLabel4)
                    .addContainerGap(209, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private void listarVeiculos() {
        Point posicaoScroll = tabela.getViewport().getViewPosition();
        int indiceSelecionado = veiculoRead.getSelectedRow();

        VeiculoDAO veiculoDAO = new VeiculoDAO();
        ArrayList<Veiculo> veiculosList = veiculoDAO.list();

        DefaultTableModel model = (DefaultTableModel) veiculoRead.getModel();
        model.setRowCount(0); 

        for (Veiculo veiculo : veiculosList) {
            Object[] rowData = { veiculo.getIdVeiculo(), veiculo.getNome(), veiculo.getCor(), veiculo.getModelo() };
            model.addRow(rowData);
        }

        if (indiceSelecionado >= 0 && indiceSelecionado < veiculoRead.getRowCount()) {
            veiculoRead.setRowSelectionInterval(indiceSelecionado, indiceSelecionado);
        } else {
            veiculoRead.clearSelection();
        }

        tabela.getViewport().setViewPosition(posicaoScroll);
    }   
  
    
    private void cadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadastrarActionPerformed
        setVisible(false);
        new VeiculoCreate().setVisible(true);
    }//GEN-LAST:event_cadastrarActionPerformed

    private void apagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apagarActionPerformed
        Thread thread = new Thread(() -> {
            int selectedRow = veiculoRead.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Por favor, selecione um veículo para apagar.");
                return;
            }

            int idVeiculoSelecionado = (int) veiculoRead.getValueAt(selectedRow, 0);

            FabricacaoDAO fabricacaoDAO = new FabricacaoDAO();
            ArrayList<Fabricacao> fabricacoesRelacionadas = fabricacaoDAO.getFabricacoesByVeiculoId(idVeiculoSelecionado);

            if (!fabricacoesRelacionadas.isEmpty()) {
                int confirmacao = JOptionPane.showConfirmDialog(this, "Este veículo está relacionado a fabricações. Deseja apagar também todas as fabricações relacionadas?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);

                if (confirmacao == JOptionPane.YES_OPTION) {
                    int rowCount = fabricacaoDAO.deleteFabricacoesByVeiculoId(idVeiculoSelecionado);
                    
                    if (rowCount > 0) {
                        VeiculoDAO veiculoDAO = new VeiculoDAO();
                        int rowCountVeiculo = veiculoDAO.delete(idVeiculoSelecionado);

                        SwingUtilities.invokeLater(() -> {
                            if (rowCountVeiculo > 0) {
                                JOptionPane.showMessageDialog(this, "Veículo e fabricações relacionadas apagados com sucesso.");
                                listarVeiculos();
                            } else {
                                JOptionPane.showMessageDialog(this, "Falha ao apagar veículo e fabricações relacionadas.");
                            }
                        });
                    }
                }
            } else {
                int confirmacao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja apagar este veículo?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);

                if (confirmacao == JOptionPane.YES_OPTION) {
                    VeiculoDAO veiculoDAO = new VeiculoDAO();
                    int rowCountVeiculo = veiculoDAO.delete(idVeiculoSelecionado);

                    SwingUtilities.invokeLater(() -> {
                        if (rowCountVeiculo > 0) {
                            JOptionPane.showMessageDialog(this, "Veículo apagado com sucesso.");
                            listarVeiculos();
                        } else {
                            JOptionPane.showMessageDialog(this, "Falha ao apagar veículo.");
                        }
                    });
                }
            }
        });
        thread.start();
    }//GEN-LAST:event_apagarActionPerformed

    private void voltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarActionPerformed
        setVisible(false);
        new Home().setVisible(true);
    }//GEN-LAST:event_voltarActionPerformed

    private void botaoPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPesquisarActionPerformed
        pesquisaAtiva = true;
        timer.cancel();
        
        Thread thread = new Thread(() -> {
            
            
            String termoPesquisa = campoDePesquisa.getText().trim();

            if (termoPesquisa.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, digite um termo para pesquisar.");
                return;
            }

            VeiculoDAO veiculoDAO = new VeiculoDAO();
            ArrayList<Veiculo> veiculosList = veiculoDAO.searchByTerm(termoPesquisa);

            DefaultTableModel model = (DefaultTableModel) veiculoRead.getModel();
            model.setRowCount(0);

            for (Veiculo veiculo : veiculosList) {
                Object[] rowData = { veiculo.getIdVeiculo(), veiculo.getNome(), veiculo.getCor(), veiculo.getModelo() };
                model.addRow(rowData);
            }
            SwingUtilities.invokeLater(() -> {
                iniciarTimer();
                botaoLimpar.setEnabled(true);
            });

        });
        thread.start();
    }//GEN-LAST:event_botaoPesquisarActionPerformed

    private void campoDePesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoDePesquisaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoDePesquisaActionPerformed

    private void editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarActionPerformed
        Thread thread = new Thread(() -> {
            int selectedRow = veiculoRead.getSelectedRow();

            int id = (int) veiculoRead.getValueAt(selectedRow, 0); 
            String nome = (String) veiculoRead.getValueAt(selectedRow, 1); 
            String cor = (String) veiculoRead.getValueAt(selectedRow, 2); 
            String modelo = (String) veiculoRead.getValueAt(selectedRow, 3); 

            Veiculo veiculoSelecionado = new Veiculo(id, nome, cor, modelo);

            setVisible(false);
            new VeiculoUpdate(veiculoSelecionado).setVisible(true);
        });
        thread.start();
    }//GEN-LAST:event_editarActionPerformed

    private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparActionPerformed
        pesquisaAtiva = false;
        timer.cancel(); 
        listarVeiculos();
        iniciarTimer();
        
        veiculoRead.clearSelection();
        campoDePesquisa.setText("Digite para pesquisar...");
        botaoLimpar.setEnabled(false);
    }//GEN-LAST:event_botaoLimparActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton apagar;
    private javax.swing.JButton botaoLimpar;
    private javax.swing.JButton botaoPesquisar;
    private javax.swing.JButton cadastrar;
    private javax.swing.JTextField campoDePesquisa;
    private javax.swing.JButton editar;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane tabela;
    private javax.swing.JTable veiculoRead;
    private javax.swing.JButton voltar;
    // End of variables declaration//GEN-END:variables
}
