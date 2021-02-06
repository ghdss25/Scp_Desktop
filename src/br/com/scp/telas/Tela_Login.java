package br.com.scp.telas;

import java.sql.*;
import br.com.scp.dao.Conexao_SCP;
import java.awt.Color;
import javax.swing.JOptionPane;

public class Tela_Login extends javax.swing.JFrame {
    
    // Usando a variavel conexão do DAL 
    Connection conexao = null; 

    // são frameworks do pacote java.sql e executar as instruções SQL
    PreparedStatement pst = null;

    // são frameworks do pacote java.sql e executar as instruções SQL
    ResultSet rs = null; 
    
    public void logar() {
        
        String sql = "SELECT * FROM clientes WHERE nome=? AND senha = ?";
        
        try {
            
            // As linhas Abaixo preparam a consulta ao banco em função  
            // do que foi digitado nas caixas de texto. 
            // O ? é substituído pelo contéudo das variáveis 
			
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtusuario.getText());
            pst.setString(2, txtsenha.getText());

            // A linha abaixo executa a query 
            rs = pst.executeQuery();
            
            // Se existir usuário e senha correspondente 
            
            if (rs.next()) {
                
                // a linha abaixo obtém o conteúdo do campo perfil da tabela 
                // cliente
                
                String perfil = rs.getString(5); 
                //System.out.println(perfil); 
                
                // a estrutura abaixo faz o tratamento do perfil dos clientes
                if(perfil.equals("admin")) {
                    
                // a linha abaixo exibe o conteúdo do campo da tabela
                Tela_Menu_SCP scp = new Tela_Menu_SCP(); 
                scp.setVisible(true); 
                
                Tela_Menu_SCP.menCad.setEnabled(true);
                Tela_Menu_SCP.MenCadCli.setEnabled(true);
                Tela_Menu_SCP.MenCadEnd.setEnabled(true);
                Tela_Menu_SCP.MenCadPro.setEnabled(true);
                Tela_Menu_SCP.MenCadTel.setEnabled(true);
                Tela_Menu_SCP.MenRel.setEnabled(true);
                Tela_Menu_SCP.MenRelNf.setEnabled(true);
                Tela_Menu_SCP.lblcliente.setText(rs.getString(2));
                Tela_Menu_SCP.lblcliente.setForeground(Color.red);
                
                this.dispose();
                
                 conexao.close();
                
                } else {
                    
                    // a linha abaixo exibe o conteúdo do campo da tabela
                    Tela_Menu_SCP scp = new Tela_Menu_SCP(); 
                    scp.setVisible(true); 
                    Tela_Menu_SCP.lblcliente.setText(rs.getString(2));
                    this.dispose();
                }
                        
            } else {
                
                JOptionPane.showMessageDialog(null, "Usuario e / ou senha inválida (s)"); 
                
            }
            
        }  catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public Tela_Login() {
        
        initComponents();
        
        conexao = Conexao_SCP.conector(); 
        
        System.out.println(conexao);
        
        if(conexao != null) {
            
            lblstatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/scp/icones/scpok.png")));
            
        } else {
            
           lblstatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/scp/icones/scperror.png")));
           
        }   
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnLogin = new javax.swing.JButton();
        lblusuario = new javax.swing.JLabel();
        lblsenha = new javax.swing.JLabel();
        txtusuario = new javax.swing.JTextField();
        txtsenha = new javax.swing.JPasswordField();
        lblstatus = new javax.swing.JLabel();
        btnlogar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        btnLogin.setText("Login");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SCP - Login");
        setResizable(false);

        lblusuario.setFont(new java.awt.Font("Abyssinica SIL", 1, 14)); // NOI18N
        lblusuario.setText("Usuario");

        lblsenha.setFont(new java.awt.Font("Abyssinica SIL", 1, 14)); // NOI18N
        lblsenha.setText("Senha");

        lblstatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/scp/icones/scpok.png"))); // NOI18N
        lblstatus.setPreferredSize(new java.awt.Dimension(128, 128));

        btnlogar.setBackground(new java.awt.Color(255, 0, 51));
        btnlogar.setFont(new java.awt.Font("Abyssinica SIL", 1, 14)); // NOI18N
        btnlogar.setText("Logar");
        btnlogar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlogarActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/scp/icones/SCP.png"))); // NOI18N
        jLabel2.setMaximumSize(new java.awt.Dimension(56, 49));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblusuario)
                            .addComponent(lblsenha))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtusuario, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtsenha, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(lblstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(93, 93, 93)
                        .addComponent(btnlogar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(62, 62, 62))
            .addGroup(layout.createSequentialGroup()
                .addGap(174, 174, 174)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtusuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblusuario))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblsenha, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtsenha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnlogar))
                .addGap(32, 32, 32))
        );

        setSize(new java.awt.Dimension(410, 305));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnlogarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlogarActionPerformed
        // TODO add your handling code here:
        
        // chamando o método logar
        logar(); 
        
    }//GEN-LAST:event_btnlogarActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Tela_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tela_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tela_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tela_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            
            public void run() {
                
                new Tela_Login().setVisible(true);
                
            }
            
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnlogar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblsenha;
    private javax.swing.JLabel lblstatus;
    private javax.swing.JLabel lblusuario;
    private javax.swing.JPasswordField txtsenha;
    private javax.swing.JTextField txtusuario;
    // End of variables declaration//GEN-END:variables
}
