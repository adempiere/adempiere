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

package org.compiere.process;


/** Generated Process for (GL Distribution Copy From)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class CopyFromDistributionAbstract extends SvrProcess
{
	/** Process Value 	*/
	private static final String VALUE = "GL_Distribution_CopyFrom";
	/** Process Name 	*/
	private static final String NAME = "GL Distribution Copy From";
	/** Process Id 	*/
	private static final int ID = 53949;
 
	/**	Parameter Name for GL_Distribution_ID	*/
	public static final String GL_Distribution_ID = "GL_Distribution_ID";

	/**	Parameter Value for gLDistributionId	*/
	private int gLDistributionId;
 

	@Override
	protected void prepare()
	{
		gLDistributionId = getParameterAsInt(GL_Distribution_ID);
	}

	/**	 Getter Parameter Value for gLDistributionId	*/
	protected int getGLDistributionId() {
		return gLDistributionId;
	}

	/**	 Getter Parameter Value for Process ID	*/
	public static final int getProcessId() {
		return ID;
	}

	/**	 Getter Parameter Value for Process Value	*/
	public static final String getProcessValue() {
		return VALUE;
	}

	/**	 Getter Parameter Value for Process Name	*/
	public static final String getProcessName() {
		return NAME;
	}
}