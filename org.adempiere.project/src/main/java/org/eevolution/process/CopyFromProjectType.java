/**
 * Copyright (C) 2003-2017, e-Evolution Consultants S.A. , http://www.e-evolution.com
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * Email: victor.perez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
 * Created by victor.perez@e-evolution.com , www.e-evolution.com
 */

package org.eevolution.process;

import org.compiere.model.MProjectType;
import org.compiere.model.MProjectTypePhase;
import org.compiere.model.MProjectTypeTask;
import org.compiere.model.MStandardProjectLine;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/** Generated Process for (Copy from another Project Type)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.1
 */
public class CopyFromProjectType extends CopyFromProjectTypeAbstract
{
	@Override
	protected void prepare()
	{
		super.prepare();
	}

	@Override
	protected String doIt() throws Exception
	{
		Optional<MProjectType> projectTypeFromOptiional = Optional.of(new MProjectType(getCtx() , getProjectTypeId() , get_TrxName()));
		projectTypeFromOptiional.ifPresent(projectTypeFrom -> {
			if (projectTypeFrom.get_ValueAsString("ProjectBased").equals("L")) {
				List<MStandardProjectLine> standardLinesFrom = MStandardProjectLine.getNodes(projectTypeFrom, 0);
				HashMap<MStandardProjectLine,MStandardProjectLine> standardLinesTo = new HashMap<MStandardProjectLine,MStandardProjectLine>();
				HashMap<Integer,Integer> stdParents = new HashMap<Integer,Integer>(); 
				
				
				standardLinesFrom.stream()
					.forEach(stdPLineFrom ->{
						MStandardProjectLine stdPLineTo = new MStandardProjectLine(getCtx(), 0, get_TrxName());
						MStandardProjectLine.copyValues(stdPLineFrom, stdPLineTo);
						stdPLineTo.setParent_ID(0);
						stdPLineTo.setC_ProjectType_ID(getRecord_ID());
						stdPLineTo.save();
						if (stdPLineFrom.hasChilds())
							stdParents.put(stdPLineFrom.getC_StandardProjectLine_ID(),stdPLineTo.getC_StandardProjectLine_ID());
						standardLinesTo.put(stdPLineFrom, stdPLineTo);
				});
		
				standardLinesTo
					.forEach( (stdPLineFrom, stdPLineTo) ->{
						Integer Parent_ID = stdParents.get(stdPLineFrom.getParent_ID());
						if (Parent_ID!=null) {
							stdPLineTo.setParent_ID(Parent_ID);
							stdPLineTo.save();
						}
				});
				
			}else {
				projectTypeFrom.getPhases().stream().forEach(fromPhase -> {
					MProjectTypePhase projectTypePhase = new MProjectTypePhase(getCtx(), 0, get_TrxName());
					projectTypePhase.setC_ProjectType_ID(getRecord_ID());
					projectTypePhase.setSeqNo(fromPhase.getSeqNo());
					projectTypePhase.setName(fromPhase.getName());
					projectTypePhase.setDescription(fromPhase.getDescription());
					projectTypePhase.setHelp(fromPhase.getHelp());
					projectTypePhase.setM_Product_ID(fromPhase.getM_Product_ID());
					projectTypePhase.setStandardQty(fromPhase.getStandardQty());
					projectTypePhase.setR_StandardRequestType_ID(fromPhase.getR_StandardRequestType_ID());
					projectTypePhase.saveEx();
					fromPhase.getTasks().stream().forEach(task -> {
						MProjectTypeTask projectTypeTask = new MProjectTypeTask(getCtx(), 0, get_TrxName());
						projectTypeTask.setC_Phase_ID(projectTypePhase.getC_Phase_ID());
						projectTypeTask.setSeqNo(task.getSeqNo());
						projectTypeTask.setName(task.getName());
						projectTypeTask.setDescription(task.getDescription());
						projectTypeTask.setHelp(task.getHelp());
						projectTypeTask.setM_Product_ID(task.getM_Product_ID());
						projectTypeTask.setStandardQty(task.getStandardQty());
						projectTypeTask.setR_StandardRequestType_ID(task.getR_StandardRequestType_ID());
						projectTypeTask.saveEx();
					});
				});
			}
		});
		return "@OK@";
	}
}