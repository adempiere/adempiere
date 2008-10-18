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


<%
java.util.Calendar c = java.util.Calendar.getInstance();
int curYear = c.get(java.util.Calendar.YEAR);
%>


<html:option value="<%=String.valueOf(curYear-1)%>"><%=curYear-1%></html:option>
<html:option value="<%=String.valueOf(curYear)%>"><%=curYear%></html:option>
<html:option value="<%=String.valueOf(curYear+1)%>"><%=curYear+1%></html:option>
<html:option value="<%=String.valueOf(curYear+2)%>"><%=curYear+2%></html:option>
<html:option value="<%=String.valueOf(curYear+3)%>"><%=curYear+3%></html:option>
<html:option value="<%=String.valueOf(curYear+4)%>"><%=curYear+4%></html:option>
<html:option value="<%=String.valueOf(curYear+5)%>"><%=curYear+5%></html:option>
