DROP INDEX PK_member;

/* ȸ�� */
DROP TABLE member 
	CASCADE CONSTRAINTS;

/* ȸ�� */
CREATE TABLE member (
	idx number(8) NOT NULL, /* ȸ����ȣ */
	name varchar2(30) NOT NULL, /* �̸� */
	userid varchar2(20) NOT NULL, /* ���̵� */
	pwd varchar2(100) NOT NULL, /* ��й�ȣ */
	hp1 char(3) NOT NULL, /* ����ó1 */
	hp2 char(4) NOT NULL, /* ����ó2 */
	hp3 char(4) NOT NULL, /* ����ó3 */
	post char(5), /* �����ȣ */
	addr1 varchar2(100), /* �ּ�1 */
	addr2 varchar2(100), /* �ּ�2 */
	indate DATE, /* ������ */
	mileage NUMBER(8), /* ���������� */
	mstate NUMBER(2), /* ȸ������ */
	mreason VARCHAR2(1000) /* ����,Ż����� */
);


CREATE UNIQUE INDEX PK_member
	ON member (
		idx ASC
	);

ALTER TABLE member
	ADD
		CONSTRAINT PK_member
		PRIMARY KEY (
			idx
		);
        
drop sequence member_seq;        

create sequence member_seq
start with 1
increment by 1
nocache;