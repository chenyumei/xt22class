package dao;

import java.sql.Connection;
import java.util.List;

import entity.User;


public interface UserDao {
	/**
	 * �����û���Ϣ
	 * @param connection
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int add(Connection connection,User user)throws Exception;

	/**
	 * ͨ��userCode��ȡUser
	 * @param connection
	 * @param userCode
	 * @return
	 * @throws Exception
	 */
	public User getLoginUser(Connection connection,String userCode)throws Exception;

	/**
	 * ͨ��������ѯ-userList
	 * @param connection
	 * @param userName
	 * @param userRole
	 * @return
	 * @throws Exception
	 */
	public List<User> getUserList(Connection connection,String userName,int userRole,int currentPageNo, int pageSize)throws Exception;
	/**
	 * ͨ��������ѯ-�û����¼��
	 * @param connection
	 * @param userName
	 * @param userRole
	 * @return
	 * @throws Exception
	 */
	public int getUserCount(Connection connection,String userName,int userRole)throws Exception;
	
	/**
	 * ͨ��userIdɾ��user
	 * @param delId
	 * @return
	 * @throws Exception
	 */
	public int deleteUserById(Connection connection,Integer delId)throws Exception; 
	
	
	/**
	 * ͨ��userId��ȡuser
	 * @param connection
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public User getUserById(Connection connection,String id)throws Exception; 
	
	/**
	 * �޸��û���Ϣ
	 * @param connection
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int modify(Connection connection,User user)throws Exception;
	
	
	/**
	 * �޸ĵ�ǰ�û�����
	 * @param connection
	 * @param id
	 * @param pwd
	 * @return
	 * @throws Exception
	 */
	public int updatePwd(Connection connection,int id,String pwd)throws Exception;
	
	
}
