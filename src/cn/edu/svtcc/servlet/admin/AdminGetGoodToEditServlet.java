package cn.edu.svtcc.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.svtcc.bus.CategoryBus;
import cn.edu.svtcc.bus.ProductBus;
import cn.edu.svtcc.bus.ProviderBus;
import cn.edu.svtcc.domain.Category;
import cn.edu.svtcc.domain.Product;
import cn.edu.svtcc.domain.Provider;

/**
 * Servlet implementation class AdminGetGoodToEditServlet
 * ����Աϵͳ�У������ݻ�ȡ��ת�����༭ҳ����߼�
 */
@WebServlet("/admin/AdminGetGoodToEditServlet")
public class AdminGetGoodToEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminGetGoodToEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ҳ���ȡ��Ʒid
		int id = 0;
		try {
			id = Integer.valueOf(request.getParameter("id"));
		} catch(Exception e) {
			id = 0;
		}
		//ͨ��idѰ�Ҷ�Ӧ��Ʒ���ж��Ƿ����
		if(ProductBus.getProductByID(id)!=null) {
			//������ڣ�����Ʒ�õ�
			Product pro = ProductBus.getProductByID(id);
			//�õ��������
			List<Category> categoryList = CategoryBus.getAllCategory();
			//�õ����й�Ӧ��
			List<Provider> providerList = ProviderBus.getAllProvider();
			//����Ӧ��Ʒ������������й�Ӧ�̷���request����
			request.setAttribute("Product", pro);
			request.setAttribute("categoryList", categoryList);
			request.setAttribute("providerList", providerList);
			//����ת��
			request.getRequestDispatcher("admin_editGood.jsp").forward(request, response);
		} else {
			//����Ӧ��Ʒ������
			response.getWriter().print("id������");
			//�Զ�ˢ�£���ת���µ�ҳ��
			response.setHeader("refresh", "2;AdminGoodsListServlet?pageIndex=1");
		}
	}

}
