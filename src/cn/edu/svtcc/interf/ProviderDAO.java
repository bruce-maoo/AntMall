package cn.edu.svtcc.interf;

import java.util.List;

import cn.edu.svtcc.domain.Provider;
/**
 * 
 * @author Shixuanming
 *	��Ӧ�̵����ݿ���ʽӿ�
 */
public interface ProviderDAO {
	public List<Provider> getAllProvider();
	public boolean addProvider(Provider provider);
	public Provider getProviderById(Integer id);
	public boolean updateProvider(Provider provider);
	public boolean deleteProvider(Integer id);
}
