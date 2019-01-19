# Author + Copyright 1999-2005 Jorg Janke
# $Id: RUN_sqlj.sh,v 1.1 2005/05/31 07:28:21 jjanke Exp $
if [ $ADEMPIERE_HOME ]; then
  cd $ADEMPIERE_HOME/utils
fi
. ./myEnvironment.sh Server
echo 	Create Oracle SQLJ - $ADEMPIERE_HOME \($ADEMPIERE_DB_NAME\)

sh $ADEMPIERE_DB_PATH/create.sh $ADEMPIERE_DB_USER/$ADEMPIERE_DB_PASSWORD

