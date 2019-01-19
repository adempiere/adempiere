/**
 * 
 */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.CCache;

/**
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *			<li> FR [ 402 ] Mail setup is hardcoded
 *			@see https://github.com/adempiere/adempiere/issues/402
 */
public class MEMailConfig extends X_AD_EMailConfig {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5216582487415235887L;

	/**
	 * @param ctx
	 * @param AD_EMailConfig_ID
	 * @param trxName
	 */
	public MEMailConfig(Properties ctx, int AD_EMailConfig_ID, String trxName) {
		super(ctx, AD_EMailConfig_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEMailConfig(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**	Cache						*/
	private static CCache<Integer, MEMailConfig>	s_cache = new CCache<Integer, MEMailConfig>("AD_EMailConfig", 3);
	
	/**	Default Port					*/
	public static final int			DEFAULT_SMTP_PORT 		= 25;
	public static final int			DEFAULT_SMTP_SSL_PORT 	= 587;
	public static final int			DEFAULT_POP3_PORT 		= 110;
	public static final int			DEFAULT_POP3_SSL_PORT 	= 995;
	public static final int			DEFAULT_IMAP_PORT 		= 143;
	public static final int			DEFAULT_IMAP_SSL_PORT 	= 993;
	
	/**
	 * 	Get EMail Configuration
	 *	@param ctx context
	 * 	@param AD_EMailConfig_ID id
	 *	@return eMailConfig
	 */
	public static MEMailConfig get (Properties ctx, int AD_EMailConfig_ID) {
		Integer key = new Integer (AD_EMailConfig_ID);
		MEMailConfig eMailConfig = (MEMailConfig) s_cache.get(key);
		if (eMailConfig != null)
			return eMailConfig;
		eMailConfig = new MEMailConfig (ctx, AD_EMailConfig_ID, null);
		s_cache.put (key, eMailConfig);
		return eMailConfig;
	}	//	get
	
}
