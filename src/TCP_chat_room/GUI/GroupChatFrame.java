package TCP_chat_room.GUI;

import java.awt.AWTEvent;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import TCP_chat_room.model.Const;
import TCP_chat_room.model.db.Message;
import TCP_chat_room.server.TCPclient;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Shinelon
 */
public class GroupChatFrame extends javax.swing.JFrame implements ChatFrame {
	public GroupChatFrame(TCPclient client) {
		this.client = client;
		// 默认构造中调用了组件初始化方法 这里要加上
		initComponents();
		// 初始化UI显示信息 并开启刷新信息的线程
		initInfo();
	}

	private void initInfo() {
		// 设置一下标题中的登陆信息
		this.setTitle(this.getTitle() + "  -user: " + client.getClientInfo().getUser().getUsername());
		try {
			jLabel_show_onlinenum.setText(String.valueOf(client.queryOnlineNumber()));
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//不需要循环取了 只要一开始查一次就够了 之后的人数变化会通过推送消息得到
//		new Timer().schedule(new TimerTask() {
//            @Override
//            public void run() {
//                try {
//    				jLabel_show_onlinenum.setText(String.valueOf(client.queryOnlineNumber()));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        },0,5000);
	}

	/**
	 * Creates new form GroupChatFrame
	 */
	public GroupChatFrame() {
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {
		// 为了自定义关闭事件 移交窗口事件控制权
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);

		jSeparator1 = new javax.swing.JSeparator();
		jScrollPane1 = new javax.swing.JScrollPane();
		jEditorPane_input = new javax.swing.JEditorPane();
		jButton_close = new javax.swing.JButton();
		jButton_send = new javax.swing.JButton();
		jScrollPane2 = new javax.swing.JScrollPane();
		jTextArea_show_messages = new javax.swing.JEditorPane();
		jLabel_tips_mynickname = new javax.swing.JLabel();
		jTextField_nickname = new javax.swing.JTextField();
		jLabel_tmp_tips = new javax.swing.JLabel();
		jButton_modify_userinfo = new javax.swing.JButton();
		jLabel_tips_onlinenum = new javax.swing.JLabel();
		jLabel_show_onlinenum = new javax.swing.JLabel();
		jLabel_img = new javax.swing.JLabel();
		
		//默认昵称为账号
		nickname=client.getClientInfo().getUser().getUsername();
		jTextField_nickname.setText(nickname);	
		
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("俊男靓女专用冲浪聊天室 v1.0");
		setLocation(new java.awt.Point(656, 211));
		setMinimumSize(new java.awt.Dimension(756, 635));
		setMaximumSize(new java.awt.Dimension(7800, 650));
		setName("俊男靓女专用冲浪聊天室 v1.0"); // NOI18N

		jScrollPane1.setHorizontalScrollBar(null);

		jEditorPane_input.setAutoscrolls(false);
		jEditorPane_input.setMargin(new java.awt.Insets(4, 8, 4, 8));
		jEditorPane_input.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				jEditorPane_inputKeyPressed(evt);
			}
		});
		jScrollPane1.setViewportView(jEditorPane_input);

		jButton_close.setText("关闭");
		jButton_close.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton_closeActionPerformed(evt);
			}
		});
		jButton_send.setText("发送");
		jButton_send.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton_sendActionPerformed(evt);
			}
		});

		jScrollPane2.setHorizontalScrollBar(null);

		jTextArea_show_messages.setEditable(false);
//		jTextArea_show_messages.setColumns(20);
//		jTextArea_show_messages.setRows(5);
		jScrollPane2.setViewportView(jTextArea_show_messages);

		jLabel_tips_mynickname.setFont(new java.awt.Font("微软雅黑", 0, 18)); // NOI18N
		jLabel_tips_mynickname.setText("我的昵称：");

		jLabel_tmp_tips.setForeground(new java.awt.Color(153, 153, 153));
		jLabel_tmp_tips.setText("做UI太麻烦了orz 暂时只填这些信息咯");

		jButton_modify_userinfo.setText("修改");
		jButton_modify_userinfo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton_modify_userinfoActionPerformed(evt);
			}
		});

		jLabel_tips_onlinenum.setText("当前在线人数：");

		jLabel_show_onlinenum.setText("0");

		jLabel_img.setIcon(
				new javax.swing.ImageIcon("F:\\Eclipse\\JavaSE_WorkSpace\\SocketLab\\src\\resourses\\1111E.jpg")); // NOI18N

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jSeparator1)
				.addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 738, Short.MAX_VALUE)
						.addGroup(layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE)
								.addComponent(jButton_close, javax.swing.GroupLayout.PREFERRED_SIZE, 130,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(jButton_send, javax.swing.GroupLayout.PREFERRED_SIZE, 137,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(25, 25, 25))
						.addGroup(layout.createSequentialGroup()
								.addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 482,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(jLabel_tmp_tips)
										.addGroup(layout.createSequentialGroup()
												.addComponent(jLabel_tips_mynickname,
														javax.swing.GroupLayout.PREFERRED_SIZE, 90,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addGroup(layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(jButton_modify_userinfo,
																javax.swing.GroupLayout.DEFAULT_SIZE, 129,
																Short.MAX_VALUE)
														.addComponent(jTextField_nickname)))
										.addGroup(layout.createSequentialGroup().addComponent(jLabel_tips_onlinenum)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(jLabel_show_onlinenum))
										.addComponent(jLabel_img, javax.swing.GroupLayout.DEFAULT_SIZE, 229,
												Short.MAX_VALUE))))
						.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGroup(layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jScrollPane2).addGap(18,
								18, 18))
						.addGroup(layout.createSequentialGroup().addGap(20, 20, 20)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jLabel_tips_onlinenum).addComponent(jLabel_show_onlinenum))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(jLabel_img, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jTextField_nickname, javax.swing.GroupLayout.PREFERRED_SIZE, 35,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jLabel_tips_mynickname, javax.swing.GroupLayout.PREFERRED_SIZE,
												41, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(jButton_modify_userinfo).addGap(9, 9, 9).addComponent(jLabel_tmp_tips)
								.addGap(28, 28, 28)))
						.addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 120,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jButton_close, javax.swing.GroupLayout.PREFERRED_SIZE, 26,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jButton_send, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addContainerGap()));

		pack();
	}// </editor-fold>

	protected void processWindowEvent(final WindowEvent pEvent) {
		if (pEvent.getID() == WindowEvent.WINDOW_CLOSING) {
			int option = JOptionPane.showConfirmDialog(null, "是否关闭程序？", "程序退出提示", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION) {
				client.exit();
				System.exit(0);
			} else {
				// 用户选择不退出本程序，因此可以继续留在本窗口
			}
		} else {
			// 忽略其他事件，交给JFrame处理
			super.processWindowEvent(pEvent);
		}
	}

	private void jButton_sendActionPerformed(java.awt.event.ActionEvent evt) {
		// 发送消息
		sendMSG();
	}

	private void sendMSG() {
		// 获取当前输入框内容
		String trim = jEditorPane_input.getText().trim();
		if (trim == null || trim.length() == 0) {
			System.out.println("消息不能为空");
		} else {
			// 封装message对象
			Message message = new Message();
			message.setCreate_at(new Date());
			message.setChannel_type(Const.MessageChannelTypeTypeEnum.GROUP_CHAT);
			message.setMessage_type(Const.MessageTypeEnum.TEXT);
			message.setReceiver_id(Const.ROOT_ROOM_ID); // 发送目标群号
			message.setSender_id(client.getClientInfo().getUserInfo().getUid());
//			message.setSenderNickname(client.getClientInfo().getUserInfo().getNickname());
			message.setSenderNickname(nickname);		//暂时还没有实现修改用户资料 先拿本地变量随时修改昵称
			// message.setSenderNickname(client.getClientInfo().getUser().getUsername()); //
			// nickname存在中文乱码的问题 先用username代替
			message.setContent(trim);

			// 调用发送接口
			try {
				boolean sendMSG = client.sendMSG(message);
				// 发送成功
				if (sendMSG) {
					// 清空输入栏
					jEditorPane_input.setText("");
					// 输入框获得焦点
					jEditorPane_input.requestFocus();
				} else {
					jEditorPane_input.setText("发送失败，请重试");
				}
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private void jButton_closeActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		System.out.println("关闭");
		int option = JOptionPane.showConfirmDialog(null, "是否关闭程序？", "程序退出提示", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			client.exit();
			System.exit(0);
		} else {
			// 用户选择不退出本程序，因此可以继续留在本窗口
		}
	}

	private void jEditorPane_inputKeyPressed(java.awt.event.KeyEvent evt) {
		// shift+回车换行
		if ((char) evt.getKeyChar() == KeyEvent.VK_ENTER && evt.isShiftDown()) {
			jEditorPane_input.setText(jEditorPane_input.getText() + "\n");
		}
		// 回车发送
		else if ((char) evt.getKeyChar() == KeyEvent.VK_ENTER) {
			sendMSG();
			evt.consume();
		}
	}

	private void jButton_modify_userinfoActionPerformed(java.awt.event.ActionEvent evt) {
		//试图更新昵称
		String trim = jTextField_nickname.getText().trim();
		if(trim!=null &&trim.length()!=0) {
			nickname=trim;
		}
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
		// (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the default
		 * look and feel. For details see
		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(GroupChatFrame.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(GroupChatFrame.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(GroupChatFrame.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(GroupChatFrame.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new GroupChatFrame().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify
	private javax.swing.JButton jButton_close;
	private javax.swing.JButton jButton_modify_userinfo;
	private javax.swing.JButton jButton_send;
	private javax.swing.JEditorPane jEditorPane_input;
	private javax.swing.JLabel jLabel_img;
	public javax.swing.JLabel jLabel_show_onlinenum;
	private javax.swing.JLabel jLabel_tips_mynickname;
	private javax.swing.JLabel jLabel_tips_onlinenum;
	private javax.swing.JLabel jLabel_tmp_tips;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JSeparator jSeparator1;
	private javax.swing.JEditorPane jTextArea_show_messages;
	private javax.swing.JTextField jTextField_nickname;
	// End of variables declaration

	private TCPclient client;
	private String nickname;
	@Override
	public void showMessage(Message message) {
		// 向jTextArea1中写入此消息
		StringBuilder sb = new StringBuilder();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		sb.append("\n" + message.getSenderNickname() + "  ")
				.append(simpleDateFormat.format(message.getCreate_at()) + "\n").append(message.getContent());
		// 更新文本显示区的内容
		jTextArea_show_messages.setText(jTextArea_show_messages.getText() + (sb.toString()));
		// 输出一个空行
		jTextArea_show_messages.setText(jTextArea_show_messages.getText() + "\n");
		jTextArea_show_messages.setCaretPosition(jTextArea_show_messages.getDocument().getLength());
	}

	public void updateOnlineNumber(String content) {
		jLabel_show_onlinenum.setText(content);
	}
}

//用于定时刷新UI上显示实时数据的线程
class InfoRefreshThread extends Thread {
	private GroupChatFrame frame;
	private TCPclient client;

	public InfoRefreshThread(GroupChatFrame frame, TCPclient client) {
		this.frame = frame;
		this.client = client;
	}

	@Override
	public void run() {
		new Timer();
		try {
			frame.jLabel_show_onlinenum.setText(String.valueOf(client.queryOnlineNumber()));
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
