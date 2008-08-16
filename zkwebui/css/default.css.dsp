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
	filter: progid : DXImageTransform . Microsoft . Alpha(opacity = 20);
	-moz-opacity: 0.2;
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

<%-- desktop tabbox --%>
.desktop-tb {
	position:absolute;
}
	
td.tab-desktop-tb-first1 {
	background-image: url(${c:encodeURL('~./zul/img/tab/lite-first2.gif')});
	width: 1px; height: 1px;
}
td.tab-desktop-tb-first2 {
	background-image: url(${c:encodeURL('~./zul/img/tab/lite-first2.gif')});
	width: 3px; height: 1px;
}
td.tab-desktop-tb-last1 {
	background-image: url(${c:encodeURL('~./zul/img/tab/lite-last1.gif')});
	width: 3px; height: 1px;
}
td.tab-desktop-tb-last2 {
	background-image: url(${c:encodeURL('~./zul/img/tab/lite-last1.gif')});
	width: 1px; height: 1px;
}

td.tab-desktop-tb-tl-sel {
	background-image: url(${c:encodeURL('~./zul/img/tab/lite-tl-sel.gif')});
	width: 5px; height: 6px;
}
td.tab-desktop-tb-tl-uns {
	background-image: url(${c:encodeURL('~./zul/img/tab/lite-tl-uns.gif')});
	width: 5px; height: 6px;
}
td.tab-desktop-tb-tm-sel {
	background-image: url(${c:encodeURL('~./zul/img/tab/lite-tm-sel.gif')});
	height: 6px;
}
td.tab-desktop-tb-tm-uns {
	background-image: url(${c:encodeURL('~./zul/img/tab/lite-tm-uns.gif')});
	height: 6px;
}
td.tab-desktop-tb-tr-sel {
	background-image: url(${c:encodeURL('~./zul/img/tab/lite-tr-sel.gif')});
	width: 5px; height: 6px;
}
td.tab-desktop-tb-tr-uns {
	background-image: url(${c:encodeURL('~./zul/img/tab/lite-tr-uns.gif')});
	width: 5px; height: 6px;
}

td.tab-desktop-tb-ml-sel {
	background-image: url(${c:encodeURL('~./zul/img/tab/lite-ml-sel.gif')});
	width: 5px;
}
td.tab-desktop-tb-ml-uns {
	background-image: url(${c:encodeURL('~./zul/img/tab/lite-ml-uns.gif')});
	width: 5px;
}
td.tab-desktop-tb-mm-sel, td.tab-desktop-tb-mm-uns {
	background-image: url(${c:encodeURL('~./zul/img/tab/lite-white.gif')});
}
td.tab-desktop-tb-mr-sel {
	background-image: url(${c:encodeURL('~./zul/img/tab/lite-mr-sel.gif')});
	width: 5px;
}
td.tab-desktop-tb-mr-uns {
	background-image: url(${c:encodeURL('~./zul/img/tab/lite-mr-uns.gif')});
	width: 5px;
}

td.tab-desktop-tb-bl-sel, td.tab-desktop-tb-bm-sel, td.tab-desktop-tb-br-sel {
	background-image: url(${c:encodeURL('~./zul/img/tab/lite-white.gif')});
	width: 5px; height: 1px;
}
td.tab-desktop-tb-bl-uns, td.tab-desktop-tb-bm-uns, td.tab-desktop-tb-br-uns {
	background-image: url(${c:encodeURL('~./zul/img/tab/lite-last1.gif')});
	width: 5px; height: 1px;
}

tr.tab-desktop-tb-m {
	height: 21px;
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
}
