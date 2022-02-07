import org.adempiere.webui.theme.ThemeManager;
var themeStyleSheet = ThemeManager.getStyleSheet();
var themeStyleSheetByBrowser = ThemeManager.getStyleSheetByBrowser();
var browserIcon = ThemeManager.getBrowserIcon();
var fontSizeM = org.zkoss.lang.Library.getProperty("org.zkoss.zul.theme.fontSizeM");
var fontSizeS = org.zkoss.lang.Library.getProperty("org.zkoss.zul.theme.fontSizeS");
var fontSizeXS = org.zkoss.lang.Library.getProperty("org.zkoss.zul.theme.fontSizeXS");
var fontSizeMS = org.zkoss.lang.Library.getProperty("org.zkoss.zul.theme.fontSizeMS");
var fontFamilyC = org.zkoss.lang.Library.getProperty("org.zkoss.zul.theme.fontFamilyC");

if (fontFamilyC != null){
   fontFamilyC = "font-family:" + fontFamilyC + ";";
}

if (fontSizeM != null){
    fontSizeM = "font-size:" + fontSizeM + ";";
}