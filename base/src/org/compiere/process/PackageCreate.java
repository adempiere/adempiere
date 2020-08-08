/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.process;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MInOut;
import org.compiere.model.MPackage;
import org.compiere.model.MShipper;
 
/**
 *	Create Package from Shipment for Shipper
 *	
 *  @author Jorg Janke
 *  @version $Id: PackageCreate.java,v 1.2 2006/07/30 00:51:01 jjanke Exp $
 *  @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 *  Add support to package like document
 */
public class PackageCreate extends PackageCreateAbstract {
	/**
	 * 	Process
	 *	@return message
	 *	@throws Exception
	 */
	protected String doIt () throws Exception {
		log.info("doIt - M_InOut_ID=" + getInOutId() + ", M_Shipper_ID=" + getShipperId());
		MInOut shipment = new MInOut (getCtx(), getInOutId(), get_TrxName());
		if (shipment.get_ID() != getInOutId())
			throw new IllegalArgumentException("@M_InOut_ID@ @NotFound@");
		MShipper shipper = new MShipper (getCtx(), getShipperId(), get_TrxName());
		if (shipper.get_ID() != getShipperId())
			throw new IllegalArgumentException("@M_Shipper_ID@ @NotFound@");
		//
		MPackage pack = MPackage.create(shipment, shipper, shipment.getMovementDate());
		if(!pack.processIt(MPackage.ACTION_Complete)) {
			throw new AdempiereException(pack.getProcessMsg());
		}
		//	Save
		pack.saveEx();
		return pack.getDocumentNo();
	}	//	doIt
}	//	PackageCreate
