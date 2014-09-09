package view;

import control.Core;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import tcp.TCPServer;

/**
 *
 * @author LuizVenturote https://github.com/luizventurote
 */
public class MainWin extends javax.swing.JFrame {

    private Core core;
    private TrayIcon trayIcon = null;
    private SystemTray tray = null;

    /**
     * Creates new form MainWin
     */
    public MainWin() {
        initComponents();
        this.setIcon();

        //Setando versão
        this.version.setText("v0.0.1");

        //Adicionando Opção de SystemTray
        this.adicionarSystemTray();

        // Iniciar o Core do sistema
        this.core = new Core();
    }

    private void adicionarSystemTray() {
        if (SystemTray.isSupported()) {
            tray = SystemTray.getSystemTray();

            //Adicionando Icone
            URL url = this.getClass().getResource("/img/icon.png");
            Image image = Toolkit.getDefaultToolkit().getImage(url);

            //Criando menu PopUp do Tray
            PopupMenu popup = new PopupMenu();

            //Criando menu Abrir
            MenuItem defaultItem = new MenuItem("Abrir");
            defaultItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    tray.remove(trayIcon);
                    setVisible(true);
                }
            });
            //Inserindo o menu Abrir no PopUpMenu 'popup'
            popup.add(defaultItem);

            //#######################################
            //Criando menu Sair
            defaultItem = new MenuItem("Sair");
            //Adicionando Listener para sair quando clicar no Botao criado acima
            defaultItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
            //Inserindo o menu Sair no PopUpMenu 'popup'
            popup.add(defaultItem);

            //Adicionando nome ao icone no Tray
            trayIcon = new TrayIcon(image, "Resytor " + version.getText(), popup);
            trayIcon.setImageAutoSize(true);

            //Adicionando opção de abrir ao dar duplo clique no icone
            trayIcon.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    tray.remove(trayIcon);
                    setVisible(true);
                }
            });
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao minimizar", "O sistema não suporta System Tray.", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        btn_initServer = new javax.swing.JToggleButton();
        version = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setTitle("RESYTOR - Server Application");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Logo.png"))); // NOI18N

        btn_initServer.setText("Iniciar Servidor");
        btn_initServer.setBorderPainted(false);
        btn_initServer.setFocusPainted(false);
        btn_initServer.setRequestFocusEnabled(false);
        btn_initServer.setRolloverEnabled(false);
        btn_initServer.setVerifyInputWhenFocusTarget(false);
        btn_initServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_initServerActionPerformed(evt);
            }
        });

        version.setText("v0.1.0");

        jLabel4.setText("DevIFES - M19");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(44, 44, 44))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_initServer, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(version)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel2)
                .addGap(28, 28, 28)
                .addComponent(btn_initServer, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(version)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        try {
            tray.add(trayIcon);
            setVisible(false);
            System.out.println("Adicionado ao SystemTray");
        } catch (AWTException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao minimizar", "O sistema não suporta System Tray.", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_formWindowClosing

    private void btn_initServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_initServerActionPerformed

        try {
            // TODO add your handling code here:
            core.criarBD();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO no Banco de Dados: " + 
                        e.getMessage() );     
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO não esperado. " + 
                        e.getMessage() );
        }
        
        // Inicia o Servidor
        TCPServer server_con = new TCPServer();
        server_con.start();
        

    }//GEN-LAST:event_btn_initServerActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(MainWin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                MainWin wm = new MainWin();
                wm.setLocationRelativeTo(null);
                wm.setVisible(true);
            }
        });
    }

    public void setIcon() {
        URL url = this.getClass().getResource("/img/icon.png");
        Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(url);
        this.setIconImage(imagemTitulo);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btn_initServer;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel version;
    // End of variables declaration//GEN-END:variables
}
