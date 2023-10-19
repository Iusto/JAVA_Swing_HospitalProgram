select * from record;

SELECT * FROM patient WHERE EXTRACT(MONTH FROM visitdate) BETWEEN 6 AND 10
AND EXTRACT(YEAR FROM your_date_column) = 2022;

drop table record purge;

commit;


insert into patient (patientnum, name, phone, email, address, visitdate, memo)
values (0, '��ö��', '010-5464-8788', 'gogildong@naver.com', '��赿 �񸮾���Ʈ', '2018-10-28', '������');

insert into patient (patientnum, name, phone, email, address, visitdate, memo)
values (1, '��浿', '010-1234-5678', 'gogildong@naver.com', '��浿 ������Ʈ', '2016-10-28', '���ݳ���');

insert into patient (patientnum, name, phone, email, address, visitdate, memo)
values (2, 'ȫ�浿', '010-2453-8778', 'honggildong@naver.com', 'ȫ�浿 ȫ�����Ʈ', '2016-10-28', '����');

-- ���� ���̺� ����
CREATE TABLE staff (
    staffnum NUMBER PRIMARY KEY,       -- �����ȣ(�⺻Ű)
    id VARCHAR2(30) NOT NULL,          -- ���̵�
    password VARCHAR2(30) NOT NULL,    -- ��й�ȣ
    name VARCHAR2(20) NOT NULL,        -- �̸�
    num VARCHAR2(30) NOT NULL,         -- �ֹι�ȣ
    age NUMBER NOT NULL,                -- ����
    gender VARCHAR2(10) NOT NULL,       -- ����
    phone VARCHAR2(30) NOT NULL,       -- ��ȭ��ȣ
    email VARCHAR2(50) NOT NULL,       -- �̸���
    position VARCHAR2(30) NOT NULL,    -- ����(Ex. ���, �븮, ����, ����, ������Ʈ, ������)
    dept VARCHAR2(30) NOT NULL,        -- ���μ�(Ex. ������, �����, ����)
    employment VARCHAR2(10) NOT NULL  -- �������� (Y/N)
);

-- ȯ�� ���̺� ����
CREATE TABLE patient (
    patientnum NUMBER PRIMARY KEY,     -- ȯ�ڹ�ȣ(�⺻Ű)
    name VARCHAR2(15) NOT NULL,        -- �̸�
    phone VARCHAR2(30) NOT NULL,       -- ��ȭ��ȣ
    email VARCHAR2(50) NOT NULL,       -- �̸���
    address VARCHAR2(200) NOT NULL,    -- �ּ�
    visitdate DATE not null,           -- ó�� ������¥
    memo VARCHAR2(200)                 -- �޸����
);

select * from record;
insert into record (treatmentnum, patientnum, staffnum, treatment, dept, visitdate)
values (0, 0, 0, '����', '�Ȱ�', '2023-05-05');

    

-- ������ ���̺� ����
CREATE TABLE record (
    treatmentnum NUMBER PRIMARY KEY,   -- �����ȣ
    patientnum NUMBER,                 -- ȯ�ڹ�ȣ(�ܷ�Ű)
    staffnum NUMBER,                   -- ��������ڵ�(�ܷ�Ű)
    treatment VARCHAR2(20),            -- �������� (���� / ����)
    dept VARCHAR2(30) NOT NULL,        -- ������а�(�ܷ�Ű)
    visitdate date NOT NULL           -- �ش� ��¥
);
