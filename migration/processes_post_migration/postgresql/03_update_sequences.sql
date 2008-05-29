CREATE OR REPLACE FUNCTION update_sequences() RETURNS void as $func$
-- TODO: Currently not inserting new sequences
DECLARE
   cmdsys           VARCHAR (1000);
   cmdnosys         VARCHAR (1000);
   cmdseq           VARCHAR (1000);
   cmdupd           VARCHAR (1000);
   currentnextsys   NUMERIC (10);
   currentnext      NUMERIC (10);
   currentseqsys    NUMERIC (10);
   currentseq       NUMERIC (10);
   ok               BOOLEAN;
   r                RECORD;
BEGIN

   FOR r IN (SELECT   tablename
                 FROM AD_TABLE t
                WHERE EXISTS (
                         SELECT 1
                           FROM AD_COLUMN c
                          WHERE t.ad_table_id = c.ad_table_id
                            AND c.columnname = t.tablename || '_ID')
             ORDER BY 1)
   LOOP
      cmdsys :=
            'SELECT  MAX ('
         || r.tablename
         || '_id) currentnextsys FROM '
         || r.tablename
         || ' where '
         || r.tablename
         || '_id<1000000';

      ok := true;
      BEGIN
         EXECUTE cmdsys INTO currentnextsys;
      EXCEPTION
         WHEN OTHERS
         THEN
            ok := false;
      END;

    IF ok THEN
      IF currentnextsys IS NULL
      THEN
         currentnextsys := 0;
      END IF;

      SELECT INTO currentnextsys DECODE (SIGN (currentnextsys - 50000),
                     -1, 50000,
                     NVL (currentnextsys + 1, 50000)
                    );

      cmdnosys :=
            'SELECT  MAX ('
         || r.tablename
         || '_id) currentnext FROM '
         || r.tablename
         || ' where '
         || r.tablename
         || '_id>=1000000';

      EXECUTE cmdnosys INTO currentnext;

      IF currentnext IS NULL
      THEN
         currentnext := 0;
      END IF;

      SELECT INTO currentnext DECODE (SIGN (currentnext - 1000000),
                     -1, 1000000,
                     NVL (currentnext + 1, 1000000)
                    );

      cmdseq :=
            'SELECT currentnext, currentnextsys FROM AD_Sequence '
         || 'WHERE Name = '''
         || r.tablename
         || ''' AND istableid = ''Y''';

      EXECUTE cmdseq INTO currentseq, currentseqsys;

      IF currentnextsys <> currentseqsys OR currentnext <> currentseq
      THEN
         cmdupd :=
               'update ad_sequence set currentnextsys = '
            || currentnextsys
            || ', currentnext='
            || currentnext
            || ' where name='''
            || r.tablename
            || ''' and istableid=''Y''';

         EXECUTE cmdupd;
      END IF;
    END IF;

   END LOOP;
END;
$func$ LANGUAGE plpgsql;

select update_sequences();

commit;