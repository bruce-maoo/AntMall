package cn.edu.svtcc.servlet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.svtcc.bus.OrderBus;
import cn.edu.svtcc.domain.Order;

/**
 * Servlet implementation class ChangeToXMLServlet
 * ����������ת����xml�ļ���Ϊ��׿�ṩ�ӿ�
 */
@WebServlet("/ChangeToXMLServlet")
public class ChangeToXMLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeToXMLServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�����ļ�
		File f = new File("D:/Java/JavaWeb/Shoping/WebContent/WEB-INF"+File.separator+"inf.xml");
		//�ж��ļ��Ƿ����
		if(!f.exists()) {
			f.createNewFile();
		}
		//��ö�������
		List<Order> orderList = OrderBus.getAllOrders();
		//����ֽ������
		FileOutputStream fi = new FileOutputStream(f);
		//�ַ���
		OutputStreamWriter ir = new OutputStreamWriter(fi,"utf-8");
		//�����������ַ������
		BufferedWriter bw = new BufferedWriter(ir);
		//����д��
		bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		bw.write("<orders>\n");
		for(Order order:orderList) {
			bw.write("\t<order>\r\n" + 
					"		<orderId>"+order.getOrderId()+"</orderId>\r\n" + 
					"		<username>"+order.getUsername()+"</username>\r\n" + 
					"		<orderTime>"+order.getOrderTime()+"</orderTime>\r\n" + 
					"		<totalPrice>"+order.getTotalPrice()+"</totalPrice>\r\n" + 
					"		<productNum>"+order.getProductNum()+"</productNum>\r\n" + 
					"		<addres>"+order.getAddress()+"</addres>\r\n" + 
					"		<reciever>"+order.getReciever()+"</reciever>\r\n" + 
					"		<phone>"+order.getPhone()+"</phone>\r\n" + 
					"	</order>\n");
		}
		bw.write("</orders>");
		bw.flush();
		bw.close();
		//����ת��
		request.getRequestDispatcher("WEB-INF/inf.xml").forward(request, response);;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
