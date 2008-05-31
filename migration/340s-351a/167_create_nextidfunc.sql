CREATE OR REPLACE FUNCTION nextidfunc (
   p_ad_sequence_id   IN   NUMBER,
   p_system           IN   CHAR
)
   RETURN NUMBER
IS
   PRAGMA AUTONOMOUS_TRANSACTION;
   tmpvar   NUMBER;
BEGIN
   Nextid (p_ad_sequence_id, p_system, tmpvar);
   COMMIT;
   RETURN tmpvar;
END nextidfunc;
/