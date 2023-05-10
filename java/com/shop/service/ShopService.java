package com.shop.service;

import java.util.List;
import java.util.Map;

import com.shop.model.CartVO;
import com.shop.model.ProductVO;

public interface ShopService {
	
	/*Pspec ���� ��ǰ ���� ��������*/
	public List<ProductVO> selectByPspec(String pspec);
	/*ī�װ��� ��ǰ���� ��������*/
	public List<ProductVO> selectByCategory(int cg_num);
	/**��ǰ��ȣ�� Ư�� ��ǰ ���� ��������*/
	public ProductVO selectByPnum(int pnum);
	
	/**��ٱ��� ���� �޼ҵ�===============*/
	int addCart(CartVO cartVo);//��ٱ��� �߰��ϱ�
	int updateCartQty(CartVO cartVo);//��ٱ��� �߰� ����=>������ ��� ��ǰ�̸� ������ �����ϱ�
	int editCart(CartVO cartVo);// ��ٱ��� �����ϱ�
	List<CartVO> selectCartView(int midx);//Ư�� ȸ���� ��ٱ��� ��Ϻ���
	
	int delCart(int cartNum);
	int delCartAll(CartVO cartVo);
	int delCartOrder(Map<String,Integer>map);
	
	int getCartCountByIdx(CartVO cartVo);
	
	CartVO getCartTotal(int midx_fk);//Ư�� ȸ���� ��ٱ��� �Ѿ�,������Ʈ ���ϱ�

	public void delCartByOrder(int midx_fk, int pnum);
	
}
