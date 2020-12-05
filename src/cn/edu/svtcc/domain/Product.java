package cn.edu.svtcc.domain;

/**
 * ��Ʒ��
 * @author Shixuanming
 *
 */

public class Product {
	//��Ʒid
	private Integer pid;
	//��ƷͼƬ
	private String pimage;
	//��Ʒ����
	private String pname;
	//���
	private Integer stuck;
	//����
	private String pprice;
	//���id
	private Integer category;
	//�������
	private String categoryName;
	//��Ӧ��id
	private Integer provider;
	//��Ӧ������
	private String providerName;
	//������д����������������Map�е�containsKey()�������ж�
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Product) {
			Product pro = (Product)obj;
			if(this.pid.equals(pro.pid)) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	@Override
	public int hashCode() {
		return this.pid;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getPimage() {
		return pimage;
	}
	public void setPimage(String pimage) {
		this.pimage = pimage;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public Integer getStuck() {
		return stuck;
	}
	public void setStuck(Integer stuck) {
		this.stuck = stuck;
	}
	public String getPprice() {
		return pprice;
	}
	public void setPprice(String pprice) {
		this.pprice = pprice;
	}
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Integer getProvider() {
		return provider;
	}
	public void setProvider(Integer provider) {
		this.provider = provider;
	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	
}
