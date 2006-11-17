SELECT TableSpace_Name, SUM(Blocks) AS Free_Blk, 
  TRUNC(SUM(BYTES)/(1024*1024)) AS Free_MB,
  MAX(Bytes)/(1024) AS Big_Chunk_kB, COUNT(*) AS Num_Chunks
FROM DBA_FREE_SPACE
GROUP BY TableSpace_Name
/

