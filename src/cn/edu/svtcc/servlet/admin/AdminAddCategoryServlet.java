package cn.edu.svtcc.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.svtcc.bus.CategoryBus;
import cn.edu.svtcc.domain.Category;

/**
 * Servlet implementation class AdminAddCategoryServlet
 * ����Աϵͳ�У�������������߼�
 */
@WebServlet("/admin/AdminAddCategoryServlet")
public class AdminAddCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAddCategoryServlet() {
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
		/**
		 * ������id
		 */
		Integer categoryId = 0;
		try {
			//��ҳ����
			categoryId = Integer.valueOf(request.getParameter("category"));
		} catch(Exception e) {
			e.printStackTrace();
			response.getWriter().write("Error2");
			return;
		}
		//��ҳ�����������
		String categoryName = request.getParameter("categoryName");
		//���������󣬲���������
		Category category = new Category();
		category.setCategory(categoryId);
		category.setCategoryName(categoryName);
		//����������
		List<Category> categoryList = CategoryBus.getAllCategory();
		//�����������
		for(Category c:categoryList) {
			//�ж��Ƿ����ظ�id������
			if(c.getCategoryName().equals(category.getCategoryName()) || c.getCategory()==categoryId) {
				response.getWriter().write("Error3");
				return;
			}
		}
		//������
		boolean r = CategoryBus.addCategory(category);
		//��ӳɹ��򷵻���ϢOK
		if(r) {
			response.getWriter().write("OK");
		} else {
			response.getWriter().write("Error");
		}
	}

}
