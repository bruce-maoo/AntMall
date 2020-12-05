package cn.edu.svtcc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.svtcc.bus.UserBus;
import cn.edu.svtcc.domain.User;

/**
 * Servlet implementation class RePasswordServlet
 * �޸�������߼�
 */
@WebServlet("/RePasswordServlet")
public class RePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RePasswordServlet() {
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
		//��ȡ����ҳ�����Ϣ���û����������룬�����룬��֤��
		String username = request.getParameter("username");
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		String checking = request.getSession().getAttribute("sb").toString();
		String checking_r = request.getParameter("request");
		//�ж��������ٴ�����������Ƿ�һ��
		if(!checking.equals(checking_r)) {
			response.getWriter().print("checkingError");
			return;
		}
		//���û�������֤��Ѱ���Ƿ��и��û�������֤������
		if(UserBus.login(new User(username,oldPassword)) != null) {
			//�����û����󣬲���Ϊ�û�����������
			User user = new User(username,newPassword);
			//ͨ���û����ҵ���Ӧ�û����޸�����
			if(UserBus.rePwd(user)){
				//��������OK
				response.getWriter().write("OK");
			} else {
				response.getWriter().write("Error");
			}
		} else {
			response.getWriter().write("PwdError");
		}
	}

}
