
/* Drop Tables */

DROP TABLE Reservation CASCADE CONSTRAINTS;
DROP TABLE Customer CASCADE CONSTRAINTS;
DROP TABLE Food CASCADE CONSTRAINTS;
DROP TABLE Screening CASCADE CONSTRAINTS;
DROP TABLE Movie CASCADE CONSTRAINTS;

-- create sequence
DROP SEQUENCE reserseq;

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
	MoiveNo varchar2(10) NOT NULL,
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

SELECT r1.screendate, r.ScreenNo, r.reserseat, r.MovieTitle 
FROM reservation r 
JOIN Screening r1 
ON r.MovieTitle = r1.MovieTitle 
WHERE r.ReserNo = '1211341924142196';

SELECT ScreenDate
FROM Screening S
JOIN RESERVATION R
ON S.ScreenNo = R.ScreenNo 
WHERE ReserNo = '1211341924142199';



CREATE TABLE Reservation
(
	ReserNo varchar2(16) NOT NULL, -- 예약번호는 꼭 16자리여야만 함!!!!!!!!
	ReserDate date NOT NULL,
	ReserSeat varchar2(10) NOT NULL,
	MovieTitle varchar2(100) NOT NULL,
	CustNo number, --회원
	ScreenNo number NOT NULL,
	PRIMARY KEY (ReserNo)
);
INSERT INTO  Reservation VALUES(reserseq.nextval ,'2018-04-25','A05','신세계' , 2 , 5);
INSERT INTO  Reservation VALUES(reserseq.nextval ,'2018-11-17','B03','19곰테드' , 1 , 4);
INSERT INTO  Reservation VALUES(reserseq.nextval ,'2019-07-24','C02','아저씨' , 3 , 1);
INSERT INTO  Reservation VALUES(reserseq.nextval ,'2020-07-21','D05','국제시장' , 4 , 10);
INSERT INTO  Reservation VALUES(reserseq.nextval ,'2020-04-03','A03','신세계' , 3 , 11);
INSERT INTO  Reservation VALUES(reserseq.nextval ,'2022-03-10','C01','타짜:신의손' , 4 , 9);
INSERT INTO  Reservation VALUES(reserseq.nextval ,'2019-05-22','B04','신세계' , 4 , 5);


SELECT * FROM Reservation;
SELECT * FROM Screening;



CREATE TABLE Screening
(
	ScreenNo number NOT NULL,
	ScreenDate date NOT NULL,
	ScreenTheater varchar2(5) NOT NULL,
	MovieTitle varchar2(100) NOT NULL,
	PRIMARY KEY (ScreenNo)
);
-- 1상영관
INSERT INTO SCREENING VALUES(1,TO_Date('2022-07-21 10:00:00','yyyy-mm-dd hh24:mi:ss'),'1','미션임파서블_데드레코닝(4DX)');
INSERT INTO SCREENING VALUES(2,TO_Date('2022-07-21 13:00:00','yyyy-mm-dd hh24:mi:ss'),'1','엘리멘탈(더빙)');
INSERT INTO SCREENING VALUES(3,TO_Date('2022-07-21 16:00:00','yyyy-mm-dd hh24:mi:ss'),'1','스파이더맨ACU');
INSERT INTO SCREENING VALUES(4,TO_Date('2022-07-21 19:00:00','yyyy-mm-dd hh24:mi:ss'),'1','엘리멘탈');
INSERT INTO SCREENING VALUES(5,TO_Date('2022-07-21 22:00:00','yyyy-mm-dd hh24:mi:ss'),'1','스파이더맨ACU(4DX)');
INSERT INTO SCREENING VALUES(6,TO_Date('2022-07-22 10:00:00','yyyy-mm-dd hh24:mi:ss'),'1','여름날우리(더빙)');
INSERT INTO SCREENING VALUES(7,TO_Date('2022-07-22 13:00:00','yyyy-mm-dd hh24:mi:ss'),'1','미션임파서블_데드레코닝(4DX)');
INSERT INTO SCREENING VALUES(8,TO_Date('2022-07-22 16:00:00','yyyy-mm-dd hh24:mi:ss'),'1','엘리멘탈(더빙)');
INSERT INTO SCREENING VALUES(9,TO_Date('2022-07-22 19:00:00','yyyy-mm-dd hh24:mi:ss'),'1','엘리멘탈');
INSERT INTO SCREENING VALUES(10,TO_Date('2022-07-22 22:00:00','yyyy-mm-dd hh24:mi:ss'),'1','스파이더맨ACU');
INSERT INTO SCREENING VALUES(11,TO_Date('2022-07-23 10:00:00','yyyy-mm-dd hh24:mi:ss'),'1','여름날우리(더빙)');
INSERT INTO SCREENING VALUES(12,TO_Date('2022-07-23 13:00:00','yyyy-mm-dd hh24:mi:ss'),'1','스파이더맨ACU(4DX)');
INSERT INTO SCREENING VALUES(13,TO_Date('2022-07-23 16:00:00','yyyy-mm-dd hh24:mi:ss'),'1','엘리멘탈(더빙)');
INSERT INTO SCREENING VALUES(14,TO_Date('2022-07-23 19:00:00','yyyy-mm-dd hh24:mi:ss'),'1','미션임파서블_데드레코닝(4DX)');
INSERT INTO SCREENING VALUES(15,TO_Date('2022-07-23 22:00:00','yyyy-mm-dd hh24:mi:ss'),'1','엘리멘탈(더빙)');
INSERT INTO SCREENING VALUES(16,TO_Date('2022-07-24 10:00:00','yyyy-mm-dd hh24:mi:ss'),'1','여름날우리(더빙)');
INSERT INTO SCREENING VALUES(17,TO_Date('2022-07-24 13:00:00','yyyy-mm-dd hh24:mi:ss'),'1','스파이더맨ACU(4DX)');
INSERT INTO SCREENING VALUES(18,TO_Date('2022-07-24 16:00:00','yyyy-mm-dd hh24:mi:ss'),'1','스파이더맨ACU');
INSERT INTO SCREENING VALUES(19,TO_Date('2022-07-24 19:00:00','yyyy-mm-dd hh24:mi:ss'),'1','엘리멘탈');
INSERT INTO SCREENING VALUES(20,TO_Date('2022-07-24 22:00:00','yyyy-mm-dd hh24:mi:ss'),'1','미션임파서블_데드레코닝(4DX)');
-- 2상영관
INSERT INTO SCREENING VALUES(21,TO_Date('2022-07-21 10:00:00','yyyy-mm-dd hh24:mi:ss'),'2','인디아나존스5');
INSERT INTO SCREENING VALUES(22,TO_Date('2022-07-21 13:00:00','yyyy-mm-dd hh24:mi:ss'),'2','인디아나존스5(4DX)');
INSERT INTO SCREENING VALUES(23,TO_Date('2022-07-21 16:00:00','yyyy-mm-dd hh24:mi:ss'),'2','악마들');
INSERT INTO SCREENING VALUES(24,TO_Date('2022-07-21 19:00:00','yyyy-mm-dd hh24:mi:ss'),'2','애스터로이드시티');
INSERT INTO SCREENING VALUES(25,TO_Date('2022-07-21 22:00:00','yyyy-mm-dd hh24:mi:ss'),'2','트랜스포머(3D)');
INSERT INTO SCREENING VALUES(26,TO_Date('2022-07-22 10:00:00','yyyy-mm-dd hh24:mi:ss'),'2','범죄도시3');
INSERT INTO SCREENING VALUES(27,TO_Date('2022-07-22 13:00:00','yyyy-mm-dd hh24:mi:ss'),'2','인디아나존스5');
INSERT INTO SCREENING VALUES(28,TO_Date('2022-07-22 16:00:00','yyyy-mm-dd hh24:mi:ss'),'2','인디아나존스5(4DX)');
INSERT INTO SCREENING VALUES(29,TO_Date('2022-07-22 19:00:00','yyyy-mm-dd hh24:mi:ss'),'2','악마들');
INSERT INTO SCREENING VALUES(30,TO_Date('2022-07-22 22:00:00','yyyy-mm-dd hh24:mi:ss'),'2','애스터로이드시티');
INSERT INTO SCREENING VALUES(31,TO_Date('2022-07-23 10:00:00','yyyy-mm-dd hh24:mi:ss'),'2','트랜스포머(3D)');
INSERT INTO SCREENING VALUES(32,TO_Date('2022-07-23 13:00:00','yyyy-mm-dd hh24:mi:ss'),'2','범죄도시3');
INSERT INTO SCREENING VALUES(33,TO_Date('2022-07-23 16:00:00','yyyy-mm-dd hh24:mi:ss'),'2','인디아나존스5');
INSERT INTO SCREENING VALUES(34,TO_Date('2022-07-23 19:00:00','yyyy-mm-dd hh24:mi:ss'),'2','인디아나존스5(4DX)');
INSERT INTO SCREENING VALUES(35,TO_Date('2022-07-23 22:00:00','yyyy-mm-dd hh24:mi:ss'),'2','악마들');
INSERT INTO SCREENING VALUES(36,TO_Date('2022-07-24 10:00:00','yyyy-mm-dd hh24:mi:ss'),'2','애스터로이드시티');
INSERT INTO SCREENING VALUES(37,TO_Date('2022-07-24 13:00:00','yyyy-mm-dd hh24:mi:ss'),'2','트랜스포머(3D)');
INSERT INTO SCREENING VALUES(38,TO_Date('2022-07-24 16:00:00','yyyy-mm-dd hh24:mi:ss'),'2','범죄도시3');
INSERT INTO SCREENING VALUES(39,TO_Date('2022-07-24 19:00:00','yyyy-mm-dd hh24:mi:ss'),'2','인디아나존스5');
INSERT INTO SCREENING VALUES(40,TO_Date('2022-07-24 22:00:00','yyyy-mm-dd hh24:mi:ss'),'2','인디아나존스5(4DX)');
-- 3상영관
INSERT INTO SCREENING VALUES(41,TO_Date('2022-07-21 10:00:00','yyyy-mm-dd hh24:mi:ss'),'3','좋댓구');
INSERT INTO SCREENING VALUES(42,TO_Date('2022-07-21 13:00:00','yyyy-mm-dd hh24:mi:ss'),'3','탑건_매버릭');
INSERT INTO SCREENING VALUES(43,TO_Date('2022-07-21 16:00:00','yyyy-mm-dd hh24:mi:ss'),'3','탑건_매버릭(4DX)');
INSERT INTO SCREENING VALUES(44,TO_Date('2022-07-21 19:00:00','yyyy-mm-dd hh24:mi:ss'),'3','명량');
INSERT INTO SCREENING VALUES(45,TO_Date('2022-07-21 22:00:00','yyyy-mm-dd hh24:mi:ss'),'3','극한직업');
INSERT INTO SCREENING VALUES(46,TO_Date('2022-07-22 10:00:00','yyyy-mm-dd hh24:mi:ss'),'3','신과함께:죄와 벌');
INSERT INTO SCREENING VALUES(47,TO_Date('2022-07-22 13:00:00','yyyy-mm-dd hh24:mi:ss'),'3','좋댓구');
INSERT INTO SCREENING VALUES(48,TO_Date('2022-07-22 16:00:00','yyyy-mm-dd hh24:mi:ss'),'3','탑건_매버릭');
INSERT INTO SCREENING VALUES(49,TO_Date('2022-07-22 19:00:00','yyyy-mm-dd hh24:mi:ss'),'3','탑건_매버릭(4DX)');
INSERT INTO SCREENING VALUES(50,TO_Date('2022-07-22 22:00:00','yyyy-mm-dd hh24:mi:ss'),'3','명량');
INSERT INTO SCREENING VALUES(51,TO_Date('2022-07-23 10:00:00','yyyy-mm-dd hh24:mi:ss'),'3','극한직업');
INSERT INTO SCREENING VALUES(52,TO_Date('2022-07-23 13:00:00','yyyy-mm-dd hh24:mi:ss'),'3','신과함께:죄와 벌');
INSERT INTO SCREENING VALUES(53,TO_Date('2022-07-23 16:00:00','yyyy-mm-dd hh24:mi:ss'),'3','좋댓구');
INSERT INTO SCREENING VALUES(54,TO_Date('2022-07-23 19:00:00','yyyy-mm-dd hh24:mi:ss'),'3','탑건_매버릭');
INSERT INTO SCREENING VALUES(55,TO_Date('2022-07-23 22:00:00','yyyy-mm-dd hh24:mi:ss'),'3','탑건_매버릭(4DX)');
INSERT INTO SCREENING VALUES(56,TO_Date('2022-07-24 10:00:00','yyyy-mm-dd hh24:mi:ss'),'3','명량');
INSERT INTO SCREENING VALUES(57,TO_Date('2022-07-24 13:00:00','yyyy-mm-dd hh24:mi:ss'),'3','극한직업');
INSERT INTO SCREENING VALUES(58,TO_Date('2022-07-24 16:00:00','yyyy-mm-dd hh24:mi:ss'),'3','신과함께:죄와 벌');
INSERT INTO SCREENING VALUES(59,TO_Date('2022-07-24 19:00:00','yyyy-mm-dd hh24:mi:ss'),'3','좋댓구');
INSERT INTO SCREENING VALUES(60,TO_Date('2022-07-24 22:00:00','yyyy-mm-dd hh24:mi:ss'),'3','명량');
-- 4상영관
INSERT INTO SCREENING VALUES(61,TO_Date('2022-07-21 10:00:00','yyyy-mm-dd hh24:mi:ss'),'4','아바타');
INSERT INTO SCREENING VALUES(62,TO_Date('2022-07-21 13:00:00','yyyy-mm-dd hh24:mi:ss'),'4','어벤져스:엔드게임');
INSERT INTO SCREENING VALUES(63,TO_Date('2022-07-21 16:00:00','yyyy-mm-dd hh24:mi:ss'),'4','어벤져스:엔드게임(4DX)');
INSERT INTO SCREENING VALUES(64,TO_Date('2022-07-21 19:00:00','yyyy-mm-dd hh24:mi:ss'),'4','어벤져스:엔드게임(더빙)');
INSERT INTO SCREENING VALUES(65,TO_Date('2022-07-21 22:00:00','yyyy-mm-dd hh24:mi:ss'),'4','아바타(4DX)');
INSERT INTO SCREENING VALUES(66,TO_Date('2022-07-22 10:00:00','yyyy-mm-dd hh24:mi:ss'),'4','아바타(더빙)');
INSERT INTO SCREENING VALUES(67,TO_Date('2022-07-22 13:00:00','yyyy-mm-dd hh24:mi:ss'),'4','아바타');
INSERT INTO SCREENING VALUES(68,TO_Date('2022-07-22 16:00:00','yyyy-mm-dd hh24:mi:ss'),'4','어벤져스:엔드게임');
INSERT INTO SCREENING VALUES(69,TO_Date('2022-07-22 19:00:00','yyyy-mm-dd hh24:mi:ss'),'4','어벤져스:엔드게임(4DX)');
INSERT INTO SCREENING VALUES(70,TO_Date('2022-07-22 22:00:00','yyyy-mm-dd hh24:mi:ss'),'4','어벤져스:엔드게임(더빙)');
INSERT INTO SCREENING VALUES(71,TO_Date('2022-07-23 10:00:00','yyyy-mm-dd hh24:mi:ss'),'4','아바타(4DX)');
INSERT INTO SCREENING VALUES(72,TO_Date('2022-07-23 13:00:00','yyyy-mm-dd hh24:mi:ss'),'4','아바타(더빙)');
INSERT INTO SCREENING VALUES(73,TO_Date('2022-07-23 16:00:00','yyyy-mm-dd hh24:mi:ss'),'4','아바타');
INSERT INTO SCREENING VALUES(74,TO_Date('2022-07-23 19:00:00','yyyy-mm-dd hh24:mi:ss'),'4','어벤져스:엔드게임');
INSERT INTO SCREENING VALUES(75,TO_Date('2022-07-23 22:00:00','yyyy-mm-dd hh24:mi:ss'),'4','어벤져스:엔드게임(4DX)');
INSERT INTO SCREENING VALUES(76,TO_Date('2022-07-24 10:00:00','yyyy-mm-dd hh24:mi:ss'),'4','어벤져스:엔드게임(더빙)');
INSERT INTO SCREENING VALUES(77,TO_Date('2022-07-24 13:00:00','yyyy-mm-dd hh24:mi:ss'),'4','아바타(4DX)');
INSERT INTO SCREENING VALUES(78,TO_Date('2022-07-24 16:00:00','yyyy-mm-dd hh24:mi:ss'),'4','아바타(더빙)');
INSERT INTO SCREENING VALUES(79,TO_Date('2022-07-24 19:00:00','yyyy-mm-dd hh24:mi:ss'),'4','아바타(더빙)');
INSERT INTO SCREENING VALUES(80,TO_Date('2022-07-24 22:00:00','yyyy-mm-dd hh24:mi:ss'),'4','아바타(더빙)');
-- 5상영관
INSERT INTO SCREENING VALUES(81,TO_Date('2022-07-21 10:00:00','yyyy-mm-dd hh24:mi:ss'),'5','국제시장');
INSERT INTO SCREENING VALUES(82,TO_Date('2022-07-21 13:00:00','yyyy-mm-dd hh24:mi:ss'),'5','어벤져스:인피니티워(더빙)');
INSERT INTO SCREENING VALUES(83,TO_Date('2022-07-21 16:00:00','yyyy-mm-dd hh24:mi:ss'),'5','어벤져스:인피니티워');
INSERT INTO SCREENING VALUES(84,TO_Date('2022-07-21 19:00:00','yyyy-mm-dd hh24:mi:ss'),'5','어벤져스:인피니티워(4DX)');
INSERT INTO SCREENING VALUES(85,TO_Date('2022-07-21 22:00:00','yyyy-mm-dd hh24:mi:ss'),'5','검사외전');
INSERT INTO SCREENING VALUES(86,TO_Date('2022-07-22 10:00:00','yyyy-mm-dd hh24:mi:ss'),'5','아저씨');
INSERT INTO SCREENING VALUES(87,TO_Date('2022-07-22 13:00:00','yyyy-mm-dd hh24:mi:ss'),'5','국제시장');
INSERT INTO SCREENING VALUES(88,TO_Date('2022-07-22 16:00:00','yyyy-mm-dd hh24:mi:ss'),'5','어벤져스:인피니티워(더빙)');
INSERT INTO SCREENING VALUES(89,TO_Date('2022-07-22 19:00:00','yyyy-mm-dd hh24:mi:ss'),'5','어벤져스:인피니티워');
INSERT INTO SCREENING VALUES(90,TO_Date('2022-07-22 22:00:00','yyyy-mm-dd hh24:mi:ss'),'5','어벤져스:인피니티워(4DX)');
INSERT INTO SCREENING VALUES(91,TO_Date('2022-07-23 10:00:00','yyyy-mm-dd hh24:mi:ss'),'5','검사외전');
INSERT INTO SCREENING VALUES(92,TO_Date('2022-07-23 13:00:00','yyyy-mm-dd hh24:mi:ss'),'5','아저씨');
INSERT INTO SCREENING VALUES(93,TO_Date('2022-07-23 16:00:00','yyyy-mm-dd hh24:mi:ss'),'5','국제시장');
INSERT INTO SCREENING VALUES(94,TO_Date('2022-07-23 19:00:00','yyyy-mm-dd hh24:mi:ss'),'5','어벤져스:인피니티워(더빙)');
INSERT INTO SCREENING VALUES(95,TO_Date('2022-07-23 22:00:00','yyyy-mm-dd hh24:mi:ss'),'5','어벤져스:인피니티워');
INSERT INTO SCREENING VALUES(96,TO_Date('2022-07-24 10:00:00','yyyy-mm-dd hh24:mi:ss'),'5','어벤져스:인피니티워(4DX)');
INSERT INTO SCREENING VALUES(97,TO_Date('2022-07-24 13:00:00','yyyy-mm-dd hh24:mi:ss'),'5','검사외전');
INSERT INTO SCREENING VALUES(98,TO_Date('2022-07-24 16:00:00','yyyy-mm-dd hh24:mi:ss'),'5','아저씨');
INSERT INTO SCREENING VALUES(99,TO_Date('2022-07-24 19:00:00','yyyy-mm-dd hh24:mi:ss'),'5','국제시장');
INSERT INTO SCREENING VALUES(100,TO_Date('2022-07-24 22:00:00','yyyy-mm-dd hh24:mi:ss'),'5','검사외전');
-- 6상영관
INSERT INTO SCREENING VALUES(101,TO_Date('2022-07-21 10:00:00','yyyy-mm-dd hh24:mi:ss'),'6','스파이더맨:파프롬홈');
INSERT INTO SCREENING VALUES(102,TO_Date('2022-07-21 13:00:00','yyyy-mm-dd hh24:mi:ss'),'6','스파이더맨:파프롬홈(더빙)');
INSERT INTO SCREENING VALUES(103,TO_Date('2022-07-21 16:00:00','yyyy-mm-dd hh24:mi:ss'),'6','스파이더맨:파프롬홈(4DX)');
INSERT INTO SCREENING VALUES(104,TO_Date('2022-07-21 19:00:00','yyyy-mm-dd hh24:mi:ss'),'6','타짜:신의손');
INSERT INTO SCREENING VALUES(105,TO_Date('2022-07-21 22:00:00','yyyy-mm-dd hh24:mi:ss'),'6','신세계');
INSERT INTO SCREENING VALUES(106,TO_Date('2022-07-22 10:00:00','yyyy-mm-dd hh24:mi:ss'),'6','19곰테드');
INSERT INTO SCREENING VALUES(107,TO_Date('2022-07-22 13:00:00','yyyy-mm-dd hh24:mi:ss'),'6','스파이더맨:파프롬홈');
INSERT INTO SCREENING VALUES(108,TO_Date('2022-07-22 16:00:00','yyyy-mm-dd hh24:mi:ss'),'6','스파이더맨:파프롬홈(더빙)');
INSERT INTO SCREENING VALUES(109,TO_Date('2022-07-22 19:00:00','yyyy-mm-dd hh24:mi:ss'),'6','스파이더맨:파프롬홈(4DX)');
INSERT INTO SCREENING VALUES(110,TO_Date('2022-07-22 22:00:00','yyyy-mm-dd hh24:mi:ss'),'6','타짜:신의손');
INSERT INTO SCREENING VALUES(111,TO_Date('2022-07-23 10:00:00','yyyy-mm-dd hh24:mi:ss'),'6','신세계');
INSERT INTO SCREENING VALUES(112,TO_Date('2022-07-23 13:00:00','yyyy-mm-dd hh24:mi:ss'),'6','19곰테드');
INSERT INTO SCREENING VALUES(113,TO_Date('2022-07-23 16:00:00','yyyy-mm-dd hh24:mi:ss'),'6','스파이더맨:파프롬홈');
INSERT INTO SCREENING VALUES(114,TO_Date('2022-07-23 19:00:00','yyyy-mm-dd hh24:mi:ss'),'6','스파이더맨:파프롬홈(더빙)');
INSERT INTO SCREENING VALUES(115,TO_Date('2022-07-23 22:00:00','yyyy-mm-dd hh24:mi:ss'),'6','스파이더맨:파프롬홈(4DX)');
INSERT INTO SCREENING VALUES(116,TO_Date('2022-07-24 10:00:00','yyyy-mm-dd hh24:mi:ss'),'6','타짜:신의손');
INSERT INTO SCREENING VALUES(117,TO_Date('2022-07-24 13:00:00','yyyy-mm-dd hh24:mi:ss'),'6','신세계');
INSERT INTO SCREENING VALUES(118,TO_Date('2022-07-24 16:00:00','yyyy-mm-dd hh24:mi:ss'),'6','19곰테드');
INSERT INTO SCREENING VALUES(119,TO_Date('2022-07-24 19:00:00','yyyy-mm-dd hh24:mi:ss'),'6','스파이더맨:파프롬홈');
INSERT INTO SCREENING VALUES(120,TO_Date('2022-07-24 22:00:00','yyyy-mm-dd hh24:mi:ss'),'6','스파이더맨:파프롬홈(4DX)');


/* Create Foreign Keys */

ALTER TABLE Reservation
	ADD FOREIGN KEY (CustNo)
	REFERENCES Customer (CustNo)
;


ALTER TABLE Reservation
	ADD FOREIGN KEY (MovieTitle)
	REFERENCES Movie (MovieTitle)
;


ALTER TABLE Screening
	ADD FOREIGN KEY (MovieTitle)
	REFERENCES Movie (MovieTitle)
;


ALTER TABLE Reservation
	ADD FOREIGN KEY (ScreenNo)
	REFERENCES Screening (ScreenNo)
;



