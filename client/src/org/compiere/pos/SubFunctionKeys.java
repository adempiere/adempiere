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
 *****************************************************************************/

package org.compiere.pos;

import java.awt.Dimension;
import java.awt.Font;

import net.miginfocom.swing.MigLayout;

import org.adempiere.plaf.AdempierePLAF;
import org.compiere.apps.ADialog;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MOrder;
import org.compiere.model.MPOSKey;
import org.compiere.model.MUser;
import org.compiere.swing.CLabel;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;


/**
 *	Function Key Sub Panel
 *	
 *  @author Comunidad de Desarrollo OpenXpertya 
 *         *Basado en Codigo Original Modificado, Revisado y Optimizado de:
 *         *Copyright (c) Jorg Janke
 *  @version $Id: SubFunctionKeys.java,v 1.1 2004/07/12 04:10:04 jjanke Exp $
 */
public class SubFunctionKeys extends PosSubPanel implements PosKeyListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2131406504920855582L;

	/**
	 * 	Constructor
	 *	@param posPanel POS Panel
	 */
	public SubFunctionKeys (VPOS posPanel)
	{
		super (posPanel);
	}	//	PosSubFunctionKeys
	
	/**	Keys				*/
	private MPOSKey[] 	m_keys;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(SubFunctionKeys.class);
	

	/**
	 * 	Initialize
	 */
	public void init()
	{
		int C_POSKeyLayout_ID = p_pos.getC_POSKeyLayout_ID();
		if (C_POSKeyLayout_ID == 0)
			return;
		
		PosKeyPanel panel = new PosKeyPanel(C_POSKeyLayout_ID, this);
		this.setLayout(new MigLayout("fill, ins 20 10"));
		Font bigFont = AdempierePLAF.getFont_Field().deriveFont(18f);		
 		// DOC NO
		CLabel lDocNo = new CLabel(Msg.getMsg(Env.getCtx(),MOrder.COLUMNNAME_DocumentNo)+":");
		add (lDocNo, "growx"); 
		lDocNo.setFontBold(true);
		lDocNo.setFont(bigFont);
		lDocNo.setFontBold(true);
		p_posPanel.f_curLine.f_DocumentNo = new CLabel("");
		lDocNo.setLabelFor(p_posPanel.f_curLine.f_DocumentNo);
		p_posPanel.f_curLine.f_DocumentNo.setName(MOrder.COLUMNNAME_DocumentNo);
		p_posPanel.f_curLine.f_DocumentNo.setFont(bigFont);
		add (p_posPanel.f_curLine.f_DocumentNo, "pushx");
		
		CLabel lNet = new CLabel (Msg.translate(Env.getCtx(), MOrder.COLUMNNAME_TotalLines)+":");
		add(lNet, "");
		p_posPanel.f_curLine.f_net = new CLabel("0.00");
		p_posPanel.f_curLine.f_net.setFocusable(false);
		p_posPanel.f_curLine.f_net.setFont(bigFont);
		p_posPanel.f_curLine.f_net.setFontBold(true);
		lNet.setLabelFor(p_posPanel.f_curLine.f_net);
		lNet.setFont(bigFont);
		lNet.setFontBold(true);
		add(p_posPanel.f_curLine.f_net, "wrap, growx, pushx");
	
		// SALES REP
		CLabel lSalesRep = new CLabel(Msg.translate(Env.getCtx(), "POS.SalesRep_ID")+":");
		add(lSalesRep, "growx"); 
		lSalesRep.setFont(bigFont);
		lSalesRep.setFontBold(true);
		MUser salesrep = new MUser(p_ctx, Env.getAD_User_ID(p_ctx), null);
		p_posPanel.f_curLine.f_RepName = new CLabel(salesrep.getName());
		p_posPanel.f_curLine.f_RepName.setName("SalesRep");
		p_posPanel.f_curLine.f_RepName.setFont(bigFont);
		add (p_posPanel.f_curLine.f_RepName, "");

		CLabel lTax = new CLabel (Msg.translate(Env.getCtx(), MInvoiceLine.COLUMNNAME_C_Tax_ID)+":");
		add(lTax, "growx");
		p_posPanel.f_curLine.f_tax = new CLabel("0.00");
		p_posPanel.f_curLine.f_tax.setFocusable(false);
		p_posPanel.f_curLine.f_tax.setFont(bigFont);
		p_posPanel.f_curLine.f_tax.setFontBold(true);
		lTax.setLabelFor(p_posPanel.f_curLine.f_tax);
		lTax.setFont(bigFont);
		lTax.setFontBold(true);
		add(p_posPanel.f_curLine.f_tax, "wrap, growx, pushx");
		//
		//
		CLabel f_Line = new CLabel ("___________________");
		add(f_Line, "span, growx, wrap");
		
		CLabel lTotal = new CLabel (Msg.translate(Env.getCtx(), MOrder.COLUMNNAME_GrandTotal)+":");
		add(lTotal, "cell 2 4, growx");
		p_posPanel.f_curLine.f_total = new CLabel("0.00");
		p_posPanel.f_curLine.f_total.setFocusable(false);
		p_posPanel.f_curLine.f_total.setFont(bigFont);
		p_posPanel.f_curLine.f_total.setFontBold(true);
		p_posPanel.f_curLine.f_total.setMinimumSize(new Dimension(150,5));
		lTotal.setLabelFor(p_posPanel.f_curLine.f_total);
		lTotal.setFont(bigFont);
		lTotal.setFontBold(true);
		add(p_posPanel.f_curLine.f_total, "wrap, growx, pushx,cell 3 4");

		CLabel lProduct = new CLabel(Msg.translate(Env.getCtx(), "M_Product_ID"));
		lProduct.setFont(bigFont);
		lProduct.setFontBold(true);
		add(lProduct, "split 2,spanx 4");
		
		p_posPanel.f_curLine.f_name.setFont(bigFont);
		add (p_posPanel.f_curLine.f_name, "spanx 3, growx, h 30:30:, wrap");

		add(panel, "growx, growy, span");

	}	//	init
	
	/**
	 * 	Dispose - Free Resources
	 */
	public void dispose()
	{
		super.dispose();
	}	//	dispose

	/**
	 * Call back from key panel
	 */
	public void keyReturned(MPOSKey key) {
		// processed order
		if (p_posPanel.getM_Order() != null 
				&& p_posPanel.getM_Order().isProcessed())
			return;
		
		// new line
		p_posPanel.f_curLine.setM_Product_ID(key.getM_Product_ID());
		p_posPanel.f_curLine.setPrice();
		p_posPanel.f_curLine.setQty(key.getQty());
		if ( !p_posPanel.f_curLine.saveLine() )
		{
			ADialog.error(0, this, "Could not save order line");
		}
		p_posPanel.updateInfo();
		return;
	}
	
}	//	PosSubFunctionKeys
