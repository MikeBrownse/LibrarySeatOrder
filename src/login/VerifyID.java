package login;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;

class PswChange extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	class PswChangeDia extends JDialog{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		PswChangeDia(Boolean reslut){
			JDialog ConfirmErr = new JDialog(PswChange.this, "��ʾ", true);
			JPanel cp = new JPanel(new BorderLayout());
			if(reslut) {
				cp.add(new JLabel("���ĳɹ���", JLabel.CENTER), BorderLayout.CENTER);
				PswChange.this.dispose();
			}else {
				cp.add(new JLabel("�����������벻һ�£�", JLabel.CENTER), BorderLayout.CENTER);
			}
			ConfirmErr.getContentPane().add(cp);
			ConfirmErr.setSize(200, 100);
			ConfirmErr.setResizable(false);
			ConfirmErr.setLocationRelativeTo(null);
			ConfirmErr.setVisible(true);
		}
	}
	
	PswChange(int UID){
		JFrame lost = new JFrame("����������");
		Container c = lost.getContentPane();
		c.setLayout(new GridLayout(4 ,1));
		
		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel p3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel p4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		final JPasswordField newpsw = new JPasswordField("", 10);
		final JPasswordField confirm = new JPasswordField("", 10);
		final JButton confirmButton = new JButton("ȷ���޸�");
		
		newpsw.setEchoChar('*');
		confirm.setEchoChar('*');
		
		confirmButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				if(String.valueOf(newpsw.getPassword()).equals(String.valueOf(confirm.getPassword()))){
					AccessDb db = new AccessDb();
					try {
						db.updata(UID, String.valueOf(newpsw.getPassword()));
						new PswChangeDia(true);
						lost.dispose();
					} catch (ClassNotFoundException e1) {
						// TODO �Զ����ɵ� catch ��
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO �Զ����ɵ� catch ��
						e1.printStackTrace();
					}
				}else {
					new PswChangeDia(false);
					newpsw.setText("");
					confirm.setText("");
				}
			}
		});
		
		p1.add(new JLabel("���ţ�" + UID));
		p2.add(new JLabel("���������룺"));
		p2.add(newpsw);
		p3.add(new JLabel("ȷ�������룺"));
		p3.add(confirm);
		p4.add(confirmButton);
		
		c.add(p1);
		c.add(p2);
		c.add(p3);
		c.add(p4);
		
		lost.setSize(300, 200);
		lost.setResizable(false);
		lost.setLocationRelativeTo(null);
		lost.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		lost.setVisible(true);
		
	}
}

public class VerifyID extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	VerifyID(JFrame win, int UID){
		JDialog rd = new JDialog(win, "�����֤", true);
		
		Container c = rd.getContentPane();
		c.setLayout(new GridLayout(4, 1));
		
		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel p3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel p4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		JTextField EnterName = new JTextField("", 10);
		JTextField EnterCode = new JTextField("�����ݲ�����", 10);
		JButton btn = new JButton("�����֤");
		EnterCode.setEditable(false);
		
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				AccessDb db = new AccessDb();
				try {
					if(db.searchname(UID).equals(EnterName.getText())) {
						rd.dispose();
						new PswChange(UID);
					}else {
						JDialog err = new JDialog(rd, "��Ϣ����", true);
						err.getContentPane().add(new JLabel("�û���Ϣ/��֤�벻ƥ�䣡", JLabel.CENTER));
						EnterName.setText("");
						err.setSize(300, 200);
						err.setResizable(false);
						err.setLocationRelativeTo(null);
						err.setVisible(true);
					}
				} catch (ClassNotFoundException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
			}
		});

		p1.add(new JLabel("���������Ͷ�����֤������֤ ���ţ�" + UID + " �����", JLabel.CENTER));
		p2.add(new JLabel("������������"));
		p2.add(EnterName);
		p3.add(new JLabel("������֤�룺"));
		p3.add(EnterCode);
		p4.add(btn);
		
		c.add(p1);
		c.add(p2);
		c.add(p3);
		c.add(p4);
		
		rd.setSize(400, 200);
		rd.setResizable(false);
		rd.setLocationRelativeTo(null);
		rd.setVisible(true);
	}
}
