ALTER TABLE CART_ITEM DROP CONSTRAINT "CART_ITEM_WINE_ITEM_FK"
;

ALTER TABLE CART_ITEM DROP CONSTRAINT "CART_ITEM_CUSTOMER_FK"
;

ALTER TABLE CUSTOMER DROP CONSTRAINT "CUSTOMER_BUSINESS_CONTACT_FK"
;

ALTER TABLE CUSTOMER DROP CONSTRAINT "CUSTOMER_ADDRESS_FK"
;

ALTER TABLE CUSTOMER DROP CONSTRAINT "CUSTOMER_ADDRESS_FK1"
;

ALTER TABLE CUSTOMER_ADDRESS DROP CONSTRAINT "CUSTOMER_ADDRESS_ADDRESS_FK"
;

ALTER TABLE CUSTOMER_ADDRESS DROP CONSTRAINT "CUSTOMER_ADDRESS_CUSTOMER_FK"
;

ALTER TABLE CUSTOMER_ORDER DROP CONSTRAINT "CUSTOMER_ORDER_CUSTOMER_FK"
;

ALTER TABLE DISTRIBUTOR DROP CONSTRAINT "DISTRIBUTOR_FK"
;

ALTER TABLE INDIVIDUAL DROP CONSTRAINT "INDIVIDUAL_BUSINESS_CONTACT_FK"
;

ALTER TABLE INVENTORY_ITEM DROP CONSTRAINT "INVENTORY_ITEM_WINE_ITEM_FK"
;

ALTER TABLE ORDER_ITEM DROP CONSTRAINT "ORDER_ITEM_CUSTOMER_ORDER_FK"
;

ALTER TABLE ORDER_ITEM DROP CONSTRAINT "ORDER_ITEM_WINE_ITEM_FK"
;

ALTER TABLE WINE_ITEM DROP CONSTRAINT "WINE_ITEM_WINE_FK"
;

ALTER TABLE SUPPLIER DROP CONSTRAINT "SUPPLIER_BUSINESS_CONTACT_FK"
;

ALTER TABLE SUPPLIER DROP CONSTRAINT "SUPPLIER_ADDRESS_FK"
;

ALTER TABLE WINE_SUPPLIER DROP CONSTRAINT "WINE_SUPPLIER_SUPPLIER_FK"
;

ALTER TABLE WINE_SUPPLIER DROP CONSTRAINT "WINE_SUPPLIER_WINE_FK"
;

DROP TABLE ADDRESS CASCADE CONSTRAINTS;

DROP TABLE BUSINESS_CONTACT CASCADE CONSTRAINTS;

DROP TABLE CART_ITEM CASCADE CONSTRAINTS;

DROP TABLE CUSTOMER CASCADE CONSTRAINTS;

DROP TABLE CUSTOMER_ADDRESS CASCADE CONSTRAINTS;

DROP TABLE CUSTOMER_ORDER CASCADE CONSTRAINTS;

DROP TABLE DISTRIBUTOR CASCADE CONSTRAINTS;

DROP TABLE INDIVIDUAL CASCADE CONSTRAINTS;

DROP TABLE INVENTORY_ITEM CASCADE CONSTRAINTS;

DROP TABLE ORDER_ITEM CASCADE CONSTRAINTS;

DROP TABLE WINE CASCADE CONSTRAINTS;

DROP TABLE WINE_ITEM CASCADE CONSTRAINTS;

DROP TABLE SUPPLIER CASCADE CONSTRAINTS;

DROP TABLE WINE_SUPPLIER CASCADE CONSTRAINTS;

DROP SEQUENCE EJB_SEQ_ID_GEN;

DROP TABLE EJB_TAB_ID_GEN CASCADE CONSTRAINTS;

CREATE TABLE ADDRESS
(
ID INTEGER NOT NULL,
VERSION INTEGER,
STREET1 VARCHAR2(4000),
STREET2 VARCHAR2(4000),
CITY VARCHAR2(4000),
STATE CHAR(2),
ZIP_CODE INTEGER
)
;

CREATE TABLE BUSINESS_CONTACT
(
ID INTEGER NOT NULL,
TYPE VARCHAR2(30),
VERSION INTEGER,
FIRST_NAME VARCHAR2(4000),
LAST_NAME VARCHAR2(4000),
PHONE VARCHAR2(15)
)
;

CREATE TABLE CART_ITEM
(
ID INTEGER NOT NULL,
CUSTOMER_ID INTEGER NOT NULL,
CREATED_DATE DATE
)
;

CREATE TABLE CUSTOMER
(
ID INTEGER NOT NULL,
SHIPPING_ADDRESS INTEGER,
EMAIL VARCHAR2(4000),
BILLING_ADDRESS INTEGER
)
;

CREATE TABLE CUSTOMER_ADDRESS
(
ADDRESS_ID INTEGER,
CUSTOMER_ID INTEGER
)
;

CREATE TABLE CUSTOMER_ORDER
(
ID INTEGER NOT NULL,
VERSION INTEGER,
CUSTOMER_ID INTEGER NOT NULL,
CREATION_DATE DATE,
STATUS VARCHAR2(30),
STREET1 VARCHAR2(4000),
STREET2 VARCHAR2(4000),
CITY VARCHAR2(4000),
STATE CHAR(2),
ZIP_CODE VARCHAR2(15)
)
;

CREATE TABLE DISTRIBUTOR
(
ID INTEGER NOT NULL,
MEMBER_STATUS VARCHAR2(30),
DISCOUNT INTEGER,
COMPANY_NAME VARCHAR2(4000)
)
;

CREATE TABLE INDIVIDUAL
(
ID INTEGER NOT NULL,
CC_NUM INTEGER,
CC_EXP_DATE VARCHAR2(7)
)
;

CREATE TABLE INVENTORY_ITEM
(
ID INTEGER NOT NULL,
STOCK_DATE DATE,
WHOLESALE_PRICE NUMBER(38, 2)
)
;

CREATE TABLE ORDER_ITEM
(
ID INTEGER NOT NULL,
CUSTOMER_ORDER_ID INTEGER NOT NULL,
ORDER_DATE DATE,
SHIP_DATE VARCHAR2(4000),
PRICE NUMBER(38, 2),
STATUS VARCHAR2(4000)
)
;

CREATE TABLE WINE
(
ID INTEGER NOT NULL,
VERSION INTEGER,
NAME VARCHAR2(4000) NOT NULL,
DESCRIPTION VARCHAR2(4000),
RETAIL_PRICE NUMBER(38, 2),
RATING NUMBER(4, 2),
COUNTRY VARCHAR2(4000),
REGION VARCHAR2(4000),
VARIETAL VARCHAR2(4000),
YEAR NUMBER(4, 0)
)
;

CREATE TABLE WINE_ITEM
(
ID INTEGER NOT NULL,
TYPE VARCHAR2(30),
VERSION INTEGER,
WINE_ID INTEGER,
QUANTITY INTEGER
)
;

CREATE TABLE SUPPLIER
(
ID INTEGER NOT NULL,
PAYMENT_ADDRESS INTEGER
)
;

CREATE TABLE WINE_SUPPLIER
(
WINES_ID INTEGER NOT NULL,
SUPPLIERS_ID INTEGER NOT NULL
)
;

CREATE TABLE EJB_TAB_ID_GEN
(
ID_NAME VARCHAR2(4000),
SEQ_VALUE NUMBER(*, 0)
)
;

ALTER TABLE ADDRESS
ADD CONSTRAINT ADDRESS_PK PRIMARY KEY
(
ID
)
 ENABLE
;

ALTER TABLE BUSINESS_CONTACT
ADD CONSTRAINT BUSINESS_CONTACT_PK PRIMARY KEY
(
ID
)
 ENABLE
;

ALTER TABLE CART_ITEM
ADD CONSTRAINT CART_ITEM_PK PRIMARY KEY
(
ID
)
 ENABLE
;

ALTER TABLE CUSTOMER
ADD CONSTRAINT CUSTOMER_PK PRIMARY KEY
(
ID
)
 ENABLE
;

ALTER TABLE CUSTOMER_ADDRESS
ADD CONSTRAINT CUSTOMER_ADDRESS_UK1 UNIQUE
(
ADDRESS_ID
)
 ENABLE
;

ALTER TABLE CUSTOMER_ORDER
ADD CONSTRAINT CUSTOMER_ORDER_PK PRIMARY KEY
(
ID
)
 ENABLE
;

ALTER TABLE DISTRIBUTOR
ADD CONSTRAINT DISTRIBUTOR_PK PRIMARY KEY
(
ID
)
 ENABLE
;

ALTER TABLE INDIVIDUAL
ADD CONSTRAINT INDIVIDUAL_PK PRIMARY KEY
(
ID
)
 ENABLE
;

ALTER TABLE INVENTORY_ITEM
ADD CONSTRAINT INVENTORY_ITEM_PK PRIMARY KEY
(
ID
)
 ENABLE
;

ALTER TABLE ORDER_ITEM
ADD CONSTRAINT ORDER_ITEM_PK PRIMARY KEY
(
ID
)
 ENABLE
;

ALTER TABLE WINE
ADD CONSTRAINT WINE_PK PRIMARY KEY
(
ID
)
 ENABLE
;

ALTER TABLE WINE_ITEM
ADD CONSTRAINT PRODUCT_ITEM_PK PRIMARY KEY
(
ID
)
 ENABLE
;

ALTER TABLE SUPPLIER
ADD CONSTRAINT SUPPLIER_PK PRIMARY KEY
(
ID
)
 ENABLE
;

ALTER TABLE WINE_SUPPLIER
ADD CONSTRAINT WINE_SUPPLIER_PK PRIMARY KEY
(
WINES_ID,
SUPPLIERS_ID
)
 ENABLE
;

ALTER TABLE CART_ITEM
ADD CONSTRAINT CART_ITEM_WINE_ITEM_FK FOREIGN KEY
(
ID
)
REFERENCES WINE_ITEM
(
ID
) ON DELETE CASCADE ENABLE
;

ALTER TABLE CART_ITEM
ADD CONSTRAINT CART_ITEM_CUSTOMER_FK FOREIGN KEY
(
CUSTOMER_ID
)
REFERENCES CUSTOMER
(
ID
) ON DELETE CASCADE ENABLE
;

ALTER TABLE CUSTOMER
ADD CONSTRAINT CUSTOMER_BUSINESS_CONTACT_FK FOREIGN KEY
(
ID
)
REFERENCES BUSINESS_CONTACT
(
ID
) ON DELETE CASCADE ENABLE
;

ALTER TABLE CUSTOMER
ADD CONSTRAINT CUSTOMER_ADDRESS_FK FOREIGN KEY
(
SHIPPING_ADDRESS
)
REFERENCES ADDRESS
(
ID
) ON DELETE CASCADE ENABLE
;

ALTER TABLE CUSTOMER
ADD CONSTRAINT CUSTOMER_ADDRESS_FK1 FOREIGN KEY
(
BILLING_ADDRESS
)
REFERENCES ADDRESS
(
ID
) ON DELETE CASCADE ENABLE
;

ALTER TABLE CUSTOMER_ADDRESS
ADD CONSTRAINT CUSTOMER_ADDRESS_ADDRESS_FK FOREIGN KEY
(
ADDRESS_ID
)
REFERENCES ADDRESS
(
ID
) ON DELETE CASCADE ENABLE
;

ALTER TABLE CUSTOMER_ADDRESS
ADD CONSTRAINT CUSTOMER_ADDRESS_CUSTOMER_FK FOREIGN KEY
(
CUSTOMER_ID
)
REFERENCES CUSTOMER
(
ID
) ON DELETE CASCADE ENABLE
;

ALTER TABLE CUSTOMER_ORDER
ADD CONSTRAINT CUSTOMER_ORDER_CUSTOMER_FK FOREIGN KEY
(
CUSTOMER_ID
)
REFERENCES CUSTOMER
(
ID
) ON DELETE CASCADE ENABLE
;

ALTER TABLE DISTRIBUTOR
ADD CONSTRAINT DISTRIBUTOR_FK FOREIGN KEY
(
ID
)
REFERENCES BUSINESS_CONTACT
(
ID
) ON DELETE CASCADE ENABLE
;

ALTER TABLE INDIVIDUAL
ADD CONSTRAINT INDIVIDUAL_BUSINESS_CONTACT_FK FOREIGN KEY
(
ID
)
REFERENCES BUSINESS_CONTACT
(
ID
) ON DELETE CASCADE ENABLE
;

ALTER TABLE INVENTORY_ITEM
ADD CONSTRAINT INVENTORY_ITEM_WINE_ITEM_FK FOREIGN KEY
(
ID
)
REFERENCES WINE_ITEM
(
ID
) ON DELETE CASCADE ENABLE
;

ALTER TABLE ORDER_ITEM
ADD CONSTRAINT ORDER_ITEM_CUSTOMER_ORDER_FK FOREIGN KEY
(
CUSTOMER_ORDER_ID
)
REFERENCES CUSTOMER_ORDER
(
ID
) ON DELETE CASCADE ENABLE
;

ALTER TABLE ORDER_ITEM
ADD CONSTRAINT ORDER_ITEM_WINE_ITEM_FK FOREIGN KEY
(
ID
)
REFERENCES WINE_ITEM
(
ID
) ON DELETE CASCADE ENABLE
;

ALTER TABLE WINE_ITEM
ADD CONSTRAINT WINE_ITEM_WINE_FK FOREIGN KEY
(
WINE_ID
)
REFERENCES WINE
(
ID
) ON DELETE CASCADE ENABLE
;

ALTER TABLE SUPPLIER
ADD CONSTRAINT SUPPLIER_BUSINESS_CONTACT_FK FOREIGN KEY
(
ID
)
REFERENCES BUSINESS_CONTACT
(
ID
) ON DELETE CASCADE ENABLE
;

ALTER TABLE SUPPLIER
ADD CONSTRAINT SUPPLIER_ADDRESS_FK FOREIGN KEY
(
PAYMENT_ADDRESS
)
REFERENCES ADDRESS
(
ID
) ON DELETE CASCADE ENABLE
;

ALTER TABLE WINE_SUPPLIER
ADD CONSTRAINT WINE_SUPPLIER_SUPPLIER_FK FOREIGN KEY
(
SUPPLIERS_ID
)
REFERENCES SUPPLIER
(
ID
) ON DELETE CASCADE ENABLE
;

ALTER TABLE WINE_SUPPLIER
ADD CONSTRAINT WINE_SUPPLIER_WINE_FK FOREIGN KEY
(
WINES_ID
)
REFERENCES WINE
(
ID
) ON DELETE CASCADE ENABLE
;

CREATE SEQUENCE EJB_SEQ_ID_GEN INCREMENT BY 50 START WITH 100 MAXVALUE 999999999999999999999999999 MINVALUE 1 CACHE 20 ;

