:
if [ $# -ne 1 ]
then
    echo "Usage: $0 Message_File
    echo " i.e.: $0 MsgModelSetDocAction
    exit 1
fi
FILENAME=ModelADService.wsdl.$$
wget \
    --post-file=$1 \
    --save-headers \
    http://localhost:8081/ADInterface/services/ModelADService \
    -O $FILENAME
ls -l $FILENAME
cat $FILENAME
echo
rm -f $FILENAME
