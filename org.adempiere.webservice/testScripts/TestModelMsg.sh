:
if [ $# -ne 1 ]
then
    echo "Usage: $0 Message_File
    echo " i.e.: $0 MsgModelSetDocAction
    exit 1
fi
FILENAME=ModelADService.wsdl.$$
wget \
    --no-check-certificate \
    --post-file=$1 \
    --save-headers \
    https://localhost:8443/ADInterface/services/ModelADService \
    -O $FILENAME
ls -l $FILENAME
cat $FILENAME
echo
rm -f $FILENAME
