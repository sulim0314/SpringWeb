package com.shop.model;

import java.sql.Date;

import lombok.Data;

@Data
public class CartVO {
	
	private int cartNum;//��ٱ��� ��ȣ
	private int idx_fk;//ȸ����ȣ
	private int pnum_fk;//��ǰ��ȣ
	private int pqty;//��ٱ��� ��ǰ ����
	private Date indate;
	
	//��ٱ��� ��ǰ����
	private String pname;
	private String pimage1;
	private int price;
	private int saleprice;
	private int point;
	
	private int totalPrice;//saleprice*pqty => ���� ��ǰ�� �Ѿ�
	private int totalPoint;//point*pqty => ���� ��ǰ�� �� ��������Ʈ

	private int cartTotalPrice;//��ٱ��Ͽ� ���� ��� ��ǰ�� �Ѿ�
	private int cartTotalPoint;

}
