/*************************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                              *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, C.A.                      *
 * Contributor(s): Yamel Senih ysenih@erpya.com                                      *
 * This program is free software: you can redistribute it and/or modify              *
 * it under the terms of the GNU General Public License as published by              *
 * the Free Software Foundation, either version 3 of the License, or                 *
 * (at your option) any later version.                                               *
 * This program is distributed in the hope that it will be useful,                   *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of                    *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the                     *
 * GNU General Public License for more details.                                      *
 * You should have received a copy of the GNU General Public License                 *
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.            *
 ************************************************************************************/
package org.spin.store.util.support.elasticsearch;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.adempiere.exceptions.AdempiereException;
import org.apache.http.HttpHost;
import org.compiere.model.MStore;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Util;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.spin.model.MADAppRegistration;
import org.spin.store.support.IExternalCache;
import org.spin.util.support.IAppSupport;

/**
 * 	Util class for some helper methods
 * 	@author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 */
public class ElasticSearch implements IExternalCache, IAppSupport {
	/**	Host name	*/
	private String host = "localhost";
	/**	Port	*/
	private int port = 9200;
	/**	Schema	*/
	private String schema = "http";
	/**	Client	*/
	private RestHighLevelClient client;
	/** Logger */
	private static CLogger log = CLogger.getCLogger(ElasticSearch.class);
	/**	Date format	*/
	public final static String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
	/**	Registration Id	*/
	private int registrationId = 0;
	/**	Schema	*/
	private final String SCHEMA_KEY = "SCHEMA";
	/** Web Store	*/
	private MStore webStore;
	
	/**
	 * Convert date
	 * @param millis
	 * @return
	 */
	public static String convertedDate(long millis) {
		return new SimpleDateFormat(DATE_FORMAT).format(millis);
	}
	
	/**
	 * Validate connection
	 */
	private void validate() {
		if(getAppRegistrationId() <= 0) {
			throw new AdempiereException("@AD_AppRegistration_ID@ @NotFound@");
		}
		MADAppRegistration registration = MADAppRegistration.getById(Env.getCtx(), getAppRegistrationId(), null);
		withHost(registration.getHost());
		withPort(registration.getPort());
		withSchema(registration.getParameterValue(SCHEMA_KEY));
		//	Host
		if(Util.isEmpty(getHost())) {
			throw new AdempiereException("@Host@ @NotFound@");
		}
		//	Schema
		if(Util.isEmpty(getSchema())) {
			throw new AdempiereException("@Schema@ @NotFound@");
		}
		//	Port
		if(port <= 0) {
			throw new AdempiereException("@Port@ @NotFound@");
		}
	}
	
	/**
	 * Connect with cluster
	 * @return
	 */
	public ElasticSearch connect() throws IOException {
		if(client != null) {
			return this;
		}
		//	Validate host, Port, Schema
		validate();
		client = new RestHighLevelClient(
		        RestClient.builder(
		                new HttpHost(host, port, schema)));
		//	
		return this;
	}
	
	/**
	 * Close connection
	 * @throws IOException 
	 */
	public ElasticSearch close() throws IOException {
		if(client == null) {
			return this;
		}
		client.close();
		client = null;
		return this;
	}

	/**
	 * @return the host
	 */
	public final String getHost() {
		return host;
	}

	/**
	 * @param host the host to set
	 */
	public final ElasticSearch withHost(String host) {
		this.host = host;
		return this;
	}

	/**
	 * @return the port
	 */
	public final int getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public final ElasticSearch withPort(int port) {
		this.port = port;
		return this;
	}

	/**
	 * @return the schema
	 */
	public final String getSchema() {
		return schema;
	}

	/**
	 * @param schema the schema to set
	 */
	public final ElasticSearch withSchema(String schema) {
		this.schema = schema;
		return this;
	}
	
	/**
	 * Index Product
	 * @param entity
	 * @throws IOException 
	 */
	public ElasticSearch index(IPersistenceWrapper entity) throws IOException {
		if(Optional.ofNullable(entity).isPresent()
				&& entity.isValid()) {
			if(exist(entity)) {
				return update(entity);
			}
			try {
				GetIndexRequest getRequest = new GetIndexRequest(getCatalogName(entity.getCatalogName()));
				if(!client.indices().exists(getRequest, RequestOptions.DEFAULT)) {
					CreateIndexRequest indexDefinition = new CreateIndexRequest(getCatalogName(entity.getCatalogName()));
					Map<String, Object> mapping = entity.getMapping();
					if(Optional.ofNullable(mapping).isPresent()) {
						indexDefinition.mapping(mapping);
						client.indices().create(indexDefinition, RequestOptions.DEFAULT);
					}
				}
			} catch (Exception e) {
				throw new AdempiereException(e);
			}
			//	Create
			IndexRequest indexRequest = new IndexRequest(getCatalogName(entity.getCatalogName())).id(entity.getKeyValue()).source(entity.getMap());
			indexRequest.opType(DocWriteRequest.OpType.CREATE);
			IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
			if (indexResponse.getResult() == DocWriteResponse.Result.CREATED) {
				log.fine("Entity Created: " + entity.getKeyValue());
			} else if (indexResponse.getResult() == DocWriteResponse.Result.UPDATED) {
				log.fine("Entity Updated: " + entity.getKeyValue());
			}
			ReplicationResponse.ShardInfo shardInfo = indexResponse.getShardInfo();
			if (shardInfo.getTotal() != shardInfo.getSuccessful()) {
				log.fine("Entity Created: " + entity.getKeyValue());
			}
			if (shardInfo.getFailed() > 0) {
			    for (ReplicationResponse.ShardInfo.Failure failure :
			            shardInfo.getFailures()) {
			        String reason = failure.reason();
			        log.warning("Entity Index Error: " + entity.getKeyValue() + " - Reason: " + reason);
			    }
			}
		}
		return this;
	}
	
	/**
	 * Update Product
	 * @param entity
	 * @throws IOException
	 */
	public ElasticSearch update(IPersistenceWrapper entity) throws IOException {
		if(Optional.ofNullable(entity).isPresent()
				&& entity.isValid()) {
			if(!exist(entity)) {
				return index(entity);
			}
			UpdateRequest request = new UpdateRequest(getCatalogName(entity.getCatalogName()), entity.getKeyValue()).doc(entity.getMap());
			UpdateResponse updateResponse = client.update(request, RequestOptions.DEFAULT);
			if (updateResponse.getResult() == DocWriteResponse.Result.CREATED) {
				log.fine("Entity Created: " + entity.getKeyValue());
			} else if (updateResponse.getResult() == DocWriteResponse.Result.UPDATED) {
				log.fine("Entity Updated: " + entity.getKeyValue());
			} else if (updateResponse.getResult() == DocWriteResponse.Result.DELETED) {
				log.fine("Entity Deleted: " + entity.getKeyValue());
			} else if (updateResponse.getResult() == DocWriteResponse.Result.NOOP) {
				log.fine("Entity Noop: " + entity.getKeyValue());
			}
		}
		return this;
	}
	
	/**
	 * Delete Entity
	 * @param entity
	 * @throws IOException
	 */
	public ElasticSearch delete(IPersistenceWrapper entity) throws IOException {
		if(Optional.ofNullable(entity).isPresent()
				&& entity.isValid()
				&& exist(entity)) {
			DeleteRequest request = new DeleteRequest(getCatalogName(entity.getCatalogName()), entity.getKeyValue());
			DeleteResponse deleteResponse = client.delete(request, RequestOptions.DEFAULT);
			ReplicationResponse.ShardInfo shardInfo = deleteResponse.getShardInfo();
			if (shardInfo.getTotal() != shardInfo.getSuccessful()) {
				log.fine("Entity Deleted: " + entity.getKeyValue());
			}
			if (shardInfo.getFailed() > 0) {
			    for (ReplicationResponse.ShardInfo.Failure failure :
			            shardInfo.getFailures()) {
			        String reason = failure.reason(); 
			        log.warning("Entity Delete Error: " + entity.getKeyValue() + " - Reason: " + reason);
			    }
			}
		}
		return this;
	}
	
	/**
	 * Validate if exist a entity
	 * @param entity
	 * @return
	 * @throws IOException
	 */
	public boolean exist(IPersistenceWrapper entity) throws IOException {
		if(Optional.ofNullable(entity).isPresent()
				&& entity.isValid()) {
			GetRequest getRequest = new GetRequest(getCatalogName(entity.getCatalogName()), entity.getKeyValue());
			getRequest.fetchSourceContext(new FetchSourceContext(false)); 
			getRequest.storedFields("_none_");
			return client.exists(getRequest, RequestOptions.DEFAULT);
		}
		//	Return if it exists
		return false;
	}

	@Override
	public String testConnection() {
		validate();
		String message = null;
		try {
			connect();
			message = "Ok: " + exist(new IPersistenceWrapper() {
				
				@Override
				public Map<String, Object> getMap() {
					return new HashMap<String, Object>();
				}
				
				@Override
				public String getKeyValue() {
					return "1";
				}
				
				@Override
				public String getCatalogName() {
					return "product";
				}

				@Override
				public boolean isValid() {
					return true;
				}

				@Override
				public IPersistenceWrapper withWebStoreId(int webStoreId) {
					return null;
				}

				@Override
				public Map<String, Object> getMapping() {
					return null;
				}
			});
		} catch (Exception e) {
			throw new AdempiereException(e.getMessage());
		} finally {
			try {
				close();
			} catch (Exception e) {
				throw new AdempiereException(e.getMessage());
			}
		}
		return message;
	}
	
	/**
	 * Get catalog based on web store and entity catalog
	 * @param entityCatalog
	 * @return
	 */
	private String getCatalogName(String entityCatalog) {
		return (webStore.getWebContext() + "_" + entityCatalog)
				.trim()
				.toLowerCase()
				.replaceAll("[+^:,.&áàäéèëíìïóòöúùñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ$()/]", "");
	}

	@Override
	public void setAppRegistrationId(int registrationId) {
		this.registrationId = registrationId;
	}

	@Override
	public int getAppRegistrationId() {
		return registrationId;
	}

	@Override
	public void withWebStore(MStore webStore) {
		this.webStore = webStore;
	}

	@Override
	public int getCacheId() {
		return getAppRegistrationId();
	}
}
