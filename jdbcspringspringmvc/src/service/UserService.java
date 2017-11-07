package service;

import java.util.List;

import entity.User;


public interface UserService {
	/**
	 * �����û���Ϣ
	 * @param user
	 * @return
	 */
	public boolean add(User user);
	
	/**
	 * �û���¼
	 * @param userCode
	 * @param userPassword
	 * @return
	 */
	public User login(String userCode,String userPassword);
	
	/**
	 * ����������ѯ�û��б�
	 * @param queryUserName
	 * @param queryUserRole
	 * @return
	 */
	public List<User> getUserList(String queryUserName,int queryUserRole,int currentPageNo, int pageSize);
	/**
	 * ����������ѯ�û����¼��
	 * @param queryUserName
	 * @param queryUserRole
	 * @return
	 */
	public int getUserCount(String queryUserName,int queryUserRole);
	
	/**
	 * ����userCode��ѯ��User
	 * @param userCode
	 * @return
	 */
	public User selectUserCodeExist(String userCode);
	
	/**
	 * ����IDɾ��user
	 * @param delId
	 * @return
	 */
	public boolean deleteUserById(Integer delId);
	
	/**
	 * ����ID����user
	 * @param id
	 * @return
	 */
	public User getUserById(String id);
	
	/**
	 * �޸��û���Ϣ
	 * @param user
	 * @return
	 */
	public boolean modify(User user);
	
	/**
	 * ����userId�޸�����
	 * @param id
	 * @param pwd
	 * @return
	 */
	public boolean updatePwd(int id,String pwd);
}
