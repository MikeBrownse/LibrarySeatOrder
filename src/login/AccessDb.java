package login;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccessDb{
	
	//���ҷ���   ����ѧ��  �����û�������
	public String searchname(int account)throws ClassNotFoundException,SQLException{
		String name = null;
		String[] n=new String[1000];
		int i=0;
		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		String accdbPath = "D:/library.accdb";
		Connection conn = DriverManager.getConnection("jdbc:ucanaccess://" + accdbPath);
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT*FROM student");
		while(rs.next() && account!=rs.getLong("Account")) {
		}
		if(!rs.isAfterLast()) {
			name=rs.getString("UserName");
		}
		else {
			name=null;
		}
		rs.close();
		stmt.close();
		conn.close();	
		return name;
	}
	//��������
	public String searchpws(long account)throws ClassNotFoundException,SQLException{
		String name = null;
		String[] n=new String[1000];
		int i=0;
		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		String accdbPath = "D:/library.accdb";
		Connection conn = DriverManager.getConnection("jdbc:ucanaccess://" + accdbPath);
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT*FROM student");
		while(rs.next() && account!=rs.getLong("Account")) {
		}
		if(!rs.isAfterLast()) {
			name=rs.getString("pws");
		}
		else {
			name=null;
		}
		rs.close();
		stmt.close();
		conn.close();	
		return name;
	}
	
	//ע�᷽�� �����û�ѧ�� �û����� �û����� ע���û���Ϣ
	public void add(long account, String name, String Pws)throws ClassNotFoundException,SQLException{
		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		String accdbPath = "D:/library.accdb";
		Connection conn = DriverManager.getConnection("jdbc:ucanaccess://" + accdbPath);
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT*FROM student");
		stmt.executeUpdate("insert into student(Account,UserName,pws) values('"+account+"','"+name+"','"+Pws+"')");
		rs.close();
		stmt.close();
		conn.close();	
	}
	
	//�޸�����  �û��Ѿ���½״̬�� �����û�ѧ�� �û�������
	public void updata(long aCcount,String pWs)throws ClassNotFoundException,SQLException{		//�޸���������ѧ�ţ�����
		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		String accdbPath = "D:/library.accdb";		//���ݿ��ַ
		Connection conn = DriverManager.getConnection("jdbc:ucanaccess://" + accdbPath);
		Statement stmt = conn.createStatement();						//�������ݿ�
		ResultSet rs = stmt.executeQuery("SELECT*FROM student");		//rsΪһ����¼��studentΪ��
		stmt.executeUpdate("update student set pws='"+pWs+"' where Account='"+aCcount+"' ");
		rs.close();
		stmt.close();
		conn.close();	
	}
}

//public class access2{
//	public static void main(String[] args) throws ClassNotFoundException, SQLException{  //�������ݿ�ʱ��ǰ��Ҫ��  throws ClassNotFoundException, SQLException
//		AccessDb acc=new AccessDb();
		//acc.updata(20186698,"20186698");
		//acc.add(20183054, "wujinliang", "20183054");
//		System.out.println(acc.search(20183034));
//	}
//}