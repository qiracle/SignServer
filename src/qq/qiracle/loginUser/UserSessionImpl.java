package qq.qiracle.loginUser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserSessionImpl implements UserSession{
	  ResultSet rs;
	 
	@Override
	public boolean insertLoginUser(String loginName, String sessionID) throws Exception {
	
		//1. 注册驱动
		   Class.forName("com.mysql.jdbc.Driver") ;
		//2. 创建一个连接对象
		   Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sign","root","qiracle") ; 
		//3. 创建一个sql语句的发送命令对象
		   Statement stmt = conn.createStatement() ;
		//4. 执行SQL,拿到查询的结构集对象
		   PreparedStatement ps1,ps2;
		   rs = stmt.executeQuery("select sessionId from onlineuser where user ="+loginName);
		  
		   String sql2 ="update  onlineuser set sessionId=? where user=?";
		   ps2=conn.prepareStatement(sql2);
		   ps2.setString(1, sessionID);
		   ps2.setString(2, loginName);
		   
		   String sql1 ="insert into onlineuser(user,sessionId) values(?,?)"; 
		   ps1=conn.prepareStatement(sql1);
		   ps1.setString(1, loginName);
		   ps1.setString(2, sessionID);
		   
		   boolean result = rs.first();
		   if(result){//如果当前在线用户表中存在正在登入的用户
			   String origin_sessionId = rs.getString("sessionId");
			   System.out.println(origin_sessionId);
			   
			   ps2.executeUpdate();//更改sessionid
			  
			   
		   }else{//如果当前在线用户表中不存在正在登入的用户
			   
			   ps1.executeUpdate();//在在线用户表中增加正在登入的用户
		   }
		   
		
		   
		  
			  
		  
			   
		   
		
		
		//System.out.println(loginName+"--"+sessionID);
		return true;
	}

}
