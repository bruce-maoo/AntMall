package cn.edu.svtcc.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.svtcc.bus.OrderBus;
import cn.edu.svtcc.bus.OrderDetailBus;

/**
 * Servlet implementation class DeleteOrderServlet
 * ɾ��������Ϣ���߼�
 */
@WebServlet("/DeleteOrderServlet")
public class DeleteOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�õ��������
		String orderId = request.getParameter("orderId");
		//�ֱ�ɾ���������ݺͶ�����������
		boolean r1 = OrderBus.deleteOrder(orderId);
		boolean r2 = OrderDetailBus.deleteOrderDetails(orderId);
		//������ִ�гɹ��򷵻�OK
		if(r1 && r2) {
			response.getWriter().write("OK");
		} else {
			response.getWriter().write("Error");
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
