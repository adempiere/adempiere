package de.metas.adempiere.form;

/**
 * It's the Client User Interface in a particular user session.
 * 
 * You can pass instances of this interface to non-UI working-threads and all calls will go to Desktop used in construction time.
 * 
 * Example:
 * <ul>
 * <li>for Swing we will have only one client instance because Swing runs desktop client side
 * <li>in a web based solution each user has it's own Session and Desktop. So an IClientUIInstance is bound to a particular desktop
 * </ul>
 * 
 * @author tsa
 * 
 */
public interface IClientUIInstance
{
	void info(int WindowNo, String AD_Message);

	void info(int WindowNo, String AD_Message, String message);

	/**
	 * create dialog window
	 * 
	 * @param WindowNo
	 * @param AD_Message
	 * @return
	 */
	boolean ask(int WindowNo, String AD_Message);

	boolean ask(int WindowNo, String AD_Message, String message);

	void warn(int WindowNo, String AD_Message);

	void warn(int WindowNo, String AD_Message, String message);

	void warn(int WindowNo, Throwable e);

	void error(int WIndowNo, String AD_Message);

	void error(int WIndowNo, String AD_Message, String message);

	/**
	 * Trigger a download of given data
	 * 
	 * @param data
	 * @param contentType
	 * @param filename
	 */
	void download(byte[] data, String contentType, String filename);

	void invokeLater(int windowNo, Runnable runnable);

	/**
	 * Creates user thread. An user thread is a thread which will be interrupted on user logout.
	 * 
	 * @param runnable
	 * @param threadName
	 * @return
	 */
	Thread createUserThread(final Runnable runnable, final String threadName);
}
