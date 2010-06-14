DROP VIEW C_PAYSELECTION_CHECK_VT;
CREATE OR REPLACE VIEW C_PAYSELECTION_CHECK_VT
(AD_CLIENT_ID, AD_ORG_ID, AD_LANGUAGE, C_PAYSELECTION_ID, C_PAYSELECTIONCHECK_ID, 
 ORG_LOCATION_ID, TAXID, C_DOCTYPE_ID, C_BPARTNER_ID, BPVALUE, 
 BPTAXID, NAICS, DUNS, BPGREETING, NAME, 
 NAME2, C_LOCATION_ID, REFERENCENO, POREFERENCE, PAYDATE, 
 PAYAMT, AMTINWORDS, QTY, PAYMENTRULE, DOCUMENTNO, 
 HR_PAYSELECTION_ID , HR_PAYSELECTIONCHECK_ID)
AS 
SELECT psc.AD_Client_ID, psc.AD_Org_ID, 
	l.AD_Language,
	psc.C_PaySelection_ID, psc.C_PaySelectionCheck_ID,
	oi.C_Location_ID AS Org_Location_ID, oi.TaxID, 0 AS C_DocType_ID,
	bp.C_BPartner_ID, bp.Value AS BPValue, bp.TaxID AS BPTaxID, bp.NAICS, bp.DUNS,
	bpg.Greeting AS BPGreeting,
	bp.Name, bp.Name2,
	bpartnerRemitLocation(bp.C_BPartner_ID) AS C_Location_ID,
	bp.ReferenceNo, bp.POReference,
	ps.PayDate,
	psc.PayAmt, psc.PayAmt AS AmtInWords,
	psc.Qty, psc.PaymentRule, psc.DocumentNo,
	0 AS HR_PaySelection_ID , 0 AS HR_PaySelectionCheck_ID 
FROM C_PaySelectionCheck psc
	INNER JOIN C_PaySelection ps ON (psc.C_PaySelection_ID=ps.C_PaySelection_ID)
	INNER JOIN C_BPartner bp ON (psc.C_BPartner_ID=bp.C_BPartner_ID)
	INNER JOIN AD_OrgInfo oi ON (psc.AD_Org_ID=oi.AD_Org_ID)
        LEFT OUTER JOIN AD_Language l ON (l.IsSystemLanguage='Y')
	LEFT OUTER JOIN C_Greeting_Trl bpg on (bp.C_Greeting_ID=bpg.C_Greeting_ID AND bpg.AD_Language=l.AD_Language)
UNION	
SELECT psc.AD_Client_ID, psc.AD_Org_ID, 
	l.AD_Language,
	0 AS C_PaySelection_ID, 0 AS C_PaySelectionCheck_ID, 
	oi.C_Location_ID AS Org_Location_ID, oi.TaxID, 0 AS C_DocType_ID,
	bp.C_BPartner_ID, bp.Value AS BPValue, bp.TaxID AS BPTaxID, bp.NAICS, bp.DUNS, 
	bpg.Greeting AS BPGreeting,
	bp.Name, bp.Name2, 
	BPartnerRemitLocation(bp.C_BPartner_ID) AS C_Location_ID, 
	bp.ReferenceNo, bp.poreference, 
	ps.PayDate, psc.Payamt, psc.PayAmt AS AmtInWords, 
	psc.Qty, psc.PaymentRule, psc.DocumentNo,
	psc.HR_PaySelection_ID , HR_PaySelectionCheck_ID 
FROM hr_payselectioncheck psc
   INNER JOIN HR_PaySelection ps ON (psc.HR_PaySelection_ID = ps.HR_PaySelection_ID)
   INNER JOIN C_BPartner bp ON (psc.C_BPartner_ID = bp.C_BPartner_ID)
   INNER JOIN AD_OrgInfo oi ON (psc.AD_Org_ID = oi.AD_Org_ID)
   LEFT OUTER JOIN AD_Language l ON (l.IsSystemLanguage='Y')
   LEFT OUTER JOIN C_Greeting_Trl bpg on (bp.C_Greeting_ID=bpg.C_Greeting_ID AND bpg.AD_Language=l.AD_Language)