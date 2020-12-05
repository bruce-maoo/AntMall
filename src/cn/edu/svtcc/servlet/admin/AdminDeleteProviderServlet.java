package cn.edu.svtcc.servlet.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.svtcc.bus.ProviderBus;

/**
 * Servlet implementation class AdminDeleteProviderServlet
 * ����Աϵͳ�У�ɾ����Ӧ�̵��߼�
 */
@WebServlet("/admin/AdminDeleteProviderServlet")
public class AdminDeleteProviderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDeleteProviderServlet() {
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
		//��ҳ���ȡ��Ӧ��id��ǿת
		Integer providerId = Integer.valueOf(request.getParameter("providerId"));
		//ͨ����Ӧ��idɾ���ù�Ӧ��
		boolean r = ProviderBus.deleteProvider(providerId);
		//ɾ���ɹ�����Ӧ��ҳ��OK
		if(r) {
			response.getWriter().write("OK");
		} else {
			response.getWriter().write("Error");
		}
	}

}
