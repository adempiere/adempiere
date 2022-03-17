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

import org.compiere.model.MProject;
import org.compiere.model.MProjectType;

/**
 *  Set Project Type
 *
 *	@author Jorg Janke
 *	@version $Id: ProjectSetType.java,v 1.2 2006/07/30 00:51:02 jjanke Exp $
 */
public class ProjectSetType extends ProjectSetTypeAbstract
{
	/**	Project directly from Project	*/
	//private int				m_C_Project_ID = 0;
	/** Project Type Parameter			*/
	//private int				m_C_ProjectType_ID = 0;

	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		super.prepare();
	}	//	prepare

	/**
	 *  Perform process.
	 *  @return Message (clear text)
	 *  @throws Exception if not successful
	 */
	protected String doIt() throws Exception
	{
		//m_C_Project_ID = getRecord_ID();
		log.info("doIt - C_Project_ID=" + getRecord_ID() + ", C_ProjectType_ID=" + getProjectTypeId());
		//
		MProject project = new MProject (getCtx(), getRecord_ID(), get_TrxName());
		if (project.getC_Project_ID() == 0 || project.getC_Project_ID() != getRecord_ID())
			throw new IllegalArgumentException("Project not found C_Project_ID=" + getRecord_ID());
		if (project.getC_ProjectType_ID_Int() > 0)
			throw new IllegalArgumentException("Project already has Type (Cannot overwrite) " + project.getC_ProjectType_ID());
		//
		MProjectType type = new MProjectType (getCtx(), getProjectTypeId(), get_TrxName());
		if (type.getC_ProjectType_ID() == 0 || type.getC_ProjectType_ID() != getProjectTypeId())
			throw new IllegalArgumentException("Project Type not found C_ProjectType_ID=" + getProjectTypeId());

		//	Set & Copy if Service
		project.setProjectType(type);
		project.saveEx();
		return "@OK@";
	}	//	doIt

}	//	ProjectSetType
