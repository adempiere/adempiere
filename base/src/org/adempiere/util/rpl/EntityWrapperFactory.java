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
 * along with this program.	If not, see <https://www.gnu.org/licenses/>.        *
 ************************************************************************************/
package org.adempiere.util.rpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

import javax.xml.xpath.XPathExpressionException;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.process.rpl.exp.WrapperUtil;
import org.compiere.model.I_AD_Image;
import org.compiere.model.I_C_Order;
import org.compiere.model.MClient;
import org.compiere.model.MColumn;
import org.compiere.model.MEXPFormat;
import org.compiere.model.MEXPFormatLine;
import org.compiere.model.MImage;
import org.compiere.model.MOrder;
import org.compiere.model.MReplicationStrategy;
import org.compiere.model.MTable;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.model.X_AD_ReplicationDocument;
import org.compiere.model.X_AD_ReplicationTable;
import org.compiere.model.X_EXP_FormatLine;
import org.compiere.process.DocAction;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
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
    /**	Transaction Name	*/
    private String transactionName;
    /**	Context	*/
    private Properties context;
    
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
     * Get Context from client
     * @return
     */
    private Properties getContext() {
    	return context;
    }
    
    /**
     * Get transaction name
     * @return
     */
    private String getTransactionName() {
    	return transactionName;
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
		if(entity == null) {
			throw new AdempiereException("@Entity@ @NotFound@");
		}
		this.entity = entity;
		context = entity.getCtx();
		transactionName = entity.get_TrxName();
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
		if(wrapper == null) {
			throw new AdempiereException("@Wrapper@ @NotFound@");
		}
		this.wrapper = wrapper;
		withReplicationEvent(wrapper.getReplicationEvent());
		withReplicationMode(wrapper.getReplicationMode());
		withReplicationType(wrapper.getReplicationType());
		context = wrapper.getContext();
		transactionName = wrapper.getTransactionName();
		return this;
	}
	
	/**
	 * Get Client
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
		wrapper.setAttributes(exportFormat, client, replicationMode, replicationType, replicationEvent);
		return getWrapper();
	}
	
	/**
	 * Build PO entity from wrapper
	 * This can validate if exist a wrapper, client and export format
	 * @return
	 */
	public EntityWrapperFactory buildEntity() {
		//	Validate before process
		if(client == null) {
			throw new AdempiereException("@AD_Client_ID@ @NotFound@");
		}
		if(exportFormat == null || exportFormat.getEXP_Format_ID() == 0) {
			throw new AdempiereException("@EXP_Format_ID@ @NotFound@");
		}
		if(wrapper == null || Util.isEmpty(wrapper.getUuid())) {
			throw new AdempiereException("@Wrapper0@ @NotFound@");
		}
		//	Build it
		entity = buildEntity(exportFormat, wrapper, getTransactionName());
		//	
		return this;
	}
	
	/**
	 * Save Entity wrapper
	 */
	public EntityWrapperFactory saveEntity() {
		if (ModelValidator.TYPE_BEFORE_DELETE == replicationEvent
				|| ModelValidator.TYPE_BEFORE_DELETE_REPLICATION == replicationEvent
				|| ModelValidator.TYPE_DELETE == replicationEvent)
		;
		if(getEntity() == null) {
			return this;
		}
		else if (!entity.is_Changed()) {
		    log.info("Object not changed = " + entity.toString());
		    return this;
		}
		Env.setContext(entity.getCtx(), "#AD_Client_ID", entity.getAD_Client_ID());

		if (MReplicationStrategy.REPLICATION_TABLE == replicationMode) {
			// Here must invoke other method else we get cycle...
			if (ModelValidator.TYPE_BEFORE_DELETE == replicationEvent
				||	ModelValidator.TYPE_BEFORE_DELETE_REPLICATION == replicationEvent
				||	ModelValidator.TYPE_DELETE == replicationEvent) {
				entity.deleteEx(true);
			}
			else {
				if (X_AD_ReplicationTable.REPLICATIONTYPE_Broadcast.equals(replicationType)) {
					MReplicationStrategy rplStrategy = new MReplicationStrategy(client.getCtx(), client.getAD_ReplicationStrategy_ID(), entity.get_TrxName());
					WrapperUtil.exportRecord(entity, 
							client, 
							MReplicationStrategy.REPLICATION_TABLE,
							X_AD_ReplicationTable.REPLICATIONTYPE_Merge,
							ModelValidator.TYPE_AFTER_CHANGE, 
							rplStrategy, 
							exportFormat);
					entity.saveReplica(true);
				} else if (X_AD_ReplicationTable.REPLICATIONTYPE_Merge.equals(replicationType)
					||  X_AD_ReplicationTable.REPLICATIONTYPE_Reference.equals(replicationType)) {
					entity.saveReplica(true);
				} else if (X_AD_ReplicationTable.REPLICATIONTYPE_Local.equals(replicationType)) {
					//Do nothing??	
				} else {
					// Replication Type is not one of the possible values...ERROR
					throw new AdempiereException("@EXPReplicationTypeNonValidType@");
				}
			}	
		} else if (MReplicationStrategy.REPLICATION_DOCUMENT == replicationMode
				&& X_AD_ReplicationDocument.REPLICATIONTYPE_Merge.equals(replicationType)
				&& entity instanceof DocAction) {
			DocAction document = (DocAction) entity;
			String action = document.getDocAction();
			String status = document.getDocStatus();
			log.info("Document:"+document.toString() + " DocStauts:" + status + " DocAction:"+action);

			if (ModelValidator.TIMING_AFTER_REVERSECORRECT==replicationEvent) {
				if(status.equals(DocAction.STATUS_Reversed) && action.equals(DocAction.ACTION_None)) {
					entity.saveEx();
					return this;
				}
			}	 
			
			if (((action.equals(DocAction.ACTION_Prepare) || action.equals(DocAction.ACTION_Complete)) 
					&& (!status.equals(DocAction.STATUS_Completed) && !status.equals(DocAction.STATUS_Closed) && !status.equals(DocAction.STATUS_Voided) && !status.equals(DocAction.STATUS_Reversed)))
				||  (action.equals(DocAction.ACTION_Close) && status.equals(DocAction.STATUS_Completed) && !status.equals(DocAction.STATUS_Voided) && !status.equals(DocAction.STATUS_Reversed))) {
				try {
					if (!document.processIt(action)) {
						log.info("PO.toString() = can not " + entity.get_Value("DocAction"));
					}
					entity.saveEx();
				} catch (Exception e) {
					throw new AdempiereException(e.getLocalizedMessage());
				}
			}
			else {
				entity.saveEx();
			}
		}
		return this;
	}
	
	/**
	 * Build Entity based on wrapper, client and export format
	 */
	private PO findEntityFromWrapper(MEXPFormat exportFormat, EntityWrapper wrapper) {
		List<PO> values = null;
		log.info("expFormat = " + exportFormat);
		// Get list with all Unique columns!
		try {
			Collection<MEXPFormatLine> uniqueFormatLines = exportFormat.getUniqueColumns();
			if (uniqueFormatLines == null || uniqueFormatLines.size() < 1) {
				throw new AdempiereException("@EXPFormatLineNoUniqueColumns@");
			}
			AtomicInteger replicationId = new AtomicInteger(0);
			List<Object> parameters = new ArrayList<Object>();
			StringBuffer whereClause = new StringBuffer("");
			uniqueFormatLines.forEach(uniqueFormatLine -> {
				MColumn column = MColumn.get(getContext(), uniqueFormatLine.getAD_Column_ID());
				log.info("UNIQUE column = ["+column.getAD_Table().getTableName()+"."+column.getColumnName()+"]");
				String columnName = column.getColumnName();
				String key = uniqueFormatLine.getValue();
				String type = uniqueFormatLine.getType();
				Object value = null;
				if (MEXPFormatLine.TYPE_XMLElement.equals(type)
						|| MEXPFormatLine.TYPE_XMLAttribute.equals(type)) {
					// XML Element
					value = wrapper.getValueAsObject(key);
					log.info("value=" + value);
				} else if (MEXPFormatLine.TYPE_ReferencedEXPFormat.equals(type)) {
					// Referenced Export Format
					log.info("referencedExpFormat.EXP_EmbeddedFormat_ID = " + uniqueFormatLine.getEXP_EmbeddedFormat_ID());
					//get from cache
					MEXPFormat referencedExpFormat = MEXPFormat.get(getContext(), uniqueFormatLine.getEXP_EmbeddedFormat_ID(), getTransactionName());
					log.info("referencedExpFormat = " + referencedExpFormat);
					//	
					EntityWrapper referencedWrapper = wrapper.getReference(key);
					if(referencedWrapper == null) {
						throw new AdempiereException("@ReferencedRecordNotFound@");
					}
					PO childEntity = findEntityFromWrapper(referencedExpFormat, referencedWrapper);
					// If ReferencedExport format has checkbox "CreateNonExisting" marked then NON existing record MUST be created!!!
					// Add new column to EXP_FormatLine "IsCreateNonExisting"
					boolean createNonExistingRecords = uniqueFormatLine.isCreateNonExisting();
					if (createNonExistingRecords 
							&& (childEntity == null || childEntity.get_ID() <= 0)) {
						//	TODO: To be defined
						PO entityToCreate = buildEntity(referencedExpFormat, referencedWrapper, getTransactionName());
						log.info("Embeded - entityToCreate = " + entityToCreate);
						if (!entityToCreate.is_Changed()) {
						    log.info("Object not changed = " + entityToCreate.toString());
						} else {	
							entityToCreate.saveReplica(true);
						}
					}
					value = childEntity.get_ID();
				} else {
					// Export Format Line is not one of two possible values...ERROR
					throw new AdempiereException("@EXPFormatLineNonValidType@");
				}
				//	Get ID
				if(!Optional.ofNullable(value).isPresent()) {
					if(DisplayType.isID(column.getAD_Reference_ID()) || DisplayType.Integer == column.getAD_Reference_ID()) {
						if (wrapper.getValueAsInt(key) > 0) {
							//double doubleValue = Double.parseDouble(value.toString());
							if (DisplayType.ID == column.getAD_Reference_ID()) {
								replicationId.set(wrapper.getValueAsInt(key));
							}
						} else {
							value = null;
						}
					} else if(value == null){	
						value = wrapper.getValueAsObject(key);
					}
				}
				//	
				if (parameters.size() == 0) {
					whereClause.append(" ").append(columnName).append(" = ? ");
				} else {
					whereClause.append(" AND ").append(columnName).append(" = ? ");
				}
				parameters.add(value);
			});
			//	
			values = new Query(getContext(), MTable.get(getContext(), exportFormat.getAD_Table_ID()), whereClause.toString(), getTransactionName())
					.setParameters(parameters)
					.list();
			
			if (values.size() > 1) {	//	The Return Object must be always one
				throw new AdempiereException("@EXPFormatLineNoUniqueColumns@ : " + exportFormat.getName());
			}
			//	Validate values
			if (values.size() <= 0) {	//	Means that is a new record
				PO po = MTable.get(getContext(), exportFormat.getAD_Table_ID()).getPO(0, getTransactionName());
				if (replicationId.get() > 0 ) {
					po.set_CustomColumn(po.get_KeyColumns()[0], replicationId.get());
				}
				return po;
			}
			//`
			return values.get(0);	//Return the first (unique) record.
		} catch (Exception e) {
			throw new AdempiereException(e.getLocalizedMessage());
		}
	}
	
	/**
	 *
	 * @param ctx
	 * @param result
	 * @param rootElement
	 * @param expFormat
	 * @param replicationType
	 * @param trxName
	 * @return
	 * @throws Exception
	 * @throws XPathExpressionException
	 */
	private PO buildEntity(MEXPFormat expFormat, EntityWrapper wrapper, String trxName) {
		PO entity = findEntityFromWrapper(expFormat, wrapper);
		//	Getting the Object for the replicate
		if (entity == null) {
			throw new AdempiereException("@UnableToLoadEntity@");
		}
		// If this is just for push and exists we do nothing
		if (X_AD_ReplicationTable.REPLICATIONTYPE_Reference.equals(replicationType)) {
			if (entity.get_ID() == 0) {
				return null;
			}
		}
		log.info("PO.toString() = " + entity.toString());
		
		if (entity.get_KeyColumns().length < 1) {
			throw new AdempiereException("@EDIMultiColumnNotSupported@");
		}
		//	
		Collection<MEXPFormatLine> formatLines = expFormat.getFormatLinesOrderedBy(MEXPFormatLine.COLUMNNAME_IsMandatory + " , " + MEXPFormatLine.COLUMNNAME_Position);
		if (formatLines == null || formatLines.size() < 1)  {
			throw new AdempiereException("@EXPFormatNoLines@");
		}
		// Iterate all Export Format Lines (even this marked as part of unique index)
		//  and set value of column!
		for (MEXPFormatLine formatLine : formatLines) {
			log.info("=================== Beginnig of Format Line ===============================");
			log.info("formatLine: [" + formatLine.toString() + "]");			
			//Set the value
			setReplicaValues(formatLine, wrapper, entity);
		}
		return entity;
	}
	
	/**
	 * Set value on entity as replication
	 * @param formatLine
	 * @param entity
	 * @throws Exception
	 */
	private void setReplicaValues(MEXPFormatLine formatLine, EntityWrapper wrapper, PO entity) {
		Object value = getValueFromFormat(formatLine, entity, wrapper);
		if (value == null) { // @Trifon - If XML document contains XML element then even if value is EMPTY we will update the DB record! - OLD: || value.toString().equals(""))
			return;
		}
		MColumn column = MColumn.get(getContext(), formatLine.getAD_Column_ID());
		log.info("column=[" + column + "]");

		if (value != null) {
			if (!MEXPFormatLine.TYPE_EmbeddedEXPFormat.equals(formatLine.getType())) {
				// Clazz
				Class<?> clazz = DisplayType.getClass(column.getAD_Reference_ID(), true);

				//	Handle Posted
				if (   column.getColumnName().equalsIgnoreCase("Posted") 
					|| column.getColumnName().equalsIgnoreCase("Processed")
					|| column.getColumnName().equalsIgnoreCase("Processing")) {
					clazz = Boolean.class;
				} else if (column.getColumnName().equalsIgnoreCase("Record_ID")) {
					clazz = Integer.class;
				} else if (column.getColumnName().equalsIgnoreCase("AD_Language")
						|| column.getColumnName().equalsIgnoreCase("EntityType")) {
					clazz = String.class;
				}	
				log.info("clazz = " + clazz.getName());
				
				// Handle Date and Time
				log.info("formatLinesType = " + formatLine.getType());
				if (MEXPFormatLine.TYPE_EmbeddedEXPFormat.equals( formatLine.getType())) {
					// DO NOTHING
					throw new AdempiereException("@WeCantBeHere@");
				} else {
					if ( DisplayType.DateTime == column.getAD_Reference_ID() 
					  || DisplayType.Date == column.getAD_Reference_ID()) {
						// 
						entity.set_ValueOfColumn(formatLine.getAD_Column_ID(), value);
						log.info("Set value of column ["+column.getColumnName()+"]=["+value+"]");
					} 
					else if (  DisplayType.isID(column.getAD_Reference_ID())
							|| DisplayType.Integer == column.getAD_Reference_ID()) {
						//
						if (!Util.isEmpty(value.toString())) {
							int intValue = Integer.parseInt(value.toString());
							value = Integer.valueOf(intValue);
						} else {
							value = null;
						}

						log.info("About to set int value of column ["+column.getColumnName()+"]=["+value+"]");
						entity.set_ValueOfColumn(formatLine.getAD_Column_ID(), value);
						log.info("Set int value of column ["+column.getColumnName()+"]=["+value+"]");
					} else if ( DisplayType.isNumeric(column.getAD_Reference_ID())	
							&& column.getAD_Reference_ID() != DisplayType.Integer) {
						//
						if (!Util.isEmpty(value.toString())) {
							value = new BigDecimal(value.toString());
							//value = new Double( doubleValue );
						} else {
							value = null;
						}
						
						log.info("About to set BigDecimal value of column ["+column.getColumnName()+"]=["+value+"]");
						entity.set_ValueOfColumn(formatLine.getAD_Column_ID(), value);
						log.info("Set BigDecimal value of column ["+column.getColumnName()+"]=["+value+"]");
					} else if(DisplayType.YesNo == column.getAD_Reference_ID()) {
						if (clazz == Boolean.class) {						 
							String v = value.toString().equals("true") ? "Y" : "N";	
							entity.set_ValueOfColumn(formatLine.getAD_Column_ID(), v);
						}
					} else {
						//
						try {
							log.info("About to set value of column ["+column.getColumnName()+"]=["+value+"]");

							if (clazz == Boolean.class) {
								String v = value.toString().equals("true") ? "Y" : "N";
								entity.set_ValueOfColumn(formatLine.getAD_Column_ID(), v);
							} else {
								entity.set_ValueOfColumn(formatLine.getAD_Column_ID(), clazz.cast(value));
							}
							log.info("Set value of column ["+column.getColumnName()+"]=["+value+"]");
						} catch (ClassCastException ex) {
							ex.printStackTrace();
							throw new AdempiereException(ex);
						}
					}
				}//end else			
			}//end if TYPE_EmbeddedEXPFormat			
		}//end if value !=null
	}
	
	/**
	 * Get the value from format
	 * @param line
	 * @param entity
	 * @param wrapper
	 * @return
	 * @throws Exception
	 */
	private Object getValueFromFormat(MEXPFormatLine line, PO entity, EntityWrapper wrapper) {
		Object value = null;
		String key = line.getValue();
		String type = line.getType();
		if (MEXPFormatLine.TYPE_XMLElement.equals(type)
				|| MEXPFormatLine.TYPE_XMLAttribute.equals(type)) {
			// XML Element
			value = wrapper.getValueAsObject(key);
			log.info("value=[" + value + "]");
		} else if (MEXPFormatLine.TYPE_ReferencedEXPFormat.equals(type)) {
			// Referenced Export Format. Get it from cache
			MEXPFormat referencedExpFormat = MEXPFormat.get(getContext(), line.getEXP_EmbeddedFormat_ID(), getTransactionName());
			log.info("referencedExpFormat = " + referencedExpFormat);
			// Find Record_ID by ???Value??? In fact by Columns set as Part Of Unique Index in Export Format!
			log.info("Seach for XML Element = " + key);
			EntityWrapper referencedWrapper = wrapper.getReference(key);
			log.info("referencedNode = " + referencedWrapper);
			if (referencedWrapper != null) {
				PO embeddedEntity = findEntityFromWrapper(referencedExpFormat, referencedWrapper);
				if(embeddedEntity != null
						&& embeddedEntity.get_ID() > 0) {
					value = embeddedEntity.get_ID();
				}
				log.info("referenced Record_ID = " + value);
			} else {
				log.info("NULL VALUE FOR " + key);
				value = null;
			}
			log.info("value=[" + value + "]");
		} else if (MEXPFormatLine.TYPE_EmbeddedEXPFormat.equals(line.getType())) {
			if (entity.is_Changed()) {
				if(entity instanceof DocAction) {
					entity.set_ValueOfColumn(I_C_Order.COLUMNNAME_DocStatus, MOrder.DOCSTATUS_Drafted);
					entity.set_ValueOfColumn(I_C_Order.COLUMNNAME_Processing, false);
					entity.set_ValueOfColumn(I_C_Order.COLUMNNAME_Processed, false);
				}
				entity.saveReplica(true);
			} else {
				return value;
			}
			// Embedded Export Format It is used for Parent-Son records like Order&OrderLine
			//get from cache
			MEXPFormat referencedExpFormat = MEXPFormat.get(getContext(), line.getEXP_EmbeddedFormat_ID(), getTransactionName());
			log.info("embeddedExpFormat = " + referencedExpFormat);

			// Skip View tables!
			if (referencedExpFormat.getAD_Table().isView() ) {
				// SKIP
			} else {
				List<EntityWrapper> embeddedList = wrapper.getChildren(key);
				if(embeddedList != null) {
					embeddedList.forEach(embeddedWrapper -> {
						PO embeddedEntity = buildEntity(referencedExpFormat, embeddedWrapper, getTransactionName());
						log.info("embeddedPo = " + embeddedEntity);
						if (!embeddedEntity.is_Changed()) {
						    log.info("Object not changed = " + entity.toString());
						} else {	
							embeddedEntity.saveReplica(true);
						}
					});
				}
			}
		} else {
			// Export Format Line is not one of the possible values...ERROR
			throw new AdempiereException("@EXPFormatLineNonValidType@");
		}
		return value;
	}
	
	/**
	 * Create wrapper
	 * @param exportFormat
	 * @param entity
	 * @return wrapper
	 */
	private EntityWrapper createWrapper(MEXPFormat exportFormat, PO entity) {
		EntityWrapper wrapperToCreate = new EntityWrapper();
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
			MEXPFormat embeddedFormat = MEXPFormat.get(entity.getCtx(), exportFormatLine.getEXP_EmbeddedFormat_ID(), getTransactionName());
			MTable tableEmbedded = MTable.get(entity.getCtx(), embeddedFormat.getAD_Table_ID());
			log.info("Table Embedded = " + tableEmbedded);
			
			final StringBuffer whereClause = new StringBuffer(entity.get_KeyColumns()[0] +"=?");

			if (embeddedFormat.getWhereClause() != null & !"".equals(embeddedFormat.getWhereClause()))  {
			    whereClause.append(" AND ").append(embeddedFormat.getWhereClause());
			}
			new Query(entity.getCtx(), tableEmbedded.getTableName(), whereClause.toString(),
					getTransactionName())
				.setApplyAccessFilter(true)
				.setParameters(new Object[] { entity.get_ID() })
				.list()
				.forEach(embeddedEntity -> {
					EntityWrapper child = createWrapper(embeddedFormat, embeddedEntity);
					wrapper.addChild(exportFormatLine.getValue(), child);
				});
		} else if(exportFormatLine.getType().equals(X_EXP_FormatLine.TYPE_ReferencedEXPFormat)) {
			// process Referenced Export Format

			int embeddedFormatId = exportFormatLine.getEXP_EmbeddedFormat_ID();
			//get from cache
			MEXPFormat referencedFormat =  MEXPFormat.get(entity.getCtx(), embeddedFormatId, getTransactionName());
			
			MTable tableEmbedded = MTable.get(entity.getCtx(), referencedFormat.getAD_Table_ID());
			log.info("Table Embedded = " + tableEmbedded);

			final StringBuffer whereClause = new StringBuffer(tableEmbedded.getTableName() + "_ID =?");
			if (referencedFormat.getWhereClause() != null & !"".equals(referencedFormat.getWhereClause()))  {
			    whereClause.append(" AND ").append(referencedFormat.getWhereClause());
			}
			
			String columnName = "";
			if(exportFormatLine.getAD_Column_ID() != 0) {
				MColumn column = MColumn.get(entity.getCtx(), exportFormatLine.getAD_Column_ID());
				if(column.getAD_Reference_ID() == DisplayType.Table || column.getAD_Reference_ID() == DisplayType.Search) {
					columnName = column.getColumnName();
				} else {
					columnName = tableEmbedded.getTableName() + "_ID";
				}
			} else {
				columnName = tableEmbedded.getTableName() + "_ID";
			}

			Object value = entity.get_Value(columnName);
			if (value != null) {
				Collection<PO> instances = null;
				MTable tableForGet = MTable.get(getContext(), tableEmbedded.getTableName());
				if(tableForGet.getAccessLevel().equals(MTable.ACCESSLEVEL_All)
						|| tableForGet.getAccessLevel().equals(MTable.ACCESSLEVEL_SystemPlusClient)) {
					// SYSTEM records
					instances = new Query(entity.getCtx(), tableEmbedded.getTableName(), whereClause.toString(), getTransactionName())
						.setParameters(value)
						.list();
					// Tenant specific records
					Collection<PO> tenantSpecificInstances = new Query(entity.getCtx(), tableEmbedded.getTableName(), whereClause.toString(), getTransactionName())
						//.setClient_ID()
						.setApplyAccessFilter(true)
						.setParameters(value)
						.list();

					instances.addAll(tenantSpecificInstances);
				} else {
					instances = new Query(entity.getCtx(), tableEmbedded.getTableName(), whereClause.toString(), getTransactionName())
						.setApplyAccessFilter(true)
						.setParameters(value)
						.list();
				}
				//	Iterate
				if(instances.size() > 0) {
					EntityWrapper reference = createWrapper(referencedFormat, instances.stream().findFirst().get());
					wrapper.setReference(exportFormatLine.getValue(), reference);
				}
			}
		} else {
			throw new AdempiereException(Msg.getMsg (entity.getCtx(), "EXPUnknownLineType"));
		}
	}
}
