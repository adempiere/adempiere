

/**
 *	Rebuild Indexes with BLevel >= 4
 */
--SELECT Index_Name, BLevel FROM USER_Indexes WHERE BLevel >= 4
	--ALTER INEX x REBUILD

/**
 *	Hit rate of index
 */	
SELECT	i.Index_Name, i.Table_Name, i.Distinct_Keys, t.Num_Rows,
	ROUND(t.Num_Rows/i.Distinct_Keys,2) AS "Rows Per Key",
	ROUND((t.Num_Rows/i.Distinct_Keys)/t.Num_Rows*100,6) AS "% Hit Rate", i.Uniqueness
FROM 	User_Indexes i, User_Tables t
WHERE	i.Table_Name = t.Table_Name
AND i.Distinct_Keys > 0 AND t.Num_Rows > 50
AND t.Num_Rows > i.Distinct_Keys
ORDER BY 6 DESC

/**
 * LibraryCache - GetHitRate should be > 95% and ReloadRatio < 1%
 */
SELECT NameSpace, Gets, GetHits, ROUND(GetHitRatio,6) AS GetHitRatio, 
	Reloads, Pins, ROUND(Reloads/Pins,6) AS ReloadRatio, Invalidations
FROM V$LibraryCache 
WHERE (GetHitRatio < 0.95 OR Reloads/Pins > 0.01) 
AND NameSpace NOT IN ('INDEX', 'CLUSTER', 'OBJECT', 'PIPE')
ORDER BY GetHitRatio;

/**
 *	Data Dictionary - Hit Ratio should be > 85%
 */
SELECT Parameter, Gets, GetMisses, ROUND(GetMisses/Gets,6) AS DD_HitRatio
FROM v$RowCache
WHERE Gets > 0 AND GetMisses/Gets < .85
ORDER BY 4 DESC;

/**
 *	Buffer Cache - Buffer Cache Hit Ratio should be > 90%
 */
SELECT  physical.value "Physical Reads",
	ROUND(1-(physical.value / (blockgets.value + consistent.value)),6) "Buffer Cache Hit Ratio"
FROM v$sysstat physical, v$sysstat blockgets, v$sysstat consistent
WHERE physical.name = 'physical reads'
  AND blockgets.name = 'db block gets'
  AND consistent.name = 'consistent gets';

/**
 *	Cached Tables
 */
SELECT Table_Name
FROM USER_TABLES WHERE TRIM(CACHE) = 'Y';
ALTER TABLE AD_Window CACHE;
ALTER TABLE AD_Val_Rule CACHE;
ALTER TABLE AD_Field_Trl CACHE;
ALTER TABLE AD_Tab_Trl CACHE;
ALTER TABLE AD_Window_Trl CACHE;
ALTER TABLE AD_FieldGroup CACHE;
ALTER TABLE AD_FieldGroup_Trl CACHE;
ALTER TABLE AD_Message CACHE;
ALTER TABLE AD_Message_Trl CACHE;

/**
 *	DD Buffer Size
 */
SELECT o.object_name, o.object_type, COUNT(DISTINCT bh.block#) "# Buffers"
FROM USER_OBJECTS o, v$bh bh
WHERE o.object_ID=bh.objd
GROUP BY o.object_name, o.object_type
ORDER BY 2; --4 DESC;

/**
 *	Buffer Pools
 *	Init.ora:
 *		DB_BLOCK_SIZE = 8192		# 8k
 *		DB_BLOCK_BUFFERS = 64000	# 500 MB total - for Default 300 
 *		DB_BLOCK_LRU_LATCHES = 30	# total
 *		BUFFER_POOL_KEEP = (BUFFERS: 19200, LRU_LATCHES: 10)	# 150MB of 500
 *		BUFFER_POOL_RECYCLE = (BUFFERS: 6400, LRU_LATCHES: 5)	# 50 MB of 500
 */
--	What Pools do we have
SELECT Name, Set_Count "Latches", Buffers
FROM v$buffer_pool;
--	What is not in the Defualt pool
SELECT Segment_type, Segment_Name, Buffer_Pool
FROM USER_SEGMENTS
WHERE Buffer_Pool != 'DEFAULT';
--
--ALTER TABLE x STORAGE (BUFFER_POOL KEEP);
--	Buffer Pool Hit Rate
SELECT Name "Buffer Pool", Physical_Reads, DB_Block_Gets, Consistent_Gets,
	1-(Physical_Reads / DECODE(DB_Block_Gets+Consistent_Gets,0,1,DB_Block_Gets+Consistent_Gets)) "Hit Ratio"
FROM v$buffer_pool_statistics;

/**
 *	Redo Log Buffer - Redo Log Retry Ration should be < 1%
 *	Init.pra
 *		LOG_BUFFERS should be multiple of OS block (Win 4k)
 */
SELECT entries.value "redo entries",
	retries.value "redo buffer allocation retries", 
	requests.value "redo log space requests",
	ROUND(retries.value/entries.value,6) "Redo Log Retry Ratio"
FROM v$sysstat retries, v$sysstat entries, v$sysstat requests
WHERE retries.name = 'redo buffer allocation retries'
AND entries.name = 'redo entries'
AND requests.name = 'redo log space requests';
--	Wait Time
SELECT * 
FROM v$system_event 
WHERE Event LIKE 'log file switch%';

--	Not logged tablespaces
SELECT TableSpace_Name, Logging
FROM DBA_TableSpaces;

--	Not Logged Tables
SELECT Table_Name, Logging
FROM USER_Tables
WHERE Logging <> 'YES'
--ALTER TABLE x NOLOGGING;

/**
 *	IO
 *	Init.ora
 *		DB_FILE_MULTIBLOCK_READ_COUNT = 	# >8
 */
--	Table Scans
SELECT Name, Value
FROM v$sysstat
WHERE name LIKE 'table scans%';

--	Locally Managed Tablespaces
SELECT TableSpace_Name, Extent_Management
FROM DBA_TableSpaces;

--	Waiting on DBW0
SELECT *
FROM v$system_event
WHERE Event in ('buffer busy waits', 'db file parallel write');
--
SELECT scanned.value "DBWR buffers scanned", scans.value "DBWR lru scans",
	ROUND(scanned.value/scans.value,2) "Avg # Buffers Scanned"
FROM v$sysstat scanned, v$sysstat scans
WHERE scanned.name = 'DBWR buffers scanned'
AND scans.name = 'DBWR lru scans';

-- Avoiding Dynamic Extent Allocation 
SELECT Table_Name, Num_Rows, Blocks, Empty_Blocks,
	ROUND(1-(Empty_Blocks/(Empty_blocks+Blocks)),2) "Blocks Used Ratio"
FROM USER_Tables
WHERE Blocks IS NOT NULL
  AND 1-(Empty_Blocks/(Empty_blocks+Blocks)) > .95
ORDER BY 5 DESC;

--	Chained Rows & Migration
SELECT Table_Name, Chain_Cnt
FROM USER_Tables
WHERE Chain_Cnt > 0;
--
SELECT Name, Value 
FROM v$sysstat
WHERE Name = 'table fetch continued row';

/**
 *	Sorting - Memory Sort Ratio should > 95%
 *	Init.ora
 *		SORT_AREA_SIZE = 262144		# 256k = Multiple of Temp Initial/Next (64k)
 *		SORT_MULTIBLOCK_READ = 8	# (Sort_Area_Size)/BlockSize - here 64k
 */
SELECT mem.value "sorts (memory)", disk.value "sorts (disk)",
	ROUND(mem.value/(disk.value+mem.value),6) "Memory Sort Ratio"
FROM v$sysstat mem, v$sysstat disk
WHERE mem.name = 'sorts (memory)'
AND disk.name = 'sorts (disk)';
--	Sort Segment
SELECT TableSpace_Name, Current_Users, Max_Sort_Blocks
FROM v$sort_segment

/**
 *	Rollback
 */
--	Waiting for ..
select * from v$waitstat where class like '%undo%';
select * from v$system_event where event like '%undo%';
SELECT name, value "reads from rbs" FROM v$sysstat WHERE name = 'consistent gets';

-- Get Ration shound be > 95% - wraps from one extent to another (high: too small)
SELECT n.name, s.gets, s.waits, 
	DECODE (s.waits,0,1, 1-(s.waits/s.gets)) "RBS Get Ratio",
	s.wraps, s.extends
FROM v$rollstat s, v$rollname n
WHERE s.usn = n.usn;

/**
 *	Latches - cache buffer key chain should be >= 99
 *	Init.ora
 *		DB_BLOCK_LRU_LATCHES = 3		# 6*CPU=(6) max DB_BLOCK_BUFFERS/50=(300)
 */
SELECT name, gets, misses, sleeps, 1-(sleeps/gets) "Hit Ratio"
FROM v$latch
WHERE gets > 0
AND name = 'cache buffers lru chain'
ORDER BY 5 

/**
 *	Locks
 */
SELECT s.username, l.type, o.owner, o.object_name, o.object_type
FROM v$session s, v$lock l, dba_objects o
WHERE s.sid=l.sid
AND o.object_id=l.id1
AND s.username IS NOT NULL;

/**
 *	User Memory
 */
SELECT ROUND(SUM(Value)/1024,0) "Session UGA kB"
FROM v$sesstat s, v$statname n
WHERE s.statistic#=n.statistic#
AND n.name='session uga memory';
select * from v$sysstat where name like '%uga%';

/**
 *	Sizing
 *		SGA	Total - 55% of physical memory (60-75% if > 1GB)
 *			SharedPool = 45%
 *			DatabaseBuffer = 45%
 *			ReDo Log = 10%
 *
 *	Rollback Segments
 *		initial=512k  Next=512k  MinExtent=20
 */
-- init.ora
select name, value, description, isdefault from v$parameter order by type, name;
