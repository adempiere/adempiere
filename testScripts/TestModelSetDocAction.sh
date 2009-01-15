FILENAME=ADService.wsdl.$$
wget \
    --post-file=MsgModelSetDocAction \
    --save-headers \
    http://localhost:8081/ADInterface/services/ADService \
    -O $FILENAME
ls -l $FILENAME
cat $FILENAME
echo
rm -f $FILENAME
