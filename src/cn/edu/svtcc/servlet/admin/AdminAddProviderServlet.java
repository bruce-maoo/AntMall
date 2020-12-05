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
 * Servlet implementation class AdminAddProviderServlet
 * ����Աϵͳ�У���ӹ�Ӧ�̵��߼�
 */
@WebServlet("/admin/AdminAddProviderServlet")
public class AdminAddProviderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAddProviderServlet() {
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
		//��ù�Ӧ��id
		Integer providerId = 0;
		try {
			//��ҳ���ȡid��ǿת
			providerId = Integer.valueOf(request.getParameter("provider"));
		} catch(Exception e) {
			e.printStackTrace();
			response.getWriter().write("Error2");
			return;
		}
		//��ù�Ӧ������
		String providerName = request.getParameter("providerName");
		//������Ӧ�̶��󲢴�������
		Provider provider = new Provider();
		provider.setProviderId(providerId);
		provider.setProviderName(providerName);
		//������й�Ӧ������
		List<Provider> providerList = ProviderBus.getAllProvider();
		//�������Թ�Ӧ��
		for(Provider p : providerList) {
			//�ж��Ƿ���id�������ظ�
			if(p.getProviderId()==providerId || p.getProviderName().equals(providerName)) {
				//�ظ��򷵻�Error3
				response.getWriter().write("Error3");
				return;
			}
		}
		//��ӹ�Ӧ��
		boolean r = ProviderBus.addProvider(provider);
		//��ӳɹ��򷵻�OK
		if(r) {
			response.getWriter().write("OK");
		} else {
			response.getWriter().write("Error");
		}
	}

}
