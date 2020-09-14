create table board_cafe(
	num number primary key,
	writer varchar2(200) not null,
	title varchar2(100) not null,
	content clob,
	viewCount number, --조회수
	regdate date
);

create sequence board_cafe_seq;

create table board_cafe_comment(
	num number primary key, -- 댓글의 글번호 (댓글이던 대댓글이던 다 자신만의 댓글 글번호를 가지고있음-pk)
	writer  varchar2(100), -- 댓글 작성자의 아이디
	content varchar2(500), -- 댓글 내용
	target_id varchar2(100), --댓글의 대상자
	ref_group number, -- 원글 글번호 (원글 글 밑에 달려있는 댓글들 한꺼번에 묶어서 가져오게)
	comment_group number, -- 댓글 글번호 (댓글 밑에 달려있는 대댓글들 한꺼번에 묶어서 가져오게/만약  자기 pk 글번호랑 comment_group 번호가 다르면 대댓글임)
	deleted char(3) default 'no', --삭제된건지 아닌지 알려주는거 
	regdate date
);

create sequence board_cafe_comment_seq;

create table board_gallery(
	num number primary key,
	writer varchar2(100),
	caption varchar2(100),
	imagePath varchar2(100),
	regdate date
);

create sequence board_gallery_seq;

-- 사용자(회원) 정보를 저장할 테이블
create table users(
	id varchar2(100) primary key,
	pwd varchar2(100) not null,
	email varchar2(100),
	profile varchar2(100), -- 프로필 이미지 경로를 저장할 칼럼
	regdate date
);


-- upload된 파일의 정보를 저장할 테이블 --
CREATE TABLE board_file(
   num number primary key,
   writer varchar2(100) not null,
   title varchar2(100) not null,
   orgFileName varchar2(100) not null, -- 원본 파일명
   saveFileName varchar2(100) not null, -- 서버에 저장된 파일명
   -- (나눠서 저장하는 이유: 서로 다른 여러 사람이 올린 파일을 중복되지 않게 저장하기 위함)
   fileSize number not null,
   regdate date
);

create sequence board_file_seq;


-- 트랜젝션으로 관리 : 주문을 했을 때 shop은 update, client_account는 update, client_order는 insert가 된다. 한 번에 승인되거나, 한 번에 취소되어야하는 작업 단위임.

-- 상품 테이블
CREATE TABLE shop(
	num NUMBER PRIMARY KEY, --상품번호
	name VARCHAR2(30), --상품이름
	price NUMBER, --상품가격
	remainCount NUMBER CHECK(remainCount >= 0) --재고갯수 (0보다 작아지면 예외가 발생한다.)
);

-- 고객 계좌 테이블
CREATE TABLE client_account(
	id VARCHAR2(30) PRIMARY KEY, -- 고객의 아이디
	money NUMBER CHECK(money >= 0), -- 고객의 잔고 
	point NUMBER
);

-- 주문 테이블
CREATE TABLE client_order(
	num NUMBER PRIMARY KEY, -- 주문번호
	id VARCHAR2(30), -- 주문 고객의 아이디
	code NUMBER, -- 주문한 상품의 번호 
	addr VARCHAR2(50) -- 배송 주소
);

-- 주문 테이블에 사용할 시퀀스 
CREATE SEQUENCE client_order_seq;


-- sample 데이터
INSERT INTO shop (num,name,price,remainCount)
VALUES(1, '사과', 1000, 5);

INSERT INTO shop (num,name,price,remainCount)
VALUES(2, '바나나', 2000, 5);

INSERT INTO shop (num,name,price,remainCount)
VALUES(3, '귤', 3000, 5);

INSERT INTO client_account (id, money, point)
VALUES('superman', 10000, 0);

INSERT INTO client_account (id, money, point)
VALUES('batman', 10000, 0);




