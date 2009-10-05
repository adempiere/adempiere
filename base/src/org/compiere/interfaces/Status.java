package org.compiere.interfaces;

/**
 * Interface for adempiere/Status.
 */
public interface Status
{
   public final static String JNDI_NAME="adempiere/Status";
   
   public final static String EJB_NAME="adempiereStatus";
   
   /**
    * Get Version (Date)
    * @return version e.g. 2002-09-02    */
   public String getDateVersion(  );

   /**
    * Get Main Version
    * @return main version - e.g. Version 2.4.3b    */
   public String getMainVersion(  );

   /**
    * Get Database Type
    * @return Database Type    */
   public String getDbType(  );

   /**
    * Get Database Host
    * @return Database Host Name    */
   public String getDbHost(  );

   /**
    * Get Database Port
    * @return Database Port    */
   public int getDbPort(  );

   /**
    * Get Database SID
    * @return Database SID    */
   public String getDbName(  );

   /**
    * Get Database URL
    * @return Database URL    */
   public String getConnectionURL(  );

   /**
    * Get Database UID
    * @return Database User Name    */
   public String getDbUid(  );

   /**
    * Get Database PWD
    * @return Database User Password    */
   public String getDbPwd(  );

   /**
    * Get Connection Manager Host
    * @return Connection Manager Host    */
   public String getFwHost(  );

   /**
    * Get Connection Manager Port
    * @return Connection Manager Port    */
   public int getFwPort(  );

   /**
    * Get Version Count
    * @return number of version inquiries    */
   public int getVersionCount(  );

   /**
    * Get Database Count
    * @return number of database inquiries    */
   public int getDatabaseCount(  );

   /**
    * Describes the instance and its content for debugging purpose
    * @return Debugging information about the instance and its content    */
   public String getStatus(  );
}
