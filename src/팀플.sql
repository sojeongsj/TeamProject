
/* Drop Tables */

DROP TABLE Reservation CASCADE CONSTRAINTS;
DROP TABLE Customer CASCADE CONSTRAINTS;
DROP TABLE Food CASCADE CONSTRAINTS;
DROP TABLE Screening CASCADE CONSTRAINTS;
DROP TABLE Movie CASCADE CONSTRAINTS;

-- create sequence
CREATE SEQUENCE reserseq START WITH 10001;


/* Create Tables */

CREATE TABLE Customer
(
	CustNo number NOT NULL,
	CustName varchar2(10) NOT NULL,
	CustPhone varchar2(20) NOT NULL UNIQUE,
	CustPoint number,
	PRIMARY KEY (CustNo)
);


CREATE TABLE Food
(
	FoodNo number NOT NULL,
	FoodCate varchar2(20) NOT NULL,
	FoodName varchar2(20) NOT NULL,
	FoodPrice number NOT NULL,
	PRIMARY KEY (FoodNo)
);


CREATE TABLE Movie
(
	MoiveNo varchar2(10) NOT NULL,
	MovieTitle varchar2(20) NOT NULL,
	MovieDate date NOT NULL,
	MovieGrade varchar2(10) NOT NULL,
	MoviePrice number NOT NULL,
	PRIMARY KEY (MoiveNo)
);


CREATE TABLE Reservation
(
	ReserNo varchar2(20) NOT NULL,
	ReserDate date NOT NULL,
	ReserSeat varchar2(10) NOT NULL,
	MoiveNo varchar2(10) NOT NULL,
	CustNo number NOT NULL,
	ScreenNo number NOT NULL,
	PRIMARY KEY (ReserNo)
);


CREATE TABLE Screening
(
	ScreenNo number NOT NULL,
	ScreenDate date NOT NULL,
	ScreenTheater varchar2(5) NOT NULL,
	MoiveNo varchar2(10) NOT NULL,
	PRIMARY KEY (ScreenNo)
);



/* Create Foreign Keys */

ALTER TABLE Reservation
	ADD FOREIGN KEY (CustNo)
	REFERENCES Customer (CustNo)
;


ALTER TABLE Reservation
	ADD FOREIGN KEY (MoiveNo)
	REFERENCES Movie (MoiveNo)
;


ALTER TABLE Screening
	ADD FOREIGN KEY (MoiveNo)
	REFERENCES Movie (MoiveNo)
;


ALTER TABLE Reservation
	ADD FOREIGN KEY (ScreenNo)
	REFERENCES Screening (ScreenNo)
;



