
package br.com.scp.telas;

import java.sql.*;
import br.com.scp.dao.Conexao_SCP;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils; 
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class Tela_Notas_Fiscais extends javax.swing.JInternalFrame {
    
    // Usando a variavel conexão do DAL 
    Connection conexao = null; 

    // são frameworks do pacote java.sql e executar as instruções SQL
    PreparedStatement pst = null;

    // são frameworks do pacote java.sql e executar as instruções SQL
    ResultSet rs = null; 
    
    // A linha abaixo cria uma variável para armazenar um texto de acordo com o 
    // radion button selecionado 
    
    private String tipo; 
    
    public Tela_Notas_Fiscais() {
        
        initComponents();
        
        conexao = Conexao_SCP.conector(); 
    }
    
    private void pesquisar_cliente() {
        
        String sql = "SELECT id AS id, nome AS Nome, cpf AS Cpf FROM clientes WHERE nome LIKE ?"; 
        
        try {
            
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txt_pes_cli.getText() + "%");
            rs = pst.executeQuery();
            
            tbl_cli.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void setar_campos() {
        
        int setar = tbl_cli.getSelectedRow();
        
        txt_cli_id.setText(tbl_cli.getModel().getValueAt(setar, 0).toString());
    }
    
    // método para cadastrar uma nota fiscal 
    private void emitir_nota_fiscal() {
        
        String sql = "INSERT INTO nota_fiscal(id, data_emissao,Situacao, tipo, valor, id_cliente, id_produto) VALUES(?, ?, ?, ?, ?, ?, ?)";
        
        try {
            
            pst = conexao.prepareStatement(sql);
            
            pst.setString(1, txt_id_nota_fiscal.getText());
            pst.setString(2, txt_data_emissao.getText());
            pst.setString(3, Combo_Situacao.getSelectedItem().toString());
            pst.setString(4, tipo);
            pst.setString(5, txt_valor.getText().replace(",", "."));
            pst.setString(6, txt_cli_id.getText());
            pst.setString(7, txt_id_produto.getText()); 
            
            // Validação dos campos obrigatórios
            if(txt_id_nota_fiscal.getText().isEmpty() || txt_data_emissao.getText().isEmpty() || (txt_valor.getText().isEmpty()) || (txt_cli_id.getText().isEmpty()) || (txt_id_produto.getText().isEmpty())) {
                
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatorios");
                
            } else {
                
                int adicionado = pst.executeUpdate();
                
                if (adicionado > 0) {
                    
                    JOptionPane.showMessageDialog(null, "Nota Fiscal Emitida com sucesso"); 
                    
                    txt_id_nota_fiscal.setText(null);
                    txt_data_emissao.setText(null);    
                    txt_valor.setText(null);
                    txt_cli_id.setText(null);
                    txt_id_produto.setText(null);
                }
            }
       
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void consultar_nota_fiscal() {
        
        // A linha abaixo cria uma caixa de entrada do tipo JOptionpane
        
        String num_nota_fiscal = JOptionPane.showInputDialog("ID de Nota Fiscal: "); 
        String sql = "SELECT * FROM nota_fiscal WHERE id = " + num_nota_fiscal;
        
        try {
            
             pst = conexao.prepareStatement(sql); 
             
             rs = pst.executeQuery();
             
             if(rs.next()) {
                 
                 txt_id_nota_fiscal.setText(rs.getString(1));
                 txt_data_emissao.setText(rs.getString(2));
                 Combo_Situacao.setSelectedItem(rs.getString(3));
               
                 // setando os radio button 
                 String rbtTipo = rs.getString(4);
                 
                 if(rbtTipo.equals("Nota Fiscal")) {
                     
                     rbtnf.setSelected(true);
                     tipo = "Nota Fiscal";
                     
                 } else {
                     
                     rbtorc.setSelected(true);
                     tipo = "Orçamento";
                 }
                 
                  txt_valor.setText(rs.getString(5));
                  txt_cli_id.setText(rs.getString(6));
                  txt_id_produto.setText(rs.getString(7));
                  
                  // Evitando Problemas 
                  btn_Adicionar.setEnabled(false);
                  txt_pes_cli.setEnabled(false);
                  tbl_cli.setVisible(false);
                  txt_id_nota_fiscal.setEnabled(false);
                  txt_data_emissao.setEnabled(false);
                 
             } else {
                 
                 JOptionPane.showMessageDialog(null, "Nota Fiscal não cadastrada");
                 
             }
            
        } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException e) {
            
            JOptionPane.showMessageDialog(null, "Nota Fiscal Inválida");
            // System.out.println(e);
            
        } catch (Exception e2) {
            
            JOptionPane.showMessageDialog(null, e2);
        }
        
    }
    
    private void alterar_Nota_fiscal() {
        
        String sql = "UPDATE nota_fiscal set data_emissao = ?, tipo = ?, Situacao = ?, valor = ?, id_cliente = ?, id_produto = ? WHERE id = ?";
        
       try {
           
            pst = conexao.prepareStatement(sql); 
            pst.setString(1, txt_data_emissao.getText());
            pst.setString(2, tipo);
            pst.setString(3, Combo_Situacao.getSelectedItem().toString());
            pst.setString(4, txt_valor.getText());
            pst.setString(5, txt_cli_id.getText());
            pst.setString(6, txt_id_produto.getText());
            pst.setString(7, txt_id_nota_fiscal.getText());
            
            if(txt_id_nota_fiscal.getText().isEmpty() || txt_data_emissao.getText().isEmpty() || txt_valor.getText().isEmpty() 
              || txt_cli_id.getText().isEmpty() || txt_id_produto.getText().isEmpty()) {
                
                 JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
                 
            } else {
                
                 // a estrutura abaixo é usada para confirmar a alteração dos dados na tabela 
                int adicionado = pst.executeUpdate();
                
                 if(adicionado > 0) {
                     
                      JOptionPane.showMessageDialog(null, "Nota Fiscal alterada com sucesso");
                      
                      // As linhas abaixo "limpam" os campos
                      txt_id_nota_fiscal.setText(null);
                      txt_data_emissao.setText(null);
                      txt_valor.setText(null);
                      txt_cli_id.setText(null);
                      txt_id_produto.setText(null); 
                      
                      // Habilitar a Nota fiscal para os seguintes campos
                      btn_Adicionar.setEnabled(true);
                      txt_pes_cli.setEnabled(true);
                      tbl_cli.setVisible(true);
                      txt_id_nota_fiscal.setEnabled(true);
                      txt_data_emissao.setEnabled(true);
                 }
            }
           
       } catch (Exception e) {
           
           JOptionPane.showMessageDialog(null, e);
       } 
    }
    
    // Método para excluir Nota Fiscal 
    private void excluir_Nota_Fiscal() {
        
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este endereço", "Atenção", JOptionPane.YES_NO_OPTION); 
        
        if (confirma == JOptionPane.YES_OPTION) {
            
            String sql = "DELETE FROM nota_fiscal WHERE id = ?";  
            
            try {
                
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txt_id_nota_fiscal.getText());
                int apagado = pst.executeUpdate();
                
                if (apagado > 0) {
                    
                    JOptionPane.showMessageDialog(null, "Nota Fiscal removida com sucesso");
                    
                    txt_id_nota_fiscal.setText(null);
                    txt_data_emissao.setText(null);
                    txt_valor.setText(null);
                    txt_cli_id.setText(null);
                    txt_id_produto.setText(null); 
                    
                    // Habilitar a Nota fiscal para os seguintes campos
                    btn_Adicionar.setEnabled(true);
                    txt_pes_cli.setEnabled(true);
                    tbl_cli.setVisible(true);
                    txt_id_nota_fiscal.setEnabled(true);
                    txt_data_emissao.setEnabled(true);
                }
                
            } catch (Exception e) {
                
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    
    // Método para imprimir uma nota fiscal 
    private void imprimir_nota_fiscal() {
        
        // imprimindo uma nota fiscal 
        int confirma = JOptionPane.showConfirmDialog(null, "Confirma a impressão desta Nota Fiscal ?", "Atenção", JOptionPane.YES_NO_OPTION);
        
        if (confirma == JOptionPane.YES_OPTION) {
            
            // Imprimindo relatório com o framework JasperReports 
            try {
                
                // Usando a classe HashMap para criar um filtro 
                HashMap filtro = new HashMap(); 
                filtro.put("id", Integer.parseInt(txt_id_nota_fiscal.getText()));
                
                // Usando a classe JasperPrint para preparar a emissão de relatórios 
                JasperPrint print = JasperFillManager.fillReport("/home/gustavo/Documentos/Reports/nota_fiscal.jasper",filtro, conexao);
                
                // A linha abaixo exibe o relatório através da classe JasperViewer 
                JasperViewer.viewReport(print, false);
                
            } catch (Exception e) {
                
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_data_emissao = new javax.swing.JTextField();
        rbtnf = new javax.swing.JRadioButton();
        rbtorc = new javax.swing.JRadioButton();
        txt_id_nota_fiscal = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        txt_pes_cli = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_cli_id = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_cli = new javax.swing.JTable();
        txt_valor = new javax.swing.JTextField();
        lbl_valor = new javax.swing.JLabel();
        btn_Adicionar = new javax.swing.JButton();
        btn_Consultar1 = new javax.swing.JButton();
        btn_Atualizar = new javax.swing.JButton();
        btn_Deletar = new javax.swing.JButton();
        btn_Imprimir = new javax.swing.JButton();
        Combo_Situacao = new javax.swing.JComboBox<>();
        txt_id_produto = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Notas Fiscais");
        setPreferredSize(new java.awt.Dimension(640, 480));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("DejaVu Serif", 1, 14)); // NOI18N
        jLabel1.setText("Data da Emissão");

        txt_data_emissao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_data_emissaoActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbtnf);
        rbtnf.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        rbtnf.setText("Nota Fiscal");
        rbtnf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnfActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbtorc);
        rbtorc.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        rbtorc.setText("Orçamento");
        rbtorc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtorcActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setText("id Nota fiscal");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbtnf)
                .addGap(18, 18, 18)
                .addComponent(rbtorc)
                .addContainerGap(23, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(txt_id_nota_fiscal, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txt_data_emissao, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_data_emissao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_id_nota_fiscal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtnf)
                    .addComponent(rbtorc))
                .addContainerGap())
        );

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setText("Situação");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Realizado ", "Não Realizado", "Orçamento Reprovado", "Desfeito pelo cliente " }));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Clientes"));

        txt_pes_cli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_pes_cliKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("* id");

        txt_cli_id.setEditable(false);
        txt_cli_id.setEnabled(false);

        tbl_cli.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "nome", "cpf"
            }
        ));
        tbl_cli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_cliMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_cli);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txt_pes_cli, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_cli_id, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(197, 197, 197))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_pes_cli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txt_cli_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txt_valor.setText("0");

        lbl_valor.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lbl_valor.setText("Valor");

        btn_Adicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/scp/icones/add.png"))); // NOI18N
        btn_Adicionar.setToolTipText("Emtir Nota Fiscal");
        btn_Adicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Adicionar.setPreferredSize(new java.awt.Dimension(64, 54));
        btn_Adicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AdicionarActionPerformed(evt);
            }
        });

        btn_Consultar1.setBackground(new java.awt.Color(255, 255, 255));
        btn_Consultar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/scp/icones/pesquisar.png"))); // NOI18N
        btn_Consultar1.setToolTipText("Consultar");
        btn_Consultar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Consultar1.setPreferredSize(new java.awt.Dimension(64, 54));
        btn_Consultar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Consultar1ActionPerformed(evt);
            }
        });

        btn_Atualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/scp/icones/atualizar.png"))); // NOI18N
        btn_Atualizar.setToolTipText("Atualizar");
        btn_Atualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Atualizar.setPreferredSize(new java.awt.Dimension(64, 54));
        btn_Atualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AtualizarActionPerformed(evt);
            }
        });

        btn_Deletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/scp/icones/delete.png"))); // NOI18N
        btn_Deletar.setToolTipText("Deletar");
        btn_Deletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Deletar.setPreferredSize(new java.awt.Dimension(64, 54));
        btn_Deletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DeletarActionPerformed(evt);
            }
        });

        btn_Imprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/scp/icones/imprimir.png"))); // NOI18N
        btn_Imprimir.setToolTipText("Imprimir Nota Fiscal");
        btn_Imprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Imprimir.setPreferredSize(new java.awt.Dimension(64, 54));
        btn_Imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ImprimirActionPerformed(evt);
            }
        });

        Combo_Situacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Na bancada", "Realizada ", "Não Realizada ", "Orçamento Realizado ", "Orçamento Não Realizado", " " }));
        Combo_Situacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Combo_SituacaoActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("id Produto");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox1, 0, 1, Short.MAX_VALUE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Combo_Situacao, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_valor)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_valor, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_id_produto, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(btn_Adicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(btn_Consultar1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(btn_Atualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(btn_Deletar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(btn_Imprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(99, 99, 99))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(Combo_Situacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_valor)
                            .addComponent(txt_valor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_id_produto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(26, 26, 26)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btn_Adicionar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_Consultar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_Atualizar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_Deletar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Imprimir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(258, 258, 258)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setBounds(0, 0, 550, 406);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_data_emissaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_data_emissaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_data_emissaoActionPerformed

    private void btn_AdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AdicionarActionPerformed
        // chamando o método Emitir Nota fiscal: 
        
        emitir_nota_fiscal(); 
    }//GEN-LAST:event_btn_AdicionarActionPerformed

    private void btn_Consultar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Consultar1ActionPerformed
        // Chamando o método consultar:
       consultar_nota_fiscal();
    }//GEN-LAST:event_btn_Consultar1ActionPerformed

    private void btn_AtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AtualizarActionPerformed
        // chamando o método atualizar:
       alterar_Nota_fiscal();
    }//GEN-LAST:event_btn_AtualizarActionPerformed

    private void btn_DeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DeletarActionPerformed
        // Chamando o método apagar:
       excluir_Nota_Fiscal();
    }//GEN-LAST:event_btn_DeletarActionPerformed

    private void btn_ImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ImprimirActionPerformed
        // Imprimindo o relatório de nota fiscal
        
        imprimir_nota_fiscal();
    }//GEN-LAST:event_btn_ImprimirActionPerformed

    private void txt_pes_cliKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_pes_cliKeyReleased
        // Chamando o método pesquisar cliente: 
        
        pesquisar_cliente();
    }//GEN-LAST:event_txt_pes_cliKeyReleased

    private void tbl_cliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_cliMouseClicked
        // chamando o método setar campos
        setar_campos();
    }//GEN-LAST:event_tbl_cliMouseClicked

    private void rbtnfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnfActionPerformed
        // Atribuindo um texto a variável tipo se estiver selecionado 
        tipo = "Nota Fiscal";
    }//GEN-LAST:event_rbtnfActionPerformed

    private void rbtorcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtorcActionPerformed
         // Atribuindo um texto a variável tipo se estiver selecionado 
         
         tipo = "Orçamento";
    }//GEN-LAST:event_rbtorcActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // Ao abrir o form marcar o radio button Nota Fiscal 
        rbtnf.setSelected(true);
        tipo = "Nota Fiscal";
    }//GEN-LAST:event_formInternalFrameOpened

    private void Combo_SituacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Combo_SituacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Combo_SituacaoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Combo_Situacao;
    private javax.swing.JButton btn_Adicionar;
    private javax.swing.JButton btn_Atualizar;
    private javax.swing.JButton btn_Consultar1;
    private javax.swing.JButton btn_Deletar;
    private javax.swing.JButton btn_Imprimir;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_valor;
    private javax.swing.JRadioButton rbtnf;
    private javax.swing.JRadioButton rbtorc;
    private javax.swing.JTable tbl_cli;
    private javax.swing.JTextField txt_cli_id;
    private javax.swing.JTextField txt_data_emissao;
    private javax.swing.JTextField txt_id_nota_fiscal;
    private javax.swing.JTextField txt_id_produto;
    private javax.swing.JTextField txt_pes_cli;
    private javax.swing.JTextField txt_valor;
    // End of variables declaration//GEN-END:variables
}
