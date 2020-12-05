package cn.edu.svtcc.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.svtcc.bus.OrderBus;
import cn.edu.svtcc.bus.OrderDetailBus;
import cn.edu.svtcc.bus.ProductBus;
import cn.edu.svtcc.bus.ReturnBus;
import cn.edu.svtcc.domain.Order;
import cn.edu.svtcc.domain.OrderDetail;
import cn.edu.svtcc.domain.Product;
import cn.edu.svtcc.domain.User;

/**
 * Servlet implementation class clearCartServlet 
 * ��չ��ﳵ���߼�
 */
@WebServlet("/ClearCartServlet")
public class ClearCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClearCartServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().write("Error");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�õ��ͻ��˷���������
		/**
		 * �����ˣ���ַ���绰����Ʒ�ܼ۸�
		 */
		String reciever = request.getParameter("reciever");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String totalPrice = request.getParameter("totalPrice");
		//��session����ȡ��֮ǰ�����Map���ϣ������ǵõ�����object���ͣ�����ǿתΪmap
		Map<Product,Integer> goods = (Map)request.getSession().getAttribute("mallList");
		//������
		int count = 0;
		//������������
		Order order = new Order();
		//��session���л���û�����
		User user = (User)request.getSession().getAttribute("user");
		/**
		 *  ���ö����е���Ϣ
		 */
		//������ţ������UUID
		order.setOrderId(UUID.randomUUID().toString());
		//�����û�����session��ȡ����ǰ��¼���û������õ�objectǿתΪString
		order.setUsername(user.getUsername());
		//�����ܼ۸�
		order.setTotalPrice(Double.parseDouble(totalPrice));
		//������Ʒ����
		order.setProductNum(goods.size());
		//�����ռ���
		order.setReciever(reciever);
		//���õ�ַ
		order.setAddress(address);
		//���õ绰
		order.setPhone(phone);
		//�������������б�����Ϊ��������Ķ���
		//���б�����ö����е����ж�������
		List<OrderDetail> details = new ArrayList<>();
		//ʹ��foreach�������ﳵ�еĲ�Ʒ����
		//ʹ��keySet()������Map�����е�Kת��Ϊһ��Set���ϣ��ü����е�ÿһ��Ԫ�ض���һ��Product����
		for(Product p:goods.keySet()) {
			//�������������������
			OrderDetail detail = new OrderDetail();
			//���ö��������ţ�ʹ��UUID
			detail.setDetailId(UUID.randomUUID().toString());
			//���øõ�ǰ�����������ڵĶ������
			detail.setOrderId(order.getOrderId());
			//���ò�Ʒ���
			detail.setPid(p.getPid());
			//���ò�Ʒ���ۣ�����Productʵ�����е�PriceΪString���ͣ�Orderdetail���е�UnitPriceΪdouble���ͣ�����ҪǿתΪDouble����
			//�����̳�ȫ�����ۣ�����*0.8
			detail.setUnitPrice(Double.parseDouble(p.getPprice())*0.8);
			//������Ʒ����
			detail.setCount(goods.get(p));
			//���ö���������붩�����鼯����
			details.add(detail);
		}
		List<Product> noGoods = new ArrayList<>();
		//�޸ĮaƷ���е���Ʒ��棬�ж��Ƿ�ִ�гɹ����ɹ��������+1
		for(Product p:goods.keySet()) {
			p.setStuck(p.getStuck()-goods.get(p));
			if(ProductBus.updateProduct(p)) {
				count++;
			}
		}
		//���������ݿ�orders���м����ݵķ������ж��Ƿ�ִ�гɹ����ɹ��������+1
		if(OrderBus.putOrderData(order)) {
			count++;
		}
		//���������ݿ�orderdetails����������ݵķ������ж��Ƿ�ִ�гɹ����ɹ��������+1
		if(OrderDetailBus.putOrderDetailData(details)) {
			count ++;
		}
		//�������������ִ�гɹ����������Ϊ3
		//�ж��Ƿ����������Ƿ�ִ�гɹ�
		if(count>=3) {
			//����ɹ�����Ϣ�������ݿ��У���session�е���Ʒ��Ϣ��������仰˵������չ��ﳵ��
			request.getSession().removeAttribute("mallList");
			response.getWriter().print("OK");
		} else {
			//���ִ��ʧ�ܣ���֮ǰִ�гɹ���ӵ�����ɾ��
			//�÷�����֪���Ƿ���ȷ�����Կ��������������������apach�лع����ݵķ���
			ReturnBus.reDatabase(order);
			response.getWriter().print("Error");
		}
	}

}
