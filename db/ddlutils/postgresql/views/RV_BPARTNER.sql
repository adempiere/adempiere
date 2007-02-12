CREATE OR REPLACE VIEW RV_BPARTNER
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
 SUPERVISOR_ID, EMAILUSER, BPCONTACTGREETING, TITLE, COMMENTS, 
 PHONE, PHONE2, FAX, LASTCONTACT, LASTRESULT, 
 BIRTHDAY, AD_ORGTRX_ID, EMAILVERIFY, LDAPUSER, EMAILVERIFYDATE, 
 NOTIFICATIONTYPE, C_BPARTNER_LOCATION_ID, POSTAL, CITY, ADDRESS1, 
 ADDRESS2, ADDRESS3, C_REGION_ID, REGIONNAME, C_COUNTRY_ID, 
 COUNTRYNAME)
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
    c.EMail, c.Supervisor_ID, 
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



