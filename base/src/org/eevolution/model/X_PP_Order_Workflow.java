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
/** Generated Model - DO NOT CHANGE */
package org.eevolution.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for PP_Order_Workflow
 *  @author Adempiere (generated) 
 *  @version Release 3.9.2 - $Id$ */
public class X_PP_Order_Workflow extends PO implements I_PP_Order_Workflow, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20191120L;

    /** Standard Constructor */
    public X_PP_Order_Workflow (Properties ctx, int PP_Order_Workflow_ID, String trxName)
    {
      super (ctx, PP_Order_Workflow_ID, trxName);
      /** if (PP_Order_Workflow_ID == 0)
        {
			setAD_Workflow_ID (0);
			setAccessLevel (null);
			setAuthor (null);
			setCost (Env.ZERO);
			setDuration (0);
// 0
			setDurationUnit (null);
// h
			setEntityType (null);
// U
			setLimit (0);
			setName (null);
			setPP_Order_ID (0);
			setPP_Order_Workflow_ID (0);
			setPriority (0);
			setPublishStatus (null);
// U
			setVersion (0);
			setWaitingTime (0);
        } */
    }

    /** Load Constructor */
    public X_PP_Order_Workflow (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_PP_Order_Workflow[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_AD_Table getAD_Table() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Table)MTable.get(getCtx(), org.compiere.model.I_AD_Table.Table_Name)
			.getPO(getAD_Table_ID(), get_TrxName());	}

	/** Set Table.
		@param AD_Table_ID 
		Database Table information
	  */
	public void setAD_Table_ID (int AD_Table_ID)
	{
		if (AD_Table_ID < 1) 
			set_Value (COLUMNNAME_AD_Table_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Table_ID, Integer.valueOf(AD_Table_ID));
	}

	/** Get Table.
		@return Database Table information
	  */
	public int getAD_Table_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Table_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_AD_WF_Node getAD_WF_Node() throws RuntimeException
    {
		return (org.compiere.model.I_AD_WF_Node)MTable.get(getCtx(), org.compiere.model.I_AD_WF_Node.Table_Name)
			.getPO(getAD_WF_Node_ID(), get_TrxName());	}

	/** Set Node.
		@param AD_WF_Node_ID 
		Workflow Node (activity), step or process
	  */
	public void setAD_WF_Node_ID (int AD_WF_Node_ID)
	{
		if (AD_WF_Node_ID < 1) 
			set_Value (COLUMNNAME_AD_WF_Node_ID, null);
		else 
			set_Value (COLUMNNAME_AD_WF_Node_ID, Integer.valueOf(AD_WF_Node_ID));
	}

	/** Get Node.
		@return Workflow Node (activity), step or process
	  */
	public int getAD_WF_Node_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_WF_Node_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_AD_WF_Responsible getAD_WF_Responsible() throws RuntimeException
    {
		return (org.compiere.model.I_AD_WF_Responsible)MTable.get(getCtx(), org.compiere.model.I_AD_WF_Responsible.Table_Name)
			.getPO(getAD_WF_Responsible_ID(), get_TrxName());	}

	/** Set Workflow Responsible.
		@param AD_WF_Responsible_ID 
		Responsible for Workflow Execution
	  */
	public void setAD_WF_Responsible_ID (int AD_WF_Responsible_ID)
	{
		if (AD_WF_Responsible_ID < 1) 
			set_Value (COLUMNNAME_AD_WF_Responsible_ID, null);
		else 
			set_Value (COLUMNNAME_AD_WF_Responsible_ID, Integer.valueOf(AD_WF_Responsible_ID));
	}

	/** Get Workflow Responsible.
		@return Responsible for Workflow Execution
	  */
	public int getAD_WF_Responsible_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_WF_Responsible_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_AD_WorkflowProcessor getAD_WorkflowProcessor() throws RuntimeException
    {
		return (org.compiere.model.I_AD_WorkflowProcessor)MTable.get(getCtx(), org.compiere.model.I_AD_WorkflowProcessor.Table_Name)
			.getPO(getAD_WorkflowProcessor_ID(), get_TrxName());	}

	/** Set Workflow Processor.
		@param AD_WorkflowProcessor_ID 
		Workflow Processor Server
	  */
	public void setAD_WorkflowProcessor_ID (int AD_WorkflowProcessor_ID)
	{
		if (AD_WorkflowProcessor_ID < 1) 
			set_Value (COLUMNNAME_AD_WorkflowProcessor_ID, null);
		else 
			set_Value (COLUMNNAME_AD_WorkflowProcessor_ID, Integer.valueOf(AD_WorkflowProcessor_ID));
	}

	/** Get Workflow Processor.
		@return Workflow Processor Server
	  */
	public int getAD_WorkflowProcessor_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_WorkflowProcessor_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_AD_Workflow getAD_Workflow() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Workflow)MTable.get(getCtx(), org.compiere.model.I_AD_Workflow.Table_Name)
			.getPO(getAD_Workflow_ID(), get_TrxName());	}

	/** Set Workflow.
		@param AD_Workflow_ID 
		Workflow or combination of tasks
	  */
	public void setAD_Workflow_ID (int AD_Workflow_ID)
	{
		if (AD_Workflow_ID < 1) 
			set_Value (COLUMNNAME_AD_Workflow_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Workflow_ID, Integer.valueOf(AD_Workflow_ID));
	}

	/** Get Workflow.
		@return Workflow or combination of tasks
	  */
	public int getAD_Workflow_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Workflow_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** AccessLevel AD_Reference_ID=5 */
	public static final int ACCESSLEVEL_AD_Reference_ID=5;
	/** Organization = 1 */
	public static final String ACCESSLEVEL_Organization = "1";
	/** Client+Organization = 3 */
	public static final String ACCESSLEVEL_ClientPlusOrganization = "3";
	/** System only = 4 */
	public static final String ACCESSLEVEL_SystemOnly = "4";
	/** All = 7 */
	public static final String ACCESSLEVEL_All = "7";
	/** System+Client = 6 */
	public static final String ACCESSLEVEL_SystemPlusClient = "6";
	/** Client only = 2 */
	public static final String ACCESSLEVEL_ClientOnly = "2";
	/** Set Data Access Level.
		@param AccessLevel 
		Access Level required
	  */
	public void setAccessLevel (String AccessLevel)
	{

		set_Value (COLUMNNAME_AccessLevel, AccessLevel);
	}

	/** Get Data Access Level.
		@return Access Level required
	  */
	public String getAccessLevel () 
	{
		return (String)get_Value(COLUMNNAME_AccessLevel);
	}

	/** Set Author.
		@param Author 
		Author/Creator of the Entity
	  */
	public void setAuthor (String Author)
	{
		set_Value (COLUMNNAME_Author, Author);
	}

	/** Get Author.
		@return Author/Creator of the Entity
	  */
	public String getAuthor () 
	{
		return (String)get_Value(COLUMNNAME_Author);
	}

	/** Set Cost.
		@param Cost 
		Cost information
	  */
	public void setCost (BigDecimal Cost)
	{
		set_Value (COLUMNNAME_Cost, Cost);
	}

	/** Get Cost.
		@return Cost information
	  */
	public BigDecimal getCost () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Cost);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Document No.
		@param DocumentNo 
		Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo)
	{
		set_Value (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
	}

	/** Set Duration.
		@param Duration 
		Normal Duration in Duration Unit
	  */
	public void setDuration (int Duration)
	{
		set_Value (COLUMNNAME_Duration, Integer.valueOf(Duration));
	}

	/** Get Duration.
		@return Normal Duration in Duration Unit
	  */
	public int getDuration () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Duration);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** DurationUnit AD_Reference_ID=299 */
	public static final int DURATIONUNIT_AD_Reference_ID=299;
	/** Year = Y */
	public static final String DURATIONUNIT_Year = "Y";
	/** Month = M */
	public static final String DURATIONUNIT_Month = "M";
	/** Day = D */
	public static final String DURATIONUNIT_Day = "D";
	/** hour = h */
	public static final String DURATIONUNIT_Hour = "h";
	/** minute = m */
	public static final String DURATIONUNIT_Minute = "m";
	/** second = s */
	public static final String DURATIONUNIT_Second = "s";
	/** Set Duration Unit.
		@param DurationUnit 
		Unit of Duration
	  */
	public void setDurationUnit (String DurationUnit)
	{

		set_Value (COLUMNNAME_DurationUnit, DurationUnit);
	}

	/** Get Duration Unit.
		@return Unit of Duration
	  */
	public String getDurationUnit () 
	{
		return (String)get_Value(COLUMNNAME_DurationUnit);
	}

	/** EntityType AD_Reference_ID=389 */
	public static final int ENTITYTYPE_AD_Reference_ID=389;
	/** Set Entity Type.
		@param EntityType 
		Dictionary Entity Type; Determines ownership and synchronization
	  */
	public void setEntityType (String EntityType)
	{

		set_Value (COLUMNNAME_EntityType, EntityType);
	}

	/** Get Entity Type.
		@return Dictionary Entity Type; Determines ownership and synchronization
	  */
	public String getEntityType () 
	{
		return (String)get_Value(COLUMNNAME_EntityType);
	}

	/** Set Comment/Help.
		@param Help 
		Comment or Hint
	  */
	public void setHelp (String Help)
	{
		set_Value (COLUMNNAME_Help, Help);
	}

	/** Get Comment/Help.
		@return Comment or Hint
	  */
	public String getHelp () 
	{
		return (String)get_Value(COLUMNNAME_Help);
	}

	/** Set Default.
		@param IsDefault 
		Default value
	  */
	public void setIsDefault (boolean IsDefault)
	{
		set_Value (COLUMNNAME_IsDefault, Boolean.valueOf(IsDefault));
	}

	/** Get Default.
		@return Default value
	  */
	public boolean isDefault () 
	{
		Object oo = get_Value(COLUMNNAME_IsDefault);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Duration Limit.
		@param Limit 
		Maximum Duration in Duration Unit
	  */
	public void setLimit (int Limit)
	{
		set_Value (COLUMNNAME_Limit, Integer.valueOf(Limit));
	}

	/** Get Duration Limit.
		@return Maximum Duration in Duration Unit
	  */
	public int getLimit () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Limit);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Moving Time.
		@param MovingTime Moving Time	  */
	public void setMovingTime (int MovingTime)
	{
		set_Value (COLUMNNAME_MovingTime, Integer.valueOf(MovingTime));
	}

	/** Get Moving Time.
		@return Moving Time	  */
	public int getMovingTime () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_MovingTime);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

	/** Set Overlap Units.
		@param OverlapUnits 
		Overlap Units are number of units that must be completed before they are moved the next activity
	  */
	public void setOverlapUnits (BigDecimal OverlapUnits)
	{
		set_Value (COLUMNNAME_OverlapUnits, OverlapUnits);
	}

	/** Get Overlap Units.
		@return Overlap Units are number of units that must be completed before they are moved the next activity
	  */
	public BigDecimal getOverlapUnits () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_OverlapUnits);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.eevolution.model.I_PP_Order getPP_Order() throws RuntimeException
    {
		return (org.eevolution.model.I_PP_Order)MTable.get(getCtx(), org.eevolution.model.I_PP_Order.Table_Name)
			.getPO(getPP_Order_ID(), get_TrxName());	}

	/** Set Manufacturing Order.
		@param PP_Order_ID 
		Manufacturing Order
	  */
	public void setPP_Order_ID (int PP_Order_ID)
	{
		if (PP_Order_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_PP_Order_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_PP_Order_ID, Integer.valueOf(PP_Order_ID));
	}

	/** Get Manufacturing Order.
		@return Manufacturing Order
	  */
	public int getPP_Order_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PP_Order_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_PP_Order_Node getPP_Order_Node() throws RuntimeException
    {
		return (org.eevolution.model.I_PP_Order_Node)MTable.get(getCtx(), org.eevolution.model.I_PP_Order_Node.Table_Name)
			.getPO(getPP_Order_Node_ID(), get_TrxName());	}

	/** Set Manufacturing Order Activity.
		@param PP_Order_Node_ID 
		Workflow Node (activity), step or process
	  */
	public void setPP_Order_Node_ID (int PP_Order_Node_ID)
	{
		if (PP_Order_Node_ID < 1) 
			set_Value (COLUMNNAME_PP_Order_Node_ID, null);
		else 
			set_Value (COLUMNNAME_PP_Order_Node_ID, Integer.valueOf(PP_Order_Node_ID));
	}

	/** Get Manufacturing Order Activity.
		@return Workflow Node (activity), step or process
	  */
	public int getPP_Order_Node_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PP_Order_Node_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Manufacturing Order Workflow.
		@param PP_Order_Workflow_ID Manufacturing Order Workflow	  */
	public void setPP_Order_Workflow_ID (int PP_Order_Workflow_ID)
	{
		if (PP_Order_Workflow_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_PP_Order_Workflow_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_PP_Order_Workflow_ID, Integer.valueOf(PP_Order_Workflow_ID));
	}

	/** Get Manufacturing Order Workflow.
		@return Manufacturing Order Workflow	  */
	public int getPP_Order_Workflow_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PP_Order_Workflow_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Priority.
		@param Priority 
		Indicates if this request is of a high, medium or low priority.
	  */
	public void setPriority (int Priority)
	{
		set_Value (COLUMNNAME_Priority, Integer.valueOf(Priority));
	}

	/** Get Priority.
		@return Indicates if this request is of a high, medium or low priority.
	  */
	public int getPriority () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Priority);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** ProcessType AD_Reference_ID=53224 */
	public static final int PROCESSTYPE_AD_Reference_ID=53224;
	/** Batch Flow  = BF */
	public static final String PROCESSTYPE_BatchFlow = "BF";
	/** Continuous Flow = CF */
	public static final String PROCESSTYPE_ContinuousFlow = "CF";
	/** Dedicate Repetititive Flow = DR */
	public static final String PROCESSTYPE_DedicateRepetititiveFlow = "DR";
	/** Job Shop = JS */
	public static final String PROCESSTYPE_JobShop = "JS";
	/** Mixed Repetitive Flow = MR */
	public static final String PROCESSTYPE_MixedRepetitiveFlow = "MR";
	/** Plant = PL */
	public static final String PROCESSTYPE_Plant = "PL";
	/** Set Process Type.
		@param ProcessType Process Type	  */
	public void setProcessType (String ProcessType)
	{

		set_Value (COLUMNNAME_ProcessType, ProcessType);
	}

	/** Get Process Type.
		@return Process Type	  */
	public String getProcessType () 
	{
		return (String)get_Value(COLUMNNAME_ProcessType);
	}

	/** PublishStatus AD_Reference_ID=310 */
	public static final int PUBLISHSTATUS_AD_Reference_ID=310;
	/** Released = R */
	public static final String PUBLISHSTATUS_Released = "R";
	/** Test = T */
	public static final String PUBLISHSTATUS_Test = "T";
	/** Under Revision = U */
	public static final String PUBLISHSTATUS_UnderRevision = "U";
	/** Void = V */
	public static final String PUBLISHSTATUS_Void = "V";
	/** Set Publication Status.
		@param PublishStatus 
		Status of Publication
	  */
	public void setPublishStatus (String PublishStatus)
	{

		set_Value (COLUMNNAME_PublishStatus, PublishStatus);
	}

	/** Get Publication Status.
		@return Status of Publication
	  */
	public String getPublishStatus () 
	{
		return (String)get_Value(COLUMNNAME_PublishStatus);
	}

	/** Set Qty Batch Size.
		@param QtyBatchSize Qty Batch Size	  */
	public void setQtyBatchSize (BigDecimal QtyBatchSize)
	{
		set_Value (COLUMNNAME_QtyBatchSize, QtyBatchSize);
	}

	/** Get Qty Batch Size.
		@return Qty Batch Size	  */
	public BigDecimal getQtyBatchSize () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyBatchSize);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Queuing Time.
		@param QueuingTime 
		Queue time is the time a job waits at a work center before begin handled.
	  */
	public void setQueuingTime (int QueuingTime)
	{
		set_Value (COLUMNNAME_QueuingTime, Integer.valueOf(QueuingTime));
	}

	/** Get Queuing Time.
		@return Queue time is the time a job waits at a work center before begin handled.
	  */
	public int getQueuingTime () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_QueuingTime);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_S_Resource getS_Resource() throws RuntimeException
    {
		return (org.compiere.model.I_S_Resource)MTable.get(getCtx(), org.compiere.model.I_S_Resource.Table_Name)
			.getPO(getS_Resource_ID(), get_TrxName());	}

	/** Set Resource.
		@param S_Resource_ID 
		Resource
	  */
	public void setS_Resource_ID (int S_Resource_ID)
	{
		if (S_Resource_ID < 1) 
			set_Value (COLUMNNAME_S_Resource_ID, null);
		else 
			set_Value (COLUMNNAME_S_Resource_ID, Integer.valueOf(S_Resource_ID));
	}

	/** Get Resource.
		@return Resource
	  */
	public int getS_Resource_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_S_Resource_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Setup Time.
		@param SetupTime 
		Setup time before starting Production
	  */
	public void setSetupTime (int SetupTime)
	{
		set_Value (COLUMNNAME_SetupTime, Integer.valueOf(SetupTime));
	}

	/** Get Setup Time.
		@return Setup time before starting Production
	  */
	public int getSetupTime () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SetupTime);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Immutable Universally Unique Identifier.
		@param UUID 
		Immutable Universally Unique Identifier
	  */
	public void setUUID (String UUID)
	{
		set_Value (COLUMNNAME_UUID, UUID);
	}

	/** Get Immutable Universally Unique Identifier.
		@return Immutable Universally Unique Identifier
	  */
	public String getUUID () 
	{
		return (String)get_Value(COLUMNNAME_UUID);
	}

	/** Set Units by Cycles.
		@param UnitsCycles 
		The Units by Cycles are defined for process type  Flow Repetitive Dedicated and  indicated the product to be manufactured on a production line for duration unit.
	  */
	public void setUnitsCycles (BigDecimal UnitsCycles)
	{
		set_Value (COLUMNNAME_UnitsCycles, UnitsCycles);
	}

	/** Get Units by Cycles.
		@return The Units by Cycles are defined for process type  Flow Repetitive Dedicated and  indicated the product to be manufactured on a production line for duration unit.
	  */
	public BigDecimal getUnitsCycles () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_UnitsCycles);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Valid from.
		@param ValidFrom 
		Valid from including this date (first day)
	  */
	public void setValidFrom (Timestamp ValidFrom)
	{
		set_Value (COLUMNNAME_ValidFrom, ValidFrom);
	}

	/** Get Valid from.
		@return Valid from including this date (first day)
	  */
	public Timestamp getValidFrom () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ValidFrom);
	}

	/** Set Valid to.
		@param ValidTo 
		Valid to including this date (last day)
	  */
	public void setValidTo (Timestamp ValidTo)
	{
		set_Value (COLUMNNAME_ValidTo, ValidTo);
	}

	/** Get Valid to.
		@return Valid to including this date (last day)
	  */
	public Timestamp getValidTo () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ValidTo);
	}

	/** Set Validate Workflow.
		@param ValidateWorkflow Validate Workflow	  */
	public void setValidateWorkflow (String ValidateWorkflow)
	{
		set_Value (COLUMNNAME_ValidateWorkflow, ValidateWorkflow);
	}

	/** Get Validate Workflow.
		@return Validate Workflow	  */
	public String getValidateWorkflow () 
	{
		return (String)get_Value(COLUMNNAME_ValidateWorkflow);
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}

	/** Set Version.
		@param Version 
		Version of the table definition
	  */
	public void setVersion (int Version)
	{
		set_Value (COLUMNNAME_Version, Integer.valueOf(Version));
	}

	/** Get Version.
		@return Version of the table definition
	  */
	public int getVersion () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Version);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Waiting Time.
		@param WaitingTime 
		Workflow Simulation Waiting time
	  */
	public void setWaitingTime (int WaitingTime)
	{
		set_Value (COLUMNNAME_WaitingTime, Integer.valueOf(WaitingTime));
	}

	/** Get Waiting Time.
		@return Workflow Simulation Waiting time
	  */
	public int getWaitingTime () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_WaitingTime);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** WorkflowType AD_Reference_ID=108 */
	public static final int WORKFLOWTYPE_AD_Reference_ID=108;
	/** Single Record = S */
	public static final String WORKFLOWTYPE_SingleRecord = "S";
	/** Maintain = M */
	public static final String WORKFLOWTYPE_Maintain = "M";
	/** Transaction = T */
	public static final String WORKFLOWTYPE_Transaction = "T";
	/** Query Only = Q */
	public static final String WORKFLOWTYPE_QueryOnly = "Q";
	/** Set Workflow Type.
		@param WorkflowType 
		Type of Workflow
	  */
	public void setWorkflowType (String WorkflowType)
	{

		set_Value (COLUMNNAME_WorkflowType, WorkflowType);
	}

	/** Get Workflow Type.
		@return Type of Workflow
	  */
	public String getWorkflowType () 
	{
		return (String)get_Value(COLUMNNAME_WorkflowType);
	}

	/** Set Working Time.
		@param WorkingTime 
		Workflow Simulation Execution Time
	  */
	public void setWorkingTime (int WorkingTime)
	{
		set_Value (COLUMNNAME_WorkingTime, Integer.valueOf(WorkingTime));
	}

	/** Get Working Time.
		@return Workflow Simulation Execution Time
	  */
	public int getWorkingTime () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_WorkingTime);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Yield %.
		@param Yield 
		The Yield is the percentage of a lot that is expected to be of acceptable wuality may fall below 100 percent
	  */
	public void setYield (int Yield)
	{
		set_Value (COLUMNNAME_Yield, Integer.valueOf(Yield));
	}

	/** Get Yield %.
		@return The Yield is the percentage of a lot that is expected to be of acceptable wuality may fall below 100 percent
	  */
	public int getYield () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Yield);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}