<%@ page contentType="text/css;charset=UTF-8" %>
<%@ taglib uri="http://www.zkoss.org/dsp/web/core" prefix="c" %>
<%@ taglib uri="http://www.zkoss.org/dsp/zk/core" prefix="z" %>
${z:setCSSCacheControl()}

<c:set var="val" value="${c:property('org.zkoss.zul.theme.fontSizeM')}"/>
<c:set var="fontSizeM" value="${val}" scope="request" unless="${empty val}"/>
<c:set var="val" value="${c:property('org.zkoss.zul.theme.fontSizeMS')}"/>
<c:set var="fontSizeMS" value="${val}" scope="request" unless="${empty val}"/>
<c:set var="val" value="${c:property('org.zkoss.zul.theme.fontSizeS')}"/>
<c:set var="fontSizeS" value="${val}" scope="request" unless="${empty val}"/>
<c:set var="val" value="${c:property('org.zkoss.zul.theme.fontSizeXS')}"/>
<c:set var="fontSizeXS" value="${val}" scope="request" unless="${empty val}"/>
<c:set var="val" value="${c:property('org.zkoss.zul.theme.fontFamilyT')}"/>
<c:set var="fontFamilyT" value="${val}" scope="request" unless="${empty val}"/>
<c:set var="val" value="${c:property('org.zkoss.zul.theme.fontFamilyC')}"/>
<c:set var="fontFamilyC" value="${val}" scope="request" unless="${empty val}"/>

<c:set var="fontSizeM" value="12px" scope="request" if="${empty fontSizeM}"/>
<c:set var="fontSizeMS" value="11px" scope="request" if="${empty fontSizeMS}"/>
<c:set var="fontSizeS" value="11px" scope="request" if="${empty fontSizeS}"/>
<c:set var="fontSizeXS" value="10px" scope="request" if="${empty fontSizeXS}"/>

<c:set var="fontFamilyT" value="arial, sans-serif"
	scope="request" if="${empty fontFamilyT}"/><%-- title --%>
<c:set var="fontFamilyC" value="arial, sans-serif"
	scope="request" if="${empty fontFamilyC}"/><%-- content --%>
p,span {
	font-family: ${fontFamilyC};font-size: ${fontSizeM}; font-weight: normal;
}
h1 {
	font-family: ${fontFamilyT};
	font-size: x-large; font-weight: bold; color: #250070;
	letter-spacing: -1px; margin-top: 3pt;
}
h2 {
	font-family: ${fontFamilyT};
	font-size: large; font-weight: bold; color: #200066;
}
h3 {
	font-family: ${fontFamilyT};
	font-size: medium; font-weight: bold; color: #100050;
}
h4 {
	font-family: ${fontFamilyT};
	font-size: small; font-weight: bold; color: #346B93;
}
h5 {
	font-family: ${fontFamilyT};
	font-size: small; font-weight: bold; color: #4BA7D2;
}
h6 {
	font-family: ${fontFamilyT};
	font-size: small; font-weight: normal; color: #404040;
}

h1 em {color: #dd0000}

dt {
	margin: 0.5em 0 0.3em 0;
	font-weight: bold;
}
dd {
	margin: 0 0 0 0.8em;
}

li, dt, dd, pre, body {
	font-family: ${fontFamilyC};
	font-weight: normal;
	font-size: ${fontSizeM};
}

li	{
	margin-top: 2pt
}

code {
	font-family: "Lucida Console", "Courier New", Courier, mono;
	font-weight: normal;
}
dfn {
	font-family: "Lucida Console", "Courier New", Courier, mono;
	font-style: normal;
}

<%-- The hyperlink's style class. --%>
.link {cursor: pointer;}
