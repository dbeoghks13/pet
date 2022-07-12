-- PMEMBER
DROP TABLE PMEMBER;
CREATE TABLE PMEMBER(
    mID VARCHAR2(30) PRIMARY KEY,
    mPW VARCHAR2(30) NOT NULL,
    mNAME VARCHAR2(30) NOT NULL,
    mEMAIL VARCHAR2(30),                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
    mPHOTO VARCHAR2(30), 
    mBIRTH DATE,
    mADDRESS VARCHAR2(30),
    mRDATE DATE DEFAULT SYSDATE
);
INSERT INTO PMEMBER(MID, MPW, MNAME, MEMAIL, MPHOTO, MBIRTH, MADDRESS)
VALUES('aaa','1','아무개','a@a.com', null, '1993-09-27', '인천');
SELECT * FROM PMEMBER;
-- ADMIN
DROP TABLE PADMIN;
DROP SEQUENCE PADMIN_SEQ;
CREATE SEQUENCE PADMIN_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
CREATE TABLE PADMIN(
    aID VARCHAR2(30) PRIMARY KEY,
    aPW VARCHAR2(30) NOT NULL,
    aNAME VARCHAR2(30) NOT NULL
);
INSERT INTO PADMIN(AID, APW, ANAME)
    VALUES('yoo', '123', '유대환');
SELECT * FROM PADMIN;
-- PFILEBOARD
DROP TABLE PFILEBOARD CASCADE CONSTRAINTS;
DROP SEQUENCE PFILEBOARD_SEQ;
CREATE SEQUENCE PFILEBOARD_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
CREATE TABLE PFILEBOARD(
    fID NUMBER(6) PRIMARY KEY,
    mID VARCHAR2(30) REFERENCES PMEMBER(mID) NOT NULL,
    fTITLE VARCHAR2(100) NOT NULL,
    fCONTENT VARCHAR2(4000),
    fFILENAME VARCHAR2(100) NOT NULL,
    fRDATE DATE NOT NULL,
    fHIT NUMBER(6) DEFAULT 0 NOT NULL,
    fGROUP NUMBER(6) NOT NULL,
    fSTEP NUMBER(6) NOT NULL,
    fINDENT NUMBER(6) NOT NULL,
    fIP VARCHAR2(30) NOT NULL
);
INSERT INTO PFILEBOARD(FID, MID, FTITLE, FCONTENT, FFILENAME, FRDATE, FHIT, FGROUP, FSTEP, FINDENT, FIP )
VALUES(PFILEBOARD_SEQ.NEXTVAL, 'aaa', 'title', 'content', 'NOIMG.JPG', SYSDATE, 0, 1, 1, 1, '192.168.10.4');
SELECT * FROM PFILEBOARD;
-- DOG
DROP TABLE DOG;
DROP SEQUENCE DOG_SEQ;
CREATE SEQUENCE DOG_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
CREATE TABLE DOG(
    dNO NUMBER(6) PRIMARY KEY,
    dNAME VARCHAR2(30) NOT NULL,
    dSIZE VARCHAR2(30) NOT NULL,
    dID VARCHAR2(30) NOT NULL,
    dCONTENT VARCHAR2(2000) NOT NULL,
    dPHOTO VARCHAR2(100) NOT NULL
);
select * from dog;
INSERT INTO DOG(DNO, DNAME, DSIZE, DID, DCONTENT, DPHOTO)
VALUES(DOG_SEQ.NEXTVAL, '보통이', '소형견', '말티푸', 'CONTENT' , 'NOIMG.JPG');
INSERT INTO DOG(DNO, DNAME, DSIZE, DID, DCONTENT, DPHOTO)
VALUES(DOG_SEQ.NEXTVAL, '겨울이', '소형견', '포메라니안', 'CONTENT', 'NOIMG.JPG');
INSERT INTO DOG(DNO, DNAME, DSIZE, DID, DCONTENT, DPHOTO)
VALUES(DOG_SEQ.NEXTVAL, '코코', '소형견', '푸들', 'CONTENT', 'NOIMG.JPG');
INSERT INTO DOG(DNO, DNAME, DSIZE, DID, DCONTENT, DPHOTO)
VALUES(DOG_SEQ.NEXTVAL, '슈슈', '소형견', '푸들', 'CONTENT', 'NOIMG.JPG');
INSERT INTO DOG(DNO, DNAME, DSIZE, DID, DCONTENT, DPHOTO)
VALUES(DOG_SEQ.NEXTVAL, '두부', '소형견', '말티즈', 'CONTENT', 'NOIMG.JPG');
INSERT INTO DOG(DNO, DNAME, DSIZE, DID, DCONTENT, DPHOTO)
VALUES(DOG_SEQ.NEXTVAL, '아치', '소형견', '푸들', 'CONTENT', 'NOIMG.JPG');
INSERT INTO DOG(DNO, DNAME, DSIZE, DID, DCONTENT, DPHOTO)
VALUES(DOG_SEQ.NEXTVAL, '뚜두', '소형견', '닥스훈트', 'CONTENT', 'NOIMG.JPG');
INSERT INTO DOG(DNO, DNAME, DSIZE, DID, DCONTENT, DPHOTO)
VALUES(DOG_SEQ.NEXTVAL, '덤덤', '소형견', '닥스훈트', 'CONTENT', 'NOIMG.JPG');
INSERT INTO DOG(DNO, DNAME, DSIZE, DID, DCONTENT, DPHOTO)
VALUES(DOG_SEQ.NEXTVAL, '도쿠', '소형견', '시츄', 'CONTENT', 'NOIMG.JPG');
INSERT INTO DOG(DNO, DNAME, DSIZE, DID, DCONTENT, DPHOTO)
VALUES(DOG_SEQ.NEXTVAL, '미니미', '소형견', '요크셔테리어', 'CONTENT', 'NOIMG.JPG');
INSERT INTO DOG(DNO, DNAME, DSIZE, DID, DCONTENT, DPHOTO)
VALUES(DOG_SEQ.NEXTVAL, '닝고', '중형견', '웰시코기', 'CONTENT', 'NOIMG.JPG');
INSERT INTO DOG(DNO, DNAME, DSIZE, DID, DCONTENT, DPHOTO)
VALUES(DOG_SEQ.NEXTVAL, '부기', '중형견', '웰시코기', 'CONTENT', 'NOIMG.JPG');
INSERT INTO DOG(DNO, DNAME, DSIZE, DID, DCONTENT, DPHOTO)
VALUES(DOG_SEQ.NEXTVAL, '호야', '중형견', '스피츠', 'CONTENT', 'NOIMG.JPG');
INSERT INTO DOG(DNO, DNAME, DSIZE, DID, DCONTENT, DPHOTO)
VALUES(DOG_SEQ.NEXTVAL, '탕이', '중형견', '스피츠', 'CONTENT', 'NOIMG.JPG');
INSERT INTO DOG(DNO, DNAME, DSIZE, DID, DCONTENT, DPHOTO)
VALUES(DOG_SEQ.NEXTVAL, '치치', '중형견', '비글', 'CONTENT', 'NOIMG.JPG');
INSERT INTO DOG(DNO, DNAME, DSIZE, DID, DCONTENT, DPHOTO)
VALUES(DOG_SEQ.NEXTVAL, '두기', '중형견', '시바견', 'CONTENT', 'NOIMG.JPG');
INSERT INTO DOG(DNO, DNAME, DSIZE, DID, DCONTENT, DPHOTO)
VALUES(DOG_SEQ.NEXTVAL, '가미', '중형견', '시바견', 'CONTENT', 'NOIMG.JPG');
INSERT INTO DOG(DNO, DNAME, DSIZE, DID, DCONTENT, DPHOTO)
VALUES(DOG_SEQ.NEXTVAL, '곰다', '중형견', '프렌치불독', 'CONTENT', 'NOIMG.JPG');
INSERT INTO DOG(DNO, DNAME, DSIZE, DID, DCONTENT, DPHOTO)
VALUES(DOG_SEQ.NEXTVAL, '금자', '중형견', '프렌치불독', 'CONTENT', 'NOIMG.JPG');
INSERT INTO DOG(DNO, DNAME, DSIZE, DID, DCONTENT, DPHOTO)
VALUES(DOG_SEQ.NEXTVAL, '루나', '중형견', '셔틀랜드쉽독', 'CONTENT', 'NOIMG.JPG');
INSERT INTO DOG(DNO, DNAME, DSIZE, DID, DCONTENT, DPHOTO)
VALUES(DOG_SEQ.NEXTVAL, '알로', '대형견', '보더콜리', 'CONTENT', 'NOIMG.JPG');
INSERT INTO DOG(DNO, DNAME, DSIZE, DID, DCONTENT, DPHOTO)
VALUES(DOG_SEQ.NEXTVAL, '마르코', '대형견', '허스키', 'CONTENT', 'NOIMG.JPG');
INSERT INTO DOG(DNO, DNAME, DSIZE, DID, DCONTENT, DPHOTO)
VALUES(DOG_SEQ.NEXTVAL, '하얀', '대형견', '사모예드', 'CONTENT', 'NOIMG.JPG');
INSERT INTO DOG(DNO, DNAME, DSIZE, DID, DCONTENT, DPHOTO)
VALUES(DOG_SEQ.NEXTVAL, '늘보', '대형견', '리트리버', 'CONTENT', 'NOIMG.JPG');
INSERT INTO DOG(DNO, DNAME, DSIZE, DID, DCONTENT, DPHOTO)
VALUES(DOG_SEQ.NEXTVAL, '상두', '대형견', '잉글리쉬 불독', 'CONTENT', 'NOIMG.JPG');
INSERT INTO DOG(DNO, DNAME, DSIZE, DID, DCONTENT, DPHOTO)
VALUES(DOG_SEQ.NEXTVAL, '다오', '대형견', '리트리버', 'CONTENT', 'NOIMG.JPG');
INSERT INTO DOG(DNO, DNAME, DSIZE, DID, DCONTENT, DPHOTO)
VALUES(DOG_SEQ.NEXTVAL, '단이', '대형견', '진돗개', 'CONTENT', 'NOIMG.JPG');
INSERT INTO DOG(DNO, DNAME, DSIZE, DID, DCONTENT, DPHOTO)
VALUES(DOG_SEQ.NEXTVAL, '로망', '대형견', '말라뮤트', 'CONTENT', 'NOIMG.JPG');
INSERT INTO DOG(DNO, DNAME, DSIZE, DID, DCONTENT, DPHOTO)
VALUES(DOG_SEQ.NEXTVAL, '마블', '대형견', '보더콜리', 'CONTENT', 'NOIMG.JPG');
INSERT INTO DOG(DNO, DNAME, DSIZE, DID, DCONTENT, DPHOTO)
VALUES(DOG_SEQ.NEXTVAL, '누크', '대형견', '버니즈마운틴', 'CONTENT', 'NOIMG.JPG');

SELECT * FROM DOG;

-- CAT
DROP TABLE CAT;
DROP SEQUENCE CAT_SEQ;
CREATE SEQUENCE CAT_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
CREATE TABLE CAT(
    cNO NUMBER(6) PRIMARY KEY,
    cID VARCHAR2(30) NOT NULL,
    cNAME VARCHAR2(30) NOT NULL,
    cCONTENT VARCHAR2(2000) NOT NULL,
    cPHOTO VARCHAR2(100) NOT NULL
);