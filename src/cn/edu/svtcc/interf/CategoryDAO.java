package cn.edu.svtcc.interf;

import java.util.List;

import cn.edu.svtcc.domain.Category;
/**
 * 
 * @author Shixuanming
 *	�������ݿ���ʲ�ӿ�
 */
public interface CategoryDAO {
	public List<Category> getAllCategory();
	public boolean addCategory(Category category);
	public Category getCategoryById(Integer id);
	public boolean updateCategory(Category category);
	public boolean deleteCategoryById(Integer id);
}
