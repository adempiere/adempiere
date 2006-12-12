UPDATE ad_sequence
   SET currentnextsys = 50000
 WHERE istableid = 'Y' AND currentnextsys < 50000;
