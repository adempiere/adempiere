#
cd /home/adempiere/Adempiere/utils
rm nohup.out
nohup ./RUN_Server2.sh & 
echo After the server started enter: Ctrl-Z
echo Followed by the shell command:  bg
echo Waiting ....
sleep 5
tail -f nohup.out
