-- Posterita SQL Scripts for PostgreSQL
---------------------------
--   SCRIPT STARTS HERE!
---------------------------

--- Table: U_WEB_PROPERTIES -------------------------------------------------------
CREATE TABLE U_WEB_PROPERTIES
  (
    U_Web_Properties_ID  NUMERIC(10,0)   NOT NULL  ,
    AD_Client_ID         NUMERIC(10,0)   NOT NULL  ,
    AD_Org_ID            NUMERIC(10,0)   NOT NULL  ,
    IsActive             CHAR(1)        DEFAULT 'Y' NOT NULL ,
    Created              TIMESTAMP           DEFAULT NOW() NOT NULL ,
    CreatedBy            INTEGER        NOT NULL ,
    Updated              TIMESTAMP           DEFAULT NOW() NOT NULL ,
    UpdatedBy            INTEGER        NOT NULL ,
    U_Key                VARCHAR(240)     NOT NULL ,
    U_Value              VARCHAR(240)     NOT NULL ,
    primary key(U_Web_Properties_ID),
    CHECK(IsActive IN ('Y', 'N'))
  );

--- Table: U_RoleMenu -------------------------------------------------------
CREATE TABLE U_RoleMenu
  (
    U_RoleMenu_ID  NUMERIC(10,0)   NOT NULL  ,
    AD_Client_ID   NUMERIC(10,0)   NOT NULL  ,
    AD_Org_ID      NUMERIC(10,0)   NOT NULL  ,
    IsActive       CHAR(1)        DEFAULT 'Y' NOT NULL,
    Created        TIMESTAMP           DEFAULT NOW() NOT NULL,
    CreatedBy      INTEGER        NOT NULL,
    Updated        TIMESTAMP           DEFAULT NOW() NOT NULL,
    UpdatedBy      INTEGER        NOT NULL,
    AD_Role_ID     NUMERIC(10,0)   NOT NULL,
    U_WebMenu_ID      NUMERIC(10,0)   NOT NULL,
    primary key(U_RoleMenu_ID),
    CHECK(IsActive IN ('Y', 'N'))
  );

--- Table: U_WebMenu -------------------------------------------------------
CREATE TABLE U_WebMenu
  (
    U_WebMenu_ID      NUMERIC(10,0)      NOT NULL  ,
    AD_Client_ID   NUMERIC(10,0)      NOT NULL  ,
    AD_Org_ID      NUMERIC(10,0)      NOT NULL  ,
    IsActive       CHAR(1)           DEFAULT 'Y' NOT NULL  ,
    Created        TIMESTAMP              DEFAULT NOW() NOT NULL,
    CreatedBy      NUMERIC(10,0)      NOT NULL ,
    Updated        TIMESTAMP              DEFAULT NOW() NOT NULL ,
    UpdatedBy      NUMERIC(10,0)      NOT NULL  ,
    Name           VARCHAR(120)        NOT NULL  ,
    MenuLink       VARCHAR(510)        NOT NULL  ,
    Module         VARCHAR(120)        NOT NULL  ,
    ParentMenu_ID  NUMERIC(10,0)       ,
    HasSubMenu     CHAR(1)           DEFAULT 'N' NOT NULL,
    Description    VARCHAR(200)         ,
    ImageLink      VARCHAR(510)         ,
    Position       VARCHAR(10)        ,
    Help           VARCHAR(2000)    ,
    primary key(U_WebMenu_ID),
    CHECK(IsActive IN ('Y', 'N')),
    CHECK(HasSubMenu IN ('Y', 'N'))
  );

--- Table: U_BlackListCheque -------------------------------------------------------
CREATE TABLE U_BlackListCheque
  (
    U_BlackListCheque_ID  NUMERIC(10,0)     NOT NULL ,
    AD_Client_ID          NUMERIC(10,0)     NOT NULL  ,
    AD_Org_ID             NUMERIC(10,0)     NOT NULL  ,
    IsActive              CHAR(1)          DEFAULT 'Y' NOT NULL,
    Created               TIMESTAMP             DEFAULT NOW() NOT NULL  ,
    CreatedBy             NUMERIC(10,0)     NOT NULL  ,
    Updated               TIMESTAMP             DEFAULT NOW() NOT NULL ,
    UpdatedBy             NUMERIC(10,0)     NOT NULL  ,
    BankName              VARCHAR(120)   NOT NULL  ,
    ChequeNo              VARCHAR(120)   NOT NULL  ,
    primary key(U_BlackListCheque_ID),
    CHECK(IsActive IN ('Y', 'N'))
  );


--- Table: AD_PrintFormat -------------------------------------------------------------

ALTER TABLE AD_PrintFormat ADD COLUMN ClassName VARCHAR(240);

ALTER TABLE AD_PrintFormat ADD COLUMN Args VARCHAR(510);

----------------------------------------------------------------------------

--- Table: AD_User -------------------------------------------------------------

ALTER TABLE AD_User ADD COLUMN UserPIN VARCHAR(20);

ALTER TABLE AD_User ALTER COLUMN UserPIN TYPE VARCHAR(20);

----------------------------------------------------------------------------

--- Table: AD_Role -------------------------------------------------------------

ALTER TABLE AD_Role ADD COLUMN UserDiscount NUMERIC(22,2);

ALTER TABLE AD_Role ALTER COLUMN UserDiscount TYPE numeric(22,2);


----------------------------------------------------------------------------

--- Table: C_Order -------------------------------------------------------------

ALTER TABLE C_Order ADD COLUMN OrderType VARCHAR(510);

ALTER TABLE C_Order ADD COLUMN C_POS_ID NUMERIC(10,0);

ALTER TABLE C_Order ADD COLUMN AmountTendered NUMERIC(22,2);

ALTER TABLE C_Order ADD COLUMN AmountRefunded NUMERIC(22,2);



----------------------------------------------------------------------------

--- Table: M_Product -----------------------------------------------------------

ALTER TABLE M_Product ADD COLUMN Group1 VARCHAR(255);

ALTER TABLE M_Product ALTER COLUMN Group1 TYPE VARCHAR(255);

ALTER TABLE M_Product ADD COLUMN Group2 VARCHAR(255);

ALTER TABLE M_Product ALTER COLUMN Group2 TYPE VARCHAR(255);

----------------------------------------------------------------------------

--- Table: U_WebMenu -------------------------------------------------------
ALTER TABLE U_WebMenu ADD COLUMN Category VARCHAR(120);

ALTER TABLE U_WebMenu ALTER COLUMN Category TYPE VARCHAR(120);

ALTER TABLE U_WebMenu ADD COLUMN Sequence NUMERIC(10,0);

ALTER TABLE U_WebMenu ALTER Sequence TYPE NUMERIC(10,0);

----------------------------------------------------------------------------


--- Table: C_POS ---------------------------------------------------------------

ALTER TABLE C_POS ADD COLUMN CashDrawer VARCHAR(120);

ALTER TABLE C_POS ALTER COLUMN CashDrawer TYPE VARCHAR(120);


----------------------------------------------------------------------------


