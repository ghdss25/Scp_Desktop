
package br.com.scp.telas;

import br.com.scp.dao.Conexao_SCP;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class Tela_Endereco extends javax.swing.JInternalFrame {
    
     // Usando a variavel conexão do DAL 
    Connection conexao = null; 

    // são frameworks do pacote java.sql e executar as instruções SQL
    PreparedStatement pst = null;

    // são frameworks do pacote java.sql e executar as instruções SQL
    ResultSet rs = null; 

    public Tela_Endereco() {
        
        initComponents();
        
        conexao = Conexao_SCP.conector(); 
    }
    
    private void adicionar_endereco() {
        
        String sql = "INSERT INTO enderecos (id, cidade, estado, logradouro, bairro, numero, cep, id_cliente) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
            
            pst = conexao.prepareStatement(sql); 
			
            pst.setString(1, txt_End_Id.getText());
            pst.setString(2, txt_End_Cidade.getText());
            pst.setString(3, txt_End_Estado.getText());
            pst.setString(4, txt_End_Logradouro.getText());
            pst.setString(5, txt_End_Bairro.getText());
            pst.setString(6, txt_End_Numero.getText());
            pst.setString(7, txt_End_Cep.getText());
            pst.setString(8, txt_End_Id_Cliente.getText());
            
            // Validação dos campos obrigatórios 
            
            if ((txt_End_Id.getText().isEmpty()) || (txt_End_Cidade.getText().isEmpty()) || (txt_End_Estado.getText().isEmpty()) 
                    
                    || (txt_End_Logradouro.getText().isEmpty()) || (txt_End_Bairro.getText().isEmpty()) 
                    || (txt_End_Numero.getText().isEmpty()) || (txt_End_Cep.getText().isEmpty()) 
                    || (txt_End_Id_Cliente.getText().isEmpty())) {
                
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
                
            } else {
                
                // a linha abaixo atualiza a tabela usuario com os dados do formulário 
			
                // a estrutura abaixo é usada para confirmar a inserção dos dados na tabela 
                int adicionado = pst.executeUpdate();
                
                if(adicionado > 0) {
				
                    JOptionPane.showMessageDialog(null, "Endereço adicionado com sucesso");
                    // As linhas abaixo "limpam" os campos
                    txt_End_Id.setText(null);
                    txt_End_Cidade.setText(null);
                    txt_End_Estado.setText(null);
                    txt_End_Logradouro.setText(null);
                    txt_End_Bairro.setText(null);
                    txt_End_Numero.setText(null);
                    txt_End_Cep.setText(null);
                    txt_End_Id_Cliente.setText(null);
                }
            }
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void consultar_endereco() {
        
        String endereco = JOptionPane.showInputDialog("ID do Endereço : "); 
        String sql = "SELECT * FROM enderecos WHERE id = " + endereco;
        
        try {
            
             pst = conexao.prepareStatement(sql); 
             
             rs = pst.executeQuery();
            
            if (rs.next()) {
                
                txt_End_Id.setText(rs.getString(1));
                txt_End_Cidade.setText(rs.getString(2));
                txt_End_Estado.setText(rs.getNString(3));
                txt_End_Logradouro.setText(rs.getString(4));
                txt_End_Bairro.setText(rs.getString(5));
                txt_End_Numero.setText(rs.getString(6));
                txt_End_Cep.setText(rs.getString(7));
                txt_End_Id_Cliente.setText(rs.getString(8));
                
            } else {
                
                JOptionPane.showMessageDialog(null, "Endereços não cadastrados");
				
                // As linhas abaixo "limpam" os campos
                txt_End_Cidade.setText(null);
                txt_End_Estado.setText(null);
                txt_End_Logradouro.setText(null);
                txt_End_Bairro.setText(null); 
                txt_End_Numero.setText(null); 
                txt_End_Cep.setText(null);
                txt_End_Id_Cliente.setText(null);
            }
             
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void alterar_endereco() {
        
        String sql = "UPDATE enderecos SET cidade = ?, estado = ?, logradouro = ?, bairro = ?, numero = ?, cep = ?, id_cliente = ?  WHERE id = ?"; 
        
        try {
            
            pst = conexao.prepareStatement(sql); 
            pst.setString(1, txt_End_Cidade.getText());
            pst.setString(2, txt_End_Estado.getText());
            pst.setString(3, txt_End_Logradouro.getText());
            pst.setString(4, txt_End_Bairro.getText());
            pst.setString(5, txt_End_Numero.getText());
            pst.setString(6, txt_End_Cep.getText());
            pst.setString(7, txt_End_Id_Cliente.getText());
            pst.setString(8, txt_End_Id.getText());
            
             if ((txt_End_Id.getText().isEmpty()) || (txt_End_Cidade.getText().isEmpty()) || (txt_End_Estado.getText().isEmpty()) 
                    
                    || (txt_End_Logradouro.getText().isEmpty()) || (txt_End_Bairro.getText().isEmpty()) 
                    || (txt_End_Numero.getText().isEmpty()) || (txt_End_Cep.getText().isEmpty()) 
                    || (txt_End_Id_Cliente.getText().isEmpty())) {
                
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
                
            } else {
                
                // a linha abaixo atualiza a tabela usuario com os dados do formulário 
				
                // a estrutura abaixo é usada para confirmar a alteração dos dados na tabela 
                int adicionado = pst.executeUpdate();
                
                if(adicionado > 0) {
                    
                    JOptionPane.showMessageDialog(null, "Endereço alterado com sucesso");
                    
                    // As linhas abaixo "limpam" os campos
                    txt_End_Cidade.setText(null);
                    txt_End_Estado.setText(null);
                    txt_End_Logradouro.setText(null);
                    txt_End_Bairro.setText(null); 
                    txt_End_Numero.setText(null); 
                    txt_End_Cep.setText(null);
                    txt_End_Id_Cliente.setText(null);
                    
                }
            
             }
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void deletar_endereco() {
        
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este endereço", "Atenção", JOptionPane.YES_NO_OPTION);
        
        if (confirma == JOptionPane.YES_OPTION) {
            
            String sql = "DELETE FROM enderecos WHERE id = ?"; 
            
            try {
                
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txt_End_Id.getText());
                int apagado = pst.executeUpdate();
                
                if (apagado > 0) {
                    
                    JOptionPane.showMessageDialog(null, "Endereço removido com sucesso");
                    txt_End_Id.setText(null);
                    txt_End_Cidade.setText(null);
                    txt_End_Estado.setText(null);
                    txt_End_Logradouro.setText(null);
                    txt_End_Bairro.setText(null);
                    txt_End_Numero.setText(null);
                    txt_End_Cep.setText(null);
                    txt_End_Id_Cliente.setText(null);
                }
                
            } catch (Exception e) {
                
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_End_Id = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txt_End_Id = new javax.swing.JTextField();
        lbl_End_Cidade = new javax.swing.JLabel();
        txt_End_Cidade = new javax.swing.JTextField();
        lbl_End_Estado = new javax.swing.JLabel();
        txt_End_Estado = new javax.swing.JTextField();
        lbl_End_Logradouro = new javax.swing.JLabel();
        txt_End_Logradouro = new javax.swing.JTextField();
        lbl_End_Bairro = new javax.swing.JLabel();
        txt_End_Bairro = new javax.swing.JTextField();
        lbl_End_Numero = new javax.swing.JLabel();
        txt_End_Numero = new javax.swing.JTextField();
        lbl_End_Cep = new javax.swing.JLabel();
        txt_End_Cep = new javax.swing.JTextField();
        btn_Adicionar = new javax.swing.JButton();
        btn_Consultar = new javax.swing.JButton();
        btn_Atualizar = new javax.swing.JButton();
        btn_Deletar = new javax.swing.JButton();
        lbl_End_Id_Cliente = new javax.swing.JLabel();
        txt_End_Id_Cliente = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Endereços");
        setPreferredSize(new java.awt.Dimension(590, 0));

        lbl_End_Id.setFont(new java.awt.Font("Abyssinica SIL", 1, 18)); // NOI18N
        lbl_End_Id.setText("id");

        jLabel1.setFont(new java.awt.Font("Abyssinica SIL", 1, 18)); // NOI18N
        jLabel1.setText("* Campos Obrigátorios");

        lbl_End_Cidade.setFont(new java.awt.Font("Abyssinica SIL", 1, 18)); // NOI18N
        lbl_End_Cidade.setText("cidade");

        lbl_End_Estado.setFont(new java.awt.Font("Abyssinica SIL", 1, 18)); // NOI18N
        lbl_End_Estado.setText("estado");

        txt_End_Estado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_End_EstadoActionPerformed(evt);
            }
        });

        lbl_End_Logradouro.setFont(new java.awt.Font("Abyssinica SIL", 1, 18)); // NOI18N
        lbl_End_Logradouro.setText("logradouro");

        lbl_End_Bairro.setFont(new java.awt.Font("Abyssinica SIL", 1, 18)); // NOI18N
        lbl_End_Bairro.setText("bairro");

        lbl_End_Numero.setFont(new java.awt.Font("Abyssinica SIL", 1, 18)); // NOI18N
        lbl_End_Numero.setText("numero");

        lbl_End_Cep.setFont(new java.awt.Font("Abyssinica SIL", 1, 18)); // NOI18N
        lbl_End_Cep.setText("cep");

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

        lbl_End_Id_Cliente.setFont(new java.awt.Font("Abyssinica SIL", 1, 18)); // NOI18N
        lbl_End_Id_Cliente.setText("Id Cliente");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(283, 283, 283)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbl_End_Logradouro)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_End_Logradouro, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(25, 25, 25))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lbl_End_Estado)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txt_End_Estado))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lbl_End_Id)
                                        .addGap(27, 27, 27)
                                        .addComponent(txt_End_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(55, 55, 55)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(lbl_End_Bairro)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_End_Bairro))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(lbl_End_Cidade)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_End_Cidade, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbl_End_Numero)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_End_Numero, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(lbl_End_Cep)
                                .addGap(27, 27, 27)
                                .addComponent(txt_End_Cep, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(btn_Adicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(btn_Consultar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(btn_Atualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(btn_Deletar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(166, 166, 166)
                        .addComponent(lbl_End_Id_Cliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_End_Id_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_End_Cidade)
                    .addComponent(txt_End_Cidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_End_Id)
                    .addComponent(txt_End_Id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_End_Estado)
                    .addComponent(txt_End_Estado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_End_Bairro)
                    .addComponent(txt_End_Bairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_End_Logradouro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_End_Logradouro))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_End_Numero)
                    .addComponent(txt_End_Numero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_End_Cep)
                    .addComponent(txt_End_Cep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_End_Id_Cliente)
                    .addComponent(txt_End_Id_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_Adicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Atualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Consultar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Deletar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        setBounds(0, 0, 553, 394);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_End_EstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_End_EstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_End_EstadoActionPerformed

    private void btn_AdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AdicionarActionPerformed
        // chamando o método adicionar:
       
        adicionar_endereco();
    }//GEN-LAST:event_btn_AdicionarActionPerformed

    private void btn_ConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ConsultarActionPerformed
        // Chamando o método consultar:
       
       consultar_endereco();
    }//GEN-LAST:event_btn_ConsultarActionPerformed

    private void btn_AtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AtualizarActionPerformed
       
        // chamando o método atualizar:
        alterar_endereco();
    }//GEN-LAST:event_btn_AtualizarActionPerformed

    private void btn_DeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DeletarActionPerformed
        // Chamando o método consultar:
        deletar_endereco();
    }//GEN-LAST:event_btn_DeletarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Adicionar;
    private javax.swing.JButton btn_Atualizar;
    private javax.swing.JButton btn_Consultar;
    private javax.swing.JButton btn_Deletar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbl_End_Bairro;
    private javax.swing.JLabel lbl_End_Cep;
    private javax.swing.JLabel lbl_End_Cidade;
    private javax.swing.JLabel lbl_End_Estado;
    private javax.swing.JLabel lbl_End_Id;
    private javax.swing.JLabel lbl_End_Id_Cliente;
    private javax.swing.JLabel lbl_End_Logradouro;
    private javax.swing.JLabel lbl_End_Numero;
    private javax.swing.JTextField txt_End_Bairro;
    private javax.swing.JTextField txt_End_Cep;
    private javax.swing.JTextField txt_End_Cidade;
    private javax.swing.JTextField txt_End_Estado;
    private javax.swing.JTextField txt_End_Id;
    private javax.swing.JTextField txt_End_Id_Cliente;
    private javax.swing.JTextField txt_End_Logradouro;
    private javax.swing.JTextField txt_End_Numero;
    // End of variables declaration//GEN-END:variables
}
