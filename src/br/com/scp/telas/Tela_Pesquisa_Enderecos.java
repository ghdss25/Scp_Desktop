
package br.com.scp.telas;

import br.com.scp.dao.Conexao_SCP;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public class Tela_Pesquisa_Enderecos extends javax.swing.JInternalFrame {
    
    
    // Usando a variavel conexão do DAL 
    Connection conexao = null; 

    // são frameworks do pacote java.sql e executar as instruções SQL
    PreparedStatement pst = null;

    // são frameworks do pacote java.sql e executar as instruções SQL
    ResultSet rs = null; 
    
    public Tela_Pesquisa_Enderecos() {
        
        initComponents();
        
        conexao = Conexao_SCP.conector(); 
    }
    
    public void listar_enderecos() {
        
        try {
            
            String sql = "SELECT En.id, cidade, estado, logradouro, bairro, numero, cep, id_cliente, \n" +
"CLI.nome, cpf FROM enderecos AS En INNER JOIN clientes AS CLI ON (CLI.id = En.id_cliente)"; 
            
            PreparedStatement pst = conexao.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            tbl_enderecos.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (Exception e) {
            
            e.printStackTrace(); 
        }
    }
    
    private void pesquisar_enderecos() {
        
        // método para pesquisar clientes nome com filtro 
		
	String sql = "SELECT id, cidade, estado, logradouro, numero, cep, id_cliente FROM enderecos WHERE cidade LIKE ?";
        
        try {
            
            pst = conexao.prepareStatement(sql); 
			
            //passando o contéudo da caixa de pesquisa para o ? 
            // atenção ao "%" - continuação da String sql 

            pst.setString(1, txt_end_pesquisar.getText() + "%"); 
            rs = pst.executeQuery(); 

            // A linha abaixo usa a biblioteca rs2xml.jar para preencher a tabela 
            tbl_enderecos.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            
            JOptionPane.showConfirmDialog(null, e); 
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txt_end_pesquisar = new javax.swing.JTextField();
        btn_Consultar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_enderecos = new javax.swing.JTable();
        btnlistar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Pesquisa de Endereços");

        jLabel1.setFont(new java.awt.Font("Abyssinica SIL", 1, 18)); // NOI18N
        jLabel1.setText("Informe o nome para a sua Pesquisa");

        txt_end_pesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_end_pesquisarKeyReleased(evt);
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

        tbl_enderecos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbl_enderecos);

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
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 537, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(239, 239, 239)
                            .addComponent(btnlistar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(226, 226, 226))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel1)
                                .addComponent(txt_end_pesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(40, 40, 40)
                            .addComponent(btn_Consultar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(36, 36, 36))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 304, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1)
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txt_end_pesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_Consultar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(44, 44, 44)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(34, 34, 34)
                    .addComponent(btnlistar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_end_pesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_end_pesquisarKeyReleased
        // O método chama a pesquisa de endereços no banco de dados
        
        pesquisar_enderecos();
    }//GEN-LAST:event_txt_end_pesquisarKeyReleased

    private void btn_ConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ConsultarActionPerformed
        // Chamando o método consultar:
    }//GEN-LAST:event_btn_ConsultarActionPerformed

    private void btnlistarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlistarActionPerformed
        // TODO add your handling code here:

       listar_enderecos();
    }//GEN-LAST:event_btnlistarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Consultar;
    private javax.swing.JButton btnlistar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_enderecos;
    private javax.swing.JTextField txt_end_pesquisar;
    // End of variables declaration//GEN-END:variables
}
