package cn.edu.svtcc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.svtcc.bus.UserBus;
import cn.edu.svtcc.dao.UserDAOimp;
import cn.edu.svtcc.domain.User;
import cn.edu.svtcc.interf.UserDAO;

/**
 * Servlet implementation class LoginServlet
 * ʵ�ֵ�¼���߼�
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�ӿͻ��˻�ȡ�ı����е����ݣ��û���������
		//����������jquery���ݵ����ݣ����Բ���ת�루��ʱ��֪��Ϊʲô��Ӧ����jquery�ڲ�ʵ���ˣ�
		//���ʹ��post�ύҪ�����ܵ�����ת�� ,���� String username_r = new String(request.getParameter("userName").getBytes("ISO-8859-1"),"utf-8");
		String username_r = request.getParameter("userName");
		String password_r = request.getParameter("password");
		//����һ��user����
		User user = new User();
		//�����û���
		user.setUsername(username_r);
		//��������
		user.setPwd(password_r);
		//��ò�ѯ�����û�
		User users = UserBus.login(user);
		//����DAO��ķ��������е�¼��֤
		if(users != null) {
			//��֤���û����ǹ���Ա
			if(users.getRoleId() == 1) {
				//�����֤�ɹ�������ǰ��¼���û����ŵ�session����
				request.getSession().setAttribute("user", users);
				//����¼״̬����Ϊtrue����session����
				request.getSession().setAttribute("state", true);
				response.getWriter().print("OK");
			}
			if(users.getRoleId() == 2) {
				request.getSession().setAttribute("admin", users);
				response.getWriter().print("admin");
			}
			
		} else {
			//��֤ʧ��
			response.getWriter().print("Error");
		}
	}

}
