<!-- draftedCreditOrder.jsp -->
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.Constants" %>

<%@ page import="org.posterita.struts.pos.POSOrderAction" %>
<%@ page import="org.posterita.beans.ProductBean" %>
<%@ page import="org.posterita.user.WebUserInfo" %>
<%@ page import="org.posterita.lib.UdiConstants" %>


<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/dcs.tld" prefix="dcs" %>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<bean:define id="title"><pos:message textOnly="true" key="smenu.credit.sales"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>   
<%@ include file="/jsp/include/errors.jsp" %>  
	  						   
<table class="main">	

<html:form action="/CreateCreditOrderAction">
	<%@ include file="/jsp/include/draftedCreditOrder.jsp" %>   
</html:form>


<%@ include file="/js/draftedPOSOrder.js" %>

<script>

<logic:present name="<%= Constants.CURRENT_POS_ORDER_ID  %>">
<bean:define id="posOrderId">
	<bean:write name="<%= Constants.CURRENT_POS_ORDER_ID  %>"/>
</bean:define>
$FElement('edit').onclick = function(){
	window.location ='GetShoppingCartForOrder.do?action=getOrderShoppingCart&orderId=<%= posOrderId %>';
};				
</logic:present>	

function completeOrder(btn)
{
		setAction(document.forms[0],'CompleteCreditOrderAction.do','completeCreditOrder');
		disableButtons();
}

function recall()
{
	window.location = "CreateCreditOrder.do";
}

function deleteSelected()
{	
	if(confirmDelete(''))
		{
			window.location = "DeleteCreditOrderAction.do?action=deletePOSOrder";
		}
}

</script>
       		
<%@ include file="/jsp/include/posFooter.jsp" %>	
