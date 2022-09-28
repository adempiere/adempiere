/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2015 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpya.com                                  *
 *****************************************************************************/
package org.spin.queue.util;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.stream.IntStream;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.process.ProcessInfo;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.compiere.util.Util;
import org.eevolution.services.dsl.ProcessBuilder;
import org.spin.queue.model.MADQueueType;
import org.spin.queue.process.FlushSystemQueue;

/**
 * @author Yamel Senih, ySenih@erpya.com, ERPCyA http://www.erpya.com
 * Queue handler
 */
public class QueueLoader {
	
    private static QueueLoader queueLoader = null;
    
    /**	Token Generator	*/
    private Map<Integer, QueueManager> queueMap = null;
    
	private static final CLogger logger = CLogger.getCLogger(QueueLoader.class);
	
	/**
	 * Singleton
	 * @return
	 */
    public static QueueLoader getInstance() {
    	if(queueLoader == null) {
    		queueLoader = new QueueLoader();
    	}
    	return queueLoader;
    }
    
    /**
     * Instance hash map
     */
    private QueueLoader() {
    	queueMap = new HashMap<Integer, QueueManager>();
    }
    
    public QueueManager getQueueManager(String queueType) {
    	MADQueueType type = MADQueueType.getByQueueType(Env.getCtx(), queueType, null);
    	if(type == null) {
    		throw new AdempiereException("@AD_QueueType_ID@ @NotFound@");
    	}
    	//	
    	return getQueueManager(type.getAD_QueueType_ID());
    }
    
    /**
     * Get Database
     * @return
     * @throws Exception
     */
    public QueueManager getQueueManager(int queueTypeId) {
        if(!queueMap.containsKey(queueTypeId)) {
            loadClass(queueTypeId);
        }
        //  Default return
        QueueManager manager = queueMap.get(queueTypeId);
        manager.clear().withQueueTypeId(queueTypeId);
        return manager;
    }
    
    /**
     * Get class name for instance
     * @param queueTypeId
     * @return
     */
    private String getClassname(int queueTypeId) {
    	MADQueueType definition = MADQueueType.getById(Env.getCtx(), queueTypeId, null);
    	if(definition == null) {
    		return null;
    	}
    	//	Default
    	return definition.getClassname();
    }
    
    /**
     * Get Class from device type, used for handler
     * @param queueTypeId
     * @return
     * @return Class<?>
     */
    private Class<?> getHandlerClass(int queueTypeId) {
        String className = getClassname(queueTypeId);
        //	Validate null values
        if(Util.isEmpty(className)) {
        	className = QueueManager.class.getName();
        }
        try {
            Class<?> clazz = Class.forName(className);
            if(QueueManager.class.isAssignableFrom(clazz)) {
                return clazz;
            }
            //	Make sure that it is a PO class
            Class<?> superClazz = clazz.getSuperclass();
            //	Validate super class
            while (superClazz != null) {
                if (superClazz == QueueManager.class) {
                	logger.log(Level.SEVERE, "Error loading class, Use: " + className);
                    return clazz;
                }
                //	Get Super Class
                superClazz = superClazz.getSuperclass();
            }
        } catch (Exception e) {
        	logger.log(Level.SEVERE, "Loading class Error"+ e.getMessage());
        }
        //
        logger.log(Level.SEVERE,"Not found Class: " + className);
        return null;
    }	//	getHandlerClass

    /**
     * Load class for export
     * @param queueTypeId
     * @throws Exception
     */
    private void loadClass(int queueTypeId) {
    	try {
            //	Load it
            //	Get class from parent
            Class<?> clazz = getHandlerClass(queueTypeId);
            QueueManager generator = null;
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            //	new instance
            generator = (QueueManager) constructor.newInstance();
            generator.withQueueTypeId(queueTypeId);
            queueMap.put(queueTypeId, generator);
    	} catch (Exception e) {
    		throw new AdempiereException(e);
    	}
    }
    
    /**
     * Default
     * @param args
     */
    public static void main(String[] args) {
    	org.compiere.Adempiere.startup(true);
		Env.setContext(Env.getCtx(), "#AD_Client_ID", 11);
		Trx.run(transactionName -> {
			//	add to queue
			IntStream.range(0, 300).forEach(queueRecord -> {
				QueueLoader.getInstance().getQueueManager(MADQueueType.QUEUETYPE_QueueTestLoader)
					.withContext(Env.getCtx())
					.withTransactionName(transactionName)
					.withDescription("Hello: " + queueRecord)
					.addToQueue();
			});
		});
		Trx.run(transactionName -> {
			//	add to queue
			ProcessInfo result = ProcessBuilder.create(Env.getCtx())
				.process(FlushSystemQueue.getProcessId())
				.withParameter(FlushSystemQueue.BATCHSTOPROCESS, 10)
				.withParameter(FlushSystemQueue.RECORDSBYBATCH, 100)
				.withParameter(FlushSystemQueue.ISDELETEAFTERPROCESS, false)
				.execute();
			//	
			logger.warning(result.getSummary());
		});
	}
}
