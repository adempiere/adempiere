/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 Adempiere, Inc. All Rights Reserved.               *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * Copyright (C) 2003-2016 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/
 
package org.eevolution.model;

import java.io.File;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_C_DocType;
import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MDocType;
import org.compiere.model.MLocator;
import org.compiere.model.MMovement;
import org.compiere.model.MPeriod;
import org.compiere.model.MProject;
import org.compiere.model.MRefList;
import org.compiere.model.MStorage;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.print.ReportEngine;
import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.process.ProcessInfo;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Util;
import org.eevolution.process.GenerateMovement;
import org.eevolution.process.GenerateMovementMaterial;
import org.eevolution.service.dsl.ProcessBuilder;

/**
 *  Order Distribution Model.
 * 	Please do not set DocStatus and C_DocType_ID directly. 
 * 	They are set in the process() method. 
 * 	Use DocAction and C_DocTypeTarget_ID instead.
 *
 *  @author victor.perez@e-evolution.com, e-Evolution http://www.e-evolution.com
 *			<li> Original contributor of Distribution Functionality
 * 			<li> FR [ 2520591 ] Support multiples calendar for Org 
 *			@see http://sourceforge.net/tracker2/?func=detail&atid=879335&aid=2520591&group_id=176962 
 */
public class MDDOrder extends X_DD_Order implements DocAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5997157712614274458L;

	/**
	 * 	Create new Order by copying
	 * 	@param from order
	 * 	@param dateDoc date of the document date
	 * 	@param C_DocTypeTarget_ID target document type
	 * 	@param isSOTrx sales order 
	 * 	@param counter create counter links
	 *	@param copyASI copy line attributes Attribute Set Instance, Resaouce Assignment
	 * 	@param trxName trx
	 *	@return Distribution Order
	 */
	public static MDDOrder copyFrom (MDDOrder from, Timestamp dateDoc, 
		int C_DocTypeTarget_ID, boolean isSOTrx, boolean counter, boolean copyASI, 
		String trxName)
	{
		MDDOrder to = new MDDOrder (from.getCtx(), 0, trxName);
		to.set_TrxName(trxName);
		PO.copyValues(from, to, from.getAD_Client_ID(), from.getAD_Org_ID());
		to.set_ValueNoCheck (COLUMNNAME_DD_Order_ID, I_ZERO);
		to.set_ValueNoCheck (COLUMNNAME_DocumentNo, null);
		//
		to.setDocStatus (DOCSTATUS_Drafted);		//	Draft
		to.setDocAction(DOCACTION_Complete);
		//
		to.setC_DocType_ID(0);
		to.setIsSOTrx(isSOTrx);
		//
		to.setIsSelected (false);
		to.setDateOrdered (dateDoc);
		to.setDatePromised (dateDoc);	//	assumption
		to.setDatePrinted(null);
		to.setIsPrinted (false);
		//
		to.setIsApproved (false);
		//
		to.setIsDelivered(false);
		to.setPosted (false);
		to.setProcessed (false);
		if (counter)
			to.setRef_Order_ID(from.getDD_Order_ID());
		else
			to.setRef_Order_ID(0);
		//
		if (!to.save(trxName))
			throw new IllegalStateException("Could not create Order");
		if (counter)
			from.setRef_Order_ID(to.getDD_Order_ID());

		if (to.copyLinesFrom(from, counter, copyASI) == 0)
			throw new IllegalStateException("Could not create Order Lines");
		
		return to;
	}	//	copyFrom
	
	
	/**************************************************************************
	 *  Default Constructor
	 *  @param ctx context
	 *  @param  DD_Order_ID    order to load, (0 create new order)
	 *  @param trxName trx name
	 */
	public MDDOrder(Properties ctx, int DD_Order_ID, String trxName)
	{
		super (ctx, DD_Order_ID, trxName);
		//  New
		if (DD_Order_ID == 0)
		{
			setDocStatus(DOCSTATUS_Drafted);
			setDocAction (DOCACTION_Prepare);
			//
			setDeliveryRule (DELIVERYRULE_Availability);
			setFreightCostRule (FREIGHTCOSTRULE_FreightIncluded);
			setPriorityRule (PRIORITYRULE_Medium);
			setDeliveryViaRule (DELIVERYVIARULE_Pickup);
			//
			setIsSelected (false);
			setIsSOTrx (true);
			setIsDropShip(false);
			setSendEMail (false);
			//
			setIsApproved(false);
			setIsPrinted(false);
			setIsDelivered(false);
			//
			super.setProcessed(false);
			setProcessing(false);
			setPosted(false);
			
			setDatePromised (new Timestamp(System.currentTimeMillis()));
			setDateOrdered (new Timestamp(System.currentTimeMillis()));

			setFreightAmt (Env.ZERO);
			setChargeAmt (Env.ZERO);

		}
	}	//	MDDOrder

	/**************************************************************************
	 *  Project Constructor
	 *  @param  project Project to create Order from
	 *  @param IsSOTrx sales order
	 * 	@param	DocSubTypeSO if SO DocType Target (default DocSubTypeSO_OnCredit)
	 */
	public MDDOrder (MProject project, boolean IsSOTrx, String DocSubTypeSO)
	{
		this (project.getCtx(), 0, project.get_TrxName());
		setAD_Client_ID(project.getAD_Client_ID());
		setAD_Org_ID(project.getAD_Org_ID());
		setC_Campaign_ID(project.getC_Campaign_ID());
		setSalesRep_ID(project.getSalesRep_ID());
		//
		setC_Project_ID(project.getC_Project_ID());
		setDescription(project.getName());
		Timestamp ts = project.getDateContract();
		if (ts != null)
			setDateOrdered (ts);
		ts = project.getDateFinish();
		if (ts != null)
			setDatePromised (ts);
		//
		setC_BPartner_ID(project.getC_BPartner_ID());
		setC_BPartner_Location_ID(project.getC_BPartner_Location_ID());
		setAD_User_ID(project.getAD_User_ID());
		//
		setM_Warehouse_ID(project.getM_Warehouse_ID());
		//
		setIsSOTrx(IsSOTrx);
	}	//	MDDOrder

	/**
	 *  Load Constructor
	 *  @param ctx context
	 *  @param rs result set record
	 *  @param trxName transaction
	 */
	public MDDOrder (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MDDOrder

	/**	Order Lines					*/
	private List<MDDOrderLine> orderLines = null;
	
	/** Force Creation of order		*/
	private boolean			m_forceCreation = false;

	/**
	 * 	Add to Description
	 *	@param description text
	 */
	public void addDescription (String description)
	{
		String desc = getDescription();
		if (desc == null)
			setDescription(description);
		else
			setDescription(desc + " | " + description);
	}	//	addDescription

	/**
	 * 	Set Ship Business Partner
	 *	@param C_BPartner_ID bpartner
	 */
	public void setShip_BPartner_ID (int C_BPartner_ID)
	{
		super.setC_BPartner_ID (C_BPartner_ID);
	}	//	setShip_BPartner_ID
	
	/**
	 * 	Set Ship Business Partner Location
	 *	@param C_BPartner_Location_ID bp location
	 */
	public void setShip_Location_ID (int C_BPartner_Location_ID)
	{
		super.setC_BPartner_Location_ID (C_BPartner_Location_ID);
	}	//	setShip_Location_ID

	/**
	 * 	Set Ship Business Partner Contact
	 *	@param AD_User_ID user
	 */
	public void setShip_User_ID (int AD_User_ID)
	{
		super.setAD_User_ID (AD_User_ID);
	}	//	setShip_User_ID

	/**
	 * 	Set Business Partner Defaults & Details.
	 * 	SOTrx should be set.
	 * 	@param bp business partner
	 */
	public void setBPartner (MBPartner bp)
	{
		if (bp == null)
			return;

		setC_BPartner_ID(bp.getC_BPartner_ID());
		//	Defaults Payment Term
		int ii = 0;
		if (isSOTrx())
			ii = bp.getC_PaymentTerm_ID();
		else
			ii = bp.getPO_PaymentTerm_ID();
		
		//	Default Price List
		if (isSOTrx())
			ii = bp.getM_PriceList_ID();
		else
			ii = bp.getPO_PriceList_ID();
		//	Default Delivery/Via Rule
		String ss = bp.getDeliveryRule();
		if (ss != null)
			setDeliveryRule(ss);
		ss = bp.getDeliveryViaRule();
		if (ss != null)
			setDeliveryViaRule(ss);
		//	Default Invoice/Payment Rule
		ss = bp.getInvoiceRule();

		if (getSalesRep_ID() == 0)
		{
			ii = Env.getAD_User_ID(getCtx());
			if (ii != 0)
				setSalesRep_ID (ii);
		}

		//	Set Locations
		/*
		MBPartnerLocation[] locs = bp.getLocations(false);
		if (locs != null)
		{
			for (int i = 0; i < locs.length; i++)
			{
				if (locs[i].isShipTo())
				{
					super.setC_BPartner_Location_ID(locs[i].getC_BPartner_Location_ID());
				}
			}
			//	set to first
			if (getC_BPartner_Location_ID() == 0 && locs.length > 0)
			{
				super.setC_BPartner_Location_ID(locs[0].getC_BPartner_Location_ID());
			}
		}
		*/

		List<MBPartnerLocation> partnerLocations = Arrays.asList(bp.getLocations(false));
		// search the Ship To Location
		MBPartnerLocation partnerLocation = partnerLocations.stream() 			// create steam
				.filter( pl -> pl.isShipTo()).reduce((first , last ) -> last) 	// get of last Ship to location
				.orElse(partnerLocations.stream() 								// if not exist Ship to location else get first partner location
							.findFirst()										// if not exist partner location then throw an exception
							.orElseThrow(() -> new AdempiereException("@IsShipTo@ @NotFound@"))
				);

		setC_BPartner_Location_ID(partnerLocation.getC_BPartner_Location_ID());

		if (getC_BPartner_Location_ID() == 0)
		{
			log.log(Level.SEVERE, "MDDOrder.setBPartner - Has no Ship To Address: " + bp);
		}

		//	Set Contact
		/*MUser[] contacts = bp.getContacts(false);
		if (contacts != null && contacts.length == 1)
		{
			setAD_User_ID(contacts[0].getAD_User_ID());
		}*/
		Arrays.asList(bp.getContacts(false))
				.stream()
				.findFirst()
				.ifPresent(user -> setAD_User_ID(user.getAD_User_ID()));
	}	//	setBPartner


	/**
	 * 	Copy Lines From other Order
	 *	@param otherOrder order
	 *	@param counter set counter info
	 *	@param copyASI copy line attributes Attribute Set Instance, Resource Assignment
	 *	@return number of lines copied
	 */
	public int copyLinesFrom (MDDOrder otherOrder, boolean counter, boolean copyASI)
	{
		if (isProcessed() || isPosted() || otherOrder == null)
			return 0;
		List<MDDOrderLine> otherOrderLines = otherOrder.getLines(false, null);
		otherOrderLines.stream().forEach( otherOrderLine -> {
			MDDOrderLine orderLine = new MDDOrderLine (this);
			PO.copyValues(otherOrderLine, orderLine, getAD_Client_ID(), getAD_Org_ID());
			orderLine.setDD_Order_ID(getDD_Order_ID());
			orderLine.setOrder(this);
			//	References
			if (!copyASI)
				orderLine.setM_AttributeSetInstance_ID(0);

			orderLine.setQtyDelivered(Env.ZERO);
			orderLine.setQtyReserved(Env.ZERO);
			orderLine.setDateDelivered(null);
			orderLine.setProcessed(false);
			orderLine.saveEx(get_TrxName());
			orderLines.add(orderLine);
		});

		if (otherOrderLines.size() != orderLines.size())
			log.log(Level.SEVERE, "Line difference - From=" + otherOrderLines.size() + " <> Saved=" + orderLines.size());
		return orderLines.size();
	}	//	copyLinesFrom

	
	/**************************************************************************
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MDDOrder[")
			.append(get_ID()).append("-").append(getDocumentNo())
			.append(",IsSOTrx=").append(isSOTrx())
			.append(",C_DocType_ID=").append(getC_DocType_ID())
			.append ("]");
		return sb.toString ();
	}	//	toString

	/**
	 * 	Get Document Info
	 *	@return document info (untranslated)
	 */
	public String getDocumentInfo()
	{
		I_C_DocType docType = getC_DocType();
		return docType.getName() + " " + getDocumentNo();
	}	//	getDocumentInfo

	/**
	 * 	Create PDF
	 *	@return File or null
	 */
	public File createPDF ()
	{
		try
		{
			File temp = File.createTempFile(get_TableName()+get_ID()+"_", ".pdf");
			return createPDF (temp);
		}
		catch (Exception e)
		{
			log.severe("Could not create PDF - " + e.getMessage());
		}
		return null;

	}	//	getPDF

	/**
	 * 	Create PDF file
	 *	@param file output file
	 *	@return file if success
	 */
	public File createPDF (File file)
	{
		return Optional.ofNullable(ReportEngine.get(getCtx(), ReportEngine.DISTRIBUTION_ORDER, getDD_Order_ID()))
				.orElseThrow(() -> new AdempiereException("@AD_PrintFormat_ID@ @NotFound@"))
				.getPDF();
	}	//	createPDF
	


	
	/**************************************************************************
	 * 	Get Lines of Order
	 * 	@param whereClause where clause or null (starting with AND)
	 * 	@param orderClause order clause
	 * 	@return lines
	 */
	public List<MDDOrderLine> getLines (String whereClause, String orderClause)
	{
		StringBuffer whereClauseFinal = new StringBuffer(MDDOrderLine.COLUMNNAME_DD_Order_ID).append("=?");
		if (!Util.isEmpty(whereClause, true))
			whereClauseFinal.append(" AND (").append(whereClause).append(")");

		return new Query(getCtx(), I_DD_OrderLine.Table_Name, whereClauseFinal.toString(), get_TrxName())
												.setParameters(getDD_Order_ID())
												.setOrderBy(orderClause)
												.list();
	}	//	getLines

	/**
	 * 	Get Lines of Order
	 * 	@param reQuery requery
	 * 	@param orderBy optional order by column
	 * 	@return lines
	 */
	public List<MDDOrderLine> getLines (boolean reQuery, String orderBy)
	{
		if (orderLines != null && !reQuery) {
			orderLines.stream().forEach(orderLine -> orderLine.set_TrxName(get_TrxName()));
			return orderLines;
		}
		//
		String orderClause = "";
		if (orderBy != null && orderBy.length() > 0)
			orderClause += orderBy;
		else
			orderClause += "Line";
		orderLines = getLines(null, orderClause);
		return orderLines;
	}	//	getLines

	/**
	 * 	Get Lines of Order.
	 * 	(useb by web store)
	 * 	@return lines
	 */
	public List<MDDOrderLine> getLines()
	{
		return getLines(false, null);
	}	//	getLines
	
	/**
	 * 	Renumber Lines
	 *	@param step start and step
	 */
	public void renumberLines (int step)
	{
		AtomicInteger number = new AtomicInteger(0);
		orderLines =  getLines(true, null);
		orderLines.stream().forEach( orderLine -> {
			orderLine.setLine(  number.updateAndGet(lineNo -> lineNo + 10));
			orderLine.save(get_TrxName());
		});

		orderLines = null;
	}	//	renumberLines
	
	


	/**
	 * 	Get Movement of Order
	 * 	@return shipments
	 */
	public List<MMovement> getMovement()
	{
		StringBuilder whereClause = new StringBuilder();
		whereClause.append(MMovement.COLUMNNAME_DD_Order_ID).append("=?");
		return new Query(getCtx(), MMovement.Table_Name , whereClause.toString() , get_TrxName())
				.setClient_ID()
				.setOrderBy(MDDOrder.COLUMNNAME_Created)
				.setParameters(getDD_Order_ID())
				.list();
	}	//	get Movements

	
	
	/**
	 * 	Get Document Status
	 *	@return Document Status Clear Text
	 */
	public String getDocStatusName()
	{
		return MRefList.getListName(getCtx(), 131, getDocStatus());
	}	//	getDocStatusName

	/**
	 * 	Set DocAction
	 *	@param DocAction doc action
	 */
	public void setDocAction (String DocAction)
	{
		setDocAction (DocAction, false);
	}	//	setDocAction

	/**
	 * 	Set Processed.
	 * 	Propergate to Lines/Taxes
	 *	@param processed processed
	 */
	public void setProcessed (boolean processed)
	{
		super.setProcessed (processed);
		if (get_ID() == 0)
			return;
		getLines().stream().forEach(orderLine -> {
			orderLine.setProcessed(processed);
			orderLine.saveEx();
		});
		orderLines = null;
		log.fine("setProcessed - " + processed + " - Lines=" + getLines().size());
	}	//	setProcessed
	
	
	
	/**************************************************************************
	 * 	Before Save
	 *	@param newRecord new
	 *	@return save
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		//	Client/Org Check
		if (getAD_Org_ID() == 0)
		{
			int contextOrgId = Env.getAD_Org_ID(getCtx());
			if (contextOrgId != 0)
			{
				setAD_Org_ID(contextOrgId);
				log.warning("Changed Org to Context=" + contextOrgId);
			}
		}
		if (getAD_Client_ID() == 0)
		{
			processMessage = "AD_Client_ID = 0";
			return false;
		}
		
		//	New Record Doc Type - make sure DocType set to 0
		if (newRecord && getC_DocType_ID() == 0)
			setC_DocType_ID (0);

		//	Default Warehouse
		if (getM_Warehouse_ID() == 0)
		{
			int warehouseId = Env.getContextAsInt(getCtx(), "#M_Warehouse_ID");
			if (warehouseId != 0)
				setM_Warehouse_ID(warehouseId);
			else
			{
				log.saveError("FillMandatory", Msg.getElement(getCtx(), "M_Warehouse_ID"));
				return false;
			}
		}
		//	Reservations in Warehouse
		if (!newRecord && is_ValueChanged("M_Warehouse_ID"))
		{
			if (!getLines().stream().anyMatch(orderLine -> orderLine.canChangeWarehouse()))
				return false;
		}
		
		//	No Partner Info - set Template
		if (getC_BPartner_ID() == 0)
			setBPartner(MBPartner.getTemplate(getCtx(), getAD_Client_ID()));
		if (getC_BPartner_Location_ID() == 0)
			setBPartner(new MBPartner(getCtx(), getC_BPartner_ID(), null));


		//	Default Sales Rep
		if (getSalesRep_ID() == 0)
		{
			int userId = Env.getContextAsInt(getCtx(), "#AD_User_ID");
			if (userId != 0)
				setSalesRep_ID (userId);
		}
		
		return true;
	}	//	beforeSave
	
	
	/**
	 * 	After Save
	 *	@param newRecord new
	 *	@param success success
	 *	@return true if can be saved
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		if (!success || newRecord)
			return success;
		
		//	Propagate Description changes
		if (is_ValueChanged(COLUMNNAME_Description) || is_ValueChanged(COLUMNNAME_POReference))
		{
			getMovement()
					.stream()
					.filter(movement -> !movement.getDocStatus().endsWith(DOCSTATUS_Reversed))
					.filter(movement -> !movement.getDocStatus().endsWith(DOCACTION_Close))
					.forEach(movement -> {
						movement.setDescription(getDescription());
						movement.setPOReference(getPOReference());
						movement.saveEx();
					});
		}		
	      
		//	Sync Lines
		afterSaveSync("AD_Org_ID");
		afterSaveSync("C_BPartner_ID");
		afterSaveSync("C_BPartner_Location_ID");
		afterSaveSync("DateOrdered");
		afterSaveSync("DatePromised");
		afterSaveSync("M_Shipper_ID");
		//
		return true;
	}	//	afterSave

	private void afterSaveSync (String columnName)
	{
		if (is_ValueChanged(columnName))
		{
		    	final String whereClause = I_DD_Order.COLUMNNAME_DD_Order_ID + "=?";
		    	List<MDDOrderLine> orderLines = new Query (getCtx(), I_DD_OrderLine.Table_Name, whereClause, get_TrxName())
		    	.setParameters(getDD_Order_ID())
		    	.list();

			orderLines.stream().forEach(orderLine -> {
				orderLine.set_ValueOfColumn(columnName, get_Value(columnName));
				orderLine.saveEx();
				log.fine(columnName + " Lines -> #" + get_Value(columnName));
			});
		}		
	}	//	afterSaveSync
	
	/**
	 * 	Set DocAction
	 *	@param DocAction doc oction
	 *	@param forceCreation force creation
	 */
	public void setDocAction (String DocAction, boolean forceCreation)
	{
		super.setDocAction (DocAction);
		m_forceCreation = forceCreation;
	}	//	setDocAction


	/**
	 * 	Before Delete
	 *	@return true of it can be deleted
	 */
	protected boolean beforeDelete ()
	{
		if (isProcessed())
			return false;

		getLines()
				.stream()
				.forEach(orderLine -> orderLine.deleteEx(true));
		return true;
	}	//	beforeDelete
 
	/**************************************************************************
	 * 	Process document
	 *	@param processAction document action
	 *	@return true if performed
	 */
	public boolean processIt (String processAction)
	{
		processMessage = null;
		DocumentEngine engine = new DocumentEngine (this, getDocStatus());
		return engine.processIt (processAction, getDocAction());
	}	//	processIt
	
	/**	Process Message 			*/
	private String processMessage = null;
	/**	Just Prepared Flag			*/
	private boolean justPrepared = false;

	/**
	 * 	Unlock Document.
	 * 	@return true if success 
	 */
	public boolean unlockIt()
	{
		log.info("unlockIt - " + toString());
		setProcessing(false);
		return true;
	}	//	unlockIt
	
	/**
	 * 	Invalidate Document
	 * 	@return true if success 
	 */
	public boolean invalidateIt()
	{
		log.info(toString());
		setDocAction(DOCACTION_Prepare);
		return true;
	}	//	invalidateIt
	
	
	/**************************************************************************
	 *	Prepare Document
	 * 	@return new status (In Progress or Invalid) 
	 */
	public String prepareIt()
	{
		log.info(toString());
		processMessage = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_PREPARE);
		if (processMessage != null)
			return DocAction.STATUS_Invalid;

		MDocType dt = MDocType.get(getCtx(), getC_DocType_ID());
		//	Std Period open?
		if (!MPeriod.isOpen(getCtx(), getDateOrdered(), dt.getDocBaseType(), getAD_Org_ID()))
		{
			processMessage = "@PeriodClosed@";
			return DocAction.STATUS_Invalid;
		}

		List<MDDOrderLine> orderLines= getLines(true, "M_Product_ID");
		if (orderLines.isEmpty())
		{
			processMessage = "@NoLines@";
			return DocAction.STATUS_Invalid;
		}


		
		// Bug 1564431
		if (getDeliveryRule() != null && getDeliveryRule().equals(MDDOrder.DELIVERYRULE_CompleteOrder)) 
		{
			/*for (int i = 0; i < lines.length; i++)
			{
				MDDOrderLine line = lines[i];
				MProduct product = line.getProduct();
				if (product != null && product.isExcludeAutoDelivery())
				{
					processMessage = "@M_Product_ID@ "+product.getValue()+" @IsExcludeAutoDelivery@";
					return DocAction.STATUS_Invalid;
				}
			}*/

			orderLines.stream()
					.filter(orderLine -> orderLine.getProduct() != null && orderLine.getProduct().isExcludeAutoDelivery())
					.map(orderLine ->
							{
								processMessage = "@M_Product_ID@ " + orderLine.getProduct().getValue() + " @IsExcludeAutoDelivery@";
								return DocAction.STATUS_Invalid;
							}
					);
		}
		

		//	Mandatory Product Attribute Set Instance
		String mandatoryType = "='Y'";	//	IN ('Y','S')
		String sql = "SELECT COUNT(*) "
			+ "FROM DD_OrderLine ol"
			+ " INNER JOIN M_Product p ON (ol.M_Product_ID=p.M_Product_ID)" 
			+ " INNER JOIN M_AttributeSet pas ON (p.M_AttributeSet_ID=pas.M_AttributeSet_ID) "
			+ "WHERE pas.MandatoryType" + mandatoryType		
			+ " AND ol.M_AttributeSetInstance_ID IS NULL"
			+ " AND ol.DD_Order_ID=?";
		int no = DB.getSQLValue(get_TrxName(), sql, getDD_Order_ID());
		if (no != 0)
		{
			processMessage = "@LinesWithoutProductAttribute@ (" + no + ")";
			return DocAction.STATUS_Invalid;
		}
		
		reserveStock(orderLines);
		
		processMessage = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_PREPARE);
		if (processMessage != null)
			return DocAction.STATUS_Invalid;
		
		justPrepared = true;
		return DocAction.STATUS_InProgress;
	}	//	prepareIt



	/**
	 * 	Reserve Inventory.
	 * 	Counterpart: MMovement.completeIt()
	 * 	@param orderLines distribution order lines (ordered by M_Product_ID for deadlock prevention)
	 * 	@return true if (un) reserved
	 */
	public void reserveStock (List<MDDOrderLine> orderLines)
	{
		//	Always check and (un) Reserve Inventory		
		orderLines.stream()
				.filter(orderLine -> orderLine.getCalculateQtyReserved().signum() != 0 ) // filter the order line that where Reserved Quantity need be change
				.filter(orderLine -> orderLine.getProduct() != null && orderLine.getProduct().isStocked()) // filter that order line with product stocked
				.forEach(orderLine -> {

			MLocator locatorFrom = MLocator.get(getCtx(),orderLine.getM_Locator_ID());
			MLocator locatorTo = MLocator.get(getCtx(),orderLine.getM_LocatorTo_ID());
			log.fine("Line=" + orderLine.getLine()
				+ " - Ordered=" + orderLine.getQtyOrdered()
				+ ",Reserved=" + orderLine.getQtyReserved() + ",Delivered=" + orderLine.getQtyDelivered());
			//	Update Storage
			if (!MStorage.add(getCtx(), locatorTo.getM_Warehouse_ID(), locatorTo.getM_Locator_ID(),
					orderLine.getM_Product_ID(),
					orderLine.getM_AttributeSetInstance_ID(), orderLine.getM_AttributeSetInstance_ID(),
				Env.ZERO, Env.ZERO , orderLine.getCalculateQtyReserved() , get_TrxName()))
				throw new AdempiereException("@M_Storage_ID@ @Error@ @To@ @QtyReserved@");

			if (!MStorage.add(getCtx(), locatorFrom.getM_Warehouse_ID(), locatorFrom.getM_Locator_ID(),
					orderLine.getM_Product_ID(),
					orderLine.getM_AttributeSetInstanceTo_ID(), orderLine.getM_AttributeSetInstance_ID(),
				Env.ZERO, orderLine.getCalculateQtyReserved(), Env.ZERO , get_TrxName()))
				throw new AdempiereException("@M_Storage_ID@ @Error@ @To@ @QtyReserved@");

				//	update line
				orderLine.setQtyReserved(orderLine.getQtyReserved().add(orderLine.getCalculateQtyReserved()));
				orderLine.saveEx();
		});
		updateVolume();
		updateWeight();
	}	//	reserveStock

	public BigDecimal updateWeight()
	{
		BigDecimal weight = getLines()
				.stream()
				.filter(orderLine -> orderLine.getProduct() != null && orderLine.getProduct().isStocked())
				.map(MDDOrderLine::getWeight)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		setWeight(weight);
		saveEx();
		return  weight;
	}

	public BigDecimal updateVolume()
	{
		BigDecimal volume = getLines()
				.stream()
				.filter(orderLine -> orderLine.getProduct() != null && orderLine.getProduct().isStocked())
				.map(MDDOrderLine::getVolume)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		setVolume(volume);
		saveEx();
		return volume;
	}
	
	/**
	 * 	Approve Document
	 * 	@return true if success 
	 */
	public boolean  approveIt()
	{
		log.info("approveIt - " + toString());
		setIsApproved(true);
		return true;
	}	//	approveIt
	
	/**
	 * 	Reject Approval
	 * 	@return true if success 
	 */
	public boolean rejectIt()
	{
		log.info("rejectIt - " + toString());
		setIsApproved(false);
		return true;
	}	//	rejectIt
	
	
	/**************************************************************************
	 * 	Complete Document
	 * 	@return new status (Complete, In Progress, Invalid, Waiting ..)
	 */
	public String completeIt()
	{
		//	Just prepare
		if (DOCACTION_Prepare.equals(getDocAction()))
		{
			setProcessed(false);
			return DocAction.STATUS_InProgress;
		}
		
		//	Re-Check
		if (!justPrepared)
		{
			String status = prepareIt();
			if (!DocAction.STATUS_InProgress.equals(status))
				return status;
		}
		
		processMessage = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_COMPLETE);
		if (processMessage != null)
			return DocAction.STATUS_Invalid;
		
		//	Implicit Approval
		if (!isApproved())
			approveIt();

		getLines(true,null);
		renumberLines(10);

		if (isDropShip())
			createDropShip();

		log.info(toString());
		StringBuffer info = new StringBuffer();		
		String valid = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_COMPLETE);
		if (valid != null)
		{
			if (info.length() > 0)
				info.append(" - ");
			info.append(valid);
			processMessage = info.toString();
			return DocAction.STATUS_Invalid;
		}

		setProcessed(true);	
		processMessage = info.toString();
		//
		setDocAction(DOCACTION_Close);
		return DocAction.STATUS_Completed;
	}	//	completeIt

	private void createDropShip() {
		//Create movement delivery
		Date date = Date.from(LocalDate.now().atStartOfDay()
				.atZone(ZoneId.systemDefault()).toInstant());
		Timestamp today = new Timestamp(date.getTime());
		List<Integer> recordIds = new ArrayList<>();
		recordIds.add(getDD_Order_ID());
		ProcessInfo processInfo = ProcessBuilder.create(getCtx()).process(GenerateMovement.getProcessId())
				.withTitle(GenerateMovement.getProcessName())
				.withRecordId(MDDOrder.Table_ID, 0)
				.withSelectedRecordsIds(recordIds)
				.withParameter(MMovement.COLUMNNAME_MovementDate , today)
				.withParameter(MMovement.COLUMNNAME_DocAction, DocAction.ACTION_Complete)
				.withoutTransactionClose()
				.execute(get_TrxName());

		if (processInfo.isError())
			throw new AdempiereException(processInfo.getSummary());

		List<Integer> orderLinesIds = new ArrayList<>();
		LinkedHashMap<Integer, LinkedHashMap<String, Object>> selection = new LinkedHashMap<Integer, LinkedHashMap<String, Object>>();

		getLines().stream().filter(orderLine -> orderLine != null).forEach(orderLine -> {
			orderLinesIds.add(orderLine.get_ID());
			LinkedHashMap<String, Object> values = new LinkedHashMap<String, Object>();
			values.put("LINE_"+MDDOrderLine.COLUMNNAME_QtyInTransit , orderLine.getQtyInTransit());
			selection.put(orderLine.get_ID(), values);
		});

		processInfo = ProcessBuilder.create(getCtx()).process(GenerateMovementMaterial.getProcessId())
				.withTitle(GenerateMovementMaterial.getProcessName())
				.withRecordId(MDDOrderLine.Table_ID, 0)
				.withSelectedRecordsIds(orderLinesIds , selection)
				.withParameter(MMovement.COLUMNNAME_MovementDate , today)
				.withoutTransactionClose()
				.execute(get_TrxName());

		if (processInfo.isError())
			throw new AdempiereException(processInfo.getSummary());
	}

	/**
	 * 	Void Document.
	 * 	Set Qtys to 0 - Sales: reverse all documents
	 * 	@return true if success 
	 */
	public boolean voidIt()
	{
		log.info(toString());
		// Before Void
		processMessage = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_VOID);
		if (processMessage != null)
			return false;

		/*MDDOrderLine[] lines = getLines(true, "M_Product_ID");
		for (int i = 0; i < lines.length; i++)
		{
			MDDOrderLine line = lines[i];
			BigDecimal old = line.getQtyOrdered();
			if (old.signum() != 0)
			{
				line.addDescription(Msg.getMsg(getCtx(), "Voided") + " (" + old + ")");		
				line.save(get_TrxName());
			}
		}*/

		List<MDDOrderLine> lines = getLines(true, "M_Product_ID");
		lines.stream()
				.filter(orderLine -> orderLine.getQtyOrdered().signum() != 0)
				.forEach(orderLine -> {
					orderLine.addDescription(Msg.getMsg(getCtx(), "Voided") + " (" + orderLine.getQtyOrdered() + ")");
					orderLine.save(get_TrxName());
				});

		addDescription(Msg.getMsg(getCtx(), "Voided"));
		//	Clear Reservations
		reserveStock(lines);		
		// After Void
		processMessage = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_VOID);
		if (processMessage != null)
			return false;
		
		setProcessed(true);
		setDocAction(DOCACTION_None);
		return true;
	}	//	voidIt
	
	/**
	 * 	Close Document.
	 * 	Cancel not delivered Qunatities
	 * 	@return true if success 
	 */
	public boolean closeIt()
	{
		log.info(toString());
		// Before Close
		processMessage = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_CLOSE);
		if (processMessage != null)
			return false;
		
		//	Close Not delivered Qty - SO/PO
		/*MDDOrderLine[] lines = getLines(true, "M_Product_ID");
		for (int i = 0; i < lines.length; i++)
		{
			MDDOrderLine line = lines[i];
			BigDecimal old = line.getQtyOrdered();
			if (old.compareTo(line.getQtyDelivered()) != 0)
			{
				line.setQtyOrdered(line.getQtyDelivered());
				//	QtyEntered unchanged
				line.addDescription("Close (" + old + ")");
				line.save(get_TrxName());
			}
		}*/

		List<MDDOrderLine> lines = getLines(true, "M_Product_ID");
		lines.stream()
				.filter(orderLine -> orderLine.getQtyOrdered().compareTo(orderLine.getQtyDelivered()) != 0)
				.forEach(orderLine -> {
					orderLine.setQtyOrdered(orderLine.getQtyDelivered());
					orderLine.addDescription("Close (" + orderLine.getQtyOrdered() + ")");
					orderLine.save(get_TrxName());
				});

		//	Clear Reservations
		reserveStock(lines);
		setProcessed(true);
		setDocAction(DOCACTION_None);
		// After Close
		processMessage = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_CLOSE);
		if (processMessage != null)
			return false;
		return true;
	}	//	closeIt
	
	/**
	 * 	Reverse Correction - same void
	 * 	@return true if success 
	 */
	public boolean reverseCorrectIt()
	{
		log.info(toString());
		// Before reverseCorrect
		processMessage = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_REVERSECORRECT);
		if (processMessage != null)
			return false;
		
		// After reverseCorrect
		processMessage = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_REVERSECORRECT);
		if (processMessage != null)
			return false;
		
		return voidIt();
	}	//	reverseCorrectionIt
	
	/**
	 * 	Reverse Accrual - none
	 * 	@return false 
	 */
	public boolean reverseAccrualIt()
	{
		log.info(toString());
		// Before reverseAccrual
		processMessage = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_REVERSEACCRUAL);
		if (processMessage != null)
			return false;
		
		// After reverseAccrual
		processMessage = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_REVERSEACCRUAL);
		if (processMessage != null)
			return false;
		
		return false;
	}	//	reverseAccrualIt
	
	/**
	 * 	Re-activate.
	 * 	@return true if success 
	 */
	public boolean reActivateIt()
	{
		log.info(toString());
		// Before reActivate
		processMessage = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_REACTIVATE);
		if (processMessage != null)
			return false;	
		// After reActivate
		processMessage = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_REACTIVATE);
		if (processMessage != null)
			return false;
		
		setDocAction(DOCACTION_Complete);
		setProcessed(false);
		return true;
	}	//	reActivateIt
	
	
	/*************************************************************************
	 * 	Get Summary
	 *	@return Summary of Document
	 */
	public String getSummary()
	{
		StringBuffer sb = new StringBuffer();
		sb.append(getDocumentNo());
			
		if (orderLines != null)
			sb.append(" (#").append(orderLines.size()).append(")");
		//	 - Description
		if (getDescription() != null && getDescription().length() > 0)
			sb.append(" - ").append(getDescription());
		return sb.toString();
	}	//	getSummary
	
	/**
	 * 	Get Process Message
	 *	@return clear text error message
	 */
	public String getProcessMsg()
	{
		return processMessage;
	}	//	getProcessMsg
	
	/**
	 * 	Get Document Owner (Responsible)
	 *	@return AD_User_ID
	 */
	public int getDoc_User_ID()
	{
		return getSalesRep_ID();
	}	//	getDoc_User_ID


	public BigDecimal getApprovalAmt() {
		// TODO Auto-generated method stub
		return null;
	}


	public int getC_Currency_ID() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 	Document Status is Complete or Closed
	 *	@return true if CO, CL or RE
	 */
	public boolean isComplete()
	{
		String ds = getDocStatus();
		return DOCSTATUS_Completed.equals(ds) 
			|| DOCSTATUS_Closed.equals(ds)
			|| DOCSTATUS_Reversed.equals(ds);
	}	//	isComplete

}	//	MDDOrder
