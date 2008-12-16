--------------------------------------------------------------------------
-- Play this script in COMPIERE252D to make it look like COMPIERE253A
--                                                                      --
-- Please review the script before using it to make sure it won't       --
-- cause any unacceptable data loss.                                    --
--                                                                      --
-- COMPIERE252D Schema Extracted by User COMPIERE252D 
-- COMPIERE253A Schema Extracted by User COMPIERE252D 
ALTER TABLE C_ELEMENT
 DROP CONSTRAINT ADTREE1_CELEMENT;

ALTER TABLE C_ELEMENT
 DROP CONSTRAINT ADTREE2_CELEMENT;

ALTER TABLE AD_CLIENTINFO
 DROP CONSTRAINT SYS_C00136155;

ALTER TABLE AD_CLIENTINFO
 DROP CONSTRAINT SYS_C00136156;

ALTER TABLE AD_CLIENTINFO
 DROP CONSTRAINT CACCTSCHEMA2_ADCLIENTINFO;

ALTER TABLE AD_CLIENTINFO
 DROP CONSTRAINT CACCTSCHEMA3_ADCLIENTINFO;

ALTER TABLE AD_CLIENTINFO
 DROP CONSTRAINT MPRICELIST_ADCLIENTINFO;

ALTER TABLE C_LANDEDCOSTALLOCATION
 DROP CONSTRAINT CLANDEDCOST_ALLOCATION;

DROP VIEW RV_ORDER_OPEN;

DROP INDEX C_BANKSTATEMENTLINE_PAYMENT;

ALTER TABLE AD_CLIENT
 ADD (EMAILTEST  CHAR(1 BYTE));

ALTER TABLE AD_CLIENT
 ADD (ISSERVEREMAIL  CHAR(1 BYTE)                   DEFAULT 'N'                   NOT NULL);

ALTER TABLE AD_CLIENT
 ADD (ISPOSTIMMEDIATE  CHAR(1 BYTE)                 DEFAULT 'N'                   NOT NULL);

ALTER TABLE AD_CLIENT
 ADD (ISCOSTIMMEDIATE  CHAR(1 BYTE)                 DEFAULT 'N'                   NOT NULL);

ALTER TABLE AD_CLIENT
 ADD (ISCOSTIMMEDIATE  CHAR(1 BYTE)                 DEFAULT 'N'                   NOT NULL);
ALTER TABLE AD_CLIENT DROP COLUMN WEBDIR;

ALTER TABLE AD_CLIENT DROP COLUMN WEBPARAM1;

ALTER TABLE AD_CLIENT DROP COLUMN WEBPARAM2;

ALTER TABLE AD_CLIENT DROP COLUMN WEBPARAM3;

ALTER TABLE AD_CLIENT DROP COLUMN WEBPARAM4;

ALTER TABLE AD_CLIENT DROP COLUMN WEBORDEREMAIL;

ALTER TABLE AD_CLIENT DROP COLUMN WEBINFO;

ALTER TABLE AD_CLIENT DROP COLUMN WEBPARAM6;

ALTER TABLE AD_CLIENT DROP COLUMN WEBPARAM5;

ALTER TABLE AD_CLIENT DROP COLUMN ENCRYPTIONKEY;

ALTER TABLE AD_SYSTEM
 ADD (LDAPDOMAIN  NVARCHAR2(255));

ALTER TABLE AD_SYSTEM
 ADD (LDAPDOMAIN  NVARCHAR2(255));
ALTER TABLE AD_SYSTEM DROP COLUMN LDAPPORT;

ALTER TABLE AD_SYSTEM DROP COLUMN LDAPQUERY;

ALTER TABLE AD_TASK
 ADD (ISSERVERPROCESS  CHAR(1 BYTE)                 DEFAULT 'N'                   NOT NULL);

ALTER TABLE AD_TREE
 ADD (ISDEFAULT  CHAR(1 BYTE)                       DEFAULT 'N'                   NOT NULL);

ALTER TABLE C_ELEMENT DROP COLUMN ADD2TREE_ID;

ALTER TABLE C_ELEMENT DROP COLUMN ADD1TREE_ID;

CREATE TABLE C_TAXDECLARATION
(
  C_TAXDECLARATION_ID  NUMBER(10)               NOT NULL,
  AD_CLIENT_ID         NUMBER(10)               NOT NULL,
  AD_ORG_ID            NUMBER(10)               NOT NULL,
  ISACTIVE             CHAR(1 BYTE)             DEFAULT 'Y'                   NOT NULL,
  CREATED              DATE                     DEFAULT SYSDATE               NOT NULL,
  CREATEDBY            NUMBER(10)               NOT NULL,
  UPDATED              DATE                     DEFAULT SYSDATE               NOT NULL,
  UPDATEDBY            NUMBER(10)               NOT NULL,
  NAME                 NVARCHAR2(60)            NOT NULL,
  DESCRIPTION          NVARCHAR2(255),
  DATETRX              DATE                     NOT NULL,
  DATEFROM             DATE                     NOT NULL,
  DATETO               DATE                     NOT NULL,
  PROCESSING           CHAR(1 BYTE),
  PROCESSED            CHAR(1 BYTE)             DEFAULT 'N'                   NOT NULL
)
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;

CREATE TABLE PA_HIERARCHY
(
  PA_HIERARCHY_ID         NUMBER(10)            NOT NULL,
  AD_CLIENT_ID            NUMBER(10)            NOT NULL,
  AD_ORG_ID               NUMBER(10)            NOT NULL,
  ISACTIVE                CHAR(1 BYTE)          DEFAULT 'Y'                   NOT NULL,
  CREATED                 DATE                  DEFAULT SYSDATE               NOT NULL,
  CREATEDBY               NUMBER(10)            NOT NULL,
  UPDATED                 DATE                  DEFAULT SYSDATE               NOT NULL,
  UPDATEDBY               NUMBER(10)            NOT NULL,
  NAME                    NVARCHAR2(60)         NOT NULL,
  DESCRIPTION             NVARCHAR2(255),
  HELP                    NVARCHAR2(2000),
  AD_TREE_ORG_ID          NUMBER(10)            NOT NULL,
  AD_TREE_BPARTNER_ID     NUMBER(10)            NOT NULL,
  AD_TREE_PROJECT_ID      NUMBER(10)            NOT NULL,
  AD_TREE_SALESREGION_ID  NUMBER(10)            NOT NULL,
  AD_TREE_PRODUCT_ID      NUMBER(10)            NOT NULL,
  AD_TREE_CAMPAIGN_ID     NUMBER(10)            NOT NULL,
  AD_TREE_ACTIVITY_ID     NUMBER(10)            NOT NULL,
  AD_TREE_ACCOUNT_ID      NUMBER(10)            NOT NULL
)
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;

ALTER TABLE W_STORE
MODIFY(WSTOREEMAIL  NULL);


ALTER TABLE W_STORE
MODIFY(WSTOREUSER  NULL);


ALTER TABLE W_STORE
MODIFY(WSTOREUSERPW  NULL);


ALTER TABLE W_STORE
MODIFY(WEBINFO  NULL);


ALTER TABLE W_STORE
MODIFY(WEBPARAM1  NULL);


ALTER TABLE W_STORE
MODIFY(WEBPARAM2  NULL);


ALTER TABLE W_STORE
MODIFY(WEBPARAM3  NULL);


ALTER TABLE W_STORE
MODIFY(WEBPARAM4  NULL);


ALTER TABLE W_STORE
MODIFY(WEBPARAM5  NULL);


ALTER TABLE W_STORE
MODIFY(WEBPARAM6  NULL);


ALTER TABLE W_STORE
 ADD (WEBCONTEXT  NVARCHAR2(20)                     NOT NULL);

ALTER TABLE W_STORE
 ADD (WEBORDEREMAIL  NVARCHAR2(60));

ALTER TABLE W_STORE
 ADD (C_PAYMENTTERM_ID  NUMBER(10));

ALTER TABLE W_STORE
 ADD (ISDEFAULT  CHAR(1 BYTE)                       DEFAULT 'N'                   NOT NULL);

ALTER TABLE W_STORE
 ADD (URL  NVARCHAR2(120)                           DEFAULT 'http://localhost'    NOT NULL);

ALTER TABLE W_STORE
 ADD (URL  NVARCHAR2(120)                           DEFAULT 'http://localhost'    NOT NULL);
ALTER TABLE W_STORE DROP COLUMN WEBDIR;

ALTER TABLE W_STORE DROP COLUMN DOCUMENTDIR;

CREATE UNIQUE INDEX C_TAXDECLARATION_KEY ON C_TAXDECLARATION
(C_TAXDECLARATION_ID)
LOGGING
NOPARALLEL;

CREATE UNIQUE INDEX PA_HIERARCHY_KEY ON PA_HIERARCHY
(PA_HIERARCHY_ID)
LOGGING
NOPARALLEL;

CREATE UNIQUE INDEX W_STORE_WEBCONTEXT ON W_STORE
(WEBCONTEXT)
LOGGING
NOPARALLEL;

CREATE OR REPLACE FUNCTION paymentTermDiscount
(
	Amount			IN	NUMBER,
    Currency_ID     IN  NUMBER,
	PaymentTerm_ID	IN	NUMBER,
	DocDate			IN	DATE,
	PayDate			IN	DATE
)
RETURN NUMBER
/*************************************************************************
 * The contents of this file are subject to the Compiere License.  You may
 * obtain a copy of the License at    http://www.compiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Compiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: C_PaymentTerm_Discount.sql,v 1.5 2005/07/24 19:37:42 jjanke Exp $
 ***
 * Title:	Calculate Discount
 * Description:
 *	Calculate the allowable Discount Amount of the Payment Term
 *
 *	Test:	SELECT C_PaymentTerm_Discount(17777, 103, '10-DEC-1999') FROM DUAL
 ************************************************************************/

AS
	Discount			NUMBER := 0;
	CURSOR Cur_PT	IS
		SELECT	*
		FROM	C_PaymentTerm
		WHERE	C_PaymentTerm_ID = PaymentTerm_ID;
	Discount1Date		DATE;
	Discount2Date		DATE;
	Add1Date			NUMBER := 0;
	Add2Date			NUMBER := 0;
BEGIN
	--	No Data - No Discount
	IF (Amount IS NULL OR PaymentTerm_ID IS NULL OR DocDate IS NULL) THEN
		RETURN 0;
	END IF;

	FOR p IN Cur_PT LOOP	--	for convineance only
--		DBMS_OUTPUT.PUT_LINE(p.Name || ' - Doc = ' || TO_CHAR(DocDate));
		Discount1Date := TRUNC(DocDate + p.DiscountDays + p.GraceDays);
		Discount2Date := TRUNC(DocDate + p.DiscountDays2 + p.GraceDays);

		--	Next Business Day
		IF (p.IsNextBusinessDay='Y') THEN
			--	Not fully correct - only does weekends (7=Saturday, 1=Sunday)
			SELECT 	DECODE(TO_CHAR(Discount1Date,'D'), '7',2, '1',1, 0),
					DECODE(TO_CHAR(Discount2Date,'D'), '7',2, '1',1, 0)
			  INTO	Add1Date, Add2Date
			FROM 	DUAL;
			Discount1Date := Discount1Date+Add1Date;
			Discount2Date := Discount2Date+Add2Date;
		END IF;

		--	Discount 1
		IF (Discount1Date >= TRUNC(PayDate)) THEN
--			DBMS_OUTPUT.PUT_LINE('Discount 1 ' || TO_CHAR(Discount1Date) || ' ' || p.Discount);
			Discount := Amount * p.Discount / 100;
		--	Discount 2
		ELSIF (Discount2Date >= TRUNC(PayDate)) THEN
--			DBMS_OUTPUT.PUT_LINE('Discount 2 ' || TO_CHAR(Discount2Date) || ' ' || p.Discount2);
			Discount := Amount * p.Discount2 / 100;
		END IF;	
	END LOOP;
	--
    RETURN ROUND(NVL(Discount,0), 2);	--	fixed rounding
END paymentTermDiscount;
/

SHOW ERRORS;

ALTER TABLE C_JOB
 ADD (ISEMPLOYEE  CHAR(1 BYTE)                      DEFAULT 'N'                   NOT NULL);

ALTER TABLE C_TAX
 ADD (ISSALESTAX  CHAR(1 BYTE)                      DEFAULT 'N'                   NOT NULL);

ALTER TABLE M_ATTRIBUTESET
 ADD (SERNOCHARSOVERWRITE  NCHAR(1));

ALTER TABLE M_ATTRIBUTESET
 ADD (LOTCHARSOVERWRITE  NCHAR(1));

ALTER TABLE M_ATTRIBUTESET
 ADD (LOTCHAREOVERWRITE  NCHAR(1));

ALTER TABLE M_ATTRIBUTESET
 ADD (SERNOCHAREOVERWRITE  NCHAR(1));

ALTER TABLE W_STORE_TRL
MODIFY(WEBINFO  NULL);


ALTER TABLE W_STORE_TRL
MODIFY(WEBPARAM1  NULL);


ALTER TABLE W_STORE_TRL
MODIFY(WEBPARAM2  NULL);


ALTER TABLE W_STORE_TRL
MODIFY(WEBPARAM3  NULL);


ALTER TABLE W_STORE_TRL
MODIFY(WEBPARAM4  NULL);


ALTER TABLE W_STORE_TRL
MODIFY(WEBPARAM5  NULL);


ALTER TABLE W_STORE_TRL
MODIFY(WEBPARAM6  NULL);


ALTER TABLE C_ACCTSCHEMA
 ADD (COSTINGLEVEL  CHAR(1 BYTE)                    DEFAULT 'C'                   NOT NULL);

ALTER TABLE C_ACCTSCHEMA
 ADD (ISADJUSTCOGS  CHAR(1 BYTE)                    DEFAULT 'N'                   NOT NULL);

ALTER TABLE C_ACCTSCHEMA
 ADD (AD_ORGONLY_ID  NUMBER(10));

ALTER TABLE C_ACCTSCHEMA
 ADD (ISPOSTSERVICES  CHAR(1 BYTE)                  DEFAULT ('N')                 NOT NULL);

ALTER TABLE C_ACCTSCHEMA
 ADD (ISEXPLICITCOSTADJUSTMENT  CHAR(1 BYTE)        DEFAULT ('N')                 NOT NULL);

ALTER TABLE C_ACCTSCHEMA
 ADD (COMMITMENTTYPE  CHAR(1 BYTE)                  DEFAULT 'N'                   NOT NULL);

ALTER TABLE C_ACCTSCHEMA
 ADD (PROCESSING  CHAR(1 BYTE));

CREATE TABLE GL_BUDGETCONTROL
(
  GL_BUDGETCONTROL_ID  NUMBER(10)               NOT NULL,
  AD_CLIENT_ID         NUMBER(10)               NOT NULL,
  AD_ORG_ID            NUMBER(10)               NOT NULL,
  ISACTIVE             CHAR(1 BYTE)             DEFAULT 'Y'                   NOT NULL,
  CREATED              DATE                     DEFAULT SYSDATE               NOT NULL,
  CREATEDBY            NUMBER(10)               NOT NULL,
  UPDATED              DATE                     DEFAULT SYSDATE               NOT NULL,
  UPDATEDBY            NUMBER(10)               NOT NULL,
  NAME                 NVARCHAR2(60)            NOT NULL,
  DESCRIPTION          NVARCHAR2(255),
  HELP                 NVARCHAR2(2000),
  C_ACCTSCHEMA_ID      NUMBER(10)               NOT NULL,
  GL_BUDGET_ID         NUMBER(10)               NOT NULL,
  COMMITMENTTYPE       CHAR(1 BYTE)             NOT NULL,
  ISBEFOREAPPROVAL     CHAR(1 BYTE)             DEFAULT 'N'                   NOT NULL,
  BUDGETCONTROLSCOPE   CHAR(1 BYTE)             NOT NULL
)
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;

CREATE TABLE GL_FUND
(
  GL_FUND_ID       NUMBER(10)                   NOT NULL,
  AD_CLIENT_ID     NUMBER(10)                   NOT NULL,
  AD_ORG_ID        NUMBER(10)                   NOT NULL,
  ISACTIVE         CHAR(1 BYTE)                 DEFAULT 'Y'                   NOT NULL,
  CREATED          DATE                         DEFAULT SYSDATE               NOT NULL,
  CREATEDBY        NUMBER(10)                   NOT NULL,
  UPDATED          DATE                         DEFAULT SYSDATE               NOT NULL,
  UPDATEDBY        NUMBER(10)                   NOT NULL,
  NAME             NVARCHAR2(60)                NOT NULL,
  DESCRIPTION      NVARCHAR2(255),
  HELP             NVARCHAR2(2000),
  C_ACCTSCHEMA_ID  NUMBER(10)                   NOT NULL,
  AMT              NUMBER                       DEFAULT 0                     NOT NULL,
  DATEFROM         DATE,
  DATETO           DATE
)
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;

CREATE TABLE M_ATTRIBUTESETEXCLUDE
(
  M_ATTRIBUTESETEXCLUDE_ID  NUMBER(10)          NOT NULL,
  AD_CLIENT_ID              NUMBER(10)          NOT NULL,
  AD_ORG_ID                 NUMBER(10)          NOT NULL,
  ISACTIVE                  CHAR(1 BYTE)        DEFAULT 'Y'                   NOT NULL,
  CREATED                   DATE                DEFAULT SYSDATE               NOT NULL,
  CREATEDBY                 NUMBER(10)          NOT NULL,
  UPDATED                   DATE                DEFAULT SYSDATE               NOT NULL,
  UPDATEDBY                 NUMBER(10)          NOT NULL,
  M_ATTRIBUTESET_ID         NUMBER(10)          NOT NULL,
  AD_TABLE_ID               NUMBER(10)          NOT NULL,
  ISSOTRX                   CHAR(1 BYTE)        DEFAULT 'Y'                   NOT NULL
)
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;

CREATE TABLE M_LOTCTLEXCLUDE
(
  M_LOTCTLEXCLUDE_ID  NUMBER(10)                NOT NULL,
  AD_CLIENT_ID        NUMBER(10)                NOT NULL,
  AD_ORG_ID           NUMBER(10)                NOT NULL,
  ISACTIVE            CHAR(1 BYTE)              DEFAULT 'Y'                   NOT NULL,
  CREATED             DATE                      DEFAULT SYSDATE               NOT NULL,
  CREATEDBY           NUMBER(10)                NOT NULL,
  UPDATED             DATE                      DEFAULT SYSDATE               NOT NULL,
  UPDATEDBY           NUMBER(10)                NOT NULL,
  M_LOTCTL_ID         NUMBER(10)                NOT NULL,
  AD_TABLE_ID         NUMBER(10)                NOT NULL,
  ISSOTRX             CHAR(1 BYTE)              DEFAULT 'N'                   NOT NULL
)
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;

CREATE TABLE M_SERNOCTLEXCLUDE
(
  M_SERNOCTLEXCLUDE_ID  NUMBER(10)              NOT NULL,
  AD_CLIENT_ID          NUMBER(10)              NOT NULL,
  AD_ORG_ID             NUMBER(10)              NOT NULL,
  ISACTIVE              CHAR(1 BYTE)            DEFAULT 'Y'                   NOT NULL,
  CREATED               DATE                    DEFAULT SYSDATE               NOT NULL,
  CREATEDBY             NUMBER(10)              NOT NULL,
  UPDATED               DATE                    DEFAULT SYSDATE               NOT NULL,
  UPDATEDBY             NUMBER(10)              NOT NULL,
  M_SERNOCTL_ID         NUMBER(10)              NOT NULL,
  AD_TABLE_ID           NUMBER(10)              NOT NULL,
  ISSOTRX               CHAR(1 BYTE)            DEFAULT 'N'                   NOT NULL
)
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;

ALTER TABLE M_WAREHOUSE
 ADD (M_WAREHOUSESOURCE_ID  NUMBER(10));

ALTER TABLE M_WAREHOUSE
 ADD (REPLENISHMENTCLASS  NVARCHAR2(60));

CREATE UNIQUE INDEX GL_BUDGETCONTROL_KEY ON GL_BUDGETCONTROL
(GL_BUDGETCONTROL_ID)
LOGGING
NOPARALLEL;

CREATE UNIQUE INDEX GL_FUND_KEY ON GL_FUND
(GL_FUND_ID)
LOGGING
NOPARALLEL;

CREATE UNIQUE INDEX M_ATTRIBUTESETEXCLUDE_KEY ON M_ATTRIBUTESETEXCLUDE
(M_ATTRIBUTESETEXCLUDE_ID)
LOGGING
NOPARALLEL;

CREATE UNIQUE INDEX M_LOTCTLEXCLUDE_KEY ON M_LOTCTLEXCLUDE
(M_LOTCTLEXCLUDE_ID)
LOGGING
NOPARALLEL;

CREATE UNIQUE INDEX M_SERNOCTLEXCLUDE_KEY ON M_SERNOCTLEXCLUDE
(M_SERNOCTLEXCLUDE_ID)
LOGGING
NOPARALLEL;

CREATE TABLE GL_FUNDRESTRICTION
(
  GL_FUNDRESTRICTION_ID  NUMBER(10)             NOT NULL,
  AD_CLIENT_ID           NUMBER(10)             NOT NULL,
  AD_ORG_ID              NUMBER(10)             NOT NULL,
  ISACTIVE               CHAR(1 BYTE)           DEFAULT 'Y'                   NOT NULL,
  CREATED                DATE                   DEFAULT SYSDATE               NOT NULL,
  CREATEDBY              NUMBER(10)             NOT NULL,
  UPDATED                DATE                   DEFAULT SYSDATE               NOT NULL,
  UPDATEDBY              NUMBER(10)             NOT NULL,
  NAME                   NVARCHAR2(60)          NOT NULL,
  DESCRIPTION            NVARCHAR2(255),
  GL_FUND_ID             NUMBER(10)             NOT NULL,
  C_ELEMENTVALUE_ID      NUMBER(10)             NOT NULL
)
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;

CREATE UNIQUE INDEX GL_FUNDRESTRICTION_KEY ON GL_FUNDRESTRICTION
(GL_FUNDRESTRICTION_ID)
LOGGING
NOPARALLEL;

ALTER TABLE AD_PROCESS
 ADD (ISSERVERPROCESS  CHAR(1 BYTE)                 DEFAULT 'N'                   NOT NULL);

ALTER TABLE AD_CHANGELOG
 ADD (DESCRIPTION  NVARCHAR2(255));

ALTER TABLE AD_CLIENTINFO DROP COLUMN ACCT2_ACTIVE;

ALTER TABLE AD_CLIENTINFO DROP COLUMN C_ACCTSCHEMA2_ID;

ALTER TABLE AD_CLIENTINFO DROP COLUMN ACCT3_ACTIVE;

ALTER TABLE AD_CLIENTINFO DROP COLUMN C_ACCTSCHEMA3_ID;

ALTER TABLE AD_CLIENTINFO DROP COLUMN M_PRICELIST_ID;

ALTER TABLE AD_ROLE
 ADD (CONFIRMQUERYRECORDS  NUMBER(10)               DEFAULT 0                     NOT NULL);

ALTER TABLE AD_ROLE
 ADD (MAXQUERYRECORDS  NUMBER(10)                   DEFAULT 0                     NOT NULL);

ALTER TABLE AD_USER
 ADD (ISFULLBPACCESS  CHAR(1 BYTE)                  DEFAULT 'Y'                   NOT NULL);

ALTER TABLE AD_USER
 ADD (C_JOB_ID  NUMBER(10));

ALTER TABLE AD_USER
 ADD (LDAPUSER  NVARCHAR2(60));

ALTER TABLE AD_USER
 ADD (LDAPUSER  NVARCHAR2(60));
ALTER TABLE AD_USER DROP COLUMN ISLDAPAUTHORIZED;

/* This object may not be sorted properly in the script due to cirular references */
CREATE TABLE AD_USERBPACCESS
(
  AD_USERBPACCESS_ID  NUMBER(10)                NOT NULL,
  AD_CLIENT_ID        NUMBER(10)                NOT NULL,
  AD_ORG_ID           NUMBER(10)                NOT NULL,
  ISACTIVE            CHAR(1 BYTE)              DEFAULT 'Y'                   NOT NULL,
  CREATED             DATE                      DEFAULT SYSDATE               NOT NULL,
  CREATEDBY           NUMBER(10)                NOT NULL,
  UPDATED             DATE                      DEFAULT SYSDATE               NOT NULL,
  UPDATEDBY           NUMBER(10)                NOT NULL,
  AD_USER_ID          NUMBER(10)                NOT NULL,
  BPACCESSTYPE        CHAR(1 BYTE)              NOT NULL,
  R_REQUESTTYPE_ID    NUMBER(10),
  DOCBASETYPE         CHAR(3 BYTE)
)
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;

ALTER TABLE AD_USERMAIL
 ADD (SUBJECT  NVARCHAR2(255));

ALTER TABLE AD_USERMAIL
 ADD (MAILTEXT  NVARCHAR2(2000));

/* This object may not be sorted properly in the script due to cirular references */
CREATE TABLE AD_USERQUERY
(
  AD_USERQUERY_ID  NUMBER(10)                   NOT NULL,
  AD_CLIENT_ID     NUMBER(10)                   NOT NULL,
  AD_ORG_ID        NUMBER(10)                   NOT NULL,
  ISACTIVE         CHAR(1 BYTE)                 DEFAULT 'Y'                   NOT NULL,
  CREATED          DATE                         DEFAULT SYSDATE               NOT NULL,
  CREATEDBY        NUMBER(10)                   NOT NULL,
  UPDATED          DATE                         DEFAULT SYSDATE               NOT NULL,
  UPDATEDBY        NUMBER(10)                   NOT NULL,
  NAME             NVARCHAR2(60)                NOT NULL,
  DESCRIPTION      NVARCHAR2(255),
  AD_USER_ID       NUMBER(10),
  AD_TABLE_ID      NUMBER(10)                   NOT NULL,
  CODE             NVARCHAR2(2000)
)
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;

ALTER TABLE A_ASSET
 ADD (C_PROJECT_ID  NUMBER(10));

ALTER TABLE A_ASSET
 ADD (C_BPARTNERSR_ID  NUMBER(10));

ALTER TABLE A_ASSET
 ADD (M_INOUTLINE_ID  NUMBER(10));

ALTER TABLE C_ACCTSCHEMA_DEFAULT
 ADD (C_RECEIVABLE_SERVICES_ACCT  NUMBER(10)      default 0  NOT NULL);

ALTER TABLE C_ACCTSCHEMA_DEFAULT
 ADD (P_INVENTORYCLEARING_ACCT  NUMBER(10)        default 0  NOT NULL);

ALTER TABLE C_ACCTSCHEMA_DEFAULT
 ADD (P_COSTADJUSTMENT_ACCT  NUMBER(10)           default 0  NOT NULL);

ALTER TABLE C_ACCTSCHEMA_GL
 ADD (COMMITMENTOFFSET_ACCT  NUMBER(10)           default 0  NOT NULL);

ALTER TABLE C_ALLOCATIONLINE DROP COLUMN C_CURRENCY_ID;

ALTER TABLE C_BP_CUSTOMER_ACCT
 ADD (C_RECEIVABLE_SERVICES_ACCT  NUMBER(10));

ALTER TABLE C_BP_GROUP_ACCT
 ADD (C_RECEIVABLE_SERVICES_ACCT  NUMBER(10));

ALTER TABLE C_INVOICELINE
 ADD (C_PROJECT_ID  NUMBER(10));

ALTER TABLE C_LANDEDCOST
MODIFY(LANDEDCOSTDISTRIBUTION  NOT NULL);


ALTER TABLE C_LANDEDCOST
 ADD (PROCESSING  CHAR(1 BYTE));

ALTER TABLE C_LANDEDCOSTALLOCATION
 ADD (C_LANDEDCOSTALLOCATION_ID  NUMBER(10)         NOT NULL);

ALTER TABLE C_LANDEDCOSTALLOCATION
 ADD (C_INVOICELINE_ID  NUMBER(10)                  NOT NULL);

ALTER TABLE C_LANDEDCOSTALLOCATION
 ADD (M_ATTRIBUTESETINSTANCE_ID  NUMBER(10));

ALTER TABLE C_LANDEDCOSTALLOCATION
 ADD (M_COSTELEMENT_ID  NUMBER(10)                  NOT NULL);

ALTER TABLE C_LANDEDCOSTALLOCATION
 ADD (BASE  NUMBER                                  DEFAULT 0                     NOT NULL);

-- Column to be dropped is part of a multi-column constraint.
-- Oracle requires that the constraint be dropped first.
-- There may be another statement later in the script that tries to drop
-- the constraint again.  Errors produced by it can be ignored.
ALTER TABLE C_LANDEDCOSTALLOCATION DROP CONSTRAINT C_LANDEDCOSTALLOCATION_KEY;
ALTER TABLE C_LANDEDCOSTALLOCATION DROP COLUMN C_LANDEDCOST_ID;

ALTER TABLE C_ORDERLINE
 ADD (C_PROJECT_ID  NUMBER(10));

ALTER TABLE C_ORDERLINE
 ADD (PRICECOST  NUMBER);

ALTER TABLE C_ORDERLINE
 ADD (QTYLOSTSALES  NUMBER                          DEFAULT 0                     NOT NULL);

/* This object may not be sorted properly in the script due to cirular references */
CREATE TABLE C_PAYMENTALLOCATE
(
  C_PAYMENTALLOCATE_ID  NUMBER(10)              NOT NULL,
  AD_CLIENT_ID          NUMBER(10)              NOT NULL,
  AD_ORG_ID             NUMBER(10)              NOT NULL,
  ISACTIVE              CHAR(1 BYTE)            DEFAULT 'Y'                   NOT NULL,
  CREATED               DATE                    DEFAULT SYSDATE               NOT NULL,
  CREATEDBY             NUMBER(10)              NOT NULL,
  UPDATED               DATE                    DEFAULT SYSDATE               NOT NULL,
  UPDATEDBY             NUMBER(10)              NOT NULL,
  C_PAYMENT_ID          NUMBER(10)              NOT NULL,
  C_INVOICE_ID          NUMBER(10)              NOT NULL,
  AMOUNT                NUMBER                  DEFAULT 0                     NOT NULL,
  DISCOUNTAMT           NUMBER                  DEFAULT 0                     NOT NULL,
  WRITEOFFAMT           NUMBER                  DEFAULT 0                     NOT NULL,
  OVERUNDERAMT          NUMBER                  DEFAULT 0                     NOT NULL,
  INVOICEAMT            NUMBER                  DEFAULT 0,
  C_ALLOCATIONLINE_ID   NUMBER(10)
)
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;

ALTER TABLE C_PROJECT
 ADD (C_BPARTNERSR_ID  NUMBER(10));

/* This object may not be sorted properly in the script due to cirular references */
CREATE TABLE C_TAXDECLARATIONACCT
(
  C_TAXDECLARATIONACCT_ID  NUMBER(10)           NOT NULL,
  AD_CLIENT_ID             NUMBER(10)           NOT NULL,
  AD_ORG_ID                NUMBER(10)           NOT NULL,
  ISACTIVE                 CHAR(1 BYTE)         DEFAULT 'Y'                   NOT NULL,
  CREATED                  DATE                 DEFAULT SYSDATE               NOT NULL,
  CREATEDBY                NUMBER(10)           NOT NULL,
  UPDATED                  DATE                 DEFAULT SYSDATE               NOT NULL,
  UPDATEDBY                NUMBER(10)           NOT NULL,
  DESCRIPTION              NVARCHAR2(255),
  C_TAXDECLARATION_ID      NUMBER(10)           NOT NULL,
  FACT_ACCT_ID             NUMBER(10)           NOT NULL,
  C_ACCTSCHEMA_ID          NUMBER(10)           NOT NULL
)
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;

/* This object may not be sorted properly in the script due to cirular references */
CREATE TABLE C_TAXDECLARATIONLINE
(
  C_TAXDECLARATIONLINE_ID  NUMBER(10)           NOT NULL,
  AD_CLIENT_ID             NUMBER(10)           NOT NULL,
  AD_ORG_ID                NUMBER(10)           NOT NULL,
  ISACTIVE                 CHAR(1 BYTE)         DEFAULT 'Y'                   NOT NULL,
  CREATED                  DATE                 DEFAULT SYSDATE               NOT NULL,
  CREATEDBY                NUMBER(10)           NOT NULL,
  UPDATED                  DATE                 DEFAULT SYSDATE               NOT NULL,
  UPDATEDBY                NUMBER(10)           NOT NULL,
  DESCRIPTION              NVARCHAR2(255),
  LINE                     NUMBER(10)           DEFAULT 0                     NOT NULL,
  C_TAXDECLARATION_ID      NUMBER(10)           NOT NULL,
  C_BPARTNER_ID            NUMBER(10)           NOT NULL,
  C_TAX_ID                 NUMBER(10)           NOT NULL,
  C_INVOICE_ID             NUMBER(10),
  C_INVOICELINE_ID         NUMBER(10),
  C_ALLOCATIONLINE_ID      NUMBER(10),
  TAXBASEAMT               NUMBER               DEFAULT 0                     NOT NULL,
  TAXAMT                   NUMBER               DEFAULT 0                     NOT NULL,
  DATEACCT                 DATE                 NOT NULL
)
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;

ALTER TABLE I_GLJOURNAL
 ADD (ISCREATENEWBATCH  CHAR(1 BYTE));

ALTER TABLE I_GLJOURNAL
 ADD (ISCREATENEWJOURNAL  CHAR(1 BYTE));

ALTER TABLE I_PRODUCT
 ADD (PRICESTD  NUMBER);

ALTER TABLE I_PRODUCT
 ADD (PRICELIMIT  NUMBER);

ALTER TABLE M_COST
 ADD (M_ATTRIBUTESETINSTANCE_ID  NUMBER(10)         NOT NULL);

ALTER TABLE M_COST
 ADD (CURRENTQTY  NUMBER                            DEFAULT 0                     NOT NULL);

ALTER TABLE M_COST
 ADD (CUMULATEDAMT  NUMBER                          DEFAULT 0);

ALTER TABLE M_COST
 ADD (CUMULATEDQTY  NUMBER                          DEFAULT 0);

ALTER TABLE M_COST
MODIFY(FUTURECOSTPRICE  NULL);


ALTER TABLE M_COST
 ADD (PERCENT  NUMBER(10)                           DEFAULT 0);

/* This object may not be sorted properly in the script due to cirular references */
CREATE TABLE M_COSTDETAIL
(
  M_COSTDETAIL_ID            NUMBER(10)         NOT NULL,
  AD_CLIENT_ID               NUMBER(10)         NOT NULL,
  AD_ORG_ID                  NUMBER(10)         NOT NULL,
  C_ACCTSCHEMA_ID            NUMBER(10)         NOT NULL,
  M_PRODUCT_ID               NUMBER(10)         NOT NULL,
  M_ATTRIBUTESETINSTANCE_ID  NUMBER(10)         NOT NULL,
  ISACTIVE                   CHAR(1 BYTE)       DEFAULT 'Y'                   NOT NULL,
  CREATED                    DATE               DEFAULT SYSDATE               NOT NULL,
  CREATEDBY                  NUMBER(10)         NOT NULL,
  UPDATED                    DATE               DEFAULT SYSDATE               NOT NULL,
  UPDATEDBY                  NUMBER(10)         NOT NULL,
  M_COSTELEMENT_ID           NUMBER(10),
  C_ORDERLINE_ID             NUMBER(10),
  M_INOUTLINE_ID             NUMBER(10),
  C_INVOICELINE_ID           NUMBER(10),
  M_MOVEMENTLINE_ID          NUMBER(10),
  M_INVENTORYLINE_ID         NUMBER(10),
  M_PRODUCTIONLINE_ID        NUMBER(10),
  C_PROJECTISSUE_ID          NUMBER(10),
  ISSOTRX                    CHAR(1 BYTE)       DEFAULT 'Y'                   NOT NULL,
  AMT                        NUMBER             DEFAULT 0                     NOT NULL,
  QTY                        NUMBER             DEFAULT 0                     NOT NULL,
  DELTAAMT                   NUMBER             DEFAULT 0,
  DELTAQTY                   NUMBER             DEFAULT 0,
  DESCRIPTION                NVARCHAR2(255),
  PROCESSED                  CHAR(1 BYTE)       DEFAULT 'N'                   NOT NULL
)
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;

/* This object may not be sorted properly in the script due to cirular references */
CREATE TABLE M_COSTQUEUE
(
  M_COSTQUEUE_ID             NUMBER(10)         NOT NULL,
  AD_CLIENT_ID               NUMBER(10)         NOT NULL,
  AD_ORG_ID                  NUMBER(10)         NOT NULL,
  ISACTIVE                   CHAR(1 BYTE)       DEFAULT 'Y'                   NOT NULL,
  CREATED                    DATE               DEFAULT SYSDATE               NOT NULL,
  CREATEDBY                  NUMBER(10)         NOT NULL,
  UPDATED                    DATE               DEFAULT SYSDATE               NOT NULL,
  UPDATEDBY                  NUMBER(10)         NOT NULL,
  M_COSTTYPE_ID              NUMBER(10)         NOT NULL,
  C_ACCTSCHEMA_ID            NUMBER(10)         NOT NULL,
  M_PRODUCT_ID               NUMBER(10)         NOT NULL,
  M_ATTRIBUTESETINSTANCE_ID  NUMBER(10)         NOT NULL,
  M_COSTELEMENT_ID           NUMBER(10)         NOT NULL,
  CURRENTCOSTPRICE           NUMBER             DEFAULT 0                     NOT NULL,
  CURRENTQTY                 NUMBER             DEFAULT 0                     NOT NULL
)
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;

ALTER TABLE M_INOUTLINE
 ADD (C_PROJECT_ID  NUMBER(10));

ALTER TABLE M_MATCHINV
 ADD (M_ATTRIBUTESETINSTANCE_ID  NUMBER(10));

ALTER TABLE M_MATCHPO
 ADD (M_ATTRIBUTESETINSTANCE_ID  NUMBER(10));

ALTER TABLE M_MOVEMENTLINEMA
MODIFY(MOVEMENTQTY NUMBER);


ALTER TABLE M_PRODUCT
 ADD (ISEXCLUDEAUTODELIVERY  CHAR(1 BYTE)           DEFAULT 'N'                   NOT NULL);

ALTER TABLE M_PRODUCT_ACCT
 ADD (P_INVENTORYCLEARING_ACCT  NUMBER(10));

ALTER TABLE M_PRODUCT_ACCT
 ADD (P_COSTADJUSTMENT_ACCT  NUMBER(10));

ALTER TABLE M_PRODUCT_CATEGORY_ACCT
 ADD (COSTINGLEVEL  CHAR(1 BYTE));

ALTER TABLE M_PRODUCT_CATEGORY_ACCT
 ADD (P_INVENTORYCLEARING_ACCT  NUMBER(10));

ALTER TABLE M_PRODUCT_CATEGORY_ACCT
 ADD (P_COSTADJUSTMENT_ACCT  NUMBER(10));

ALTER TABLE M_REPLENISH
 ADD (M_WAREHOUSESOURCE_ID  NUMBER(10));

ALTER TABLE M_REQUISITION
 ADD (DATEDOC  DATE                                 DEFAULT sysDate               NOT NULL);

ALTER TABLE M_REQUISITIONLINE
 ADD (M_ATTRIBUTESETINSTANCE_ID  NUMBER);

ALTER TABLE T_REPLENISH
MODIFY(REPLENISHMENTCREATE CHAR(3 BYTE));


ALTER TABLE T_REPLENISH
 ADD (M_WAREHOUSESOURCE_ID  NUMBER(10));

ALTER TABLE W_COUNTERCOUNT DROP COLUMN COUNTER;

/* This object may not be sorted properly in the script due to cirular references */
CREATE UNIQUE INDEX AD_USERBPACCESS_KEY ON AD_USERBPACCESS
(AD_USERBPACCESS_ID)
LOGGING
NOPARALLEL;

/* This object may not be sorted properly in the script due to cirular references */
CREATE UNIQUE INDEX AD_USERQUERY_KEY ON AD_USERQUERY
(AD_USERQUERY_ID)
LOGGING
NOPARALLEL;

DROP INDEX C_LANDEDCOSTALLOCATION_KEY;
/* This object may not be sorted properly in the script due to cirular references */
CREATE UNIQUE INDEX C_LANDEDCOSTALLOCATION_KEY ON C_LANDEDCOSTALLOCATION
(C_LANDEDCOSTALLOCATION_ID)
LOGGING
NOPARALLEL;

/* This object may not be sorted properly in the script due to cirular references */
CREATE UNIQUE INDEX C_PAYMENTALLOCATE_KEY ON C_PAYMENTALLOCATE
(C_PAYMENTALLOCATE_ID)
LOGGING
NOPARALLEL;

/* This object may not be sorted properly in the script due to cirular references */
CREATE UNIQUE INDEX C_TAXDECLARATIONACCT_KEY ON C_TAXDECLARATIONACCT
(C_TAXDECLARATIONACCT_ID)
LOGGING
NOPARALLEL;

/* This object may not be sorted properly in the script due to cirular references */
CREATE UNIQUE INDEX C_TAXDECLARATIONLINE_KEY ON C_TAXDECLARATIONLINE
(C_TAXDECLARATIONLINE_ID)
LOGGING
NOPARALLEL;

/* This object may not be sorted properly in the script due to cirular references */
CREATE UNIQUE INDEX M_COSTDETAIL_KEY ON M_COSTDETAIL
(M_COSTDETAIL_ID)
LOGGING
NOPARALLEL;

/* This object may not be sorted properly in the script due to cirular references */
CREATE INDEX M_COSTDETAIL_PRODUCT ON M_COSTDETAIL
(AD_ORG_ID, M_PRODUCT_ID, M_ATTRIBUTESETINSTANCE_ID)
LOGGING
NOPARALLEL;

/* This object may not be sorted properly in the script due to cirular references */
CREATE UNIQUE INDEX M_COSTQUEUE_KEY ON M_COSTQUEUE
(M_COSTQUEUE_ID)
LOGGING
NOPARALLEL;

/* This object may not be sorted properly in the script due to cirular references */
CREATE INDEX M_COSTQUEUE_PRODUCT ON M_COSTQUEUE
(C_ACCTSCHEMA_ID, M_PRODUCT_ID, M_COSTELEMENT_ID)
LOGGING
NOPARALLEL;

DROP INDEX M_COST_KEY;
/* This object may not be sorted properly in the script due to cirular references */
CREATE UNIQUE INDEX M_COST_KEY ON M_COST
(AD_CLIENT_ID, AD_ORG_ID, M_PRODUCT_ID, M_COSTTYPE_ID, C_ACCTSCHEMA_ID, 
M_COSTELEMENT_ID, M_ATTRIBUTESETINSTANCE_ID)
LOGGING
NOPARALLEL;

/* This object may not be sorted properly in the script due to cirular references */
CREATE OR REPLACE FUNCTION productAttribute
(
    p_M_AttributeSetInstance_ID     IN NUMBER
)
RETURN NVARCHAR2
/*************************************************************************
 * The contents of this file are subject to the Compiere License.  You may
 * obtain a copy of the License at    http://www.compiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Compiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: M_Attribute_Name.sql,v 1.4 2005/10/11 02:28:41 jjanke Exp $
 ***
 * Title:	Return Instance Attribute Info
 * Description:
 *		
 * Test:
    SELECT M_Attribute_Name (M_AttributeSetInstance_ID) 
    FROM M_InOutLine WHERE M_AttributeSetInstance_ID > 0
    --
    SELECT p.Name
    FROM C_InvoiceLine il LEFT OUTER JOIN M_Product p ON (il.M_Product_ID=p.M_Product_ID);
    SELECT p.Name || M_Attribute_Name (il.M_AttributeSetInstance_ID) 
    FROM C_InvoiceLine il LEFT OUTER JOIN M_Product p ON (il.M_Product_ID=p.M_Product_ID);
    
 ************************************************************************/
AS
    v_Name          NVARCHAR2(2000) := NULL;
    v_NameAdd       NVARCHAR2(2000) := '';
    --
    v_Lot           M_AttributeSetInstance.Lot%TYPE;
    v_LotStart      M_AttributeSet.LotCharSOverwrite%TYPE;
    v_LotEnd        M_AttributeSet.LotCharEOverwrite%TYPE;
    v_SerNo         M_AttributeSetInstance.SerNo%TYPE;
    v_SerNoStart    M_AttributeSet.SerNoCharSOverwrite%TYPE;
    v_SerNoEnd      M_AttributeSet.SerNoCharEOverwrite%TYPE;
    v_GuaranteeDate M_AttributeSetInstance.GuaranteeDate%TYPE;
    --
    CURSOR CUR_Attributes IS
        SELECT ai.Value, a.Name
        FROM M_AttributeInstance ai
          INNER JOIN M_Attribute a ON (ai.M_Attribute_ID=a.M_Attribute_ID AND a.IsInstanceAttribute='Y')
        WHERE ai.M_AttributeSetInstance_ID=p_M_AttributeSetInstance_ID;

BEGIN
/*    --  Get Product Name
    SELECT Name 
      INTO v_Name
    FROM M_Product WHERE M_Product_ID=p_M_Product_ID;
*/
    --  Get Product Attribute Set Instance
    IF (p_M_AttributeSetInstance_ID > 0) THEN
        SELECT asi.Lot, asi.SerNo, asi.GuaranteeDate,
            COALESCE(a.SerNoCharSOverwrite, N'#'), COALESCE(a.SerNoCharEOverwrite, N''),
            COALESCE(a.LotCharSOverwrite, N'«'), COALESCE(a.LotCharEOverwrite, N'»')
          INTO v_Lot, v_SerNo, v_GuaranteeDate,
            v_SerNoStart, v_SerNoEnd, v_LotStart, v_LotEnd
        FROM M_AttributeSetInstance asi
          INNER JOIN M_AttributeSet a ON (asi.M_AttributeSet_ID=a.M_AttributeSet_ID)
        WHERE asi.M_AttributeSetInstance_ID=p_M_AttributeSetInstance_ID;
        --
        IF (v_SerNo IS NOT NULL) THEN
            v_NameAdd := v_NameAdd || v_SerNoStart || v_SerNo || v_SerNoEnd || ' ';
        END IF;
        IF (v_Lot IS NOT NULL) THEN
            v_NameAdd := v_NameAdd || v_LotStart || v_Lot || v_LotEnd || ' ';
        END IF;
        IF (v_GuaranteeDate IS NOT NULL) THEN
            v_NameAdd := v_NameAdd || v_GuaranteeDate || ' ';
        END IF;
        --
        FOR a IN CUR_Attributes LOOP
            v_NameAdd := v_NameAdd || a.Name || ':' || a.Value || ' ';
        END LOOP;
        --
        IF (LENGTH(v_NameAdd) > 0) THEN
            v_Name := v_Name || ' (' || TRIM(v_NameAdd) || ')';
        END IF;
    END IF;
    
    RETURN v_Name;
END productAttribute;
/

SHOW ERRORS;

/* This object may not be sorted properly in the script due to cirular references */
CREATE OR REPLACE FUNCTION bomQtyOnHand
( 
	Product_ID 		IN NUMBER,
    Warehouse_ID	IN NUMBER,
	Locator_ID		IN NUMBER	--	Only used, if warehouse is null
)
RETURN NUMBER
/******************************************************************************
 * ** Compiere Product **             Copyright (c) 1999-2001 Accorto, Inc. USA
 * Open  Source  Software        Provided "AS IS" without warranty or liability
 * When you use any parts (changed or unchanged), add  "Powered by Compiere" to
 * your product name;  See license details http://www.compiere.org/license.html
 ******************************************************************************
 *	Return quantity on hand for BOM
 */
AS
	myWarehouse_ID	NUMBER;
 	Quantity		NUMBER := 99999;	--	unlimited
	IsBOM			CHAR(1);
	IsStocked		CHAR(1);
	ProductType		CHAR(1);
 	ProductQty		NUMBER;
	StdPrecision	NUMBER;
	--	Get BOM Product info
	CURSOR CUR_BOM IS
		SELECT b.M_ProductBOM_ID, b.BOMQty, p.IsBOM, p.IsStocked, p.ProductType
		FROM M_Product_BOM b, M_Product p
		WHERE b.M_ProductBOM_ID=p.M_Product_ID
		  AND b.M_Product_ID=Product_ID;
	--
BEGIN
	--	Check Parameters
	myWarehouse_ID := Warehouse_ID;
	IF (myWarehouse_ID IS NULL) THEN
		IF (Locator_ID IS NULL) THEN
			RETURN 0;
		ELSE
			SELECT 	SUM(M_Warehouse_ID) INTO myWarehouse_ID
			FROM	M_Locator
			WHERE	M_Locator_ID=Locator_ID;
		END IF;
	END IF;
	IF (myWarehouse_ID IS NULL) THEN
		RETURN 0;
	END IF;
--	DBMS_OUTPUT.PUT_LINE('Warehouse=' || myWarehouse_ID);

	--	Check, if product exists and if it is stocked
	BEGIN
		SELECT	IsBOM, ProductType, IsStocked
	 	  INTO	IsBOM, ProductType, IsStocked
		FROM M_Product
		WHERE M_Product_ID=Product_ID;
		--
	EXCEPTION	--	not found
		WHEN OTHERS THEN
			RETURN 0;
	END;
	--	Unimited capacity if no item
	IF (IsBOM='N' AND (ProductType<>'I' OR IsStocked='N')) THEN
		RETURN Quantity;
	--	Stocked item
	ELSIF (IsStocked='Y') THEN
		--	Get ProductQty
		SELECT 	NVL(SUM(QtyOnHand), 0)
		  INTO	ProductQty
		FROM 	M_Storage s
		WHERE M_Product_ID=Product_ID
		  AND EXISTS (SELECT * FROM M_Locator l WHERE s.M_Locator_ID=l.M_Locator_ID
		  	AND l.M_Warehouse_ID=myWarehouse_ID);
		--
	--	DBMS_OUTPUT.PUT_LINE('Qty=' || ProductQty);
		RETURN ProductQty;
	END IF;

	--	Go though BOM
--	DBMS_OUTPUT.PUT_LINE('BOM');
	FOR bom IN CUR_BOM LOOP
		--	Stocked Items "leaf node"
		IF (bom.ProductType = 'I' AND bom.IsStocked = 'Y') THEN
			--	Get ProductQty
			SELECT 	NVL(SUM(QtyOnHand), 0)
			  INTO	ProductQty
			FROM 	M_Storage s
			WHERE M_Product_ID=bom.M_ProductBOM_ID
			  AND EXISTS (SELECT * FROM M_Locator l WHERE s.M_Locator_ID=l.M_Locator_ID
			  	AND l.M_Warehouse_ID=myWarehouse_ID);
			--	Get Rounding Precision
			SELECT 	NVL(MAX(u.StdPrecision), 0)
			  INTO	StdPrecision
			FROM 	C_UOM u, M_Product p 
			WHERE u.C_UOM_ID=p.C_UOM_ID AND p.M_Product_ID=bom.M_ProductBOM_ID;
			--	How much can we make with this product
			ProductQty := ROUND (ProductQty/bom.BOMQty, StdPrecision);
			--	How much can we make overall
			IF (ProductQty < Quantity) THEN
				Quantity := ProductQty;
			END IF;
		--	Another BOM
		ELSIF (bom.IsBOM = 'Y') THEN
			ProductQty := bomQtyOnHand (bom.M_ProductBOM_ID, myWarehouse_ID, Locator_ID);
			--	How much can we make overall
			IF (ProductQty < Quantity) THEN
				Quantity := ProductQty;
			END IF;
		END IF;
	END LOOP;	--	BOM

	IF (Quantity > 0) THEN
		--	Get Rounding Precision for Product
		SELECT 	NVL(MAX(u.StdPrecision), 0)
		  INTO	StdPrecision
		FROM 	C_UOM u, M_Product p 
		WHERE u.C_UOM_ID=p.C_UOM_ID AND p.M_Product_ID=Product_ID;
		--
		RETURN ROUND (Quantity, StdPrecision);
	END IF;
	RETURN 0;
END bomQtyOnHand;
/

SHOW ERRORS;

/* This object may not be sorted properly in the script due to cirular references */
CREATE OR REPLACE FUNCTION invoiceDiscount
(
	p_C_Invoice_ID		        IN NUMBER,
	p_PayDate			        IN	DATE,
	p_C_InvoicePaySchedule_ID	IN	NUMBER
)
RETURN NUMBER
/*************************************************************************
 * The contents of this file are subject to the Compiere License.  You may
 * obtain a copy of the License at    http://www.compiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Compiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: C_Invoice_Discount.sql,v 1.6 2005/07/24 19:37:42 jjanke Exp $
 ***
 * Title:	Calculate Payment Discount Amount
 * Description:
 *			- Calculate discountable amount (i.e. with or without tax)
 *			- Calculate and return payment discount
 ************************************************************************/
AS
	v_Amount			NUMBER;
	v_IsDiscountLineAmt	CHAR(1);
	v_GrandTotal		NUMBER;
	v_TotalLines		NUMBER;
	v_C_PaymentTerm_ID	NUMBER(10);
	v_DocDate			DATE;
	v_PayDate			DATE := SysDate;
    v_IsPayScheduleValid    CHAR(1);

BEGIN
	SELECT 	ci.IsDiscountLineAmt, i.GrandTotal, i.TotalLines,
		i.C_PaymentTerm_ID, i.DateInvoiced, i.IsPayScheduleValid
	  INTO 	v_IsDiscountLineAmt, v_GrandTotal, v_TotalLines,
		v_C_PaymentTerm_ID, v_DocDate, v_IsPayScheduleValid
	FROM 	AD_ClientInfo ci, C_Invoice i
	WHERE 	ci.AD_Client_ID=i.AD_Client_ID
	  AND 	i.C_Invoice_ID=p_C_Invoice_ID;
	--	What Amount is the Discount Base?
 	IF (v_IsDiscountLineAmt = 'Y') THEN
		v_Amount := v_TotalLines;
	ELSE
		v_Amount := v_GrandTotal;
	END IF;

	--	Anything to discount?
	IF (v_Amount = 0) THEN
		RETURN 0;
   	END IF;
	IF (p_PayDate IS NOT NULL) THEN
		v_PayDate := p_PayDate;
  	END IF;

    --  Valid Payment Schedule
    IF (v_IsPayScheduleValid='Y' AND p_C_InvoicePaySchedule_ID > 0) THEN
        SELECT COALESCE(MAX(DiscountAmt),0)
          INTO v_Amount
        FROM C_InvoicePaySchedule
        WHERE C_InvoicePaySchedule_ID=p_C_InvoicePaySchedule_ID
          AND DiscountDate <= v_PayDate;
        --
        RETURN v_Amount;
    END IF;

	--	return discount amount	
	RETURN paymentTermDiscount (v_Amount, 0, v_C_PaymentTerm_ID, v_DocDate, p_PayDate);

--	Most likely if invoice not found
EXCEPTION
	WHEN OTHERS THEN
		RETURN NULL;
END invoiceDiscount;
/

SHOW ERRORS;

/* This object may not be sorted properly in the script due to cirular references */
CREATE OR REPLACE FORCE VIEW RV_BPARTNER
(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, C_BPARTNER_ID, VALUE, NAME, 
 NAME2, DESCRIPTION, ISSUMMARY, C_BP_GROUP_ID, ISONETIME, 
 ISPROSPECT, ISVENDOR, ISCUSTOMER, ISEMPLOYEE, ISSALESREP, 
 REFERENCENO, DUNS, URL, AD_LANGUAGE, TAXID, 
 ISTAXEXEMPT, C_INVOICESCHEDULE_ID, RATING, SALESVOLUME, NUMBEREMPLOYEES, 
 NAICS, FIRSTSALE, ACQUSITIONCOST, POTENTIALLIFETIMEVALUE, ACTUALLIFETIMEVALUE, 
 SHAREOFCUSTOMER, PAYMENTRULE, SO_CREDITLIMIT, SO_CREDITUSED, SO_CREDITAVAILABLE, 
 C_PAYMENTTERM_ID, M_PRICELIST_ID, M_DISCOUNTSCHEMA_ID, C_DUNNING_ID, ISDISCOUNTPRINTED, 
 SO_DESCRIPTION, POREFERENCE, PAYMENTRULEPO, PO_PRICELIST_ID, PO_DISCOUNTSCHEMA_ID, 
 PO_PAYMENTTERM_ID, DOCUMENTCOPIES, C_GREETING_ID, INVOICERULE, DELIVERYRULE, 
 FREIGHTCOSTRULE, DELIVERYVIARULE, SALESREP_ID, SENDEMAIL, BPARTNER_PARENT_ID, 
 INVOICE_PRINTFORMAT_ID, SOCREDITSTATUS, SHELFLIFEMINPCT, AD_ORGBP_ID, FLATDISCOUNT, 
 TOTALOPENBALANCE, AD_USER_ID, CONTACTNAME, CONTACTDESCRIPTION, EMAIL, 
 SUPERVISOR_ID, PA_GOAL_ID, PA_GOALPRIVATE_ID, EMAILUSER, BPCONTACTGREETING, 
 TITLE, COMMENTS, PHONE, PHONE2, FAX, 
 LASTCONTACT, LASTRESULT, BIRTHDAY, AD_ORGTRX_ID, EMAILVERIFY, 
 LDAPUSER, EMAILVERIFYDATE, NOTIFICATIONTYPE, C_BPARTNER_LOCATION_ID, POSTAL, 
 CITY, ADDRESS1, ADDRESS2, ADDRESS3, C_REGION_ID, 
 REGIONNAME, C_COUNTRY_ID, COUNTRYNAME)
AS 
SELECT bp.AD_Client_ID, bp.AD_Org_ID, 
	bp.IsActive, bp.Created, bp.CreatedBy, bp.Updated, bp.UpdatedBy,
    bp.C_BPartner_ID, bp.Value, bp.Name, bp.Name2, bp.Description, bp.IsSummary,
    bp.C_BP_Group_ID, bp.IsOneTime, bp.IsProspect, bp.IsVendor, bp.IsCustomer, bp.IsEmployee, bp.IsSalesRep,
    bp.ReferenceNo, bp.Duns, bp.URL, bp.AD_Language, bp.TaxID, bp.IsTaxExempt, 
    bp.C_InvoiceSchedule_ID, bp.Rating, bp.SalesVolume, bp.NumberEmployees, bp.NAICS,
    bp.FirstSale, bp.AcqusitionCost, bp.PotentialLifeTimeValue, bp.ActualLifeTimeValue,
    bp.ShareOfCustomer, bp.PaymentRule, 
    bp.SO_CreditLimit, bp.SO_CreditUsed, bp.SO_CreditUsed-bp.SO_CreditLimit AS SO_CreditAvailable,
    bp.C_PaymentTerm_ID, bp.M_PriceList_ID, bp.M_DiscountSchema_ID, bp.C_Dunning_ID,
    bp.IsDiscountPrinted, bp.SO_Description, bp.POReference, PaymentRulePO,
    bp.PO_PriceList_ID, bp.PO_DiscountSchema_ID, bp.PO_PaymentTerm_ID,
    bp.DocumentCopies, bp.C_Greeting_ID, bp.InvoiceRule, bp.DeliveryRule,
    bp.FreightCostRule, bp.DeliveryViaRule, bp.SalesRep_ID,
    bp.SendEMail, bp.BPartner_Parent_ID, bp.Invoice_PrintFormat_ID,
    bp.SOCreditStatus, bp.ShelfLifeMinPct, bp.AD_OrgBP_ID,
    bp.FlatDiscount, bp.TotalOpenBalance,
    -- Contact
    c.AD_User_ID, c.Name AS ContactName, c.Description AS ContactDescription,
    c.EMail, c.Supervisor_ID, c.PA_Goal_ID, c.PA_GoalPrivate_ID,
    c.EMailUser, c.C_Greeting_ID AS BPContactGreeting,
    c.Title, c.Comments, c.Phone, c.Phone2, c.Fax,
    c.LastContact, c.LastResult, c.BirthDay, c.AD_OrgTrx_ID,
    c.EMailVerify, c.LDAPUser, c.EMailVerifyDate, c.NotificationType,
    -- Location
	l.C_BPartner_Location_ID, a.Postal, a.City, a.Address1, a.Address2, a.Address3, 
    a.C_Region_ID, COALESCE(r.Name,a.RegionName) AS RegionName,
    a.C_Country_ID, cc.Name AS CountryName
FROM C_BPartner bp
 LEFT OUTER JOIN C_BPartner_Location l ON (bp.C_BPartner_ID=l.C_BPartner_ID AND l.IsActive='Y')
 LEFT OUTER JOIN AD_User c ON (bp.C_BPartner_ID=c.C_BPartner_ID AND (c.C_BPartner_Location_ID IS NULL OR c.C_BPartner_Location_ID=l.C_BPartner_Location_ID) AND c.IsActive='Y')
 LEFT OUTER JOIN C_Location a ON (l.C_Location_ID=a.C_Location_ID)
 LEFT OUTER JOIN C_Region r ON (a.C_Region_ID=r.C_Region_ID)
 INNER JOIN C_Country cc ON (a.C_Country_ID=cc.C_Country_ID);

/* This object may not be sorted properly in the script due to cirular references */
CREATE OR REPLACE FORCE VIEW M_INOUT_CANDIDATE_V
(AD_CLIENT_ID, AD_ORG_ID, C_BPARTNER_ID, C_ORDER_ID, DOCUMENTNO, 
 DATEORDERED, C_DOCTYPE_ID, POREFERENCE, DESCRIPTION, SALESREP_ID, 
 M_WAREHOUSE_ID, TOTALLINES)
AS 
SELECT	
	o.AD_Client_ID, o.AD_Org_ID, o.C_BPartner_ID, o.C_Order_ID,
	o.DocumentNo, o.DateOrdered, o.C_DocType_ID, 
    o.POReference, o.Description, o.SalesRep_ID,
    l.M_Warehouse_ID,
	SUM((l.QtyOrdered-l.QtyDelivered)*l.PriceActual) AS TotalLines
FROM C_Order o
  INNER JOIN C_OrderLine l ON (o.C_Order_ID=l.C_Order_ID)
WHERE	(o.DocStatus = 'CO' AND o.IsDelivered='N')  --  Status must be CO - not CL/RE
	--	not Offers and open Walkin-Receipts
	AND o.C_DocType_ID IN (SELECT C_DocType_ID FROM C_DocType
		WHERE DocBaseType='SOO' AND DocSubTypeSO NOT IN ('ON','OB','WR'))
    --  Delivery Rule - not manual
    AND o.DeliveryRule<>'M'
	--	we need to ship
	AND	l.QtyOrdered <> l.QtyDelivered
	AND o.IsDropShip='N' 
    AND (l.M_Product_ID IS NOT NULL OR l.C_Charge_ID IS NOT NULL)
    --  Not confirmed shipment
    AND NOT EXISTS (SELECT * FROM M_InOutLine iol 
        INNER JOIN M_InOut io ON (iol.M_InOut_ID=io.M_InOut_ID)
        WHERE iol.C_OrderLine_ID=l.C_OrderLine_ID AND io.DocStatus IN ('IP','WC'))
	--
GROUP BY o.AD_Client_ID, o.AD_Org_ID, o.C_BPartner_ID, o.C_Order_ID,
	o.DocumentNo, o.DateOrdered, o.C_DocType_ID,
    o.POReference, o.Description, o.SalesRep_ID, l.M_Warehouse_ID;

/* This object may not be sorted properly in the script due to cirular references */
CREATE OR REPLACE FORCE VIEW C_INVOICE_LINETAX_V
(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, AD_LANGUAGE, C_INVOICE_ID, C_INVOICELINE_ID, 
 C_TAX_ID, TAXAMT, LINETOTALAMT, TAXINDICATOR, LINE, 
 QTYINVOICED, QTYENTERED, UOMSYMBOL, NAME, DESCRIPTION, 
 DOCUMENTNOTE, UPC, SKU, PRODUCTVALUE, RESOURCEDESCRIPTION, 
 PRICELIST, PRICEENTEREDLIST, DISCOUNT, PRICEACTUAL, PRICEENTERED, 
 LINENETAMT, M_ATTRIBUTESETINSTANCE_ID, M_ATTRIBUTESET_ID, SERNO, LOT, 
 M_LOT_ID, GUARANTEEDATE, PRODUCTDESCRIPTION, IMAGEURL)
AS 
SELECT il.AD_Client_ID, il.AD_Org_ID, il.IsActive, il.Created, il.CreatedBy, il.Updated, il.UpdatedBy,
	'en_US' AS AD_Language,
	il.C_Invoice_ID, il.C_InvoiceLine_ID, 
    il.C_Tax_ID, il.TaxAmt, il.LineTotalAmt, t.TaxIndicator,
	il.Line, 
	CASE WHEN il.QtyInvoiced<>0 OR il.M_Product_ID IS NOT NULL THEN il.QtyInvoiced END AS QtyInvoiced, 
    CASE WHEN il.QtyEntered<>0 OR il.M_Product_ID IS NOT NULL THEN il.QtyEntered END AS QtyEntered, 
    CASE WHEN il.QtyEntered<>0 OR il.M_Product_ID IS NOT NULL THEN uom.UOMSymbol END AS UOMSymbol, 
	COALESCE(c.Name,p.Name||productAttribute(il.M_AttributeSetInstance_ID), il.Description) AS Name, -- main line
	CASE WHEN COALESCE(c.Name,p.Name) IS NOT NULL THEN il.Description END AS Description, -- second line
	p.DocumentNote, -- third line
    p.UPC, p.SKU, COALESCE(pp.VendorProductNo,p.Value) AS ProductValue,
	ra.Description AS ResourceDescription, -- forth line
	CASE WHEN i.IsDiscountPrinted='Y' AND il.PriceList<>0 
        THEN il.PriceList END AS PriceList,
	CASE WHEN i.IsDiscountPrinted='Y' AND il.PriceList<>0 AND il.QtyEntered<>0
        THEN il.PriceList*il.QtyInvoiced/il.QtyEntered END AS PriceEnteredList,
	CASE WHEN i.IsDiscountPrinted='Y' AND il.PriceList>il.PriceActual AND il.PriceList<>0
        THEN (il.PriceList-il.PriceActual)/il.PriceList*100 END AS Discount,
	CASE WHEN il.PriceActual<>0 OR il.M_Product_ID IS NOT NULL THEN il.PriceActual END AS PriceActual, 
	CASE WHEN il.PriceEntered<>0 OR il.M_Product_ID IS NOT NULL THEN il.PriceEntered END AS PriceEntered, 
	CASE WHEN il.LineNetAmt<>0 OR il.M_Product_ID IS NOT NULL THEN il.LineNetAmt END AS LineNetAmt,
    il.M_AttributeSetInstance_ID, asi.M_AttributeSet_ID, 
    asi.SerNo, asi.Lot, asi.M_Lot_ID,asi.GuaranteeDate, 
    p.Description as ProductDescription, p.ImageURL
FROM C_InvoiceLine il
	INNER JOIN C_UOM uom ON (il.C_UOM_ID=uom.C_UOM_ID)
	INNER JOIN C_Invoice i ON (il.C_Invoice_ID=i.C_Invoice_ID)
    LEFT OUTER JOIN C_Tax t ON (il.C_Tax_ID=t.C_Tax_ID)
	LEFT OUTER JOIN M_Product p ON (il.M_Product_ID=p.M_Product_ID)
	LEFT OUTER JOIN C_Charge c ON (il.C_Charge_ID=c.C_Charge_ID)
    LEFT OUTER JOIN C_BPartner_Product pp ON (il.M_Product_ID=pp.M_Product_ID AND i.C_BPartner_ID=pp.C_BPartner_ID)
	LEFT OUTER JOIN S_ResourceAssignment ra ON (il.S_ResourceAssignment_ID=ra.S_ResourceAssignment_ID)
    LEFT OUTER JOIN M_AttributeSetInstance asi ON (il.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID)
UNION   --  bom lines
SELECT il.AD_Client_ID, il.AD_Org_ID, il.IsActive, il.Created, il.CreatedBy, il.Updated, il.UpdatedBy,
	'en_US' AS AD_Language,
	il.C_Invoice_ID, il.C_InvoiceLine_ID, 
    il.C_Tax_ID, il.TaxAmt, il.LineTotalAmt, t.TaxIndicator,
	il.Line+(b.Line/100) AS Line,
	il.QtyInvoiced*b.BOMQty AS QtyInvoiced,
    il.QtyEntered*b.BOMQty AS QtyEntered,
    uom.UOMSymbol,
	p.Name,	-- main
	b.Description,
	p.DocumentNote, p.UPC, p.SKU, p.Value AS ProductValue,
	null, null, null, null, null, null, null,
    il.M_AttributeSetInstance_ID, asi.M_AttributeSet_ID, asi.SerNo, asi.Lot, asi.M_Lot_ID,asi.GuaranteeDate,
    p.Description as ProductDescription, p.ImageURL
FROM M_Product_BOM b	-- BOM lines
	INNER JOIN C_InvoiceLine il ON (b.M_Product_ID=il.M_Product_ID)
	INNER JOIN M_Product bp ON (bp.M_Product_ID=il.M_Product_ID -- BOM Product
		AND bp.IsBOM='Y' AND bp.IsVerified='Y' AND bp.IsInvoicePrintDetails='Y')
	INNER JOIN M_Product p ON (b.M_ProductBOM_ID=p.M_Product_ID) -- BOM line product
	INNER JOIN C_UOM uom ON (p.C_UOM_ID=uom.C_UOM_ID)
    LEFT OUTER JOIN C_Tax t ON (il.C_Tax_ID=t.C_Tax_ID)
    LEFT OUTER JOIN M_AttributeSetInstance asi ON (il.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID)
UNION   --  comment lines
SELECT il.AD_Client_ID, il.AD_Org_ID, il.IsActive, il.Created, il.CreatedBy, il.Updated, il.UpdatedBy,
	'en_US', il.C_Invoice_ID, il.C_InvoiceLine_ID, 
    null, null, null, null,
	il.Line,
	null, null, null,
	il.Description,
	null, null, null, null, null, null,
	null, null, null, null, null, null,
    null, null, null, null, null, null, null, null
FROM C_InvoiceLine il
WHERE il.C_UOM_ID IS NULL
UNION   --  empty line
SELECT AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
	'en_US', C_Invoice_ID, null,
    null, null, null, null,
	9998,
	null, null, null,
	null,
	null, null, null, null, null, null,
	null, null, null, null, null, null,
    null, null, null, null, null, null, null, null
FROM C_Invoice
UNION   --   tax lines
SELECT it.AD_Client_ID, it.AD_Org_ID, it.IsActive, it.Created, it.CreatedBy, it.Updated, it.UpdatedBy,
	'en_US', it.C_Invoice_ID, null,
    it.C_Tax_ID, null, null, t.TaxIndicator,
	9999,
	null, null, null,
	t.Name,
	null, null, null, null, null, null,
	null, null, null, 
    CASE WHEN it.IsTaxIncluded='Y' THEN it.TaxAmt ELSE it.TaxBaseAmt END, 
    CASE WHEN it.IsTaxIncluded='Y' THEN it.TaxAmt ELSE it.TaxBaseAmt END, 
    CASE WHEN it.IsTaxIncluded='Y' THEN NULL ELSE it.TaxAmt END,
    null, null, null, null, null, null, null, null
FROM C_InvoiceTax it
	INNER JOIN C_Tax t ON (it.C_Tax_ID=t.C_Tax_ID);

/* This object may not be sorted properly in the script due to cirular references */
CREATE OR REPLACE FORCE VIEW C_INVOICE_LINETAX_VT
(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, AD_LANGUAGE, C_INVOICE_ID, C_INVOICELINE_ID, 
 C_TAX_ID, TAXAMT, LINETOTALAMT, TAXINDICATOR, LINE, 
 QTYINVOICED, QTYENTERED, UOMSYMBOL, NAME, DESCRIPTION, 
 DOCUMENTNOTE, UPC, SKU, PRODUCTVALUE, RESOURCEDESCRIPTION, 
 PRICELIST, PRICEENTEREDLIST, DISCOUNT, PRICEACTUAL, PRICEENTERED, 
 LINENETAMT, M_ATTRIBUTESETINSTANCE_ID, M_ATTRIBUTESET_ID, SERNO, LOT, 
 M_LOT_ID, GUARANTEEDATE, PRODUCTDESCRIPTION, IMAGEURL)
AS 
SELECT il.AD_Client_ID, il.AD_Org_ID, il.IsActive, il.Created, il.CreatedBy, il.Updated, il.UpdatedBy,
	uom.AD_Language,
	il.C_Invoice_ID, il.C_InvoiceLine_ID, 
    il.C_Tax_ID, il.TaxAmt, il.LineTotalAmt, t.TaxIndicator,
	il.Line, 
	CASE WHEN il.QtyInvoiced<>0 OR il.M_Product_ID IS NOT NULL THEN il.QtyInvoiced END AS QtyInvoiced, 
    CASE WHEN il.QtyEntered<>0 OR il.M_Product_ID IS NOT NULL THEN il.QtyEntered END AS QtyEntered, 
	CASE WHEN il.QtyEntered<>0 OR il.M_Product_ID IS NOT NULL THEN uom.UOMSymbol END AS UOMSymbol, 
	COALESCE(c.Name,COALESCE(pt.Name,p.Name)||productAttribute(il.M_AttributeSetInstance_ID), il.Description) AS Name, -- main line
	CASE WHEN COALESCE(c.Name,pt.Name,p.Name) IS NOT NULL THEN il.Description END AS Description, -- second line
	COALESCE(pt.DocumentNote,p.DocumentNote) AS DocumentNote, -- third line
    p.UPC, p.SKU, COALESCE(pp.VendorProductNo,p.Value) AS ProductValue,
	ra.Description AS ResourceDescription, -- forth line
	CASE WHEN i.IsDiscountPrinted='Y' AND il.PriceList<>0 
        THEN il.PriceList END AS PriceList,
	CASE WHEN i.IsDiscountPrinted='Y' AND il.PriceList<>0 AND il.QtyEntered<>0
        THEN il.PriceList*il.QtyInvoiced/il.QtyEntered END AS PriceEnteredList,
	CASE WHEN i.IsDiscountPrinted='Y' AND il.PriceList>il.PriceActual AND il.PriceList<>0
        THEN (il.PriceList-il.PriceActual)/il.PriceList*100 END AS Discount,
	CASE WHEN il.PriceActual<>0 OR il.M_Product_ID IS NOT NULL THEN il.PriceActual END AS PriceActual, 
	CASE WHEN il.PriceEntered<>0 OR il.M_Product_ID IS NOT NULL THEN il.PriceEntered END AS PriceEntered, 
	CASE WHEN il.LineNetAmt<>0 OR il.M_Product_ID IS NOT NULL THEN il.LineNetAmt END AS LineNetAmt,
    il.M_AttributeSetInstance_ID, asi.M_AttributeSet_ID, asi.SerNo, asi.Lot, asi.M_Lot_ID,asi.GuaranteeDate,
    pt.Description as ProductDescription, p.ImageURL
FROM C_InvoiceLine il
	INNER JOIN C_UOM_Trl uom ON (il.C_UOM_ID=uom.C_UOM_ID)
	INNER JOIN C_Invoice i ON (il.C_Invoice_ID=i.C_Invoice_ID)
    LEFT OUTER JOIN C_Tax_Trl t ON (il.C_Tax_ID=t.C_Tax_ID AND uom.AD_Language=t.AD_Language)
	LEFT OUTER JOIN M_Product p ON (il.M_Product_ID=p.M_Product_ID)
	LEFT OUTER JOIN C_Charge c ON (il.C_Charge_ID=c.C_Charge_ID)
    LEFT OUTER JOIN C_BPartner_Product pp ON (il.M_Product_ID=pp.M_Product_ID AND i.C_BPartner_ID=pp.C_BPartner_ID)
	LEFT OUTER JOIN M_Product_Trl pt ON (il.M_Product_ID=pt.M_Product_ID AND uom.AD_Language=pt.AD_Language)
	LEFT OUTER JOIN S_ResourceAssignment ra ON (il.S_ResourceAssignment_ID=ra.S_ResourceAssignment_ID)
    LEFT OUTER JOIN M_AttributeSetInstance asi ON (il.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID)
UNION   --  bom lines
SELECT il.AD_Client_ID, il.AD_Org_ID, il.IsActive, il.Created, il.CreatedBy, il.Updated, il.UpdatedBy,
	uom.AD_Language,
	il.C_Invoice_ID, il.C_InvoiceLine_ID, 
    il.C_Tax_ID, il.TaxAmt, il.LineTotalAmt, t.TaxIndicator,
	il.Line+(b.Line/100) AS Line,
	il.QtyInvoiced*b.BOMQty AS QtyInvoiced,
    il.QtyEntered*b.BOMQty AS QtyEntered,
	uom.UOMSymbol,
	COALESCE(pt.Name,p.Name) AS Name,	-- main
	b.Description,
	COALESCE(pt.DocumentNote,p.DocumentNote) AS DocumentNote, p.UPC, p.SKU, p.Value AS ProductValue,
	null, null, null, null, null, null, null,
    il.M_AttributeSetInstance_ID, asi.M_AttributeSet_ID, asi.SerNo, asi.Lot, asi.M_Lot_ID,asi.GuaranteeDate,
    pt.Description as ProductDescription, p.ImageURL
FROM M_Product_BOM b	-- BOM lines
	INNER JOIN C_InvoiceLine il ON (b.M_Product_ID=il.M_Product_ID)
	INNER JOIN M_Product bp ON (bp.M_Product_ID=il.M_Product_ID -- BOM Product
		AND bp.IsBOM='Y' AND bp.IsVerified='Y' AND bp.IsInvoicePrintDetails='Y')
	INNER JOIN M_Product p ON (b.M_ProductBOM_ID=p.M_Product_ID) -- BOM line product
	INNER JOIN C_UOM_Trl uom ON (p.C_UOM_ID=uom.C_UOM_ID)
	INNER JOIN M_Product_Trl pt ON (b.M_ProductBOM_ID=pt.M_Product_ID AND uom.AD_Language=pt.AD_Language)
    LEFT OUTER JOIN C_Tax t ON (il.C_Tax_ID=t.C_Tax_ID)
    LEFT OUTER JOIN M_AttributeSetInstance asi ON (il.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID)
UNION   --  comment line
SELECT il.AD_Client_ID, il.AD_Org_ID, il.IsActive, il.Created, il.CreatedBy, il.Updated, il.UpdatedBy,
	l.AD_Language, il.C_Invoice_ID, il.C_InvoiceLine_ID, 
    null, null, null, null,
	il.Line,
	null, null, null,
	il.Description,
	null, null, null, null, null, null,
	null, null,	null, null, null, null,
    null, null, null, null, null, null, null, null
FROM C_InvoiceLine il, AD_Language l
WHERE il.C_UOM_ID IS NULL
  AND l.IsBaseLanguage='N' AND l.IsSystemLanguage='Y'
UNION   --  empty line
SELECT i.AD_Client_ID, i.AD_Org_ID, i.IsActive, i.Created, i.CreatedBy, i.Updated, i.UpdatedBy,
	AD_Language, i.C_Invoice_ID, null,
    null, null, null, null,
	9998,
	null, null, null,
	null,
	null, null, null, null, null, null,
	null, null,	null, null, null, null,
    null, null, null, null, null, null, null, null
FROM C_Invoice i, AD_Language l
WHERE l.IsBaseLanguage='N' AND l.IsSystemLanguage='Y'
UNION   --  tax lines
SELECT it.AD_Client_ID, it.AD_Org_ID, it.IsActive, it.Created, it.CreatedBy, it.Updated, it.UpdatedBy,
	t.AD_Language, it.C_Invoice_ID, null,
    it.C_Tax_ID, null, null, t.TaxIndicator,
	9999,
	null, null, null,
	t.Name,
	null, null, null, null, null, null,
	null, null, null,
    CASE WHEN it.IsTaxIncluded='Y' THEN it.TaxAmt ELSE it.TaxBaseAmt END, 
    CASE WHEN it.IsTaxIncluded='Y' THEN it.TaxAmt ELSE it.TaxBaseAmt END, 
    CASE WHEN it.IsTaxIncluded='Y' THEN NULL ELSE it.TaxAmt END,
    null, null, null, null, null, null, null, null
FROM C_InvoiceTax it
	INNER JOIN C_Tax_Trl t ON (it.C_Tax_ID=t.C_Tax_ID);

/* This object may not be sorted properly in the script due to cirular references */
CREATE OR REPLACE FORCE VIEW C_ORDER_LINETAX_V
(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, AD_LANGUAGE, C_ORDER_ID, C_ORDERLINE_ID, 
 C_TAX_ID, TAXINDICATOR, C_BPARTNER_ID, C_BPARTNER_LOCATION_ID, BPNAME, 
 C_LOCATION_ID, LINE, QTYORDERED, QTYENTERED, UOMSYMBOL, 
 NAME, DESCRIPTION, DOCUMENTNOTE, UPC, SKU, 
 PRODUCTVALUE, RESOURCEDESCRIPTION, PRICELIST, PRICEENTEREDLIST, DISCOUNT, 
 PRICEACTUAL, PRICEENTERED, LINENETAMT, PRODUCTDESCRIPTION, IMAGEURL)
AS 
SELECT ol.AD_Client_ID, ol.AD_Org_ID, ol.IsActive, ol.Created, ol.CreatedBy, ol.Updated, ol.UpdatedBy,
	'en_US' AS AD_Language,
	ol.C_Order_ID, ol.C_OrderLine_ID, ol.C_Tax_ID, t.TaxIndicator,
    ol.C_BPartner_ID, ol.C_BPartner_Location_ID, bp.Name AS BPName, bpl.C_Location_ID,
	ol.Line, 
	CASE WHEN ol.QtyOrdered<>0 OR ol.M_Product_ID IS NOT NULL THEN ol.QtyOrdered END AS QtyOrdered, 
    CASE WHEN ol.QtyEntered<>0 OR ol.M_Product_ID IS NOT NULL THEN ol.QtyEntered END AS QtyEntered, 
    CASE WHEN ol.QtyEntered<>0 OR ol.M_Product_ID IS NOT NULL THEN uom.UOMSymbol END AS UOMSymbol, 
	COALESCE(c.Name,p.Name||productAttribute(ol.M_AttributeSetInstance_ID), ol.Description) AS Name, -- main line
	CASE WHEN COALESCE(c.Name,p.Name) IS NOT NULL THEN ol.Description END AS Description, -- second line
	p.DocumentNote, -- third line
    p.UPC, p.SKU, COALESCE(pp.VendorProductNo,p.Value) AS ProductValue,
	ra.Description AS ResourceDescription, -- forth line
	CASE WHEN i.IsDiscountPrinted='Y' AND ol.PriceList<>0
        THEN ol.PriceList END AS PriceList,
	CASE WHEN i.IsDiscountPrinted='Y' AND ol.PriceList<>0 AND ol.QtyEntered<>0
        THEN ol.PriceList*ol.QtyOrdered/ol.QtyEntered END AS PriceEnteredList,
	CASE WHEN i.IsDiscountPrinted='Y' AND ol.PriceList>ol.PriceActual AND ol.PriceList<>0
        THEN (ol.PriceList-ol.PriceActual)/ol.PriceList*100 END AS Discount,
	CASE WHEN ol.PriceActual<>0 OR ol.M_Product_ID IS NOT NULL THEN ol.PriceActual END AS PriceActual, 
	CASE WHEN ol.PriceEntered<>0 OR ol.M_Product_ID IS NOT NULL THEN ol.PriceEntered END AS PriceEntered, 
	CASE WHEN ol.LineNetAmt<>0 OR ol.M_Product_ID IS NOT NULL THEN ol.LineNetAmt END AS LineNetAmt,
    p.Description as ProductDescription, p.ImageURL
FROM C_OrderLine ol
	INNER JOIN C_UOM uom ON (ol.C_UOM_ID=uom.C_UOM_ID)
	INNER JOIN C_Order i ON (ol.C_Order_ID=i.C_Order_ID)
	LEFT OUTER JOIN M_Product p ON (ol.M_Product_ID=p.M_Product_ID)
	LEFT OUTER JOIN S_ResourceAssignment ra ON (ol.S_ResourceAssignment_ID=ra.S_ResourceAssignment_ID)
	LEFT OUTER JOIN C_Charge c ON (ol.C_Charge_ID=c.C_Charge_ID)
    LEFT OUTER JOIN C_BPartner_Product pp ON (ol.M_Product_ID=pp.M_Product_ID AND i.C_BPartner_ID=pp.C_BPartner_ID)
	INNER JOIN C_BPartner bp ON (ol.C_BPartner_ID=bp.C_BPartner_ID)
	INNER JOIN C_BPartner_Location bpl ON (ol.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
	LEFT OUTER JOIN C_Tax t ON (ol.C_Tax_ID=t.C_Tax_ID)
UNION
SELECT ol.AD_Client_ID, ol.AD_Org_ID, ol.IsActive, ol.Created, ol.CreatedBy, ol.Updated, ol.UpdatedBy,
	'en_US' AS AD_Language,
	ol.C_Order_ID, ol.C_OrderLine_ID, ol.C_Tax_ID, null,
    null, null, null, null,
	ol.Line+(b.Line/100) AS Line,
	ol.QtyOrdered*b.BOMQty AS QtyInvoiced, ol.QtyEntered*b.BOMQty AS QtyEntered, uom.UOMSymbol,
	p.Name,	-- main
	b.Description,
	p.DocumentNote, p.UPC, p.SKU, p.Value AS ProductValue,
	null, null, null, null, null, null, null, p.Description as ProductDescription, p.ImageURL
FROM M_Product_BOM b	-- BOM lines
	INNER JOIN C_OrderLine ol ON (b.M_Product_ID=ol.M_Product_ID)
	INNER JOIN M_Product bp ON (bp.M_Product_ID=ol.M_Product_ID -- BOM Product
		AND bp.IsBOM='Y' AND bp.IsVerified='Y' AND bp.IsInvoicePrintDetails='Y')
	INNER JOIN M_Product p ON (b.M_ProductBOM_ID=p.M_Product_ID) -- BOM line product
	INNER JOIN C_UOM uom ON (p.C_UOM_ID=uom.C_UOM_ID)
UNION
SELECT AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
	'en_US', C_Order_ID, null, null, null,
	null,
	null, null, null,
    null, null, null, null,
	null,
	null, null, null, null, null, null,
	null, null, null, null, null, null, null, null
FROM C_Order
UNION
SELECT ot.AD_Client_ID, ot.AD_Org_ID, ot.IsActive, ot.Created, ot.CreatedBy, ot.Updated, ot.UpdatedBy,
	'en_US', ot.C_Order_ID, null, ot.C_Tax_ID, t.TaxIndicator,
    null, null, null, null,
	null,
	null, null, null,
	t.Name,
	null, null, null, null, null, null,
	null, null, null, 
    CASE WHEN ot.IsTaxIncluded='Y' THEN ot.TaxAmt ELSE ot.TaxBaseAmt END, 
    CASE WHEN ot.IsTaxIncluded='Y' THEN ot.TaxAmt ELSE ot.TaxBaseAmt END, 
    CASE WHEN ot.IsTaxIncluded='Y' THEN NULL ELSE ot.TaxAmt END,
    null, null
FROM C_OrderTax ot
	INNER JOIN C_Tax t ON (ot.C_Tax_ID=t.C_Tax_ID);

/* This object may not be sorted properly in the script due to cirular references */
CREATE OR REPLACE FORCE VIEW C_ORDER_LINETAX_VT
(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, AD_LANGUAGE, C_ORDER_ID, C_ORDERLINE_ID, 
 C_TAX_ID, TAXINDICATOR, C_BPARTNER_ID, C_BPARTNER_LOCATION_ID, BPNAME, 
 C_LOCATION_ID, LINE, QTYORDERED, QTYENTERED, UOMSYMBOL, 
 NAME, DESCRIPTION, DOCUMENTNOTE, UPC, SKU, 
 PRODUCTVALUE, RESOURCEDESCRIPTION, PRICELIST, PRICEENTEREDLIST, DISCOUNT, 
 PRICEACTUAL, PRICEENTERED, LINENETAMT, PRODUCTDESCRIPTION, IMAGEURL)
AS 
SELECT ol.AD_Client_ID, ol.AD_Org_ID, ol.IsActive, ol.Created, ol.CreatedBy, ol.Updated, ol.UpdatedBy,
	uom.AD_Language,
	ol.C_Order_ID, ol.C_OrderLine_ID, ol.C_Tax_ID, t.TaxIndicator,
    ol.C_BPartner_ID, ol.C_BPartner_Location_ID, bp.Name AS BPName, bpl.C_Location_ID,
	ol.Line, 
	CASE WHEN ol.QtyOrdered<>0 OR ol.M_Product_ID IS NOT NULL THEN ol.QtyOrdered END AS QtyOrdered, 
    CASE WHEN ol.QtyEntered<>0 OR ol.M_Product_ID IS NOT NULL THEN ol.QtyEntered END AS QtyEntered, 
    CASE WHEN ol.QtyEntered<>0 OR ol.M_Product_ID IS NOT NULL THEN uom.UOMSymbol END AS UOMSymbol, 
	COALESCE(c.Name,p.Name||productAttribute(ol.M_AttributeSetInstance_ID), ol.Description) AS Name, -- main line
	CASE WHEN COALESCE(c.Name,pt.Name, p.Name) IS NOT NULL THEN ol.Description END AS Description, -- second line
	COALESCE(pt.DocumentNote, p.DocumentNote) AS DocumentNote, -- third line
    p.UPC, p.SKU, COALESCE(pp.VendorProductNo,p.Value) AS ProductValue,
	ra.Description AS ResourceDescription, -- forth line
	CASE WHEN i.IsDiscountPrinted='Y' AND ol.PriceList<>0
        THEN ol.PriceList END AS PriceList,
	CASE WHEN i.IsDiscountPrinted='Y' AND ol.PriceList<>0 AND ol.QtyEntered<>0
        THEN ol.PriceList*ol.QtyOrdered/ol.QtyEntered END AS PriceEnteredList,
	CASE WHEN i.IsDiscountPrinted='Y' AND ol.PriceList>ol.PriceActual AND ol.PriceList<>0
        THEN (ol.PriceList-ol.PriceActual)/ol.PriceList*100 END AS Discount,
	CASE WHEN ol.PriceActual<>0 OR ol.M_Product_ID IS NOT NULL THEN ol.PriceActual END AS PriceActual, 
	CASE WHEN ol.PriceEntered<>0 OR ol.M_Product_ID IS NOT NULL THEN ol.PriceEntered END AS PriceEntered, 
	CASE WHEN ol.LineNetAmt<>0 OR ol.M_Product_ID IS NOT NULL THEN ol.LineNetAmt END AS LineNetAmt,
    pt.Description as ProductDescription, p.ImageURL
FROM C_OrderLine ol
	INNER JOIN C_UOM_Trl uom ON (ol.C_UOM_ID=uom.C_UOM_ID)
	INNER JOIN C_Order i ON (ol.C_Order_ID=i.C_Order_ID)
	LEFT OUTER JOIN M_Product p ON (ol.M_Product_ID=p.M_Product_ID)
	LEFT OUTER JOIN M_Product_Trl pt ON (ol.M_Product_ID=pt.M_Product_ID AND uom.AD_Language=pt.AD_Language)
	LEFT OUTER JOIN S_ResourceAssignment ra ON (ol.S_ResourceAssignment_ID=ra.S_ResourceAssignment_ID)
	LEFT OUTER JOIN C_Charge c ON (ol.C_Charge_ID=c.C_Charge_ID)
    LEFT OUTER JOIN C_BPartner_Product pp ON (ol.M_Product_ID=pp.M_Product_ID AND i.C_BPartner_ID=pp.C_BPartner_ID)
	INNER JOIN C_BPartner bp ON (ol.C_BPartner_ID=bp.C_BPartner_ID)
	INNER JOIN C_BPartner_Location bpl ON (ol.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
	LEFT OUTER JOIN C_Tax_Trl t ON (ol.C_Tax_ID=t.C_Tax_ID AND uom.AD_Language=t.AD_Language)
UNION
SELECT ol.AD_Client_ID, ol.AD_Org_ID, ol.IsActive, ol.Created, ol.CreatedBy, ol.Updated, ol.UpdatedBy,
	uom.AD_Language,
	ol.C_Order_ID, ol.C_OrderLine_ID, ol.C_Tax_ID, null,
    null, null, null, null,
	ol.Line+(b.Line/100) AS Line,
	ol.QtyOrdered*b.BOMQty AS QtyInvoiced, ol.QtyEntered*b.BOMQty AS QtyEntered, uom.UOMSymbol,
	COALESCE(pt.Name, p.Name) AS Name,	-- main
	b.Description,
	COALESCE(pt.DocumentNote, p.DocumentNote) AS DocumentNote, p.UPC, p.SKU, p.Value AS ProductValue,
	null, null, null, null, null, null, null, pt.Description AS ProductDescription, p.ImageURL
FROM M_Product_BOM b	-- BOM lines
	INNER JOIN C_OrderLine ol ON (b.M_Product_ID=ol.M_Product_ID)
	INNER JOIN M_Product bp ON (bp.M_Product_ID=ol.M_Product_ID -- BOM Product
		AND bp.IsBOM='Y' AND bp.IsVerified='Y' AND bp.IsInvoicePrintDetails='Y')
	INNER JOIN M_Product p ON (b.M_ProductBOM_ID=p.M_Product_ID) -- BOM line product
	INNER JOIN C_UOM_Trl uom ON (p.C_UOM_ID=uom.C_UOM_ID)
	INNER JOIN M_Product_Trl pt ON (b.M_ProductBOM_ID=pt.M_Product_ID AND uom.AD_Language=pt.AD_Language)
UNION
SELECT o.AD_Client_ID, o.AD_Org_ID, o.IsActive, o.Created, o.CreatedBy, o.Updated, o.UpdatedBy,
	l.AD_Language, o.C_Order_ID, null, null, null,
	null,
	null, null, null,
    null, null, null, null,
	null,
	null, null, null, null, null, null,
	null, null, null, null, null, null, null, null
FROM C_Order o, AD_Language l
WHERE l.IsBaseLanguage='N' AND l.IsSystemLanguage='Y'
UNION
SELECT ot.AD_Client_ID, ot.AD_Org_ID, ot.IsActive, ot.Created, ot.CreatedBy, ot.Updated, ot.UpdatedBy,
	t.AD_Language, ot.C_Order_ID, null, ot.C_Tax_ID, t.TaxIndicator,
    null, null, null, null,
	null,
	null, null, null,
	t.Name,
	null, null, null, null, null, null,
	null, null, null, 
    CASE WHEN ot.IsTaxIncluded='Y' THEN ot.TaxAmt ELSE ot.TaxBaseAmt END, 
    CASE WHEN ot.IsTaxIncluded='Y' THEN ot.TaxAmt ELSE ot.TaxBaseAmt END, 
    CASE WHEN ot.IsTaxIncluded='Y' THEN NULL ELSE ot.TaxAmt END,
    null, null
FROM C_OrderTax ot
	INNER JOIN C_Tax_Trl t ON (ot.C_Tax_ID=t.C_Tax_ID);

/* This object may not be sorted properly in the script due to cirular references */
CREATE OR REPLACE FORCE VIEW C_PAYSELECTION_REMITTANCE_VT
(AD_CLIENT_ID, AD_ORG_ID, AD_LANGUAGE, C_PAYSELECTION_ID, C_PAYSELECTIONLINE_ID, 
 C_PAYSELECTIONCHECK_ID, PAYMENTRULE, LINE, OPENAMT, PAYAMT, 
 DISCOUNTAMT, DIFFERENCEAMT, C_BPARTNER_ID, DOCUMENTNO, DATEINVOICED, 
 GRANDTOTAL, AMTINWORDS)
AS 
SELECT psl.AD_Client_ID, psl.AD_Org_ID, 
	l.AD_Language,
	psl.C_PaySelection_ID, psl.C_PaySelectionLine_ID, 
	psl.C_PaySelectionCheck_ID,
	psl.PaymentRule, psl.Line, psl.OpenAmt, psl.PayAmt, psl.DiscountAmt, psl.DifferenceAmt, 
	i.C_BPartner_ID, i.DocumentNo, i.DateInvoiced, i.GrandTotal, i.GrandTotal AS AmtInWords
FROM C_PaySelectionLine psl
  INNER JOIN C_Invoice i ON (psl.C_Invoice_ID=i.C_Invoice_ID)
  INNER JOIN AD_Language l ON (l.IsSystemLanguage='Y');

/* This object may not be sorted properly in the script due to cirular references */
CREATE OR REPLACE FORCE VIEW M_INOUT_LINE_V
(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, AD_LANGUAGE, M_INOUT_ID, M_INOUTLINE_ID, 
 LINE, MOVEMENTQTY, QTYENTERED, UOMSYMBOL, QTYORDERED, 
 QTYDELIVERED, QTYBACKORDERED, NAME, DESCRIPTION, DOCUMENTNOTE, 
 UPC, SKU, PRODUCTVALUE, M_LOCATOR_ID, M_WAREHOUSE_ID, 
 X, Y, Z, M_ATTRIBUTESETINSTANCE_ID, M_ATTRIBUTESET_ID, 
 SERNO, LOT, M_LOT_ID, GUARANTEEDATE, PRODUCTDESCRIPTION, 
 IMAGEURL)
AS 
SELECT iol.AD_Client_ID, iol.AD_Org_ID, iol.IsActive, iol.Created, iol.CreatedBy, iol.Updated, iol.UpdatedBy,
	'en_US' AS AD_Language,
	iol.M_InOut_ID, iol.M_InOutLine_ID, 
	iol.Line, 
	CASE WHEN iol.MovementQty<>0 OR iol.M_Product_ID IS NOT NULL THEN iol.MovementQty END AS MovementQty, 
	CASE WHEN iol.QtyEntered<>0 OR iol.M_Product_ID IS NOT NULL THEN iol.QtyEntered END AS QtyEntered, 
	CASE WHEN iol.MovementQty<>0 OR iol.M_Product_ID IS NOT NULL THEN uom.UOMSymbol END AS UOMSymbol, 
    ol.QtyOrdered, ol.QtyDelivered, 
	CASE WHEN iol.MovementQty<>0 OR iol.M_Product_ID IS NOT NULL THEN ol.QtyOrdered-ol.QtyDelivered END AS QtyBackOrdered,
	COALESCE(p.Name||productAttribute(iol.M_AttributeSetInstance_ID), c.Name, iol.Description) AS Name, -- main line
	CASE WHEN COALESCE(c.Name,p.Name) IS NOT NULL THEN iol.Description END AS Description, -- second line
	p.DocumentNote, -- third line
    p.UPC, p.SKU, p.Value AS ProductValue,
	iol.M_Locator_ID, l.M_Warehouse_ID, l.X, l.Y, l.Z,
    iol.M_AttributeSetInstance_ID, asi.M_AttributeSet_ID, asi.SerNo, asi.Lot, asi.M_Lot_ID,asi.GuaranteeDate,
    p.Description AS ProductDescription, p.ImageURL
FROM M_InOutLine iol
	INNER JOIN C_UOM uom ON (iol.C_UOM_ID=uom.C_UOM_ID)
	LEFT OUTER JOIN M_Product p ON (iol.M_Product_ID=p.M_Product_ID)
    LEFT OUTER JOIN M_AttributeSetInstance asi ON (iol.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID)
    LEFT OUTER JOIN M_Locator l ON (iol.M_Locator_ID=l.M_Locator_ID)
    LEFT OUTER JOIN C_OrderLine ol ON (iol.C_OrderLine_ID=ol.C_OrderLine_ID)
    LEFT OUTER JOIN C_Charge c ON (iol.C_Charge_ID=c.C_Charge_ID)
UNION   --  BOM lines
SELECT iol.AD_Client_ID, iol.AD_Org_ID, iol.IsActive, iol.Created, iol.CreatedBy, iol.Updated, iol.UpdatedBy,
	'en_US' AS AD_Language,
	iol.M_InOut_ID, iol.M_InOutLine_ID, 
	iol.Line+(b.Line/100) AS Line,
	iol.MovementQty*b.BOMQty AS QtyInvoiced, iol.QtyEntered*b.BOMQty AS QtyEntered, uom.UOMSymbol, 
    null, null, null,
	p.Name, -- main line
	b.Description, -- second line
	p.DocumentNote, -- third line
    p.UPC, p.SKU, p.Value AS ProductValue,
	iol.M_Locator_ID, l.M_Warehouse_ID, l.X, l.Y, l.Z,
    iol.M_AttributeSetInstance_ID, asi.M_AttributeSet_ID, asi.SerNo, asi.Lot, asi.M_Lot_ID,asi.GuaranteeDate,
    p.Description AS ProductDescription, p.ImageURL
FROM M_Product_BOM b	-- BOM lines
	INNER JOIN M_InOutLine iol ON (b.M_Product_ID=iol.M_Product_ID)
	INNER JOIN M_Product bp ON (bp.M_Product_ID=iol.M_Product_ID -- BOM Product
		AND bp.IsBOM='Y' AND bp.IsVerified='Y' AND bp.IsPickListPrintDetails='Y')
	INNER JOIN M_Product p ON (b.M_ProductBOM_ID=p.M_Product_ID) -- BOM line product
	INNER JOIN C_UOM uom ON (p.C_UOM_ID=uom.C_UOM_ID)
    LEFT OUTER JOIN M_AttributeSetInstance asi ON (iol.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID)
    LEFT OUTER JOIN M_Locator l ON (iol.M_Locator_ID=l.M_Locator_ID);

/* This object may not be sorted properly in the script due to cirular references */
CREATE OR REPLACE FORCE VIEW M_INOUT_LINE_VT
(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, AD_LANGUAGE, M_INOUT_ID, M_INOUTLINE_ID, 
 LINE, MOVEMENTQTY, QTYENTERED, UOMSYMBOL, QTYORDERED, 
 QTYDELIVERED, QTYBACKORDERED, NAME, DESCRIPTION, DOCUMENTNOTE, 
 UPC, SKU, PRODUCTVALUE, M_LOCATOR_ID, M_WAREHOUSE_ID, 
 X, Y, Z, M_ATTRIBUTESETINSTANCE_ID, M_ATTRIBUTESET_ID, 
 SERNO, LOT, M_LOT_ID, GUARANTEEDATE, PRODUCTDESCRIPTION, 
 IMAGEURL)
AS 
SELECT iol.AD_Client_ID, iol.AD_Org_ID, iol.IsActive, iol.Created, iol.CreatedBy, iol.Updated, iol.UpdatedBy,
	uom.AD_Language,
	iol.M_InOut_ID, iol.M_InOutLine_ID, 
	iol.Line, 
	CASE WHEN iol.MovementQty<>0 OR iol.M_Product_ID IS NOT NULL THEN iol.MovementQty END AS MovementQty, 
	CASE WHEN iol.QtyEntered<>0 OR iol.M_Product_ID IS NOT NULL THEN iol.QtyEntered END AS QtyEntered, 
	CASE WHEN iol.MovementQty<>0 OR iol.M_Product_ID IS NOT NULL THEN uom.UOMSymbol END AS UOMSymbol, 
    ol.QtyOrdered, ol.QtyDelivered,
	CASE WHEN iol.MovementQty<>0 OR iol.M_Product_ID IS NOT NULL THEN ol.QtyOrdered-ol.QtyDelivered END AS QtyBackOrdered,
	COALESCE(COALESCE(pt.Name,p.Name)||productAttribute(iol.M_AttributeSetInstance_ID), c.Name, iol.Description) AS Name, -- main line
	CASE WHEN COALESCE(pt.Name,p.Name,c.Name) IS NOT NULL THEN iol.Description END AS Description, -- second line
	COALESCE(pt.DocumentNote, p.DocumentNote) AS DocumentNote, -- third line
    p.UPC, p.SKU, p.Value AS ProductValue,
	iol.M_Locator_ID, l.M_Warehouse_ID, l.X, l.Y, l.Z,
    iol.M_AttributeSetInstance_ID, asi.M_AttributeSet_ID, asi.SerNo, asi.Lot, asi.M_Lot_ID,asi.GuaranteeDate,
    pt.Description AS ProductDescription, p.ImageURL
From	M_InOutLine iol
	INNER JOIN C_UOM_Trl uom ON (iol.C_UOM_ID=uom.C_UOM_ID)
	LEFT OUTER JOIN M_Product p ON (iol.M_Product_ID=p.M_Product_ID)
	LEFT OUTER JOIN M_Product_Trl pt ON (iol.M_Product_ID=pt.M_Product_ID AND uom.AD_Language=pt.AD_Language)
    LEFT OUTER JOIN M_AttributeSetInstance asi ON (iol.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID)
    LEFT OUTER JOIN M_Locator l ON (iol.M_Locator_ID=l.M_Locator_ID)
    LEFT OUTER JOIN C_OrderLine ol ON (iol.C_OrderLine_ID=ol.C_OrderLine_ID)
    LEFT OUTER JOIN C_Charge c ON (iol.C_Charge_ID=c.C_Charge_ID)
UNION
SELECT iol.AD_Client_ID, iol.AD_Org_ID, iol.IsActive, iol.Created, iol.CreatedBy, iol.Updated, iol.UpdatedBy,
	uom.AD_Language,
	iol.M_InOut_ID, iol.M_InOutLine_ID, 
	iol.Line+(b.Line/100) AS Line,
	iol.MovementQty*b.BOMQty AS QtyInvoiced, iol.QtyEntered*b.BOMQty AS QtyEntered, uom.UOMSymbol, 
    null, null, null,
	COALESCE (pt.Name, p.Name) AS Name, -- main line
	b.Description, -- second line
	COALESCE (pt.DocumentNote, p.DocumentNote) AS DocumentNote, -- third line
    p.UPC, p.SKU, p.Value AS ProductValue,
	iol.M_Locator_ID, l.M_Warehouse_ID, l.X, l.Y, l.Z,
    iol.M_AttributeSetInstance_ID, asi.M_AttributeSet_ID, asi.SerNo, asi.Lot, asi.M_Lot_ID,asi.GuaranteeDate,
    pt.Description AS ProductDescription, p.ImageURL
FROM M_Product_BOM b	-- BOM lines
	INNER JOIN M_InOutLine iol ON (b.M_Product_ID=iol.M_Product_ID)
	INNER JOIN M_Product bp ON (bp.M_Product_ID=iol.M_Product_ID -- BOM Product
		AND bp.IsBOM='Y' AND bp.IsVerified='Y' AND bp.IsPickListPrintDetails='Y')
	INNER JOIN M_Product p ON (b.M_ProductBOM_ID=p.M_Product_ID) -- BOM line product
	INNER JOIN C_UOM_Trl uom ON (p.C_UOM_ID=uom.C_UOM_ID)
	INNER JOIN M_Product_Trl pt ON (iol.M_Product_ID=pt.M_Product_ID AND uom.AD_Language=pt.AD_Language)
    LEFT OUTER JOIN M_AttributeSetInstance asi ON (iol.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID)
    LEFT OUTER JOIN M_Locator l ON (iol.M_Locator_ID=l.M_Locator_ID);

-- No action taken.  This is a column of a view.  
-- Changes should be made in underlying objects of the view, not the view itself.

/* This object may not be sorted properly in the script due to cirular references */
CREATE OR REPLACE FORCE VIEW C_PROJECT_DETAILS_VT
(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, AD_LANGUAGE, C_PROJECT_ID, C_PROJECTLINE_ID, 
 LINE, PLANNEDQTY, PLANNEDPRICE, PLANNEDAMT, PLANNEDMARGINAMT, 
 COMMITTEDAMT, M_PRODUCT_ID, NAME, DESCRIPTION, DOCUMENTNOTE, 
 UPC, SKU, PRODUCTVALUE, M_PRODUCT_CATEGORY_ID, INVOICEDAMT, 
 INVOICEDQTY, COMMITTEDQTY)
AS 
SELECT pl.AD_Client_ID, pl.AD_Org_ID, pl.IsActive, pl.Created, pl.CreatedBy, pl.Updated, pl.UpdatedBy,
	l.AD_Language,
    pj.C_Project_ID, pl.C_ProjectLine_ID,
    pl.Line, 
    pl.PlannedQty, pl.PlannedPrice, pl.PlannedAmt, pl.PlannedMarginAmt,
    pl.CommittedAmt,
    pl.M_Product_ID,
	COALESCE(p.Name, pl.Description) AS Name, -- main line
	CASE WHEN p.Name IS NOT NULL THEN pl.Description END AS Description, -- second line
	p.DocumentNote, -- third line
    p.UPC, p.SKU, p.Value AS ProductValue,
    pl.M_Product_Category_ID,
    pl.InvoicedAmt, pl.InvoicedQty, pl.CommittedQty
FROM C_ProjectLine pl
  INNER JOIN C_Project pj ON (pl.C_Project_ID=pj.C_Project_ID)
  LEFT OUTER JOIN M_Product p ON (pl.M_Product_ID=p.M_Product_ID)
  INNER JOIN AD_Language l ON (l.IsSystemLanguage='Y')
WHERE pl.IsPrinted='Y';

/* This object may not be sorted properly in the script due to cirular references */
CREATE OR REPLACE FORCE VIEW RV_UNPOSTED
(AD_CLIENT_ID, AD_ORG_ID, CREATED, CREATEDBY, UPDATED, 
 UPDATEDBY, ISACTIVE, DOCUMENTNO, DATEDOC, DATEACCT, 
 AD_TABLE_ID, RECORD_ID, ISSOTRX)
AS 
SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive,
    DocumentNo, DateDoc, DateAcct, 224 AS AD_Table_ID, GL_Journal_ID AS Record_ID, 'N' AS IsSOTrx
FROM GL_Journal  WHERE Posted<>'Y'
UNION
SELECT pi.AD_Client_ID, pi.AD_Org_ID, pi.Created, pi.CreatedBy, pi.Updated, pi.UpdatedBy, pi. IsActive,
    p.Name || '_' || pi.Line, pi.MovementDate, pi.MovementDate, 623, pi.C_ProjectIssue_ID, 'N' 
FROM C_ProjectIssue pi INNER JOIN C_Project p ON (pi.C_Project_ID=p.C_Project_ID)  WHERE Posted<>'Y'
UNION
SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive,
    DocumentNo, DateInvoiced, DateAcct, 318, C_Invoice_ID, IsSOTrx
FROM C_Invoice  WHERE Posted<>'Y'
UNION
SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive,
    DocumentNo, MovementDate, DateAcct, 319, M_InOut_ID, IsSOTrx 
FROM M_InOut  WHERE Posted<>'Y'
UNION
SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive,
    DocumentNo, MovementDate, MovementDate, 321, M_Inventory_ID, 'N' 
FROM M_Inventory  WHERE Posted<>'Y'
UNION
SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive,
    DocumentNo, MovementDate, MovementDate, 323, M_Movement_ID, 'N' 
FROM M_Movement  WHERE Posted<>'Y'
UNION
SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive, 
    Name, MovementDate, MovementDate, 325, M_Production_ID, 'N'
FROM M_Production  WHERE Posted<>'Y'
UNION
SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive, 
    Name, StatementDate, DateAcct, 407, C_Cash_ID, 'N' 
FROM C_Cash  WHERE Posted<>'Y'
UNION
SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive, 
    DocumentNo, DateTrx, DateTrx, 335, C_Payment_ID, 'N'
FROM C_Payment  WHERE Posted<>'Y'
UNION
SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive, 
    DocumentNo, DateTrx, DateTrx, 735, C_AllocationHdr_ID, 'N' 
FROM C_AllocationHdr  WHERE Posted<>'Y'
UNION
SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive,
    Name, StatementDate, StatementDate, 392, C_BankStatement_ID, 'N' 
FROM C_BankStatement  WHERE Posted<>'Y'
UNION
SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive, 
    DocumentNo, DateTrx, DateTrx, 472, M_MatchInv_ID, 'N' 
FROM M_MatchInv  WHERE Posted<>'Y'
UNION
SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive, 
    DocumentNo, DateTrx, DateTrx, 473, M_MatchPO_ID, 'N'
FROM M_MatchPO  WHERE Posted<>'Y'
UNION
SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive, 
    DocumentNo, DateOrdered, DateAcct, 259, C_Order_ID, IsSOTrx
FROM C_Order  WHERE Posted<>'Y'
UNION
SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive, 
    DocumentNo, DateRequired, DateRequired, 702, M_Requisition_ID, 'N'
FROM M_Requisition  WHERE Posted<>'Y';

-- No action taken.  This is a column of a view.  
-- Changes should be made in underlying objects of the view, not the view itself.

/* This object may not be sorted properly in the script due to cirular references */
CREATE OR REPLACE FORCE VIEW C_RFQRESPONSE_VT
(C_RFQRESPONSE_ID, C_RFQ_ID, AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, 
 CREATED, CREATEDBY, UPDATED, UPDATEDBY, AD_LANGUAGE, 
 ORG_LOCATION_ID, TAXID, NAME, DESCRIPTION, HELP, 
 C_CURRENCY_ID, ISO_CODE, DATERESPONSE, DATEWORKSTART, DELIVERYDAYS, 
 C_BPARTNER_ID, BPNAME, BPNAME2, C_BPARTNER_LOCATION_ID, C_LOCATION_ID, 
 AD_USER_ID, TITLE, PHONE, CONTACTNAME)
AS 
SELECT rr.C_RfQResponse_ID, rr.C_RfQ_ID, 
    rr.AD_Client_ID, rr.AD_Org_ID, rr.IsActive, rr.Created, rr.CreatedBy, rr.Updated, rr.UpdatedBy,
	l.AD_Language,
	oi.C_Location_ID AS Org_Location_ID, oi.TaxID,
    r.Name, r.Description, r.Help,
    r.C_Currency_ID, c.ISO_Code,
    r.DateResponse, r.DateWorkStart, r.DeliveryDays,
    rr.C_BPartner_ID, bp.Name AS BPName, bp.Name2 AS BPName2,
    rr.C_BPartner_Location_ID, bpl.C_Location_ID,
    rr.AD_User_ID, bpc.Title, bpc.Phone,
    NULLIF (bpc.Name, bp.Name) AS ContactName
FROM C_RfQResponse rr
  INNER JOIN C_RfQ r ON (rr.C_RfQ_ID=r.C_RfQ_ID)
  INNER JOIN AD_OrgInfo oi ON (rr.AD_Org_ID=oi.AD_Org_ID)
  INNER JOIN C_Currency c ON (r.C_Currency_ID=c.C_Currency_ID)
  INNER JOIN C_BPartner bp ON (rr.C_BPartner_ID=bp.C_BPartner_ID)
  INNER JOIN C_BPartner_Location bpl ON (rr.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
  LEFT OUTER JOIN AD_User bpc ON (rr.AD_User_ID=bpc.AD_User_ID)
  INNER JOIN AD_Language l ON (l.IsSystemLanguage='Y');

-- No action taken.  This is a column of a view.  
-- Changes should be made in underlying objects of the view, not the view itself.

/* This object may not be sorted properly in the script due to cirular references */
CREATE OR REPLACE FORCE VIEW C_RFQRESPONSELINE_VT
(C_RFQRESPONSE_ID, C_RFQRESPONSELINE_ID, C_RFQLINE_ID, C_RFQRESPONSELINEQTY_ID, C_RFQLINEQTY_ID, 
 AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, AD_LANGUAGE, LINE, M_PRODUCT_ID, 
 M_ATTRIBUTESETINSTANCE_ID, NAME, DESCRIPTION, DOCUMENTNOTE, UPC, 
 SKU, PRODUCTVALUE, HELP, DATEWORKSTART, DELIVERYDAYS, 
 C_UOM_ID, UOMSYMBOL, QTY, PRICE, DISCOUNT)
AS 
SELECT rrl.C_RfQResponse_ID, rrl.C_RfQResponseLine_ID, rrl.C_RfQLine_ID, 
    rq.C_RfQResponseLineQty_ID, rq.C_RfQLineQty_ID,
    rrl.AD_Client_ID, rrl.AD_Org_ID, rrl.IsActive, rrl.Created, rrl.CreatedBy, rrl.Updated, rrl.UpdatedBy,
	l.AD_Language,
    rl.Line,
    rl.M_Product_ID, rl.M_AttributeSetInstance_ID,
	COALESCE(p.Name||productAttribute(rl.M_AttributeSetInstance_ID), rl.Description) AS Name, -- main line
	CASE WHEN p.Name IS NOT NULL THEN rl.Description END AS Description, -- second line
	p.DocumentNote, -- third line
    p.UPC, p.SKU, p.Value AS ProductValue,
    rl.Help,
    rl.DateWorkStart, rl.DeliveryDays,
    q.C_UOM_ID, uom.UOMSymbol,
    q.Qty, rq.Price, rq.Discount
FROM C_RfQResponseLineQty rq
  INNER JOIN C_RfQLineQty q ON (rq.C_RfQLineQty_ID=q.C_RfQLineQty_ID)
  INNER JOIN C_UOM uom ON (q.C_UOM_ID=uom.C_UOM_ID)
  INNER JOIN C_RfQResponseLine rrl ON (rq.C_RfQResponseLine_ID=rrl.C_RfQResponseLine_ID)
  INNER JOIN C_RfQLine rl ON (rrl.C_RfQLine_ID=rl.C_RfQLine_ID)
  LEFT OUTER JOIN M_Product p ON (rl.M_Product_ID=p.M_Product_ID)
  INNER JOIN AD_Language l ON (l.IsSystemLanguage='Y')
WHERE rq.IsActive='Y' AND q.IsActive='Y'
  AND rrl.IsActive='Y' AND rl.IsActive='Y';

-- No action taken.  This is a column of a view.  
-- Changes should be made in underlying objects of the view, not the view itself.

/* This object may not be sorted properly in the script due to cirular references */
CREATE OR REPLACE FORCE VIEW C_RFQRESPONSELINEQTY_VT
(C_RFQRESPONSELINE_ID, C_RFQRESPONSELINEQTY_ID, C_RFQLINEQTY_ID, AD_CLIENT_ID, AD_ORG_ID, 
 ISACTIVE, CREATED, CREATEDBY, UPDATED, UPDATEDBY, 
 AD_LANGUAGE, C_UOM_ID, UOMSYMBOL, QTY, PRICE, 
 DISCOUNT)
AS 
SELECT rq.C_RfQResponseLine_ID, rq.C_RfQResponseLineQty_ID, rq.C_RfQLineQty_ID,
    rq.AD_Client_ID, rq.AD_Org_ID, rq.IsActive, rq.Created, rq.CreatedBy, rq.Updated, rq.UpdatedBy,
	l.AD_Language,
    q.C_UOM_ID, uom.UOMSymbol,
    q.Qty, rq.Price, rq.Discount
FROM C_RfQResponseLineQty rq
  INNER JOIN C_RfQLineQty q ON (rq.C_RfQLineQty_ID=q.C_RfQLineQty_ID)
  INNER JOIN C_UOM uom ON (q.C_UOM_ID=uom.C_UOM_ID)
  INNER JOIN AD_Language l ON (l.IsSystemLanguage='Y')
WHERE rq.IsActive='Y' AND q.IsActive='Y';

/* This object may not be sorted properly in the script due to cirular references */
CREATE OR REPLACE FORCE VIEW RV_COST
(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, M_PRODUCT_ID, VALUE, NAME, 
 UPC, ISBOM, PRODUCTTYPE, M_PRODUCT_CATEGORY_ID, M_COSTTYPE_ID, 
 M_COSTELEMENT_ID, COSTELEMENTTYPE, COSTINGMETHOD, ISCALCULATED, C_ACCTSCHEMA_ID, 
 C_CURRENCY_ID, CURRENTCOSTPRICE, FUTURECOSTPRICE, DESCRIPTION)
AS 
SELECT 	c.AD_Client_ID, c.AD_Org_ID, c.IsActive, c.Created,c.CreatedBy,c.Updated,c.UpdatedBy,
    p.M_Product_ID, p.Value, p.Name, p.UPC, p.IsBOM, p.ProductType, p.M_Product_Category_ID,
    c.M_CostType_ID, 
    ce.M_CostElement_ID, ce.CostElementType, ce.CostingMethod, ce.IsCalculated, 
    acct.C_AcctSchema_ID, acct.C_Currency_ID,
    c.CurrentCostPrice, c.FutureCostPrice, c.Description
FROM M_Cost c
  INNER JOIN M_Product p ON (c.M_Product_ID=p.M_Product_ID)
  INNER JOIN M_CostElement ce ON (c.M_CostElement_ID=ce.M_CostElement_ID)
  INNER JOIN C_AcctSchema acct ON (c.C_AcctSchema_ID=acct.C_AcctSchema_ID);

/* This object may not be sorted properly in the script due to cirular references */
CREATE OR REPLACE FORCE VIEW RV_COSTDETAIL
(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, M_PRODUCT_ID, VALUE, NAME, 
 UPC, ISBOM, PRODUCTTYPE, M_PRODUCT_CATEGORY_ID, M_INOUTLINE_ID, 
 C_INVOICELINE_ID, M_ATTRIBUTESETINSTANCE_ID, M_ATTRIBUTESET_ID, LOT, SERNO, 
 C_ACCTSCHEMA_ID, C_CURRENCY_ID, AMT, QTY, DESCRIPTION, 
 PROCESSED)
AS 
SELECT 	c.AD_Client_ID, c.AD_Org_ID, c.IsActive, c.Created,c.CreatedBy,c.Updated,c.UpdatedBy,
    p.M_Product_ID, p.Value, p.Name, p.UPC, p.IsBOM, p.ProductType, p.M_Product_Category_ID,
    c.M_InOutLine_ID, c.C_InvoiceLine_ID,
    asi.M_AttributeSetInstance_ID, asi.M_AttributeSet_ID, asi.Lot, asi.SerNo,
    acct.C_AcctSchema_ID, acct.C_Currency_ID,
    c.Amt, c.Qty, c.Description, Processed
FROM M_CostDetail c
  INNER JOIN M_Product p ON (c.M_Product_ID=p.M_Product_ID)
  INNER JOIN C_AcctSchema acct ON (c.C_AcctSchema_ID=acct.C_AcctSchema_ID)
  INNER JOIN M_AttributeSetInstance asi ON (c.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID);

/* This object may not be sorted properly in the script due to cirular references */
CREATE OR REPLACE FORCE VIEW RV_COSTSUMMARY
(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATEDBY, CREATED, 
 UPDATEDBY, UPDATED, M_PRODUCT_ID, VALUE, NAME, 
 UPC, ISBOM, PRODUCTTYPE, M_PRODUCT_CATEGORY_ID, M_COSTTYPE_ID, 
 C_ACCTSCHEMA_ID, C_CURRENCY_ID, CURRENTCOSTPRICE, FUTURECOSTPRICE)
AS 
SELECT 	c.AD_Client_ID, c.AD_Org_ID, c.IsActive, 0 AS CreatedBy,SysDate AS Created,0 AS UpdatedBy,SysDate AS Updated,
    p.M_Product_ID, p.Value, p.Name, p.UPC, p.IsBOM, p.ProductType, p.M_Product_Category_ID,
    c.M_CostType_ID, 
    acct.C_AcctSchema_ID, acct.C_Currency_ID,
    SUM(c.CurrentCostPrice) AS CurrentCostPrice, SUM(c.FutureCostPrice) AS FutureCostPrice
FROM M_Cost c
  INNER JOIN M_Product p ON (c.M_Product_ID=p.M_Product_ID)
  INNER JOIN C_AcctSchema acct ON (c.C_AcctSchema_ID=acct.C_AcctSchema_ID)
WHERE acct.M_CostType_ID=c.M_CostType_ID
GROUP BY c.AD_Client_ID, c.AD_Org_ID, c.IsActive, 
    p.M_Product_ID, p.Value, p.Name, p.UPC, p.IsBOM, p.ProductType, p.M_Product_Category_ID,
    c.M_CostType_ID, acct.C_AcctSchema_ID, acct.C_Currency_ID;

/* This object may not be sorted properly in the script due to cirular references */
CREATE OR REPLACE FORCE VIEW RV_ORDERDETAIL
(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, C_ORDER_ID, DOCSTATUS, DOCACTION, 
 C_DOCTYPE_ID, ISAPPROVED, ISCREDITAPPROVED, SALESREP_ID, BILL_BPARTNER_ID, 
 BILL_LOCATION_ID, BILL_USER_ID, ISDROPSHIP, C_BPARTNER_ID, C_BPARTNER_LOCATION_ID, 
 AD_USER_ID, POREFERENCE, C_CURRENCY_ID, ISSOTRX, C_ORDERLINE_ID, 
 DATEORDERED, DATEPROMISED, M_PRODUCT_ID, M_WAREHOUSE_ID, M_ATTRIBUTESETINSTANCE_ID, 
 PRODUCTATTRIBUTE, M_ATTRIBUTESET_ID, M_LOT_ID, GUARANTEEDATE, LOT, 
 SERNO, C_UOM_ID, QTYENTERED, QTYORDERED, QTYRESERVED, 
 QTYDELIVERED, QTYINVOICED, PRICEACTUAL, PRICEENTERED, QTYTODELIVER, 
 QTYTOINVOICE, NETAMTTOINVOICE, QTYLOSTSALES, AMTLOSTSALES, DISCOUNT, 
 MARGIN, MARGINAMT)
AS 
SELECT l.AD_Client_ID, l.AD_Org_ID, 
	l.IsActive, l.Created, l.CreatedBy, l.Updated, l.UpdatedBy,
	o.C_Order_ID, o.DocStatus, o.DocAction, o.C_DocType_ID, o.IsApproved, o.IsCreditApproved,
	o.SalesRep_ID, 
    o.Bill_BPartner_ID, o.Bill_Location_ID, o.Bill_User_ID, o.IsDropShip,
    l.C_BPartner_ID, l.C_BPartner_Location_ID, o.AD_User_ID,
	o.POReference, o.C_Currency_ID, o.IsSOTrx,
	l.C_OrderLine_ID, l.DateOrdered, l.DatePromised, l.M_Product_ID, l.M_Warehouse_ID,
    l.M_AttributeSetInstance_ID, productAttribute(l.M_AttributeSetInstance_ID) AS ProductAttribute,
    pasi.M_AttributeSet_ID, pasi.M_Lot_ID, pasi.GuaranteeDate, pasi.Lot, pasi.SerNo,
	l.C_UOM_ID, l.QtyEntered, l.QtyOrdered, l.QtyReserved, l.QtyDelivered, l.QtyInvoiced, 
    l.PriceActual, l.PriceEntered,
	l.QtyOrdered-l.QtyDelivered AS QtyToDeliver,
	l.QtyOrdered-l.QtyInvoiced AS QtyToInvoice,
	(l.QtyOrdered-l.QtyInvoiced)*l.PriceActual AS NetAmtToInvoice,
    l.QtyLostSales, l.QtyLostSales*l.PriceActual AS AmtLostSales,
	CASE WHEN PriceList=0 THEN 0 ELSE
	  ROUND((PriceList-PriceActual)/PriceList*100,2) END AS Discount,
	CASE WHEN PriceLimit=0 THEN 0 ELSE
	  ROUND((PriceActual-PriceLimit)/PriceLimit*100,2) END AS Margin,
	CASE WHEN PriceLimit=0 THEN 0 ELSE
	  (PriceActual-PriceLimit)*QtyDelivered END AS MarginAmt
FROM C_Order o
  INNER JOIN C_OrderLine l ON (o.C_Order_ID=l.C_Order_ID)
  LEFT OUTER JOIN M_AttributeSetInstance pasi ON (l.M_AttributeSetInstance_ID=pasi.M_AttributeSetInstance_ID);

/* This object may not be sorted properly in the script due to cirular references */
CREATE OR REPLACE FORCE VIEW M_INOUTLINEMA_V
(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, M_INOUT_ID, M_INOUTLINE_ID, LINE, 
 M_PRODUCT_ID, M_ATTRIBUTESETINSTANCE_ID, MOVEMENTQTY, M_LOCATOR_ID)
AS 
SELECT m.AD_Client_ID, m.AD_Org_ID, m.IsActive, m.Created, m.CreatedBy, m.Updated, m.UpdatedBy,
    l.M_InOut_ID, m.M_InOutLine_ID, l.Line, l.M_Product_ID, 
    m.M_AttributeSetInstance_ID, m.MovementQty, l.M_Locator_ID
FROM M_InOutLineMA m INNER JOIN M_InOutLine l ON (m.M_InOutLine_ID=l.M_InOutLine_ID)
UNION
SELECT AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
    M_InOut_ID, M_InOutLine_ID, Line, M_Product_ID, 
    M_AttributeSetInstance_ID, MovementQty, M_Locator_ID
FROM M_InOutLine;

/* This object may not be sorted properly in the script due to cirular references */
CREATE OR REPLACE FORCE VIEW M_MOVEMENTLINEMA_V
(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, M_MOVEMENT_ID, M_MOVEMENTLINE_ID, LINE, 
 M_PRODUCT_ID, M_ATTRIBUTESETINSTANCE_ID, MOVEMENTQTY, M_LOCATOR_ID, M_LOCATORTO_ID)
AS 
SELECT m.AD_Client_ID, m.AD_Org_ID, m.IsActive, m.Created, m.CreatedBy, m.Updated, m.UpdatedBy,
    l.M_Movement_ID, m.M_MovementLine_ID, l.Line, l.M_Product_ID, 
    m.M_AttributeSetInstance_ID, m.MovementQty, l.M_Locator_ID, l.M_LocatorTo_ID
FROM M_MovementLineMA m INNER JOIN M_MovementLine l ON (m.M_MovementLine_ID=l.M_MovementLine_ID)
UNION
SELECT AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
    M_Movement_ID, M_MovementLine_ID, Line, M_Product_ID, 
    M_AttributeSetInstance_ID, MovementQty, M_Locator_ID, M_LocatorTo_ID
FROM M_MovementLine;

/* This object may not be sorted properly in the script due to cirular references */
CREATE OR REPLACE FORCE VIEW RV_C_INVOICELINE
(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, C_INVOICELINE_ID, C_INVOICE_ID, SALESREP_ID, 
 C_BPARTNER_ID, C_BP_GROUP_ID, M_PRODUCT_ID, M_PRODUCT_CATEGORY_ID, DATEINVOICED, 
 DATEACCT, ISSOTRX, C_DOCTYPE_ID, DOCSTATUS, QTYINVOICED, 
 QTYENTERED, M_ATTRIBUTESETINSTANCE_ID, PRODUCTATTRIBUTE, M_ATTRIBUTESET_ID, M_LOT_ID, 
 GUARANTEEDATE, LOT, SERNO, PRICELIST, PRICEACTUAL, 
 PRICELIMIT, PRICEENTERED, DISCOUNT, MARGIN, MARGINAMT, 
 LINENETAMT, LINELISTAMT, LINELIMITAMT, LINEDISCOUNTAMT, LINEOVERLIMITAMT)
AS 
SELECT 
	il.AD_Client_ID, il.AD_Org_ID, il.IsActive, il.Created, il.CreatedBy, il.Updated, il.UpdatedBy,
	il.C_InvoiceLine_ID, i.C_Invoice_ID, i.SalesRep_ID,
	i.C_BPartner_ID, i.C_BP_Group_ID,
	il.M_Product_ID, p.M_Product_Category_ID,
	i.DateInvoiced, i.DateAcct, i.IsSOTrx, i.C_DocType_ID, i.DocStatus,
	--	Qty
	il.QtyInvoiced*i.Multiplier AS QtyInvoiced,
	il.QtyEntered*i.Multiplier AS QtyEntered,
    -- Attributes
    il.M_AttributeSetInstance_ID, productAttribute(il.M_AttributeSetInstance_ID) AS ProductAttribute,
    pasi.M_AttributeSet_ID, pasi.M_Lot_ID, pasi.GuaranteeDate, pasi.Lot, pasi.SerNo,
	--	Item Amounts
	il.PriceList, il.PriceActual, il.PriceLimit, il.PriceEntered,
	CASE WHEN PriceList=0 THEN 0 ELSE
	  ROUND((PriceList-PriceActual)/PriceList*100,2) END AS Discount,
	CASE WHEN PriceLimit=0 THEN 0 ELSE
	  ROUND((PriceActual-PriceLimit)/PriceLimit*100,2) END AS Margin,
	CASE WHEN PriceLimit=0 THEN 0 ELSE
	  (PriceActual-PriceLimit)*QtyInvoiced END AS MarginAmt,
	--	Line Amounts
	ROUND(i.Multiplier*LineNetAmt, 2) AS LineNetAmt,
	ROUND(i.Multiplier*PriceList*QtyInvoiced, 2) AS LineListAmt,
	CASE WHEN COALESCE(il.PriceLimit, 0)=0 THEN ROUND(i.Multiplier*LineNetAmt,2) ELSE
		ROUND(i.Multiplier*PriceLimit*QtyInvoiced,2) END AS LineLimitAmt,
	ROUND(i.Multiplier*PriceList*QtyInvoiced-LineNetAmt,2) AS LineDiscountAmt,
	CASE WHEN COALESCE(il.PriceLimit,0)=0 THEN 0 ELSE
		ROUND(i.Multiplier*LineNetAmt-PriceLimit*QtyInvoiced,2) END AS LineOverLimitAmt
FROM  RV_C_Invoice i
  INNER JOIN C_InvoiceLine il ON (i.C_Invoice_ID=il.C_Invoice_ID)
  LEFT OUTER JOIN M_Product p ON (il.M_Product_ID=p.M_Product_ID)
  LEFT OUTER JOIN M_AttributeSetInstance pasi ON (il.M_AttributeSetInstance_ID=pasi.M_AttributeSetInstance_ID);

ALTER TABLE GL_JOURNAL
 DROP CONSTRAINT GLJOURNALBATCH_GLJOURNAL;
ALTER TABLE GL_JOURNAL
 ADD CONSTRAINT GLJOURNALBATCH_GLJOURNAL 
 FOREIGN KEY (GL_JOURNALBATCH_ID) 
 REFERENCES GL_JOURNALBATCH (GL_JOURNALBATCH_ID)
    ON DELETE CASCADE;

ALTER TABLE AD_USER
 DROP CONSTRAINT CBPARTNER_ADUSER;
ALTER TABLE AD_USER
 ADD CONSTRAINT CBPARTNER_ADUSER 
 FOREIGN KEY (C_BPARTNER_ID) 
 REFERENCES C_BPARTNER (C_BPARTNER_ID)
    ON DELETE CASCADE;

ALTER TABLE C_LANDEDCOSTALLOCATION
 DROP PRIMARY KEY CASCADE;
ALTER TABLE C_LANDEDCOSTALLOCATION
 ADD CONSTRAINT C_LANDEDCOSTALLOCATION_KEY
 PRIMARY KEY
 (C_LANDEDCOSTALLOCATION_ID);

ALTER TABLE C_LANDEDCOSTALLOCATION
 ADD CONSTRAINT CINVOICELINE_CLANDEDCOSTALLOC 
 FOREIGN KEY (C_INVOICELINE_ID) 
 REFERENCES C_INVOICELINE (C_INVOICELINE_ID);

ALTER TABLE C_LANDEDCOSTALLOCATION
 ADD CONSTRAINT MASI_CLANDEDCOSTALLOCATION 
 FOREIGN KEY (M_ATTRIBUTESETINSTANCE_ID) 
 REFERENCES M_ATTRIBUTESETINSTANCE (M_ATTRIBUTESETINSTANCE_ID);

ALTER TABLE C_LANDEDCOSTALLOCATION
 ADD CONSTRAINT MCOSTELEMENT_MLANDEDCOSTALLOC 
 FOREIGN KEY (M_COSTELEMENT_ID) 
 REFERENCES M_COSTELEMENT (M_COSTELEMENT_ID);

ALTER TABLE M_COST
 DROP PRIMARY KEY CASCADE;
ALTER TABLE M_COST
 ADD CONSTRAINT M_COST_KEY
 PRIMARY KEY
 (AD_CLIENT_ID, AD_ORG_ID, M_PRODUCT_ID, M_COSTTYPE_ID, C_ACCTSCHEMA_ID, M_COSTELEMENT_ID, M_ATTRIBUTESETINSTANCE_ID);

ALTER TABLE M_COST
 ADD CONSTRAINT MASI_MCOST 
 FOREIGN KEY (M_ATTRIBUTESETINSTANCE_ID) 
 REFERENCES M_ATTRIBUTESETINSTANCE (M_ATTRIBUTESETINSTANCE_ID)
    ON DELETE CASCADE;

ALTER TABLE M_COST
 DROP CONSTRAINT MCOSTELEMENT_MCOST;
ALTER TABLE M_COST
 ADD CONSTRAINT MCOSTELEMENT_MCOST 
 FOREIGN KEY (M_COSTELEMENT_ID) 
 REFERENCES M_COSTELEMENT (M_COSTELEMENT_ID)
    ON DELETE CASCADE;

ALTER TABLE M_INVENTORYLINEMA
 DROP CONSTRAINT MINVENTORYLINE_MILINEMA;
ALTER TABLE M_INVENTORYLINEMA
 ADD CONSTRAINT MINVENTORYLINE_MILINEMA 
 FOREIGN KEY (M_INVENTORYLINE_ID) 
 REFERENCES M_INVENTORYLINE (M_INVENTORYLINE_ID)
    ON DELETE CASCADE;

ALTER TABLE C_TAXDECLARATION
 ADD CHECK (IsActive in ('Y','N'));

ALTER TABLE C_TAXDECLARATION
 ADD CHECK (Processed in ('Y','N'));

ALTER TABLE C_TAXDECLARATION
 ADD CONSTRAINT C_TAXDECLARATION_KEY
 PRIMARY KEY
 (C_TAXDECLARATION_ID);

ALTER TABLE PA_HIERARCHY
 ADD CHECK (IsActive in ('Y','N'));

ALTER TABLE PA_HIERARCHY
 ADD CONSTRAINT PA_HIERARCHY_KEY
 PRIMARY KEY
 (PA_HIERARCHY_ID);

ALTER TABLE PA_HIERARCHY
 ADD CONSTRAINT ADTREEACCOUNT_PAHIERARCHY 
 FOREIGN KEY (AD_TREE_ACCOUNT_ID) 
 REFERENCES AD_TREE (AD_TREE_ID);

ALTER TABLE PA_HIERARCHY
 ADD CONSTRAINT ADTREEACTIVITY_PAHIERARCHY 
 FOREIGN KEY (AD_TREE_ACTIVITY_ID) 
 REFERENCES AD_TREE (AD_TREE_ID);

ALTER TABLE PA_HIERARCHY
 ADD CONSTRAINT ADTREEBPARTNER_PAHIERARCHY 
 FOREIGN KEY (AD_TREE_BPARTNER_ID) 
 REFERENCES AD_TREE (AD_TREE_ID);

ALTER TABLE PA_HIERARCHY
 ADD CONSTRAINT ADTREECAMPAIGN_PAHIERARCHY 
 FOREIGN KEY (AD_TREE_CAMPAIGN_ID) 
 REFERENCES AD_TREE (AD_TREE_ID);

ALTER TABLE PA_HIERARCHY
 ADD CONSTRAINT ADTREEORG_PAHIERARCHY 
 FOREIGN KEY (AD_TREE_ORG_ID) 
 REFERENCES AD_TREE (AD_TREE_ID);

ALTER TABLE PA_HIERARCHY
 ADD CONSTRAINT ADTREEPRODUCT_PAHIERARCHY 
 FOREIGN KEY (AD_TREE_PRODUCT_ID) 
 REFERENCES AD_TREE (AD_TREE_ID);

ALTER TABLE PA_HIERARCHY
 ADD CONSTRAINT ADTREEPROJECT_PAHIERARCHY 
 FOREIGN KEY (AD_TREE_PROJECT_ID) 
 REFERENCES AD_TREE (AD_TREE_ID);

ALTER TABLE PA_HIERARCHY
 ADD CONSTRAINT ADTREESR_PAHIERARCHY 
 FOREIGN KEY (AD_TREE_SALESREGION_ID) 
 REFERENCES AD_TREE (AD_TREE_ID);

ALTER TABLE GL_BUDGETCONTROL
 ADD CHECK (IsActive in ('Y','N'));

ALTER TABLE GL_BUDGETCONTROL
 ADD CHECK (IsBeforeApproval in ('Y','N'));

ALTER TABLE GL_BUDGETCONTROL
 ADD CONSTRAINT GL_BUDGETCONTROL_KEY
 PRIMARY KEY
 (GL_BUDGETCONTROL_ID);

ALTER TABLE GL_BUDGETCONTROL
 ADD CONSTRAINT CACCTSCHEMA_GLBUDGETCONTROL 
 FOREIGN KEY (C_ACCTSCHEMA_ID) 
 REFERENCES C_ACCTSCHEMA (C_ACCTSCHEMA_ID);

ALTER TABLE GL_BUDGETCONTROL
 ADD CONSTRAINT GLBUDGET_GLBUDGETCONTROL 
 FOREIGN KEY (GL_BUDGET_ID) 
 REFERENCES GL_BUDGET (GL_BUDGET_ID);

ALTER TABLE GL_FUND
 ADD CHECK (IsActive in ('Y','N'));

ALTER TABLE GL_FUND
 ADD CONSTRAINT GL_FUND_KEY
 PRIMARY KEY
 (GL_FUND_ID);

ALTER TABLE GL_FUND
 ADD CONSTRAINT CACCTSCHEMA_GLFUND 
 FOREIGN KEY (C_ACCTSCHEMA_ID) 
 REFERENCES C_ACCTSCHEMA (C_ACCTSCHEMA_ID);

ALTER TABLE M_ATTRIBUTESETEXCLUDE
 ADD CHECK (IsActive in ('Y','N'));

ALTER TABLE M_ATTRIBUTESETEXCLUDE
 ADD CHECK (IsSOTrx in ('Y','N'));

ALTER TABLE M_ATTRIBUTESETEXCLUDE
 ADD CONSTRAINT M_ATTRIBUTESETEXCLUDE_KEY
 PRIMARY KEY
 (M_ATTRIBUTESETEXCLUDE_ID);

ALTER TABLE M_ATTRIBUTESETEXCLUDE
 ADD CONSTRAINT ADTABLE_MATTRIBUTESETEXCLUDE 
 FOREIGN KEY (AD_TABLE_ID) 
 REFERENCES AD_TABLE (AD_TABLE_ID);

ALTER TABLE M_ATTRIBUTESETEXCLUDE
 ADD CONSTRAINT MATTRIBUTESET_MASEXCLUDE 
 FOREIGN KEY (M_ATTRIBUTESET_ID) 
 REFERENCES M_ATTRIBUTESET (M_ATTRIBUTESET_ID);

ALTER TABLE M_LOTCTLEXCLUDE
 ADD CHECK (IsActive in ('Y','N'));

ALTER TABLE M_LOTCTLEXCLUDE
 ADD CHECK (IsSOTrx in ('Y','N'));

ALTER TABLE M_LOTCTLEXCLUDE
 ADD CONSTRAINT M_LOTCTLEXCLUDE_KEY
 PRIMARY KEY
 (M_LOTCTLEXCLUDE_ID);

ALTER TABLE M_LOTCTLEXCLUDE
 ADD CONSTRAINT ADTABLE_MLOTCTLEXCLUDE 
 FOREIGN KEY (AD_TABLE_ID) 
 REFERENCES AD_TABLE (AD_TABLE_ID);

ALTER TABLE M_LOTCTLEXCLUDE
 ADD CONSTRAINT MLOTCTL_MLOTCTLEXCLUDE 
 FOREIGN KEY (M_LOTCTL_ID) 
 REFERENCES M_LOTCTL (M_LOTCTL_ID);

ALTER TABLE M_SERNOCTLEXCLUDE
 ADD CHECK (IsActive in ('Y','N'));

ALTER TABLE M_SERNOCTLEXCLUDE
 ADD CHECK (IsSOTrx in ('Y','N'));

ALTER TABLE M_SERNOCTLEXCLUDE
 ADD CONSTRAINT M_SERNOCTLEXCLUDE_KEY
 PRIMARY KEY
 (M_SERNOCTLEXCLUDE_ID);

ALTER TABLE M_SERNOCTLEXCLUDE
 ADD CONSTRAINT ADTABLE_MSERNOCTLEXCLUDE 
 FOREIGN KEY (AD_TABLE_ID) 
 REFERENCES AD_TABLE (AD_TABLE_ID);

ALTER TABLE M_SERNOCTLEXCLUDE
 ADD CONSTRAINT MSERNOCTL_MSERNOCTLEXCLUDE 
 FOREIGN KEY (M_SERNOCTL_ID) 
 REFERENCES M_SERNOCTL (M_SERNOCTL_ID);

ALTER TABLE GL_FUNDRESTRICTION
 ADD CHECK (IsActive in ('Y','N'));

ALTER TABLE GL_FUNDRESTRICTION
 ADD CONSTRAINT GL_FUNDRESTRICTION_KEY
 PRIMARY KEY
 (GL_FUNDRESTRICTION_ID);

ALTER TABLE GL_FUNDRESTRICTION
 ADD CONSTRAINT CELEMENTVALUE_GLFUNDRESTR 
 FOREIGN KEY (C_ELEMENTVALUE_ID) 
 REFERENCES C_ELEMENTVALUE (C_ELEMENTVALUE_ID);

ALTER TABLE GL_FUNDRESTRICTION
 ADD CONSTRAINT GLFUND_GLFUNDRESTRICTION 
 FOREIGN KEY (GL_FUND_ID) 
 REFERENCES GL_FUND (GL_FUND_ID)
    ON DELETE CASCADE;

ALTER TABLE AD_USERBPACCESS
 ADD CHECK (IsActive in ('Y','N'));

ALTER TABLE AD_USERBPACCESS
 ADD CONSTRAINT AD_USERBPACCESS_KEY
 PRIMARY KEY
 (AD_USERBPACCESS_ID);

ALTER TABLE AD_USERBPACCESS
 ADD CONSTRAINT ADUSER_ADUSERBPACCESS 
 FOREIGN KEY (AD_USER_ID) 
 REFERENCES AD_USER (AD_USER_ID)
    ON DELETE CASCADE;

ALTER TABLE AD_USERBPACCESS
 ADD CONSTRAINT RREQUESTTYPE_ADUSERBPACCESS 
 FOREIGN KEY (R_REQUESTTYPE_ID) 
 REFERENCES R_REQUESTTYPE (R_REQUESTTYPE_ID)
    ON DELETE CASCADE;

ALTER TABLE AD_USERQUERY
 ADD CHECK (IsActive in ('Y','N'));

ALTER TABLE AD_USERQUERY
 ADD CONSTRAINT AD_USERQUERY_KEY
 PRIMARY KEY
 (AD_USERQUERY_ID);

ALTER TABLE AD_USERQUERY
 ADD CONSTRAINT ADTABLE_ADUSERQUERY 
 FOREIGN KEY (AD_TABLE_ID) 
 REFERENCES AD_TABLE (AD_TABLE_ID)
    ON DELETE CASCADE;

ALTER TABLE AD_USERQUERY
 ADD CONSTRAINT ADUSER_ADUSERQUERY 
 FOREIGN KEY (AD_USER_ID) 
 REFERENCES AD_USER (AD_USER_ID)
    ON DELETE CASCADE;

ALTER TABLE C_PAYMENTALLOCATE
 ADD CHECK (IsActive in ('Y','N'));

ALTER TABLE C_PAYMENTALLOCATE
 ADD CONSTRAINT C_PAYMENTALLOCATE_KEY
 PRIMARY KEY
 (C_PAYMENTALLOCATE_ID);

ALTER TABLE C_PAYMENTALLOCATE
 ADD CONSTRAINT CINVOICE_CPAYMENTALLOCATE 
 FOREIGN KEY (C_INVOICE_ID) 
 REFERENCES C_INVOICE (C_INVOICE_ID);

ALTER TABLE C_PAYMENTALLOCATE
 ADD CONSTRAINT CPAYMENT_CPAYMENTALLOCATE 
 FOREIGN KEY (C_PAYMENT_ID) 
 REFERENCES C_PAYMENT (C_PAYMENT_ID)
    ON DELETE CASCADE;

ALTER TABLE C_PAYMENTALLOCATE
 ADD CONSTRAINT CPAYMTALLOCATE_CALLOCATIONLINE 
 FOREIGN KEY (C_ALLOCATIONLINE_ID) 
 REFERENCES C_ALLOCATIONLINE (C_ALLOCATIONLINE_ID)
    ON DELETE SET NULL;

ALTER TABLE C_TAXDECLARATIONACCT
 ADD CHECK (IsActive in ('Y','N'));

ALTER TABLE C_TAXDECLARATIONACCT
 ADD CONSTRAINT C_TAXDECLARATIONACCT_KEY
 PRIMARY KEY
 (C_TAXDECLARATIONACCT_ID);

ALTER TABLE C_TAXDECLARATIONACCT
 ADD CONSTRAINT CACCTSCHEMA_CTAXDECLACCT 
 FOREIGN KEY (C_ACCTSCHEMA_ID) 
 REFERENCES C_ACCTSCHEMA (C_ACCTSCHEMA_ID)
    ON DELETE CASCADE;

ALTER TABLE C_TAXDECLARATIONACCT
 ADD CONSTRAINT CTAXDECL_CTAXDECLACCT 
 FOREIGN KEY (C_TAXDECLARATION_ID) 
 REFERENCES C_TAXDECLARATION (C_TAXDECLARATION_ID);

ALTER TABLE C_TAXDECLARATIONACCT
 ADD CONSTRAINT FACTACCT_CTAXDECLACCT 
 FOREIGN KEY (FACT_ACCT_ID) 
 REFERENCES FACT_ACCT (FACT_ACCT_ID)
    ON DELETE CASCADE;

ALTER TABLE C_TAXDECLARATIONLINE
 ADD CHECK (IsActive in ('Y','N'));

ALTER TABLE C_TAXDECLARATIONLINE
 ADD CONSTRAINT C_TAXDECLARATIONLINE_KEY
 PRIMARY KEY
 (C_TAXDECLARATIONLINE_ID);

ALTER TABLE C_TAXDECLARATIONLINE
 ADD CONSTRAINT CALLOCATIONLINE_CTAXDECLLINE 
 FOREIGN KEY (C_ALLOCATIONLINE_ID) 
 REFERENCES C_ALLOCATIONLINE (C_ALLOCATIONLINE_ID);

ALTER TABLE C_TAXDECLARATIONLINE
 ADD CONSTRAINT CBPARTNER_CTAXDECLLINE 
 FOREIGN KEY (C_BPARTNER_ID) 
 REFERENCES C_BPARTNER (C_BPARTNER_ID);

ALTER TABLE C_TAXDECLARATIONLINE
 ADD CONSTRAINT CINVOICELINE_CTAXDECLLINE 
 FOREIGN KEY (C_INVOICELINE_ID) 
 REFERENCES C_INVOICELINE (C_INVOICELINE_ID);

ALTER TABLE C_TAXDECLARATIONLINE
 ADD CONSTRAINT CINVOICE_CTAXDECLLINE 
 FOREIGN KEY (C_INVOICE_ID) 
 REFERENCES C_INVOICE (C_INVOICE_ID);

ALTER TABLE C_TAXDECLARATIONLINE
 ADD CONSTRAINT CTAXDECLARATION_CTAXDECLLINE 
 FOREIGN KEY (C_TAXDECLARATION_ID) 
 REFERENCES C_TAXDECLARATION (C_TAXDECLARATION_ID)
    ON DELETE CASCADE;

ALTER TABLE C_TAXDECLARATIONLINE
 ADD CONSTRAINT CTAX_CTAXDECLLINE 
 FOREIGN KEY (C_TAX_ID) 
 REFERENCES C_TAX (C_TAX_ID);

ALTER TABLE M_COSTDETAIL
 ADD CHECK (IsActive in ('Y','N'));

ALTER TABLE M_COSTDETAIL
 ADD CHECK (IsSOTrx in ('Y','N'));

ALTER TABLE M_COSTDETAIL
 ADD CHECK (Processed in ('Y','N'));

ALTER TABLE M_COSTDETAIL
 ADD CONSTRAINT M_COSTDETAIL_KEY
 PRIMARY KEY
 (M_COSTDETAIL_ID);

ALTER TABLE M_COSTDETAIL
 ADD CONSTRAINT ADCLIENT_MCOSTDETAIL 
 FOREIGN KEY (AD_CLIENT_ID) 
 REFERENCES AD_CLIENT (AD_CLIENT_ID);

ALTER TABLE M_COSTDETAIL
 ADD CONSTRAINT ADORG_MCOSTDETAIL 
 FOREIGN KEY (AD_ORG_ID) 
 REFERENCES AD_ORG (AD_ORG_ID);

ALTER TABLE M_COSTDETAIL
 ADD CONSTRAINT CACCTSCHEMA_MCOSTDETAIL 
 FOREIGN KEY (C_ACCTSCHEMA_ID) 
 REFERENCES C_ACCTSCHEMA (C_ACCTSCHEMA_ID);

ALTER TABLE M_COSTDETAIL
 ADD CONSTRAINT CORDERLINE_MCOSTDETAIL 
 FOREIGN KEY (C_ORDERLINE_ID) 
 REFERENCES C_ORDERLINE (C_ORDERLINE_ID);

ALTER TABLE M_COSTDETAIL
 ADD CONSTRAINT CPROJECTISSUE_MCOSTDETAIL 
 FOREIGN KEY (C_PROJECTISSUE_ID) 
 REFERENCES C_PROJECTISSUE (C_PROJECTISSUE_ID);

ALTER TABLE M_COSTDETAIL
 ADD CONSTRAINT MASI_MCOSTDETAIL 
 FOREIGN KEY (M_ATTRIBUTESETINSTANCE_ID) 
 REFERENCES M_ATTRIBUTESETINSTANCE (M_ATTRIBUTESETINSTANCE_ID);

ALTER TABLE M_COSTDETAIL
 ADD CONSTRAINT MCOSTELEMENT_MCOSTDETAIL 
 FOREIGN KEY (M_COSTELEMENT_ID) 
 REFERENCES M_COSTELEMENT (M_COSTELEMENT_ID);

ALTER TABLE M_COSTDETAIL
 ADD CONSTRAINT MINOUTLINE_MCOSTDETAIL 
 FOREIGN KEY (M_INOUTLINE_ID) 
 REFERENCES M_INOUTLINE (M_INOUTLINE_ID);

ALTER TABLE M_COSTDETAIL
 ADD CONSTRAINT MINVENTORYLINE_MCOSTDETAIL 
 FOREIGN KEY (M_INVENTORYLINE_ID) 
 REFERENCES M_INVENTORYLINE (M_INVENTORYLINE_ID);

ALTER TABLE M_COSTDETAIL
 ADD CONSTRAINT MINVOICELINE_MCOSTDETAIL 
 FOREIGN KEY (C_INVOICELINE_ID) 
 REFERENCES C_INVOICELINE (C_INVOICELINE_ID);

ALTER TABLE M_COSTDETAIL
 ADD CONSTRAINT MMOVEMENTLINE_MCOSTDETAIL 
 FOREIGN KEY (M_MOVEMENTLINE_ID) 
 REFERENCES M_MOVEMENTLINE (M_MOVEMENTLINE_ID);

ALTER TABLE M_COSTDETAIL
 ADD CONSTRAINT MPRODUCTIONLINE_MCOSTDETAIL 
 FOREIGN KEY (M_PRODUCTIONLINE_ID) 
 REFERENCES M_PRODUCTIONLINE (M_PRODUCTIONLINE_ID);

ALTER TABLE M_COSTDETAIL
 ADD CONSTRAINT MPRODUCT_MCOSTDETAIL 
 FOREIGN KEY (M_PRODUCT_ID) 
 REFERENCES M_PRODUCT (M_PRODUCT_ID);

ALTER TABLE M_COSTQUEUE
 ADD CHECK (IsActive in ('Y','N'));

ALTER TABLE M_COSTQUEUE
 ADD CONSTRAINT M_COSTQUEUE_KEY
 PRIMARY KEY
 (M_COSTQUEUE_ID);

ALTER TABLE M_COSTQUEUE
 ADD CONSTRAINT CACCTSCHEMA_MCOSTQUEUE 
 FOREIGN KEY (C_ACCTSCHEMA_ID) 
 REFERENCES C_ACCTSCHEMA (C_ACCTSCHEMA_ID)
    ON DELETE CASCADE;

ALTER TABLE M_COSTQUEUE
 ADD CONSTRAINT MASI_MCOSTQUEUE 
 FOREIGN KEY (M_ATTRIBUTESETINSTANCE_ID) 
 REFERENCES M_ATTRIBUTESETINSTANCE (M_ATTRIBUTESETINSTANCE_ID)
    ON DELETE CASCADE;

ALTER TABLE M_COSTQUEUE
 ADD CONSTRAINT MCOSTELEMENT_MCOSTQUEUE 
 FOREIGN KEY (M_COSTELEMENT_ID) 
 REFERENCES M_COSTELEMENT (M_COSTELEMENT_ID)
    ON DELETE CASCADE;

ALTER TABLE M_COSTQUEUE
 ADD CONSTRAINT MCOSTTYPE_MCOSTQUEUE 
 FOREIGN KEY (M_COSTTYPE_ID) 
 REFERENCES M_COSTTYPE (M_COSTTYPE_ID)
    ON DELETE CASCADE;

ALTER TABLE M_COSTQUEUE
 ADD CONSTRAINT MPRODUCT_MCOSTQUEUE 
 FOREIGN KEY (M_PRODUCT_ID) 
 REFERENCES M_PRODUCT (M_PRODUCT_ID);

