SELECT id "id", username "username", password "password",
	name "name", authorities "authorities", regdate "regdate" from t4_user order by id desc;

ALTER TABLE t4_user AUTO_INCREMENT = 1;
-- 샘플 사용자
INSERT INTO t4_user (username, password, name, authorities) VALUES
('USER1', '1234', '회원1', 'ROLE_MEMBER'),
('USER2', '1234', '회원2', null),
('ADMIN1', '1234', '관리자1', 'ROLE_MEMBER,ROLE_ADMIN')
;

-- 샘플 글
INSERT INTO t4_write (user_id, subject, content) VALUES
(1, '제목입니다1', '내용입니다1'),
(1, '제목입니다2', '내용입니다2'),
(3, '제목입니다3', '내용입니다3'),
(3, '제목입니다4', '내용입니다4')
;//ddd

-- 샘플 댓글
INSERT INTO t4_comment(user_id, write_id, content) VALUES
(1, 1, '1. user1이 1번글에 댓글 작성.'),
(1, 1, '2. user1이 1번글에 댓글 작성.'),
(1, 2, '3. user1이 2번글에 댓글 작성.'),
(1, 2, '4. user1이 2번글에 댓글 작성.'),
(1, 3, '5. user1이 3번글에 댓글 작성.'),
(1, 3, '6. user1이 3번글에 댓글 작성.'),
(1, 4, '7. user1이 4번글에 댓글 작성.'),
(1, 4, '8. user1이 4번글에 댓글 작성.'),
(3, 1, '9. admin1이 1번글에 댓글 작성.'),
(3, 1, '10. admin1이 1번글에 댓글 작성.'),
(3, 2, '11. admin1이 2번글에 댓글 작성.'),
(3, 2, '12. admin1이 2번글에 댓글 작성.'),
(3, 3, '13. admin1이 3번글에 댓글 작성.'),
(3, 3, '14. admin1이 3번글에 댓글 작성.'),
(3, 4, '15. admin1이 4번글에 댓글 작성.'),
(3, 4, '16. admin1이 4번글에 댓글 작성.')
;




SELECT * FROM t4_user ORDER BY uid DESC;
SELECT * FROM t4_write ORDER BY uid DESC;

-- 글 조회 (작성자 포함 JOIN)
SELECT w.id, w.subject, w.content, w.viewcnt, w.regdate,
	u.id , u.username, u.password , u.name , u.authorities , u.regdate 
FROM t4_write w, t4_user u
WHERE w.user_id  = u.id
ORDER BY w.id DESC
;

# 페이징
-- 다량의 데이터
INSERT INTO t4_write (user_id, subject, content)
SELECT user_id, subject, content FROM t4_write;

SELECT count(*) FROM t4_write;

SELECT * FROM t4_write ORDER BY id DESC LIMIT 5;
SELECT * FROM t4_write ORDER BY id DESC LIMIT 5, 5;

-- 한 페이지 10개씩?
SELECT * FROM  t4_write ORDER BY id DESC LIMIT 0, 10; -- 1page
SELECT * FROM  t4_write ORDER BY id DESC LIMIT 10, 10; -- 2page

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



