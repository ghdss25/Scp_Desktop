
package br.com.scp.telas;

import br.com.scp.dao.Conexao_SCP;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class Tela_Telefone extends javax.swing.JInternalFrame {
    
     // Usando a variavel conexão do DAL 
    Connection conexao = null; 

    // são frameworks do pacote java.sql e executar as instruções SQL
    PreparedStatement pst = null;

    // são frameworks do pacote java.sql e executar as instruções SQL
    ResultSet rs = null; 
    
    public Tela_Telefone() {
        
        initComponents();
        
        conexao = Conexao_SCP.conector(); 
    }
    
    private void adicionar_telefone() {
        
        // chamando o método adicionar
        String sql = "INSERT INTO telefones (id, numero, id_cliente) VALUES(?, ?, ?)";
        
        try { 
            
            pst = conexao.prepareStatement(sql);  
					
            pst.setString(1, txt_tel_Id.getText()); 
            pst.setString(2, txt_tel_numero.getText()); 
            pst.setString(3, txt_tel_id_cliente.getText());
            
            // Validação dos campos obrigatórios 
            if ((txt_tel_Id.getText().isEmpty()) || (txt_tel_numero.getText().isEmpty()) || (txt_tel_id_cliente.getText().isEmpty())) {
						
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
						 
            } else {
                
                // a linha abaixo atualiza a tabela usuario com os dados do formulário 
						
                // a estrutura abaixo é usada para confirmar a inserção dos dados na tabela 
                int adicionado = pst.executeUpdate();
                
                if(adicionado > 0) {

                    JOptionPane.showMessageDialog(null, "Telefone adicionado com sucesso");
                    // As linhas abaixo "limpam" os campos
                    txt_tel_Id.setText(null);
                    txt_tel_numero.setText(null);
                    txt_tel_id_cliente.setText(null);
                }
            }
            
        }  catch (Exception e1) {
            
            JOptionPane.showMessageDialog(null, e1);
        }
    }
    
      private void alterar_telefone() {
        
        // chamando o método alterar 
        String sql = "UPDATE telefones SET numero = ?, id_cliente = ?  WHERE id = ?"; 
        
        try {
            
            pst = conexao.prepareStatement(sql); 
					
            pst.setString(1, txt_tel_numero.getText()); 
            pst.setString(2, txt_tel_id_cliente.getText());
            pst.setString(3, txt_tel_Id.getText()); 
            
            // Validação dos campos obrigatórios 
            if ((txt_tel_Id.getText().isEmpty()) || (txt_tel_numero.getText().isEmpty()) || (txt_tel_id_cliente.getText().isEmpty())) {
						
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
						 
            } else {
                
                 // a linha abaixo atualiza a tabela usuario com os dados do formulário 
						
                // a estrutura abaixo é usada para confirmar a inserção dos dados na tabela 
                int adicionado = pst.executeUpdate();
                
                if(adicionado > 0) {

                    JOptionPane.showMessageDialog(null, "Telefone alterado com sucesso");
                    // As linhas abaixo "limpam" os campos
                    txt_tel_Id.setText(null);
                    txt_tel_numero.setText(null);
                    txt_tel_id_cliente.setText(null);
                }
            }
            
        } catch (Exception e1) {
            
            JOptionPane.showMessageDialog(null, e1);
        }
    }
    
    private void consultar_telefone() {
        
        String telefone = JOptionPane.showInputDialog("ID do Telefone : "); 
        String sql = "SELECT * FROM telefones WHERE id = " + telefone;
        
        try {
            
            pst = conexao.prepareStatement(sql); 
             
            rs = pst.executeQuery();
            
            if (rs.next()) {
		
                txt_tel_Id.setText(rs.getString(1));
                txt_tel_numero.setText(rs.getString(2));
                txt_tel_id_cliente.setText(rs.getString(3));
                
            } else {
                
                JOptionPane.showMessageDialog(null, "Telefone não cadastrado");
                txt_tel_numero.setText(null);
                txt_tel_id_cliente.setText(null);
            }
            
        } catch (Exception e1) {
            
            JOptionPane.showMessageDialog(null, e1);
        }
    }
    
    private void deletar_telefone() {
        
        // chamando o método remover

        // A estrutura abaixo confirma a remoção do usuário 
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este telefones", "Atenção", JOptionPane.YES_NO_OPTION);
        
        if (confirma == JOptionPane.YES_OPTION) {
            
            String sql = "DELETE FROM telefones WHERE id = ?"; 
            
            try { 
                
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txt_tel_Id.getText());
                int apagado = pst.executeUpdate();
                
                if (apagado > 0) {
                    
                    JOptionPane.showMessageDialog(null, "Telefone removido com sucesso");
                    txt_tel_Id.setText(null);
                    txt_tel_numero.setText(null);
                    txt_tel_id_cliente.setText(null);
                }
                
            } catch (Exception e1) {
                
                JOptionPane.showMessageDialog(null, e1);
            }
        }
				
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_tel_Id = new javax.swing.JTextField();
        lbl_numero = new javax.swing.JLabel();
        txt_tel_numero = new javax.swing.JTextField();
        lbl_id_cliente = new javax.swing.JLabel();
        txt_tel_id_cliente = new javax.swing.JTextField();
        btn_Adicionar = new javax.swing.JButton();
        btn_Consultar = new javax.swing.JButton();
        btn_Atualizar = new javax.swing.JButton();
        btn_Deletar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Telefones");

        jLabel1.setFont(new java.awt.Font("Abyssinica SIL", 1, 18)); // NOI18N
        jLabel1.setText("* Campos Obrigátorios");

        jLabel2.setFont(new java.awt.Font("Abyssinica SIL", 1, 18)); // NOI18N
        jLabel2.setText("id");

        lbl_numero.setFont(new java.awt.Font("Abyssinica SIL", 1, 18)); // NOI18N
        lbl_numero.setText("numero");

        txt_tel_numero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tel_numeroActionPerformed(evt);
            }
        });

        lbl_id_cliente.setFont(new java.awt.Font("Abyssinica SIL", 1, 18)); // NOI18N
        lbl_id_cliente.setText("id cliente");

        txt_tel_id_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tel_id_clienteActionPerformed(evt);
            }
        });

        btn_Adicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/scp/icones/add.png"))); // NOI18N
        btn_Adicionar.setToolTipText("Adicionar");
        btn_Adicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Adicionar.setPreferredSize(new java.awt.Dimension(64, 54));
        btn_Adicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AdicionarActionPerformed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(btn_Adicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(btn_Consultar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(btn_Atualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(btn_Deletar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(165, 165, 165)
                        .addComponent(lbl_id_cliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_tel_id_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_tel_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbl_numero)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_tel_numero, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_tel_Id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_numero)
                    .addComponent(txt_tel_numero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_id_cliente)
                    .addComponent(txt_tel_id_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_Adicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Atualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Consultar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Deletar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        setBounds(0, 0, 526, 364);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_AdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AdicionarActionPerformed
        // chamando o método adicionar:

       adicionar_telefone();
    }//GEN-LAST:event_btn_AdicionarActionPerformed

    private void btn_ConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ConsultarActionPerformed
        // Chamando o método consultar:

       consultar_telefone();
    }//GEN-LAST:event_btn_ConsultarActionPerformed

    private void btn_AtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AtualizarActionPerformed

        // chamando o método atualizar:
        alterar_telefone();
    }//GEN-LAST:event_btn_AtualizarActionPerformed

    private void btn_DeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DeletarActionPerformed
        // Chamando o método deletar:
        deletar_telefone();
    }//GEN-LAST:event_btn_DeletarActionPerformed

    private void txt_tel_id_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tel_id_clienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tel_id_clienteActionPerformed

    private void txt_tel_numeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tel_numeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tel_numeroActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Adicionar;
    private javax.swing.JButton btn_Atualizar;
    private javax.swing.JButton btn_Consultar;
    private javax.swing.JButton btn_Deletar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lbl_id_cliente;
    private javax.swing.JLabel lbl_numero;
    private javax.swing.JTextField txt_tel_Id;
    private javax.swing.JTextField txt_tel_id_cliente;
    private javax.swing.JTextField txt_tel_numero;
    // End of variables declaration//GEN-END:variables
}
