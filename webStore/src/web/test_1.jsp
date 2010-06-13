<%@ include file="/WEB-INF/jspf/page.jspf" %>
<html>
<!--
  - Author:  Jorg Janke
  - Version: $Id: test_1.jsp,v 1.2 2006/05/06 00:41:33 mdeaelfweald Exp $
  - Adempiere ERP & CRM Smart Business Solution - Copyright (c) 1999-2003 Jorg Janke
  - - -
  - Web Store Interest Area
  -->
<head>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<title><c:out value='${ctx.name}'/>- My Test Area</title>
</head>
<body><div id="page">
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div id="main">
    <%@ include file="/WEB-INF/jspf/menu.jspf" %>
    <%@ include file="/WEB-INF/jspf/vendor.jspf" %>
    <div id="content"><h1>WebStore Links</h1>
      <p>Add Product to Web Store: <a href="/wstore/basketServlet?M_Product_ID=128">test Az</a> - <a href="/wstore/basketServlet?M_Product_ID=123">test Oak</a></p>
      <p>Create Request for Sales Rep: <a href="/wstore/request.jsp?SalesRep_ID=102">test GU </a>  - <a href="/wstore/request.jsp?SalesRep_ID=101">test GA</a></p>
      <h1>EL Test</h1>
      <p>\${1.2 + 2.3} = ${1.2 + 2.3}</p>
      <h1>Test for Each</h1>
      <c:forEach var="i" begin="1" end="10" step="1"> <c:out value='${i}'/> <br />
      </c:forEach>
      <h1>Form</h1>
      <form name="form1" method="post" action="">
        <p>
          <input name="myField" type="text" id="myField">
        </p>
        <p>
          <textarea name="myText" id="myText"></textarea>
        </p>
        <p>
          <select name="myList" size="1" id="myList">
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
          </select>
        </p>
        <p>
          <input type="submit" name="Submit" value="Submit">
        </p>
      </form>
      <h1>Table</h1>
      <table  border="1" cellspacing="2" cellpadding="2" summary="Summary">
        <caption>
        caption
        </caption>
        <tr>
          <th scope="col">c1</th>
          <th width="10" nowrap scope="col">c2</th>
          <th scope="col">c3</th>
          <th scope="col">c4</th>
        </tr>
        <tr>
          <td>c1</td>
          <td width="10" nowrap>c2</td>
          <td>c3</td>
          <td>c4</td>
        </tr>
        <tr>
          <td>c1</td>
          <td width="10" nowrap>c2</td>
          <td>c3</td>
          <td>c4</td>
        </tr>
      </table>
    <p>&nbsp;</p></div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div></body>
</html>
