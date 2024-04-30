/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jframe.fabricante;

import fabricacao.Fabricacao;
import fabricacao.FabricacaoDAO;
import fabricante.*;
import java.awt.Point;
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
import main.CadastrarEmMassa;

/**
 *
 * @author Arthur
 */
public class FabricanteRead extends javax.swing.JFrame {
    private boolean pesquisaAtiva = false;
    private Timer timer;
    private int tempoAtualização = 10; //Segundos

    public FabricanteRead() {
        initComponents();
        iniciarTimer();
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
                    listarFabricantes();
                }
            }
        }, 0, tempoAtualização * 1000);
    }
    
    private void listarFabricantes() {
        Point posicaoScroll = tabela.getViewport().getViewPosition();
        int indiceSelecionado = fabricanteRead.getSelectedRow();

        FabricanteDAO fabricanteDAO = new FabricanteDAO();
        ArrayList<Fabricante> fabricantesList = fabricanteDAO.list();

        DefaultTableModel model = (DefaultTableModel) fabricanteRead.getModel();
        model.setRowCount(0); 

        for (Fabricante fabricante : fabricantesList) {
            Object[] rowData = { fabricante.getIdFabricante(), fabricante.getNome(), fabricante.getPaisOrigem() };
            model.addRow(rowData);
        }

        if (indiceSelecionado >= 0 && indiceSelecionado < fabricanteRead.getRowCount()) {
            fabricanteRead.setRowSelectionInterval(indiceSelecionado, indiceSelecionado);
        } else {
            fabricanteRead.clearSelection();
        }

        tabela.getViewport().setViewPosition(posicaoScroll);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        voltar = new javax.swing.JButton();
        cadastrar = new javax.swing.JButton();
        apagar = new javax.swing.JButton();
        tabela = new javax.swing.JScrollPane();
        fabricanteRead = new javax.swing.JTable();
        botaoPesquisar = new javax.swing.JButton();
        campoDePesquisa = new javax.swing.JTextField();
        editar = new javax.swing.JButton();
        titulo = new javax.swing.JLabel();
        botaoLimpar = new javax.swing.JButton();

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
        tabela.setViewportView(fabricanteRead);

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

        titulo.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        titulo.setText("Fabricante");

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titulo)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(campoDePesquisa)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(botaoPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(tabela, javax.swing.GroupLayout.PREFERRED_SIZE, 746, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(campoDePesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botaoPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tabela, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        Thread thread = new Thread(() -> {
            // Obtém a linha selecionada na tabela
            int selectedRow = fabricanteRead.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Por favor, selecione um fabricante para apagar.");
                return;
            }

            // Obtém o ID do fabricante selecionado
            int idFabricanteSelecionado = (int) fabricanteRead.getValueAt(selectedRow, 0);

            FabricacaoDAO fabricacaoDAO = new FabricacaoDAO();
            ArrayList<Fabricacao> fabricacoesRelacionadas = fabricacaoDAO.getFabricacoesByFabricanteId(idFabricanteSelecionado);

            if (!fabricacoesRelacionadas.isEmpty()) {
                int confirmacao = JOptionPane.showConfirmDialog(this, "Este fabricante está relacionado a fabricações. Deseja apagar também todas as fabricações relacionadas?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);

                if (confirmacao == JOptionPane.YES_OPTION) {
                    // Apaga todas as fabricações relacionadas ao fabricante
                    int rowCount = fabricacaoDAO.deleteFabricacoesByFabricanteId(idFabricanteSelecionado);

                    if (rowCount > 0) {
                        FabricanteDAO fabricanteDAO = new FabricanteDAO();
                        int rowCountFabricante = fabricanteDAO.delete(idFabricanteSelecionado);

                        // Atualiza a interface do usuário com base no resultado da exclusão do fabricante
                        SwingUtilities.invokeLater(() -> {
                            if (rowCountFabricante > 0) {
                                JOptionPane.showMessageDialog(this, "Fabricante e fabricações relacionadas apagados com sucesso.");
                                listarFabricantes();
                            } else {
                                JOptionPane.showMessageDialog(this, "Falha ao apagar fabricante e fabricações relacionadas.");
                            }
                        });
                    }
                }
            } else {
                // Não há fabricações relacionadas, então apaga apenas o fabricante
                int confirmacao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja apagar este fabricante?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);

                if (confirmacao == JOptionPane.YES_OPTION) {
                    // Apaga o fabricante do banco de dados
                    FabricanteDAO fabricanteDAO = new FabricanteDAO();
                    int rowCount = fabricanteDAO.delete(idFabricanteSelecionado);

                    // Atualiza a interface do usuário com base no resultado da exclusão do fabricante
                    SwingUtilities.invokeLater(() -> {
                        if (rowCount > 0) {
                            JOptionPane.showMessageDialog(this, "Fabricante apagado com sucesso.");
                            listarFabricantes();
                        } else {
                            JOptionPane.showMessageDialog(this, "Falha ao apagar fabricante.");
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

            FabricanteDAO fabricanteDAO = new FabricanteDAO();
            ArrayList<Fabricante> fabricantesList = fabricanteDAO.searchByTerm(termoPesquisa);

            DefaultTableModel model = (DefaultTableModel) fabricanteRead.getModel();
            model.setRowCount(0);

            for (Fabricante fabricante : fabricantesList) {
                Object[] rowData = { fabricante.getIdFabricante(), fabricante.getNome(), fabricante.getPaisOrigem() };
                model.addRow(rowData);
            }

            SwingUtilities.invokeLater(() -> {
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
        });
        thread.start();
    }//GEN-LAST:event_editarActionPerformed

    private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparActionPerformed
        pesquisaAtiva = false;
        timer.cancel(); 
        listarFabricantes();
        iniciarTimer();
        
        fabricanteRead.clearSelection();
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
    private javax.swing.JTable fabricanteRead;
    private javax.swing.JScrollPane tabela;
    private javax.swing.JLabel titulo;
    private javax.swing.JButton voltar;
    // End of variables declaration//GEN-END:variables
}
