package qq.qiracle.loginUser;

public interface UserSession {
	
	public boolean insertLoginUser(String loginName,String sessionId) throws Exception;

}
