/* Drop Tables */

DROP TABLE RESERVATION CASCADE CONSTRAINTS;
DROP TABLE CUSTOMER CASCADE CONSTRAINTS;
DROP TABLE FOOD_BUY CASCADE CONSTRAINTS;
DROP TABLE FOOD CASCADE CONSTRAINTS;
DROP TABLE SCREENING CASCADE CONSTRAINTS;
DROP TABLE MOVIE CASCADE CONSTRAINTS;




/* Create Tables */

CREATE TABLE CUSTOMER
(
	CustNo number NOT NULL,
	CustName varchar2(30),
	CustPhone varchar2(30) NOT NULL UNIQUE,
	CustPoint number,
	PRIMARY KEY (CustNo)
);

INSERT INTO Customer VALUES (1,'이소정','010-6689-1543',9999);
INSERT INTO Customer VALUES (2,'채율','010-1578-5483',5000);
INSERT INTO Customer VALUES (3,'김민섭','010-2874-7791',2400);
INSERT INTO Customer VALUES (4,'박상현','010-1748-4832',8000);


CREATE TABLE FOOD
(
	FoodNo number NOT NULL,
	FoodCate varchar2(20) NOT NULL,
	FoodName varchar2(50) NOT NULL UNIQUE,
	FoodPrice number NOT NULL,
	PRIMARY KEY (FoodNo)
);

INSERT INTO FOOD VALUES (1, 'snack', '떡볶이', 6500);
INSERT INTO FOOD VALUES (2, 'snack', '나쵸', 5500);
INSERT INTO FOOD VALUES (3, 'snack', '핫도그', 4500);
INSERT INTO FOOD VALUES (4, 'snack', '갈릭스노잉핫도그',5500);
INSERT INTO FOOD VALUES (5, 'snack', '바삭 구운 먹태', 5500);
INSERT INTO FOOD VALUES (6, 'snack', '즉석구이오징어',5000);
INSERT INTO FOOD VALUES (7, 'snack', '치즈볼',4500);
INSERT INTO FOOD VALUES (8, 'popcorn', '캬라멜 팝콘', 7000);
INSERT INTO FOOD VALUES (9, 'popcorn', '팝콘', 6000);
INSERT INTO FOOD VALUES (10, 'popcorn', '버터갈릭 팝콘', 7000);
INSERT INTO FOOD VALUES (11, 'drink', '칠성사이다', 3500);
INSERT INTO FOOD VALUES (12, 'drink', '제로콜라(펩시)', 3500);
INSERT INTO FOOD VALUES (13, 'drink', '코카콜라', 3500);
INSERT INTO FOOD VALUES (14, 'drink', '환타오렌지', 3500);
INSERT INTO FOOD VALUES (15, 'drink', '환타파인애플', 3500);
INSERT INTO FOOD VALUES (16, 'drink', '(CAN)테라', 4000);
INSERT INTO FOOD VALUES (17, 'drink', '에이드',5000);
INSERT INTO FOOD VALUES (18,'combo','스파이더맨콤보',14000);
INSERT INTO FOOD VALUES (19, 'combo', '트랜스포머콤보', 11000);
INSERT INTO FOOD VALUES (20, 'combo', '커플콤보', 17000);
INSERT INTO FOOD VALUES (21, 'combo', '먹태N비어세트', 9500);
INSERT INTO FOOD VALUES (22, 'combo', '갈릭홀릭 스몰세트', 11000);
INSERT INTO FOOD VALUES (23, 'combo', '시그니처 팝콘세트', 6000);
INSERT INTO FOOD VALUES (24, 'combo', '시그니처 팝콘콤보', 11000);
INSERT INTO FOOD VALUES (25, 'combo', '생일 콤보', 10000);


CREATE TABLE FOOD_BUY
(
	FoodBuNo number NOT NULL,
	FoodCate varchar2(100) NOT NULL,
	FoodName number NOT NULL,
	FoodPrice number NOT NULL,
	FoodQuantity number NOT NULL,
	PRIMARY KEY (FoodBuNo)
);

CREATE SEQUENCE FOODSEQ;
INSERT INTO FOOD_BUY VALUES (FOODSEQ.nextval,'combo', '생일 콤보', 10000, 3);
INSERT INTO FOOD_BUY VALUES (FOODSEQ.nextval,'drink', '환타파인애플', 3500, 1);
INSERT INTO FOOD_BUY VALUES (FOODSEQ.nextval,'snack', '즉석구이오징어',5000, 2);
INSERT INTO FOOD_BUY VALUES (FOODSEQ.nextval,'snack', '떡볶이', 6500,1);


CREATE TABLE MOVIE
(
	MovieNo varchar2(100) NOT NULL,
	MovieTitle varchar2(100) NOT NULL UNIQUE,
	MovieDate date NOT NULL,
	MovieGrade varchar2(30) NOT NULL,
	MoivePrice number NOT NULL,
	PRIMARY KEY (MovieNo)
);

INSERT INTO  Movie VALUES('ac1234' ,'미션임파서블_데드레코닝(4DX)' ,'2022-03-17','15세' ,36000);
INSERT INTO  Movie VALUES('ani5743' ,'엘리멘탈(더빙)' ,'2022-04-10' , '전체' , 15000);
INSERT INTO  Movie VALUES('ro5959' ,'아바타2 : 물의길' ,'2023-06-28' , '12세' , 15000);
INSERT INTO  Movie VALUES('sf1597' ,'트랜스포머(3D)' ,'2023-06-06' , '12세' , 18000);
INSERT INTO  Movie VALUES('ac8878' ,'범죄도시3' ,'2023-05-31' , '청불' , 15000);
INSERT INTO  Movie VALUES('co2215' ,'19곰테드' ,'2023-07-12' , '15세' , 15000);
INSERT INTO  Movie VALUES('ac5724' ,'탑건_매버릭' ,'2023-07-12' , '15세' , 15000);
INSERT INTO  Movie VALUES('sf0416' ,'타짜:신의손' ,'2019-12-17' , '청불' , 15000);
INSERT INTO  Movie VALUES('sr3319' ,'신세계' ,'2020-06-21' , '청불' , 15000);
INSERT INTO  Movie VALUES('sr3110' ,'아저씨' ,'2019-05-14' , '청불' , 15000);




CREATE TABLE RESERVATION
(
	ReserNo number NOT NULL,
	ReserDate date NOT NULL,
	ReserSeat varchar2(100) NOT NULL,
	MovieTitle varchar2(100) NOT NULL,
	CustNo number,
	ScreenTheater number NOT NULL,
	ScreenNo number NOT NULL,
	PRIMARY KEY (ReserNo)
);

CREATE SEQUENCE reserseq START WITH 1211341924142181; --회원번호 시퀀스 설정
INSERT INTO  Reservation VALUES(reserseq.nextval ,'2018-04-25','A05','신세계' , 2 , 5);
INSERT INTO  Reservation VALUES(reserseq.nextval ,'2018-11-17','B03','19곰테드' , 1 , 4);
INSERT INTO  Reservation VALUES(reserseq.nextval ,'2019-07-24','C02','아저씨' , 3 , 1);
INSERT INTO  Reservation VALUES(reserseq.nextval ,'2020-07-21','D05','국제시장' , 4 , 10);
INSERT INTO  Reservation VALUES(reserseq.nextval ,'2020-04-03','A03','신세계' , 3 , 11);
INSERT INTO  Reservation VALUES(reserseq.nextval ,'2022-03-10','C01','타짜:신의손' , 4 , 9);
INSERT INTO  Reservation VALUES(reserseq.nextval ,'2019-05-22','B04','신세계' , 4 , 5);


CREATE TABLE SCREENING
(
	ScreenNo number NOT NULL,
	ScreenDate date NOT NULL,
	ScreenTheater number NOT NULL,
	MovieTitle varchar2(100) NOT NULL,
	PRIMARY KEY (ScreenNo)
);

--1
INSERT INTO SCREENING VALUES(1, TO_Date('2023-07-21 7:00:00', 'yyyy-mm-dd hh24:mi:ss'), '1', '미션임파서블_데드레코닝(4DX)');
INSERT INTO SCREENING VALUES(2, TO_Date('2023-07-21 10:00:00', 'yyyy-mm-dd hh24:mi:ss'), '1', '미션임파서블_데드레코닝(4DX)');
INSERT INTO SCREENING VALUES(3, TO_Date('2023-07-21 13:00:00', 'yyyy-mm-dd hh24:mi:ss'), '1', '미션임파서블_데드레코닝(4DX)');
INSERT INTO SCREENING VALUES(4, TO_Date('2023-07-21 16:40:00', 'yyyy-mm-dd hh24:mi:ss'), '1', '미션임파서블_데드레코닝(4DX)');
INSERT INTO SCREENING VALUES(5, TO_Date('2023-07-21 19:40:00', 'yyyy-mm-dd hh24:mi:ss'), '1', '미션임파서블_데드레코닝(4DX)');
INSERT INTO SCREENING VALUES(6, TO_Date('2023-07-21 23:10:00', 'yyyy-mm-dd hh24:mi:ss'), '1', '미션임파서블_데드레코닝(4DX)');

--2
INSERT INTO SCREENING VALUES(7, TO_Date('2023-07-21 8:00:00', 'yyyy-mm-dd hh24:mi:ss'), '2', '엘리멘탈(더빙)');
INSERT INTO SCREENING VALUES(8, TO_Date('2023-07-21 10:00:00', 'yyyy-mm-dd hh24:mi:ss'), '2', '엘리멘탈(더빙)');
INSERT INTO SCREENING VALUES(9, TO_Date('2023-07-21 12:30:00', 'yyyy-mm-dd hh24:mi:ss'), '2', '엘리멘탈(더빙)');
INSERT INTO SCREENING VALUES(10, TO_Date('2023-07-21 14:40:00', 'yyyy-mm-dd hh24:mi:ss'), '2', '엘리멘탈(더빙)');
INSERT INTO SCREENING VALUES(11, TO_Date('2023-07-21 17:00:00', 'yyyy-mm-dd hh24:mi:ss'), '2', '엘리멘탈(더빙)');
INSERT INTO SCREENING VALUES(12, TO_Date('2023-07-21 19:20:00', 'yyyy-mm-dd hh24:mi:ss'), '2', '엘리멘탈(더빙)');
INSERT INTO SCREENING VALUES(13, TO_Date('2023-07-21 23:40:00', 'yyyy-mm-dd hh24:mi:ss'), '2', '엘리멘탈(더빙)');

--3
INSERT INTO SCREENING VALUES(14, TO_Date('2023-07-21 6:00:00', 'yyyy-mm-dd hh24:mi:ss'), '3', '아바타2 : 물의길');
INSERT INTO SCREENING VALUES(15, TO_Date('2023-07-21 9:30:00', 'yyyy-mm-dd hh24:mi:ss'), '3', '아바타2 : 물의길');
INSERT INTO SCREENING VALUES(16, TO_Date('2023-07-21 13:00:00', 'yyyy-mm-dd hh24:mi:ss'), '3', '아바타2 : 물의길');
INSERT INTO SCREENING VALUES(17, TO_Date('2023-07-21 16:30:00', 'yyyy-mm-dd hh24:mi:ss'), '3', '아바타2 : 물의길');
INSERT INTO SCREENING VALUES(18, TO_Date('2023-07-21 20:00:00', 'yyyy-mm-dd hh24:mi:ss'), '3', '아바타2 : 물의길');
INSERT INTO SCREENING VALUES(19, TO_Date('2023-07-21 23:30:00', 'yyyy-mm-dd hh24:mi:ss'), '3', '아바타2 : 물의길');

--4
INSERT INTO SCREENING VALUES(20, TO_Date('2023-07-21 8:00:00', 'yyyy-mm-dd hh24:mi:ss'), '4', '트랜스포머(3D)');
INSERT INTO SCREENING VALUES(21, TO_Date('2023-07-21 10:20:00', 'yyyy-mm-dd hh24:mi:ss'), '4', '트랜스포머(3D)');
INSERT INTO SCREENING VALUES(22, TO_Date('2023-07-21 12:40:00', 'yyyy-mm-dd hh24:mi:ss'), '4', '트랜스포머(3D)');
INSERT INTO SCREENING VALUES(23, TO_Date('2023-07-21 15:00:00', 'yyyy-mm-dd hh24:mi:ss'), '4', '트랜스포머(3D)');
INSERT INTO SCREENING VALUES(24, TO_Date('2023-07-21 17:30:00', 'yyyy-mm-dd hh24:mi:ss'), '4', '트랜스포머(3D)');
INSERT INTO SCREENING VALUES(25, TO_Date('2023-07-21 19:50:00', 'yyyy-mm-dd hh24:mi:ss'), '4', '트랜스포머(3D)');
INSERT INTO SCREENING VALUES(26, TO_Date('2023-07-21 22:10:00', 'yyyy-mm-dd hh24:mi:ss'), '4', '트랜스포머(3D)');

--5
INSERT INTO SCREENING VALUES(27, TO_Date('2023-07-21 7:00:00', 'yyyy-mm-dd hh24:mi:ss'), '5', '범죄도시3');
INSERT INTO SCREENING VALUES(28, TO_Date('2023-07-21 9:00:00', 'yyyy-mm-dd hh24:mi:ss'), '5', '범죄도시3');
INSERT INTO SCREENING VALUES(29, TO_Date('2023-07-21 11:00:00', 'yyyy-mm-dd hh24:mi:ss'), '5', '범죄도시3');
INSERT INTO SCREENING VALUES(30, TO_Date('2023-07-21 13:20:00', 'yyyy-mm-dd hh24:mi:ss'), '5', '범죄도시3');
INSERT INTO SCREENING VALUES(31, TO_Date('2023-07-21 15:30:00', 'yyyy-mm-dd hh24:mi:ss'), '5', '범죄도시3');
INSERT INTO SCREENING VALUES(32, TO_Date('2023-07-21 17:30:00', 'yyyy-mm-dd hh24:mi:ss'), '5', '범죄도시3');
INSERT INTO SCREENING VALUES(33, TO_Date('2023-07-21 19:50:00', 'yyyy-mm-dd hh24:mi:ss'), '5', '범죄도시3');
INSERT INTO SCREENING VALUES(34, TO_Date('2023-07-21 22:10:00', 'yyyy-mm-dd hh24:mi:ss'), '5', '범죄도시3');

--6
INSERT INTO SCREENING VALUES(35, TO_Date('2023-07-21 9:00:00', 'yyyy-mm-dd hh24:mi:ss'), '6', '19곰테드');
INSERT INTO SCREENING VALUES(36, TO_Date('2023-07-21 11:00:00', 'yyyy-mm-dd hh24:mi:ss'), '6', '19곰테드');
INSERT INTO SCREENING VALUES(37, TO_Date('2023-07-21 13:20:00', 'yyyy-mm-dd hh24:mi:ss'), '6', '19곰테드');
INSERT INTO SCREENING VALUES(38, TO_Date('2023-07-21 15:20:00', 'yyyy-mm-dd hh24:mi:ss'), '6', '19곰테드');
INSERT INTO SCREENING VALUES(39, TO_Date('2023-07-21 17:20:00', 'yyyy-mm-dd hh24:mi:ss'), '6', '19곰테드');
INSERT INTO SCREENING VALUES(40, TO_Date('2023-07-21 19:30:00', 'yyyy-mm-dd hh24:mi:ss'), '6', '19곰테드');
INSERT INTO SCREENING VALUES(41, TO_Date('2023-07-21 21:40:00', 'yyyy-mm-dd hh24:mi:ss'), '6', '19곰테드');

--7
INSERT INTO SCREENING VALUES(42, TO_Date('2023-07-21 7:00:00', 'yyyy-mm-dd hh24:mi:ss'), '7', '탑건_매버릭');
INSERT INTO SCREENING VALUES(43, TO_Date('2023-07-21 9:30:00', 'yyyy-mm-dd hh24:mi:ss'), '7', '탑건_매버릭');
INSERT INTO SCREENING VALUES(44, TO_Date('2023-07-21 12:00:00', 'yyyy-mm-dd hh24:mi:ss'), '7', '탑건_매버릭');
INSERT INTO SCREENING VALUES(45, TO_Date('2023-07-21 14:30:00', 'yyyy-mm-dd hh24:mi:ss'), '7', '탑건_매버릭');
INSERT INTO SCREENING VALUES(46, TO_Date('2023-07-21 17:00:00', 'yyyy-mm-dd hh24:mi:ss'), '7', '탑건_매버릭');
INSERT INTO SCREENING VALUES(47, TO_Date('2023-07-21 19:30:00', 'yyyy-mm-dd hh24:mi:ss'), '7', '탑건_매버릭');
INSERT INTO SCREENING VALUES(48, TO_Date('2023-07-21 22:00:00', 'yyyy-mm-dd hh24:mi:ss'), '7', '탑건_매버릭');

--8
INSERT INTO SCREENING VALUES(49, TO_Date('2023-07-21 8:00:00', 'yyyy-mm-dd hh24:mi:ss'), '8', '타짜:신의손');
INSERT INTO SCREENING VALUES(50, TO_Date('2023-07-21 11:00:00', 'yyyy-mm-dd hh24:mi:ss'), '8', '타짜:신의손');
INSERT INTO SCREENING VALUES(51, TO_Date('2023-07-21 15:00:00', 'yyyy-mm-dd hh24:mi:ss'), '8', '타짜:신의손');
INSERT INTO SCREENING VALUES(52, TO_Date('2023-07-21 18:00:00', 'yyyy-mm-dd hh24:mi:ss'), '8', '타짜:신의손');
INSERT INTO SCREENING VALUES(53, TO_Date('2023-07-21 21:00:00', 'yyyy-mm-dd hh24:mi:ss'), '8', '타짜:신의손');


--9
INSERT INTO SCREENING VALUES(54, TO_Date('2023-07-21 7:30:00', 'yyyy-mm-dd hh24:mi:ss'), '9', '신세계');
INSERT INTO SCREENING VALUES(55, TO_Date('2023-07-21 10:00:00', 'yyyy-mm-dd hh24:mi:ss'), '9', '신세계');
INSERT INTO SCREENING VALUES(56, TO_Date('2023-07-21 12:30:00', 'yyyy-mm-dd hh24:mi:ss'), '9', '신세계');
INSERT INTO SCREENING VALUES(57, TO_Date('2023-07-21 15:00:00', 'yyyy-mm-dd hh24:mi:ss'), '9', '신세계');
INSERT INTO SCREENING VALUES(58, TO_Date('2023-07-21 17:30:00', 'yyyy-mm-dd hh24:mi:ss'), '9', '신세계');
INSERT INTO SCREENING VALUES(59, TO_Date('2023-07-21 20:00:00', 'yyyy-mm-dd hh24:mi:ss'), '9', '신세계');
INSERT INTO SCREENING VALUES(60, TO_Date('2023-07-21 22:30:00', 'yyyy-mm-dd hh24:mi:ss'), '9', '신세계');

--10
INSERT INTO SCREENING VALUES(61, TO_Date('2023-07-21 6:00:00', 'yyyy-mm-dd hh24:mi:ss'), '10', '아저씨');
INSERT INTO SCREENING VALUES(62, TO_Date('2023-07-21 9:00:00', 'yyyy-mm-dd hh24:mi:ss'), '10', '아저씨');
INSERT INTO SCREENING VALUES(63, TO_Date('2023-07-21 12:00:00', 'yyyy-mm-dd hh24:mi:ss'), '10', '아저씨');
INSERT INTO SCREENING VALUES(64, TO_Date('2023-07-21 15:00:00', 'yyyy-mm-dd hh24:mi:ss'), '10', '아저씨');
INSERT INTO SCREENING VALUES(65, TO_Date('2023-07-21 18:00:00', 'yyyy-mm-dd hh24:mi:ss'), '10', '아저씨');
INSERT INTO SCREENING VALUES(66, TO_Date('2023-07-21 21:00:00', 'yyyy-mm-dd hh24:mi:ss'), '10', '아저씨');
INSERT INTO SCREENING VALUES(67, TO_Date('2023-07-21 23:30:00', 'yyyy-mm-dd hh24:mi:ss'), '10', '아저씨');




/* Create Foreign Keys */

ALTER TABLE RESERVATION
	ADD FOREIGN KEY (CustNo)
	REFERENCES CUSTOMER (CustNo)
;


ALTER TABLE FOOD_BUY
	ADD FOREIGN KEY (FoodName)
	REFERENCES FOOD (FoodNo)
;


ALTER TABLE RESERVATION
	ADD FOREIGN KEY (MovieTitle)
	REFERENCES MOVIE (MovieNo)
;


ALTER TABLE SCREENING
	ADD FOREIGN KEY (MovieTitle)
	REFERENCES MOVIE (MovieNo)
;


ALTER TABLE RESERVATION
	ADD FOREIGN KEY (ScreenNo)
	REFERENCES SCREENING (ScreenNo)
;
