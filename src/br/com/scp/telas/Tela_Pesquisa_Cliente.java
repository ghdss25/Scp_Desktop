
package br.com.scp.telas;
import br.com.scp.dao.Conexao_SCP; 
import java.sql.*;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public class Tela_Pesquisa_Cliente extends javax.swing.JInternalFrame {
    
    // Usando a variavel conexão do DAL 
    Connection conexao = null; 

    // são frameworks do pacote java.sql e executar as instruções SQL
    PreparedStatement pst = null;

    // são frameworks do pacote java.sql e executar as instruções SQL
    ResultSet rs = null; 
    
    public Tela_Pesquisa_Cliente() {
        
        initComponents();
        
        conexao = Conexao_SCP.conector(); 
    }
    
    public void listar_clientes() {

            try {

                String sql = "SELECT * FROM clientes";
                PreparedStatement pst = conexao.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();

                tbl_clientes.setModel(DbUtils.resultSetToTableModel(rs));

            } catch (Exception e) {

                e.printStackTrace();
            }
	}
    
    // Método para pesquisar clientes pelo nome com filtro 
    private void pesquisar_cliente() {
        
       // método para pesquisar telefones nome com filtro 	
        String sql = "SELECT id, nome, cpf, perfil FROM clientes WHERE nome LIKE ?";
        
        try { 
            
            pst = conexao.prepareStatement(sql); 
            
            // passando o contéudo da caixa de pesquisa para o ? 
            // atenção ao "%" - continuação da String sql 

            pst.setString(1, txt_cli_pesquisar.getText() + "%"); 
            rs = pst.executeQuery(); 

            // A linha abaixo usa a biblioteca rs2xml.jar para preencher a tabela 
            tbl_clientes.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (Exception e) {
            
            JOptionPane.showConfirmDialog(null, e); 
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_cli_pesquisar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btn_Consultar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_clientes = new javax.swing.JTable();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        lbl_Cli_Id = new javax.swing.JLabel();
        lbl_Cli_Nome = new javax.swing.JLabel();
        lbl_Cli_Cpf = new javax.swing.JLabel();
        lbl_Cli_Senha = new javax.swing.JLabel();
        lbl_Cli_Perfil = new javax.swing.JLabel();
        txt_Cli_Id = new javax.swing.JTextField();
        txt_Cli_Nome = new javax.swing.JTextField();
        txt_Cli_Cpf = new javax.swing.JTextField();
        txt_Cli_Senha = new javax.swing.JTextField();
        btn_Adicionar = new javax.swing.JButton();
        Combo_Perfil = new javax.swing.JComboBox<>();
        btn_Atualizar = new javax.swing.JButton();
        btn_Consultar1 = new javax.swing.JButton();
        btn_Deletar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnlistar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Pesquisa de Clientes");
        setToolTipText("");

        txt_cli_pesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_cli_pesquisarKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Abyssinica SIL", 1, 18)); // NOI18N
        jLabel1.setText("Informe o nome para a sua Pesquisa");

        btn_Consultar.setBackground(new java.awt.Color(255, 255, 255));
        btn_Consultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/scp/icones/pesquisar.png"))); // NOI18N
        btn_Consultar.setToolTipText("Consultar");
        btn_Consultar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Consultar.setPreferredSize(new java.awt.Dimension(64, 54));
        btn_Consultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ConsultarActionPerformed(evt);
            }
        });

        tbl_clientes.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbl_clientes);

        jInternalFrame1.setClosable(true);
        jInternalFrame1.setIconifiable(true);
        jInternalFrame1.setMaximizable(true);
        jInternalFrame1.setTitle("Clientes");
        jInternalFrame1.setPreferredSize(new java.awt.Dimension(590, 0));

        lbl_Cli_Id.setFont(new java.awt.Font("Abyssinica SIL", 1, 18)); // NOI18N
        lbl_Cli_Id.setText("id");

        lbl_Cli_Nome.setFont(new java.awt.Font("Abyssinica SIL", 1, 18)); // NOI18N
        lbl_Cli_Nome.setText("nome");

        lbl_Cli_Cpf.setFont(new java.awt.Font("Abyssinica SIL", 1, 18)); // NOI18N
        lbl_Cli_Cpf.setText("cpf");

        lbl_Cli_Senha.setFont(new java.awt.Font("Abyssinica SIL", 1, 18)); // NOI18N
        lbl_Cli_Senha.setText("senha");

        lbl_Cli_Perfil.setFont(new java.awt.Font("Abyssinica SIL", 1, 18)); // NOI18N
        lbl_Cli_Perfil.setText("perfil");

        btn_Adicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/scp/icones/add.png"))); // NOI18N
        btn_Adicionar.setToolTipText("Adicionar");
        btn_Adicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Adicionar.setPreferredSize(new java.awt.Dimension(64, 54));
        btn_Adicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AdicionarActionPerformed(evt);
            }
        });

        Combo_Perfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "admin", "user" }));

        btn_Atualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/scp/icones/atualizar.png"))); // NOI18N
        btn_Atualizar.setToolTipText("Atualizar");
        btn_Atualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Atualizar.setPreferredSize(new java.awt.Dimension(64, 54));
        btn_Atualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AtualizarActionPerformed(evt);
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

        btn_Deletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/scp/icones/delete.png"))); // NOI18N
        btn_Deletar.setToolTipText("Deletar");
        btn_Deletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Deletar.setPreferredSize(new java.awt.Dimension(64, 54));
        btn_Deletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DeletarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Abyssinica SIL", 1, 18)); // NOI18N
        jLabel2.setText("* Campos Obrigátorios");

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_Cli_Nome)
                    .addComponent(lbl_Cli_Cpf)
                    .addComponent(lbl_Cli_Id))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                .addComponent(txt_Cli_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(71, 71, 71)
                                .addComponent(jLabel2))
                            .addComponent(txt_Cli_Nome, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addComponent(btn_Adicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(btn_Consultar1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(btn_Atualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(btn_Deletar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrame1Layout.createSequentialGroup()
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(lbl_Cli_Perfil)
                                .addGap(40, 40, 40)
                                .addComponent(Combo_Perfil, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                .addComponent(txt_Cli_Cpf, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbl_Cli_Senha)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_Cli_Senha, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57))))
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_Cli_Id)
                    .addComponent(txt_Cli_Id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(40, 40, 40)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_Cli_Nome)
                    .addComponent(txt_Cli_Nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_Cli_Cpf)
                    .addComponent(txt_Cli_Cpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_Cli_Senha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_Cli_Senha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Combo_Perfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_Cli_Perfil))
                .addGap(49, 49, 49)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_Adicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Atualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Consultar1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Deletar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        btnlistar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/scp/icones/lista.png"))); // NOI18N
        btnlistar.setToolTipText("Listar Clientes");
        btnlistar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlistarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addGap(252, 252, 252)
                .addComponent(btnlistar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(txt_cli_pesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(btn_Consultar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 269, Short.MAX_VALUE)
                    .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 269, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_cli_pesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Consultar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(btnlistar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 170, Short.MAX_VALUE)
                    .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 169, Short.MAX_VALUE)))
        );

        setBounds(0, 0, 548, 371);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ConsultarActionPerformed
        // Chamando o método consultar:
       
    }//GEN-LAST:event_btn_ConsultarActionPerformed

    private void btn_AdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AdicionarActionPerformed
        // chamando o método adicionar:
        
    }//GEN-LAST:event_btn_AdicionarActionPerformed

    private void btn_AtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AtualizarActionPerformed
        // chamando o método atualizar:
        
    }//GEN-LAST:event_btn_AtualizarActionPerformed

    private void btn_Consultar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Consultar1ActionPerformed
        // Chamando o método consultar:
        
    }//GEN-LAST:event_btn_Consultar1ActionPerformed

    private void btn_DeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DeletarActionPerformed
        // Chamando o método consultar:
      
    }//GEN-LAST:event_btn_DeletarActionPerformed

    private void txt_cli_pesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cli_pesquisarKeyReleased
        // O método chama a pesquisa de clientes no banco de dados
        pesquisar_cliente();
    }//GEN-LAST:event_txt_cli_pesquisarKeyReleased

    private void btnlistarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlistarActionPerformed
        // TODO add your handling code here:
        
        listar_clientes();
    }//GEN-LAST:event_btnlistarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Combo_Perfil;
    private javax.swing.JButton btn_Adicionar;
    private javax.swing.JButton btn_Atualizar;
    private javax.swing.JButton btn_Consultar;
    private javax.swing.JButton btn_Consultar1;
    private javax.swing.JButton btn_Deletar;
    private javax.swing.JButton btnlistar;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_Cli_Cpf;
    private javax.swing.JLabel lbl_Cli_Id;
    private javax.swing.JLabel lbl_Cli_Nome;
    private javax.swing.JLabel lbl_Cli_Perfil;
    private javax.swing.JLabel lbl_Cli_Senha;
    private javax.swing.JTable tbl_clientes;
    private javax.swing.JTextField txt_Cli_Cpf;
    private javax.swing.JTextField txt_Cli_Id;
    private javax.swing.JTextField txt_Cli_Nome;
    private javax.swing.JTextField txt_Cli_Senha;
    private javax.swing.JTextField txt_cli_pesquisar;
    // End of variables declaration//GEN-END:variables
}
