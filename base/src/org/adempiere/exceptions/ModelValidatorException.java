/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) metas.de. All Rights Reserved.                               *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *                                                                            *
 * @author Tobias Schoeneberg, t.schoeneberg@metas.de                         *
 *  FR [ADEMPIERE-28] ModelValidatorException                                 *
 *   https://adempiere.atlassian.net/browse/ADEMPIERE-28                      *
 *  			                                                              *
 *****************************************************************************/
package org.adempiere.exceptions;

import org.compiere.model.ModelValidationEngine;

/**
 * Exception can be thrown by model validators. This exception has a property {@link #isPassOn()} that tells the
 * {@link ModelValidationEngine} whether it should be caught like every ordinary exception or if it should be re-thrown.
 * 
 * The intention is to give a model validator the ability to pass detailed error information to the caller of its
 * respective PO.
 * 
 * @author Tobias Schoeneberg, t.schoeneberg@metas.de FR [ADEMPIERE-28] ModelValidatorException
 *         https://adempiere.atlassian.net/browse/ADEMPIERE-28
 * 
 */
public class ModelValidatorException extends AdempiereException
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1697279669903710377L;

	private final boolean passOn;

	public ModelValidatorException(
			String language, String message, Object[] params,
			boolean passOn)
	{
		super(language, message, params);
		this.passOn = passOn;
	}

	public boolean isPassOn()
	{
		return passOn;
	}
}
