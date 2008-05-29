package org.eevolution.process;

import java.math.*;
import java.util.logging.Level;
import java.util.*;

import org.compiere.model.*;
import org.compiere.util.*;
import org.compiere.process.*;


/**
 *	AddLiberoRecords
 *	
 *	@author Tim Heath
 *	@version $Id: AddLiberoRecords.java,v 1 xp_prg Exp $
 */
public class CreateDocType extends SvrProcess
{
	/**					*/
        
	private int AD_Client_ID = 0 ;
	private String trxname = null;
        
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		System.out.println("In AddLiberoRecords prepare");
		log.fine("In AddLiberoRecords prepare");
		AD_Client_ID = Integer.parseInt(Env.getContext(Env.getCtx(), "#AD_Client_ID"));
                ProcessInfoParameter[] para = getParameter();
	}	//	prepare

        
     protected String doIt() throws Exception                
	{
		System.out.println("In AddLiberoRecords doIt");
		log.fine("In AddLiberoRecords doIt");

			//MClient c = (MClient)po;
	        Properties m_ctx = Env.getCtx();
	        trxname = get_TrxName();
			int GL_M = createGLCategory("Manufactuing ", MGLCategory.CATEGORYTYPE_Document, false);
				createDocType("Maintenacne Order", Msg.getElement(m_ctx, "MPC_Order_ID", false),
					X_C_DocType.DOCBASETYPE_MaintenanceOrder, null, 0, 0, 910000, GL_M);
				createDocType("Manufacturing Order Issue", Msg.getElement(m_ctx, "MPC_Order_ID", false), 
						X_C_DocType.DOCBASETYPE_ManufacturingOrderIssue, null, 0, 0, 920000, GL_M);
				createDocType("Manufacturing Order Method Variation", Msg.getElement(m_ctx, "MPC_Order_ID", false), 
						X_C_DocType.DOCBASETYPE_ManufacturingOrderMethodVariation, null, 0, 0, 930000, GL_M);
				createDocType("Manufacturing Order", Msg.getElement(m_ctx, "MPC_Order_ID", false), 
						X_C_DocType.DOCBASETYPE_ManufacturingOrder, null, 0, 0, 940000, GL_M);
				createDocType("Manufacturing Order Planning", Msg.getElement(m_ctx, "MPC_Order_ID", false), 
						X_C_DocType.DOCBASETYPE_ManufacturingOrder, null, 0, 0, 950000, GL_M);
				createDocType("Manufacturing Order Receipt", Msg.getElement(m_ctx, "MPC_Order_ID", false), 
						X_C_DocType.DOCBASETYPE_ManufacturingOrderReceipt, null, 0, 0, 960000, GL_M);
				createDocType("Manufacturing Order Use Variation", Msg.getElement(m_ctx, "MPC_Order_ID", false), 
						X_C_DocType.DOCBASETYPE_ManufacturingOrderUseVariation, null, 0, 0, 970000, GL_M);
				createDocType("Manufaturing Order Rate Variation", Msg.getElement(m_ctx, "MPC_Order_ID", false), 
						X_C_DocType.DOCBASETYPE_ManufacturingOrderRateVariation, null, 0, 0, 980000, GL_M);
				createDocType("Distribution Order", Msg.getElement(m_ctx, "DD_Order_ID", false), 
						X_C_DocType.DOCBASETYPE_DistributionOrder, null, 0, 0, 990000, GL_M);
            return "ok";
	
		}
            

	/**
	 *  Create GL Category
	 *  @param Name name
	 *  @param CategoryType category type MGLCategory.CATEGORYTYPE_*
	 *  @param isDefault is default value
	 *  @return GL_Category_ID
	 */
	private int createGLCategory (String Name, String CategoryType, boolean isDefault)
	{
		MGLCategory cat = new MGLCategory (Env.getCtx() , 0, trxname);
		cat.setName(Name);
		cat.setCategoryType(CategoryType);
		cat.setIsDefault(isDefault);
		if (!cat.save())
		{
			log.log(Level.SEVERE, "GL Category NOT created - " + Name);
			return 0;
		}
		//
		return cat.getGL_Category_ID();
	}   //  createGLCategory
	
	/**
	 *  Create Document Types with Sequence
	 *  @param Name name
	 *  @param PrintName print name
	 *  @param DocBaseType document base type
	 *  @param DocSubTypeSO sales order sub type
	 *  @param C_DocTypeShipment_ID shipment doc
	 *  @param C_DocTypeInvoice_ID invoice doc
	 *  @param StartNo start doc no
	 *  @param GL_Category_ID gl category
	 *  @return C_DocType_ID doc type or 0 for error
	 */
	private int createDocType (String Name, String PrintName,
		String DocBaseType, String DocSubTypeSO,
		int C_DocTypeShipment_ID, int C_DocTypeInvoice_ID,
		int StartNo, int GL_Category_ID)
	{
	        log.fine("In createDocType");
	        log.fine("docBaseType: " + DocBaseType);
	        log.fine("GL_Category_ID: " + GL_Category_ID);
		MSequence sequence = null;
		if (StartNo != 0)
		{
			sequence = new MSequence(Env.getCtx(), getAD_Client_ID(), Name, StartNo, trxname);
			if (!sequence.save())
			{
				log.log(Level.SEVERE, "Sequence NOT created - " + Name);
				return 0;
			}
		}
		
		//MDocType dt = new MDocType (Env.getCtx(), DocBaseType, Name, trxname);
		MDocType dt = new MDocType (Env.getCtx(),0 , trxname);
		dt.setAD_Org_ID(0);
		dt.set_CustomColumn("DocBaseType", (Object) DocBaseType);	
		dt.setName (Name);
		dt.setPrintName (Name);
		if (DocSubTypeSO != null)
			dt.setDocSubTypeSO(DocSubTypeSO);
		if (C_DocTypeShipment_ID != 0)
			dt.setC_DocTypeShipment_ID(C_DocTypeShipment_ID);
		if (C_DocTypeInvoice_ID != 0)
			dt.setC_DocTypeInvoice_ID(C_DocTypeInvoice_ID);
		if (GL_Category_ID != 0)
			dt.setGL_Category_ID(GL_Category_ID);
		if (sequence == null)
			dt.setIsDocNoControlled(false);
		else
		{
			dt.setIsDocNoControlled(true);
			dt.setDocNoSequence_ID(sequence.getAD_Sequence_ID());
		}
		dt.setIsSOTrx(false);
		if (!dt.save())
		{
			log.log(Level.SEVERE, "DocType NOT created - " + Name);
			return 0;
		}
		//
		return dt.getC_DocType_ID();
	}   //  createDocType

}
