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
 * Servlet implementation class EditUserServlet
 * �༭�û���Ϣ���߼�
 */
@WebServlet("/EditUserServlet")
public class EditUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditUserServlet() {
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
		//�ֱ����û������绰��session�е���֤���Լ��û��������֤��
		String username = request.getParameter("userName");
		String phone = request.getParameter("phone");
		String checking = request.getSession().getAttribute("sb").toString();
		String checking_r = request.getParameter("request");
		//���û��������֤��session�е���֤����жԱȣ���֤�Ƿ�������ȷ
		if(!checking_r.equals(checking)) {
			//��������򷵻�checkingError��Ϣ
			response.getWriter().write("checkingError");
			return;
		}
		//����User���󲢴�������
		User user=  new User();
		user.setUsername(username);
		user.setPhone(phone);
		//�޸��û���Ϣ
		if(UserBus.editUser(user)){
			//���µ�ǰ��¼���û���Ϣ
			User users = UserBus.getUser(username);
			request.getSession().setAttribute("user", users);
			//������ϢOK
			response.getWriter().write("OK");
		}
	}

}
