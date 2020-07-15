/************************************************************************************
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, C.A.                     *
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
package org.adempiere.util.rpl;

import java.util.Collection;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_AD_Image;
import org.compiere.model.MClient;
import org.compiere.model.MColumn;
import org.compiere.model.MEXPFormat;
import org.compiere.model.MEXPFormatLine;
import org.compiere.model.MImage;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.model.X_EXP_FormatLine;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Msg;
import org.compiere.util.Util;

/**
 * Persistence Object wrapper for data
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 */
public class EntityWrapperFactory {
    
    /**	Logger			*/
	private static CLogger log = CLogger.getCLogger(EntityWrapperFactory.class);
    
    /**	Entity to be parsed	*/
    private PO entity;
    /**	Client	*/
    private MClient client;
    /**	Export format configuration	*/
    private MEXPFormat exportFormat;
    /**	Replication Mode	*/
    private Integer replicationMode;
    /**	Replication Type	*/
    private String replicationType;
    /**	Replication Event	*/
    private Integer replicationEvent;
    /**	Entity Wrapper	*/
    private EntityWrapper wrapper;
    
    /**
     * Default instance
     */
    private static EntityWrapperFactory instance;
    
    public static EntityWrapperFactory create() {
    	if(instance == null) {
    		instance = new EntityWrapperFactory();
    	}
    	//	Wipe values of instance
    	instance.wipeValues();
    	return instance;
    }
    
    /**
     * Wipe values for reuse it
     */
    private void wipeValues() {
    	entity = null;
    	wrapper = null;
    	exportFormat = null;
    	replicationMode = null;
    	replicationType = null;
    	replicationEvent = null;
    }

	/**
	 * @return the entity
	 */
	public final PO getEntity() {
		return entity;
	}

	/**
	 * @param entity the entity to set
	 */
	public final EntityWrapperFactory withEntity(PO entity) {
		this.entity = entity;
		return this;
	}

	/**
	 * @return the exportFormat
	 */
	public final MEXPFormat getExportFormat() {
		return exportFormat;
	}

	/**
	 * @param exportFormat the exportFormat to set
	 */
	public final EntityWrapperFactory withExportFormat(MEXPFormat exportFormat) {
		this.exportFormat = exportFormat;
		return this;
	}

	/**
	 * @return the replicationMode
	 */
	public final Integer getReplicationMode() {
		return replicationMode;
	}

	/**
	 * @param replicationMode the replicationMode to set
	 */
	public final EntityWrapperFactory withReplicationMode(Integer replicationMode) {
		this.replicationMode = replicationMode;
		return this;
	}

	/**
	 * @return the replicationType
	 */
	public final String getReplicationType() {
		return replicationType;
	}

	/**
	 * @param replicationType the replicationType to set
	 */
	public final EntityWrapperFactory withReplicationType(String replicationType) {
		this.replicationType = replicationType;
		return this;
	}

	/**
	 * @return the replicationEvent
	 */
	public final Integer getReplicationEvent() {
		return replicationEvent;
	}

	/**
	 * @param replicationEvent the replicationEvent to set
	 */
	public final EntityWrapperFactory withReplicationEvent(Integer replicationEvent) {
		this.replicationEvent = replicationEvent;
		return this;
	}

	/**
	 * @return the wrapper
	 */
	public final EntityWrapper getWrapper() {
		return wrapper;
	}

	/**
	 * @param wrapper the wrapper to set
	 */
	public final EntityWrapperFactory withWrapper(EntityWrapper wrapper) {
		this.wrapper = wrapper;
		return this;
	}
	
	/**
	 * @return the client
	 */
	public final MClient getClient() {
		return client;
	}

	/**
	 * @param client the client to set
	 */
	public final EntityWrapperFactory withClient(MClient client) {
		this.client = client;
		return this;
	}

	/**
	 * Build wrapper and export from defined export format
	 * @return
	 */
	public EntityWrapper buildWrapper() {
		//	Validate before process
		if(client == null) {
			throw new AdempiereException("@AD_Client_ID@ @NotFound@");
		}
		if(exportFormat == null || exportFormat.getEXP_Format_ID() == 0) {
			throw new AdempiereException("@EXP_Format_ID@ @NotFound@");
		}
		if(entity == null || entity.get_ID() == 0 || Util.isEmpty(entity.get_UUID())) {
			throw new AdempiereException("@Entity@ @NotFound@");
		}
		//	Instance wrapper
		wrapper = createWrapper(exportFormat, entity);
		return getWrapper();
	}
	
	/**
	 * Create wrapper
	 * @param exportFormat
	 * @param entity
	 * @return wrapper
	 */
	private EntityWrapper createWrapper(MEXPFormat exportFormat, PO entity) {
		EntityWrapper wrapperToCreate = new EntityWrapper();
		wrapperToCreate.setAttributes(exportFormat, client, replicationMode, replicationType, replicationEvent);
		wrapperToCreate.setTableName(entity.get_TableName());
		exportFormat.getFormatLines()
		.forEach(exportFormatLine -> {
			createWrapperFromLine(exportFormatLine, entity, wrapperToCreate);
		});
		return wrapperToCreate;
	}
	
	/**
	 * Create Wrapper from export format line
	 * @param exportFormatLine
	 */
	private void createWrapperFromLine(MEXPFormatLine exportFormatLine, PO entity, EntityWrapper wrapper) {
		if(exportFormatLine.getType().equals(X_EXP_FormatLine.TYPE_XMLElement)
				|| exportFormatLine.getType().equals(X_EXP_FormatLine.TYPE_XMLAttribute)) {
			// process single XML Attribute
			// Create new element
			log.info("Format Line Search key: "+ exportFormatLine.getValue());
			if (exportFormatLine.getAD_Column_ID() == 0) {
				throw new AdempiereException(Msg.getMsg (entity.getCtx(), "EXPColumnMandatory"));
			}
			MColumn column = MColumn.get(entity.getCtx(), exportFormatLine.getAD_Column_ID());
			if (column == null) {
				throw new AdempiereException(Msg.getMsg (entity.getCtx(), "EXPColumnMandatory"));
			}
			if (column.isVirtualColumn()) {
				log.info("This is Virtual Column!");
			}
			Object value = entity.get_Value(column.getColumnName());
			if (value == null
					&& exportFormatLine.getType().equals(X_EXP_FormatLine.TYPE_XMLAttribute)
					&& exportFormatLine.isMandatory()) {
				throw new AdempiereException(Msg.getMsg (entity.getCtx(), "EXPFieldMandatory"));
			} else {
				if(column.getAD_Reference_ID() == DisplayType.Image
						&& exportFormatLine.getType().equals(X_EXP_FormatLine.TYPE_XMLElement)) {
					int imageId = ((Integer)value).intValue();
					// Read AD_Image
					MImage image = new MImage(entity.getCtx(), imageId, null);
					if (image.getAD_Image_ID() > 0) {
						EntityWrapper childElement = new EntityWrapper();
						childElement.setAttributes(exportFormat, client, replicationMode, replicationType, replicationEvent);
						childElement.setTableName(I_AD_Image.Table_Name);
						childElement.setId(imageId);
						childElement.setUuid(image.getUUID());
						childElement.setValue(I_AD_Image.COLUMNNAME_Name, image.getName());
						//	Description
						if (!Util.isEmpty(image.getDescription())) {
							childElement.setValue(I_AD_Image.COLUMNNAME_Description, image.getDescription());
						}
						if (!Util.isEmpty(image.getImageURL())) {
							childElement.setValue(I_AD_Image.COLUMNNAME_ImageURL, image.getImageURL());
						}
						if (image.getBinaryData() != null && image.getBinaryData().length > 0) {
							childElement.setValue(I_AD_Image.COLUMNNAME_BinaryData, image.getBinaryData());
						}
					}
				} else {
					wrapper.setValue(column.getColumnName(), entity.get_Value(column.getColumnName()));
				}
			}
		} else if(exportFormatLine.getType().equals(X_EXP_FormatLine.TYPE_EmbeddedEXPFormat)) {
			// get from cache
			MEXPFormat embeddedFormat = MEXPFormat.get(entity.getCtx(), exportFormatLine.getEXP_EmbeddedFormat_ID(), entity.get_TrxName());
			MTable tableEmbedded = MTable.get(entity.getCtx(), embeddedFormat.getAD_Table_ID());
			log.info("Table Embedded = " + tableEmbedded);
			
			final StringBuffer whereClause = new StringBuffer(entity.get_KeyColumns()[0] +"=?");

			if (embeddedFormat.getWhereClause() != null & !"".equals(embeddedFormat.getWhereClause()))  {
			    whereClause.append(" AND ").append(embeddedFormat.getWhereClause());
			}
			new Query(entity.getCtx(), tableEmbedded.getTableName(), whereClause.toString(),
					entity.get_TrxName())
				.setApplyAccessFilter(true)
				.setParameters(new Object[] { entity.get_ID() })
				.list()
				.forEach(embeddedEntity -> {
					EntityWrapper child = createWrapper(embeddedFormat, embeddedEntity);
					wrapper.addChild(child);
				});
		} else if(exportFormatLine.getType().equals(X_EXP_FormatLine.TYPE_ReferencedEXPFormat)) {
			// process Referenced Export Format

			int embeddedFormat_ID = exportFormatLine.getEXP_EmbeddedFormat_ID();
			//get from cache
			MEXPFormat embeddedFormat =  MEXPFormat.get(entity.getCtx(), embeddedFormat_ID, entity.get_TrxName());
			
			MTable tableEmbedded = MTable.get(entity.getCtx(), embeddedFormat.getAD_Table_ID());
			log.info("Table Embedded = " + tableEmbedded);

			final StringBuffer whereClause = new StringBuffer(tableEmbedded.getTableName() + "_ID =?");
			if (embeddedFormat.getWhereClause() != null & !"".equals(embeddedFormat.getWhereClause()))  {
			    whereClause.append(" AND ").append(embeddedFormat.getWhereClause());
			}
			
			String columnName = "";
			if(exportFormatLine.getAD_Reference_ID() == DisplayType.Table | exportFormatLine.getAD_Reference_ID() == DisplayType.Search) {
				MColumn column = MColumn.get(entity.getCtx(), exportFormatLine.getAD_Column_ID());
				columnName = column.getColumnName();
			} else {
				columnName = tableEmbedded.getTableName() + "_ID";
			}

			Object value = entity.get_Value(columnName);
			if (value != null) {
				Collection<PO> instances = null;
				if (tableEmbedded.getTableName().equals("C_Country") || tableEmbedded.getTableName().equals("C_UOM")) {
					// SYSTEM records
					instances = new Query(entity.getCtx(), tableEmbedded.getTableName(), whereClause.toString(), entity.get_TrxName())
						.setParameters(value)
						.list();
					// Tenant specific records
					Collection<PO> tenantSpecificInstances = new Query(entity.getCtx(), tableEmbedded.getTableName(), whereClause.toString(), entity.get_TrxName())
						//.setClient_ID()
						.setApplyAccessFilter(true)
						.setParameters(value)
						.list();

					instances.addAll(tenantSpecificInstances);
				} else {
					instances = new Query(entity.getCtx(), tableEmbedded.getTableName(), whereClause.toString(), entity.get_TrxName())
						.setApplyAccessFilter(true)
						.setParameters(value)
						.list();
				}
				//	Iterate
				instances.forEach(instance -> {
					EntityWrapper child = createWrapper(embeddedFormat, instance);
					wrapper.addChild(child);
				});
			}
		} else {
			throw new AdempiereException(Msg.getMsg (entity.getCtx(), "EXPUnknownLineType"));
		}
	}
}
