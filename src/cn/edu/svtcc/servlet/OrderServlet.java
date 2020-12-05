package cn.edu.svtcc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.svtcc.bus.OrderBus;
import cn.edu.svtcc.domain.Order;
import cn.edu.svtcc.domain.User;

/**
 * Servlet implementation class OrderServlet
 * Ϊ����ҳ���ṩ��Ϣ���߼�������ת����Ϣ
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//����¼״̬
		if(request.getSession().getAttribute("user")==null) {
			response.getWriter().write("���ȵ�¼��");
			return;
		}
		//��session���л�õ�ǰ��¼���û�����
		User user = (User)request.getSession().getAttribute("user");
		//��õ�ǰ��¼���û���
		String username = user.getUsername();
		//�õ����û������ж�����Ϣ
		List<Order> orderList = OrderBus.getOrdersByUser(username);
		//����request����
		request.setAttribute("orderList", orderList);
		//����ת��
		request.getRequestDispatcher("order.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
