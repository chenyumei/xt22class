package dao;

import java.sql.Connection;
import java.util.List;

import entity.Role;

public interface RoleDao {
	
	public List<Role> getRoleList(Connection connection)throws Exception;

}
