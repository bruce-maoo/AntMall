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
 * Servlet implementation class AdminUpdateProviderServlet
 * ����Աϵͳ�У��޸Ĺ�Ӧ����Ϣ���߼�
 */
@WebServlet("/admin/AdminUpdateProviderServlet")
public class AdminUpdateProviderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminUpdateProviderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * ͨ��get����ʽ���ʣ�������Ҫ�����ȡ���ݣ���ת����ҳ��
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ҳ���ȡ��Ӧ��id��ǿת
		Integer providerId = Integer.valueOf(request.getParameter("providerId"));
		//ͨ��id��ȡ��Ӧ��Ӧ�̵���Ϣ
		Provider provider = ProviderBus.getProviderById(providerId);
		//����ȡ���Ĺ�Ӧ�̶������request����
		request.setAttribute("provider", provider);
		//����ת��
		request.getRequestDispatcher("admin_editProvider.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * ͨ��post����ʽ���ʣ����޸Ĺ�Ӧ����Ϣ���߼�
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ҳ���ȡ��Ӧ��id��ǿת
		Integer providerId = Integer.valueOf(request.getParameter("id"));
		//��ȡ��Ӧ������
		String providerName = request.getParameter("providerName");
		//������Ӧ�̶��󣬲���������
		Provider provider = new Provider();
		provider.setProviderId(providerId);
		provider.setProviderName(providerName);
		//��ȡ���й�Ӧ����Ϣ
		List<Provider> providerList = ProviderBus.getAllProvider();
		//�������й�Ӧ��
		for(Provider p : providerList) {
			//�ж��޸ĵĹ�Ӧ�������Ƿ���֮ǰ�����ظ�
			if(p.getProviderName().equals(providerName)) {
				//���ظ��򷵻ظ�ҳ����ϢError2
				response.getWriter().write("Error2");
				return;
			}
		}
		//�޸Ĺ�Ӧ����Ϣ
		boolean r = ProviderBus.updateProvider(provider);
		//�޸ĳɹ����򷵻�ҳ����ϢOK
		if(r) {
			response.getWriter().write("OK");
		} else {
			response.getWriter().write("Error");
		}
	}

}
