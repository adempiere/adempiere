<%@ taglib uri="http://www.zkoss.org/dsp/web/core" prefix="c" %>
<%@ page contentType="text/css;charset=UTF-8" %>

<%-- Words --%>
<c:set var="Color01" value="#403E39"/>
<%-- Menu Background --%>
<c:set var="Color02" value="#EEEDEB"/>
<%-- Group Background --%>
<c:set var="Color03" value="#F3F3F1"/>
<%-- Tab Border --%>
<c:set var="Color04" value="#ABA799"/>
<%-- Input Border --%>
<c:set var="Color05" value="#FFFFFF"/>
<%-- Selection --%>
<c:set var="Color06" value="#D1CFCA"/>
<%-- Zebra Color --%>
<c:set var="Color07" value="#F9F9F8"/>
<%-- Group Bottom Line --%>
<c:set var="Color08" value="#CDCDCD"/>
<%-- Hover+Select--%>
<c:set var="Color09" value="#B2AEA6"/>
<%-- Hover --%>
<c:set var="Color10" value="#D6D6D6"/>

<c:set var="ColorWhite" value="#FFFFFF"/>
<c:set var="ColorBGTree" value="#F7F7F7"/>
<c:set var="ColorSeld" value="#0068c5"/>
<c:set var="ColorGray" value="#FFFFFF"/>
<c:set var="ColorLightGray" value="#F0F0F0"/>
<c:set var="ColorBorder" value="#999999"/>
<c:set var="aColor" value="#debe09"/>

<%-- OpenUp Ltda. Inicio --%>
<%-- Window Font --%>
<c:set var="WindowFont" value="'Open Sans', sans-serif, Verdana, Arial, Helvetica;"/>
<%-- OpenUp Ltda. Fin --%>


@import url('https://fonts.googleapis.com/css?family=Open+Sans');

.desktop-header-font {
    font-family: 'Open Sans', sans-serif, Verdana, Arial, Helvetica;
	font-size: 10px;
	color: #FFFFFF;
}


<%-- Start e-Evolution --%>
.z-toolbar-button {
 color: #282828 !important;
}

.z-listbox {
  color: #282828 !important;
}
<%-- End e-Evolution --%>

<%-- OpenUp Ltda. Inicio --%>
*, .z-label, .z-checkbox {
  font-family: ${WindowFont};
}
.mandatory-decorator-text {
  color: #ff0000 !important;
}
.z-combobox-inp, .z-combobox-inp, .z-textbox, .z-decimalbox, .z-checkbox-cnt {
  font-family: ${WindowFont};
}
.z-combobox-disd * {
  font-family: ${WindowFont};
  color: #686868 !important;
}
.desktop-header-font {
  color: #FFFFFF !important;
}
.z-tab-seld *, .z-button *, .form-button *, .adwindow-navbtn-sel * {
  color: #ffffff !important;
}
.z-textbox
, .z-combobox-inp
, .z-decimalbox
, .z-datebox-inp {
  background-color: #ffffff !important;
  border: 1px solid #bbbbbb !important;
}
.z-textbox-readonly
, .z-combobox-inp-readonly
, .z-decimalbox-readonly
, .z-datebox-inp-readonly
, .z-combobox-text-disd {
  background-image: url("../images/zul/misc/text-bg8-dis.gif") !important;
  background-repeat: repeat !important;
  color: #282828 !important;
}

.z-panel-header {
  color: #ffffff !important;
}
.adtab-grid-panel .z-row:nth-child(even) * {
  background-color: #f0f0f0 !important;
}
.adtab-grid-panel .z-row:nth-child(odd) * {
  background-color: #ffffff !important;
}

<%-- Skyblue underline tabs - Start --%>
.z-tabs, z-tabs-scroll {
  padding-bottom: 0px !important;
}
.z-tabpanels {
  border-top: solid 1px #1f9bde !important;
}
<%-- Skyblue underline tabs - End --%>

.z-tree-row-over *, .z-tree-row-over-seld *, .z-tree-row-seld * {
  color: #ffffff !important;
  background-color: #1F9BDE !important;
}

<%-- Logo position --%>
.desktop-header-left {
  margin-top: 0px;
  padding-left: 15px;
}

.z-combo-item-text {
  font-size: 12px !important;
}
.z-combo-item-inner {
  font-size: 10px !important;
  font-style: italic;
}

.z-column:not(.z-column-over) {
  background-color: #EBEBEB !important;
}

.adwindow-nav-content {
  background-color: #E7E7E7;
}

.z-list-item-seld *, .z-list-item-over * {
}

.adwindow-navbtn-uns
, .adwindow-navbtn-sel
, .adwindow-navbtn-dis {
  padding-top: 8px;
  padding-bottom: 8px;
  padding-right: 20px;
}

.z-north-splt, .z-south-splt {
  background: #003764;
  height: 1px;
}
.z-border-layout {
  background: #003764;
}


<%-- OpenUp Ltda. Fin --%>


<%-- Begin ERPCyA --%>
.z-toolbar-body span {
	font-size: 11px !important;
}
<%-- ERPCyA end --%>

tr.z-vbox{
	background-color:${ColorGray};
}

.z-drag-over {
	background: ${Color09};
}

div.z-drop-ghost {
	border:none;
}
.z-loading {
	background-color:${Color04};
	border:none;
}
div.z-progressmeter {
	border:none;
}
.z-loading-indicator {
	border:none;
	color:${Color01};
}
.z-datebox-pp .z-datebox-calyear {
	background-color:${Color07};
}
.z-calendar-calmon td.z-calendar-seld, .z-calendar-calday td.z-calendar-seld,
.z-datebox-calmon td.z-datebox-seld, .z-datebox-calday td.z-datebox-seld {
	background-color:${ColorSeld};
	border:none;
}
.z-textbox, .z-decimalbox, .z-intbox, .z-longbox, .z-doublebox {
	border-color:${ColorBorder};
}
.z-combobox-inp ,.z-spinner-inp,.z-datebox-inp,.z-timebox-inp ,.z-bandbox-inp {
	border-color:${ColorBorder};
}
.z-combobox .z-combobox-img,
.z-spinner .z-spinner-img,
.z-datebox .z-datebox-img,
.z-timebox .z-timebox-img ,
.z-bandbox .z-bandbox-img {
	border:none;
}
.z-textbox-focus, .z-textbox-focus input,
.z-decimalbox-focus, .z-decimalbox-focus input,
.z-intbox-focus, .z-intbox-focus input,
.z-longbox-focus, .z-longbox-focus input,
.z-doublebox-focus, .z-doublebox-focus input {

}
.z-combobox-focus .z-combobox-inp,
.z-spinner-focus .z-spinner-inp,
.z-datebox-focus .z-datebox-inp,
.z-timebox-focus .z-timebox-inp ,
.z-bandbox-focus .z-bandbox-inp  {

}
.z-combobox-focus .z-combobox-img,
.z-spinner-focus .z-spinner-img,
.z-datebox-focus .z-datebox-img,
.z-timebox-focus .z-timebox-img ,
.z-bandbox-focus .z-bandbox-img {
	border:none;
}
.z-combobox-pp ,.z-bandbox-pp {
	border:none;
}

.z-combobox-pp .z-combo-item-over {
	background-color: ${ColorSeld};
}

.z-combobox-pp .z-combo-item-seld .z-combo-item-text .z-combo-item-inner {
	color:${ColorWhite};
}
.z-combobox-pp .z-combo-item-over-seld .z-combo-item-text{
	color:${ColorWhite};
}
.z-combobox-pp .z-combo-item-over .z-combo-item-text{
	color:${ColorWhite};
}


.z-groupbox-tl,
.z-groupbox-hl {
	border:none;
}
.z-groupbox-cnt {
	border:none;
}
.z-window-embedded-cnt, .z-window-popup-cnt {
	border:none;
}
.z-window-embedded-tl, .z-window-embedded-tl-noborder {
	border:none;
}
.z-window-popup-cm, .z-window-modal-cm, .z-window-highlighted-cm, .z-window-overlapped-cm {
	border:none;
}
.z-window-resize-proxy {
	border:none;
	background-color: ${Color06};
}
.z-window-move-ghost {
	background-color : ${Color06};
}
.z-window-move-ghost dl,
.z-window-resize-faker {
	background-color : ${Color06};
	border:none;
}
.z-window-popup-tl {
	border:none;
}
div.z-tree, div.z-dottree, div.z-filetree, div.z-vfiletree {
	border:none;
}

div.z-tree-body{
	background-color : ${ColorBGTree};
	border:none;
}


tr.z-tree-row td.z-tree-row-focus tr.z-tree-row-text {
	color : ${ColorWhite};
}

tr.z-tree-row-seld tr.z-tree-row-text {
	color : ${ColorWhite};
}

tr.z-tree-row-over tr.z-tree-row-text{
	color : ${ColorWhite};
}

tr.z-tree-row-over-seld tr.z-tree-row-text{
	color : ${ColorWhite};
}


.z-tab-seld .z-tab-text {
	color : ${ColorWhite};
}

div.z-tree-header th.z-tree-col, div.z-tree-header th.z-auxheader,
div.z-dottree-header th, div.z-filetree-header th, div.z-vfiletree-header th {
	border:none;
}
tr.z-listbox-odd {
	background-color : ${Color07};
}

tr.z-list-item-focus, div.z-listcell-cnt-text{
	color: ${ColorWhite};
}

tr.z-list-item-seld, div.z-listcell-cnt-text{
	color: ${ColorWhite};
}
tr.z-list-item-over,  div.z-listcell-cnt-text{
	color: ${ColorWhite};
}

tr.z-list-item-over-seld,  div.z-listcell-cnt-text{
	color:${ColorWhite};
}

div.z-listbox-header th.z-list-header, div.z-listbox-header th.z-auxheader {
	border:none;
}
div.z-listbox {
	background-color : ${Color06};
	border:none;
}
div.z-listbox-footer {
	background-color : ${Color06};
	border:none;
}
tr.cells td {
	border-bottom-color:${Color04};
	border:none;
}
div.z-grid {
	background-color:${ColorGray};
	border:none;

	-moz-box-shadow: 1px 1px 4px gray;
	-webkit-box-shadow: 1px 1px 4px gray;
	box-shadow: 1px 1px 4px #F0F0F0;
}

div.z-grid-body {
	-moz-box-shadow: 0 0 5px #888;
	-webkit-box-shadow: 0 0 5px #888;
	box-shadow: 0 0 5px #888;
	background-color:${ColorGray};
	border:none;
}

tr.z-grid-odd td.z-row-inner, tr.z-grid-odd {
	background-color : ${ColorLightGray};
}

tr.z-grid-odd .z-cell{
	background-color : ${ColorLightGray};
}


tr.z-row td.z-row-inner{
	background-color : ${ColorGray};
	margin-left:4px;
	margin-right:4px;
	<%-- border-bottom: 1px solid ${ColorBGTree}; --%>
	border-left: 0;
	<%-- border-right: 1px solid ${ColorBGTree}; --%>
}

div.z-row-cnt{
	margin-left:4px;
	margin-right:4px;
}

<%--
tr.z-rows{
	background-color : ${ColorGray};
}
--%>

div.z-grid-header th.z-column, div.z-grid-header th.z-auxheader {
	border:none;
}
td.z-list-group-inner div.z-list-cell-cnt {
	color : ${Color01};
}
td.z-list-group-inner {
	border-top-color: ${Color04};
	border-bottom-color:${Color08};
	border:none;
}
td.z-list-group-foot-inner div.z-list-cell-cnt {
	color : ${Color01};
}
tr.z-list-group {
	background-color: ${Color03};
}
tr.z-group {
	background-color: ${Color03};
}
td.z-group-inner {
	border-top-color: ${Color04};
	border-bottom-color:${Color08};
	border:none;
}
.z-group-inner .z-group-cnt span, .z-group-inner .z-group-cnt {
	color : ${Color01};
}
.z-group-foot-inner .z-group-foot-cnt span, .z-group-foot-inner .z-group-foot-cnt {
	color : ${Color01};
}
.z-paging {
	border:none;
}
.z-paging-inp {
	border:none;
}
.z-paging-os .z-paging-os-cnt {
	background-color: ${Color09};
	border:none;
	color: ${Color01};
}
.z-paging-os .z-paging-os-seld:hover {
	color: white;
}

.z-paging-info, .z-paging-text{
    color:#FFFFFF;
}

.z-west-header, .z-center-header, .z-east-header, .z-north-header, .z-south-header {
	color : ${Color01};
}
.z-east-colpsd, .z-west-colpsd{
	background-color : ${Color07};
	border: 1px solid ${ColorBGTree};
}

.z-west-header, .z-center-header, .z-east-header, .z-north-header, .z-south-header {
	border:none;
}
.z-west, .z-center, .z-east, .z-north, .z-south {
	border:none;
	background-color: ${ColorGray};
}

.z-menubar-hor, .z-menubar-ver {
	border:none;
	background-color: ${Color02};
}


.z-menu-popup-cnt .z-menu-over a.z-menu-item-cnt,
.z-menu-popup-cnt .z-menu-item-over a.z-menu-item-cnt {
	border:none;
	color:${ColorWhite};
}


.z-menu-popup {
	border:none;
}
.z-menu-body-over .z-menu-inner-m .z-menu-btn,
.z-menu-body-seld .z-menu-inner-m .z-menu-btn,
.z-menu-item-body-over .z-menu-item-inner-m .z-menu-btn{
	color : ${ColorSeld};
}
.z-tabs-scroll {
	background-color : ${Color07};
	border:none;
}
.z-tabs .z-tabs-space {
	background-color : ${Color07};
	border:none;
}
.z-tabs .z-tabs-cnt {
	border:none;
}
.z-tabpanel{
	border:none;
}
.z-tabbox-ver .z-tabpanels-ver {
	border:none;
}
.z-tabs-ver-scroll {
	border:none;
}
.z-tabs-ver .z-tabs-ver-cnt {
	border:none;
}
.z-tabs-ver-space {
	background-color : ${Color07};
	border:none;
}
.z-tab .z-tab-text,
.z-tab .z-tab-hl:hover .z-tab-text {
	color : ${Color01};
}
.z-tab-seld .z-tab-text {
	color : ${ColorWhite};
}
.z-tab .z-tab-body:hover .z-tab-text {
	color : ${Color01};
}
.z-tab-ver .z-tab-ver-text {
	color : ${Color01};
}
.z-tab-ver-seld .z-tab-ver-text {
	color : ${ColorWhite};
}
.z-tabs-left-scroll , .z-tabs-right-scroll,
.z-tabs-ver-down-scroll, .z-tabs-ver-up-scroll {
	border:none;
}
.z-tabbox-accordion .z-tabpanel-accordion {
	border:none;
}
.z-tabbox-accordion-lite .z-tabpanel-accordion-lite {
	border:none;
}
.z-tab-accordion-lite-header {
	border:none;
}
.z-tabpanels-accordion-lite {
	border:none;
}
.z-toolbar {
	vertical-align: middle;
	border:none;
	height:38px;
}

.z-toolbar a, .z-toolbar a:visited, .z-toolbar a:hover {
	background:transparent;
	border:none;
	color: ${aColor};
}

.z-panel-children{
	background-color:${ColorBGTree};
	border-color:${ColorBorder};
}



.z-panel-hl,
.z-panel-tl {
	border:none;
}
.z-panel-hm .z-panel-header,

.z-panel-header {
	color : #FFFFFF;
	border:none;
}
.z-panel-children {
	border:1px;
	border-color:${ColorBorder};
}
.z-panel-cm .z-panel-children,
.z-panel-cl .z-panel-children,
.z-panel-children-noheader {
	border:1px;
	border-color:${ColorBorder};
}
.z-panel-cm {
	background-color :${Color03};
}
.z-panel-tl .z-panel-header {
	color : ${Color01};
}
.z-panel-fm {
	background-color:${Color07} ;
}

.z-panel-move-ghost {
	background-color: ${Color06};
}
.z-panel-move-ghost ul {
	border:none;
	background-color: ${Color06};
}
.z-panel-move-block {
	border:none;
}
.z-panel-top-noborder .z-toolbar,
.z-panel-top.z-panel-noheader .z-toolbar,
.z-panel-noborder .z-panel-header-noborder,
.z-panel-noborder .z-panel-tbar-noborder .z-toolbar,
.z-panel-noborder .z-panel-header.z-panel-header-noborder,
.z-panel-noborder .z-panel-top-noborder .z-toolbar,
.z-panel-noborder .z-panel-top.z-panel-top-noborder .z-toolbar,
.z-panel-noborder .z-panel-btm.z-panel-btm-noborder .z-toolbar,
.z-panel-noborder .z-panel-btm.z-panel-fbar-noborder .z-toolbar,
.z-panel-noborder .z-panel-btm.z-panel-btm-noborder .z-toolbar {
	border:none;
}
.z-panel-tbar .z-toolbar ,
.z-panel-bbar .z-toolbar,
.z-panel-tbar .z-toolbar,
.z-panel-body .z-panel-top .z-toolbar,
.z-panel-body .z-panel-btm .z-toolbar,
.z-panel-cl .z-panel-top .z-toolbar {
	border:none;
}
.z-popup .z-popup-cm {
	background-color:${Color03} ;
}
/*additional */


.z-menu-separator-inner {
	background-color:${Color08};
}
div.z-listbox-pgi-b, div.z-tree-pgi-b, div.z-grid-pgi-b  {
	border:none;
}




<%-- calendar.css.dsp --%>
.z-calendar-calyear, .z-datebox-calyear {
	background-color: ${Color02};
	border:none;
}
.z-calendar-calday, .z-datebox-calday {
	border:none;
}
.z-calendar-calmon td, .z-calendar-calday td, .z-calendar-calday td a, .z-calendar-calday td a:visited,
.z-datebox-calmon td, .z-datebox-calday td, .z-datebox-calday td a, .z-datebox-calday td a:visited {
	color: ${Color01};
}
.z-calendar-calmon td.z-calendar-seld, .z-calendar-calday td.z-calendar-seld,
.z-datebox-calmon td.z-datebox-seld, .z-datebox-calday td.z-datebox-seld {
	background-color: ${ColorSeld};
	border:none;
	color:#FFF !important;
}
.z-calendar-over, .z-datebox-over {
	background-color: ${ColorSeld};
}
.z-datebox-calmon td.z-datebox-over-seld,
.z-datebox-calday td.z-datebox-over-seld{
	background-color: ${ColorSeld};
}
.z-calendar-caldow td, .z-datebox-caldow td {
	color: ${Color01};
	background-color: ${Color02};
}
.z-datebox-readonly {
    background-image: url(../images/zul/misc/text-bg8-dis.gif) !important;
    border-color: #bbbbbb;
    background-color: #ECEAE4 !important;
}
.z-datebox-readonly + span {
opacity: 0.5;
}
