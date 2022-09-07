SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS t404_file;
DROP TABLE IF EXISTS t404_reserve;
DROP TABLE IF EXISTS t404_review_comment;
DROP TABLE IF EXISTS t404_review_write;
DROP TABLE IF EXISTS t404_parkinglot;
DROP TABLE IF EXISTS t404_qna_comment;
DROP TABLE IF EXISTS t404_qna_write;
DROP TABLE IF EXISTS t404_user;




/* Create Tables */

CREATE TABLE t404_file
(
	id int NOT NULL AUTO_INCREMENT,
	review_write_id int NOT NULL,
	file varchar(100) NOT NULL,
	source varchar(100) NOT NULL,
	PRIMARY KEY (id)
);


CREATE TABLE t404_parkinglot
(
	id int NOT NULL AUTO_INCREMENT,
	addr varchar(100) NOT NULL,
	weekend_begin_time varchar(100),
	weekend_end_time varchar(100),
	weekday_begin_time varchar(100),
	weekday_end_time varchar(100),
	holiday_begin_time varchar(100),
	holiday_end_time varchar(100),
	holiday_pay_nm varchar(100),
	fulltime_monthly varchar(100),
	weekday_pay_nm varchar(100),
	saturday_pay_nm varchar(100),
	tel varchar(100),
	parking_name varchar(100) NOT NULL,
	PRIMARY KEY (id)
);


CREATE TABLE t404_qna_comment
(
	id int NOT NULL AUTO_INCREMENT,
	qna_id int NOT NULL,
	user_id int NOT NULL,
	content text NOT NULL,
	regdate datetime DEFAULT now(),
	PRIMARY KEY (id)
);


CREATE TABLE t404_qna_write
(
	id int NOT NULL AUTO_INCREMENT,
	user_id int NOT NULL,
	subject varchar(200) NOT NULL,
	content longtext NOT NULL,
	viewcnt int,
	regdate datetime DEFAULT now(),
	PRIMARY KEY (id)
);


CREATE TABLE t404_reserve
(
	id int NOT NULL AUTO_INCREMENT,
	parking_id int NOT NULL,
	user_id int NOT NULL,
	regdate datetime NOT NULL DEFAULT now(),
	checkin_time varchar(100) NOT NULL,
	PRIMARY KEY (id)
);


CREATE TABLE t404_review_comment
(
	id int NOT NULL AUTO_INCREMENT,
	review_write_id int NOT NULL,
	user_id int NOT NULL,
	content text NOT NULL,
	regdate datetime DEFAULT now(),
	PRIMARY KEY (id)
);


CREATE TABLE t404_review_write
(
	id int NOT NULL AUTO_INCREMENT,
	user_id int NOT NULL,
	parking_id int NOT NULL,
	subject varchar(200) NOT NULL,
	content longtext,
	viewcnt int DEFAULT 0 CHECK(viewcnt >= 0),
	regdate datetime DEFAULT now(),
	PRIMARY KEY (id)
);


CREATE TABLE t404_user
(
	id int NOT NULL AUTO_INCREMENT,
	username varchar(80) NOT NULL,
	password varchar(100) NOT NULL,
	name varchar(80) NOT NULL,
	authorities varchar(200) DEFAULT 'PREMIUM',
	regdate datetime DEFAULT now(),
	car_name varchar(100),
	car_number varchar(100),
	phone_number varchar(100) NOT NULL,
	PRIMARY KEY (id),
	UNIQUE (username)
);



/* Create Foreign Keys */

ALTER TABLE t404_reserve
	ADD FOREIGN KEY (parking_id)
	REFERENCES t404_parkinglot (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE t404_review_write
	ADD FOREIGN KEY (parking_id)
	REFERENCES t404_parkinglot (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE t404_qna_comment
	ADD FOREIGN KEY (qna_id)
	REFERENCES t404_qna_write (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE t404_file
	ADD FOREIGN KEY (review_write_id)
	REFERENCES t404_review_write (id)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;


ALTER TABLE t404_review_comment
	ADD FOREIGN KEY (review_write_id)
	REFERENCES t404_review_write (id)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;


ALTER TABLE t404_qna_comment
	ADD FOREIGN KEY (user_id)
	REFERENCES t404_user (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE t404_qna_write
	ADD FOREIGN KEY (user_id)
	REFERENCES t404_user (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE t404_reserve
	ADD FOREIGN KEY (user_id)
	REFERENCES t404_user (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE t404_review_comment
	ADD FOREIGN KEY (user_id)
	REFERENCES t404_user (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE t404_review_write
	ADD FOREIGN KEY (user_id)
	REFERENCES t404_user (id)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;



