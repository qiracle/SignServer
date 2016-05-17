
/*Copyright (C) 2016.4
 * 
 *author:Qiangqiang Jiang
 * 
 */
package qq.qiracle.com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import qq.qiracle.db.ConnectDb;
import qq.qiracle.db.ConnectDbImpl;

import net.sf.json.JSONObject;

public class login extends HttpServlet {


	/**
	 * 
	 */
	private static final long serialVersionUID = -7822061198602237970L;
	private ConnectDb connectdb = new ConnectDbImpl();


	/**
	 * 
	 */



	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);


	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		/**
		 * userLogin{"LoginName":"loginName","LoginPassword":"loginPassword","Type":"type"}
		 * 
		 */
	
		
		
		
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(); 
		System.out.println(session.getId());
		String userLogin = request.getParameter("UserLogin");
		System.out.println(userLogin);
		JSONObject loginObj = JSONObject.fromObject(userLogin);		
		String loginName = loginObj.getString("LoginName");
		String loginPassword = loginObj.getString("LoginPassword");
		int type = loginObj.getInt("Type");
		//System.out.println(loginName+"------"+loginPassword);
		boolean state = false;
		PrintWriter out = null;
		try {
			
		state = connectdb.LoginConnectDb(loginName, loginPassword,type);
		out = response.getWriter();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		if(state){
			
			out.print("success");
			
		}else{
			out.print("failed");
		}
		
		} catch (Exception e) {
		
			e.printStackTrace();
		}
	
		
		
		

		
	}

}
