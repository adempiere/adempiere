/************************************************************************************
 * Copyright (C) 2012-2020 E.R.P. Consultores y Asociados, C.A.                     *
 * Contributor(s): Yamel Senih ysenih@erpya.com                                     *
 * This program is free software: you can redistribute it and/or modify             *
 * it under the terms of the GNU General Public License as published by             *
 * the Free Software Foundation, either version 2 of the License, or                *
 * (at your option) any later version.                                              *
 * This program is distributed in the hope that it will be useful,                  *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of                   *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.	See the                     *
 * GNU General Public License for more details.                                     *
 * You should have received a copy of the GNU General Public License                *
 * along with this program.	If not, see <https://www.gnu.org/licenses/>.            *
 ************************************************************************************/
package org.adempiere.process.rpl.exp;

import org.adempiere.process.rpl.IExportProcessorAsWrapper;
import org.adempiere.process.rpl.IExportProcessorDefinition;
import org.adempiere.util.rpl.EntityWrapper;
import org.adempiere.util.rpl.EntityWrapperFactory;
import org.compiere.model.MClient;
import org.compiere.model.MEXPFormat;
import org.compiere.model.MEXPProcessor;
import org.compiere.model.MEXPProcessorType;
import org.compiere.model.MReplicationStrategy;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.Trx;


/**
 * Util class for export record based on wrapper
 * @author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 * 	<li> Add exporter based on wrapper
 */
public class ExportWrapperUtil {

	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(ExportWrapperUtil.class);
	
	/**
	 * Export record based on entity and client
	 * @param entity
	 * @param client
	 * @param replicationMode
	 * @param replicationType
	 * @param replicationEvent
	 * @param replicationStrategy
	 * @param exportFormat
	 * @return
	 * @throws Exception
	 */
	public static String exportRecord(PO entity, MClient client, Integer replicationMode, String replicationType, Integer replicationEvent, MReplicationStrategy replicationStrategy, MEXPFormat exportFormat) throws Exception {
		//	Build Wrapper
		EntityWrapper wrapper = EntityWrapperFactory.create()
			.withClient(client)
			.withExportFormat(exportFormat)
			.withReplicationEvent(replicationEvent)
			.withReplicationMode(replicationMode)
			.withReplicationType(replicationType)
			.withEntity(entity)
			.buildWrapper();
		//	
		MEXPProcessor exportProcessor = new MEXPProcessor(entity.getCtx(), replicationStrategy.getEXP_Processor_ID(), entity.get_TrxName());
		log.fine("ExportProcessor = " + exportProcessor);
		MEXPProcessorType expProcessorType = new MEXPProcessorType(entity.getCtx(), exportProcessor.getEXP_Processor_Type_ID(), entity.get_TrxName() );
		IExportProcessorDefinition processorInstance = expProcessorType.getProcessorInstance();
		if(IExportProcessorAsWrapper.class.isAssignableFrom(processorInstance.getClass())) {
			IExportProcessorAsWrapper exportProcessorInstance = (IExportProcessorAsWrapper) processorInstance;
			exportProcessorInstance.process(entity.getCtx(), exportProcessor, wrapper, Trx.get( entity.get_TrxName(), false));
		}
		//	Return document as String
		return wrapper.toString();
	}
}
