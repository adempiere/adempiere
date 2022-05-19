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
package org.compiere.project.process;


import java.util.List;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MProject;
import org.compiere.model.MProjectLine;
 
/**
 *  Close Project.
 *
 *	@author Jorg Janke
 *	@version $Id: ProjectClose.java,v 1.2 2006/07/30 00:51:01 jjanke Exp $
 *
 * @author Teo Sarca, wwww.arhipac.ro
 * 			<li>FR [ 2791635 ] Use saveEx whenever is possible
 * 				https://sourceforge.net/tracker/?func=detail&aid=2791635&group_id=176962&atid=879335
 */
public class ProjectClose extends ProjectCloseAbstract {
	
	@Override
	protected void prepare() {
		super.prepare();
		if(getRecord_ID() == 0) {
			throw new AdempiereException("@C_Project_ID@ @IsMandatory@");
		}
	}

	/**
	 *  Perform process.
	 *  The process sets "unprocessed" projects to "processed" and vice-versa
	 *  It works like a flip-flop
	 *  @return Message (translated text)
	 *  @throws Exception if not successful
	 */
	protected String doIt() throws Exception {
		MProject project = new MProject (getCtx(), getRecord_ID(), get_TrxName());
		log.info("doIt - " + project);

		List<MProjectLine> projectLines = project.getLines();
		if (MProject.PROJECTCATEGORY_WorkOrderJob.equals(project.getProjectCategory())
			|| MProject.PROJECTCATEGORY_AssetProject.equals(project.getProjectCategory()))
		{
			/** @todo Check if we should close it */
		}

		//	Close lines
		projectLines.stream().forEach( projectLine ->{
			projectLine.setProcessed(projectLine.isProcessed()?false:true);
			projectLine.saveEx();
		});

		project.setProcessed(project.isProcessed()?false:true);
		project.saveEx();

		return "";
	}	//	doIt

}	//	ProjectClose
