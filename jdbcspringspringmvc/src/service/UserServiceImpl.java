package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.BaseDao;
import dao.UserDao;
import entity.User;

/**
 * service�㲶���쳣������������
 * �����������ò�ͬdao�Ķ������������ʹ��ͬһ��connection��connection��Ϊ�������ݣ�
 * �������֮����Ҫ��service�����connection�Ĺرգ���dao��رգ�PreparedStatement��ResultSet����
 * @author Administrator
 *
 */
@Service
public class UserServiceImpl implements UserService{
    @Resource
	private UserDao userDao;
	/*public UserServiceImpl(){
		userDao = new UserDaoImpl();
	}*/
	public boolean add(User user) {
		// TODO Auto-generated method stub
		
		boolean flag = false;
		Connection connection = null;
		try {
			connection = BaseDao.getConnection();
			connection.setAutoCommit(false);//����JDBC�������
			int updateRows = userDao.add(connection,user);
			connection.commit();
			if(updateRows > 0){
				flag = true;
				System.out.println("add success!");
			}else{
				System.out.println("add failed!");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				System.out.println("rollback==================");
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally{
			//��service�����connection���ӵĹر�
			BaseDao.closeResource(connection, null, null);
		}
		return flag;
	}
	public User login(String userCode, String userPassword) {
		// TODO Auto-generated method stub
		Connection connection = null;
		User user = null;
		try {
			connection = BaseDao.getConnection();
			user = userDao.getLoginUser(connection, userCode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			BaseDao.closeResource(connection, null, null);
		}
		
		//ƥ������
		if(null != user){
			if(!user.getUserPassword().equals(userPassword))
				user = null;
		}
		
		return user;
	}
	public List<User> getUserList(String queryUserName,int queryUserRole,int currentPageNo, int pageSize) {
		// TODO Auto-generated method stub
		Connection connection = null;
		List<User> userList = null;
		System.out.println("queryUserName ---- > " + queryUserName);
		System.out.println("queryUserRole ---- > " + queryUserRole);
		System.out.println("currentPageNo ---- > " + currentPageNo);
		System.out.println("pageSize ---- > " + pageSize);
		try {
			connection = BaseDao.getConnection();
			userList = userDao.getUserList(connection, queryUserName,queryUserRole,currentPageNo,pageSize);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			BaseDao.closeResource(connection, null, null);
		}
		return userList;
	}
	public User selectUserCodeExist(String userCode) {
		// TODO Auto-generated method stub
		Connection connection = null;
		User user = null;
		try {
			connection = BaseDao.getConnection();
			user = userDao.getLoginUser(connection, userCode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			BaseDao.closeResource(connection, null, null);
		}
		return user;
	}
	public boolean deleteUserById(Integer delId) {
		// TODO Auto-generated method stub
		Connection connection = null;
		boolean flag = false;
		try {
			connection = BaseDao.getConnection();
			if(userDao.deleteUserById(connection,delId) > 0)
				flag = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			BaseDao.closeResource(connection, null, null);
		}
		return flag;
	}
	public User getUserById(String id) {
		// TODO Auto-generated method stub
		User user = null;
		Connection connection = null;
		try{
			connection = BaseDao.getConnection();
			user = userDao.getUserById(connection,id);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			user = null;
		}finally{
			BaseDao.closeResource(connection, null, null);
		}
		return user;
	}
	public boolean modify(User user) {
		// TODO Auto-generated method stub
		Connection connection = null;
		boolean flag = false;
		try {
			connection = BaseDao.getConnection();
			if(userDao.modify(connection,user) > 0)
				flag = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			BaseDao.closeResource(connection, null, null);
		}
		return flag;
	}
	public boolean updatePwd(int id, String pwd) {
		// TODO Auto-generated method stub
		boolean flag = false;
		Connection connection = null;
		try{
			connection = BaseDao.getConnection();
			if(userDao.updatePwd(connection,id,pwd) > 0)
				flag = true;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			BaseDao.closeResource(connection, null, null);
		}
		return flag;
	}
	public int getUserCount(String queryUserName, int queryUserRole) {
		// TODO Auto-generated method stub
		Connection connection = null;
		int count = 0;
		System.out.println("queryUserName ---- > " + queryUserName);
		System.out.println("queryUserRole ---- > " + queryUserRole);
		try {
			connection = BaseDao.getConnection();
			count = userDao.getUserCount(connection, queryUserName,queryUserRole);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			BaseDao.closeResource(connection, null, null);
		}
		return count;
	}
	
}