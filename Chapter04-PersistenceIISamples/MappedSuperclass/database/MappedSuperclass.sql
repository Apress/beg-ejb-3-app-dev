ALTER TABLE CH04_MS_EMPLOYEE DROP CONSTRAINT "INHER_MS_EMPLOYEE_ADDRESS_FK"
;

DROP TABLE CH04_MS_ADDRESS CASCADE CONSTRAINTS;

DROP TABLE CH04_MS_EMPLOYEE CASCADE CONSTRAINTS;

DROP SEQUENCE CH04_MS_ADDRESS_SEQ;

DROP SEQUENCE CH04_MS_PERSON_SEQ;

CREATE TABLE CH04_MS_ADDRESS
(
ID NUMBER(*, 0) NOT NULL,
VERSION NUMBER(*, 0) NOT NULL,
STREET1 VARCHAR2(100),
STREET2 VARCHAR2(100),
CITY VARCHAR2(50),
STATE VARCHAR2(2),
ZIP_CODE NUMBER(*, 0)
)
;

CREATE TABLE CH04_MS_EMPLOYEE
(
ID NUMBER(*, 0) NOT NULL,
TYPE VARCHAR2(30),
VERSION NUMBER(*, 0) NOT NULL,
FIRST_NAME VARCHAR2(30),
LAST_NAME VARCHAR2(30),
HOME_ADDRESS NUMBER(*, 0),
DEPT VARCHAR2(30),
EMAIL VARCHAR2(50),
MANAGER NUMBER(*, 0)
)
;

ALTER TABLE CH04_MS_ADDRESS
ADD CONSTRAINT INHER_MS_ADDRESS_PK PRIMARY KEY
(
ID
)
 ENABLE
;

ALTER TABLE CH04_MS_EMPLOYEE
ADD CONSTRAINT INHER_MS_PERSON_PK PRIMARY KEY
(
ID
)
 ENABLE
;

ALTER TABLE CH04_MS_EMPLOYEE
ADD CONSTRAINT INHER_MS_EMPLOYEE_ADDRESS_UK1 UNIQUE
(
HOME_ADDRESS
)
 ENABLE
;

ALTER TABLE CH04_MS_EMPLOYEE
ADD CONSTRAINT INHER_MS_EMPLOYEE_ADDRESS_FK FOREIGN KEY
(
HOME_ADDRESS
)
REFERENCES CH04_MS_ADDRESS
(
ID
) ENABLE
;

CREATE SEQUENCE CH04_MS_ADDRESS_SEQ INCREMENT BY 50 START WITH 100 MINVALUE 1 CACHE 20 ;

CREATE SEQUENCE CH04_MS_PERSON_SEQ INCREMENT BY 50 START WITH 100 MINVALUE 1 CACHE 20 ;
