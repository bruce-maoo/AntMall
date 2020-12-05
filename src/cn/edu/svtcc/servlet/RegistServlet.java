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
 * Servlet implementation class RegistServlet
 * ע���û����߼�
 */
@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistServlet() {
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
		//�ӿͻ��˻�ȡ�ı����е����ݣ��û��������룬�ظ�������͵绰�Լ���֤��
		//����������jquery���ݵ����ݣ����Բ���ת�루��ʱ��֪��Ϊʲô��Ӧ����jquery�ڲ�ʵ���ˣ�
		//���ʹ��post�ύҪ�����ܵ�����ת�� ,���� String username_r = new String(request.getParameter("userName").getBytes("ISO-8859-1"),"utf-8");
		String username_r = request.getParameter("userName");
		String password_r = request.getParameter("password");
		String checking_r = request.getParameter("request");
		String phone = request.getParameter("phone");
		//objectתΪString
		String checking = request.getSession().getAttribute("sb").toString();
		//�ж��������ٴ�����������Ƿ�һ��
		if(!checking.equals(checking_r)) {
			response.getWriter().print("checkingError");
			return;
		}
		//����һ��user���󣬲�������Ӧ������,�û���������͵绰
		User user = new User(username_r,password_r,phone);
		//����ע���û��ķ�������֤�Ƿ�ע��ɹ�
		if(UserBus.addUser(user)) {
			//ע��ɹ�
			response.getWriter().print("OK");
		} else {
			//ע��ʧ��
			response.getWriter().print("Error1");
		}
	}

}
