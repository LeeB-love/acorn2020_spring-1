create table board_cafe(
	num number primary key
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