DROP INDEX PK_member;

/* 회원 */
DROP TABLE member 
	CASCADE CONSTRAINTS;

/* 회원 */
CREATE TABLE member (
	idx number(8) NOT NULL, /* 회원번호 */
	name varchar2(30) NOT NULL, /* 이름 */
	userid varchar2(20) NOT NULL, /* 아이디 */
	pwd varchar2(100) NOT NULL, /* 비밀번호 */
	hp1 char(3) NOT NULL, /* 연락처1 */
	hp2 char(4) NOT NULL, /* 연락처2 */
	hp3 char(4) NOT NULL, /* 연락처3 */
	post char(5), /* 우편번호 */
	addr1 varchar2(100), /* 주소1 */
	addr2 varchar2(100), /* 주소2 */
	indate DATE, /* 가입일 */
	mileage NUMBER(8), /* 보유적립금 */
	mstate NUMBER(2), /* 회원상태 */
	mreason VARCHAR2(1000) /* 정지,탈퇴사유 */
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