package login;

import javax.swing.*;

public class MainProceed extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static ImageIcon icon = new ImageIcon("data/library.png");
	
	public static void main(String[] args) throws Exception {
		// TODO �Զ����ɵķ������
		new Thread(new TestRxTx()).start();
		new Login();
	}
}
