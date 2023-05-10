package com.shop.mapper;

import java.util.List;

import com.shop.model.CartVO;

public interface CartMapper {
	
	Integer selectCartCountByPnum(CartVO cvo);
	//ȸ����ȣ, ��ǰ��ȣ
	int updateCartQty(CartVO cvo);
	int addCart(CartVO cvo);
	
	List<CartVO> selectCartView(int idx_fk);
	CartVO getCartTotal(int idx_fk);
	
	int delCart(int cartNum);
	int editCart(CartVO cvo);
	

}
