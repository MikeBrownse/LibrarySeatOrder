package windowSys;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.event.*;

import login.Login;
import login.VerifyID;

public class AfterLogin extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
			}
		});
		
		logout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				af.dispose();
				try {
					Login.CurrentUser = null;
					Login.f.setVisible(true);

				} catch (Exception e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
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
		try {
			new Inquire(UID);
			new Companion(UID);
		} catch (ClassNotFoundException e1) {
			// TODO �Զ����ɵ� catch ��
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO �Զ����ɵ� catch ��
			e1.printStackTrace();
		}
		tab.addTab("����Ԥ��", null, SeatOrdering.Container_SeatOrdering);
		tab.addTab("������Ϣ", null, Inquire.Container_Inquire);
		tab.addTab("�����޸�", null, new JLabel("���ڵ�����������ɲ���", JLabel.CENTER));
		tab.addTab("��ѯͬ��", null, Companion.Container_Companion);
		
		af.setSize(600, 600);
		af.setLocationRelativeTo(null);
		af.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		af.setVisible(true);
	}
}
