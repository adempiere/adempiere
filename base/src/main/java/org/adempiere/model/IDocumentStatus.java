package org.adempiere.model;

public interface IDocumentStatus {

	public static final String Table_Name = "PA_DocumentStatus";

	public String getName();

	public int getAD_Table_ID();

	public int getC_Project_ID();

	public int getAD_Org_ID();

	public String getWhereClause();

	public int getAD_Window_ID();

	public int getName_PrintColor_ID();

	public int getNumber_PrintColor_ID();

	public int getName_PrintFont_ID();

	public int getNumber_PrintFont_ID();

	public int getAD_Form_ID();
	
	public int getAD_Client_ID();
}
