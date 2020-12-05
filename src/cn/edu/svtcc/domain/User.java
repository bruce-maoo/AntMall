package cn.edu.svtcc.domain;

/**
 * �û���
 * @author Shixuanming
 *
 */
public class User {
	//�û���
	private String username;
	//����
	private String pwd;
	//�绰
	private String phone;
	//���id������Ա�����û�
	private Integer roleId;
	public User(){
		
	}
	public User(String username, String pwd) {
		this.username = username;
		this.pwd = pwd;
	}
	public User(String username, String pwd, String phone) {
		this.username = username;
		this.pwd = pwd;
		this.phone = phone;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + pwd + ", phone=" + phone + ", roleId=" + roleId
				+ "]";
	}
	
}
