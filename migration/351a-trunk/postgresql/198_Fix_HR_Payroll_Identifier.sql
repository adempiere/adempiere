-- Reset SeqNo for HR_Payroll.Value, there is already HR_Payroll.Name as identifier
update AD_Column set SeqNo=null
where  AD_Column_ID=54887 and IsIdentifier='N' and SeqNo=1;
