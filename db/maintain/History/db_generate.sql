--
--ER/Studio 4.3 SQL Code Generation
-- Company :      ComPiere, Inc.
-- Project :      Application Model
-- Author :       Jorg Janke
--
-- Date Created : Sunday, November 25, 2001 16:56:39
-- Target DBMS : Oracle 8
--


DROP TABLE M_MatchInv CASCADE CONSTRAINTS
;
DROP TABLE M_MatchPO CASCADE CONSTRAINTS
;
-- 
-- TABLE: M_MatchInv 
--

CREATE TABLE M_MatchInv(
    M_MatchInv_ID       NUMBER(10, 0)    NOT NULL,
    AD_Client_ID        NUMBER(10, 0)    NOT NULL,
    AD_Org_ID           NUMBER(10, 0)    NOT NULL,
    IsActive            CHAR(1)          DEFAULT 'Y' NOT NULL,
    Created             DATE             DEFAULT SysDate NOT NULL,
    CreatedBy           NUMBER(10, 0)    NOT NULL,
    Updated             DATE             DEFAULT SysDate NOT NULL,
    UpdatedBy           NUMBER(10, 0)    NOT NULL,
    M_InOutLine_ID      NUMBER(10, 0)    NOT NULL,
    C_InvoiceLine_ID    NUMBER(10, 0)    NOT NULL,
    M_Product_ID        NUMBER(10, 0)    NOT NULL,
    DateTrx             DATE             NOT NULL,
    Qty            NUMBER           DEFAULT 0 NOT NULL,
    Processing          CHAR(1)          NOT NULL,
    Processed           CHAR(1)          DEFAULT 'N' NOT NULL,
    Posted              CHAR(1)          DEFAULT 'N' NOT NULL,
    CHECK (IsActive in ('Y','N')),
    CHECK (Processed in ('Y','N')),
    CONSTRAINT M_MatchInv_Key PRIMARY KEY (M_MatchInv_ID)
) 
;


-- 
-- TABLE: M_MatchPO 
--

CREATE TABLE M_MatchPO(
    M_MatchPO_ID      NUMBER(10, 0)    NOT NULL,
    AD_Client_ID      NUMBER(10, 0)    NOT NULL,
    AD_Org_ID         NUMBER(10, 0)    NOT NULL,
    IsActive          CHAR(1)          DEFAULT 'Y' NOT NULL,
    Created           DATE             DEFAULT SysDate NOT NULL,
    CreatedBy         NUMBER(10, 0)    NOT NULL,
    Updated           DATE             DEFAULT SysDate NOT NULL,
    UpdatedBy         NUMBER(10, 0)    NOT NULL,
    M_InOutLine_ID    NUMBER(10, 0)    NOT NULL,
    C_OrderLine_ID    NUMBER(10, 0)    NOT NULL,
    M_Product_ID      NUMBER(10, 0)    NOT NULL,
    DateTrx           DATE             NOT NULL,
    Qty          NUMBER           DEFAULT 0 NOT NULL,
    Processing        CHAR(1)          NOT NULL,
    Processed         CHAR(1)          DEFAULT 'N' NOT NULL,
    Posted            CHAR(1)          DEFAULT 'N' NOT NULL,
    CHECK (IsActive in ('Y','N')),
    CHECK (Processed in ('Y','N')),
    CONSTRAINT M_MatchPO_Key PRIMARY KEY (M_MatchPO_ID)
) 
;


-- 
-- INDEX: M_MatchInv_Ship 
--

CREATE INDEX M_MatchInv_Ship ON M_MatchInv(C_InvoiceLine_ID,M_InOutLine_ID)
;
-- 
-- INDEX: M_MatchPO_Ship 
--

CREATE INDEX M_MatchPO_Ship ON M_MatchPO(C_OrderLine_ID,M_InOutLine_ID)
;
-- 
-- TABLE: M_MatchInv 
--

ALTER TABLE M_MatchInv ADD CONSTRAINT COnvoiceLine_MMatchInv 
    FOREIGN KEY (C_InvoiceLine_ID)
    REFERENCES C_InvoiceLine(C_InvoiceLine_ID)
;

ALTER TABLE M_MatchInv ADD CONSTRAINT MInOutLine_MMatchInv 
    FOREIGN KEY (M_InOutLine_ID)
    REFERENCES M_InOutLine(M_InOutLine_ID)
;

ALTER TABLE M_MatchInv ADD CONSTRAINT MProduct_MMatchInv 
    FOREIGN KEY (M_Product_ID)
    REFERENCES M_Product(M_Product_ID)
;


-- 
-- TABLE: M_MatchPO 
--

ALTER TABLE M_MatchPO ADD CONSTRAINT COrderLine_MMatchPO 
    FOREIGN KEY (C_OrderLine_ID)
    REFERENCES C_OrderLine(C_OrderLine_ID)
;

ALTER TABLE M_MatchPO ADD CONSTRAINT MInOutLine_MMatchPO 
    FOREIGN KEY (M_InOutLine_ID)
    REFERENCES M_InOutLine(M_InOutLine_ID)
;

ALTER TABLE M_MatchPO ADD CONSTRAINT MProduct_MMatchPO 
    FOREIGN KEY (M_Product_ID)
    REFERENCES M_Product(M_Product_ID)
;


