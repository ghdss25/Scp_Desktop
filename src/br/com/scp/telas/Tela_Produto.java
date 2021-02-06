
package br.com.scp.telas;

import br.com.scp.dao.Conexao_SCP;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class Tela_Produto extends javax.swing.JInternalFrame {
    
     // Usando a variavel conexão do DAL 
    Connection conexao = null; 

    // são frameworks do pacote java.sql e executar as instruções SQL
    PreparedStatement pst = null;

    // são frameworks do pacote java.sql e executar as instruções SQL
    ResultSet rs = null; 
    
    public Tela_Produto() {
        
        initComponents();
        
        conexao = Conexao_SCP.conector(); 
    }
    
    private void adicionar_produtos() {
        
        // chamando o método adicionar
	String sql = "INSERT INTO produtos (id, discriminacao, valor_unitario, id_cliente) VALUES(?, ?, ?, ?)";
        
        try {
            
            pst = conexao.prepareStatement(sql);  
					
            pst.setString(1, txt_Pro_Id.getText()); 
            pst.setString(2, txt_Pro_Disc.getText()); 
            pst.setString(3, txt_Pro_Vu.getText()); 
            pst.setString(4, txt_Pro_Id_Cliente.getText());
            
            // Validação dos campos obrigatórios 
					
            if ((txt_Pro_Id.getText().isEmpty()) || (txt_Pro_Disc.getText().isEmpty()) || (txt_Pro_Vu.getText().isEmpty()) || (txt_Pro_Id_Cliente.getText().isEmpty())) {
                
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
                
            } else { 
                
                // a linha abaixo atualiza a tabela usuario com os dados do formulário 
                
                // a estrutura abaixo é usada para confirmar a inserção dos dados na tabela 
                int adicionado = pst.executeUpdate();
                
                if(adicionado > 0) {
							
                    JOptionPane.showMessageDialog(null, "Produto adicionado com sucesso");
                    
                    // As linhas abaixo "limpam" os campos
                    txt_Pro_Id.setText(null);
                    txt_Pro_Disc.setText(null);
                    txt_Pro_Vu.setText(null);
                    txt_Pro_Id_Cliente.setText(null);

                }
            }
            
        } catch (Exception e1) {
            
            JOptionPane.showMessageDialog(null, e1);
        }
    }
    
    private void atualizar_produtos() {
        
        // chamando o método alterar 
        String sql = "UPDATE produtos SET discriminacao = ?, valor_unitario = ?, id_cliente = ?  WHERE id = ?";
        
        try {
            
            pst = conexao.prepareStatement(sql); 
					
            pst.setString(1, txt_Pro_Disc.getText()); 
            pst.setString(2, txt_Pro_Vu.getText()); 
            pst.setString(3, txt_Pro_Id_Cliente.getText());
            pst.setString(4, txt_Pro_Id.getText()); 
            
            if ((txt_Pro_Id.getText().isEmpty()) || (txt_Pro_Disc.getText().isEmpty()) || (txt_Pro_Vu.getText().isEmpty()) || (txt_Pro_Id_Cliente.getText().isEmpty())) {
                
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
                
            } else {
                
                // a linha abaixo atualiza a tabela usuario com os dados do formulário 
						
                // a estrutura abaixo é usada para confirmar a inserção dos dados na tabela 
                int adicionado = pst.executeUpdate();
						
                if(adicionado > 0) {
							
                    JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso");
                    
                    // As linhas abaixo "limpam" os campos
                    txt_Pro_Id.setText(null);
                    txt_Pro_Disc.setText(null);
                    txt_Pro_Vu.setText(null);
                    txt_Pro_Id_Cliente.setText(null);
                }
            }
            
        } catch (Exception e1) {
            
            JOptionPane.showMessageDialog(null, e1);
        }
    }
    
    private void consultar_produtos() {
        
        String produto = JOptionPane.showInputDialog("ID do Produto : "); 
        String sql = "SELECT * FROM produtos WHERE id = " + produto;
        
        try {
            
             pst = conexao.prepareStatement(sql); 
             
             rs = pst.executeQuery();
            
            if (rs.next()) {
                
                txt_Pro_Id.setText(rs.getString(1));
                txt_Pro_Disc.setText(rs.getString(2));
                txt_Pro_Vu.setText(rs.getString(3)); 
                txt_Pro_Id_Cliente.setText(rs.getString(4));
                
            } else {
                
                JOptionPane.showMessageDialog(null, "Produto não cadastrado");
                txt_Pro_Disc.setText(null);
                txt_Pro_Vu.setText(null);
                txt_Pro_Id_Cliente.setText(null);
                
            }
            
        } catch (Exception e1) {
            
            JOptionPane.showMessageDialog(null, e1);
        }
    }
    
    private void deletar_produtos() {
        
        // chamando o método remover
        
        // A estrutura abaixo confirma a remoção do usuário 
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este produtos", "Atenção", JOptionPane.YES_NO_OPTION);
        
        if (confirma == JOptionPane.YES_OPTION) {
            
            String sql = "DELETE FROM produtos WHERE id = ?";
            
            try {
                
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txt_Pro_Id.getText());
                int apagado = pst.executeUpdate();
                
                if (apagado > 0) {
                    
                    JOptionPane.showMessageDialog(null, "Produto removido com sucesso");
                    txt_Pro_Id.setText(null);
                    txt_Pro_Disc.setText(null);
                    txt_Pro_Vu.setText(null);
                    txt_Pro_Id_Cliente.setText(null);
                }
                
            } catch (Exception e1) {
                
                JOptionPane.showMessageDialog(null, e1);
            }
        }
				
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_Atualizar = new javax.swing.JButton();
        lbl_Pro_Produto = new javax.swing.JLabel();
        txt_Pro_Id = new javax.swing.JTextField();
        lbl_Pro_Disc = new javax.swing.JLabel();
        txt_Pro_Disc = new javax.swing.JTextField();
        lbl_Pro_VU = new javax.swing.JLabel();
        txt_Pro_Vu = new javax.swing.JTextField();
        lbl_Pro_Id_Cliente = new javax.swing.JLabel();
        txt_Pro_Id_Cliente = new javax.swing.JTextField();
        btn_Adicionar = new javax.swing.JButton();
        btn_Consultar = new javax.swing.JButton();
        btn_Atualizar1 = new javax.swing.JButton();
        btn_Deletar = new javax.swing.JButton();

        btn_Atualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/scp/icones/atualizar.png"))); // NOI18N
        btn_Atualizar.setToolTipText("Atualizar");
        btn_Atualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Atualizar.setPreferredSize(new java.awt.Dimension(64, 54));
        btn_Atualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AtualizarActionPerformed(evt);
            }
        });

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Produtos");

        lbl_Pro_Produto.setFont(new java.awt.Font("Abyssinica SIL", 1, 18)); // NOI18N
        lbl_Pro_Produto.setText("id");

        lbl_Pro_Disc.setFont(new java.awt.Font("Abyssinica SIL", 1, 18)); // NOI18N
        lbl_Pro_Disc.setText("discriminação");

        lbl_Pro_VU.setFont(new java.awt.Font("Abyssinica SIL", 1, 18)); // NOI18N
        lbl_Pro_VU.setText("valor unitario");

        lbl_Pro_Id_Cliente.setFont(new java.awt.Font("Abyssinica SIL", 1, 18)); // NOI18N
        lbl_Pro_Id_Cliente.setText("id cliente");

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

        btn_Atualizar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/scp/icones/atualizar.png"))); // NOI18N
        btn_Atualizar1.setToolTipText("Atualizar");
        btn_Atualizar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Atualizar1.setPreferredSize(new java.awt.Dimension(64, 54));
        btn_Atualizar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Atualizar1ActionPerformed(evt);
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
                        .addGap(96, 96, 96)
                        .addComponent(btn_Adicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(btn_Consultar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(btn_Atualizar1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(btn_Deletar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbl_Pro_Disc)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_Pro_Disc, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbl_Pro_Produto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_Pro_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_Pro_VU)
                                    .addComponent(lbl_Pro_Id_Cliente))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_Pro_Vu)
                                    .addComponent(txt_Pro_Id_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(124, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_Pro_Produto)
                    .addComponent(txt_Pro_Id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_Pro_Disc)
                    .addComponent(txt_Pro_Disc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_Pro_VU)
                    .addComponent(txt_Pro_Vu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_Pro_Id_Cliente)
                    .addComponent(txt_Pro_Id_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_Consultar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Adicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Atualizar1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Deletar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55))
        );

        setBounds(0, 0, 554, 396);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_AdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AdicionarActionPerformed
        // chamando o método adicionar:
        adicionar_produtos();
    }//GEN-LAST:event_btn_AdicionarActionPerformed

    private void btn_ConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ConsultarActionPerformed
        // Chamando o método consultar:
        consultar_produtos();
    }//GEN-LAST:event_btn_ConsultarActionPerformed

    private void btn_AtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AtualizarActionPerformed
        // chamando o método atualizar:
       atualizar_produtos();
    }//GEN-LAST:event_btn_AtualizarActionPerformed

    private void btn_Atualizar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Atualizar1ActionPerformed
        // chamando o método atualizar:
         atualizar_produtos();
    }//GEN-LAST:event_btn_Atualizar1ActionPerformed

    private void btn_DeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DeletarActionPerformed
        // Chamando o método deletar:
        deletar_produtos();
    }//GEN-LAST:event_btn_DeletarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Adicionar;
    private javax.swing.JButton btn_Atualizar;
    private javax.swing.JButton btn_Atualizar1;
    private javax.swing.JButton btn_Consultar;
    private javax.swing.JButton btn_Deletar;
    private javax.swing.JLabel lbl_Pro_Disc;
    private javax.swing.JLabel lbl_Pro_Id_Cliente;
    private javax.swing.JLabel lbl_Pro_Produto;
    private javax.swing.JLabel lbl_Pro_VU;
    private javax.swing.JTextField txt_Pro_Disc;
    private javax.swing.JTextField txt_Pro_Id;
    private javax.swing.JTextField txt_Pro_Id_Cliente;
    private javax.swing.JTextField txt_Pro_Vu;
    // End of variables declaration//GEN-END:variables
}
