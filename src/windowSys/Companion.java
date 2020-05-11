package windowSys;

import login.AccessDb;
import windowSys.Data;
import java.awt.*;
import java.sql.SQLException;
import javax.swing.*;

public class Companion extends JFrame{
	
	public static Container Container_Companion;
	
	String[] columnTime=new String[] {"�û���","Ԥ��ʱ��"};
	String[][] columnRoom=new String[100][2];
	
	public Companion(long CurrentUserName)throws ClassNotFoundException,SQLException{
		
			JFrame SOFrame = new JFrame("ͬ����Ϣ");
			Container c = SOFrame.getContentPane();
			c.setLayout(new GridLayout(3, 1));  //����3��1�����񲼾�
			Data data=new Data();
			AccessDb acc=new AccessDb();
			
			JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
			JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
			
			int n=0;
			
			for(int i=0;i<3;i++) {
				for(int j=0;j<13;j++) {
					for(int k=0;k<45;k++) {
						if(CurrentUserName==data.Yuyue[i][j][k]) {
							for(int z=0;z<45;z++) {
								if(data.Yuyue[i][j][z]!=0 &&CurrentUserName!=data.Yuyue[i][j][z]) {
									columnRoom[n][1]=String.valueOf(j+7)+":00";
									columnRoom[n][0]=acc.searchname(data.Yuyue[i][j][z]);
									n++;
								}
							}
						}
					}
				}
			}
			
			JTable table=new JTable(columnRoom,columnTime);
			for(int i=0;i<2;i++) {
				table.getColumnModel().getColumn(i).setPreferredWidth(40);
			}
			table.setRowHeight(22);
			JScrollPane scrollPane = new JScrollPane(table);
			
			p1.add(new JLabel("ͬ����Ϣ"));
			p2.add(scrollPane);
			
			c.add(p1);
			c.add(p2);
			
			Container_Companion = c;
	}	
}