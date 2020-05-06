package windowSys;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

import login.Login;
import login.VerifyID;

public class AfterLogin extends JFrame {
	public AfterLogin(int UID) {
		JFrame af = new JFrame("���ţ�" + UID + "����ѡ��");
		
		final JMenuBar menubar = new JMenuBar();
		final JMenu exit = new JMenu("�˳� (E)");
		final JMenu help = new JMenu("���� (H)");
		final JMenuItem logout = new JMenuItem("�˳���¼ (O)");
		final JMenuItem exitsys = new JMenuItem("�˳�ϵͳ (X)");
		final JMenuItem nothing = new JMenuItem("û�а��� (A)");
		final JTabbedPane tab = new JTabbedPane(JTabbedPane.LEFT);
		
		exit.setMnemonic('E');
		logout.setMnemonic('O');
		exitsys.setMnemonic('X');
		help.setMnemonic('H');
		nothing.setMnemonic('A');
		
		af.setJMenuBar(menubar);
		menubar.add(exit);
		menubar.add(help);
		exit.add(logout);
		exit.add(exitsys);
		help.add(nothing);
		tab.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tab.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO �Զ����ɵķ������
				int selectedIndex = tab.getSelectedIndex();
				String title = tab.getTitleAt(selectedIndex);
				if(title.equals("�����޸�")){
					new VerifyID(af, UID);
				}
				System.out.println(title);
			}
		});
		
		logout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				af.dispose();
				new Login();
				Login.CurrentUser = null;
				
			}
		});
		
		exitsys.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				System.exit(0);
			}
		});
		
		af.addWindowListener(new WindowAdapter() {
			
			public void windowClosing(java.awt.event.WindowEvent e) {
				super.windowClosing(e);
				System.exit(0);
			}
			
		});
		
		af.getContentPane().add(tab, BorderLayout.CENTER);
		new SeatOrdering(af, UID);
		tab.addTab("����Ԥ��", null, SeatOrdering.con);
		tab.addTab("������Ϣ", null, null);
		tab.addTab("�����޸�", null, new JLabel("���ڵ�����������ɲ���", JLabel.CENTER));
		tab.addTab("��ѯͬ��", null, null);
		
		af.setSize(600, 600);
		af.setLocationRelativeTo(null);
		af.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		af.setVisible(true);
	}
	
	public static void main(String args[]) {
		new AfterLogin(123);
	}
}
