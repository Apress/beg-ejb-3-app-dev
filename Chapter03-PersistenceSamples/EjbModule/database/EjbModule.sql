ALTER TABLE CH03_CUSTOMER DROP CONSTRAINT "CH03_CUSTOMER_ADDRESS_FK"
;

ALTER TABLE CH03_CUSTOMER DROP CONSTRAINT "CH03_CUSTOMER_ADDRESS_FK1"
;

ALTER TABLE CH03_CUSTOMER_ORDER DROP CONSTRAINT "CH03_CUST_ORD_CUSTOMER_FK"
;

DROP TABLE CH03_ADDRESS CASCADE CONSTRAINTS;

DROP TABLE CH03_CUSTOMER CASCADE CONSTRAINTS;

DROP TABLE CH03_CUSTOMER_ORDER CASCADE CONSTRAINTS;

DROP SEQUENCE CH03_EJB_SEQ_ID_GEN;

CREATE TABLE CH03_ADDRESS
(
ID NUMBER(*, 0) NOT NULL,
VERSION NUMBER(*, 0),
STREET1 VARCHAR2(4000),
STREET2 VARCHAR2(4000),
CITY VARCHAR2(4000),
STATE CHAR(2),
ZIP_CODE NUMBER(*, 0)
)
;

CREATE TABLE CH03_CUSTOMER
(
ID NUMBER(*, 0) NOT NULL,
SHIPPING_ADDRESS NUMBER(*, 0),
EMAIL VARCHAR2(4000),
BILLING_ADDRESS NUMBER(*, 0)
)
;

CREATE TABLE CH03_CUSTOMER_ORDER
(
ID NUMBER(*, 0) NOT NULL,
VERSION NUMBER(*, 0),
CUSTOMER_ID NUMBER(*, 0) NOT NULL,
CREATION_DATE DATE,
STATUS VARCHAR2(30),
STREET1 VARCHAR2(4000),
STREET2 VARCHAR2(4000),
CITY VARCHAR2(4000),
STATE CHAR(2),
ZIP_CODE VARCHAR2(15)
)
;

ALTER TABLE CH03_ADDRESS
ADD CONSTRAINT CH03_ADDRESS_PK PRIMARY KEY
(
ID
)
 ENABLE
;

ALTER TABLE CH03_CUSTOMER
ADD CONSTRAINT CH03_CUSTOMER_PK PRIMARY KEY
(
ID
)
 ENABLE
;

ALTER TABLE CH03_CUSTOMER_ORDER
ADD CONSTRAINT CH03_CUSTOMER_ORDER_PK PRIMARY KEY
(
ID
)
 ENABLE
;

ALTER TABLE CH03_CUSTOMER
ADD CONSTRAINT CH03_CUSTOMER_ADDRESS_FK1 FOREIGN KEY
(
BILLING_ADDRESS
)
REFERENCES CH03_ADDRESS
(
ID
) ENABLE
;

ALTER TABLE CH03_CUSTOMER
ADD CONSTRAINT CH03_CUSTOMER_ADDRESS_FK FOREIGN KEY
(
SHIPPING_ADDRESS
)
REFERENCES CH03_ADDRESS
(
ID
) ENABLE
;

ALTER TABLE CH03_CUSTOMER_ORDER
ADD CONSTRAINT CH03_CUST_ORD_CUSTOMER_FK FOREIGN KEY
(
CUSTOMER_ID
)
REFERENCES CH03_CUSTOMER
(
ID
) ENABLE
;

CREATE SEQUENCE CH03_EJB_SEQ_ID_GEN INCREMENT BY 1 START WITH 100 MINVALUE 1 CACHE 50 ;

