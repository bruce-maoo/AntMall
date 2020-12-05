package cn.edu.svtcc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.svtcc.bus.ProductBus;
import cn.edu.svtcc.domain.Product;

/**
 * Servlet implementation class GoodsListServlet
 * ������Ʒ�б�����ݻ�ȡ�߼�
 */
@WebServlet("/GoodsListServlet")
public class GoodsListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoodsListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Ĭ�ϵ�ǰҳΪ��һҳ
		int pageIndex = 1;
		//�ж�session���Ƿ�����˷���ҳ��
		if(request.getSession().getAttribute("GoodsPageIndex") != null) {
			try {
				//���ҳ�棬ǿת
				pageIndex = Integer.valueOf(request.getParameter("pageIndex").toString());
			} catch (Exception e) {
				pageIndex = 1;
			}
			
		}
		//��ȫ������ÿҳ��ʾ����Ʒ����
		int pageSize = Integer.valueOf(this.getServletContext().getInitParameter("pageSize"))-15;
		//�ӿͻ��˽�����Ҫչʾ����Ʒ���
		/**���������԰칫���ͻ��˴��������Ϊ2
		    *����Ҿ߼Ҿӣ�����Ϊ3
		 */
		int category = Integer.valueOf(request.getParameter("category"));
		//ͨ�����յ���category�����ݿ���Ѱ�����Ϊ��������Ʒ��Ϣ
		//�����õ�����Ʒ��Ϣ����һ����Ʒ������
		//�ü�����ÿ��Ԫ�ض���һ��Product����һ������Ͷ�Ӧ���ݿ��е�һ����Ʒ����
		List<Product> goodsList = ProductBus.getAllProductByPage(category,pageSize,pageIndex);
		//��ø���������Ʒ
		List<Product> allGoods = ProductBus.getAllProduct(category);
		//�����ҳ��
		int pageNum = allGoods.size()/pageSize;
		//���õ�����Ҫչʾ����Ʒ�б�ʵ��Ϊһ�����ϣ�����request����
		request.setAttribute("Goods", goodsList);
		request.setAttribute("category", category);
		request.setAttribute("pageNum", pageNum);
		request.getSession().setAttribute("GoodsPageIndex", pageIndex);
		//��ȡת����
		//����ת����goods_list.jsp
		request.getRequestDispatcher("goods_list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
