/* Drop Tables */

DROP TABLE Reservation CASCADE CONSTRAINTS;
DROP TABLE Customer CASCADE CONSTRAINTS;
DROP TABLE Food CASCADE CONSTRAINTS;
DROP TABLE Screening CASCADE CONSTRAINTS;
DROP TABLE Movie CASCADE CONSTRAINTS;

-- create sequence


CREATE SEQUENCE reserseq START WITH 1211341924142181; --회원번호 시퀀스 설정
CREATE SEQUENCE cusseq START WITH 5; --회원번호 시퀀스 설정



/* Create Tables */

CREATE TABLE Customer
(
	CustNo number NOT NULL,
	CustName varchar2(10) NOT NULL,
	CustPhone varchar2(20) NOT NULL UNIQUE,
	CustPoint number,
	PRIMARY KEY (CustNo)
);

-- 손님 테이블
INSERT INTO Customer VALUES (1,'이소정',010-6689-1543,9999);
INSERT INTO Customer VALUES (2,'채율',010-1578-5483,5000);
INSERT INTO Customer VALUES (3,'김민섭',010-2874-7791,2400);
INSERT INTO Customer VALUES (4,'박상현',010-1748-4832,8000);

SELECT * FROM CUSTOMER;


CREATE TABLE Food
(
	FoodNo number NOT NULL,
	FoodCate varchar2(20) NOT NULL,
	FoodName varchar2(30) NOT NULL,
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

CREATE TABLE Movie
(
	MovieNo varchar2(10) NOT NULL,
	MovieTitle varchar2(100) NOT NULL UNIQUE,
	MovieDate date NOT NULL,
	MovieGrade varchar2(100) NOT NULL,
	MoviePrice number NOT NULL,
	PRIMARY KEY (MoiveNo)
);

--3D는 18000원 4dx는 36000원 2D는 15000원
INSERT INTO  Movie VALUES('ac1234' ,'미션임파서블_데드레코닝(4DX)' ,'2022-03-17','15세' ,36000);
INSERT INTO  Movie VALUES('ani5743' ,'엘리멘탈' ,'2022-04-10' , '전체' , 15000);
INSERT INTO  Movie VALUES('ani5747' ,'엘리멘탈(더빙)' ,'2023-07-12' , '전체' , 15000);
INSERT INTO  Movie VALUES('ani9797' ,'스파이더맨ACU' ,'2023-06-21' , '전체' , 15000);
INSERT INTO  Movie VALUES('ani9898' ,'스파이더맨ACU(4DX)' ,'2023-06-21' , '전체' , 36000);
INSERT INTO  Movie VALUES('ac1651' ,'인디아나존스5' ,'2023-06-28' , '12세' , 15000);
INSERT INTO  Movie VALUES('ac1652' ,'인디아나존스5(4DX)' ,'2023-06-28' , '12세' , 36000);
INSERT INTO  Movie VALUES('sr6666' ,'악마들' ,'2023-07-05' , '청불' , 15000);
INSERT INTO  Movie VALUES('ro5959' ,'여름날우리(더빙)' ,'2023-06-28' , '12세' , 15000);
INSERT INTO  Movie VALUES('ac1235' ,'애스터로이드시티' ,'2023-06-28' , '12세' , 15000);
INSERT INTO  Movie VALUES('sf1597' ,'트랜스포머(3D)' ,'2023-06-06' , '12세' , 18000);
INSERT INTO  Movie VALUES('ac8878' ,'범죄도시3' ,'2023-05-31' , '15세' , 15000);
INSERT INTO  Movie VALUES('co2215' ,'좋댓구' ,'2023-07-12' , '15세' , 15000);
INSERT INTO  Movie VALUES('ac5724' ,'탑건_매버릭' ,'2023-07-12' , '15세' , 15000);
INSERT INTO  Movie VALUES('ac5714' ,'탑건_매버릭(4DX)' ,'2023-07-12' , '15세' , 36000);
INSERT INTO  Movie VALUES('ac5792' ,'명량' ,'2014-07-30' , '15세' , 15000);
INSERT INTO  Movie VALUES('co0730' ,'극한직업' ,'2019-01-23' , '15세' , 15000);
INSERT INTO  Movie VALUES('co1220' ,'신과함께:죄와 벌' ,'2017-12-20' , '15세' , 15000);
INSERT INTO  Movie VALUES('co1217' ,'국제시장' ,'2014-12-17' , '15세' , 15000);
INSERT INTO  Movie VALUES('sf0424' ,'어벤져스:엔드게임' ,'2019-04-24' , '15세' , 15000);
INSERT INTO  Movie VALUES('sf0427' ,'어벤져스:엔드게임(4DX)' ,'2019-04-24' , '15세' , 36000);
INSERT INTO  Movie VALUES('sf0426' ,'어벤져스:엔드게임(더빙)' ,'2019-04-24' , '15세' , 15000);
INSERT INTO  Movie VALUES('sf0827' ,'아바타(4DX)' ,'2019-12-17' , '15세' , 36000);
INSERT INTO  Movie VALUES('sf1217' ,'아바타(더빙)' ,'2019-12-17' , '15세' , 15000);
INSERT INTO  Movie VALUES('sf0416' ,'아바타' ,'2019-12-17' , '15세' , 15000);
INSERT INTO  Movie VALUES('sf0725' ,'어벤져스:인피니티워(더빙)' ,'2018-04-25' , '15세' , 15000);
INSERT INTO  Movie VALUES('sf1426' ,'어벤져스:인피니티워' ,'2018-04-25' , '15세' , 15000);
INSERT INTO  Movie VALUES('sf8427' ,'어벤져스:인피니티워(4DX)' ,'2018-04-25' , '15세' , 36000);
INSERT INTO  Movie VALUES('co2003' ,'검사외전' ,'2016-02-03' , '15세' , 15000);
INSERT INTO  Movie VALUES('co2019' ,'스파이더맨:파프롬홈' ,'2019-07-02' , '15세' , 15000);
INSERT INTO  Movie VALUES('co2719' ,'스파이더맨:파프롬홈(더빙)' ,'2019-07-02' , '15세' , 15000);
INSERT INTO  Movie VALUES('co2319' ,'스파이더맨:파프롬홈(4DX)' ,'2019-07-02' , '15세' , 36000);
INSERT INTO  Movie VALUES('sr3119' ,'타짜:신의손' ,'2014-05-19' , '청불' , 15000);
INSERT INTO  Movie VALUES('sr3319' ,'신세계' ,'2020-06-21' , '청불' , 15000);
INSERT INTO  Movie VALUES('sr3291' ,'19곰테드' ,'2022-05-08' , '청불' , 15000);
INSERT INTO  Movie VALUES('sr3110' ,'아저씨' ,'2019-05-14' , '청불' , 15000);


SELECT * FROM movie;

--키오스크에서 사용자가 조회할 기능
SELECT r1.screendate, r.ScreenNo, r.reserseat, r.MovieTitle 
FROM reservation r 
JOIN Screening r1 
ON r.MovieTitle = r1.MovieTitle 
WHERE r.ReserNo = 1;

--관리자가 조회할 기능
SELECT r.MovieTitle, COUNT(r.MovieTitle), SUM(m.MoviePrice) AS MoviePrice
FROM Reservation r
JOIN Movie m ON r.MovieTitle = m.MovieTitle
GROUP BY r.MovieTitle;

CREATE TABLE Reservation
(
	ReserNo varchar2(16) NOT NULL, -- 예약번호는 꼭 16자리여야만 함!!!!!!!!
	ReserDate date NOT NULL,
	ReserSeat varchar2(10) NOT NULL,
	MovieTitle varchar2(100) NOT NULL,
	CustNo number NOT NULL,
	ScreenNo number NOT NULL,
	PRIMARY KEY (ReserNo)
);
INSERT INTO  Reservation VALUES(reserseq.nextval ,'2018-04-25','A05','신세계' , 2 , 5);
INSERT INTO  Reservation VALUES(reserseq.nextval ,'2018-11-17','B03','19곰테드' , 1 , 4);
INSERT INTO  Reservation VALUES(reserseq.nextval ,'2019-07-24','C02','아저씨' , 3 , 1);
INSERT INTO  Reservation VALUES(reserseq.nextval ,'2020-07-21','D05','국제시장' , 4 , 10);
INSERT INTO  Reservation VALUES(reserseq.nextval ,'2020-04-03','A03','신세계' , 3 , 11);
INSERT INTO  Reservation VALUES(reserseq.nextval ,'2020-04-03','A03','신세계' , 3 , 11);
INSERT INTO  Reservation VALUES(reserseq.nextval ,'2020-04-03','A03','신세계' , 3 , 11);
INSERT INTO  Reservation VALUES(reserseq.nextval ,'2022-03-10','C01','타짜:신의손' , 4 , 9);
INSERT INTO  Reservation VALUES(reserseq.nextval ,'2019-05-22','B04','신세계' , 4 , 5);


SELECT * FROM Reservation;



CREATE TABLE Screening
(
	ScreenNo number NOT NULL,
	ScreenDate date NOT NULL,
	ScreenTheater varchar2(5) NOT NULL,
	MovieTitle varchar2(100) NOT NULL,
	PRIMARY KEY (ScreenNo)
);



/* Create Foreign Keys */

ALTER TABLE Reservation
	ADD FOREIGN KEY (CustNo)
	REFERENCES Customer (CustNo)
;


ALTER TABLE Reservation
	ADD FOREIGN KEY (MoiveTitle)
	REFERENCES Movie (MoiveTitle)
;


ALTER TABLE Screening
	ADD FOREIGN KEY (MoiveTitle)
	REFERENCES Movie (MoiveTitle)
;


ALTER TABLE Reservation
	ADD FOREIGN KEY (ScreenNo)
	REFERENCES Screening (ScreenNo)
;
