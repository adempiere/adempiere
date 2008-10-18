<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>Tamak POS</title>
		<link rel="stylesheet" href="css/pos.css" type="text/css">
		<script src="js/enableButton.js"></script>			
	</head>
	<body>
		<table width="100%" height="100%">
		<tr>
			<td valign="middle" align="center">				
				<table align="center" border="1" cellpadding="0" cellspacing="0" class="content" width="50%" height="50%">
				<tr>
					<td>
						<div id="posLogo" onclick="javascript:window.location='POSLogin.do'" style="cursor:pointer;display:block;height:100%" >
						<table align="center" width="100%" height="100%">
						<tr>
							<td align="center" valign="bottom">							
								<img src="images/pos/logo_pos.gif"/>
								<p style="font-size:21"><pos:message key="posPassowrd.welcome"/></p>																	
							</td>
						</tr>
						<tr>
							<td align="center">
								<a href="POSLogin.do">
									<h1><pos:message key="enter"/></h1>
								</a>
							</td>
						</tr>
						</table>
						</div>
					
					</td>
				</tr>
				</table>								
			</td>
		</tr>
		</table>		
	</body>
</html>