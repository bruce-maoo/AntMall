package cn.edu.svtcc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.svtcc.bus.ProductBus;
import cn.edu.svtcc.dao.ProductDAOimp;
import cn.edu.svtcc.domain.Product;
import cn.edu.svtcc.interf.ProductDAO;

/**
 * Servlet implementation class IndexGoodsServlet
 * ��Ʒչʾ��ҳ���߼�
 */
@WebServlet("/IndexGoodsServlet")
public class IndexGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexGoodsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�ӿͻ��˽������ݣ���ʾ����Ʒ�������ҳ������Ϊ1
		int category = 1;
		//�����ݿ����ҳ����и�������Ʒ������List�����У��ü���ÿ��Ԫ�ض�ΪProduct����
		//ʵ�����ݿ���ÿһ�ж�����װ����һ��Product����
		List<Product> goodsList = ProductBus.getAllProduct(category);
		//��ȡ�ü��ϵ�ǰ5����Ʒ
		List<Product> showGoods = goodsList.subList(0, 5);
		//��ȡ�ü��ϳ���ǰ�����֮�����Ʒ
		goodsList = goodsList.subList(6,goodsList.size());
		//��������ȡ��ļ��Ϸ���request�������
		//������ȡʵ����Ϊ�˷ֱ�չʾ������Ʒ����ɱ��Ʒ
		request.setAttribute("Goods", goodsList);
		request.setAttribute("Show", showGoods);
		//��ȡת����
		//����ת��,index.jsp
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
