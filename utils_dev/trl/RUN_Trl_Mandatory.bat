@Rem	Extract only mandatory fields of translation files

@Rem	Parameter - file to process otherwise default
SET FILE=%1%
IF '%FILE%'=='' SET FILE=AD_Element_Trl_en_US.xml
IF '%ADEMPIERE_SOURCE%'=='' SET ADEMPIERE_SOURCE=D:\Adempiere
IF '%ADEMPIERE_TRL%'=='' SET ADEMPIERE_TRL=en_US

@Echo ... %FILE%

@java org.apache.xalan.xslt.Process -in %ADEMPIERE_SOURCE%\data\%ADEMPIERE_TRL%\%FILE% -xsl trl_Mandatory.xsl -out %ADEMPIERE_SOURCE%\data\%ADEMPIERE_TRL%\m_%FILE%

@pause