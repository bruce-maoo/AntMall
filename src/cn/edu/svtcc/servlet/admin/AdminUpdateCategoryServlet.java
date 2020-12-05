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
 * Servlet implementation class AdminUpdateCategoryServlet
 * ����Աϵͳ�У��޸������Ϣ���߼�
 */
@WebServlet("/admin/AdminUpdateCategoryServlet")
public class AdminUpdateCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminUpdateCategoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * ͨ��get��ʽ����ʱ�������ǻ�ȡ����ת����ҳ��
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ҳ���ȡ���id��ǿת
		int id = Integer.valueOf(request.getParameter("categoryId"));
		//ͨ��idѰ�Ҷ�Ӧ�������Ϣ
		Category category = CategoryBus.getCategoryById(id);
		//�������������request����
		request.setAttribute("category", category);
		//����ת��
		request.getRequestDispatcher("admin_editCategory.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * ͨ��post��ʽ����ʱ���Ǵ����޸������Ϣ���߼�
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ҳ���ȡ�����
		int id = Integer.valueOf(request.getParameter("id"));
		//��ȡ�������
		String categoryName = request.getParameter("categoryName");
		//���������󣬲���������
		Category category = new Category();
		category.setCategory(id);
		category.setCategoryName(categoryName);
		//������������Ϣ
		List<Category> categoryList = CategoryBus.getAllCategory();
		//�����������
		for(Category c:categoryList) {
			//�ж��޸ĺ����������Ƿ���֮ǰ�����ظ�
			if(c.getCategoryName().equals(category.getCategoryName())) {
				//�ظ��򷵻ظ�ҳ����ϢError2
				response.getWriter().write("Error2");
				return;
			}
		}
		//�޸������Ϣ
		boolean r = CategoryBus.updateCategory(category);
		//�޸ĳɹ����򷵻���ϢOK
		if(r) {
			response.getWriter().write("OK");
		} else {
			response.getWriter().write("Error");
		}
	}

}
