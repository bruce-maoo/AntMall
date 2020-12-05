package cn.edu.svtcc.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * Servlet implementation class MallServlet
   * ʵ�ֹ��ﳵ���߼�
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�����������Ӧ��ʽ
		response.setContentType("text/html;charset=utf8");
		//�ж��Ƿ��ܹ����ܵ�����
		if(request.getParameter("id")==null ||request.getParameter("count")==null) {
			response.getWriter().print("�Ƿ����ʣ�");
		}
		//���տͻ��˴�����������,���ڽ��յ�����String����,��Ҫǿ��ת��
		//�õ���ϸҳ���е���Ʒid
		int id = Integer.valueOf(request.getParameter("id"));
		//�õ���Ʒ����
		int count = Integer.valueOf(request.getParameter("count"));
		//�ҵ���id��Ӧ��Product����
		Product pro = ProductBus.getProductByID(id);
		if(pro.getStuck()<count) {
			response.getWriter().print("Error2");
			return;
		}
		//����¼״̬
		if(request.getSession().getAttribute("state")==null) {
			response.getWriter().print("noLogin");
			return;
		}
		//�����һ�η��ʹ��ﳵ
		if(request.getSession().getAttribute("mallList")==null) {
			//����һ��˫�м���Map<K,V>,KΪProduct���Ͷ���VΪ����Ʒ������
			Map<Product,Integer> mallList = new HashMap<Product,Integer>();
			//������Ʒ�����ݷ�������
			mallList.put(pro,count);
			//����Session,�����Ϸ���session����
			request.getSession().setAttribute("mallList", mallList);
			response.getWriter().print("OK");
		} else {
			//֮����ʹ��ﳵ
			//��Session���еõ�֮ǰ�����Map<Product,count>����
			Map<Product,Integer> mallList = (Map<Product,Integer>) request.getSession().getAttribute("mallList");
			//�����Ʒ�����ڹ��ﳵ��
			if(!mallList.containsKey(pro)) {
				//ֱ�ӽ���Ʒ���뼯����
				mallList.put(pro, count);
			} else {
				//��Ʒ���ڹ��ﳵ��
				int oldCount = mallList.get(pro);
				//��֮ǰ����Ʒ������ȡ�������µ���Ʒ����
				int newCount = oldCount + count;
				//���뼯���У�������Ʒ���ڣ�KֵΨһ�����Խ��滻֮ǰ���ڵĸ���Ʒ
				mallList.put(pro, newCount);
			}
			//�����Ϸ���Session����
			request.getSession().setAttribute("mallList", mallList);
			response.getWriter().print("OK");
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
