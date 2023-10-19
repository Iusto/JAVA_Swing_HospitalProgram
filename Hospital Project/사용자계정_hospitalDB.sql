select * from record;

SELECT * FROM patient WHERE EXTRACT(MONTH FROM visitdate) BETWEEN 6 AND 10
AND EXTRACT(YEAR FROM your_date_column) = 2022;

drop table record purge;

commit;


insert into patient (patientnum, name, phone, email, address, visitdate, memo)
values (0, '김철수', '010-5464-8788', 'gogildong@naver.com', '방배동 비리아파트', '2018-10-28', '돈많음');

insert into patient (patientnum, name, phone, email, address, visitdate, memo)
values (1, '고길동', '010-1234-5678', 'gogildong@naver.com', '고길동 고길아파트', '2016-10-28', '성격나쁨');

insert into patient (patientnum, name, phone, email, address, visitdate, memo)
values (2, '홍길동', '010-2453-8778', 'honggildong@naver.com', '홍길동 홍길아파트', '2016-10-28', '사기꾼');

-- 직원 테이블 생성
CREATE TABLE staff (
    staffnum NUMBER PRIMARY KEY,       -- 사원번호(기본키)
    id VARCHAR2(30) NOT NULL,          -- 아이디
    password VARCHAR2(30) NOT NULL,    -- 비밀번호
    name VARCHAR2(20) NOT NULL,        -- 이름
    num VARCHAR2(30) NOT NULL,         -- 주민번호
    age NUMBER NOT NULL,                -- 나이
    gender VARCHAR2(10) NOT NULL,       -- 성별
    phone VARCHAR2(30) NOT NULL,       -- 전화번호
    email VARCHAR2(50) NOT NULL,       -- 이메일
    position VARCHAR2(30) NOT NULL,    -- 직위(Ex. 사원, 대리, 과장, 부장, 레지던트, 전공의)
    dept VARCHAR2(30) NOT NULL,        -- 담당부서(Ex. 원무과, 전산과, 내과)
    employment VARCHAR2(10) NOT NULL  -- 재직여부 (Y/N)
);

-- 환자 테이블 생성
CREATE TABLE patient (
    patientnum NUMBER PRIMARY KEY,     -- 환자번호(기본키)
    name VARCHAR2(15) NOT NULL,        -- 이름
    phone VARCHAR2(30) NOT NULL,       -- 전화번호
    email VARCHAR2(50) NOT NULL,       -- 이메일
    address VARCHAR2(200) NOT NULL,    -- 주소
    visitdate DATE not null,           -- 처음 내원날짜
    memo VARCHAR2(200)                 -- 메모사항
);

select * from record;
insert into record (treatmentnum, patientnum, staffnum, treatment, dept, visitdate)
values (0, 0, 0, '진료', '안과', '2023-05-05');

    

-- 진료기록 테이블 생성
CREATE TABLE record (
    treatmentnum NUMBER PRIMARY KEY,   -- 진료번호
    patientnum NUMBER,                 -- 환자번호(외래키)
    staffnum NUMBER,                   -- 담당직원코드(외래키)
    treatment VARCHAR2(20),            -- 진료형태 (진료 / 수술)
    dept VARCHAR2(30) NOT NULL,        -- 담당의학과(외래키)
    visitdate date NOT NULL           -- 해당 날짜
);
