import org.compiere.model.MSysConfig;
import org.adempiere.webui.theme.ThemeManager;
var theme = MSysConfig.getValue("ZK_THEME", "default");
var browserIcon = ThemeManager.getBrowserIcon();
