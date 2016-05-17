
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

import qq.qiracle.db.ConnectDb;
import qq.qiracle.db.ConnectDbImpl;

import net.sf.json.JSONObject;

public class sign extends HttpServlet {

	private static final long serialVersionUID = -4160634220097202645L;
	private ConnectDb connectdb = new ConnectDbImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		request.setCharacterEncoding("UTF-8");
		String sign = request.getParameter("Sign");
		System.out.println(sign);
		JSONObject signObj = JSONObject.fromObject(sign);		
		String scanResult = signObj.getString("ScanResult");
		String userName = signObj.getString("UserName");
	
		System.out.println(scanResult+"------"+userName);
		
		boolean state = false;
		PrintWriter out = null;
		try {
			
		state = connectdb.SignConnectDb(scanResult,userName);
		out = response.getWriter();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		if(state){
			
			out.print("SignSuccess");
			
		}else{
			out.print("SignFailed");
		}
		
		} catch (Exception e) {
		
			e.printStackTrace();
		}
	
		
	}

}
