-- Migration Scripts for Posterita
-------------------------------------
--   SCRIPT STARTS HERE!
-------------------------------------

--- Table: U_WEB_PROPERTIES ---------

CREATE TABLE U_WEB_PROPERTIES
  (
    U_Web_Properties_ID  NUMBER(10,0)   NOT NULL  ,
    AD_Client_ID         NUMBER(10,0)   NOT NULL  ,
    AD_Org_ID            NUMBER(10,0)   NOT NULL  ,
    IsActive             CHAR(1)        DEFAULT 'Y' NOT NULL ,
    Created              DATE           DEFAULT SYSDATE NOT NULL ,
    CreatedBy            INTEGER        NOT NULL ,
    Updated              DATE           DEFAULT SYSDATE NOT NULL ,
    UpdatedBy            INTEGER        NOT NULL ,
    U_Key                NVARCHAR2(240)     NOT NULL ,
    U_Value              NVARCHAR2(240)     NOT NULL ,
    primary key(U_Web_Properties_ID),
    CHECK(IsActive IN ('Y', 'N'))
  );


--- Table: U_RoleMenu -------------------------------------------------------

CREATE TABLE U_RoleMenu
  (
    U_RoleMenu_ID  NUMBER(10,0)   NOT NULL  ,
    AD_Client_ID   NUMBER(10,0)   NOT NULL  ,
    AD_Org_ID      NUMBER(10,0)   NOT NULL  ,
    IsActive       CHAR(1)        DEFAULT 'Y' NOT NULL,
    Created        DATE           DEFAULT SYSDATE NOT NULL,
    CreatedBy      INTEGER        NOT NULL,
    Updated        DATE           DEFAULT SYSDATE NOT NULL,
    UpdatedBy      INTEGER        NOT NULL,
    AD_Role_ID     NUMBER(10,0)   NOT NULL,
    U_WebMenu_ID      NUMBER(10,0)   NOT NULL,
    primary key(U_RoleMenu_ID),
    CHECK(IsActive IN ('Y', 'N'))
  );

--- Table: U_WebMenu -------------------------------------------------------

CREATE TABLE U_WebMenu
  (
    U_WEBMENU_ID   NUMBER(10,0)      NOT NULL  ,
    AD_Client_ID   NUMBER(10,0)      NOT NULL  ,
    AD_Org_ID      NUMBER(10,0)      NOT NULL  ,
    IsActive       CHAR(1)           DEFAULT 'Y' NOT NULL  ,
    Created        DATE              DEFAULT SYSDATE NOT NULL,
    CreatedBy      NUMBER(10,0)      NOT NULL ,
    Updated        DATE              DEFAULT SYSDATE NOT NULL ,
    UpdatedBy      NUMBER(10,0)      NOT NULL  ,
    Name           NVARCHAR2(120)        NOT NULL  ,
    MenuLink       NVARCHAR2(510)        NOT NULL  ,
    Module         NVARCHAR2(120)        NOT NULL  ,
    ParentMenu_ID  NUMBER(10,0)       ,
    HasSubMenu     CHAR(1)           DEFAULT 'N' NOT NULL,
    Description    NVARCHAR2(200)         ,
    ImageLink      NVARCHAR2(510)         ,
    Position       VARCHAR(10)        ,
    Help           NVARCHAR2(2000)    ,
    primary key(U_WEBMENU_ID),
    CHECK(IsActive IN ('Y', 'N')),
    CHECK(HasSubMenu IN ('Y', 'N'))
  );
--- Table: U_BlackListCheque-------------------------------------------------------
CREATE TABLE U_BlackListCheque
  (
    U_BlackListCheque_ID  NUMBER(10,0)     NOT NULL ,
    AD_Client_ID          NUMBER(10,0)     NOT NULL  ,
    AD_Org_ID             NUMBER(10,0)     NOT NULL  ,
    IsActive              CHAR(1)          DEFAULT 'Y' NOT NULL,
    Created               DATE             DEFAULT SYSDATE NOT NULL  ,
    CreatedBy             NUMBER(10,0)     NOT NULL  ,
    Updated               DATE             DEFAULT SYSDATE NOT NULL ,
    UpdatedBy             NUMBER(10,0)     NOT NULL  ,
    BankName              NVARCHAR2(120)   NOT NULL  ,
    ChequeNo              NVARCHAR2(120)   NOT NULL  ,
    primary key(U_BlackListCheque_ID),
    CHECK(IsActive IN ('Y', 'N'))
  );

--- Table: AD_PrintFormat --------------

ALTER TABLE AD_PrintFormat 
ADD ClassName VARCHAR(240);

ALTER TABLE AD_PrintFormat 
ADD Args VARCHAR(480);


--- Table: AD_User ------------------------
ALTER TABLE AD_User 
ADD UserPIN NVARCHAR2(20);

ALTER TABLE AD_User 
MODIFY UserPIN NVARCHAR2(20);

--- Table: AD_Role --------------------------
ALTER TABLE AD_Role
ADD UserDiscount NUMBER(22,2);

ALTER TABLE AD_Role 
MODIFY UserDiscount NUMBER(22,2);

--- Table: C_Order --------------------------

ALTER TABLE C_Order 
ADD OrderType NVARCHAR2(510);

ALTER TABLE C_Order 
MODIFY OrderType NVARCHAR2(510);

ALTER TABLE C_Order 
ADD C_POS_ID NUMBER(10,0);

ALTER TABLE C_Order 
MODIFY C_POS_ID NUMBER(10,0);

ALTER TABLE C_Order 
ADD AmountTendered NUMBER(22,2);

ALTER TABLE C_Order 
MODIFY AmountTendered NUMBER(22,2);

ALTER TABLE C_Order 
ADD AmountRefunded NUMBER(22,2);

ALTER TABLE C_Order 
MODIFY AmountRefunded NUMBER(22,2);

--- Table: M_Product -------------------------
ALTER TABLE M_Product 
MODIFY Value NVARCHAR2(510);

ALTER TABLE M_Product 
MODIFY Name NVARCHAR2(510);

ALTER TABLE M_Product 
ADD Group1 NVARCHAR2(255);

ALTER TABLE M_Product 
MODIFY Group1 NVARCHAR2(255);

ALTER TABLE M_Product 
ADD Group2 NVARCHAR2(255);

ALTER TABLE M_Product 
MODIFY Group2 NVARCHAR2(255);

--- Table: U_WebMenu --------------------------
ALTER TABLE U_WebMenu 
ADD Category NVARCHAR2(120);

ALTER TABLE U_WebMenu 
MODIFY Category NVARCHAR2(120);

ALTER TABLE U_WebMenu
ADD Sequence NUMBER(10,0);

ALTER TABLE U_WebMenu 
MODIFY Sequence NUMBER(10,0);

--- Table: C_POS ---------------------------

ALTER TABLE C_POS 
ADD CashDrawer VARCHAR(120);

ALTER TABLE C_POS 
MODIFY CashDrawer NVARCHAR2(120);
