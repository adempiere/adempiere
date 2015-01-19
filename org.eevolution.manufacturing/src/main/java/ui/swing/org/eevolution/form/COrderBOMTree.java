/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2007 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/

package org.eevolution.form;

import org.eevolution.model.wrapper.BOMWrapper;

/**
 * Order BOM Tree is based on PPOrderBOM.
 *
 * AD_FORM:
 * INSERT INTO ad_form VALUES (1000024, 0, 0, 'Y', CURRENT_TIMESTAMP, 100, CURRENT_TIMESTAMP, 100, 'Order BOM Tree', NULL, NULL, '3', 'org.compiere.mfg.form.COrderBOMTree', 'U', 'N', NULL);
 *
 * @author Gunther Hoppe, tranSIT GmbH Ilmenau/Germany
 * @version 1.0, October 14th 2005
 */
public class COrderBOMTree extends CAbstractBOMTree {

	public COrderBOMTree() {

		super();
	}

	protected String type() {

		return BOMWrapper.BOM_TYPE_ORDER;
	}
}
