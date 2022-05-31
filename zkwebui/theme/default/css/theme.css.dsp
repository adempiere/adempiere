<%@ page contentType="text/css;charset=UTF-8" %>
<%@ taglib uri="http://www.zkoss.org/dsp/web/core" prefix="c" %>

html,body {
	margin: 0;
	padding: 0;
	height: 100%;
	width: 100%;
	background-color: #D4E3F4;
	overflow: hidden;
}

<%-- login --%>
.login-window {
	background-color: #003764;
}

.login-box-body {
	width: 500px;
	height: 300px;
	background-image: url(../images/login-box-bg.png);
	background-repeat: repeat-y;
	background-color: transparent;
	z-index: 1;
	padding: 10px;
	margin: 0;
	text-align: center;
	padding-bottom: 5px;
}

.login-box-header {
	background-image: url(../images/login-box-header.png);
	background-color: transparent;
	z-index: 2;
	height: 54px;
	width: 500px;
}

.login-box-header-txt {
	color: black;
	font-weight: bold;
	position: relative;
	top: 30px;
}

.login-box-header-logo {
	padding-top: 20px;
	padding-bottom: 25px;
}

.login-box-footer {
	background-image: url(../images/login-box-footer.png);
	background-position: top right;
	background-attachment: scroll;
	background-repeat: repeat-y;
	z-index: 2;
	height: 70px;
	width: 500px;
}

.login-box-footer-pnl {
	width: 420px;
	margin-left: 10px;
	margin-right: 10px;
	padding-top: -10px;
}

.login-label {
	color: black;
	text-align: right;
	width: 20%;
}

.login-error {
	font-weight: bold;
	color: red;
	text-align: left;
	width: 20%;
}
.login-field {
	text-align: left;
	width: 40%;
}

.login-btn {
	height: 36px;
	width: 72px;
}

.login-east-panel, .login-west-panel {
	width: 1px;
	background-color: #DDE3EB;
	position: relative;
	border-style: solid;
	border-width: 1px;
	border-color:#919191;
}

<%-- header --%>
.desktop-header-left {
	margin: 0;
	margin-left: 5px;
	margin-top: 3px;
}

.desktop-header-right {
	margin: 0;
	margin-top: 3px;
	padding-right: 5px;
}

.disableFilter img {
	opacity: 0.2;
	filter: progid : DXImageTransform . Microsoft . Alpha(opacity = 20);
	-moz-opacity: 0.2;
}

.toolbar {
	padding: 0px;
	height: 35px;
}


.toolbar-button{
	padding-top:7px;
	padding-bottom:7px;
	padding-right:0px;
	padding-left:5px;
}

.toolbar-button img {
	width: 22px;
	height: 22px;
	border-style: solid;
	border-width: 1px;
	border-color: transparent;
}

.embedded-toolbar-button img {
	width: 22px;
	height: 22px;
	<%--padding: 0px 1px 0px 1px;--%>
	border-style: solid;
	border-width: 1px;
	border-color: transparent;
}

.depressed img {
	border-style: inset;
	border-width: 1px;
	border-color: #9CBDFF;
	background-color: #C4DCFB;
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	padding: 0px 1px 0px 1px;
}

.desktop-header {
	background-image: url(../images/header-bg.png);
	background-repeat: repeat-x;
	background-position: bottom left;
	background-color: white;
	width: 100%;
	height: 50px;
}

.desktop-header-font {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 10px;
	color: #FFFFFF;
}

<%-- button --%>
.action-button {
	height: 15px;
	width: 48px;
	padding: 0px;
}

.action-text-button {
	height: 15px;
	width: 80px;
	padding: 0px;
}

.editor-button {
	width: 25px;
	height:25px;
	padding: 0px;
}

.editor-button img {
	vertical-align: middle;
	text-align: center;
}

<%-- desktop --%>
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
	background-color: #DDE3EB;
	color:black;
	height: 25px;
	padding-top: 0;
	pdding-bottom: 0;
<%--
	background-image: url(../images/bar-bg.png);
	background-repeat: repeat-x;
	background-position: center left;
--%>
}

.adwindow-nav {
	width:110px;
}

.adwindow-left-nav {
	border-right: 1px solid #919191;
	border-left: none;
}

.adwindow-right-nav {
	border-left: 1px solid #7EAAC6;
	border-right: none;
}

.adwindow-nav-content {
	background-color: #f0faff;
	height: 100%;
	width:110px;
	-moz-box-shadow: inset 0 0 5px #333;
	-webkit-box-shadow: inset 0 0 5px#333;
	box-shadow: inner 0 0 5px #333;
}

.adwindow-toolbar {
	vertical-align: middle;
	border: 0px;
	height:39px;
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
	color: #FFFFFF;
	cursor: pointer;
	border-top: none;
	border-bottom: none;
}

.adwindow-left-navbtn-sel {
	border-left: none;
	border-right: none;
	text-align: right;
	-moz-border-radius-topleft: 5px;
	-moz-border-radius-bottomleft: 5px;
	-webkit-border-top-left-radius: 5px;
	-webkit-border-bottom-left-radius: 5px;

	-moz-box-shadow: 0 0 5px #333;
	-webkit-box-shadow: 0 0 5px #333;
	box-shadow: 0 0 5px #333;


	background-color: #0068c5 !important;
	background-image: url(../images/adtab-left-bg.png);
	background-repeat: repeat-x;
	background-position: top left;
}

.adwindow-right-navbtn-sel {
	border-right: none;
	border-left: none;
	text-align: left;
	-moz-border-radius-topright: 5px;
	-moz-border-radius-bottomright: 5px;
	-webkit-border-top-right-radius: 5px;
	-webkit-border-bottom-right-radius: 5px;

	-moz-box-shadow: 0 0 5px #333;
	-webkit-box-shadow: 0 0 5px #333;
	box-shadow: 0 0 5px #333;

	background-color: #0068c5 !important;
	background-image: url(../images/adtab-right-bg.png);
	background-repeat: repeat-x;
	background-position: top left;
}

.adwindow-navbtn-uns {
	background-color: #C4DCFB;
	font-weight: normal;
	color: #000000;
	cursor: pointer;
}

.adwindow-navbtn-dis {
	background-color: #AAAAAA;
}

.adwindow-navbtn-uns, .adwindow-navbtn-dis {
	border-top: none;
	border-bottom: none;
}

.adwindow-left-navbtn-uns, .adwindow-left-navbtn-dis {
	background-image: url(../images/adtab-bg-uns.png);
	border-left: none;
	border-right: none;
	text-align: right;
	-moz-border-radius-topleft: 5px;
	-moz-border-radius-bottomleft: 5px;
	-webkit-border-top-left-radius: 5px;
	-webkit-border-bottom-left-radius: 5px;

	-moz-box-shadow: 0 0 5px #333;
	-webkit-box-shadow: 0 0 5px #333;
	box-shadow: 0 0 5px #333;
}

.adwindow-right-navbtn-uns, .adwindow-right-navbtn-dis {
	background-image: url(../images/adtab-bg-uns.png);
	border-right: none;
	border-left: none;
	text-align: left;
	-moz-border-radius-topright: 5px;
	-moz-border-radius-bottomright: 5px;
	-webkit-border-top-right-radius: 5px;
	-webkit-border-bottom-right-radius: 5px;

	-moz-box-shadow: 0 0 5px #333;
	-webkit-box-shadow: 0 0 5px #333;
	box-shadow: 0 0 5px #333;
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
	background-color: #EDEDED;

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
	height: 25px;
	padding-top: 0;
	pdding-bottom: 0;
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
	color:black;
	font-weight: bold;
}

.status-info {
	padding-right: 10px;
	border-left: solid 1px #9CBDFF;
	color:black;
	font-weight: bold;
}

.status-border {
	border: solid 1px #9CBDFF;
}

.message-error{
	background-color:#ffd7d7;
}

.message-error-text{
	color: black;
	font-weight: bold;
	margin-top:10px;
}



.message-info{
	background-color:#FFFFFF;
}

.message-info-text{
	color: black;
	font-weight: bold;
}


.message-warning{
	background-color:yellow;
}

.message-warning-text{
	color: black;
	font-weight: bold;
}




.form-button{
	width: 99%;
}

<%-- Combobox --%>
.z-combobox-disd {
	color: black !important; cursor: default !important; opacity: 1; -moz-opacity: 1; -khtml-opacity: 1; filter: alpha(opacity=100);
}

.z-combobox-disd * {
	color: black !important; cursor: default !important;
}

.z-combobox-text-disd {
	background-color: #ECEAE4 !important;
}

<%-- Button --%>
.z-button-disd {
	color: black; cursor: default; opacity: .6; -moz-opacity: .6; -khtml-opacity: .6; filter: alpha(opacity=60);
}

<%-- highlight focus form element --%>
input:focus, textarea:focus, .z-combobox-inp:focus, z-datebox-inp:focus {
	border: 1px solid #0000ff;
}

.mandatory-decorator-text {
	text-decoration: none;  vertical-align: top; color:red;
	font-weight: bold;
	font-size: xx-small;
}



<%-- menu tree cell --%>
div.z-tree-body td.menu-tree-cell {
	cursor: pointer;
	padding: 0 2px;
   	font-size: ${fontSizeM};
   	font-weight: normal;
   	overflow: visible;
}

div.menu-tree-cell-cnt {
	border: 0; margin: 0; padding: 0;
	font-family: ${fontFamilyC};
	font-size: ${fontSizeM}; font-weight: normal;
    white-space:nowrap
}

td.menu-tree-cell-disd * {
	color: #C5CACB !important; cursor: default!important;
}

td.menu-tree-cell-disd a:visited, td.menu-tree-cell-disd a:hover {
	text-decoration: none !important;
	cursor: default !important;;
	border-color: #D0DEF0 !important;
}

div.z-dottree-body td.menu-tree-cell {
	cursor: pointer; padding: 0 2px;
	font-size: ${fontSizeM}; font-weight: normal; overflow: visible;
}

div.z-filetree-body td.menu-tree-cell {
	cursor: pointer; padding: 0 2px;
	font-size: ${fontSizeM}; font-weight: normal; overflow: visible;
}

div.z-vfiletree-body td.menu-tree-cell {
	cursor: pointer; padding: 0 2px;
	font-size: ${fontSizeM}; font-weight: normal; overflow: visible;
}

tr.z-row td.z-row-inner{
	background-color : #FAFAFA;
	margin-left:4px;
	margin-right:4px;
	border-bottom: 0px solid #DDE3EB;
	border-left: 0;
	border-right: 0px solid #DDE3EB;
}

<%-- Document Status Indicator First Column - Number --%>
.docStatus td:first-child {
	width : 60px;
	text-align : right;

}

.docStatus-sep {
	min-width :10px !important;
}

<%-- Document Status Indicator 3rd Column - Name Label --%>

.docStatus td {
	text-align : left;
}


input[type="checkbox"]  {

	border: 1px solid #bbbbbb;
	box-shadow: 0 1px 2px rgba(0,0,0,0.05), inset 0px -15px 10px -12px rgba(0,0,0,0.05);
	padding: 9px;
	display: inline-block;
	position: relative;
	appearance: none;
	-webkit-appearance: initial !important;

}

input[type="checkbox"] :active, input[type="checkbox"]:checked :active {
	box-shadow: 0 1px 2px rgba(0,0,0,0.05), inset 0px 1px 3px rgba(0,0,0,0.1);
}

input[type="checkbox"]:checked  {
	background-color: #e9ecee;
	border: 1px solid #adb8c0;
	box-shadow: 0 1px 2px rgba(0,0,0,0.05), inset 0px -15px 10px -12px rgba(0,0,0,0.05), inset 15px 10px -12px rgba(255,255,255,0.1);
	color: #99a1a7;
	background   : url('../images/zul/input/check.png')

}
input[type="checkbox"]:disabled {
    border: 1px solid #f0f0f0 !important;
    background: f0f0f0;
    }
input[type="checkbox"]:checked :after {
	background   : url('../images/zul/input/check.png')
	font-size: 14px;
	position: absolute;
	top: 0px;
	left: 3px;
	color: #99a1a7;
}
.z-checkbox-cnt {
	position: relative;
    bottom: 8px;
}

.z-tab-close {
    background-image: url(../images/zul/tab/tab-close.png);
    background-repeat: no-repeat;
    cursor: pointer;
    display: block;
    width: 12px;
    height: 22px;
    position: absolute;
    right: 1px; 
    top: 1px;
    z-index: 15;
}