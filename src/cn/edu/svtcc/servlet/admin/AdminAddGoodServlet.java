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
 * 管理员系统中，添加商品的逻辑
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
		//声明变量用来存放信息
		String name = "";
		String price = "";
		Integer stuck = 0;
		Integer category = 0;
		Integer providerId = 0;
		String picture = "";
		//外部jar包提供的类
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload fileUpload = new ServletFileUpload(factory);
		//设置编码，文件名不会乱码
		fileUpload.setHeaderEncoding("utf-8");
		try {
			//获取页面发送的信息
			List<FileItem> items = fileUpload.parseRequest(request);
			//遍历获取到的信息
			for(FileItem item : items) {
				//获得信息对应的name
				String fileName = item.getFieldName();
				/**
				 * 分别对比name，将对应的值赋给之前声明的变量
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
				//如果检测到是file文件
				if("picture".equals(fileName)) {
					//上传文件存放的路径
					String path1 = this.getServletContext().getRealPath("images/goods");
					String path2 = "D:/Java/JavaWeb/Shoping/WebContent/images/goods";
					//文件名
					picture = item.getName();
					//文件路径
					String picPath1 = path1 + File.separator + picture;
					String picPath2 = path2 + File.separator + picture;
					//连接文件
					File f1 = new File(picPath1);
					File f2 = new File(picPath2);
					//创建目录，创建文件
					f1.getParentFile().mkdirs();
					f1.createNewFile();
					f2.getParentFile().mkdirs();
					f2.createNewFile();
					//字节输入流
					InputStream in = item.getInputStream();
					//字节输出流
					FileOutputStream out1 = new FileOutputStream(f1);
					FileOutputStream out2 = new FileOutputStream(f2);
					//存放二进制数据
					byte[] bytes = new byte[1024];
					//读取长度
					int len = 0;
					//判断读取的长度是否>0
					while((len=in.read(bytes))>0) {
						//将文件以二进制的形式写入目标路径
						out1.write(bytes, 0, len);
						out2.write(bytes, 0, len);
					}
					//关闭
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
		//创建产品对象，并存入数据
		Product pro = new Product();
		pro.setPname(name);
		pro.setPimage(picture);
		pro.setStuck(stuck);
		pro.setPprice(price);
		pro.setCategory(category);
		pro.setProvider(providerId);
		//设置浏览器响应编码
		response.setContentType("text/html;charset=utf-8");
		//添加商品
		if(ProductBus.addProduct(pro)) {
			//添加成功则显示提示信息
			response.getWriter().print("添加商品成功");
			//设置自动刷新，跳转到新的页面
			response.setHeader("refresh", "3;adminIndex.jsp");
		} else {
			response.getWriter().print("商品已存在");
			response.setHeader("refresh", "3;adminAddProduct.jsp");
		}
	}

}
