package cn.edu.svtcc.servlet.admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
 * Servlet implementation class AdminEditGoodServlet
 * ����Աϵͳ�У��༭��Ʒ���߼�
 */
@WebServlet("/admin/AdminEditGoodServlet")
public class AdminEditGoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminEditGoodServlet() {
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
		//�������������ڴ������
		String name = "";
		String price = "";
		Integer stuck = 0;
		Integer category = 0;
		Integer providerId = 0;
		String picture = "";
		Integer id = 0;
		boolean r = false;
		//����jar������
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload fileUpload = new ServletFileUpload(factory);
		//���ñ��룬�ļ�����������
		fileUpload.setHeaderEncoding("utf-8");
		try {
			//��ȡҳ���������Ϣ
			List<FileItem> items = fileUpload.parseRequest(request);
			//������Ϣ
			for(FileItem item : items) {
				//��ö�Ӧ��Ϣ��name
				String fileName = item.getFieldName();
				/**
				 * �ֱ�name���жԱȣ���ֵ������
				 */
				if("pid".equals(fileName)) {
					id = Integer.valueOf(item.getString("utf-8"));
				}
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
				//����ϢΪ�ļ�
				if("picture".equals(fileName)) {
					//����ļ�����Ϊ�գ��޸���ͼƬ��
					if(!("".equals(item.getName()))) {
						//trueΪ�޸Ĺ�ͼƬ
						r = true;
						//�ļ��ϴ�·��
						String path1 = this.getServletContext().getRealPath("images/goods");
						String path2 = "D:/Java/JavaWeb/Shoping/WebContent/images/goods";
						//�ļ�����
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
						//����ֽ�������
						InputStream in = item.getInputStream();
						//����ֽ������
						FileOutputStream out1 = new FileOutputStream(f1);
						FileOutputStream out2 = new FileOutputStream(f2);
						//��Ŷ���������
						byte[] bytes = new byte[1024];
						//��ȡ����
						int len = 0;
						//�ж϶�ȡ�ĳ����Ƿ�>1
						while((len=in.read(bytes))>0) {
							//ͨ��������д���ļ�
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
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//������Ʒ���󣬲���������
		Product pro = new Product();
		pro.setPid(id);
		pro.setPname(name);
		pro.setPimage(picture);
		pro.setStuck(stuck);
		pro.setPprice(price);
		pro.setCategory(category);
		pro.setProvider(providerId);
		//��֪�����������ʾ��ʽ������
		response.setContentType("text/html;charset=utf-8");
		//����޸���ͼƬ
		if(r) {
			//��������Ϣ
			if(ProductBus.updateProductAll(pro)) {
				response.getWriter().write("�޸ĳɹ�");
				//����ˢ�£��Զ���ת
				response.setHeader("refresh", "2;AdminGoodsListServlet");
			} else {
				response.getWriter().write("�޸�ʧ��");
			}
		} else {
			//���û���޸�ͼƬ
			//�޸���Ϣ������ͼƬ
			if(ProductBus.updateProductAllButPic(pro)) {
				response.getWriter().write("�޸ĳɹ�");
				//�����Զ�ˢ�£���ת����ҳ��
				response.setHeader("refresh", "2;AdminGoodsListServlet");
			} else {
				response.getWriter().write("�޸�ʧ��");
			}
		}
	}

}
