
package jframe.fabricacao;

import fabricacao.*;
import fabricante.Fabricante;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import jframe.telainicial.Home;
import veiculo.Veiculo;

/**
 *
 * @author Arthur
 */
public class FabricacaoRead extends javax.swing.JFrame {

    /**
     * Creates new form veiculoRead
     */
    public FabricacaoRead() {
        initComponents();
        listarFabricacao();
        desativarBotoes();
        
    }

    private void desativarBotoes(){
        fabricacaoRead.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    // Verifica se algum item da tabela está selecionado
                    if (fabricacaoRead.getSelectedRow() == -1) {
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
    
    private void listarFabricacao() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // Obtém a lista de fabricações do banco de dados
                FabricacaoDAO fabricacaoDAO = new FabricacaoDAO();
                ArrayList<Fabricacao> fabricacoesList = fabricacaoDAO.list();

                // Configura o modelo da tabela
                DefaultTableModel model = (DefaultTableModel) fabricacaoRead.getModel();
                model.setRowCount(0); // Limpa a tabela antes de adicionar novos dados

                // Cria um renderizador para centralizar os itens das células
                DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                centerRenderer.setHorizontalAlignment(JLabel.CENTER);

                // Aplica o renderizador a todas as colunas da tabela
                for (int i = 0; i < fabricacaoRead.getColumnCount(); i++) {
                    fabricacaoRead.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                }

                for (Fabricacao fabricacao : fabricacoesList) {
                    // Obtém os dados relacionados à fabricação
                    int idFabricacao = fabricacao.getIdFabricacao();
                    String dataFabricacao = fabricacao.getDataFabricacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")); // Formatação da data
                    String paisFabricacao = fabricacao.getPaisFabricacao();
                    int idVeiculo = fabricacao.getVeiculo().getIdVeiculo();
                    String nomeVeiculo = fabricacao.getVeiculo().getNome();
                    String corVeiculo = fabricacao.getVeiculo().getCor();
                    String modeloVeiculo = fabricacao.getVeiculo().getModelo();
                    int idFabricante = fabricacao.getFabricante().getIdFabricante();
                    String nomeFabricante = fabricacao.getFabricante().getNome();
                    String paisOrigemFabricante = fabricacao.getFabricante().getPaisOrigem(); // Alterado para obter o país de origem do veículo

                    // Adiciona os dados à tabela
                    Object[] rowData = {idFabricacao, dataFabricacao, paisFabricacao, idVeiculo, nomeVeiculo, corVeiculo, modeloVeiculo, idFabricante, nomeFabricante, paisOrigemFabricante};
                    model.addRow(rowData);
                }
            }
        }, 0, 10 * 1000); // 10 segundos em milissegundos
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        campoDePesquisa = new javax.swing.JTextField();
        voltar = new javax.swing.JButton();
        cadastrar = new javax.swing.JButton();
        apagar = new javax.swing.JButton();
        botaoPesquisar = new javax.swing.JButton();
        editar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        tebela = new javax.swing.JScrollPane();
        fabricacaoRead = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Arthur Cristovão - Fabricação");
        setBackground(new java.awt.Color(153, 255, 204));
        setFocusable(false);
        setFocusableWindowState(false);
        setMinimumSize(new java.awt.Dimension(800, 450));
        setResizable(false);

        campoDePesquisa.setText("teste");

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

        botaoPesquisar.setBackground(new java.awt.Color(204, 255, 204));
        botaoPesquisar.setText("Pesquisar");
        botaoPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoPesquisarActionPerformed(evt);
            }
        });

        editar.setBackground(new java.awt.Color(255, 255, 204));
        editar.setText("Editar");
        editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel5.setText("Fabricação");

        fabricacaoRead.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Data de fabricação", "País de fabricação", "ID Veiculo", "Nome do veiculo", "Cor", "Modelo", "ID Fabricante", "Nome do fabricante", "País de origem"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        fabricacaoRead.setToolTipText("");
        tebela.setViewportView(fabricacaoRead);
        if (fabricacaoRead.getColumnModel().getColumnCount() > 0) {
            fabricacaoRead.getColumnModel().getColumn(0).setPreferredWidth(20);
            fabricacaoRead.getColumnModel().getColumn(1).setPreferredWidth(55);
            fabricacaoRead.getColumnModel().getColumn(2).setPreferredWidth(50);
            fabricacaoRead.getColumnModel().getColumn(3).setPreferredWidth(20);
            fabricacaoRead.getColumnModel().getColumn(4).setPreferredWidth(55);
            fabricacaoRead.getColumnModel().getColumn(5).setPreferredWidth(40);
            fabricacaoRead.getColumnModel().getColumn(6).setPreferredWidth(40);
            fabricacaoRead.getColumnModel().getColumn(7).setPreferredWidth(20);
            fabricacaoRead.getColumnModel().getColumn(8).setPreferredWidth(55);
            fabricacaoRead.getColumnModel().getColumn(9).setPreferredWidth(40);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(apagar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(voltar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tebela, javax.swing.GroupLayout.PREFERRED_SIZE, 1104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(jLabel5))
                            .addComponent(campoDePesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 945, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addComponent(botaoPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(cadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(editar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(apagar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(168, 168, 168)
                        .addComponent(voltar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botaoPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoDePesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tebela, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        campoDePesquisa.getAccessibleContext().setAccessibleName("barra");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadastrarActionPerformed
        setVisible(false);
        new FabricacaoCreate().setVisible(true);
    }//GEN-LAST:event_cadastrarActionPerformed

    private void apagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apagarActionPerformed
         // Verifica se há uma linha selecionada na tabela de fabricações
        int selectedRow = fabricacaoRead.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione uma fabricação para apagar.");
            return;
        }

        // Obtém o ID da fabricação selecionada
        int idFabricacaoSelecionada = (int) fabricacaoRead.getValueAt(selectedRow, 0);

        // Exibe um JOptionPane de confirmação
        int confirmacao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja apagar esta fabricação?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);

        if (confirmacao == JOptionPane.YES_OPTION) {
            // Cria uma thread para realizar a exclusão da fabricação
            Thread thread = new Thread(() -> {
                // Apaga a fabricação do banco de dados
                FabricacaoDAO fabricacaoDAO = new FabricacaoDAO();
                int rowCount = fabricacaoDAO.delete(idFabricacaoSelecionada);

                // Exibe mensagem de sucesso ou erro
                SwingUtilities.invokeLater(() -> {
                    if (rowCount > 0) {
                        JOptionPane.showMessageDialog(this, "Fabricação apagada com sucesso.");
                        // Atualiza a tabela de fabricações
                        listarFabricacao();
                    } else {
                        JOptionPane.showMessageDialog(this, "Falha ao apagar fabricação.");
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

        // Cria um novo DAO e busca as fabricações
        FabricacaoDAO fabricacaoDAO = new FabricacaoDAO();
        ArrayList<Fabricacao> fabricacoesList = fabricacaoDAO.searchByTerm(termoPesquisa);

        // Atualiza a tabela com os resultados da pesquisa
        DefaultTableModel model = (DefaultTableModel) fabricacaoRead.getModel();
        model.setRowCount(0);

        for (Fabricacao fabricacao : fabricacoesList) {
            // Obtém os dados da fabricação, do veículo e do fabricante
            int idFabricacao = fabricacao.getIdFabricacao();
            String dataFabricacao = fabricacao.getDataFabricacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String paisFabricacao = fabricacao.getPaisFabricacao();
            int idVeiculo = fabricacao.getVeiculo().getIdVeiculo();
            String nomeVeiculo = fabricacao.getVeiculo().getNome();
            String corVeiculo = fabricacao.getVeiculo().getCor();
            String modeloVeiculo = fabricacao.getVeiculo().getModelo();
            int idFabricante = fabricacao.getFabricante().getIdFabricante();
            String nomeFabricante = fabricacao.getFabricante().getNome();
            String paisOrigemFabricante = fabricacao.getFabricante().getPaisOrigem();

            // Adiciona os dados à tabela
            Object[] rowData = { idFabricacao, dataFabricacao, paisFabricacao, idVeiculo, nomeVeiculo, corVeiculo, modeloVeiculo, idFabricante, nomeFabricante, paisOrigemFabricante };
            model.addRow(rowData);
        }
    }//GEN-LAST:event_botaoPesquisarActionPerformed

    private void editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarActionPerformed
        int selectedRow = fabricacaoRead.getSelectedRow();
    
        int idFabricacao = (int) fabricacaoRead.getValueAt(selectedRow, 0); 
        String dataFabricacao = (String) fabricacaoRead.getValueAt(selectedRow, 1); 
        String paisFabricacao = (String) fabricacaoRead.getValueAt(selectedRow, 2); 
        int idVeiculo = (int) fabricacaoRead.getValueAt(selectedRow, 3); 
        String nomeVeiculo = (String) fabricacaoRead.getValueAt(selectedRow, 4); 
        String corVeiculo = (String) fabricacaoRead.getValueAt(selectedRow, 5); 
        String modeloVeiculo = (String) fabricacaoRead.getValueAt(selectedRow, 6); 
        int idFabricante = (int) fabricacaoRead.getValueAt(selectedRow, 7); 
        String nomeFabricante = (String) fabricacaoRead.getValueAt(selectedRow, 8); 
        String paisOrigemFabricante = (String) fabricacaoRead.getValueAt(selectedRow, 9); 

        Fabricacao fabricacaoSelecionada = new Fabricacao(
            idFabricacao,
            new Veiculo(idVeiculo, nomeVeiculo, corVeiculo, modeloVeiculo),
            new Fabricante(idFabricante, nomeFabricante, paisOrigemFabricante),
            LocalDate.parse(dataFabricacao, DateTimeFormatter.ofPattern("dd/MM/yyyy")),
            paisFabricacao
        );

        setVisible(false);
        new FabricacaoUpdate(fabricacaoSelecionada).setVisible(true);
    }//GEN-LAST:event_editarActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton apagar;
    private javax.swing.JButton botaoPesquisar;
    private javax.swing.JButton cadastrar;
    private javax.swing.JTextField campoDePesquisa;
    private javax.swing.JButton editar;
    private javax.swing.JTable fabricacaoRead;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane tebela;
    private javax.swing.JButton voltar;
    // End of variables declaration//GEN-END:variables
}
