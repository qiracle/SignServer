
//======================================================================
 //
 //        Copyright (C) 2016   
 //        All rights reserved
 //
 //        filename :modifyPwd
 //        
 //
 //        created by Qiangqiang Jinag in  2016.04
 //        https://github.com/qiracle
 //		   qiracle@foxmail.com
 //
 //======================================================================
package qq.qiracle.com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qq.qiracle.db.ConnectDb;
import qq.qiracle.db.ConnectDbImpl;

import net.sf.json.JSONObject;

public class modifyPwd extends HttpServlet {


	/**
	 * 
	 */
	
	private static final long serialVersionUID = -7257315684131452248L;
	private ConnectDb connectdb = new ConnectDbImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

		
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		/**
		 * ModifyPwd{"LoginName":"loginName","LoginOldPassword":"OldPassword","LoginNewPassword":"NewPassword","Type":"type"}
		 * 
		 * 
		 * 
		 */

		request.setCharacterEncoding("UTF-8");
		String modifyPwd = request.getParameter("ModifyPwd");
		System.out.println(modifyPwd);
		JSONObject pwdObj = JSONObject.fromObject(modifyPwd);		
		String loginName = pwdObj.getString("LoginName");
		String OldPassword = pwdObj.getString("LoginOldPassword");
		String NewPassword = pwdObj.getString("LoginNewPassword");
		int type = pwdObj.getInt("Type");
		
		
		boolean state = false;
		PrintWriter out = null;
		try {
			
		state = connectdb.ModPwdConnectDb(loginName, OldPassword,NewPassword,type);
		out = response.getWriter();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		if(state){
			
			out.print("ModSuccess");
			
		}else{
			out.print("ModFailed");
		}
		
		} catch (Exception e) {
		
			e.printStackTrace();
		}
	
		
		
		
		
		
		
		
	}

}
