package br.com.scp.telas;

import java.sql.*;
import br.com.scp.dao.Conexao_SCP; 
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Tela_Cliente extends javax.swing.JInternalFrame {
    
    // Usando a variavel conexão do DAL 
    Connection conexao = null; 

    // são frameworks do pacote java.sql e executar as instruções SQL
    PreparedStatement pst = null;

    // são frameworks do pacote java.sql e executar as instruções SQL
    ResultSet rs = null; 

    public Tela_Cliente() {
        
        initComponents();
        
        conexao = Conexao_SCP.conector(); 
    }
    
    private void adicionar_cliente() {
        
        // chamando o método adicionar
        String sql = "INSERT INTO clientes (id, nome, cpf, senha, perfil) VALUES(?, ?, ?, MD5(?), ?)";
        
        try {
            
            pst = conexao.prepareStatement(sql);  
            
            pst.setString(1, txt_Cli_Id.getText()); 
            pst.setString(2, txt_Cli_Nome.getText()); 
            pst.setString(3, txt_Cli_Cpf.getText()); 
            pst.setString(4, txt_Cli_Senha.getText());
            pst.setString(5, Combo_Perfil.getSelectedItem().toString());
            
            // Atualiza a tabela cliente com os dados do formulário 
            //pst.executeUpdate();
           
            // Validação dos campos obrigatórios 
            if ((txt_Cli_Id.getText().isEmpty()) || (txt_Cli_Nome.getText().isEmpty()) || (txt_Cli_Cpf.getText().isEmpty()) || (txt_Cli_Senha.getText().isEmpty())) {
                
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");

            } else {
                
                // a estrutura abaixo é usada para confirmar a inserção dos dados na tabela 
		int adicionado = pst.executeUpdate();
                
                if(adicionado > 0){
                    
                    JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso");
                    
                    // As linhas abaixo "limpam" os campos
                    txt_Cli_Id.setText(null);
                    txt_Cli_Nome.setText(null);
                    txt_Cli_Cpf.setText(null);
                    txt_Cli_Senha.setText(null);
                    
                }
            }
            
        } catch (Exception e1) {
            
            JOptionPane.showMessageDialog(null, e1);
        }
    }
    
    private void atualizar_cliente() {
        
        String sql = "UPDATE clientes SET nome = ?, cpf = ?, senha = MD5(?), perfil = ?  WHERE id = ?"; 
        
        try { 
            
            pst = conexao.prepareStatement(sql); 
            pst.setString(1, txt_Cli_Nome.getText());
            pst.setString(2, txt_Cli_Cpf.getText());
            pst.setString(3, txt_Cli_Senha.getText());
            pst.setString(4, Combo_Perfil.getSelectedItem().toString());
            pst.setString(5, txt_Cli_Id.getText());
            
            if ((txt_Cli_Id.getText().isEmpty()) || (txt_Cli_Nome.getText().isEmpty()) || (txt_Cli_Cpf.getText().isEmpty()) || (txt_Cli_Senha.getText().isEmpty())) {
                
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");

            } else {
                
                // a linha abaixo atualiza a tabela usuario com os dados do formulário 
                //pst.executeUpdate();
				
                // a estrutura abaixo é usada para confirmar a alteração dos dados na tabela 
                int adicionado = pst.executeUpdate();

                if(adicionado > 0) {
                    
                    
                    JOptionPane.showMessageDialog(null, "Dados do Cliente alterado com sucesso");

                    // As linhas abaixo "limpam" os campos
                    txt_Cli_Id.setText(null);
                    txt_Cli_Nome.setText(null);
                    txt_Cli_Cpf.setText(null);
                    txt_Cli_Senha.setText(null);
                        
                }
            }

        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void consultar_cliente() throws SQLException {
        
        String cliente = JOptionPane.showInputDialog("ID do Cliente : "); 
        String sql = "SELECT * FROM clientes WHERE id = " + cliente;
        
        try {
            
             pst = conexao.prepareStatement(sql); 
             
             rs = pst.executeQuery();
            
            if (rs.next()) {
                
                txt_Cli_Id.setText(rs.getString(1));
                txt_Cli_Nome.setText(rs.getString(2));
                txt_Cli_Cpf.setText(rs.getString(3));
                txt_Cli_Senha.setText(rs.getString(4));
                
                // A linha Abaixo se refere ao combo box
                Combo_Perfil.setSelectedItem(rs.getString(5));
                
            } else {
                
                JOptionPane.showMessageDialog(null, "Cliente não cadastrado");
				
                // As linhas abaixo "limpam" os campos
                txt_Cli_Nome.setText(null);
                txt_Cli_Cpf.setText(null);
                txt_Cli_Senha.setText(null);
                
            }
            
        } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException e) {
            
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void deletar_cliente() {
        
        // A estrutura abaixo confirma a remoção do usuário 
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este usuário", "Atenção", JOptionPane.YES_NO_OPTION);

        if (confirma == JOptionPane.YES_OPTION) {
            
            String sql = "DELETE FROM clientes WHERE id = ?"; 

            try {

                pst = conexao.prepareStatement(sql);
                pst.setString(1, txt_Cli_Id.getText());
                int apagado = pst.executeUpdate();

                if (apagado > 0) {
                    
                    JOptionPane.showMessageDialog(null, "Cliente removido com sucesso");
                    txt_Cli_Id.setText(null);
                    txt_Cli_Nome.setText(null);
                    txt_Cli_Cpf.setText(null);
                    txt_Cli_Senha.setText(null);
                    Combo_Perfil.setSelectedItem(null);
                    
                }

            } catch (Exception e) {

                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        btn_Consultar = new javax.swing.JButton();
        btn_Deletar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Clientes");
        setPreferredSize(new java.awt.Dimension(590, 0));

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

        btn_Deletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/scp/icones/delete.png"))); // NOI18N
        btn_Deletar.setToolTipText("Deletar");
        btn_Deletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Deletar.setPreferredSize(new java.awt.Dimension(64, 54));
        btn_Deletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DeletarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Abyssinica SIL", 1, 18)); // NOI18N
        jLabel1.setText("* Campos Obrigátorios");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(56, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_Cli_Nome)
                    .addComponent(lbl_Cli_Cpf)
                    .addComponent(lbl_Cli_Id))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txt_Cli_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(71, 71, 71)
                                .addComponent(jLabel1))
                            .addComponent(txt_Cli_Nome, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_Adicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(btn_Consultar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(btn_Atualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(btn_Deletar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 29, Short.MAX_VALUE)
                                .addComponent(lbl_Cli_Perfil)
                                .addGap(40, 40, 40)
                                .addComponent(Combo_Perfil, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txt_Cli_Cpf, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbl_Cli_Senha)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_Cli_Senha, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_Cli_Id)
                    .addComponent(txt_Cli_Id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_Cli_Nome)
                    .addComponent(txt_Cli_Nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_Cli_Cpf)
                    .addComponent(txt_Cli_Cpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_Cli_Senha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_Cli_Senha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Combo_Perfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_Cli_Perfil))
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_Adicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Atualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Consultar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Deletar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        btn_Adicionar.getAccessibleContext().setAccessibleName("");
        btn_Adicionar.getAccessibleContext().setAccessibleDescription("");

        setBounds(0, 0, 554, 396);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_DeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DeletarActionPerformed
        // Chamando o método consultar: 
        deletar_cliente(); 
    }//GEN-LAST:event_btn_DeletarActionPerformed

    private void btn_ConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ConsultarActionPerformed
        try {
            // Chamando o método consultar:
            
            consultar_cliente();
        } catch (SQLException ex) {
            
            Logger.getLogger(Tela_Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_ConsultarActionPerformed

    private void btn_AdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AdicionarActionPerformed
       // chamando o método adicionar: 
       adicionar_cliente();
    }//GEN-LAST:event_btn_AdicionarActionPerformed

    private void btn_AtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AtualizarActionPerformed
       // chamando o método atualizar:
       atualizar_cliente();
    }//GEN-LAST:event_btn_AtualizarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Combo_Perfil;
    private javax.swing.JButton btn_Adicionar;
    private javax.swing.JButton btn_Atualizar;
    private javax.swing.JButton btn_Consultar;
    private javax.swing.JButton btn_Deletar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbl_Cli_Cpf;
    private javax.swing.JLabel lbl_Cli_Id;
    private javax.swing.JLabel lbl_Cli_Nome;
    private javax.swing.JLabel lbl_Cli_Perfil;
    private javax.swing.JLabel lbl_Cli_Senha;
    private javax.swing.JTextField txt_Cli_Cpf;
    private javax.swing.JTextField txt_Cli_Id;
    private javax.swing.JTextField txt_Cli_Nome;
    private javax.swing.JTextField txt_Cli_Senha;
    // End of variables declaration//GEN-END:variables
}
