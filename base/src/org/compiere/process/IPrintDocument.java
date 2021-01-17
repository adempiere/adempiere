/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.									  *
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
package org.compiere.process;

import java.util.List;

import org.compiere.model.PO;

/**
 * Created by eEvolution author Victor Perez <victor.perez@e-evolution.com> on 25/01/15.
 * @author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 * Improve definition
 */
public interface IPrintDocument {
    /**
     * Each document
     * @param document
     * @param printFormatId
     * @param windowNo
     * @param askPrint
     */
	public void print (PO document, int printFormatId, int windowNo, boolean askPrint);
    
	/**
	 * Print a Document List
	 * @param documentList
	 * @param printFormatId
	 * @param windowNo
	 * @param askPrint
	 */
    public void print (List<PO> documentList, int printFormatId, int windowNo, boolean askPrint);
}
