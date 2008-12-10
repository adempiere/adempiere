package org.compiere.swing;

import java.util.Map;

import javax.swing.plaf.ColorUIResource;

public interface ExtendedTheme {

	public ColorUIResource getWhite();
	public ColorUIResource getBlack();
	public ColorUIResource getErrorForeground();
	public ColorUIResource getErrorBackground();
	public ColorUIResource getInactiveBackground();
	public ColorUIResource getMandatoryBackground();
	public ColorUIResource getInfoBackground();
	
	public final static ColorUIResource DEFAULT_MANDATORY_BG = new ColorUIResource(224, 224, 255); //  blue-isch
	public final static ColorUIResource DEFAULT_ERROR_BG = new ColorUIResource(255, 204, 204); //  red-isch
	public final static ColorUIResource DEFAULT_ERROR_FG = new ColorUIResource(204, 0, 0);     //  dark red
	public final static ColorUIResource DEFAULT_INACTIVE_BG = new ColorUIResource(234, 234, 234);	//	light gray
	public final static ColorUIResource DEFAULT_INFO_BG = new ColorUIResource(253, 237, 207);	//	light yellow
	
	public void setUIProperties(Map propertyMap);
	
	public final static String ERROR_BG_KEY = "TextField.errorBackground";
	public final static String ERROR_FG_KEY = "TextField.errorForeground";
	public final static String MANDATORY_BG_KEY = "TextField.mandatoryBackground";
	public final static String INACTIVE_BG_KEY = "TextField.inactiveBackground";
	public final static String INFO_BG_KEY = "Info.background";
}
