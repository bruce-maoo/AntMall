package cn.edu.svtcc.servlet;

import java.io.IOException;
import java.util.HashMap;

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
 * Servlet implementation class deleteGoodServlet
 * ɾ�����ﳵ����Ʒ���߼�
 */
@WebServlet("/DeleteGoodServlet")
public class DeleteGoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteGoodServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�õ��ͻ��˽��յ���Ҫɾ����Ʒ��id
		int id = Integer.valueOf(request.getParameter("id"));
		//ͨ��id���������ݿ��ѯ������Ʒ���ݵķ������õ�һ��Product����
		Product pro = ProductBus.getProductByID(id);
		//��session���еõ����ﳵ��Ϣ��ʵ��Ϊһ��Map<K,V>�б�KΪProduct����VΪ��Ʒ����
		HashMap<Product,Integer> mallList = (HashMap<Product,Integer>)request.getSession().getAttribute("mallList");
		//��Map�����У����ﳵ�У��ҵ�����Ʒ�������Ӽ������Ƴ�
		mallList.remove(pro);
		if(mallList.size()>0) {
			//���Ƴ�����Ʒ��ļ������·���session���У��滻֮ǰ�ļ���
			request.getSession().setAttribute("mallList", mallList);
			response.sendRedirect("cart.jsp");
		} else {
			request.getSession().removeAttribute("mallList");
			response.sendRedirect("cart.jsp");
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
