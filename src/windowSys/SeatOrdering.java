package windowSys;

import java.awt.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.JTableHeader;

public class SeatOrdering extends JFrame {
	
	public void TableInit() {
		
	}
	
	public SeatOrdering(int CurrentUserName){
		JFrame SOFrame = new JFrame("��λԤԼ");
		Container c = SOFrame.getContentPane();
		c.setLayout(new GridLayout(2, 1));
		
//		Vector<String> columnNameV = new Vector<>();// ��������������
//		columnNameV.add("8");// �������
//		columnNameV.add("9");// �������
//		columnNameV.add("10");
//		columnNameV.add("11");
//		columnNameV.add("12");
//		columnNameV.add("13");
//		columnNameV.add("14");
//		columnNameV.add("15");
//		columnNameV.add("16");
//		columnNameV.add("17");
//		columnNameV.add("18");
//		columnNameV.add("19");
//		Vector<Vector<String>> tableValueV = new Vector<>();// ��������������

//		char[] columnName = new char(); 
		
		for (int row = 0; row < 3; row++) {
			Vector<String> rowV = new Vector<>();// ������������
			for(int column = 0; column < 12; column++) {
				rowV.add("45");// ��ӵ�Ԫ������
			}
			tableValueV.add(rowV);// �������������ӵ��������������
		}
		JTable table = new JTable(tableValueV, columnNameV);
		
		SOFrame.setSize(900, 600);
		SOFrame.setResizable(false);
		SOFrame.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		SeatOrdering s = new SeatOrdering(null);
	}

}
