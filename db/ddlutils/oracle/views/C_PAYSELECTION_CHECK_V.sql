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

