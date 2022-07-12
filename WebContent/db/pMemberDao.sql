-- dao에 들어갈 query
-- pMemberDao
-- (1) 로그인
SELECT * FROM PMEMBER WHERE mID='aaa' and mPW='1';
-- (2) mid로 dto가져오기(로그인 성공시 session에 넣기 위함)
SELECT * FROM PMEMBER WHERE MID='aaa';
-- (3) 회원id 중복체크
SELECT * FROM PMEMBER WHERE MID='aaa';
-- (4) 회원가입
INSERT INTO PMEMBER (MID, MPW, MNAME, MEMAIL, MPHOTO, MBIRTH, MADDRESS)
    VALUES ('aaa','1','아무개','a@a.com', null, '1993-09-27', '인천');
-- (5) 회원정보 수정
UPDATE PMEMBER SET MPW = '1',
                    MNAME = 'aaa',
                    MEMAIL = 'bbb@naver.com',
                    MPHOTO = 'NOIMG.JPG',
                    MBIRTH = '1991/12/12',
                    MADDRESS = '서울'
        WHERE mId='aaa';
-- (6) 회원리스트(top-N구문)
SELECT * FROM (SELECT ROWNUM RN, A.* FROM
                        (SELECT * FROM PMEMBER ORDER BY mRDATE DESC) A)
        WHERE RN BETWEEN 3 AND 5;
-- (7) 회원수
SELECT COUNT(*) CNT FROM PMEMBER;
COMMIT;
-- (8) 회원탈퇴
DELETE FROM PMEMBER WHERE MID = 'aaa';
ROLLBACK;
-- (9) 이메일 중복체크
SELECT * FROM PMEMBER WHERE mEMAIL = 'go@naver.com';

select * from pmember order by mrdate desc;
-- PFILEBOARD
-- (1) 글목록(startRow부터 endRow까지) - 글번호, 작성자이름, ...
SELECT F.*, MNAME FROM PFILEBOARD F, PMEMBER M WHERE F.MID=M.MID
                ORDER BY FGROUP DESC, FSTEP; -- 출력 기준
SELECT * FROM
    (SELECT ROWNUM RN, A.* FROM
    (SELECT F.*, MNAME FROM PFILEBOARD F, PMEMBER M WHERE F.MID=M.MID 
                ORDER BY FGROUP DESC, FSTEP) A)
    WHERE RN BETWEEN 1 AND 11; -- DAO에 들어갈 QUERY
-- (2) 글갯수
SELECT COUNT(*) FROM PFILEBOARD;

-- (3) 글쓰기(원글)
INSERT INTO PFILEBOARD (FID, MID, FTITLE, FCONTENT, fFILENAME,  
        FGROUP, FSTEP, FINDENT, FIP)
    VALUES (PFILEBOARD_SEQ.NEXTVAL, 'aaa','title','content','NOIMG.JPG', 
        PFILEBOARD_SEQ.CURRVAL, 0, 0, '192.168.10.151');
-- (4) fHit 하나 올리기(1번글 조회수 하나 올리기)
UPDATE PFILEBOARD SET FHIT = FHIT +1 WHERE FID=1;
-- (5) bId로 글 dto보기
SELECT F.*, MNAME FROM PFILEBOARD F, PMEMBER M WHERE M.MID=F.MID AND FID=1;
-- (6) 글 수정하기(FId, FTitle, FContent, FILENAME,  FIp, FDATE)
UPDATE PFILEBOARD SET FTITLE = '바뀐제목',
                    FCONTENT = '본문',
                    fFILENAME = NULL,
                    FIP = '192.168.151.10',
                    FRDATE = SYSDATE
            WHERE FID = 1;
-- (7) 글 삭제하기(bId로 삭제하기)
COMMIT;
DELETE FROM PFILEBOARD WHERE FID=3;
ROLLBACK;
--  (8) 답변글 추가전 STEP a 수행
UPDATE PFILEBOARD SET FSTEP = FSTEP+1
    WHERE FGROUP = 1 AND FSTEP>0;
-- (9) 답변글 쓰기
INSERT INTO PFILEBOARD (FID, MID, FTITLE, FCONTENT, fFILENAME,
        FGROUP, FSTEP, FINDENT, FIP)
    VALUES (PFILEBOARD_SEQ.NEXTVAL, 'aaa','reply','content', null,
        1, 1, 1, '192.168.10.151');
-- (10) 회원탈퇴 하려는 사람이 쓴 글 모두 삭제 후 탈퇴
DELETE FROM FILEBOARD WHERE MID = 'aaa';
commit;
-- ADMIN TABLE
-- (1) admin loginCheck
SELECT * FROM PADMIN WHERE AID='admin' AND APW='1';
-- (2) 로그인 후 세션에 넣을 용도 : admin aid로 dto 가져오기
SELECT * FROM PADMIN WHERE AID='admin';
-- (3) 

-----------DOG-------------------
--1. 강아지 등록
INSERT INTO DOG(DNO, DNAME, DSIZE, DID, DCONTENT, DPHOTO)
VALUES(DOG_SEQ.NEXTVAL, '보통이', '소형견', '말티푸', 'CONTENT' , 'NOIMG.JPG');



-----------CAT-------------------
INSERT INTO CAT(CNO, CID, CNAME, CCONTENT, CPHOTO)
VALUES(CAT_SEQ.NEXTVAL, '아메리칸 숏헤어', '토비', 'CONTENT', 'NOIMG.JPG');