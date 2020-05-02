package windowSys;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import windowSys.LoginError.LoginErrorCode;

class LoginError extends JDialog{		//�û�������ȷʱ�����ĶԻ�����, finished class
	
	class LoginErrorCode{				//�����û����Ĵ�����, finished inner class
		final static int NoCard = 0;
		final static int Unregistered = 1;
		final static int Registered = 2;
		final static int InvaildUserName = 3;
		final static int WrongPsw = 4;
	}
	
	public LoginError(Login LoginWin, int ErrorCode) {
		super(LoginWin, "��¼����", true);
		JPanel jp = new JPanel(new BorderLayout());
		switch(ErrorCode) {
			case LoginErrorCode.NoCard : 			jp.add(new JLabel("��ˢ����", JLabel.CENTER), BorderLayout.CENTER);		break;
			case LoginErrorCode.Unregistered : 		jp.add(new JLabel("�û�δע�ᣡ", JLabel.CENTER), BorderLayout.CENTER);		break;
			case LoginErrorCode.Registered : 		jp.add(new JLabel("���û����ѱ�ע�ᣡ", JLabel.CENTER), BorderLayout.CENTER);	break;
			case LoginErrorCode.InvaildUserName : 	jp.add(new JLabel("��Ч�û�����", JLabel.CENTER), BorderLayout.CENTER);		break;
			case LoginErrorCode.WrongPsw : 			jp.add(new JLabel("�������", JLabel.CENTER), BorderLayout.CENTER);		break;
			default : 								jp.add(new JLabel("δ֪����", JLabel.CENTER), BorderLayout.CENTER);		break;
		}

		getContentPane().add(jp);
		setSize(300, 200);
		setLocationRelativeTo(null);
	}
}



public class Login extends JFrame {

	public boolean StringIsDig(String s) {		//�ж��ַ����Ƿ��Ϊ����, finished method
		boolean result = true;
		
		for(int i = 0; i < s.length(); i++) {
			if(!Character.isDigit(s.charAt(i))) {
				result = false;
				break;
			}
		}
		
		return result;
	}
	
	boolean EqualToDB(String acc) {			//���ݿ�Ա��û���, To be continued method
		boolean result = false;
		
		return result;
	}
	
	public Login() {						//��¼ģ��ľ���ʵ��, To be continued construct method
		Container container = getContentPane();
		container.setLayout(new GridLayout(4, 1));		//����4��1�����񲼾�
		
		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JPanel p3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JPanel p4 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		final JTextField UserName = new JTextField("��ˢ��", 10);
		final JTextField Password = new JTextField("����������", 10);
		final JButton LoginBtn = new JButton("��¼");
		final JButton RegBtn = new JButton("ע��");
		final JButton ResetBtn = new JButton("����");
		UserName.setEditable(false);		//��ֹ�û��ֶ������û���
		
		LoginBtn.addActionListener(new ActionListener() {		//��¼��ť������
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				if(UserName.getText().equals("��ˢ��")) {
					new LoginError(Login.this, LoginErrorCode.NoCard).setVisible(true);
				}else if(StringIsDig(UserName.getText())){
					if(EqualToDB(UserName.getText())) {			//�Ա����ݿ�
						
						/**************************************����ѡ������, To be continued*********************************/
						
						System.out.println("login");
						System.out.println(UserName.getText());
						System.out.println(Password.getText());
						
						/**************************************���ý���*************************************/
						
					}else {
						new LoginError(Login.this, LoginErrorCode.Unregistered).setVisible(true);
					}
					
				}else {
					new LoginError(Login.this, LoginErrorCode.InvaildUserName).setVisible(true);
				}
			}
		});
		
		RegBtn.addActionListener(new ActionListener() {			//ע�ᰴť������
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������				
				if(UserName.getText().equals("��ˢ��")) {
					new LoginError(Login.this, LoginErrorCode.NoCard).setVisible(true);
				}else if(StringIsDig(UserName.getText())){
					if(!EqualToDB(UserName.getText())) {			//�Ա����ݿ�
						
						/**************************************д�����ݿ�, To be continued*********************************/
						
						System.out.println("registration");
						System.out.println(UserName.getText());
						System.out.println(Password.getText());
						
						/**************************************���ý���*************************************/
						
					}else {
						new LoginError(Login.this, LoginErrorCode.Registered).setVisible(true);
					}
					
				}else {
					new LoginError(Login.this, LoginErrorCode.InvaildUserName).setVisible(true);
				}
				
			}
		});
		
		ResetBtn.addActionListener(new ActionListener() {		//���ð�ť������, finished
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				UserName.setText("��ˢ��");
				Password.setText("");
				Password.requestFocus();
			}
		});
		
		p1.add(new JLabel("��ӭʹ��ͼ�����λԤԼϵͳ����ˢ��"));
		p2.add(new JLabel("����"));
		p2.add(UserName);
		p3.add(new JLabel("����"));
		p3.add(Password);
		p4.add(LoginBtn);
		p4.add(RegBtn);
		p4.add(ResetBtn);
		
		container.add(p1);
		container.add(p2);
		container.add(p3);
		container.add(p4);
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("ͼ�����λԤԼϵͳ");
		setSize(600, 300);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
}
