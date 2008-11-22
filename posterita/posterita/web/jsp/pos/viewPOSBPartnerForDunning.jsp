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
 * @author Alok
--%>


<!--viewPOSBPartnerForDunning.jsp-->
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.beans.BPartnerInfoBean" %>
<%@ page import="org.posterita.user.WebUserInfo" %>
<%@ page import="org.posterita.lib.UdiConstants" %>
<%@ page import="org.posterita.struts.pos.BpartnerInfoAction" %>
<%@ page import="org.posterita.struts.pos.DunningAction" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos" %>

<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/LoginHome.do"/>
</logic:notPresent>

<bean:define id="title"><pos:message key="smenu.dunning.letters" textOnly="true"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %> 

<html:form action="/PrintDunningLettersAction">
<html:hidden property="action" value="<%=DunningAction.PRINT_DUNNING_LETTERS %>"/>	

<logic:present name="<%=Constants.DEBTORS%>">
<logic:notEmpty name="<%=Constants.DEBTORS%>">

<div class="scrollpane">			
<table class="display sortable" border="1" id="1111">
	<tr>
		<th><pos:message key="Name"/></th>
		<th class="currency"><pos:message key="Revenue"/></th>
		<th class="currency"><pos:message key="SO_CreditLimit"/></th>
		<th class="currency"><pos:message key="SO_CreditUsed"/></th>
		<th class="currency"><pos:message key="OpenAmt"/></th>
		<th><pos:message key="select"/></th>
	</tr>	
	
<%
	String url = "ViewBPartnersForDunning.do";
	String collection = Constants.DEBTORS;
%>	
	
	<logic:iterate id="element" offset="<%=offset%>" length="<%=length%>" indexId="count" name="<%=collection %>" type="org.posterita.beans.BPartnerInfoBean">
	
	<tr>	
		<%
			String styleClass = "label";
			if ((count.intValue()%2) != 0)
				styleClass = "contentname";
		%>
		
						
	   <td class=<%=styleClass%>>
	   			
	   		<html:link href="<%="ViewBPartnerTrxDetailsAction.do?action=getBpartnerTrxDetails&isCustomer="+element.getIsCustomer()+"&bpartnerId=" + element.getBpartnerId() %>">
				<bean:write name="element" property="partnerName"/>
			</html:link>		
	   </td>						
	   <td class=<%=styleClass%>>
			<bean:write name="element" property="revenue"/>
			<html:hidden property="dunningId" value="<%=element.getDunningId()+""%>"/>
	   </td>						   
	   <td class=<%=styleClass%>>
			<bean:write name="element" property="creditLimit"/>
	   </td>						   
	   <td class=<%=styleClass%>>
	   		<%
				String styleClass1 = "nomalcolor";
				if(element.getCreditUsed().intValue()> element.getCreditLimit().intValue())
				styleClass1= "negativecolor";
			%>
	   		<div class="<%=styleClass1%>">
			<bean:write name="element" property="creditUsed"/>
			</div>
	   </td>
	   <td class=<%=styleClass%>>
			<%
				String styleClass2 = "nomalcolor";
				if(element.getOpenAmt().intValue()> 0)
				styleClass2= "negativecolor";
			%>
			<div class="<%=styleClass2%>">
			<bean:write name="element" property="openAmt"/>
			</div>
	   </td>
	  	<td class=<%=styleClass%>>
			<html:multibox property="bpartnerIds" value="<%=element.getBpartnerId().toString()%>">
            </html:multibox>			
		</td>			
	</tr>
	</logic:iterate> 

</table>

<%@ include file="/jsp/include/pager.jsp" %>

</div>

</logic:notEmpty>
</logic:present>





<div class="space"></div>
<div align="right">
	<input class="newbutton" type="button" onclick="selectAll()" value="Select All">
	<input class="newbutton" type="button" onclick="reset()" value="Reset">
</div>
<div class="space"></div>
<div align="right">	
	<html:submit styleClass="newbutton">
	        	Print
   	</html:submit>
 </div>  								
</html:form>
			
<script>					
	function selectAll()
	{
		var bpartnerIds=document.getElementsByName('bpartnerIds');
		for(var i=0;i<bpartnerIds.length;i++)
		{	
			bpartnerIds[i].checked='checked';
		}
	}
	
	
	function reset()
	{
		var bpartnerIds=document.getElementsByName('bpartnerIds');
		for(var i=0;i<bpartnerIds.length;i++)
		{	
			bpartnerIds[i].checked='';
		}
	}

</script>
	    									 					 
<%@ include file="/jsp/include/posFooter.jsp" %>
