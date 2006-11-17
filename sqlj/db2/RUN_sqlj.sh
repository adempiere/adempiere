if [ $ADEMPIERE_HOME ]; then
  cd $ADEMPIERE_HOME/utils
fi
. ./myEnvironment.sh Server
echo 	Create DB2 SQLJ - $ADEMPIERE_HOME \($ADEMPIERE_DB_NAME\)

sh $ADEMPIERE_DB_PATH/create.sh $ADEMPIERE_DB_USER/$ADEMPIERE_DB_PASSWORD

