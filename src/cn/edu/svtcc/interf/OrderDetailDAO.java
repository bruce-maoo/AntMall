package cn.edu.svtcc.interf;

import java.util.List;

import cn.edu.svtcc.domain.OrderDetail;
/**
 * 
 * @author Shixuanming
 * ������������ݿ���ʽӿ�
 */
public interface OrderDetailDAO {
	public boolean putOrderDetailData(List<OrderDetail> details);
	public boolean deleteOrderDetails(String orderId);
}
