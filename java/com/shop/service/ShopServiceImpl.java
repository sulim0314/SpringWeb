package com.shop.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.shop.mapper.CartMapper;
import com.shop.mapper.ProductMapper;
import com.shop.model.CartVO;
import com.shop.model.ProductVO;

@Service(value = "shopServiceImpl")
public class ShopServiceImpl implements ShopService {
	
	@Inject
	private ProductMapper productMapper;

	@Inject
	private CartMapper cartMapper;
	
	@Override
	public List<ProductVO> selectByPspec(String pspec) {
		
		return this.productMapper.selectByPspec(pspec);
	}

	@Override
	public List<ProductVO> selectByCategory(int cg_num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductVO selectByPnum(int pnum) {
		
		return productMapper.selectByPnum(pnum);
	}

	@Override
	public int addCart(CartVO cartVo) {
		//[1] ȸ����ȣ�� ��ǰ��ȣ�� cart���̺� �ִ� ��ǰ���� ��������
		Integer cnt=cartMapper.selectCartCountByPnum(cartVo);
		//[1_1] ��ٱ��Ͽ� �߰��� ��ǰ�� �̹� ��ٱ��Ͽ� ����ִٸ� => ������ ���� =>update��
		if(cnt!=null) {
			int n=cartMapper.updateCartQty(cartVo);
			return n;
		}else {
		//[1_2] ��ٱ��Ͽ� ���� ��ǰ�� �߰��Ѵٸ� => insert�� ����
			int n=cartMapper.addCart(cartVo);
			return n;
		}
	}//---------------------------------------

	@Override
	public int updateCartQty(CartVO cartVo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int editCart(CartVO cartVo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<CartVO> selectCartView(int midx) {
		
		return this.cartMapper.selectCartView(midx);
	}

	@Override
	public int delCart(int cartNum) {		
		return cartMapper.delCart(cartNum);
	}

	@Override
	public int delCartAll(CartVO cartVo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delCartOrder(Map<String, Integer> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCartCountByIdx(CartVO cartVo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CartVO getCartTotal(int midx_fk) {
		
		return this.cartMapper.getCartTotal(midx_fk);
	}

	@Override
	public void delCartByOrder(int midx_fk, int pnum) {
		// TODO Auto-generated method stub
		
	}

}
