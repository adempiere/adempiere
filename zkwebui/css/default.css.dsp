<%@ page contentType="text/css;charset=UTF-8" %>
<%@ taglib uri="http://www.zkoss.org/dsp/web/core" prefix="c" %>

html,body {
	margin: 0;
	padding: 0;
	height: 100%;
	background-color: #D4E3F4;
}

.login {
	background-color: #D4E3F4;
}

.header-left {
	margin: 0;
	margin-left: 5px;
	margin-top: 3px;	
}

.header-right {
	margin: 0;
	margin-top: 3px;
	padding-right: 5px;	
}

.disableFilter img {
	opacity: 0.2;
	filter: progid : DXImageTransform . Microsoft . Alpha(opacity = 20);	
	-moz-opacity: 0.2;			
}

.depressed img {
	border-style: inset;
	border-width: 1px;
	border-color: #9CBDFF;
	background-color: #C4DCFB;
	-moz-border-radius: 5px;
	padding: 1px 4px 1px 4px;
}

.header {
	background-image: url(images/gradient-bg.gif);
	background-repeat: repeat-x;
	width: 100%;
	height: 35px;
}

.headerFont {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 10px;
}

.action-button {
	height: 32px;
	width: 48px;
}

.action-text-button {
	height: 32px;
	width: 80px;
}

.editor-button {
	--height: 22px;
	width: 26px;
	padding: 0px;
}

.editor-button img {
	vertical-align: middle;
	text-align: center;
}

div.wc-modal, div.wc-modal-none, div.wc-highlighted, div.wc-highlighted-none {
	background-color: white;
}

.desktop-tabpanel {
	margin: 0;
	padding: 0;
	border: 0;
	position: absolute;
}

.menu-search {
	background-color: #E0EAF7;
}

<%-- adwindow and form --%>

.adform-content-none {
	overflow: auto;
	position: absolute;
	width: 100%;
	margin: 3px;
}

.adwindow-status {
	background-color: #E0EAF7;
	height: 20px;
}

.adwindow-nav {	
	width: 200px;
}

.adwindow-nav-content {
	background-color: #E0EAF7;
	height: 100%;
}

.adwindow-toolbar {
	border: 0px;
}

.adwindow-navbtn-dis, .adwindow-navbtn-sel, .adwindow-navbtn-uns {
	border: 0px;
	margin-top: 3px;
	padding-top: 2px; 
	padding-bottom: 2px;
}
  
.adwindow-navbtn-sel {
	background-color: #9CBDFF; 
	font-weight: bold; 
	color: #274D5F; 
	cursor: pointer; 
}

.adwindow-navbtn-uns {
	background-color: #C4DCFB; 
	font-weight: normal; 
	color: #274D5F;
	cursor: pointer;
}

.adwindow-navbtn-dis {
	background-color: #C4DCFB; 
}

<%-- ad tab --%>

.adtab-body {
	position: absolute;
	margin: 0;
	padding: 0;
	width: 100%;
	height: 100%;
	border: none;
}

.adtab-content {	 
	margin: 0; 
	padding: 0; 
	border: none;
	overflow: auto;
	width: 100%;
	height: 100%;
	position: absolute;	
}

.adtab-grid-panel {
	position: absolute;
	overflow: hidden;
	width: 100%;
	height: 100%;
}

.adtab-grid {
	width: 100%;
	position: absolute;
}

.adtab-tabpanels {
	width: 80%;
	border-top: 1px solid #9CBDFF;
	border-bottom: 1px solid #9CBDFF;
	border-left: 2px solid #9CBDFF;
	border-right: 2px solid #9CBDFF;
}

<%-- status bar --%>
.status {
	width: 100%;
	height: 20px;
}

.status-db {
	padding-top: 0; 
	pdding-bottom: 0; 
	padding-left: 5px; 
	padding-right: 5px; 
	cursor: pointer; 
	width: 100%; 
	height: 100%; 
	margin: 0; 
	border-left: solid 1px #9CBDFF;
}

.status-info {
	padding-right: 10px;
	border-left: solid 1px #9CBDFF;
}

.status-border {
	border: solid 1px #9CBDFF;
}

.form-button {
	width: 99%;
}

<%-- Combobox --%>
.z-combobox-disd {
	color: black !important; cursor: default !important; opacity: 1; -moz-opacity: 1; -khtml-opacity: 1; filter: alpha(opacity=100);
}

.z-combobox-disd * {
	color: black !important; cursor: default !important;
}

<%-- Button --%>
.z-button-disd {
	color: black; cursor: default; opacity: .6; -moz-opacity: .6; -khtml-opacity: .6; filter: alpha(opacity=60);
}