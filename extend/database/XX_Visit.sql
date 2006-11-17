/**
 * Example Table DDL SQL
 *
 * @author 	Jorg Janke
 * @version	$Id: XX_Visit.sql,v 1.1 2006/04/21 17:55:19 jjanke Exp $
 */
CREATE TABLE XX_Visit(
    XX_Visit_ID               NUMBER(10, 0)     NOT NULL,
    AD_Client_ID              NUMBER(10, 0)     NOT NULL,
    AD_Org_ID                 NUMBER(10, 0)     NOT NULL,
    IsActive                  CHAR(1)            DEFAULT 'Y' NOT NULL
                              CHECK (IsActive in ('Y','N')),
    Created                   DATE               DEFAULT SYSDATE NOT NULL,
    CreatedBy                 NUMBER(10, 0)     NOT NULL,
    Updated                   DATE               DEFAULT SYSDATE NOT NULL,
    UpdatedBy                 NUMBER(10, 0)     NOT NULL,
    Name                      NVARCHAR2(60)     NOT NULL,
    Description               NVARCHAR2(255),
    VisitTime                 DATE              NOT NULL,
    Minutes                   NUMBER(10, 0)     NOT NULL,
    C_BPartner_ID             NUMBER(10, 0)     NOT NULL,
    C_BPartner_Location_ID    NUMBER(10, 0)     NOT NULL,
    AD_User_ID                NUMBER(10, 0)     NOT NULL,
    SalesRep_ID               NUMBER(10, 0)     NOT NULL,
    R_InterestArea_ID         NUMBER(10, 0)     NOT NULL,
    Processed                 CHAR(1),
    Processing                CHAR(1),
    CONSTRAINT XX_Visit_Key PRIMARY KEY (XX_Visit_ID), 
    CONSTRAINT CBPLoction_XXVisit FOREIGN KEY (C_BPartner_Location_ID)
    REFERENCES C_BPartner_Location(C_BPartner_Location_ID),
    CONSTRAINT ADUser_XXVisit FOREIGN KEY (AD_User_ID)
    REFERENCES AD_User(AD_User_ID),
    CONSTRAINT RInterestArea_XXVisit FOREIGN KEY (R_InterestArea_ID)
    REFERENCES R_InterestArea(R_InterestArea_ID),
    CONSTRAINT ADUserSalesRep_XXVisit FOREIGN KEY (SalesRep_ID)
    REFERENCES AD_User(AD_User_ID),
    CONSTRAINT CBPartner_XXVisit FOREIGN KEY (C_BPartner_ID)
    REFERENCES C_BPartner(C_BPartner_ID)
) 
/



