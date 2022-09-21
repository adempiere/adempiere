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

import java.util.Properties;

import org.adempiere.core.domains.models.I_AD_Client;
import org.adempiere.exceptions.AdempiereException;
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
import org.compiere.model.Query;
import org.compiere.util.CLogger;
import org.compiere.util.Util;


/**
 * Util class for import / export record based on wrapper
 * @author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 * 	<li> Add exporter based on wrapper
 */
public class WrapperUtil {

	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(WrapperUtil.class);
	
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
	public static String exportRecord(PO entity, MClient client, Integer replicationMode, String replicationType, Integer replicationEvent, MReplicationStrategy replicationStrategy, MEXPFormat exportFormat) {
		try {
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
				exportProcessorInstance.process(entity.getCtx(), exportProcessor, wrapper, entity.get_TrxName());
			}
			//	Return document as String
			return wrapper.toString();
		} catch (Exception e) {
			throw new AdempiereException(e.getLocalizedMessage());
		}
	}
	
	/**
	 * Import based on wrapper
	 * @param context
	 * @param wrapper
	 * @param trxName
	 */
	public static void importWrapper(Properties context, EntityWrapper wrapper, String trxName) {
		try {
			//	Validate Wrapper
			if(wrapper == null) {
				throw new AdempiereException("@Wrapper@ @NotFound@");
			}
			wrapper.setContext(context);
			wrapper.setTransactionName(trxName);
			//	Validate version
			if(Util.isEmpty(wrapper.getExportFormatVersion())) {
				throw new AdempiereException("@EXP_Format_ID@ @Version@ @NotFound@");
			}
			//	Validate Export format
			if(Util.isEmpty(wrapper.getExportFormatUuid()) && Util.isEmpty(wrapper.getExportFormatValue())) {
				throw new AdempiereException("@EXP_Format_ID@ (@UUID@ / @Value@) @NotFound@");
			}
			//	
			if(Util.isEmpty(wrapper.getClientUuid()) && Util.isEmpty(wrapper.getClientValue())) {
				throw new AdempiereException("@AD_Client_ID@ (@UUID@ / @Value@) @NotFound@");
			}
			MClient client = null;
			if(!Util.isEmpty(wrapper.getClientUuid())) {
				client = new Query(context, I_AD_Client.Table_Name, I_AD_Client.COLUMNNAME_UUID + " = ?", trxName)
						.setParameters(wrapper.getClientUuid())
						.first();
			} else {
				client = new Query(context, I_AD_Client.Table_Name, I_AD_Client.COLUMNNAME_Value + " = ?", trxName)
						.setParameters(wrapper.getClientValue())
						.first();
			}
			if (client == null)  {
				throw new AdempiereException("@AD_Client_ID@ (@UUID@ / @Value@) @NotFound@");
			}
			//	
			log.info("EXP_Format_Value = " + wrapper.getExportFormatValue());
			MEXPFormat exportFormat = null;
			//	Get Export format
			if(!Util.isEmpty(wrapper.getExportFormatUuid())) {
				exportFormat = MEXPFormat.getFormatByUuid(context, wrapper.getExportFormatUuid(), trxName);
			} else {
				exportFormat = MEXPFormat.getFormatByValueAD_Client_IDAndVersion(context, wrapper.getExportFormatValue(), client.getAD_Client_ID(), wrapper.getExportFormatVersion(), trxName);
			}
			//	Default
			if (exportFormat == null || exportFormat.getEXP_Format_ID() == 0)  {
				// Fall back to SYSTEM Client.
				// Try to search Export format defined for SYSTEM Client!!!
				MClient systemClient = null;
				systemClient = MClient.get(context, 0);
				if (systemClient == null)  {
					throw new AdempiereException("@XMLClientNotFound@");
				}
				log.info("SYSTEM Client = " + systemClient.toString());
				exportFormat = MEXPFormat.getFormatByValueAD_Client_IDAndVersion(context, wrapper.getExportFormatValue(), systemClient.getAD_Client_ID(), wrapper.getExportFormatVersion(), trxName);
			}
			//	No default
			if (exportFormat == null || exportFormat.getEXP_Format_ID() == 0)  {
				throw new AdempiereException("@EXPFormatNotFound@");
			}
			log.info("exportFormat = " + exportFormat.toString());
			//	TODO: Import it
			EntityWrapperFactory.create()
					.withClient(client)
					.withExportFormat(exportFormat)
					.withWrapper(wrapper)
					.buildEntity()
					.saveEntity();
		} catch (Exception e) {
			throw new AdempiereException(e.getLocalizedMessage());
		}
	}
}
