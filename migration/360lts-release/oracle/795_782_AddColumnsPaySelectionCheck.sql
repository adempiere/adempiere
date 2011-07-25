-- Add columns DocumentType, DocumentTypeNote and Description to C_PaySelectionCheck view

CREATE OR REPLACE VIEW C_PAYSELECTION_CHECK_V
(AD_CLIENT_ID, AD_ORG_ID, AD_LANGUAGE, C_PAYSELECTION_ID, C_PAYSELECTIONCHECK_ID, 
 ORG_LOCATION_ID, TAXID, C_DOCTYPE_ID, C_BPARTNER_ID, BPVALUE, 
 BPTAXID, NAICS, DUNS, BPGREETING, NAME, 
 NAME2, C_LOCATION_ID, REFERENCENO, POREFERENCE, PAYDATE, 
 PAYAMT, AMTINWORDS, QTY, PAYMENTRULE, DOCUMENTNO, LOGO_ID,
 DOCUMENTTYPE, DOCUMENTTYPENOTE, DESCRIPTION)
AS 
SELECT psc.AD_Client_ID, psc.AD_Org_ID, 
	cast('en_US' as varchar2(6)) AS AD_Language,
	psc.C_PaySelection_ID, psc.C_PaySelectionCheck_ID,
	oi.C_Location_ID AS Org_Location_ID, oi.TaxID, p.C_DocType_ID,
	bp.C_BPartner_ID, bp.Value AS BPValue, bp.TaxID AS BPTaxID, bp.NAICS, bp.DUNS,
	bpg.Greeting AS BPGreeting,
	bp.Name, bp.Name2,
	bpartnerRemitLocation(bp.C_BPartner_ID) AS C_Location_ID,
	bp.ReferenceNo, bp.POReference,
	ps.PayDate,
	psc.PayAmt, psc.PayAmt AS AmtInWords,
	psc.Qty, psc.PaymentRule, psc.DocumentNo, COALESCE(oi.Logo_ID, ci.Logo_ID) AS Logo_ID,
	dt.PrintName AS DocumentType, dt.DocumentNote AS DocumentTypeNote, p.Description
FROM C_PaySelectionCheck psc
	INNER JOIN C_PaySelection ps ON (psc.C_PaySelection_ID=ps.C_PaySelection_ID)
	LEFT JOIN c_payment p ON (psc.c_payment_id = p.c_payment_id)
	LEFT JOIN c_doctype dt ON (p.c_doctype_id = dt.c_doctype_id)
	INNER JOIN C_BPartner bp ON (psc.C_BPartner_ID=bp.C_BPartner_ID)
	LEFT OUTER JOIN C_Greeting bpg on (bp.C_Greeting_ID=bpg.C_Greeting_ID)
	INNER JOIN AD_OrgInfo oi ON (psc.AD_Org_ID=oi.AD_Org_ID)
	INNER JOIN AD_ClientInfo ci ON (psc.AD_Client_ID=ci.AD_Client_ID);

CREATE OR REPLACE VIEW C_PAYSELECTION_CHECK_VT
(AD_CLIENT_ID, AD_ORG_ID, AD_LANGUAGE, C_PAYSELECTION_ID, C_PAYSELECTIONCHECK_ID, 
 ORG_LOCATION_ID, TAXID, C_DOCTYPE_ID, C_BPARTNER_ID, BPVALUE, 
 BPTAXID, NAICS, DUNS, BPGREETING, NAME, 
 NAME2, C_LOCATION_ID, REFERENCENO, POREFERENCE, PAYDATE, 
 PAYAMT, AMTINWORDS, QTY, PAYMENTRULE, DOCUMENTNO, LOGO_ID,
 DOCUMENTTYPE, DOCUMENTTYPENOTE, DESCRIPTION)
AS 
SELECT psc.AD_Client_ID, psc.AD_Org_ID, 
	l.AD_Language,
	psc.C_PaySelection_ID, psc.C_PaySelectionCheck_ID,
	oi.C_Location_ID AS Org_Location_ID, oi.TaxID, p.C_DocType_ID,
	bp.C_BPartner_ID, bp.Value AS BPValue, bp.TaxID AS BPTaxID, bp.NAICS, bp.DUNS,
	bpg.Greeting AS BPGreeting,
	bp.Name, bp.Name2,
	bpartnerRemitLocation(bp.C_BPartner_ID) AS C_Location_ID,
	bp.ReferenceNo, bp.POReference,
	ps.PayDate,
	psc.PayAmt, psc.PayAmt AS AmtInWords,
	psc.Qty, psc.PaymentRule, psc.DocumentNo, COALESCE(oi.Logo_ID, ci.Logo_ID) AS Logo_ID,
	dt.PrintName AS DocumentType, dt.DocumentNote AS DocumentTypeNote, p.Description
FROM C_PaySelectionCheck psc
	INNER JOIN C_PaySelection ps ON (psc.C_PaySelection_ID=ps.C_PaySelection_ID)
	LEFT JOIN c_payment p ON (psc.c_payment_id = p.c_payment_id)
	LEFT JOIN c_doctype_trl dt ON (p.c_doctype_id = dt.c_doctype_id)
	INNER JOIN C_BPartner bp ON (psc.C_BPartner_ID=bp.C_BPartner_ID)
	INNER JOIN AD_OrgInfo oi ON (psc.AD_Org_ID=oi.AD_Org_ID)
	INNER JOIN AD_ClientInfo ci ON (psc.AD_Client_ID=ci.AD_Client_ID)
	LEFT OUTER JOIN AD_Language l ON (l.IsSystemLanguage='Y')
	LEFT OUTER JOIN C_Greeting_Trl bpg on (bp.C_Greeting_ID=bpg.C_Greeting_ID AND bpg.AD_Language=l.AD_Language);

-- Mar 24, 2011 4:52:15 PM COT
-- Add columns DocumentType, DocumentTypeNote and Description to C_PaySelectionCheck view
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,60349,1841,0,10,498,'DocumentType',TO_DATE('2011-03-24 16:52:12','YYYY-MM-DD HH24:MI:SS'),100,'Document Type','D',60,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Document Type',0,TO_DATE('2011-03-24 16:52:12','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Mar 24, 2011 4:52:15 PM COT
-- Add columns DocumentType, DocumentTypeNote and Description to C_PaySelectionCheck view
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=60349 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Mar 24, 2011 4:52:47 PM COT
-- Add columns DocumentType, DocumentTypeNote and Description to C_PaySelectionCheck view
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,60350,1842,0,10,498,'DocumentTypeNote',TO_DATE('2011-03-24 16:52:47','YYYY-MM-DD HH24:MI:SS'),100,'Optional note of a document type','D',2000,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Document Type Note',0,TO_DATE('2011-03-24 16:52:47','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Mar 24, 2011 4:52:47 PM COT
-- Add columns DocumentType, DocumentTypeNote and Description to C_PaySelectionCheck view
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=60350 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Mar 24, 2011 4:53:34 PM COT
-- Add columns DocumentType, DocumentTypeNote and Description to C_PaySelectionCheck view
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,60351,275,0,10,498,'Description',TO_DATE('2011-03-24 16:53:33','YYYY-MM-DD HH24:MI:SS'),100,'Optional short description of the record','D',255,'A description is limited to 255 characters.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Description',0,TO_DATE('2011-03-24 16:53:33','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Mar 24, 2011 4:53:34 PM COT
-- Add columns DocumentType, DocumentTypeNote and Description to C_PaySelectionCheck view
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=60351 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

