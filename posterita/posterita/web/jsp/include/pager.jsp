<%--
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
 *
 * @author Praveen
--%>

<%@ page import="org.posterita.core.businesslogic.ElementManager" %>
<%@ page import="org.posterita.core.bean.ElementBean" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>
<!-- start of pager -->
<bean:size id="recordSize" name="<%=collection%>"/>
<%
int firstOffset = 0;
int lastOffset  = 0;
int currentOffset  = Integer.parseInt(offset);
int previousOffset = currentOffset - Integer.parseInt(length);
int nextOffset     = currentOffset + Integer.parseInt(length);

boolean isFirst = (currentOffset == 0);
boolean isLast  = ((currentOffset + Integer.parseInt(length)) >= recordSize.intValue());

ElementBean elementBean = null;

if(url.indexOf("?") != -1)
{
	url = url + "&";
}
else
{
	url = url + "?";
}		
%>
<div class="pagerContainer">
<div class="pager">
<logic:equal name="recordSize" value="0">
	<label>
		<bean:write name="recordSize"/><pos:message key="RecordFound"/>.
	</label>
</logic:equal>
<logic:notEqual name="recordSize" value="0">
	<label>
		<bean:write name="recordSize"/> <pos:message key="RecordFound"/>. <pos:message key="search.result.displaying"/> <%= currentOffset + 1 %> <pos:message key="to" /><%= (isLast) ? recordSize.intValue() : (Integer.parseInt(offset) + Integer.parseInt(length)) %>
	</label>
	<div class="space"></div>
<%
if(!isFirst){
	out.print("<div id='previousFirst'><div id='first'><a href='"+ url +"offset=0'>");
	out.print(ElementManager.getMsg(ctx, "first").getName());
	out.print("</a></div><span id='previous'><a href='"+ url +"offset="+ previousOffset +"'>");
	out.print(ElementManager.getMsg(ctx, "prev").getName());
	out.print("</a></span>");
}
int len = Integer.parseInt(length);

lastOffset = recordSize.intValue() - (recordSize.intValue() % len);
int b = currentOffset/len + 1;
int d = lastOffset/len;

if((recordSize.intValue() % len) != 0)
{
	d = d + 1;
}

b = (b < 1) ? 1 : b;

int a = b - 2;
int c = b + 2;

a = (a < 1) ? 1 : a;
c = (c < d) ? c : d;



if(Integer.parseInt(length) != recordSize.intValue())
{
	if(recordSize.intValue() > len)
	{	
		
		for(int i= a ; i < b ; i++)
		{
			out.print("<a href='"+ url +"offset=" + (i-1)*len + "'>" + i + "</a> ");
		}
	
		out.print(b + " ");
		
		for(int j= b+1 ; j < c+1 ; j++)
		{
			out.print("<a href='"+ url +"offset=" + (j-1)*len + "'>" + j + "</a> ");
		}
	}
	
	out.print("</div>");
	
	if(recordSize.intValue() > len){	
		out.print("<div id='all'><a href='"+ url + "offset=0&length=" + recordSize +"' onclick='return confirm(\"");
		out.print(ElementManager.getMsg(ctx, "display.all.records").getName());		
		out.print("\")'>");
		out.print(ElementManager.getMsg(ctx, "all").getName());
		out.print("</a></div>");
	}
	

	if(!isLast){
		out.print("<div id='nextLast'><div id='next'><a href='"+ url +"offset="+ nextOffset +"'>");
		out.print(ElementManager.getMsg(ctx, "next").getName());
		out.print("</a></div><span id='last'><a href='"+ url +"offset="+ lastOffset +"'>");
		out.print(ElementManager.getMsg(ctx, "last").getName());
		out.print("</a></span></div>");
	}
	
	
}
else
{
	out.print("<a href='"+ url +"offset=0'>");
	out.print(ElementManager.getMsg(ctx, "switch.to.paging").getName());
	out.print("</a>");
}

%>
</logic:notEqual>
</div>			
</div>		
<!-- end of pager -->	
