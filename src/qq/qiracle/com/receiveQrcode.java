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

public class receiveQrcode extends HttpServlet {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ConnectDb connectdb = new ConnectDbImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String qrcode = request.getParameter("Qrcode");
		System.out.println(qrcode);
		JSONObject signObj = JSONObject.fromObject(qrcode);		
		String StringQrcode = signObj.getString("StringQRcode");
	
	
	
		boolean state = false;
		PrintWriter out = null;
		try {
			
		state = connectdb.QrcodeReceive(StringQrcode);
		out = response.getWriter();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		if(state){
			
			out.print("isOk");
			
		}else{
			out.print("notOK");
		}
		
		} catch (Exception e) {
		
			e.printStackTrace();
		}

		
	}

}
