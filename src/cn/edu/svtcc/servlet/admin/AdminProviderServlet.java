package cn.edu.svtcc.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.svtcc.bus.ProviderBus;
import cn.edu.svtcc.domain.Provider;

/**
 * Servlet implementation class AdminProviderServlet
 * ����Աϵͳ�У���ȡ��Ӧ�����ݲ�ת�����߼�
 */
@WebServlet("/admin/AdminProviderServlet")
public class AdminProviderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminProviderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//������й�Ӧ��
		List<Provider> providerList = ProviderBus.getAllProvider();	
		//�����й�Ӧ����Ϣ����request����
		request.setAttribute("providerList", providerList);
		//����ת��
		request.getRequestDispatcher("admin_providers_list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
