
package br.com.scp.telas;

import br.com.scp.dao.Conexao_SCP;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class Tela_Menu_SCP extends javax.swing.JFrame {
    
    // Usando a variavel conexão do DAL 
    Connection conexao = null; 

    // são frameworks do pacote java.sql e executar as instruções SQL
    PreparedStatement pst = null;

    // são frameworks do pacote java.sql e executar as instruções SQL
    ResultSet rs = null; 

    public Tela_Menu_SCP() {
        
        initComponents();
        
        conexao = Conexao_SCP.conector(); 
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        desktop = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        lblcliente = new javax.swing.JLabel();
        lbldata = new javax.swing.JLabel();
        Menu = new javax.swing.JMenuBar();
        menCad = new javax.swing.JMenu();
        MenCadCli = new javax.swing.JMenuItem();
        MenCadPro = new javax.swing.JMenuItem();
        MenCadEnd = new javax.swing.JMenuItem();
        MenCadTel = new javax.swing.JMenuItem();
        MenNF = new javax.swing.JMenuItem();
        MenRel = new javax.swing.JMenu();
        menRelCli = new javax.swing.JMenuItem();
        MenRelNf = new javax.swing.JMenuItem();
        MenRelPr = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        Pes_Cli = new javax.swing.JMenuItem();
        Pes_Pro = new javax.swing.JMenuItem();
        Pes_Endereco = new javax.swing.JMenuItem();
        Pes_Telefone = new javax.swing.JMenuItem();
        MenAju = new javax.swing.JMenu();
        MenAjuSob = new javax.swing.JMenuItem();
        MenOpc = new javax.swing.JMenu();
        MenOpcSai = new javax.swing.JMenuItem();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu3.setText("Edit");
        jMenuBar1.add(jMenu3);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SCP - Sistema de Cadastro e Produto");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        desktop.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout desktopLayout = new javax.swing.GroupLayout(desktop);
        desktop.setLayout(desktopLayout);
        desktopLayout.setHorizontalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 590, Short.MAX_VALUE)
        );
        desktopLayout.setVerticalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/scp/icones/SCP.png"))); // NOI18N

        lblcliente.setFont(new java.awt.Font("Abyssinica SIL", 1, 18)); // NOI18N
        lblcliente.setText("Cliente");

        lbldata.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lbldata.setText("Data");

        menCad.setText("Cadastro");
        menCad.setEnabled(false);

        MenCadCli.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        MenCadCli.setText("Clientes");
        MenCadCli.setEnabled(false);
        MenCadCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenCadCliActionPerformed(evt);
            }
        });
        menCad.add(MenCadCli);

        MenCadPro.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK));
        MenCadPro.setText("Produtos");
        MenCadPro.setEnabled(false);
        MenCadPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenCadProActionPerformed(evt);
            }
        });
        menCad.add(MenCadPro);

        MenCadEnd.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_MASK));
        MenCadEnd.setText("Endereços");
        MenCadEnd.setEnabled(false);
        MenCadEnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenCadEndActionPerformed(evt);
            }
        });
        menCad.add(MenCadEnd);

        MenCadTel.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.ALT_MASK));
        MenCadTel.setText("Telefones");
        MenCadTel.setEnabled(false);
        MenCadTel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenCadTelActionPerformed(evt);
            }
        });
        menCad.add(MenCadTel);

        MenNF.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK));
        MenNF.setText("Notas Fiscais");
        MenNF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenNFActionPerformed(evt);
            }
        });
        menCad.add(MenNF);

        Menu.add(menCad);

        MenRel.setText("Relatórios");
        MenRel.setEnabled(false);

        menRelCli.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK));
        menRelCli.setText("Clientes");
        menRelCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menRelCliActionPerformed(evt);
            }
        });
        MenRel.add(menRelCli);

        MenRelNf.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.ALT_MASK));
        MenRelNf.setText("Notas Fiscais");
        MenRelNf.setEnabled(false);
        MenRelNf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenRelNfActionPerformed(evt);
            }
        });
        MenRel.add(MenRelNf);

        MenRelPr.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK));
        MenRelPr.setText("Produtos");
        MenRelPr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenRelPrActionPerformed(evt);
            }
        });
        MenRel.add(MenRelPr);

        Menu.add(MenRel);

        jMenu2.setText("Pesquisas");

        Pes_Cli.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        Pes_Cli.setText("Pesquisar Clientes");
        Pes_Cli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Pes_CliActionPerformed(evt);
            }
        });
        jMenu2.add(Pes_Cli);

        Pes_Pro.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        Pes_Pro.setText("Pesquisar Produtos");
        Pes_Pro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Pes_ProActionPerformed(evt);
            }
        });
        jMenu2.add(Pes_Pro);

        Pes_Endereco.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        Pes_Endereco.setText("Pesquisar Endereços");
        Pes_Endereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Pes_EnderecoActionPerformed(evt);
            }
        });
        jMenu2.add(Pes_Endereco);

        Pes_Telefone.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        Pes_Telefone.setText("Pesquisar Telefones");
        Pes_Telefone.addHierarchyBoundsListener(new java.awt.event.HierarchyBoundsListener() {
            public void ancestorMoved(java.awt.event.HierarchyEvent evt) {
                Pes_TelefoneAncestorMoved(evt);
            }
            public void ancestorResized(java.awt.event.HierarchyEvent evt) {
            }
        });
        Pes_Telefone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Pes_TelefoneActionPerformed(evt);
            }
        });
        jMenu2.add(Pes_Telefone);

        Menu.add(jMenu2);

        MenAju.setText("Ajuda");

        MenAjuSob.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, java.awt.event.InputEvent.ALT_MASK));
        MenAjuSob.setText("Sobre");
        MenAjuSob.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenAjuSobActionPerformed(evt);
            }
        });
        MenAju.add(MenAjuSob);

        Menu.add(MenAju);

        MenOpc.setText("Opções");

        MenOpcSai.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        MenOpcSai.setText("Sair");
        MenOpcSai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenOpcSaiActionPerformed(evt);
            }
        });
        MenOpc.add(MenOpcSai);

        Menu.add(MenOpc);

        setJMenuBar(Menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(desktop)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 124, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(123, 123, 123))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblcliente)
                            .addComponent(lbldata))
                        .addGap(87, 87, 87))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(desktop)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel1)
                .addGap(64, 64, 64)
                .addComponent(lblcliente)
                .addGap(75, 75, 75)
                .addComponent(lbldata)
                .addContainerGap(149, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(897, 465));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void MenCadEndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenCadEndActionPerformed
       // As Linhas Abaixo vão abrir o form Tela do Endereço dentro do 
        // desktop pane 
        
        Tela_Endereco endereco = new Tela_Endereco();
        endereco.setVisible(true);
        desktop.add(endereco);
    }//GEN-LAST:event_MenCadEndActionPerformed

    private void MenAjuSobActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenAjuSobActionPerformed
        // chamando a tela sobre 
        
        Tela_Sobre sobre = new Tela_Sobre();
        sobre.setVisible(true);
       
    }//GEN-LAST:event_MenAjuSobActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        
        // As linhas abaixo substituem a label lbldata pela data atual do 
        // sistema ao iniciar o form 
        
        Date data = new Date(); 
        DateFormat formatador = DateFormat.getDateInstance(DateFormat.SHORT);
        lbldata.setText(formatador.format(data)); 
        
    }//GEN-LAST:event_formWindowActivated

    private void MenOpcSaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenOpcSaiActionPerformed
        // exibe uma caixa de diálogo 
        
        int sair = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair ? ", 
                "Atenção", JOptionPane.YES_NO_OPTION);
        
        if(sair == JOptionPane.YES_OPTION) {
            
            System.exit(0);
        }
        
    }//GEN-LAST:event_MenOpcSaiActionPerformed

    private void MenCadCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenCadCliActionPerformed
        // As Linhas Abaixo vão abrir o form Tela do Usuario dentro do 
        // desktop pane 
        
        Tela_Cliente cliente = new Tela_Cliente(); 
        cliente.setVisible(true);
        desktop.add(cliente);
    }//GEN-LAST:event_MenCadCliActionPerformed

    private void MenCadProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenCadProActionPerformed
       // As Linhas Abaixo vão abrir o form Tela do Produto dentro do 
        // desktop pane 
        
        Tela_Produto produto = new Tela_Produto(); 
        produto.setVisible(true);
        desktop.add(produto);
    }//GEN-LAST:event_MenCadProActionPerformed

    private void MenCadTelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenCadTelActionPerformed
        /// As Linhas Abaixo vão abrir o form Tela do Telefone dentro do 
        // desktop pane 
        
        Tela_Telefone telefone = new Tela_Telefone(); 
        telefone.setVisible(true);
        desktop.add(telefone); 
    }//GEN-LAST:event_MenCadTelActionPerformed

    private void Pes_CliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Pes_CliActionPerformed
        // Chamando a Tela Pesquisa Cliente 
        
        Tela_Pesquisa_Cliente pc = new Tela_Pesquisa_Cliente();
        pc.setVisible(true);
        desktop.add(pc);
    }//GEN-LAST:event_Pes_CliActionPerformed

    private void Pes_ProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Pes_ProActionPerformed
       // Chamando a Tela Pesquisa de Produtos: 
       
       Tela_Pesquisa_Produtos pp = new Tela_Pesquisa_Produtos(); 
       pp.setVisible(true);
       desktop.add(pp);
    }//GEN-LAST:event_Pes_ProActionPerformed

    private void Pes_EnderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Pes_EnderecoActionPerformed
        // Chamando a Tela Pesquisa de Endereços:
        
        Tela_Pesquisa_Enderecos pe = new Tela_Pesquisa_Enderecos(); 
        pe.setVisible(true);
        desktop.add(pe); 
        
    }//GEN-LAST:event_Pes_EnderecoActionPerformed

    private void Pes_TelefoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Pes_TelefoneActionPerformed
       // Chamando a Tela Pesquisa de Telefones:
       
        Tela_Pesquisa_Telefones pt = new Tela_Pesquisa_Telefones(); 
        pt.setVisible(true); 
        desktop.add(pt);
        
    }//GEN-LAST:event_Pes_TelefoneActionPerformed

    private void Pes_TelefoneAncestorMoved(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_Pes_TelefoneAncestorMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_Pes_TelefoneAncestorMoved

    private void MenNFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenNFActionPerformed
        // TODO add your handling code here:
        
        Tela_Notas_Fiscais tf = new Tela_Notas_Fiscais();
        tf.setVisible(true);
        desktop.add(tf);
    }//GEN-LAST:event_MenNFActionPerformed

    private void menRelCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menRelCliActionPerformed
        // Gerando o Relatório de Clientes:
        Relatorio_Clientes();
    }//GEN-LAST:event_menRelCliActionPerformed

    private void MenRelNfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenRelNfActionPerformed
        // Gerando o Relatório de Notas Fiscais: 
        
        Relatorios_Notas_Fiscais_Clientes();
    }//GEN-LAST:event_MenRelNfActionPerformed

    private void MenRelPrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenRelPrActionPerformed
        // Gerando o Relatório de Produtos dos Clientes: 
        Relatorios_Produtos_dos_Clientes();
    }//GEN-LAST:event_MenRelPrActionPerformed

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
            java.util.logging.Logger.getLogger(Tela_Menu_SCP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tela_Menu_SCP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tela_Menu_SCP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tela_Menu_SCP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tela_Menu_SCP().setVisible(true);
            }
        });
    }
    
    private void Relatorio_Clientes() {
        
        int confirma = JOptionPane.showConfirmDialog(null, "Confirma a impressão de relatório ?", "Atenção", JOptionPane.YES_NO_OPTION);
        
        if (confirma == JOptionPane.YES_OPTION) {
            
            // Imprimindo relatório com o framework JasperReports 
            try {
                
                // Usando a classe JasperPrint para preparar a impressão de um relatório 
                
                JasperPrint print = JasperFillManager.fillReport("/home/gustavo/Documentos/Reports/clientes.jasper",null, conexao);
                
                // A linha abaixo exibe o relatório através da classe JasperViewer 
                JasperViewer.viewReport(print, false);
                
            } catch (Exception e) {
                
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    
    private void Relatorios_Notas_Fiscais_Clientes() {
        
        int confirma = JOptionPane.showConfirmDialog(null, "Confirma a emissão desse relatório ?", "Atenção", JOptionPane.YES_NO_OPTION);
        
        if (confirma == JOptionPane.YES_OPTION) {
            
            // Imprimindo relatório com o framework JasperReports 
            try {
                
                // Usando a classe JasperPrint para preparar a emissão de relatórios 
                
                JasperPrint print = JasperFillManager.fillReport("/home/gustavo/Documentos/Reports/nf.jasper",null, conexao);
                
                // A linha abaixo exibe o relatório através da classe JasperViewer 
                JasperViewer.viewReport(print, false);
                
            } catch (Exception e) {
                
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    
    private void Relatorios_Produtos_dos_Clientes() {
        
        
        int confirma = JOptionPane.showConfirmDialog(null, "Confirma a emissão desse relatório ?", "Atenção", JOptionPane.YES_NO_OPTION);
        
        if (confirma == JOptionPane.YES_OPTION) {
            
            // Imprimindo relatório com o framework JasperReports 
            try {
                
                // Usando a classe JasperPrint para preparar a emissão de relatórios 
                
                JasperPrint print = JasperFillManager.fillReport("/home/gustavo/Documentos/Reports/Produtos_Clientes.jasper",null, conexao);
                
                // A linha abaixo exibe o relatório através da classe JasperViewer 
                JasperViewer.viewReport(print, false);
                
            } catch (Exception e) {
                
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu MenAju;
    private javax.swing.JMenuItem MenAjuSob;
    public static javax.swing.JMenuItem MenCadCli;
    public static javax.swing.JMenuItem MenCadEnd;
    public static javax.swing.JMenuItem MenCadPro;
    public static javax.swing.JMenuItem MenCadTel;
    private javax.swing.JMenuItem MenNF;
    private javax.swing.JMenu MenOpc;
    private javax.swing.JMenuItem MenOpcSai;
    public static javax.swing.JMenu MenRel;
    public static javax.swing.JMenuItem MenRelNf;
    private javax.swing.JMenuItem MenRelPr;
    private javax.swing.JMenuBar Menu;
    private javax.swing.JMenuItem Pes_Cli;
    private javax.swing.JMenuItem Pes_Endereco;
    private javax.swing.JMenuItem Pes_Pro;
    private javax.swing.JMenuItem Pes_Telefone;
    private javax.swing.JDesktopPane desktop;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    public static javax.swing.JLabel lblcliente;
    private javax.swing.JLabel lbldata;
    public static javax.swing.JMenu menCad;
    private javax.swing.JMenuItem menRelCli;
    // End of variables declaration//GEN-END:variables
}
