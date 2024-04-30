/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jframe.fabricacao;

import fabricacao.Fabricacao;
import fabricacao.FabricacaoDAO;
import fabricante.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import jframe.fabricante.*;
import jframe.veiculo.*;
import jframe.telainicial.Home;
import veiculo.*;


/**
 *
 * @author Arthur
 */
public class FabricacaoCreate extends javax.swing.JFrame {

    /**
     * Creates new form veiculoRead
     */
    public FabricacaoCreate() {
        initComponents();
        listarVeiculos();
        listarFabricantes();
    }
    public void listarVeiculos() {
        Thread thread = new Thread(() -> {
            VeiculoDAO veiculoDAO = new VeiculoDAO();
            ArrayList<Veiculo> veiculosList = veiculoDAO.list();

            DefaultTableModel model = (DefaultTableModel) veiculoRead.getModel();
            model.setRowCount(0); 

            for (Veiculo veiculo : veiculosList) {
                Object[] rowData = { veiculo.getIdVeiculo(), veiculo.getNome(), veiculo.getCor(), veiculo.getModelo() };
                model.addRow(rowData);
            }
            });
        thread.start();
    }
    
    public void listarFabricantes() {
        Thread thread = new Thread(() -> {
            FabricanteDAO fabricanteDAO = new FabricanteDAO();
            ArrayList<Fabricante> fabricantesList = fabricanteDAO.list();

            DefaultTableModel model = (DefaultTableModel) fabricanteRead.getModel();
            model.setRowCount(0); 

            for (Fabricante fabricante : fabricantesList) {
                Object[] rowData = { fabricante.getIdFabricante(), fabricante.getNome(), fabricante.getPaisOrigem() };
                model.addRow(rowData);
            }
        });
        thread.start();
    }
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tebela = new javax.swing.JScrollPane();
        veiculoRead = new javax.swing.JTable();
        voltar = new javax.swing.JButton();
        Cadastrar = new javax.swing.JButton();
        botaoPesquisarFabricante = new javax.swing.JButton();
        campoDePesquisaFabricante = new javax.swing.JTextField();
        tebela1 = new javax.swing.JScrollPane();
        fabricanteRead = new javax.swing.JTable();
        campoDePesquisaVeiculo = new javax.swing.JTextField();
        botaoPesquisarVeiculo = new javax.swing.JButton();
        campoDataFabricacao = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        campoPaisFabricacao = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Fabricação - Cadastro");
        setBackground(new java.awt.Color(153, 255, 204));
        setMinimumSize(new java.awt.Dimension(800, 450));
        setResizable(false);

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
        tebela.setViewportView(veiculoRead);

        voltar.setBackground(new java.awt.Color(102, 204, 255));
        voltar.setText("Voltar");
        voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarActionPerformed(evt);
            }
        });

        Cadastrar.setBackground(new java.awt.Color(204, 255, 204));
        Cadastrar.setText("Cadastrar");
        Cadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CadastrarActionPerformed(evt);
            }
        });

        botaoPesquisarFabricante.setBackground(new java.awt.Color(204, 255, 204));
        botaoPesquisarFabricante.setText("Pesquisar");
        botaoPesquisarFabricante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoPesquisarFabricanteActionPerformed(evt);
            }
        });

        campoDePesquisaFabricante.setText("Pesquise pelo fabricante.");
        campoDePesquisaFabricante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoDePesquisaFabricanteActionPerformed(evt);
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
        tebela1.setViewportView(fabricanteRead);

        campoDePesquisaVeiculo.setText("Pesquise pelo veiculo.");
        campoDePesquisaVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoDePesquisaVeiculoActionPerformed(evt);
            }
        });

        botaoPesquisarVeiculo.setBackground(new java.awt.Color(204, 255, 204));
        botaoPesquisarVeiculo.setText("Pesquisar");
        botaoPesquisarVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoPesquisarVeiculoActionPerformed(evt);
            }
        });

        campoDataFabricacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoDataFabricacaoActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Data de Fabricação: ");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("País de Fabricação:");

        campoPaisFabricacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoPaisFabricacaoActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel3.setText("Defina uma fabricação");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Cadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(voltar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(campoDePesquisaVeiculo)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(botaoPesquisarVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(campoDePesquisaFabricante)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(botaoPesquisarFabricante, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(tebela1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 746, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tebela, javax.swing.GroupLayout.PREFERRED_SIZE, 746, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campoDataFabricacao, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoPaisFabricacao, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(campoDePesquisaFabricante, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botaoPesquisarFabricante, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tebela1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(campoDePesquisaVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botaoPesquisarVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tebela, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoDataFabricacao, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoPaisFabricacao, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(Cadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(voltar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void CadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CadastrarActionPerformed
        Thread thread = new Thread(() -> {
            if (veiculoRead.getSelectedRow() == -1 || fabricanteRead.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(this, "Selecione um veículo e um fabricante para cadastrar a fabricação.");
                return;
            }

            String dataFabricacao = campoDataFabricacao.getText().trim();
            if (!dataFabricacao.matches("\\d{2}/\\d{2}/\\d{4}")) {
                JOptionPane.showMessageDialog(this, "Formato de data inválido. Utilize o formato dd/mm/aaaa.");
                return;
            }

            String paisFabricacao = campoPaisFabricacao.getText().trim();
            if (paisFabricacao.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, preencha o campo de país de fabricação.");
                return;
            }

            int idVeiculoSelecionado = (int) veiculoRead.getValueAt(veiculoRead.getSelectedRow(), 0);
            int idFabricanteSelecionado = (int) fabricanteRead.getValueAt(fabricanteRead.getSelectedRow(), 0);

            Veiculo veiculoSelecionado = new Veiculo(
                idVeiculoSelecionado,
                (String) veiculoRead.getValueAt(veiculoRead.getSelectedRow(), 1), // Nome
                (String) veiculoRead.getValueAt(veiculoRead.getSelectedRow(), 2), // Cor
                (String) veiculoRead.getValueAt(veiculoRead.getSelectedRow(), 3)  // Modelo
            );

            Fabricante fabricanteSelecionado = new Fabricante(
                idFabricanteSelecionado,
                (String) fabricanteRead.getValueAt(fabricanteRead.getSelectedRow(), 1), // Nome
                (String) fabricanteRead.getValueAt(fabricanteRead.getSelectedRow(), 2)  // País de Origem
            );

            // Cria a data de fabricação
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dataFabricacaoObj = LocalDate.parse(dataFabricacao, formatter);

            Fabricacao fabricacao = new Fabricacao(veiculoSelecionado, fabricanteSelecionado, dataFabricacaoObj, paisFabricacao);

            FabricacaoDAO fabricacaoDAO = new FabricacaoDAO();
            int rowCount = fabricacaoDAO.insert(fabricacao);

            SwingUtilities.invokeLater(() -> {
                if (rowCount > 0) {
                    JOptionPane.showMessageDialog(this, "Fabricação cadastrada com sucesso!");
                    setVisible(false);
                    new FabricacaoRead().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao cadastrar fabricação.");
                }
            }); 
        });
        thread.start();
    }//GEN-LAST:event_CadastrarActionPerformed

    private void voltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarActionPerformed
        setVisible(false);
        new FabricacaoRead().setVisible(true);
    }//GEN-LAST:event_voltarActionPerformed

    private void botaoPesquisarFabricanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPesquisarFabricanteActionPerformed
        Thread thread = new Thread(() -> {
            String termoPesquisa = campoDePesquisaFabricante.getText().trim();

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
        });
        thread.start();
    }//GEN-LAST:event_botaoPesquisarFabricanteActionPerformed

    private void campoDePesquisaFabricanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoDePesquisaFabricanteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoDePesquisaFabricanteActionPerformed

    private void campoDePesquisaVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoDePesquisaVeiculoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoDePesquisaVeiculoActionPerformed

    private void botaoPesquisarVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPesquisarVeiculoActionPerformed
        Thread thread = new Thread(() -> {
            String termoPesquisa = campoDePesquisaVeiculo.getText().trim();

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
        });
        thread.start();
    }//GEN-LAST:event_botaoPesquisarVeiculoActionPerformed

    private void campoDataFabricacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoDataFabricacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoDataFabricacaoActionPerformed

    private void campoPaisFabricacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoPaisFabricacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoPaisFabricacaoActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cadastrar;
    private javax.swing.JButton botaoPesquisarFabricante;
    private javax.swing.JButton botaoPesquisarVeiculo;
    private javax.swing.JTextField campoDataFabricacao;
    private javax.swing.JTextField campoDePesquisaFabricante;
    private javax.swing.JTextField campoDePesquisaVeiculo;
    private javax.swing.JTextField campoPaisFabricacao;
    private javax.swing.JTable fabricanteRead;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane tebela;
    private javax.swing.JScrollPane tebela1;
    private javax.swing.JTable veiculoRead;
    private javax.swing.JButton voltar;
    // End of variables declaration//GEN-END:variables
}
