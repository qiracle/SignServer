//======================================================================
 //
 //        Copyright (C) 2016   
 //        All rights reserved
 //
 //        filename :ConnectDb
 //        
 //
 //        created by Qiangqiang Jinag in  2016.04
 //        https://github.com/qiracle
 //		   qiracle@foxmail.com
 //
 //======================================================================
package qq.qiracle.db;

public interface ConnectDb {
	
	public boolean LoginConnectDb(String LoginName,String LoginPassword,int Type) throws Exception;
	public boolean ModPwdConnectDb(String LoginName,String OldPassword,String NewPassword,int Type) throws Exception;
	public boolean SignConnectDb(String scanResult,String username) throws Exception;
	public boolean QrcodeReceive(String Qrcode) throws Exception;

}
