package TCP_chat_room.GUI;

import java.awt.Color;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.net.ConnectException;

import TCP_chat_room.model.db.Message;
import TCP_chat_room.model.db.UserInfo;
import TCP_chat_room.server.TCPclient;

/**
 *
 * @author Shinelon
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.ImageIcon;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Shinelon
 */
public class LoginFrame extends javax.swing.JFrame {

    /**
     * Creates new form LoginFrame
     */
    public LoginFrame() {
    	initComponents();
    	initClient();    		
    }

    public void initClient() {
		// ��������ʼ���ͻ���	��ʱ������Ҫ������groupChatFrame��û�д��� ����ֻ�Ǹ������� 
    	// ��Ҫ�ڵ�½�ɹ�֮�� ��ʼ��groupChatFrame �ٴθ�client�е�frame��ֵ
		client = new TCPclient(groupChatFrame);
		try {
			client.initClient();
		} catch (IOException e) {
			e.printStackTrace();
			jLabel_tips.setForeground(Color.RED);
			jLabel_tips.setText("���ӷ�����ʧ�ܣ�����Ƿ��������ˣ�");
		}
    }
    
    //��ʼ������frame
    public void initChatFrame(TCPclient client) {
		groupChatFrame = new GroupChatFrame(client);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTextField_username = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPasswordField_password = new javax.swing.JPasswordField();
        jLabel_tips = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("������Ůר�ó��������� v1.0");
        setLocation(new java.awt.Point(710, 256));
        setName("frame_login"); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setFont(new java.awt.Font("΢���ź�", 0, 18)); // NOI18N

        jButton1.setText("��½");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					jButton1ActionPerformed(evt);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

        jButton2.setText("ע��");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
		jLabel_tips.setForeground(new Color(2,94,33));
        jLabel_tips.setText("�����˺�������е�½/ע��");
        jTextField_username.setFont(new java.awt.Font("΢���ź�", 0, 12)); // NOI18N
        jTextField_username.setMargin(new java.awt.Insets(2, 8, 2, 8));
        jTextField_username.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField_usernameFocusGained(evt);
            }
        });
        jTextField_username.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField_usernameMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextField_usernameMouseEntered(evt);
            }
        });
        jTextField_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_usernameActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("΢���ź�", 0, 12)); // NOI18N
        jLabel1.setText("�û�����");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel2.setFont(new java.awt.Font("΢���ź�", 0, 12)); // NOI18N
        jLabel2.setText("��  �룺");

        jPasswordField_password.setMargin(new java.awt.Insets(2, 8, 2, 8));

        jLabel_tips.setMaximumSize(new java.awt.Dimension(10, 20));
        jLabel_tips.setMinimumSize(new java.awt.Dimension(10, 20));
        jLabel_tips.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jLabel_tipsFocusGained(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel_tips, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                                .addGap(41, 41, 41)
                                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE))
                            .addComponent(jTextField_username)
                            .addComponent(jPasswordField_password))
                        .addGap(129, 129, 129))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_username, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(4, 4, 4)
                .addComponent(jLabel_tips, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jPasswordField_password, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(79, 79, 79))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

    private void jTextField_usernameActionPerformed(java.awt.event.ActionEvent evt) {                                                    
        // TODO add your handling code here:
    }                                                   

    private void jTextField_usernameMouseEntered(java.awt.event.MouseEvent evt) {                                                 
        // TODO add your handling code here:
        //�����ǰ��ʾ��������placeholder ��ôɾ��
    }                                                

    private void jTextField_usernameMouseClicked(java.awt.event.MouseEvent evt) {                                                 
        // TODO add your handling code here:
    }                                                

    private void jTextField_usernameFocusGained(java.awt.event.FocusEvent evt) {
		// TODO add your handling code here:
	}

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		// ��ȡ�û���������
		String username = jTextField_username.getText();
		String password = new String(jPasswordField_password.getPassword());
		boolean registerSuccess = false;
		try {
			registerSuccess = client.register(username, password);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		// ��½�ɹ�
		if (registerSuccess) {
			System.out.println("ע��ɹ�");
			// �������
			jPasswordField_password.setText("");

		} else {
			// ע��ʧ�� �˺�����ʱ��ʾ��ʾ��Ϣ
			jTextField_username.setText("ע��ʧ��");
			System.out.println("ע��ʧ��");
			jPasswordField_password.setText("");
		}

	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) throws InterruptedException {
		// ��ȡ�û���������
		String username = jTextField_username.getText();
		String password = new String(jPasswordField_password.getPassword());
		boolean loginSuccess = false;
		try {
			loginSuccess = client.login(username, password);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		// ��½�ɹ�
		if (loginSuccess) {
			System.out.println("��½�ɹ�");
			loginSuccessFlag=true;
			jLabel_tips.setForeground(new Color(2,94,33));
			jLabel_tips.setVisible(true);
			jLabel_tips.setText("���ڵ�½...");
			
    		//��ʼ��������ҳ��
        	initChatFrame(client);
        	//��ʱ �����е�groupChatFrame�ų�ʼ����� ��Ҫ�ٴθ�ֵ��client�ж���������
        	client.setGroupChatframe(groupChatFrame);
        	
			jLabel_tips.requestFocusInWindow();
		} else {
			// ��½ʧ�� ���������
			System.out.println("�˺Ż��������");
			loginSuccessFlag=false;
			jLabel_tips.setText("�˺Ż��������");
			jLabel_tips.setForeground(Color.red);
			jPasswordField_password.setText("");
			jLabel_tips.requestFocusInWindow();
		}

	}
	
    private void jLabel_tipsFocusGained(java.awt.event.FocusEvent evt) {                                        
        // TODO add your handling code here:
		//Thread.sleep(1000);
    	try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	if(loginSuccessFlag) {
    		// ���ص�½ҳ��
    		this.setVisible(false);
    		// ��ʾ������
    		groupChatFrame.setVisible(true);
    	}else {
    		
    	}

    }                     
                                    

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
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel_tips;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordField_password;
    private javax.swing.JTextField jTextField_username;
    // End of variables declaration    
    
    //frames
    private GroupChatFrame groupChatFrame=null;
    
	// client
	private TCPclient client;
	private boolean loginSuccessFlag=false;
}