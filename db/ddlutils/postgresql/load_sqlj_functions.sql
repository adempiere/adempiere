
	CREATE SCHEMA adempiere;
	
	SET search_path TO adempiere,sqlj;
	
	SELECT sqlj.install_jar('file:///e-evolution/adempiere/trunk/sqlj/sqlj.jar', 'sqlj', true);
	
	SELECT sqlj.set_classpath('adempiere', 'sqlj');
	