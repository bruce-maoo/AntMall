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
 * Servlet implementation class DetailServlet
 * ��Ʒ��ϸҳ����߼�
 */
@WebServlet("/DetailServlet")
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�ӿͻ��˵õ���Ҫ��ʾ��ϸ��Ϣ����Ʒid
		int id = Integer.valueOf(request.getParameter("id"));
		//ʹ����Ʒid��ͨ�������ݿ��л�ȡ����Product����Ʒ������ķ�����ȡ��ʾ�������Ʒ����
		Product product = ProductBus.getProductByID(id);
		//����Product�������request���У�Product�����д�����Ҫչʾ��Ϣ����Ʒ��������Ʒ��Ϣ
		request.setAttribute("Good", product);
		//��ȡת����
		//����ת����deatail.jsp
		request.getRequestDispatcher("detail.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
