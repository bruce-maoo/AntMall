package cn.edu.svtcc.servlet.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.svtcc.bus.ProductBus;
import cn.edu.svtcc.domain.Product;

/**
 * Servlet implementation class AdminGoodsListServlet
 * ����Աϵͳ�У���ȡ���ݲ�ת������Ʒչʾҳ����߼�
 */
@WebServlet("/admin/AdminGoodsListServlet")
public class AdminGoodsListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminGoodsListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Ĭ��ҳ��Ϊ1
		int pageIndex = 1;
		//�ж�session�����Ƿ����ҳ��
		if(request.getSession().getAttribute("pageIndex") != null) {
			try {
				//��session�е�ҳ���ȡ
				pageIndex = Integer.valueOf(request.getParameter("pageIndex").toString());
			} catch (Exception e) {
				//�������쳣���㸳ֵΪĬ��ֵ
				pageIndex = 1;
			}
			
		}
		//����ÿҳ��ʾ����Ʒ����
		int pageSize = Integer.valueOf(this.getServletContext().getInitParameter("pageSize"));
		//��ȡ������Ʒ
		List<Product> allGoods = ProductBus.getAllGoods();
		//��ȡ��ҳ��
		int pageNum = allGoods.size()/pageSize;
		//��ȡ��ҳ�������
		List<Product> goods = ProductBus.getProductByPage(pageSize, pageIndex);
		//����ǰҳ�����session����
		request.getSession().setAttribute("pageIndex", pageIndex);
		//����ҳ�����Ʒ����ҳ������request����
		request.setAttribute("Goods", goods);
		request.setAttribute("pageNum", pageNum);
		//����ת��
		request.getRequestDispatcher("admin_goods_list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
