SELECT id "id", username "username", password "password", phone_number "phone_number",
	name "name", authorities "authorities", regdate "regdate" from t404_user order by id desc;

ALTER TABLE t404_user AUTO_INCREMENT = 1;
ALTER TABLE t404_review_write AUTO_INCREMENT = 1;
ALTER TABLE t404_qna_write AUTO_INCREMENT = 1;
ALTER TABLE t404_qna_comment AUTO_INCREMENT = 1;

-- user
INSERT INTO t404_user (username, password, name, authorities, phone_number) VALUES
('USER1', '1234', '회원1', 'PREMIUM', '01011112222'),
('USER2', '1234', '회원2', null, '01033334444'),
('ADMIN1', '1234', '관리자1', 'PREMIUM', '01055556666')
;

-- parking
INSERT INTO t404_parkinglot(id, addr, parking_name) VALUES
(1, '서울시 강남구', '강남주차장'),
(2, '서울시 강동구', '강동주차장'),
(3, '서울시 강서구', '강서주차장')
;

-- review_write sample
INSERT INTO t404_review_write(user_id, parking_id , subject, content) VALUES
(1, 1, '제목입니다1', '네용입니다1'),
(1, 1, '제목입니다2', '내용입니다2'),
(3, 2, '제목입니다3', '내용입니다3'),
(2, 3, '제목입니다4', '내용입니다4')
;

-- qna_write sample
INSERT INTO t404_qna_write(user_id, subject, content) VALUES
(1, '제목입니다1', '네용입니다1'),
(1, '제목입니다2', '내용입니다2'),
(3, '제목입니다3', '내용입니다3'),
(2, '제목입니다4', '내용입니다4')
;

-- sample review_comment
INSERT INTO t404_review_comment(review_write_id, user_id, content) VALUES
(5, 1, '1. user1이 1번글에 댓글 작성.'),
(5, 2, '2. user1이 1번글에 댓글 작성.'),
(7, 3, '3. user1이 2번글에 댓글 작성.'),
(8, 1, '4. user1이 2번글에 댓글 작성.')
;

-- sample qna_comment
INSERT INTO t404_qna_comment(qna_id, user_id, content) VALUES
(1, 1, '1. user1이 1번글에 댓글 작성.'),
(2, 1, '2. user1이 1번글에 댓글 작성.'),
(5, 2, '3. user1이 2번글에 댓글 작성.'),
(8, 3, '4. user1이 2번글에 댓글 작성.')
;

SELECT * FROM t404_user ORDER BY id DESC;
SELECT * FROM t404_review_write ORDER BY id DESC;
SELECT * FROM t404_qna_write ORDER BY id DESC;
SELECT * FROM t404_review_comment ORDER BY id DESC;
SELECT * FROM t404_qna_comment ORDER BY id DESC;

-- reivew 글 조회 (작성자 포함 JOIN)SELECT r.id, r.subject, r.content, r.viewcnt, r.regdate,
SELECT u.id , u.username, u.password , u.name , u.authorities , u.regdate, u.phone_number
FROM t404_review_write r, t404_user u
WHERE r.user_id  = u.id
ORDER BY r.id DESC
;

-- qna 글 조회 (작성자 포함 JOIN)SELECT r.id, r.subject, r.content, r.viewcnt, r.regdate,
SELECT q.id, q.subject, q.content, q.viewcnt, q.regdate,
	u.id, u.username, u.password, u.name, u.authorities, u.regdate, u.car_name, u.car_number, u.phone_number
FROM t404_qna_write q, t404_user u
WHERE q.user_id  = u.id
ORDER BY q.id DESC
;

# 페이징
-- 다량의 데이터
INSERT INTO t404_review_write (user_id, parking_id, subject, content)
SELECT user_id, parking_id, subject, content FROM t404_review_write;

SELECT count(*) FROM t404_review_write;

SELECT * FROM t404_review_write ORDER BY id DESC LIMIT 5;
SELECT * FROM t404_review_write ORDER BY id DESC LIMIT 5, 5;

-- 한 페이지 10개씩?
SELECT * FROM  t404_review_write ORDER BY id DESC LIMIT 0, 10; -- 1page
SELECT * FROM  t404_review_write ORDER BY id DESC LIMIT 10, 10; -- 2page

-- --------------------------------------------
-- 파일첨부

SELECT * FROM t4_file;

--  첨부 파일이 있는 게시글 조회
SELECT w.id "w_id", f.id "f_id", f.source, f.file
FROM t4_write w, t4_file f
WHERE w.id  = f.write_id
ORDER BY w.id DESC
;

-- 특정 글 (write.id)의 첨부파일 1개 INSERT (작성, 수정)
INSERT INTO t4_file (source, file, write_id)
VALUES (?, ?, ?)
;

-- 특정 글 (write_id)의 첨부파일(들)을 SELECT  (조회, 수정)
SELECT id, source, file, write_id
FROM t4_file
WHERE write_id = 1
ORDER BY id DESC
;


-- 특정 첨부파일 (file.id) 하나를 SELECT
SELECT id, source, file, write_id
FROM t4_file
WHERE id = ?
;

-- 특정 첨부파일 (file.id) 하나를 DELETE (수정)
DELETE FROM t4_file WHERE id = ?
;

-- 특정 글의 (write_id) 의 첨부파일(들)을  DELETE  (삭제)
DELETE FROM t4_file WHERE write_id = ?
;

# -------------------------------------------------------
# 댓글

# 특정글 의 (댓글 + 사용자) 정보
SELECT c.id "id", c.content "content", c.regdate "regdate",
    u.id "user_id", u.username "user_username", u.password "user_password", u.name "user_name", u.authorities "user_authorities", u.regdate "user_regdate"
FROM t4_comment c, t4_user u
WHERE c.user_id = u.id AND c.write_id = 1
ORDER BY c.id DESC
;



