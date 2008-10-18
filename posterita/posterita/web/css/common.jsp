/**
 *  Product: Posterita Web-Based POS and Adempiere Plugin
 *  Copyright (C) 2007  Posterita Ltd
 *  This file is part of POSterita
 *  
 *  POSterita is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
 
/**
	@author tamak
 */
 

<% 
response.setContentType("text/css");
String path = request.getContextPath() + "/";
%>

div.main
{
	width:100%;
	text-align: center;
	overflow: inherit;
}


#header {
  background: #fff;
  position:relative;
  vertical-align:top;
  height:30px;
  width: 100%;
  overflow:hidden;
  text-overflow:ellipsis
  
  
}

#headerTitle
{
	float:left;
	margin-left:200px;
	font-weight: bold;
}

#logo{
height:30px;
float:left;
}

#context{
float:right;
margin-right:50px;
height:30px;
white-space:nowrap;
overflow:hidden;
text-align:right;
text-overflow:ellipsis

}

#timer{
position:relative;
vertical-align:top;
float:right;
border: 0px #E83530 solid;
background-color: #ffffff;
color: #666666; 
font-size: 13px; 
font-weight: bold;
}

#actionlink
{
position:relative;
vertical-align:top;
float:right;

}

#menu
{
	position:relative;
	background-color: #D8D8D8;
}

#menulink
{
	background-color: #D8D8D8;
	position:relative;
	width:100%;
	float:right;
	text-align: right;	
	height:20px;
	overflow:hidden;
	text-overflow: ellipsis;
}



#content {
  text-align:left;
  background-color: #F4F4F4;
  width: 100%;
  min-height: 500px;
  padding:0px;
}

#cart td{
	text-align: left;
}

div.container
{

}

div.leftContainer
{
	width:50%;
	float:left;
	text-align:left;
}

div.rightContainer
{
	width:50%;
	float:right;
	text-align:right;
}


/*#footer {
  background: #D8D8D8;
  clear: both;
  width: 100%;
}
*/

/*body
{
	font-family: arial,sans-serif;	
	font-size: 10pt;	
	letter-spacing: 1px;
	color: #000000;
}*/

#content div, #main div
{
	font-family: arial,sans-serif;	
	font-size: 10pt;	
	letter-spacing: 1px;
	color: #000000;
}

mandatory
{
	font-weight: bold;
	text-align:right;
	font-size:12px;
	color: blue;
}

p.loginHeader
{
	color: #666666;
	font-size: 36px;
	font-weight: bold;
	font-family: arial,sans-serif;	
}



div.copyright
{
	background-color: #666666;
	text-align: center;
	color: #FFFFFF;
	font-family: arial,sans-serif;	
	font-weight: bold;
	font-size: 10pt;
	letter-spacing: 1px;
	padding: 2;
}

/*label
{
	font-family: arial,sans-serif;	
	font-size: 10pt;
	/*font-weight: bold;*/
	letter-spacing: 1px;
	color: #000000;
	padding-right: 4px;	
}*/

label.red
{
	font-family: arial,sans-serif;	
	font-size: 10pt;
	/*font-weight: bold;*/
	letter-spacing: 1px;
	color: #E83530;	
}




label.green
{
	font-family: arial,sans-serif;	
	font-size: 10pt;
	/*font-weight: bold;*/
	letter-spacing: 1px;
	color: #0A9409;	
}

td.green
{
	font-family: arial,sans-serif;	
	font-size: 10pt;
	/*font-weight: bold;*/
	letter-spacing: 1px;
	background-color: #80FFC9;
}

td.green input
{
	visibility:hidden;
}

td.red
{
	font-family: arial,sans-serif;	
	font-size: 10pt;
	/*font-weight: bold;*/
	letter-spacing: 1px;
	background-color: #FF9980;
}
td.blue
{
	font-family: arial,sans-serif;	
	font-size: 10pt;
	/*font-weight: bold;*/
	letter-spacing: 1px;
	background-color: #80C9FF;
}
td.yellow
{
	font-family: arial,sans-serif;	
	font-size: 10pt;
	/*font-weight: bold;*/
	letter-spacing: 1px;
	background-color: #FFE680;
}

#messagebox div, .errormsg 
{
	background-image: url("<%= path + "images/newUI/icon-err.gif" %>");
	background-repeat: no-repeat;
	background-position: left center;
	font-family: arial,sans-serif;
	font-weight: bold;	
	font-size: 10pt;	
	letter-spacing: 1px;	
	padding-left: 30px;
	color: #E83530;	
}

.successmsg
{
	background-image: url("<%= path + "images/pos/success.gif" %>");
	background-repeat: no-repeat;
	background-position: left top;
	font-family: arial,sans-serif;
	font-weight: bold;	
	font-size: 12pt;	
	letter-spacing: 1px;	
	padding-left: 30px;
	color: #008000;	
}

legend
{
	font-family: arial,sans-serif;	
	font-size: 10pt;
	font-weight: bold;
	letter-spacing: 1px;
	color:#0A9409;
}


input.text
{
	border: solid 1px #666666;
	/*font-family: arial,sans-serif;*/	
	font-size: 10pt;
}

textarea.text
{
	border: solid 1px #666666;
	/*font-family: arial,sans-serif;*/	
	font-size: 10pt;
}

select.text
{
	border: solid 1px #666666;
	/*font-family: arial,sans-serif;*/	
	font-size: 10pt;
	color: #51585F;
}


input.medium
{
	width:100px;
}

input:focus
{
	background-color: #CDD4E9;
	border: solid 1px #333333;
}


.greencolor
{
	color:#0A9409;
}

.completecolor
{
	color:#E83530;
}

font.pagetitle
{
	font-size: 14pt;
	color: #990000;
}

font.reporttitle
{
	font-size: 14pt;
}

font.reportsubtitle
{
	font-size: 12pt;
}

font 
{
	font-family: arial,sans-serif;	
	font-size: 10pt;
	font-weight: bold;
	letter-spacing: 1px;
	color: #555555;
}

li.submenu
{
	list-style-image: url("<%= path + "images/pos/buttons/bullet_red.gif" %>");
	font-family: arial,sans-serif;	
	font-size: 10pt;
	/*font-weight: bold;*/
	letter-spacing: 1px;
	color: #000000;
}

img.topmenu
{
	border-style: none;
	
}

img.button
{
	cursor:pointer;	
}

/* anchor sytles */
/*a:hover 
{
	color:#0A9409;
}
*/
a.topmenu
{
	text-decoration: none;
	border-width: 0px;
	border-style: none;
}

a.submenu
{	
	text-decoration: none;
	color:#666666;
	font-family: arial,sans-serif;
	font-size: 10pt;
	letter-spacing: 1px;
	font-weight: bold;
}

a
{	
	text-decoration: none;	
	font-family:tahoma,san-serif;
	font-size: 10pt;
	letter-spacing: 0px;	
}

a.forgotpassword
{
	color:#0A9409;
	font-family: arial,sans-serif;
	font-size: 12pt;
	letter-spacing: 1px;
	font-weight: bold;
}

/* end of anchor styles */

/* Auto complete style */
div.autocomplete {
      position:absolute;
      width:250px;
      height:300px;      
      background-color:white;
      border:1px solid #888;
      margin:0px;
      padding:0px;
      overflow: scroll;
}

div.autocomplete ul {
  list-style-type:none;
  margin:0px;
  padding:0px;
}
div.autocomplete ul li.selected { background-color: #ffb;}
div.autocomplete ul li {
  list-style-type:none;
  display:block;
  margin:0;
  padding:2px;  
  cursor:pointer;
  letter-spacing: 1px;
  font-size: 10pt;
  font-family:arial,sans-serif;
}
div.notfound {
	background-color: #ffb;	
}

div.autocomplete2 {
      position:absolute;
      width:250px;     
      background-color:white;
      border:1px solid #888;
      margin:0px;
      padding:0px;
      overflow: auto;
}

div.autocomplete2 ul {
  list-style-type:none;
  margin:0px;
  padding:0px;
}
div.autocomplete2 ul li.selected { background-color: #9ACF30;}
div.autocomplete2 ul li {
  list-style-type:none;
  display:block;
  margin:0;
  padding:2px;  
  cursor:pointer;
  letter-spacing: 1px;
  font-size: 10pt;
  font-family:arial,sans-serif;
  text-align:left;
}

/* tables styles */
table.view
{
	border-collapse: collapse;
	/*border: solid 1px black;*/
}

table.view td
{	
	font-family: arial,sans-serif;	
	font-size: 10pt;	
	letter-spacing: 1px;	
	color: #000000;	
	padding: 5px 0px 5px 0px;
}

table.layout
{
	padding-left: 10;
	padding-right: 10;
	padding-top: 0;
	padding-bottom: 5;
	
	/* border:solid 1px #000000; */
}

table.main
{
	width: 100%;
	/*border: solid 1px black;*/
	border-collapse: collapse;
}

table.main td
{
	padding: 5px 0px 5px 0px;	
}

table.topmenu
{
	padding-left: 0;
	padding-right: 0;
	
	/* border:solid 1px #000000; */	
}

td.buttoncell
{
	padding-right: 0px;	
}

table.login
{
	width: 800px;
	/*height: 600px;*/
	border: solid black 1px;
	border-spacing: 0;		
}

table.content
{
	width: 100%;
	/* border: solid 1px black; */
	border-collapse: collapse;
}

table.content th
{
	font-family: arial,sans-serif;	
	font-size: 10pt;
	font-weight: bold;
	letter-spacing: 1px;
	color: #FFFFFF;
	background-color: #909090;	
}

table.content td.total
{
	font-family: arial,sans-serif;
	font-size: 10pt;
	font-weight: bold;
	letter-spacing: 1px;
	color: #FFFFFF;
	background-color: #BBBBBB;
}

table.content td
{
	font-family: arial,sans-serif;	
	font-size: 10pt;	
	letter-spacing: 1px;
	font-weight: bold;
	color: #000000;
	padding: 5px 0px 5px 0px;
	
}

table.cart
{
	width: 100%;
	/*border: solid 1px black;*/
	border-collapse: collapse;
	border-spacing: 0px;
	border: 0px;
}

table.cart td
{
	font-family: arial,sans-serif;	
	font-size: 10pt;	
	letter-spacing: 1px;
	font-weight: bold;
	color: #000000;
	padding: 5px 5px 5px 5px;	
}

table.cart th
{
	font-family: arial,sans-serif;	
	font-size: 10pt;
	font-weight: bold;
	letter-spacing: 1px;
	color: #FFFFFF;
	background-color: #909090;
	padding: 5px 5px 5px 5px;
}

table.cart td.total
{
	color: #FFFFFF;
	background-color: #909090;
}

table.cart td.label
{
	color: #000000;
	background-color: #D8D8D8;
}

table.cart td.contentname
{
	color: #000000;
	background-color: #C9C9C9;
}

table.content td.label
{
	font-family: arial,sans-serif;	
	font-size: 10pt;	
	letter-spacing: 1px;
	font-weight: bold;
	color: #000000;
	background-color: #D8D8D8;
}

table.content td.contentname
{
	font-family: arial,sans-serif;	
	font-size: 10pt;	
	letter-spacing: 1px;
	font-weight: bold;
	color: #ffffff;
	background-color: #C9C9C9;
}

table.orderheader td
{
	font-family: arial,sans-serif;	
	font-size: 10pt;	
	letter-spacing: 1px;
	padding: 5px 0px 5px 0px;
	color: #000000;	
}

/*
td.copyright
{
	height: 60px;
	vertical-align: top;
	padding: 0;
}
*/

table.display td a
{
	text-decoration: none;
	color:blue;
	font-family: arial,sans-serif;
	font-size: 10pt;
	letter-spacing: 1px;	
}

table.display
{
	width: 100%;
	border-color: #000000;
	border-collapse: collapse;
}

table.display td.label
{
	/*
	font-family: arial,sans-serif;		
	letter-spacing: 1px;	
	color: #000000;
	*/
	font-size: 8pt;
	background-color: #D8D8D8;
}

table.display td.contentname
{
	/*
	font-family: arial,sans-serif;	
	letter-spacing: 1px;	
	color: #000000;
	*/
	font-size: 8pt;
	background-color: #C9C9C9;
}

table.display td
{
	font-family: arial,sans-serif;	
	font-size: 9pt;	
	letter-spacing: 1px;	
	color: #000000;
	padding: 5px 5px 5px 5px;
	border-color: #000000;
	border-collapse: collapse;	
}

table.display th
{
	font-family: arial,sans-serif;	
	font-size: 9pt;
	font-weight: bold;
	letter-spacing: 1px;
	color: #FFFFFF;
	background-color: #909090;
	padding: 5px 5px 5px 5px;
	border-color: #000000;
	border-collapse: collapse;
}

table.displaytag
{
	border-width: 1px;
	width: 100%;
	border-color: #000000;
	border-collapse: collapse;
	border-style: solid;
}

table.displaytag th
{
	font-family: arial,sans-serif;	
	font-size: 9pt;
	font-weight: bold;
	letter-spacing: 1px;
	color: #FFFFFF;
	background-color: #909090;
	border-color: #000000;
	padding: 5px 5px 5px 5px;
	border-width: 1px;
	border-style: solid;
}

table.displaytag th a
{
	color: #FFFFFF;
}

table.displaytag td
{
	font-family: arial,sans-serif;	
	font-size: 9pt;	
	letter-spacing: 1px;	
	color: #000000;
	padding: 5px 5px 5px 5px;
	border-color: #000000;
	border-collapse: collapse;
	border-width: 1px;
	border-style: solid;
}

table.displaytag td.amount
{
	font-family: arial,sans-serif;	
	font-size: 9pt;	
	letter-spacing: 1px;	
	color: #000000;
	padding: 5px 5px 5px 5px;
	border-color: #000000;
	border-collapse: collapse;
	border-width: 1px;
	border-style: solid;
	text-align: right;
}

table.displaytag td.numeric
{
	font-family: arial,sans-serif;	
	font-size: 9pt;	
	letter-spacing: 1px;	
	color: #000000;
	padding: 5px 5px 5px 5px;
	border-color: #000000;
	border-collapse: collapse;
	border-width: 1px;
	border-style: solid;
	text-align: right;
	font-weight: bold;
}

table.displaytag td.negativenumeric
{
	font-family: arial,sans-serif;	
	font-size: 9pt;	
	letter-spacing: 1px;	
	padding: 5px 5px 5px 5px;
	border-color: #000000;
	border-collapse: collapse;
	border-width: 1px;
	border-style: solid;
	text-align: right;
	color: #FF0000;
	font-weight: bold;
}


table.displaytag td a
{
	text-decoration: none;
	color:blue;
	font-family: arial,sans-serif;
	font-size: 10pt;
	letter-spacing: 1px;	
}

table.displaytag tr.odd
{
	border-width: 1px;
	border-style: solid;
	text-align: left;
	background-color: #D8D8D8;
}

table.displaytag tr.even
{
	border-width: 1px;
	border-style: solid;
	background-color: #C9C9C9;
	text-align: left;
}

div.exportlinks
{
	text-align: right;
}

caption
{
	font-family: arial,sans-serif;	
	font-size: 12pt;
	font-weight: bold;
	letter-spacing: 1px;
	padding: 5px 5px 5px 5px;
}
/* end of table styles */

/* buttons styles */
.advanced
{
	background-image: url("<%= path + "images/pos/buttons/button_advanced.gif" %>");	
}

.continue
{
	background-image: url("<%= path + "images/pos/buttons/button_continue.gif" %>");
}

.create
{
	background-image: url("<%= path + "images/pos/buttons/button_create.gif" %>");
}

.complete
{
	background-image: url("<%= path + "images/pos/buttons/button_complete.gif" %>");
}

.addtocart
{
	background-image: url("<%= path + "images/pos/buttons/button_addtocart.gif" %>");
}

.newcustomer
{
	background-image: url("<%= path + "images/pos/buttons/button_newcustomer.gif" %>");
}

.checkout
{
	background-image: url("<%= path + "images/pos/buttons/button_checkout.gif" %>");
}

.delete
{
	background-image: url("<%= path + "images/pos/buttons/button_delete.gif" %>");
}

.neworder
{
	background-image: url("<%= path + "images/pos/buttons/button_neworder.gif" %>");
}
.newnote
{
	background-image: url("<%= path + "images/pos/buttons/button_newnote.gif" %>");
}

.cash
{
	background-image: url("<%= path + "images/pos/buttons/button_cash.gif" %>");
}

.card
{
	background-image: url("<%= path + "images/pos/buttons/button_card.gif" %>");
}

.cheque
{
	background-image: url("<%= path + "images/pos/buttons/button_cheque.gif" %>");
}

.mixed
{
	background-image: url("<%= path + "images/pos/buttons/button_mixed.gif" %>");
}

.save
{
	background-image: url("<%= path + "images/pos/buttons/button_save.gif" %>");
}

.search
{
	background-image: url("<%= path + "images/pos/buttons/button_search.gif" %>");
}

.edit
{
	background-image: url("<%= path + "images/pos/buttons/button_edit.gif" %>");
}

.tangoSearch
{
	background-image: url("<%= path + "images/tango/system-search.png" %>");
	margin-top: -3px;
}

.selectall
{
	background-image: url("<%= path + "images/pos/buttons/button_selectall.gif" %>");
}

.submit
{
	background-image: url("<%= path + "images/pos/buttons/button_submit.gif" %>");
}

.refresh
{
	background-image: url("<%= path + "images/pos/buttons/button_refresh.gif" %>");
}

.piechart
{
	background-image: url("<%= path + "images/pos/buttons/button_piechart.gif" %>");
}

.barchart
{
	background-image: url("<%= path + "images/pos/buttons/button_barchart.gif" %>");
}

.timeseries
{
	background-image: url("<%= path + "images/pos/buttons/button_timeseries.gif" %>");
}

.tabular
{
	background-image: url("<%= path + "images/pos/buttons/button_tabular.gif" %>");
}

.saveascsv
{
	background-image: url("<%= path + "images/pos/buttons/button_saveascsv.gif" %>");
}

.saveaspdf
{
	background-image: url("<%= path + "images/pos/buttons/button_saveaspdf.gif" %>");
}

.blank
{
	background-image: url("<%= path + "images/pos/buttons/button_blank.gif" %>");
}

.bigbutton
{	
	background-repeat: no-repeat;
	cursor: pointer;
	border: 0px;
	width: 127px;
	height: 42px;
	font-family: arial,sans-serif;	
	font-size: 12pt;	
	letter-spacing: 1px;	
	font-weight: bolder;
	color: #666666;
	padding: 0px;	
}

.smallbutton
{
	background-repeat: no-repeat;
	cursor: pointer;
	border: 0px;
	width: 87px;
	height: 42px;
	font-family: arial,sans-serif;	
	font-size: 12pt;	
	letter-spacing: 1px;	
	font-weight: bolder;
	color: #666666;
	padding: 0px;
}

.tangoButton
{
	background-repeat: no-repeat;
	cursor: pointer;
	border: 0px;
	width: 32px;
	height: 32px;
	font-family: arial,sans-serif;	
	letter-spacing: 1px;	
	font-weight: bolder;
	v-align:middle;
	background-color:transparent;
}

/* end of buttons styles */

div.space
{
	height:10px;
	/*
	border: solid 1px black;
	background-color: #0000FF;
	*/
}

span.link
{
	cursor:pointer;
	font-family: arial,sans-serif;	
	font-size: 10pt;	
	letter-spacing: 1px;
	color: #0A9409;	
}

span.shortcutkey
{
	padding:0px 4px;	
	/*float:left;*/	
}

span.shortcutkey label
{
	font-size: 10pt;
	padding-right: 0px;
}

span.shortcutkey label.red
{
	padding-left: 0px;
}

/* Shopping Cart */
div#shoppingCart
{
/*
	height:200;
	overflow:auto;
	border: solid 1px #555555;
*/
}

table.scroll
{
	border-color: #000000;
	border-collapse: collapse;
}

table.scroll td.label
{	
	background-color: #D8D8D8;
}

table.scroll td.contentname
{
	background-color: #C9C9C9;
}

table.scroll td
{
	font-family: arial,sans-serif;	
	font-size: 9pt;	
	letter-spacing: 1px;	
	color: #000000;
	padding: 5px 5px 5px 5px;
	border-color: #000000;
	border-collapse: collapse;	
}

table.scroll th
{
	font-family: arial,sans-serif;	
	font-size: 9pt;
	font-weight: bold;
	letter-spacing: 1px;
	color: #FFFFFF;
	background-color: #909090;
	padding: 5px 5px 5px 5px;
	border-color: #000000;
	border-collapse: collapse;
}

table.scroll a
{
	font-family: arial,sans-serif;	
	font-size: 9pt;	
}

table.popup
{
	background-color: #FFFFFF;
	border: solid 1px #000000;
}

table.popup td
{
	font-family: arial,sans-serif;	
	font-size: 9pt;	
	letter-spacing: 1px;	
	color: #000000;
	padding: 5px 5px 5px 5px;
	border-color: #000000;
	border-collapse: collapse;
}

div.popup
{
	font-family: arial,sans-serif;	
	font-size: 9pt;	
	letter-spacing: 1px;
	position:absolute;
	display:none;
	background-color:#FFFFFF;
}


table.cart td.label div.popupDiv:hover
{
	background-color:#CDD4E9;;
}

table.cart td.contentname div.popupDiv:hover
{
	background-color:#CDD4E9;
}

/*
table.cart tr.popup:hover td.label,td.contentname
{
	background-color:#CDD4E9;	
}
*/

table.report
{
	border-color: #000000;
	border-collapse: collapse;
	font-family: arial,sans-serif;	
	font-size: 9pt;	
	letter-spacing: 1px;
}

table.cart tbody
{
/*
	style="height:200px;
	overflow:auto";
*/
}

.border
{
	border:solid 1px #000000;
}

.error
{
	background-color:#E83530;
	border:solid 1px #000000;
}

div.scrollpane
{
	overflow:auto;
	/*height:500px;*/
}


table.numericpad td
{
	text-align:center;
	v-align:middle;
}

div.key
{	
	width:50px;
	height:50px;
	border:solid 1px #000000;
	cursor:pointer;
	text-align:center;	
	vertical-align: middle;
	font-family: arial,sans-serif;	
	font-size: 14pt;
	font-weight:bold;	
}

a.button
{
	text-decoration: none;
	cursor: pointer;
	border: 0px;	
	font-family: arial,sans-serif;	
	font-size: 10pt;	
	letter-spacing: 1px;	
	font-weight: bolder;
	color: #606760;
	padding: 2px 4px 2px 4px;
	border:solid 1px #000000;
	text-align:center;	
	background-color: #F2F2F2;
}

a.button:hover
{
	background-color: #D8D8D8;
}

div#PINPanel
{
	border:solid 2px #000000;
	z-index:1000;
	height:120px;
	width:200px;	
	background-color: #F2F2F2;
	padding: 4px;
	display:none;
}

div#divBack
{
	background-image: url("<%= path + "images/pos/maskBG.png" %>");
    filter: alpha(opacity=40);
}

span.draftedcolor
{
	color:red;
}

div.draftedcolor
{
	color:red;
}

div.completecolor
{
	color:green;
}

div.inprogresscolor
{
	color:yellow;
}

div.closed
{
	color:blue;
}

div.negativecolor
{
	color:red;
}
div.nomalcolor
{
	color:black;
}

div#keyboard{
	position:absolute;
	background-color: #FFFFFF;
	border:solid 1px #000000;
	z-index:3000;
	display:none;				
}

div#uppercase{
	display:none;
}

div#lowercase{
	display:block;
}

div.keyboardHandler{
	background-color:#777777;
	height:20px;
	cursor:move;
}

table.keyboard{
	background-color: #FFFFFF;		
}

table.keyboard td{
	vertical-align:middle;
	text-align:center;
	width:50px;
	height:50px;
	border:solid 1px #444444;
	background-color: #FFFFFF;
	cursor:pointer;
	background-color:#F4F4F4;
	font-family: arial,sans-serif;	
	font-size: 13pt;	
	letter-spacing: 1px;	
	font-weight: bolder;
	color: #000000;
}

table.keyboard td:hover{
	background-color:#FFFFFF;
}

img#keyboardicon{
	display:none;
	cursor: pointer;
}

div.tooltip{
	width:200px;
	z-index:1000;
	background-color:#FDFDDB;
	position:absolute;
	border: solid 1px #000000;
	padding:4px;
	font-color:#333333;
	font-size:10pt;
}

div.pager{
	//border: dashed 1px #000000;
	padding:4px;
	font-color:#333333;
	font-size:10pt;
	text-align:center;
}

.pager label{
	color: #51585F;
	font-weight: normal;
	font-family: Verdana,Arial,Helvetica,san-serif;
}

div.button {
	float:left;
	border:solid 1px #333333;
	margin-right: 4px;
	margin-top: 4px;
	margin-bottom: 4px;
	padding:6px;
	font-size:14px;
	font-weight: bold;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	color:#FFFFFF;
	background-color:#333333;
	cursor:pointer;
}

div.pager a{
	font-color:#333333;
	font-size:10pt;
	text-decoration:none;
}

span.menu{
	text-decoration: none;
	color:#000000;
	padding:1px;
	font-family: arial,sans-serif;
	font-size: 10pt;
	letter-spacing: 1px;
	font-weight: bold;
}

a.nodecoration:active,a.nodecoration:visited{
	text-decoration:none;
}

fieldset.submenu{	
	height:165px;
}

.newbutton{
	background-repeat: repeat-x;
	cursor: pointer;
	font-family: verdana,arial,sans-serif;	
	font-size: 11pt;
	font-weight:bold;	
	letter-spacing: 1px;	
	font-weight: bolder;
	color: #666666;	
	height: 42px;
	/*min-width:87px;*/
	background-image: url("<%= path + "images/pos/buttons/button_middle.gif" %>");
	border-left: solid 1px #9b9b9b;
	border-right: solid 1px #9b9b9b;
	border-top-width: 0px;
	border-bottom-width: 0px;
	padding-left:4px;
	padding-right:4px;
}

fieldset#createcustomer{
	height:150px;
}

/*
.searchBox{
  background-image:url("<%= path + "images/pos/magnifying-glass.gif" %>");
  background-repeat:no-repeat;
  padding-left:20px;
}
*/

span.drafted
{
	color:red;
}

span.completed
{
	color:green;
}

span.inprogress
{
	color:yellow;
}

span.closed
{
	color:blue;
}

div#errorbox
{
	background-color:CC1515;
	border: solid 1px #D41616;
	padding: 4px;
}

div#msgbox
{
	background-color:#E6F3D8;
	border: solid 1px #75D416;
	padding: 4px;
}

/***************************************************************************/
div.popupwindow{
	display:inline;
	position:absolute;
	z-index:1000;
	border: solid 1px #636363;
	padding:0px;
	visibility:hidden;
}

div.popupwindow div{
	padding:4px;
}

div.popupwindow div.title{
	color:#FFFFFF;
	background:#ACACAC;
	font-weight:bold;
	/*font-size:13pt;*/
}

div.popupwindow div.footer{
	background:#EDEDED;
	text-align:right;
	background:#ACACAC;
}

div.popupwindow div.messagebody{
	background:#EDEDED;
}

div.popupwindow div.footer a{
	font-size:10pt;
}

div#tenderPanel{
	/*height:170px;*/
	width:300px;
}

div#customerInfoPanel{
	width:400px;
}

div.disableMask
{
	background-image: url("<%= path + "images/pos/maskBG.png" %>");
    filter: alpha(opacity=40);
}

div.ajaxindicator
{
	width:150px;
	padding:10px;
	border:solid 1px #000000;
	background-color:#FFFFFF;
	/*background-image: url("<%= path + "images/ajax-indicators/indicator.gif" %>");*/
	background-repeat:no-repeat;
	background-position:left center;
	padding-left:20px;
	
}

.redcolor
{
	color:#E83530;
}

td.loginHeader
{
	height: 210px;
	width: 800px;	
    background-image: url("<%= path + "images/pos/logo.jpg" %>");
	background-position:center center;
	background-repeat:no-repeat;	
}

td.headerLogo
{
	background-image: url("<%= path + "images/pos/logo.gif" %>");
	background-position:top left;
	background-repeat:no-repeat;
	height: 40px;
	width: 320px;		
}

td.headerDetails
{
	vertical-align: top;
	background-color:#EEEEEE;
}

a.sortheader
{
	color:#000;
}

input.calendar-icon
{
	width: 28px;
	height: 22px;
	background-image: url("<%= path + "images/pos/calendar.gif" %>");
}

.floatleft
{
	float: left;
	display: block;
	padding: 0px 2px;
}

#footerContainer{
	position: relative;
}

.footer{
background: #9A9A9A url("../images/newUI/footerbg.gif") repeat-x;
padding: 8px;
margin: 0;
height: 41px;
color: #FFFFFF;
font-family: tahoma, san-serif;
font-size: 11px;
font-weight: bold;
border-top: #FFFFFF solid 1px;
position: relative;
}

.roleButton{
	background: url("../images/newUI/pricedetbg.gif") repeat;
	border: 1px solid #FFF;
	line-height: 15px;
	float: left;
	height: 15px;
	width: 90px;
	font-family: tahoma, san-serif;
	font-weight: normal;
	color: #FFF;
	padding-top: 0px;
	padding-bottom: 0px;
	vertical-align: middle;
	text-align: center;
	cursor: pointer;
	margin-left: 15px;
	margin-top: 1px;
}

.buttonLabel{
	font-family: tahoma, san-serif;
	font-weight: normal;
	color: #FFF;
}

.button span{
	color: #FFFFFF;
}

#remainingDetailsBtn, #remainingDetailsBtn1, #switchBack, #switchBack1{

	float: right;
} 

#previousFirst div {
	float:left;
}

#previousFirst span{
	float: right;
}

#nextLast div{
	float: right;
}

#nextLast span{
	float:left;
}

#nextLast{
	margin-top: -17px;
}

.pagerContainer{
	border-top: solid 1px #FFFFFF;
	font-family:tahoma,san-serif;
	background: #9A9A9A
}

.pagerContainer a{
	color: #669ACF;
}
