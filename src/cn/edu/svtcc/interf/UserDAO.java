package cn.edu.svtcc.interf;

import cn.edu.svtcc.domain.User;
/**
 * 
 * @author Shixuanming
 * �û������ݿ���ʽӿ�
 */
public interface UserDAO {
	public boolean addUser(User user);
	public User login(User user);
	public boolean rePwd(User user);
	public boolean editUser(User user);
	public User getUser(String username);
}
