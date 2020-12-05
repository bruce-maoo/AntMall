package cn.edu.svtcc.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.svtcc.bus.OrderBus;
import cn.edu.svtcc.domain.Order;

/**
 * Servlet implementation class GetOrderToEditServlet
 * ���������ݵõ���ת�����༭ҳ����߼�
 */
@WebServlet("/GetOrderToEditServlet")
public class GetOrderToEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetOrderToEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�õ��������
		String orderId = request.getParameter("orderId");
		//��ñ�Ŷ�Ӧ�Ķ�������
		Order order = OrderBus.getOrderById(orderId);
		//����Ӧ�����еĶ�����ţ��ռ��ˣ��ռ���ַ���ռ��˵绰����request����
		request.setAttribute("orderId", order.getOrderId());
		request.setAttribute("reciever", order.getReciever());
		request.setAttribute("address", order.getAddress());
		request.setAttribute("phone", order.getPhone());
		//����ת��
		request.getRequestDispatcher("editOrder.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
