package cn.edu.svtcc.servlet.admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.edu.svtcc.bus.ProductBus;
import cn.edu.svtcc.domain.Product;

/**
 * Servlet implementation class AdminAddGoodServlet
 * ����Աϵͳ�У������Ʒ���߼�
 */
@WebServlet("/admin/AdminAddGoodServlet")
public class AdminAddGoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAddGoodServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�����������������Ϣ
		String name = "";
		String price = "";
		Integer stuck = 0;
		Integer category = 0;
		Integer providerId = 0;
		String picture = "";
		//�ⲿjar���ṩ����
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload fileUpload = new ServletFileUpload(factory);
		//���ñ��룬�ļ�����������
		fileUpload.setHeaderEncoding("utf-8");
		try {
			//��ȡҳ�淢�͵���Ϣ
			List<FileItem> items = fileUpload.parseRequest(request);
			//������ȡ������Ϣ
			for(FileItem item : items) {
				//�����Ϣ��Ӧ��name
				String fileName = item.getFieldName();
				/**
				 * �ֱ�Ա�name������Ӧ��ֵ����֮ǰ�����ı���
				 */
				if("name".equals(fileName)) {
					name = item.getString("utf-8");
				}
				if("price".equals(fileName)) {
					price = item.getString("utf-8");
				}
				if("stuck".equals(fileName)) {
					stuck = Integer.valueOf(item.getString("utf-8"));
				}
				if("category".equals(fileName)) {
					category = Integer.valueOf(item.getString("utf-8"));
				}
				if("provider".equals(fileName)) {
					providerId = Integer.valueOf(item.getString("utf-8"));
				}
				//�����⵽��file�ļ�
				if("picture".equals(fileName)) {
					//�ϴ��ļ���ŵ�·��
					String path1 = this.getServletContext().getRealPath("images/goods");
					String path2 = "D:/Java/JavaWeb/Shoping/WebContent/images/goods";
					//�ļ���
					picture = item.getName();
					//�ļ�·��
					String picPath1 = path1 + File.separator + picture;
					String picPath2 = path2 + File.separator + picture;
					//�����ļ�
					File f1 = new File(picPath1);
					File f2 = new File(picPath2);
					//����Ŀ¼�������ļ�
					f1.getParentFile().mkdirs();
					f1.createNewFile();
					f2.getParentFile().mkdirs();
					f2.createNewFile();
					//�ֽ�������
					InputStream in = item.getInputStream();
					//�ֽ������
					FileOutputStream out1 = new FileOutputStream(f1);
					FileOutputStream out2 = new FileOutputStream(f2);
					//��Ŷ���������
					byte[] bytes = new byte[1024];
					//��ȡ����
					int len = 0;
					//�ж϶�ȡ�ĳ����Ƿ�>0
					while((len=in.read(bytes))>0) {
						//���ļ��Զ����Ƶ���ʽд��Ŀ��·��
						out1.write(bytes, 0, len);
						out2.write(bytes, 0, len);
					}
					//�ر�
					in.close();
					out1.close();
					out2.close();
					item.delete();
				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//������Ʒ���󣬲���������
		Product pro = new Product();
		pro.setPname(name);
		pro.setPimage(picture);
		pro.setStuck(stuck);
		pro.setPprice(price);
		pro.setCategory(category);
		pro.setProvider(providerId);
		//�����������Ӧ����
		response.setContentType("text/html;charset=utf-8");
		//�����Ʒ
		if(ProductBus.addProduct(pro)) {
			//��ӳɹ�����ʾ��ʾ��Ϣ
			response.getWriter().print("�����Ʒ�ɹ�");
			//�����Զ�ˢ�£���ת���µ�ҳ��
			response.setHeader("refresh", "3;adminIndex.jsp");
		} else {
			response.getWriter().print("��Ʒ�Ѵ���");
			response.setHeader("refresh", "3;adminAddProduct.jsp");
		}
	}

}
