<?xml version="1.0" encoding="iso-8859-1"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>JBoss Management Console - System Properties</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="StyleSheet" href="css/jboss.css" type="text/css"/>
</head>
<body>
<!-- header begin -->
	<div id="footer">
		<div id="footer_bar">&nbsp;</div>
	</div>
<!-- header end -->
<hr class="hide"/>
	<center>
	<div id="content">
		<div class="content_block" style="width: 100%;">
			<h3>JBoss&trade; System Properties</h3>
					<p>&nbsp;</p>
					
				<table border="1" cellpadding="1" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="85%" align="center" id="AutoNumber1">
<%
	java.util.Properties props = System.getProperties ();
	java.util.Iterator iter = props.keySet().iterator();
	
	while (iter.hasNext())
	{
		String key = (String)iter.next();	
		String value = props.getProperty (key);
%>
                  <tr>
                    <td align="left" valign="top"><font size="1"><%=key%></font>&nbsp;</td>
                    <td align="left" valign="top"><font size="1"><%=value%></font>&nbsp;</td>
                  </tr>
<%
	}
%>

            </table>
            <br/>    <br/>
		</div>
		<div class="spacer"><hr/></div>
	</div>
	</center>
<!-- content end -->

<hr class="hide"/>
<!-- footer begin -->
	<div id="footer">
		<div id="footer_bar">&nbsp;</div>
	</div>
<!-- footer end -->
</body>
</html>
