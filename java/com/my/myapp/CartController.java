package com.my.myapp;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.model.CartVO;
import com.shop.service.ShopService;
import com.user.model.UserVO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/user")
@Log4j
public class CartController {
	
	@Inject
	private ShopService shopService;
	
	@PostMapping("/cartAdd")
	public String addCart(
			Model m, HttpSession session,
			@RequestParam(defaultValue="0") int pnum, @RequestParam(defaultValue="0") int pqty) {
		log.info("pnum="+pnum+", pqty="+pqty);
		if(pnum==0||pqty==0) {
			return "redirect:index";
		}
		//�α����� ȸ���� ȸ����ȣ
		UserVO loginUser=(UserVO)session.getAttribute("loginUser");
		int idx_fk=loginUser.getIdx();
		
		CartVO cvo=new CartVO();
		cvo.setIdx_fk(idx_fk);
		cvo.setPnum_fk(pnum);
		cvo.setPqty(pqty);
		//��ٱ��Ͽ� ��ǰ �߰�-----
		int n=shopService.addCart(cvo);
		log.info("n: "+n+" , cvo="+cvo);
		//��ٱ��� ��� ��������
		//List<CartVO> cartList=shopService.selectCartView(idx_fk);
		//m.addAttribute("cartArr", cartList);
		
		//���⼭ forward�̵��� �ϸ� ������ ���ΰ�ħ�� ��� ��ǰ ������ �߰��Ǵ� ���� �߻�
		//=> ��ٱ��� �Ѿ� ����
		//==> redirect������� �̵��� ��
		
		//return "shop/cartList";
		return "redirect:cartList";
	}//-------------------
	
	@GetMapping("/cartList")
	public String cartList(Model m, HttpSession session) {
		
		UserVO loginUser=(UserVO)session.getAttribute("loginUser");
		int idx_fk=loginUser.getIdx();
		
		//��ٱ��� ��� ��������
		List<CartVO> cartList=shopService.selectCartView(idx_fk);
		//Ư�� ȸ���� ��ٱ��� �Ѿװ� ������Ʈ ��������
		CartVO cvo=shopService.getCartTotal(idx_fk);
		
		m.addAttribute("cartArr", cartList);
		m.addAttribute("cartTotal", cvo);
		return "shop/cartList";
	}//--------------------------
	
	@PostMapping("/cartDel")
	public String cartDelete(@RequestParam(defaultValue="0") int cartNum) {
		if(cartNum==0) {
			return "redirect:cartList";
		}
		int n=shopService.delCart(cartNum);
		
		return "redirect:cartList";
	}
	
	

}/////////////////////////////////////////////////////////











