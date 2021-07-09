/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.										*
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for I_BPartner
 *  @author Adempiere (generated) 
 *  @version Release 3.9.3
 */
public interface I_I_BPartner 
{

    /** TableName=I_BPartner */
    public static final String Table_Name = "I_BPartner";

    /** AD_Table_ID=533 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 2 - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(2);

    /** Load Meta Data */

    /** Column name AccountNo */
    public static final String COLUMNNAME_AccountNo = "AccountNo";

	/** Set Account No.
	  * Account Number
	  */
	public void setAccountNo (String AccountNo);

	/** Get Account No.
	  * Account Number
	  */
	public String getAccountNo();

    /** Column name A_City */
    public static final String COLUMNNAME_A_City = "A_City";

	/** Set Account City.
	  * City or the Credit Card or Account Holder
	  */
	public void setA_City (String A_City);

	/** Get Account City.
	  * City or the Credit Card or Account Holder
	  */
	public String getA_City();

    /** Column name A_Country */
    public static final String COLUMNNAME_A_Country = "A_Country";

	/** Set Account Country.
	  * Country
	  */
	public void setA_Country (String A_Country);

	/** Get Account Country.
	  * Country
	  */
	public String getA_Country();

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name Address1 */
    public static final String COLUMNNAME_Address1 = "Address1";

	/** Set Address 1.
	  * Address line 1 for this location
	  */
	public void setAddress1 (String Address1);

	/** Get Address 1.
	  * Address line 1 for this location
	  */
	public String getAddress1();

    /** Column name Address2 */
    public static final String COLUMNNAME_Address2 = "Address2";

	/** Set Address 2.
	  * Address line 2 for this location
	  */
	public void setAddress2 (String Address2);

	/** Get Address 2.
	  * Address line 2 for this location
	  */
	public String getAddress2();

    /** Column name Address3 */
    public static final String COLUMNNAME_Address3 = "Address3";

	/** Set Address 3.
	  * Address Line 3 for the location
	  */
	public void setAddress3 (String Address3);

	/** Get Address 3.
	  * Address Line 3 for the location
	  */
	public String getAddress3();

    /** Column name Address4 */
    public static final String COLUMNNAME_Address4 = "Address4";

	/** Set Address 4.
	  * Address Line 4 for the location
	  */
	public void setAddress4 (String Address4);

	/** Get Address 4.
	  * Address Line 4 for the location
	  */
	public String getAddress4();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name AD_User_ID */
    public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";

	/** Set User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID);

	/** Get User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID();

	public org.compiere.model.I_AD_User getAD_User() throws RuntimeException;

    /** Column name A_EMail */
    public static final String COLUMNNAME_A_EMail = "A_EMail";

	/** Set Account EMail.
	  * Email Address
	  */
	public void setA_EMail (String A_EMail);

	/** Get Account EMail.
	  * Email Address
	  */
	public String getA_EMail();

    /** Column name A_Ident_DL */
    public static final String COLUMNNAME_A_Ident_DL = "A_Ident_DL";

	/** Set Driver License.
	  * Payment Identification - Driver License
	  */
	public void setA_Ident_DL (String A_Ident_DL);

	/** Get Driver License.
	  * Payment Identification - Driver License
	  */
	public String getA_Ident_DL();

    /** Column name A_Ident_SSN */
    public static final String COLUMNNAME_A_Ident_SSN = "A_Ident_SSN";

	/** Set Social Security No.
	  * Payment Identification - Social Security No
	  */
	public void setA_Ident_SSN (String A_Ident_SSN);

	/** Get Social Security No.
	  * Payment Identification - Social Security No
	  */
	public String getA_Ident_SSN();

    /** Column name A_Name */
    public static final String COLUMNNAME_A_Name = "A_Name";

	/** Set Account Name.
	  * Name on Credit Card or Account holder
	  */
	public void setA_Name (String A_Name);

	/** Get Account Name.
	  * Name on Credit Card or Account holder
	  */
	public String getA_Name();

    /** Column name A_State */
    public static final String COLUMNNAME_A_State = "A_State";

	/** Set Account State.
	  * State of the Credit Card or Account holder
	  */
	public void setA_State (String A_State);

	/** Get Account State.
	  * State of the Credit Card or Account holder
	  */
	public String getA_State();

    /** Column name A_Street */
    public static final String COLUMNNAME_A_Street = "A_Street";

	/** Set Account Street.
	  * Street address of the Credit Card or Account holder
	  */
	public void setA_Street (String A_Street);

	/** Get Account Street.
	  * Street address of the Credit Card or Account holder
	  */
	public String getA_Street();

    /** Column name A_Zip */
    public static final String COLUMNNAME_A_Zip = "A_Zip";

	/** Set Account Zip/Postal.
	  * Zip Code of the Credit Card or Account Holder
	  */
	public void setA_Zip (String A_Zip);

	/** Get Account Zip/Postal.
	  * Zip Code of the Credit Card or Account Holder
	  */
	public String getA_Zip();

    /** Column name BankAccountType */
    public static final String COLUMNNAME_BankAccountType = "BankAccountType";

	/** Set Bank Account Type.
	  * Bank Account Type
	  */
	public void setBankAccountType (String BankAccountType);

	/** Get Bank Account Type.
	  * Bank Account Type
	  */
	public String getBankAccountType();

    /** Column name BankName */
    public static final String COLUMNNAME_BankName = "BankName";

	/** Set Bank Name	  */
	public void setBankName (String BankName);

	/** Get Bank Name	  */
	public String getBankName();

    /** Column name BirthCity */
    public static final String COLUMNNAME_BirthCity = "BirthCity";

	/** Set Birth City.
	  * Identifies a City of Birth
	  */
	public void setBirthCity (String BirthCity);

	/** Get Birth City.
	  * Identifies a City of Birth
	  */
	public String getBirthCity();

    /** Column name BirthCountryCode */
    public static final String COLUMNNAME_BirthCountryCode = "BirthCountryCode";

	/** Set Birth Country Code.
	  * Country Code of Place of Birth
	  */
	public void setBirthCountryCode (String BirthCountryCode);

	/** Get Birth Country Code.
	  * Country Code of Place of Birth
	  */
	public String getBirthCountryCode();

    /** Column name BirthCountry_ID */
    public static final String COLUMNNAME_BirthCountry_ID = "BirthCountry_ID";

	/** Set Birth Country.
	  * Country of Place of Birth
	  */
	public void setBirthCountry_ID (int BirthCountry_ID);

	/** Get Birth Country.
	  * Country of Place of Birth
	  */
	public int getBirthCountry_ID();

	public org.compiere.model.I_C_Country getBirthCountry() throws RuntimeException;

    /** Column name Birthday */
    public static final String COLUMNNAME_Birthday = "Birthday";

	/** Set Birthday.
	  * Birthday or Anniversary day
	  */
	public void setBirthday (Timestamp Birthday);

	/** Get Birthday.
	  * Birthday or Anniversary day
	  */
	public Timestamp getBirthday();

    /** Column name BirthPostal */
    public static final String COLUMNNAME_BirthPostal = "BirthPostal";

	/** Set Birth Postal Zone.
	  * Postal Zone of Place of Birth
	  */
	public void setBirthPostal (String BirthPostal);

	/** Get Birth Postal Zone.
	  * Postal Zone of Place of Birth
	  */
	public String getBirthPostal();

    /** Column name BirthRegion_ID */
    public static final String COLUMNNAME_BirthRegion_ID = "BirthRegion_ID";

	/** Set Region of Birth.
	  * Name of the Region of Birth
	  */
	public void setBirthRegion_ID (int BirthRegion_ID);

	/** Get Region of Birth.
	  * Name of the Region of Birth
	  */
	public int getBirthRegion_ID();

	public org.compiere.model.I_C_Region getBirthRegion() throws RuntimeException;

    /** Column name BirthRegionName */
    public static final String COLUMNNAME_BirthRegionName = "BirthRegionName";

	/** Set Region of Birth.
	  * Name of the Region of Birth
	  */
	public void setBirthRegionName (String BirthRegionName);

	/** Get Region of Birth.
	  * Name of the Region of Birth
	  */
	public String getBirthRegionName();

    /** Column name BloodGroup */
    public static final String COLUMNNAME_BloodGroup = "BloodGroup";

	/** Set Blood Group	  */
	public void setBloodGroup (String BloodGroup);

	/** Get Blood Group	  */
	public String getBloodGroup();

    /** Column name BPBankAcctUse */
    public static final String COLUMNNAME_BPBankAcctUse = "BPBankAcctUse";

	/** Set Account Usage.
	  * Business Partner Bank Account usage
	  */
	public void setBPBankAcctUse (String BPBankAcctUse);

	/** Get Account Usage.
	  * Business Partner Bank Account usage
	  */
	public String getBPBankAcctUse();

    /** Column name BPContactGreeting */
    public static final String COLUMNNAME_BPContactGreeting = "BPContactGreeting";

	/** Set BP Contact Greeting.
	  * Greeting for Business Partner Contact
	  */
	public void setBPContactGreeting (String BPContactGreeting);

	/** Get BP Contact Greeting.
	  * Greeting for Business Partner Contact
	  */
	public String getBPContactGreeting();

    /** Column name C_Bank_ID */
    public static final String COLUMNNAME_C_Bank_ID = "C_Bank_ID";

	/** Set Bank.
	  * Bank
	  */
	public void setC_Bank_ID (int C_Bank_ID);

	/** Get Bank.
	  * Bank
	  */
	public int getC_Bank_ID();

	public org.compiere.model.I_C_Bank getC_Bank() throws RuntimeException;

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner .
	  * Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner .
	  * Identifies a Business Partner
	  */
	public int getC_BPartner_ID();

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name C_BPartner_Location_ID */
    public static final String COLUMNNAME_C_BPartner_Location_ID = "C_BPartner_Location_ID";

	/** Set Partner Location.
	  * Identifies the (ship to) address for this Business Partner
	  */
	public void setC_BPartner_Location_ID (int C_BPartner_Location_ID);

	/** Get Partner Location.
	  * Identifies the (ship to) address for this Business Partner
	  */
	public int getC_BPartner_Location_ID();

	public org.compiere.model.I_C_BPartner_Location getC_BPartner_Location() throws RuntimeException;

    /** Column name C_BP_BankAccount_ID */
    public static final String COLUMNNAME_C_BP_BankAccount_ID = "C_BP_BankAccount_ID";

	/** Set Partner Bank Account.
	  * Bank Account of the Business Partner
	  */
	public void setC_BP_BankAccount_ID (int C_BP_BankAccount_ID);

	/** Get Partner Bank Account.
	  * Bank Account of the Business Partner
	  */
	public int getC_BP_BankAccount_ID();

	public org.compiere.model.I_C_BP_BankAccount getC_BP_BankAccount() throws RuntimeException;

    /** Column name C_BP_Group_ID */
    public static final String COLUMNNAME_C_BP_Group_ID = "C_BP_Group_ID";

	/** Set Business Partner Group.
	  * Business Partner Group
	  */
	public void setC_BP_Group_ID (int C_BP_Group_ID);

	/** Get Business Partner Group.
	  * Business Partner Group
	  */
	public int getC_BP_Group_ID();

	public org.compiere.model.I_C_BP_Group getC_BP_Group() throws RuntimeException;

    /** Column name C_Country_ID */
    public static final String COLUMNNAME_C_Country_ID = "C_Country_ID";

	/** Set Country.
	  * Country 
	  */
	public void setC_Country_ID (int C_Country_ID);

	/** Get Country.
	  * Country 
	  */
	public int getC_Country_ID();

	public org.compiere.model.I_C_Country getC_Country() throws RuntimeException;

    /** Column name C_Greeting_ID */
    public static final String COLUMNNAME_C_Greeting_ID = "C_Greeting_ID";

	/** Set Greeting.
	  * Greeting to print on correspondence
	  */
	public void setC_Greeting_ID (int C_Greeting_ID);

	/** Get Greeting.
	  * Greeting to print on correspondence
	  */
	public int getC_Greeting_ID();

	public org.compiere.model.I_C_Greeting getC_Greeting() throws RuntimeException;

    /** Column name City */
    public static final String COLUMNNAME_City = "City";

	/** Set City.
	  * Identifies a City
	  */
	public void setCity (String City);

	/** Get City.
	  * Identifies a City
	  */
	public String getCity();

    /** Column name Comments */
    public static final String COLUMNNAME_Comments = "Comments";

	/** Set Comments.
	  * Comments or additional information
	  */
	public void setComments (String Comments);

	/** Get Comments.
	  * Comments or additional information
	  */
	public String getComments();

    /** Column name ContactDescription */
    public static final String COLUMNNAME_ContactDescription = "ContactDescription";

	/** Set Contact Description.
	  * Description of Contact
	  */
	public void setContactDescription (String ContactDescription);

	/** Get Contact Description.
	  * Description of Contact
	  */
	public String getContactDescription();

    /** Column name ContactName */
    public static final String COLUMNNAME_ContactName = "ContactName";

	/** Set Contact Name.
	  * Business Partner Contact Name
	  */
	public void setContactName (String ContactName);

	/** Get Contact Name.
	  * Business Partner Contact Name
	  */
	public String getContactName();

    /** Column name CountryCode */
    public static final String COLUMNNAME_CountryCode = "CountryCode";

	/** Set ISO Country Code.
	  * Upper-case two-letter alphanumeric ISO Country code according to ISO 3166-1 - http://www.chemie.fu-berlin.de/diverse/doc/ISO_3166.html
	  */
	public void setCountryCode (String CountryCode);

	/** Get ISO Country Code.
	  * Upper-case two-letter alphanumeric ISO Country code according to ISO 3166-1 - http://www.chemie.fu-berlin.de/diverse/doc/ISO_3166.html
	  */
	public String getCountryCode();

    /** Column name C_Project_ID */
    public static final String COLUMNNAME_C_Project_ID = "C_Project_ID";

	/** Set Project.
	  * Financial Project
	  */
	public void setC_Project_ID (int C_Project_ID);

	/** Get Project.
	  * Financial Project
	  */
	public int getC_Project_ID();

	public org.compiere.model.I_C_Project getC_Project() throws RuntimeException;

    /** Column name C_ProjectMemberType_ID */
    public static final String COLUMNNAME_C_ProjectMemberType_ID = "C_ProjectMemberType_ID";

	/** Set Project Member Type.
	  * Define the Member Type for a Project
	  */
	public void setC_ProjectMemberType_ID (int C_ProjectMemberType_ID);

	/** Get Project Member Type.
	  * Define the Member Type for a Project
	  */
	public int getC_ProjectMemberType_ID();

	public org.eevolution.model.I_C_ProjectMemberType getC_ProjectMemberType() throws RuntimeException;

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name CreditCardExpMM */
    public static final String COLUMNNAME_CreditCardExpMM = "CreditCardExpMM";

	/** Set Exp. Month.
	  * Expiry Month
	  */
	public void setCreditCardExpMM (int CreditCardExpMM);

	/** Get Exp. Month.
	  * Expiry Month
	  */
	public int getCreditCardExpMM();

    /** Column name CreditCardExpYY */
    public static final String COLUMNNAME_CreditCardExpYY = "CreditCardExpYY";

	/** Set Exp. Year.
	  * Expiry Year
	  */
	public void setCreditCardExpYY (int CreditCardExpYY);

	/** Get Exp. Year.
	  * Expiry Year
	  */
	public int getCreditCardExpYY();

    /** Column name CreditCardNumber */
    public static final String COLUMNNAME_CreditCardNumber = "CreditCardNumber";

	/** Set Number.
	  * Credit Card Number 
	  */
	public void setCreditCardNumber (String CreditCardNumber);

	/** Get Number.
	  * Credit Card Number 
	  */
	public String getCreditCardNumber();

    /** Column name CreditCardType */
    public static final String COLUMNNAME_CreditCardType = "CreditCardType";

	/** Set Credit Card.
	  * Credit Card (Visa, MC, AmEx)
	  */
	public void setCreditCardType (String CreditCardType);

	/** Get Credit Card.
	  * Credit Card (Visa, MC, AmEx)
	  */
	public String getCreditCardType();

    /** Column name CreditCardVV */
    public static final String COLUMNNAME_CreditCardVV = "CreditCardVV";

	/** Set Verification Code.
	  * Credit Card Verification code on credit card
	  */
	public void setCreditCardVV (String CreditCardVV);

	/** Get Verification Code.
	  * Credit Card Verification code on credit card
	  */
	public String getCreditCardVV();

    /** Column name C_Region_ID */
    public static final String COLUMNNAME_C_Region_ID = "C_Region_ID";

	/** Set Region.
	  * Identifies a geographical Region
	  */
	public void setC_Region_ID (int C_Region_ID);

	/** Get Region.
	  * Identifies a geographical Region
	  */
	public int getC_Region_ID();

	public org.compiere.model.I_C_Region getC_Region() throws RuntimeException;

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name DUNS */
    public static final String COLUMNNAME_DUNS = "DUNS";

	/** Set D-U-N-S.
	  * Dun & Bradstreet Number
	  */
	public void setDUNS (String DUNS);

	/** Get D-U-N-S.
	  * Dun & Bradstreet Number
	  */
	public String getDUNS();

    /** Column name EMail */
    public static final String COLUMNNAME_EMail = "EMail";

	/** Set EMail Address.
	  * Electronic Mail Address
	  */
	public void setEMail (String EMail);

	/** Get EMail Address.
	  * Electronic Mail Address
	  */
	public String getEMail();

    /** Column name FathersName */
    public static final String COLUMNNAME_FathersName = "FathersName";

	/** Set Father's Name.
	  * Father's Name
	  */
	public void setFathersName (String FathersName);

	/** Get Father's Name.
	  * Father's Name
	  */
	public String getFathersName();

    /** Column name Fax */
    public static final String COLUMNNAME_Fax = "Fax";

	/** Set Fax.
	  * Facsimile number
	  */
	public void setFax (String Fax);

	/** Get Fax.
	  * Facsimile number
	  */
	public String getFax();

    /** Column name Gender */
    public static final String COLUMNNAME_Gender = "Gender";

	/** Set Gender	  */
	public void setGender (String Gender);

	/** Get Gender	  */
	public String getGender();

    /** Column name GroupValue */
    public static final String COLUMNNAME_GroupValue = "GroupValue";

	/** Set Group Key.
	  * Business Partner Group Key
	  */
	public void setGroupValue (String GroupValue);

	/** Get Group Key.
	  * Business Partner Group Key
	  */
	public String getGroupValue();

    /** Column name IBAN */
    public static final String COLUMNNAME_IBAN = "IBAN";

	/** Set IBAN.
	  * International Bank Account Number
	  */
	public void setIBAN (String IBAN);

	/** Get IBAN.
	  * International Bank Account Number
	  */
	public String getIBAN();

    /** Column name I_BPartner_ID */
    public static final String COLUMNNAME_I_BPartner_ID = "I_BPartner_ID";

	/** Set Import Business Partner	  */
	public void setI_BPartner_ID (int I_BPartner_ID);

	/** Get Import Business Partner	  */
	public int getI_BPartner_ID();

    /** Column name I_ErrorMsg */
    public static final String COLUMNNAME_I_ErrorMsg = "I_ErrorMsg";

	/** Set Import Error Message.
	  * Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg);

	/** Get Import Error Message.
	  * Messages generated from import process
	  */
	public String getI_ErrorMsg();

    /** Column name I_IsImported */
    public static final String COLUMNNAME_I_IsImported = "I_IsImported";

	/** Set Imported.
	  * Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported);

	/** Get Imported.
	  * Has this import been processed
	  */
	public boolean isI_IsImported();

    /** Column name InterestAreaName */
    public static final String COLUMNNAME_InterestAreaName = "InterestAreaName";

	/** Set Interest Area.
	  * Name of the Interest Area
	  */
	public void setInterestAreaName (String InterestAreaName);

	/** Get Interest Area.
	  * Name of the Interest Area
	  */
	public String getInterestAreaName();

    /** Column name IsACH */
    public static final String COLUMNNAME_IsACH = "IsACH";

	/** Set ACH.
	  * Automatic Clearing House
	  */
	public void setIsACH (boolean IsACH);

	/** Get ACH.
	  * Automatic Clearing House
	  */
	public boolean isACH();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name IsCustomer */
    public static final String COLUMNNAME_IsCustomer = "IsCustomer";

	/** Set Customer.
	  * Indicates if this Business Partner is a Customer
	  */
	public void setIsCustomer (boolean IsCustomer);

	/** Get Customer.
	  * Indicates if this Business Partner is a Customer
	  */
	public boolean isCustomer();

    /** Column name IsEmployee */
    public static final String COLUMNNAME_IsEmployee = "IsEmployee";

	/** Set Employee.
	  * Indicates if  this Business Partner is an employee
	  */
	public void setIsEmployee (boolean IsEmployee);

	/** Get Employee.
	  * Indicates if  this Business Partner is an employee
	  */
	public boolean isEmployee();

    /** Column name IsProjectManager */
    public static final String COLUMNNAME_IsProjectManager = "IsProjectManager";

	/** Set Is Project Manager.
	  * Is Project Manager
	  */
	public void setIsProjectManager (boolean IsProjectManager);

	/** Get Is Project Manager.
	  * Is Project Manager
	  */
	public boolean isProjectManager();

    /** Column name IsProjectMember */
    public static final String COLUMNNAME_IsProjectMember = "IsProjectMember";

	/** Set Is Project Member.
	  * Is Project Member
	  */
	public void setIsProjectMember (boolean IsProjectMember);

	/** Get Is Project Member.
	  * Is Project Member
	  */
	public boolean isProjectMember();

    /** Column name IsVendor */
    public static final String COLUMNNAME_IsVendor = "IsVendor";

	/** Set Vendor.
	  * Indicates if this Business Partner is a Vendor
	  */
	public void setIsVendor (boolean IsVendor);

	/** Get Vendor.
	  * Indicates if this Business Partner is a Vendor
	  */
	public boolean isVendor();

    /** Column name NAICS */
    public static final String COLUMNNAME_NAICS = "NAICS";

	/** Set NAICS/SIC.
	  * Standard Industry Code or its successor NAIC - http://www.osha.gov/oshstats/sicser.html
	  */
	public void setNAICS (String NAICS);

	/** Get NAICS/SIC.
	  * Standard Industry Code or its successor NAIC - http://www.osha.gov/oshstats/sicser.html
	  */
	public String getNAICS();

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name Name2 */
    public static final String COLUMNNAME_Name2 = "Name2";

	/** Set Name 2.
	  * Additional Name
	  */
	public void setName2 (String Name2);

	/** Get Name 2.
	  * Additional Name
	  */
	public String getName2();

    /** Column name Password */
    public static final String COLUMNNAME_Password = "Password";

	/** Set Password.
	  * Password of any length (case sensitive)
	  */
	public void setPassword (String Password);

	/** Get Password.
	  * Password of any length (case sensitive)
	  */
	public String getPassword();

    /** Column name Phone */
    public static final String COLUMNNAME_Phone = "Phone";

	/** Set Phone.
	  * Identifies a telephone number
	  */
	public void setPhone (String Phone);

	/** Get Phone.
	  * Identifies a telephone number
	  */
	public String getPhone();

    /** Column name Phone2 */
    public static final String COLUMNNAME_Phone2 = "Phone2";

	/** Set 2nd Phone.
	  * Identifies an alternate telephone number.
	  */
	public void setPhone2 (String Phone2);

	/** Get 2nd Phone.
	  * Identifies an alternate telephone number.
	  */
	public String getPhone2();

    /** Column name PlaceOfBirth_ID */
    public static final String COLUMNNAME_PlaceOfBirth_ID = "PlaceOfBirth_ID";

	/** Set Place of Birth (Location).
	  * Place of Birth (Location)
	  */
	public void setPlaceOfBirth_ID (int PlaceOfBirth_ID);

	/** Get Place of Birth (Location).
	  * Place of Birth (Location)
	  */
	public int getPlaceOfBirth_ID();

	public I_C_Location getPlaceOfBirth() throws RuntimeException;

    /** Column name Postal */
    public static final String COLUMNNAME_Postal = "Postal";

	/** Set ZIP.
	  * Postal code
	  */
	public void setPostal (String Postal);

	/** Get ZIP.
	  * Postal code
	  */
	public String getPostal();

    /** Column name Postal_Add */
    public static final String COLUMNNAME_Postal_Add = "Postal_Add";

	/** Set Additional Zip.
	  * Additional ZIP or Postal code
	  */
	public void setPostal_Add (String Postal_Add);

	/** Get Additional Zip.
	  * Additional ZIP or Postal code
	  */
	public String getPostal_Add();

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name ProjectMemberTypeValue */
    public static final String COLUMNNAME_ProjectMemberTypeValue = "ProjectMemberTypeValue";

	/** Set Project Member Type Value.
	  * Define the Search Key of Project Member Type
	  */
	public void setProjectMemberTypeValue (String ProjectMemberTypeValue);

	/** Get Project Member Type Value.
	  * Define the Search Key of Project Member Type
	  */
	public String getProjectMemberTypeValue();

    /** Column name ProjectValue */
    public static final String COLUMNNAME_ProjectValue = "ProjectValue";

	/** Set Project Key.
	  * Key of the Project
	  */
	public void setProjectValue (String ProjectValue);

	/** Get Project Key.
	  * Key of the Project
	  */
	public String getProjectValue();

    /** Column name R_AvsAddr */
    public static final String COLUMNNAME_R_AvsAddr = "R_AvsAddr";

	/** Set Address verified.
	  * This address has been verified
	  */
	public void setR_AvsAddr (String R_AvsAddr);

	/** Get Address verified.
	  * This address has been verified
	  */
	public String getR_AvsAddr();

    /** Column name R_AvsZip */
    public static final String COLUMNNAME_R_AvsZip = "R_AvsZip";

	/** Set Zip verified.
	  * The Zip Code has been verified
	  */
	public void setR_AvsZip (String R_AvsZip);

	/** Get Zip verified.
	  * The Zip Code has been verified
	  */
	public String getR_AvsZip();

    /** Column name RegionName */
    public static final String COLUMNNAME_RegionName = "RegionName";

	/** Set Region.
	  * Name of the Region
	  */
	public void setRegionName (String RegionName);

	/** Get Region.
	  * Name of the Region
	  */
	public String getRegionName();

    /** Column name R_InterestArea_ID */
    public static final String COLUMNNAME_R_InterestArea_ID = "R_InterestArea_ID";

	/** Set Interest Area.
	  * Interest Area or Topic
	  */
	public void setR_InterestArea_ID (int R_InterestArea_ID);

	/** Get Interest Area.
	  * Interest Area or Topic
	  */
	public int getR_InterestArea_ID();

	public org.compiere.model.I_R_InterestArea getR_InterestArea() throws RuntimeException;

    /** Column name RoutingNo */
    public static final String COLUMNNAME_RoutingNo = "RoutingNo";

	/** Set Routing No.
	  * Bank Routing Number
	  */
	public void setRoutingNo (String RoutingNo);

	/** Get Routing No.
	  * Bank Routing Number
	  */
	public String getRoutingNo();

    /** Column name TaxID */
    public static final String COLUMNNAME_TaxID = "TaxID";

	/** Set Tax ID.
	  * Tax Identification
	  */
	public void setTaxID (String TaxID);

	/** Get Tax ID.
	  * Tax Identification
	  */
	public String getTaxID();

    /** Column name Title */
    public static final String COLUMNNAME_Title = "Title";

	/** Set Title.
	  * Name this entity is referred to as
	  */
	public void setTitle (String Title);

	/** Get Title.
	  * Name this entity is referred to as
	  */
	public String getTitle();

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();

    /** Column name UUID */
    public static final String COLUMNNAME_UUID = "UUID";

	/** Set Immutable Universally Unique Identifier.
	  * Immutable Universally Unique Identifier
	  */
	public void setUUID (String UUID);

	/** Get Immutable Universally Unique Identifier.
	  * Immutable Universally Unique Identifier
	  */
	public String getUUID();

    /** Column name Value */
    public static final String COLUMNNAME_Value = "Value";

	/** Set Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value);

	/** Get Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public String getValue();
}
