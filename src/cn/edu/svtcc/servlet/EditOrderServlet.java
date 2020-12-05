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
 * Servlet implementation class EditOrderServlet
 * �༭����ҳ����߼�
 */
@WebServlet("/EditOrderServlet")
public class EditOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�ֱ��ö�����ţ��ռ��ˣ��ռ���ַ���ռ��˵绰
		String orderId = request.getParameter("orderId");
		String reciever = request.getParameter("reciever");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		//�����������󣬲���������
		Order order = new Order();
		order.setOrderId(orderId);
		order.setReciever(reciever);
		order.setAddress(address);
		order.setPhone(phone);
		//�޸Ķ�����Ϣ
		boolean r = OrderBus.updateOrder(order);
		//�޸ĳɹ��򷵻�OK
		if(r) {
			response.getWriter().write("OK");
		} else {
			response.getWriter().write("Error");
		}
	}

}
