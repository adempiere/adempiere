/*
 * Name:		DBEngine.java
 * Description:	common class for database compatibility engines
 * Created:		Jan 30, 2009
 * Vendor:		K.K. Alice
 * Author:		Stefan Christians
 *
 * FileTarget:	~/development/sandbox/migrate/src/com/kkalice/adempiere/migrate/DBEngine.java
 * FileOwner:	spc.dvp
 * FilePerms:	0644
 *
 */

/**
 * This file is part of Adempiere ERP Bazaar
 * http://www.adempiere.org
 *
 * Copyright (C) Stefan Christians
 * Copyright (C) Contributors
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 *
 * Contributors:
 * - Stefan Christians
 *
 * Sponsors:
 * - K.K. Alice
 *
 */

package com.kkalice.adempiere.migrate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;


/**
 * common class for database compatibility engines
 * @author Stefan Christians
 */
public class DBEngine {

	// DATABASES
	// =========

	/**
	 * list of classes implementing the DBEngine interface
	 * <p>
	 * The names of new database interfaces should be added here
	 * as strings.
	 */
	private final static List <String>  m_implementations = Arrays.asList(
			"DBEngine_Postgresql",
			"DBEngine_Oracle"
			);

	// MEMBERS
	// =======

	/** static parameters */
	private static Parameters s_parameters = null;
	/** static logger */
	private static MigrateLogger s_logger = null;
	/** this dbEngine */
	private static DBEngine s_dbEngine = null;

	/** database specific interfaces */
	private static HashMap <Integer, DBEngineInterface> m_interfaces = null;
	/** internal vendor IDs of different databases */
	private static HashMap <String, Integer> m_vendorIDs = null;

	// error handling
	/** database errors exist */
	private boolean m_isDBError = false;
	/** SQL errors exist */
	private boolean m_isSQLError = false;
	/** number of errors in current transaction */
	private int m_transactionErrors = 0;



	// NUMERIC DATA TYPES

	// NUMERIC: Integer
	/** small-range integer (2 bytes) -32,768 to +32,767 */
	public final static int SMALLINT = 101110;
	/** same as "smallint"*/
	public final static int INT2 = 101111;
	/** normal integer (4 bytes) -2,147,483,648 to +2,147,483,647 */
	public final static int INTEGER = 101120;
	/** same as "integer"*/
	public final static int INT4 = 101121;
	/** same as "integer"*/
	public final static int INT = 101122;
	/** special oracle implementation of "integer"*/
	public final static int PLS_INTEGER = 101123;
	/** special oracle implementation of "integer"*/
	public final static int BINARY_INTEGER = 101124;
	/** non-negative "integer"*/
	public final static int NATURAL = 101125;
	/** non-negative "integer"*/
	public final static int POSITIVE = 101126;
	/** non-nullable "integer"*/
	public final static int SIMPLE_INTEGER = 101127;
	/** non-negative, non-nullable "integer"*/
	public final static int NATURALN = 101128;
	/** non-negative, non-nullable "integer"*/
	public final static int POSITIVEN = 101129;
	/** large-range integer (8 bytes) -9,223,372,036,854,775,808 to +9,223,372,036,854,775,807 */
	public final static int BIGINT = 101140;
	/** same as "bigint"*/
	public final static int INT8 = 101141;

	// NUMERIC: Arbitrary Precision
	/** user-specified exact precision (variable size) range: no limit*/
	public final static int NUMERIC = 101210;
	/** same as "numeric"*/
	public final static int DECIMAL = 101211;
	/** same as "numeric"*/
	public final static int DEC = 101212;
	/** same as "numeric"*/
	public final static int NUMBER = 101213;

	// NUMERIC: Floating Point
	/** single precision (6 digits) inexact floating point (4 bytes) 1E-37 to 1E+37 */
	public final static int REAL = 101310;
	/** same as "real" */
	public final static int FLOAT4 = 101311;
	/** special oracle implementation of "real" */
	public final static int BINARY_FLOAT = 101313;
	/** double precision (15 digits) inexact floating point (8 bytes) 1E-307 to 1E+308 */
	public final static int DOUBLE_PRECISION = 101320;
	/** same as "double precision" */
	public final static int FLOAT8 = 101321;
	/** special oracle implementation of "real" */
	public final static int BINARY_DOUBLE = 101322;
	/** oracle equivalent of "double precision" */
	public final static int FLOAT = 101323;

	// NUMERIC: Serial
	/** auto-incrementing normal integer (4 bytes) -2,147,483,648 to +2,147,483,647 */
	public final static int SERIAL = 101420;
	/** same as "serial" */
	public final static int SERIAL4 = 101421;
	/** auto-incrementing large-range integer (8 bytes) -9,223,372,036,854,775,808 to +9,223,372,036,854,775,807 */
	public final static int BIGSERIAL = 101430;
	/** same as "bigserial"*/
	public final static int SERIAL8 = 101431;


	// MONETARY TYPES
	/** currency amount (8 bytes) -92,233,720,368,547,758.08 to +92,233,720,368,547,758.07 */
	public final static int MONEY = 102110;


	// CHARACTER TYPES
	/** start of char types */
	public final static int CHARTYPE_START = 103000;

	// CHARACTER: Fixed Length
	/** fixed-length blank-padded string */
	public final static int CHAR = 103110;
	/** same as "char" */
	public final static int CHARACTER = 103111;
	/** "char" in national language */
	public final static int NCHAR = 103120;
	/** postgres internal system catalog use, 64 characters */
	public final static int NAME = 103130;

	// CHARACTER: Limited Variable Length
	/** variable length string with limit */
	public final static int VARCHAR = 103210;
	/** same as "varchar" */
	public final static int CHARACTER_VARYING = 103211;
	/** same as "varchar" */
	public final static int CHAR_VARYING = 103212;
	/** special oracle implementation of "varchar" */
	public final static int VARCHAR2 = 103213;
	/** "varchar" in national language */
	public final static int NVARCHAR = 103220;
	/** same as "nvarchar" */
	public final static int NCHAR_VARYING = 103221;
	/** same as "nvarchar" */
	public final static int NATIONAL_CHAR_VARYING = 103222;
	/** same as "nvarchar" */
	public final static int NATIONAL_CHARACTER_VARYING = 103223;
	/** special oracle implementation of "nvarchar" */
	public final static int NVARCHAR2 = 103224;
	/** oracle "string" data type */
	public final static int STRING = 103230;
	/** variable length string up to 2GB */
	public final static int LONG = 103240;

	// CHARACTER: Unlimited Variable Length
	/** variable length string without limit */
	public final static int TEXT = 103310;
	/** variable length string without limit */
	public final static int CLOB = 103320;
	/** "clob" in national language */
	public final static int NCLOB = 103321;


	// BINARY TYPES
	/** start of binary types */
	public final static int BINTYPE_START = 104000;

	/** variable length binary data up to 5 bytes */
	public final static int MLSLABEL = 104110;
	/** variable length binary data up to 2,000 bytes */
	public final static int RAW = 104120;
	/** variable length binary data up to 2GB */
	public final static int LONG_RAW = 104130;
	/** variable length binary data without limit */
	public final static int BYTEA = 104210;
	/** unstructured binary */
	public final static int BLOB = 104310;
	/** unstructured binary data stored in files outside the database */
	public final static int BFILE = 104320;

	/** end of binary types */
	public final static int BINTYPE_END = 104399;

	/** end of char types */
	public final static int CHARTYPE_END = 104999;


	// DATE/TIME TYPES

	// DATE/TIME: Date
	/** start of date types */
	public final static int DATETYPE_START = 105100;
	/** (4 bytes) dates only 4713 BC - 5874897 AD (1 day) */
	public final static int DATE = 105110;
	/** end of date types */
	public final static int DATETYPE_END = 105199;

	// DATE/TIME: Time
	/** start of time types */
	public final static int TIMETYPE_START = 105200;
	/** (8 bytes) times of day only 00:00:00 - 24:00:00 (1 microsecond / 14 digits) */
	public final static int TIME = 105210;
	/** same as "time" */
	public final static int TIME_WITHOUT_TIME_ZONE = 105211;
	/** (12 bytes) times of day only, with time zone 00:00:00+1459 - 24:00:00-1459 (1 microsecond / 14 digits) */
	public final static int TIMETZ = 105220;
	/** same as "timetz" */
	public final static int TIME_WITH_TIME_ZONE = 105221;
	/** end of time types */
	public final static int TIMETYPE_END = 105299;

	// DATE/TIME: Timestamp
	/** start of timestamp types */
	public final static int TIMESTAMPTYPE_START = 105300;
	/** (8 bytes) both date and time 4713 BC - 5874897 AD (1 microsecond / 14 digits) */
	public final static int TIMESTAMP = 105310;
	/** same as "timestamp" */
	public final static int TIMESTAMP_WITHOUT_TIME_ZONE = 105311;
	/** (8 bytes) both date and time, with time zone 4713 BC - 5874897 AD (1 microsecond / 14 digits) */
	public final static int TIMESTAMPTZ = 105320;
	/** same as "timestamptz"*/
	public final static int TIMESTAMP_WITH_TIME_ZONE = 105321;
	/** special oracle implementation of "timestamptz" */
	public final static int TIMESTAMP_WITH_LOCAL_TIME_ZONE = 105330;
	/** end of timestamp types */
	public final static int TIMESTAMPTYPE_END = 105399;

	// DATE/TIME: Interval
	/** (12 bytes) time intervals -178000000 years - +178000000 years (1 microsecond / 14 digits) */
	public final static int INTERVAL = 105410;
	/** oracle implementation of interval days, minutes, seconds*/
	public final static int INTERVAL_DAY_TO_SECOND = 105420;
	/** oracle implementation of interval years, months*/
	public final static int INTERVAL_YEAR_TO_MONTH = 105430;

	// BOOLEAN TYPES
	/** (1 byte) true or false (or null) */
	public final static int BOOLEAN = 106110;
	/** same as "boolean"*/
	public final static int BOOL = 106111;
	/** -1, 0, or +1 */
	public final static int SIGNTYPE = 106210;


	// ENUMERATED TYPES
	/** static, pre-defined set of values */
	public final static int ENUM = 107110;


	// GEOMETRIC TYPES
	/** (16 bytes) Point on the plane (x,y) */
	public final static int POINT = 108110;
	/** (32 bytes) Infinite line (not fully implemented) ((x1,y1),(x2,y2)) */
	public final static int LINE = 108210;
	/** (32 bytes) Finite line segment ((x1,y1),(x2,y2)) */
	public final static int LSEG = 108220;
	/** (32 bytes) Rectangular box ((x1,y1),(x2,y2)) */
	public final static int BOX = 108310;
	/** (16+16n bytes) Open (or closed) path [(x1,y1),...] */
	public final static int PATH = 108410;
	/** (40+16n bytes) Polygon (closed path) ((x1,y1),...) */
	public final static int POLYGON = 108420;
	/** (24 bytes) Circle <(x,y),r> (center and radius) */
	public final static int CIRCLE = 108430;
	/** spatial object stored in a single row */
	public final static int SDO_GEOMETRY = 108510;
	/** raster grid or image stored in a single row */
	public final static int SDO_RASTER = 108520;


	// NETWORK ADDRESS TYPES
	/** (7 or 19 bytes) IPv4 and IPv6 networks */
	public final static int CIDR = 109110;
	/** (7 or 19 bytes) IPv4 and IPv6 hosts and networks */
	public final static int INET = 109120;
	/** (6 bytes) MAC addresses */
	public final static int MACADDR = 109130;


	// BIT STRING TYPES
	/** fixed length bit string (1's and 0's) */
	public final static int BIT = 110110;
	/** variable length bit string (1's and 0's) */
	public final static int VARBIT = 110120;
	/** same as "varbit" */
	public final static int BIT_VARYING = 110121;


	// TEXT SEARCH TYPES
	/** sorted list of distinct lexemes */
	public final static int TSVECTOR = 111110;
	/** lexemes to be searched for */
	public final static int TSQUERY = 111120;


	// UUID TYPES
	/** 128-bit Universally Unique Identifier (UUID) */
	public final static int UUID = 112110;


	// XML TYPES
	/** variable length text with XML validation*/
	public final static int XML = 113110;
	/** XML data */
	public final static int URIType = 113210;
	/** consistent access to data stored inside and outside the database */
	public final static int DBURIType = 113220;
	/** URLs to external web pages or to files */
	public final static int HTTPURIType = 113230;
	/** URIs that can be embedded in any URIType column in a table */
	public final static int XDBURIType = 113240;


	// ARRAYS
	/** one-dimensional array with no upper bound*/
	public final static int TABLE = 114110;
	/** two-dimensional array with a fixed number of elements*/
	public final static int VARRAY = 114120;


	// COMPOSITE TYPES
	// this is actually "RECORD", but "RECORD" is already in use by PostgreSQL for something else
	// so we are using imaginary type "COMPOSITE" instead
	/** composite variable that can store data values of different types */
	public final static int COMPOSITE = 115110;


	// OBJECT IDENTIFIER TYPES
	/** any	numeric object identifier */
	public final static int OID = 116110;
	/** row identifier */
	public final static int ROWID = 116120;
	/** universal row identifier */
	public final static int UROWID = 116130;
	/** function name */
	public final static int REGPROC = 116210;
	/** function with argument types */
	public final static int REGPROCEDURE = 116220;
	/** operator name */
	public final static int REGOPER = 116230;
	/** operator with argument types */
	public final static int REGOPERATOR = 116240;
	/** relation name */
	public final static int REGCLASS = 116250;
	/** data type name */
	public final static int REGTYPE = 116260;
	/** text search configuration */
	public final static int REGCONFIG = 116270;
	/** text search dictionary */
	public final static int REGDICTIONARY = 116280;
	/** pointer to a result set */
	public final static int REF_CURSOR = 116310;
	/** pointer to an object */
	public final static int REF = 116320;


	// PSEUDO TYPES
	/** Indicates that a function accepts any input data type whatever */
	public final static int ANY = 117110;
	/** description of any persistent SQL type */
	public final static int SYS_ANYDATA = 117111;
	/** Indicates that a function accepts any array data type */
	public final static int ANYARRAY = 117120;
	/** Indicates that a function accepts any data type */
	public final static int ANYELEMENT = 117130;
	/** same as "anyelement" */
	public final static int SYS_ANYTYPE = 117131;
	/** Indicates that a function accepts any enum data type */
	public final static int ANYENUM = 117140;
	/** description of a given type plus a set of data instances of that type */
	public final static int SYS_ANYDATASET = 117141;
	/** Indicates that a function accepts any non-array data type */
	public final static int ANYNONARRAY = 117150;
	/** Indicates that a function accepts or returns a null-terminated C string */
	public final static int CSTRING = 117160;
	/** Indicates that a function accepts or returns a server-internal data type */
	public final static int INTERNAL = 117170;
	/** A procedural language call handler is declared to return language_handler */
	public final static int LANGUAGE_HANDLER = 117180;
	/** Identifies a function returning an unspecified row type */
	public final static int RECORD = 117190;
	/** A trigger function is declared to return trigger */
	public final static int TRIGGER = 117200;
	/** Indicates that a function returns no value */
	public final static int VOID = 117210;
	/** An obsolete type name that formerly served all the above purposes */
	public final static int OPAQUE = 117220;
	/** Information about a transaction ID */
	public final static int TXID_SNAPSHOT = 117310;


	// MEDIA TYPES
	/** audio data */
	public final static int ORDAUDIO = 118110;
	/** any tpe of media data, including audio, image, and video */
	public final static int ORDDOC = 118120;
	/** image data */
	public final static int ORDIMAGE = 118130;
	/** compact representation of color, texture, and shape information of image data */
	public final static int ORDIMAGESIGNATURE = 118140;
	/** average color */
	public final static int SI_AVERAGECOLOR = 118150;
	/** color values */
	public final static int SI_COLOR = 118160;
	/** relative frequencies of the color exhibited by samples of the raw image */
	public final static int SI_COLORHISTOGRAM = 118170;
	/** up to 4 image features associated with a feature weight */
	public final static int SI_FEATURELIST = 118180;
	/** the n by m most significant colors of the rectangles*/
	public final static int SI_POSITIONALCOLOR = 118190;
	/**  images with inherent image characteristics such as height, width, and format */
	public final static int SI_STILLIMAGE = 118200;
	/** coarseness, contrast, and directionality */
	public final static int SI_TEXTURE = 118210;
	/** video data */
	public final static int ORDVIDEO = 118220;


	// KEY WORDS
	public final static List<String> keyWords = Arrays.asList(
			"A",
			"ABORT",
			"ABS",
			"ABSOLUTE",
			"ACCESS",
			"ACTION",
			"ADA",
			"ADD",
			"ADMIN",
			"AFTER",
			"AGGREGATE",
			"ALIAS",
			"ALL",
			"ALLOCATE",
			"ALSO",
			"ALTER",
			"ALWAYS",
			"ANALYSE",
			"ANALYZE",
			"AND",
			"ANY",
			"ARE",
			"ARRAY",
			"AS",
			"ASC",
			"ASENSITIVE",
			"ASSERTION",
			"ASSIGNMENT",
			"ASYMMETRIC",
			"AT",
			"ATOMIC",
			"ATTRIBUTE",
			"ATTRIBUTES",
			"AUTHORIZATION",
			"AVG",
			"BACKWARD",
			"BASE64",
			"BEFORE",
			"BEGIN",
			"BERNOULLI",
			"BETWEEN",
			"BIGINT",
			"BINARY",
			"BIT",
			"BITVAR",
			"BIT_LENGTH",
			"BLOB",
			"BOOLEAN",
			"BOTH",
			"BREADTH",
			"BY",
			"C",
			"CACHE",
			"CALL",
			"CALLED",
			"CARDINALITY",
			"CASCADE",
			"CASCADED",
			"CASE",
			"CAST",
			"CATALOG",
			"CATALOG_NAME",
			"CEIL",
			"CEILING",
			"CHAIN",
			"CHAR",
			"CHARACTER",
			"CHARACTERISTICS",
			"CHARACTERS",
			"CHARACTER_LENGTH",
			"CHARACTER_SET_CATALOG",
			"CHARACTER_SET_NAME",
			"CHARACTER_SET_SCHEMA",
			"CHAR_LENGTH",
			"CHECK",
			"CHECKED",
			"CHECKPOINT",
			"CLASS",
			"CLASS_ORIGIN",
			"CLOB",
			"CLOSE",
			"CLUSTER",
			"COALESCE",
			"COBOL",
			"COLLATE",
			"COLLATION",
			"COLLATION_CATALOG",
			"COLLATION_NAME",
			"COLLATION_SCHEMA",
			"COLLECT",
			"COLUMN",
			"COLUMN_NAME",
			"COMMAND_FUNCTION",
			"COMMAND_FUNCTION_CODE",
			"COMMENT",
			"COMMIT",
			"COMMITTED",
			"COMPLETION",
			"CONCURRENTLY",
			"CONDITION",
			"CONDITION_NUMBER",
			"CONFIGURATION",
			"CONNECT",
			"CONNECTION",
			"CONNECTION_NAME",
			"CONSTRAINT",
			"CONSTRAINTS",
			"CONSTRAINT_CATALOG",
			"CONSTRAINT_NAME",
			"CONSTRAINT_SCHEMA",
			"CONSTRUCTOR",
			"CONTAINS",
			"CONTENT",
			"CONTINUE",
			"CONVERSION",
			"CONVERT",
			"COPY",
			"CORR",
			"CORRESPONDING",
			"COST",
			"COUNT",
			"COVAR_POP",
			"COVAR_SAMP",
			"CREATE",
			"CREATEDB",
			"CREATEROLE",
			"CREATEUSER",
			"CROSS",
			"CSV",
			"CUBE",
			"CUME_DIST",
			"CURRENT",
			"CURRENT_DATE",
			"CURRENT_DEFAULT_TRANSFORM_GROUP",
			"CURRENT_PATH",
			"CURRENT_ROLE",
			"CURRENT_TIME",
			"CURRENT_TIMESTAMP",
			"CURRENT_TRANSFORM_GROUP_FOR_TYPE",
			"CURRENT_USER",
			"CURSOR",
			"CURSOR_NAME",
			"CYCLE",
			"DATA",
			"DATABASE",
			"DATE",
			"DATETIME_INTERVAL_CODE",
			"DATETIME_INTERVAL_PRECISION",
			"DAY",
			"DEALLOCATE",
			"DEC",
			"DECIMAL",
			"DECLARE",
			"DEFAULT",
			"DEFAULTS",
			"DEFERRABLE",
			"DEFERRED",
			"DEFINED",
			"DEFINER",
			"DEGREE",
			"DELETE",
			"DELIMITER",
			"DELIMITERS",
			"DENSE_RANK",
			"DEPTH",
			"DEREF",
			"DERIVED",
			"DESC",
			"DESCRIBE",
			"DESCRIPTOR",
			"DESTROY",
			"DESTRUCTOR",
			"DETERMINISTIC",
			"DIAGNOSTICS",
			"DICTIONARY",
			"DISABLE",
			"DISCARD",
			"DISCONNECT",
			"DISPATCH",
			"DISTINCT",
			"DO",
			"DOCUMENT",
			"DOMAIN",
			"DOUBLE",
			"DROP",
			"DYNAMIC",
			"DYNAMIC_FUNCTION",
			"DYNAMIC_FUNCTION_CODE",
			"EACH",
			"ELEMENT",
			"ELSE",
			"ENABLE",
			"ENCODING",
			"ENCRYPTED",
			"END",
			"END-EXEC",
			"ENUM",
			"EQUALS",
			"ESCAPE",
			"EVERY",
			"EXCEPT",
			"EXCEPTION",
			"EXCLUDE",
			"EXCLUDING",
			"EXCLUSIVE",
			"EXEC",
			"EXECUTE",
			"EXISTING",
			"EXISTS",
			"EXP",
			"EXPLAIN",
			"EXTERNAL",
			"EXTRACT",
			"0",
			"FAMILY",
			"FETCH",
			"FILTER",
			"FINAL",
			"FIRST",
			"FLOAT",
			"FLOOR",
			"FOLLOWING",
			"FOR",
			"FORCE",
			"FOREIGN",
			"FORTRAN",
			"FORWARD",
			"FOUND",
			"FREE",
			"FREEZE",
			"FROM",
			"FULL",
			"FUNCTION",
			"FUSION",
			"G",
			"GENERAL",
			"GENERATED",
			"GET",
			"GLOBAL",
			"GO",
			"GOTO",
			"GRANT",
			"GRANTED",
			"GREATEST",
			"GROUP",
			"GROUPING",
			"HANDLER",
			"HAVING",
			"HEADER",
			"HEX",
			"HIERARCHY",
			"HOLD",
			"HOST",
			"HOUR",
			"IDENTITY",
			"IF",
			"IGNORE",
			"ILIKE",
			"IMMEDIATE",
			"IMMUTABLE",
			"IMPLEMENTATION",
			"IMPLICIT",
			"IN",
			"INCLUDING",
			"INCREMENT",
			"INDEX",
			"INDEXES",
			"INDICATOR",
			"INFIX",
			"INHERIT",
			"INHERITS",
			"INITIALIZE",
			"INITIALLY",
			"INNER",
			"INOUT",
			"INPUT",
			"INSENSITIVE",
			"INSERT",
			"INSTANCE",
			"INSTANTIABLE",
			"INSTEAD",
			"INT",
			"INTEGER",
			"INTERSECT",
			"INTERSECTION",
			"INTERVAL",
			"INTO",
			"INVOKER",
			"IS",
			"ISNULL",
			"ISOLATION",
			"ITERATE",
			"JOIN",
			"K",
			"KEY",
			"KEY_MEMBER",
			"KEY_TYPE",
			"LANCOMPILER",
			"LANGUAGE",
			"LARGE",
			"LAST",
			"LATERAL",
			"LEADING",
			"LEAST",
			"LEFT",
			"LENGTH",
			"LESS",
			"LEVEL",
			"LIKE",
			"LIMIT",
			"LISTEN",
			"LN",
			"LOAD",
			"LOCAL",
			"LOCALTIME",
			"LOCALTIMESTAMP",
			"LOCATION",
			"LOCATOR",
			"LOCK",
			"LOGIN",
			"LOWER",
			"M",
			"MAP",
			"MAPPING",
			"MATCH",
			"MATCHED",
			"MAX",
			"MAXVALUE",
			"MEMBER",
			"MERGE",
			"MESSAGE_LENGTH",
			"MESSAGE_OCTET_LENGTH",
			"MESSAGE_TEXT",
			"METHOD",
			"MIN",
			"MINUTE",
			"MINVALUE",
			"MOD",
			"MODE",
			"MODIFIES",
			"MODIFY",
			"MODULE",
			"MONTH",
			"MORE",
			"MOVE",
			"MULTISET",
			"MUMPS",
			"NAME",
			"NAMES",
			"NATIONAL",
			"NATURAL",
			"NCHAR",
			"NCLOB",
			"NESTING",
			"NEW",
			"NEXT",
			"NO",
			"NOCREATEDB",
			"NOCREATEROLE",
			"NOCREATEUSER",
			"NOINHERIT",
			"NOLOGIN",
			"NONE",
			"NORMALIZE",
			"NORMALIZED",
			"NOSUPERUSER",
			"NOT",
			"NOTHING",
			"NOTIFY",
			"NOTNULL",
			"NOWAIT",
			"NULL",
			"NULLABLE",
			"NULLIF",
			"NULLS",
			"NUMBER",
			"NUMERIC",
			"OBJECT",
			"OCTETS",
			"OCTET_LENGTH",
			"OF",
			"OFF",
			"OFFSET",
			"OIDS",
			"OLD",
			"ON",
			"ONLY",
			"OPEN",
			"OPERATION",
			"OPERATOR",
			"OPTION",
			"OPTIONS",
			"OR",
			"ORDER",
			"ORDERING",
			"ORDINALITY",
			"OTHERS",
			"OUT",
			"OUTER",
			"OUTPUT",
			"OVER",
			"OVERLAPS",
			"OVERLAY",
			"OVERRIDING",
			"OWNED",
			"OWNER",
			"PAD",
			"PARAMETER",
			"PARAMETERS",
			"PARAMETER_MODE",
			"PARAMETER_NAME",
			"PARAMETER_ORDINAL_POSITION",
			"PARAMETER_SPECIFIC_CATALOG",
			"PARAMETER_SPECIFIC_NAME",
			"PARAMETER_SPECIFIC_SCHEMA",
			"PARSER",
			"PARTIAL",
			"PARTITION",
			"PASCAL",
			"PASSWORD",
			"PATH",
			"PERCENTILE_CONT",
			"PERCENTILE_DISC",
			"PERCENT_RANK",
			"PLACING",
			"PLANS",
			"PLI",
			"POSITION",
			"POSTFIX",
			"POWER",
			"PRECEDING",
			"PRECISION",
			"PREFIX",
			"PREORDER",
			"PREPARE",
			"PREPARED",
			"PRESERVE",
			"PRIMARY",
			"PRIOR",
			"PRIVILEGES",
			"PROCEDURAL",
			"PROCEDURE",
			"PUBLIC",
			"QUOTE",
			"RANGE",
			"RANK",
			"READ",
			"READS",
			"REAL",
			"REASSIGN",
			"RECHECK",
			"RECURSIVE",
			"REF",
			"REFERENCES",
			"REFERENCING",
			"REGR_AVGX",
			"REGR_AVGY",
			"REGR_COUNT",
			"REGR_INTERCEPT",
			"REGR_R2",
			"REGR_SLOPE",
			"REGR_SXX",
			"REGR_SXY",
			"REGR_SYY",
			"REINDEX",
			"RELATIVE",
			"RELEASE",
			"RENAME",
			"REPEATABLE",
			"REPLACE",
			"REPLICA",
			"RESET",
			"RESTART",
			"RESTRICT",
			"RESULT",
			"RETURN",
			"RETURNED_CARDINALITY",
			"RETURNED_LENGTH",
			"RETURNED_OCTET_LENGTH",
			"RETURNED_SQLSTATE",
			"RETURNING",
			"RETURNS",
			"REVOKE",
			"RIGHT",
			"ROLE",
			"ROLLBACK",
			"ROLLUP",
			"ROUTINE",
			"ROUTINE_CATALOG",
			"ROUTINE_NAME",
			"ROUTINE_SCHEMA",
			"ROW",
			"ROWS",
			"ROW_COUNT",
			"ROW_NUMBER",
			"RULE",
			"SAVEPOINT",
			"SCALE",
			"SCHEMA",
			"SCHEMA_NAME",
			"SCOPE",
			"SCOPE_CATALOG",
			"SCOPE_NAME",
			"SCOPE_SCHEMA",
			"SCROLL",
			"SEARCH",
			"SECOND",
			"SECTION",
			"SECURITY",
			"SELECT",
			"SELF",
			"SENSITIVE",
			"SEQUENCE",
			"SERIALIZABLE",
			"SERVER_NAME",
			"SESSION",
			"SESSION_USER",
			"SET",
			"SETOF",
			"SETS",
			"SHARE",
			"SHOW",
			"SIMILAR",
			"SIMPLE",
			"SIZE",
			"SMALLINT",
			"SOME",
			"SOURCE",
			"SPACE",
			"SPECIFIC",
			"SPECIFICTYPE",
			"SPECIFIC_NAME",
			"SQL",
			"SQLCODE",
			"SQLERROR",
			"SQLEXCEPTION",
			"SQLSTATE",
			"SQLWARNING",
			"SQRT",
			"STABLE",
			"STANDALONE",
			"START",
			"STATE",
			"STATEMENT",
			"STATIC",
			"STATISTICS",
			"STDDEV_POP",
			"STDDEV_SAMP",
			"STDIN",
			"STDOUT",
			"STORAGE",
			"STRICT",
			"STRIP",
			"STRUCTURE",
			"STYLE",
			"SUBCLASS_ORIGIN",
			"SUBLIST",
			"SUBMULTISET",
			"SUBSTRING",
			"SUM",
			"SUPERUSER",
			"SYMMETRIC",
			"SYSID",
			"SYSTEM",
			"SYSTEM_USER",
			"TABLE",
			"TABLESAMPLE",
			"TABLESPACE",
			"TABLE_NAME",
			"TEMP",
			"TEMPLATE",
			"TEMPORARY",
			"TERMINATE",
			"TEXT",
			"THAN",
			"THEN",
			"TIES",
			"TIME",
			"TIMESTAMP",
			"TIMEZONE_HOUR",
			"TIMEZONE_MINUTE",
			"TO",
			"TOP_LEVEL_COUNT",
			"TRAILING",
			"TRANSACTION",
			"TRANSACTIONS_COMMITTED",
			"TRANSACTIONS_ROLLED_BACK",
			"TRANSACTION_ACTIVE",
			"TRANSFORM",
			"TRANSFORMS",
			"TRANSLATE",
			"TRANSLATION",
			"TREAT",
			"TRIGGER",
			"TRIGGER_CATALOG",
			"TRIGGER_NAME",
			"TRIGGER_SCHEMA",
			"TRIM",
			"TRUNCATE",
			"TRUSTED",
			"TYPE",
			"UESCAPE",
			"UNBOUNDED",
			"UNCOMMITTED",
			"UNDER",
			"UNENCRYPTED",
			"UNION",
			"UNIQUE",
			"UNKNOWN",
			"UNLISTEN",
			"UNNAMED",
			"UNNEST",
			"UNTIL",
			"UPDATE",
			"UPPER",
			"USAGE",
			"USER",
			"USER_DEFINED_TYPE_CATALOG",
			"USER_DEFINED_TYPE_CODE",
			"USER_DEFINED_TYPE_NAME",
			"USER_DEFINED_TYPE_SCHEMA",
			"USING",
			"VACUUM",
			"VALID",
			"VALIDATOR",
			"VALUE",
			"VALUES",
			"VARCHAR",
			"VARIABLE",
			"VARYING",
			"VAR_POP",
			"VAR_SAMP",
			"VERBOSE",
			"VERSION",
			"VIEW",
			"VOLATILE",
			"WHEN",
			"WHENEVER",
			"WHERE",
			"WHITESPACE",
			"WIDTH_BUCKET",
			"WINDOW",
			"WITH",
			"WITHIN",
			"WITHOUT",
			"WORK",
			"WRITE",
			"XML",
			"XMLAGG",
			"XMLATTRIBUTES",
			"XMLBINARY",
			"XMLCOMMENT",
			"XMLCONCAT",
			"XMLELEMENT",
			"XMLFOREST",
			"XMLNAMESPACES",
			"XMLPARSE",
			"XMLPI",
			"XMLROOT",
			"XMLSERIALIZE",
			"YEAR",
			"YES",
			"ZONE"
			);



	// CONSTRUCTORS
	// ============

	/**
	 * Default constructor
	 */
	@SuppressWarnings("unchecked")
	private DBEngine() {
		s_parameters = Parameters.getParameters();
		s_logger = MigrateLogger.getLogger();

		// initialize interfaces to database specific engines
		m_interfaces = new HashMap <Integer, DBEngineInterface> ();
		m_vendorIDs = new HashMap <String, Integer> ();
		// iterate through known implementation classes
		for (int i=0; i < m_implementations.size(); i++) {
			String interfaceName = new StringBuffer("com.kkalice.adempiere.migrate.").append(m_implementations.get(i)).toString();
			Class <DBEngineInterface> engineClass = null;
			DBEngineInterface engineInterface = null;
			try {
				// load the implementation class
				engineClass = (Class<DBEngineInterface>) Class.forName(interfaceName);
				// instantiate the implementation
				try {
					engineInterface = (DBEngineInterface)engineClass.newInstance();
				} catch (InstantiationException e) {
					s_logger.log(Level.SEVERE, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2].getMethodName(), "instantiationException", new Object[] {engineClass.getName(), e.getMessage()});
				} catch (IllegalAccessException e) {
					s_logger.log(Level.SEVERE, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2].getMethodName(), "illegalAccessException", new Object[] {engineClass.getName(), e.getMessage()});
				}
			} catch (ClassNotFoundException e) {
				s_logger.log(Level.SEVERE, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2].getMethodName(), "interfaceNotFound", new Object[] {interfaceName, e.getMessage()});
			}
			// check if we have a valid implementation
			if (engineInterface != null) {
				// save the implementation
				m_interfaces.put(i, engineInterface);

				// load possible vendor names for later identification
				List<String> vendorNames = engineInterface.getVendorNames();
				for (String vendorName : vendorNames) {
					// do not overwrite names already registered
					if (!m_vendorIDs.containsKey(vendorName.toUpperCase()))
						m_vendorIDs.put(vendorName.toUpperCase(), i);
				}
			}
		}
	}


	/**
	 * Get DBEngine
	 * @return DBEngine
	 */
	public static DBEngine getDBEngine() {
		if (s_dbEngine == null)
			s_dbEngine = new DBEngine();
		return s_dbEngine;
	}


	// METHODS
	// =======

	// parameter checking

	/**
	 * @return translations are attempted
	 */
	@SuppressWarnings("static-access")
	private boolean isAttemptTranslation() {
		return s_parameters.isAttemptTranslation();
	}

	@SuppressWarnings("static-access")
	private String getTemporaryColumnName() {
		return s_parameters.TEMPCOLNAME;
	}


	// database access

	/**
	 * gets a list of implemented database vendors
	 * @return alphabetically sorted list of vendors
	 */
	public static ArrayList<String> getVendorList() {

		ArrayList<String> vendors = new ArrayList<String>();

		for (int i=0; i < m_implementations.size(); i++) {
			vendors.add(m_interfaces.get(i).getVendorNames().get(0));
		}

		Collections.sort(vendors);

		return vendors;
	}

	/**
	 * find out which database this is
	 * @param vendorName the database vendor
	 * @return internal database ID
	 */
	@SuppressWarnings("static-access")
	public int getDBVendorID(String vendorName) {
		if (vendorName==null || vendorName.length()==0)
			vendorName = s_parameters.getDefaultVendor();
		vendorName = vendorName.toUpperCase();
		int i = 0;
		if (m_vendorIDs.containsKey(vendorName))
			i = m_vendorIDs.get(vendorName);
		return i;
	}

	/**
	 * find out if the catalog name is a system catalog
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @return the catalog is a system catalog
	 */
	public boolean isSystemCatalog (String vendorName, String catalogName) {
		boolean result = false;

		List<String> definedNames = m_interfaces.get(getDBVendorID(vendorName)).getSystemCatalogs();
		if (definedNames==null || definedNames.size()==0)
			return false;

		ArrayList<String> reservedNames = new ArrayList<String>();
		for (String name : definedNames) {
			reservedNames.add(name.toUpperCase());
		}

		if (reservedNames.contains(catalogName.toUpperCase()))
			result = true;

		return result;
	}

	/**
	 * find out if the schema name is a system schema
	 * @param vendorName the database vendor
	 * @param schemaName the schema to use
	 * @return the schema is a system schema
	 */
	public boolean isSystemSchema (String vendorName, String schemaName) {
		boolean result = false;

		List<String> definedNames = m_interfaces.get(getDBVendorID(vendorName)).getSystemSchemas();
		if (definedNames==null || definedNames.size()==0)
			return false;

		ArrayList<String> reservedNames = new ArrayList<String>();
		for (String name : definedNames) {
			reservedNames.add(name.toUpperCase());
		}

		if (reservedNames.contains(schemaName.toUpperCase()))
			result = true;

		return result;
	}

	/**
	 * Get a jdbc driver according to database vendor
	 * @param vendorName the database vendor
	 * @return name of the vendor's jdbc driver
	 */
	public String getDBDriver (String vendorName) {
		return m_interfaces.get(getDBVendorID(vendorName)).getDBDriver();
	}

	/**
	 * get a valid port number
	 * <p>
	 * if no port is specified, chooses a default port according to vendor
	 * @param vendorName the database vendor
	 * @param port the port number to test
	 * @return valid port number for database connection
	 */
	public String getDBPort (String vendorName, String port) {
		String result = port;
		if (port==null || port.length()==0)
			result = m_interfaces.get(getDBVendorID(vendorName)).getDBPort();
		return result;
	}

	/**
	 * get the default database name
	 * @param vendorName the database vendor
	 * @return default database name
	 */
	public String getDBDefaultName (String vendorName) {
		return m_interfaces.get(getDBVendorID(vendorName)).getDBName(vendorName);
	}

	/**
	 * whether the source and target usually share the same database name
	 * @param vendorName the database vendor
	 * @return the same database name is shared by source and target
	 */
	public boolean isSourceAndTargetSameDBName (String vendorName) {
		return m_interfaces.get(getDBVendorID(vendorName)).isSourceAndTargetSameDBName();
	}

	/**
	 * get a valid system user name
	 * @param vendorName the database vendor
	 * @return valid system user name
	 */
	public String getDBDefaultSystemUser (String vendorName) {
		return m_interfaces.get(getDBVendorID(vendorName)).getDBSystemUser();
	}

	/**
	 * get a valid system user password
	 * @param vendorName the database vendor
	 * @return default system user password
	 */
	public String getDBDefaultSystemPassword (String vendorName) {
		return m_interfaces.get(getDBVendorID(vendorName)).getDBSystemPassword();
	}

	/**
	 * get a valid URL to connect to the datatabase
	 * @param vendorName the database vendor
	 * @param host server on which the database runs
	 * @param port port on which to connect to the database
	 * @param name name of the database
	 * @return connection url to the database
	 */
	public String getDBUrl (String vendorName, String host, String port, String name) {
		return m_interfaces.get(getDBVendorID(vendorName)).getDBUrl(host, port, name);
	}

	/**
	 * get a valid schema name
	 * <p>
	 * In some databases, the schema name must be same as the user name
	 * @param vendorName the database vendor
	 * @param schemaName schema to check
	 * @param user actual name of user
	 * @return name of schema to use
	 */
	public String getDBSchemaOrUser (String vendorName, String schemaName, String user) {
		return m_interfaces.get(getDBVendorID(vendorName)).getDBSchemaOrUser(schemaName, user);
	}

	/**
	 * select system or standard user
	 * <p>
	 * In some databases, a system user is required for certain operations (for example to drop a schema).<br>
	 * @param vendorName the database vendor
	 * @param normalUser name of normal database user
	 * @param systemUser name of system user
	 * @return name of user to use for privileged operations
	 */
	public String getDBSystemOrNormalUser (String vendorName, String normalUser, String systemUser) {
		return m_interfaces.get(getDBVendorID(vendorName)).getDBSystemOrNormalUser(normalUser, systemUser);
	}

	/**
	 * select password for system or standard user
	 * <p>
	 * In some databases, a system user is required for certain operations (for example to drop a schema).<br>
	 * @param vendorName the database vendor
	 * @param normalPasswd password for normal database user
	 * @param systemPasswd password for system user
	 * @return password of user to use for privileged operations
	 */
	public String getDBSystemOrNormalPassword (String vendorName, String normalPasswd, String systemPasswd) {
		return m_interfaces.get(getDBVendorID(vendorName)).getDBSystemOrNormalPassword(normalPasswd, systemPasswd);
	}


	// error handling
	// these are counters and markers for errors occurring in the DB connection class,
	// but we have two connections (one for source and one for target), so we need a
	// central location which both connections can check to track errors.

	/**
	 * @return DB errors exist
	 */
	public boolean isDBError() {
		return m_isDBError;
	}

	/**
	 * @param isDBError DB errors exist
	 */
	public void setDBError(boolean isDBError) {
		m_isDBError = isDBError;
	}

	/**
	 * @return SQL errors exist
	 */
	public boolean isSQLError() {
		return m_isSQLError;
	}

	/**
	 * @param isSQLError SQL errors exist
	 */
	public void setSQLError(boolean isSQLError) {
		m_isSQLError = isSQLError;
	}

	/**
	 * @return the transactionErrors
	 */
	public int getTransactionErrors() {
		return m_transactionErrors;
	}

	/**
	 * @param transactionErrors the transactionErrors to set
	 */
	public void setTransactionErrors(int transactionErrors) {
		m_transactionErrors = m_transactionErrors + transactionErrors;
	}

	/**
	 * set SQL errors if any transaction errors exist and clear transaction errors
	 */
	public void resetTransactionErrors() {
		if (m_transactionErrors > 0)
			setSQLError(true);
		m_transactionErrors = 0;
	}


	// data type translations

	/**
	 * converts database dependent data type name to unambiguous internal int representation
	 * @param vendorName the database vendor
	 * @param dataType name of the data type
	 * @return unambiguous data type identifier
	 */
	public int getDataTypeID (String vendorName, String dataType) {

		// sometimes a length or precision is included in the name of the data type,
		// which is wrong because special fields are used for those values
		// for example, "TIMESTAMP(6)"
		dataType = dataType.replaceAll("\\(\\d+\\)", "");
		dataType = dataType.trim();

		return getDataTypeID(m_interfaces.get(getDBVendorID(vendorName)).disambiguateDataType(dataType.toUpperCase()));
	}

	/**
	 * converts data type name to unambiguous internal int representation
	 * @param dataType name of data type
	 * @return unambiguous data type identifier
	 */
	private int getDataTypeID (String dataType) {
		int i = 0;

		String dt = dataType.toUpperCase();

		if (dt.equals("SMALLINT"))
			i = SMALLINT;
		else if (dt.equals("INT2"))
			i = INT2;
		else if (dt.equals("INTEGER"))
			i = INTEGER;
		else if (dt.equals("INT4"))
			i = INT4 ;
		else if (dt.equals("INT"))
			i = INT;
		else if (dt.equals("PLS INTEGER"))
			i = PLS_INTEGER;
		else if (dt.equals("BINARY INTEGER"))
			i = BINARY_INTEGER;
		else if (dt.equals("NATURAL"))
			i = NATURAL;
		else if (dt.equals("POSITIVE"))
			i = POSITIVE;
		else if (dt.equals("SIMPLE INTEGER"))
			i = SIMPLE_INTEGER;
		else if (dt.equals("NATURALN"))
			i = NATURALN;
		else if (dt.equals("POSITIVEN"))
			i = POSITIVEN;
		else if (dt.equals("BIGINT"))
			i = BIGINT;
		else if (dt.equals("INT8"))
			i = INT8;
		else if (dt.equals("NUMERIC"))
			i = NUMERIC;
		else if (dt.equals("DECIMAL"))
			i = DECIMAL;
		else if (dt.equals("DEC"))
			i = DEC;
		else if (dt.equals("NUMBER"))
			i = NUMBER;
		else if (dt.equals("REAL"))
			i = REAL;
		else if (dt.equals("FLOAT4"))
			i = FLOAT4;
		else if (dt.equals("BINARY FLOAT"))
			i = BINARY_FLOAT;
		else if (dt.equals("DOUBLE PRECISION"))
			i = DOUBLE_PRECISION;
		else if (dt.equals("FLOAT8"))
			i = FLOAT8;
		else if (dt.equals("BINARY DOUBLE"))
			i = BINARY_DOUBLE;
		else if (dt.equals("FLOAT"))
			i = FLOAT;
		else if (dt.equals("SERIAL"))
			i = SERIAL;
		else if (dt.equals("SERIAL4"))
			i = SERIAL4;
		else if (dt.equals("BIGSERIAL"))
			i = BIGSERIAL;
		else if (dt.equals("SERIAL8"))
			i = SERIAL8;
		else if (dt.equals("MONEY"))
			i = MONEY;
		else if (dt.equals("CHAR"))
			i = CHAR;
		else if (dt.equals("CHARACTER"))
			i = CHARACTER;
		else if (dt.equals("NCHAR"))
			i = NCHAR;
		else if (dt.equals("NAME"))
			i = NAME;
		else if (dt.equals("VARCHAR"))
			i = VARCHAR;
		else if (dt.equals("CHARACTER VARYING"))
			i = CHARACTER_VARYING;
		else if (dt.equals("CHAR VARYING"))
			i = CHAR_VARYING;
		else if (dt.equals("VARCHAR2"))
			i = VARCHAR2;
		else if (dt.equals("NVARCHAR"))
			i = NVARCHAR;
		else if (dt.equals("NCHAR VARYING"))
			i = NCHAR_VARYING;
		else if (dt.equals("NATIONAL CHAR VARYING"))
			i = NATIONAL_CHAR_VARYING;
		else if (dt.equals("NATIONAL CHARACTER VARYING"))
			i = NATIONAL_CHARACTER_VARYING;
		else if (dt.equals("NVARCHAR2"))
			i = NVARCHAR2 ;
		else if (dt.equals("STRING"))
			i = STRING;
		else if (dt.equals("LONG"))
			i = LONG;
		else if (dt.equals("TEXT"))
			i = TEXT ;
		else if (dt.equals("CLOB"))
			i = CLOB ;
		else if (dt.equals("NCLOB"))
			i = NCLOB;
		else if (dt.equals("MLSLABEL"))
			i = MLSLABEL;
		else if (dt.equals("RAW"))
			i = RAW;
		else if (dt.equals("LONG RAW"))
			i = LONG_RAW;
		else if (dt.equals("BYTEA"))
			i = BYTEA;
		else if (dt.equals("BLOB"))
			i = BLOB;
		else if (dt.equals("BFILE"))
			i = BFILE;
		else if (dt.equals("DATE"))
			i = DATE;
		else if (dt.equals("TIME"))
			i = TIME;
		else if (dt.equals("TIME WITHOUT TIME ZONE"))
			i = TIME_WITHOUT_TIME_ZONE;
		else if (dt.equals("TIMETZ"))
			i = TIMETZ;
		else if (dt.equals("TIME WITH TIME ZONE"))
			i = TIME_WITH_TIME_ZONE;
		else if (dt.equals("TIMESTAMP"))
			i = TIMESTAMP;
		else if (dt.equals("TIMESTAMP WITHOUT TIME ZONE"))
			i = TIMESTAMP_WITHOUT_TIME_ZONE;
		else if (dt.equals("TIMESTAMPTZ"))
			i = TIMESTAMPTZ;
		else if (dt.equals("TIMESTAMP WITH TIME ZONE"))
			i = TIMESTAMP_WITH_TIME_ZONE;
		else if (dt.equals("TIMESTAMP WITH LOCAL TIME ZONE"))
			i = TIMESTAMP_WITH_LOCAL_TIME_ZONE;
		else if (dt.equals("INTERVAL"))
			i = INTERVAL;
		else if (dt.equals("INTERVAL DAY TO SECOND"))
			i = INTERVAL_DAY_TO_SECOND;
		else if (dt.equals("INTERVAL YEAR TO MONTH"))
			i = INTERVAL_YEAR_TO_MONTH;
		else if (dt.equals("BOOLEAN"))
			i = BOOLEAN;
		else if (dt.equals("BOOL"))
			i = BOOL;
		else if (dt.equals("SIGNTYPE"))
			i = SIGNTYPE;
		else if (dt.equals("ENUM"))
			i = ENUM;
		else if (dt.equals("POINT"))
			i = POINT;
		else if (dt.equals("LINE"))
			i = LINE;
		else if (dt.equals("LSEG"))
			i = LSEG;
		else if (dt.equals("BOX"))
			i = BOX;
		else if (dt.equals("PATH"))
			i = PATH;
		else if (dt.equals("POLYGON"))
			i = POLYGON;
		else if (dt.equals("CIRCLE"))
			i = CIRCLE;
		else if (dt.equals("SDO GEOMETRY"))
			i = SDO_GEOMETRY;
		else if (dt.equals("SDO RASTER"))
			i = SDO_RASTER;
		else if (dt.equals("CIDR"))
			i = CIDR;
		else if (dt.equals("INET"))
			i = INET;
		else if (dt.equals("MACADDR"))
			i = MACADDR;
		else if (dt.equals("BIT"))
			i = BIT;
		else if (dt.equals("VARBIT"))
			i = VARBIT;
		else if (dt.equals("BIT VARYING"))
			i = BIT_VARYING;
		else if (dt.equals("TSVECTOR"))
			i = TSVECTOR;
		else if (dt.equals("TSQUERY"))
			i = TSQUERY;
		else if (dt.equals("UUID"))
			i = UUID;
		else if (dt.equals("XML"))
			i = XML;
		else if (dt.equals("URIType"))
			i = URIType;
		else if (dt.equals("DBURIType"))
			i = DBURIType;
		else if (dt.equals("HTTPURIType"))
			i = HTTPURIType;
		else if (dt.equals("XDBURIType"))
			i = XDBURIType;
		else if (dt.equals("TABLE"))
			i = TABLE;
		else if (dt.equals("VARRAY"))
			i = VARRAY;
		else if (dt.equals("COMPOSITE"))
			i = COMPOSITE;
		else if (dt.equals("OID"))
			i = OID;
		else if (dt.equals("ROWID"))
			i = ROWID;
		else if (dt.equals("UROWID"))
			i = UROWID;
		else if (dt.equals("REGPROC"))
			i = REGPROC;
		else if (dt.equals("REGPROCEDURE"))
			i = REGPROCEDURE;
		else if (dt.equals("REGOPER"))
			i = REGOPER;
		else if (dt.equals("REGOPERATOR"))
			i = REGOPERATOR;
		else if (dt.equals("REGCLASS"))
			i = REGCLASS;
		else if (dt.equals("REGTYPE"))
			i = REGTYPE;
		else if (dt.equals("REGCONFIG"))
			i = REGCONFIG;
		else if (dt.equals("REGDICTIONARY"))
			i = REGDICTIONARY;
		else if (dt.equals("REF CURSOR"))
			i = REF_CURSOR;
		else if (dt.equals("REF"))
			i = REF;
		else if (dt.equals("ANY"))
			i = ANY;
		else if (dt.equals("SYS.ANYDATA"))
			i = SYS_ANYDATA;
		else if (dt.equals("ANYARRAY"))
			i = ANYARRAY;
		else if (dt.equals("ANYELEMENT"))
			i = ANYELEMENT;
		else if (dt.equals("SYS.ANYTYPE"))
			i = SYS_ANYTYPE;
		else if (dt.equals("ANYENUM"))
			i = ANYENUM;
		else if (dt.equals("SYS.ANYDATASET"))
			i = SYS_ANYDATASET;
		else if (dt.equals("ANYNONARRAY"))
			i = ANYNONARRAY;
		else if (dt.equals("CSTRING"))
			i = CSTRING;
		else if (dt.equals("INTERNAL"))
			i = INTERNAL;
		else if (dt.equals("LANGUAGE_HANDLER"))
			i = LANGUAGE_HANDLER;
		else if (dt.equals("RECORD"))
			i = RECORD;
		else if (dt.equals("TRIGGER"))
			i = TRIGGER;
		else if (dt.equals("VOID"))
			i = VOID;
		else if (dt.equals("OPAQUE"))
			i = OPAQUE;
		else if (dt.equals("TXID_SNAPSHOT"))
			i = TXID_SNAPSHOT;
		else if (dt.equals("ORDAUDIO"))
			i = ORDAUDIO;
		else if (dt.equals("ORDDOC"))
			i = ORDDOC;
		else if (dt.equals("ORDIMAGE"))
			i = ORDIMAGE;
		else if (dt.equals("ORDIMAGESIGNATURE"))
			i = ORDIMAGESIGNATURE;
		else if (dt.equals("SI AVERAGECOLOR"))
			i = SI_AVERAGECOLOR;
		else if (dt.equals("SI COLOR"))
			i = SI_COLOR;
		else if (dt.equals("SI COLORHISTOGRAM"))
			i = SI_COLORHISTOGRAM;
		else if (dt.equals("SI FEATURELIST"))
			i = SI_FEATURELIST;
		else if (dt.equals("SI POSITIONALCOLOR"))
			i = SI_POSITIONALCOLOR;
		else if (dt.equals("SI STILLIMAGE"))
			i = SI_STILLIMAGE;
		else if (dt.equals("SI TEXTURE"))
			i = SI_TEXTURE;
		else if (dt.equals("ORDVIDEO"))
			i = ORDVIDEO;
		else {
			i = 0;
			s_logger.log(Level.SEVERE, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2].getMethodName(), "getDataTypeIDError", new Object[] {dataType});
		}

		return i;
	}

	/**
	 * translate data type from source to target database
	 * @param sourceVendorName the source database vendor
	 * @param targetVendorName the target database vendor
	 * @param dataType name of the data type used in source
	 * @param size size/length/precision of the column
	 * @param scale scale/decimal digits of the column
	 * @return name of data type used in target (null if no direct conversion is possible)
	 */
	public String translateDataType (String sourceVendorName, String targetVendorName, String dataType, int size, int scale) {
		return getDataType(targetVendorName, getDataTypeID(sourceVendorName, dataType), size, scale);
	}

	/**
	 * convert data type ID to database specific implementation
	 * @param vendorName the database vendor
	 * @param dataTypeID data type identifier
	 * @param size size/length/precision of the column
	 * @param scale scale/decimal digits of the column
	 * @return corresponding vendor-specific data type (null if no direct conversion is possible)
	 */
	private String getDataType (String vendorName, int dataTypeID, int size, int scale) {
		// get data type
		String result = m_interfaces.get(getDBVendorID(vendorName)).getDataType(dataTypeID, size, scale);
		// log error if no data type found
		if (result==null)
			s_logger.log(Level.SEVERE, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2].getMethodName(), "getDataTypeError", new Object[] {Integer.toString(dataTypeID)});
		return result;
	}

	/**
	 * corrects buggy oracle jdbc size of nchar columns
	 * @param sourceType data type of column to check
	 * @param sizeReported size reported for the data type
	 * @param sizeDevisor NLS conversion devisor
	 * @return correct size of column
	 */
	public int correctOracleCharSize (String sourceType, int sizeReported, int sizeDevisor) {
		int i = sizeReported;

		String columnType = sourceType.toUpperCase();

		if (sizeDevisor>1 && columnType.contains("CHAR")) {
			if (columnType.startsWith("NCHAR")) {
				i = sizeReported / sizeDevisor;
				if ( sizeReported >=2000)
					i = sizeReported / 2;
			} else if (columnType.startsWith("NVARCHAR")) {
				i = sizeReported / sizeDevisor;
				if ( sizeReported >=4000)
					i = sizeReported / 2;
			}
		}

		return i;
	}

	/**
	 * correct the size of numeric _ID columns
	 * @param vendorName the database vendor
	 * @param columnName name of column
	 * @param dataType name of the data type
	 * @param size size/length/precision of the column
	 * @return corrected size of column
	 */
	public int correctIDColumnSize (String vendorName, String columnName, String dataType, int size) {
		int i = size;

		// sometimes the developer forgets to specify the size of ID columns
		if (columnName.toUpperCase().endsWith("_ID")) {
			int dataTypeID = getDataTypeID(vendorName, dataType);
			if (dataTypeID>=NUMERIC && dataTypeID<=NUMBER)
				i = 10;
		}

		return i;
	}

	/**
	 * correct the scale of numeric _ID columns
	 * @param vendorName the database vendor
	 * @param columnName name of column
	 * @param dataType name of the data type
	 * @param scale scale/decimal digits of the column
	 * @return corrected scale of column
	 */
	public int correctIDColumnScale (String vendorName, String columnName, String dataType, int scale) {
		int i = scale;

		// sometimes the developer forgets to specify the scale of ID columns
		if (columnName.toUpperCase().endsWith("_ID")) {
			int dataTypeID = getDataTypeID(vendorName, dataType);
			if (dataTypeID>=NUMERIC && dataTypeID<=NUMBER)
				i = 0;
		}

		return i;
	}


	// expression translations

	/**
	 * translate expression from source database to target database
	 * @param sourceVendorName the source database vendor
	 * @param targetVendorName the target database vendor
	 * @param expression expression to translate
	 * @return expression as valid in target database
	 */
	public String translateExpression (String sourceVendorName, String targetVendorName, String expression) {
		if (expression == null)
			return null;
		expression = expression.trim();
		return m_interfaces.get(getDBVendorID(targetVendorName)).translateExpression(sourceVendorName.toUpperCase(), expression);
	}

	/**
	 * translate unnamed parameter from source database to target database
	 * @param vendorName the database vendor
	 * @param paramNum the parameter number
	 * @return unnamed parameter as valid in target database
	 */
	public String translateUnnamedParameter (String vendorName, int paramNum) {
		return m_interfaces.get(getDBVendorID(vendorName)).translateUnnamedParameter(paramNum);
	}

	/**
	 * convert string into boolean
	 * @param condition string representation of boolean condition
	 * @return boolean condition of string
	 */
	public boolean isTrue (String condition) {
		boolean result = false;
		if (condition!=null && condition.length()>0) {
			String s = condition.substring(0,1);
			if (s.equalsIgnoreCase("Y") || s.equals("1") || s.equalsIgnoreCase("T"))
				result = true;
		}
		return result;
	}

	/**
	 * normalize key word so that it can be used as column name
	 * <p>
	 * Some parsers get confused when key words are used as column names.<br>
	 * This function "normalizes" the key words so that the parser understands it is
	 * actually a column name.
	 * @param vendorName the database vendor
	 * @param columnName name of column
	 * @return valid column name
	 */
	public String normalizeColumnName (String vendorName, String columnName) {
		String result = columnName;
		if (columnName != null && keyWords.contains(columnName.toUpperCase()))
			result = m_interfaces.get(getDBVendorID(vendorName)).normalizeColumnName(columnName);
		return result;
	}

	/**
	 * normalize identifier to follow database conventions
	 * <p>
	 * Some databases have restrictions (for example length) on identifiers.<br>
	 * This function "normalizes" the identifier so that it can be used
	 * by the database.
	 * @param vendorName the database vendor
	 * @param identifier identifier to use
	 * @return valid identifier
	 */
	public String normalizeIdentifier (String vendorName, String identifier) {

		// ignore null
		if (identifier == null)
			return identifier;

		// maximum length of identifiers is 30 characters
		int maxlen = m_interfaces.get(getDBVendorID(vendorName)).getDBMaxIdentifierLength();

		// accept identifier within maximum length
		if (identifier.length() <= maxlen)
			return identifier;

		// remove any vowels from identifier
		if (identifier.contains("_")) {
			String prefix = identifier.substring(0, identifier.indexOf("_"));
			String suffix = identifier.substring(identifier.indexOf("_"), identifier.length()).replaceAll("[AEIOUaeiou]", "");
			identifier = new StringBuffer(prefix).append(suffix).toString();
		} else {
			identifier = identifier.replaceAll("[AEIOUaeiou]", "");
		}
		if (identifier.length() <= maxlen)
			return identifier;

		// remove any underscores from identifier
		identifier = identifier.replaceAll("_", "");
		if (identifier.length() <= maxlen)
			return identifier;

		// truncate identifier
		identifier = identifier.substring(0, maxlen+1);

		return identifier;
	}

	/**
	 * normalize null value replacement so that it can be used in column of specified type
	 * @param vendorName the database vendor
	 * @param dataTypeID  data type identifier
	 * @return valid null value replacement
	 */
	public String normalizeColumnValue (String vendorName, int dataTypeID) {
		return m_interfaces.get(getDBVendorID(vendorName)).normalizeColumnValue(dataTypeID);
	}

	/**
	/**
	 * correct expressions using quoted field names<p>
	 * some databases erroneously surround any column names used in expressions
	 * with quotes, which must be removed
	 * @param vendorName the database vendor
	 * @param expression the expression including quoted field names
	 * @return valid expression without quoted field names
	 */
	public String correctQuotedFieldNames (String vendorName, String expression) {
		return m_interfaces.get(getDBVendorID(vendorName)).correctQuotedFieldNames(expression);
	}

	// function translations

	/**
	 * translate function language from source database to target database
	 * @param sourceVendorName the source database vendor
	 * @param targetVendorName the target database vendor
	 * @param functionLanguage language of function
	 * @return function language as valid in target database
	 */
	private String translateFunctionLanguage (String sourceVendorName, String targetVendorName, String functionLanguage) {
		if (functionLanguage!=null)
			functionLanguage = functionLanguage.trim();
		return m_interfaces.get(getDBVendorID(targetVendorName)).translateFunctionLanguage(sourceVendorName.toUpperCase(), functionLanguage);
	}

	/**
	 * translate function type from source database to target database
	 * @param sourceVendorName the source database vendor
	 * @param targetVendorName the target database vendor
	 * @param functionType type of function
	 * @param functionReturnType return type of function
	 * @return function type as valid in target database
	 */
	private String translateFunctionType (String sourceVendorName, String targetVendorName, String functionType, String functionReturnType) {
		if (functionType!=null)
			functionType = functionType.trim();
		if (functionReturnType!=null)
			functionReturnType = functionReturnType.trim();
		return m_interfaces.get(getDBVendorID(targetVendorName)).translateFunctionType(sourceVendorName.toUpperCase(), functionType.toUpperCase(), functionReturnType);
	}

	/**
	 * translate function's return type from source database to target database
	 * @param sourceVendorName the source database vendor
	 * @param targetVendorName the target database vendor
	 * @param functionReturnType return type of function
	 * @return function return type as valid in target database
	 */
	private String translateFunctionReturnType (String sourceVendorName, String targetVendorName, String functionReturnType) {
		if (functionReturnType!=null)
			functionReturnType = functionReturnType.trim();
		return m_interfaces.get(getDBVendorID(targetVendorName)).translateFunctionReturnType(s_dbEngine, sourceVendorName.toUpperCase(), functionReturnType);
	}

	/**
	 * translate function body from source database to target database
	 * @param sourceVendorName the source database vendor
	 * @param sourceSchemaName schema used in source database
	 * @param targetVendorName the target database vendor
	 * @param functionLanguage language of function
	 * @param functionReturnType return type of function
	 * @param functionBodyText lines of code
	 * @return function body translated to target database
	 */
	private String translateFunctionBody (String sourceVendorName, String sourceSchemaName, String targetVendorName, String functionLanguage, String functionReturnType, String functionBodyText) {
		if (functionLanguage!=null)
			functionLanguage = functionLanguage.trim();
		if (functionReturnType!=null)
			functionReturnType = functionReturnType.trim();
		if (functionBodyText!=null)
			functionBodyText = functionBodyText.trim();

		// full translation if source vendor is same as target vendor
		// or if full translation is selected as option
		if (isAttemptTranslation() || getDBVendorID(sourceVendorName) == getDBVendorID(targetVendorName))
			return m_interfaces.get(getDBVendorID(targetVendorName)).translateFunctionBodyFull(s_dbEngine, sourceVendorName.toUpperCase(), sourceSchemaName, functionLanguage, functionReturnType, functionBodyText);
		else
			// otherwise replace with stub
			return m_interfaces.get(getDBVendorID(targetVendorName)).translateFunctionBodyStub(s_dbEngine, sourceVendorName.toUpperCase(), functionLanguage, functionReturnType, functionBodyText);
	}

	/**
	 * translate operator name from source database to target database
	 * @param sourceVendorName the source database vendor
	 * @param targetVendorName the target database vendor
	 * @param operatorName name of operator
	 * @return valid name of operator
	 */
	private String translateOperator (String sourceVendorName, String targetVendorName, String operatorName) {
		if (operatorName==null)
			operatorName = new String();
		operatorName = operatorName.trim();
		return m_interfaces.get(getDBVendorID(targetVendorName)).translateOperator(sourceVendorName.toUpperCase(), operatorName);
	}

	/**
	 * Whether or not triggers in a database can contain inline code
	 * @param vendorName the database vendor
	 * @return triggers of this database can contain inline code
	 */
	public boolean isTriggerContainsInlineCode (String vendorName) {
		return m_interfaces.get(getDBVendorID(vendorName)).isTriggerContainsInlineCode();
	}

	/**
	 * translate trigger type from source database to target database
	 * @param sourceVendorName the source database vendor
	 * @param targetVendorName the target database vendor
	 * @param triggerType type of trigger(before/after)
	 * @return valid trigger type
	 */
	private String translateTriggerType(String sourceVendorName, String targetVendorName, String triggerType) {
		if (triggerType==null)
			triggerType = new String ();
		return m_interfaces.get(getDBVendorID(targetVendorName)).translateTriggerType(sourceVendorName.toUpperCase(), triggerType);
	}

	/**
	 * translate trigger action orientation from source database to target database
	 * @param sourceVendorName the source database vendor
	 * @param targetVendorName the target database vendor
	 * @param actionOrientation fire on row or statement
	 * @return valid trigger type
	 */
	private String translateTriggerActionOrientation(String sourceVendorName, String targetVendorName, String actionOrientation) {
		if (actionOrientation==null)
			actionOrientation = new String ();
		return m_interfaces.get(getDBVendorID(targetVendorName)).translateTriggerActionOrientation(sourceVendorName.toUpperCase(), actionOrientation.toUpperCase());
	}

	/**
	 * translate trigger function from source database to target database
	 * @param sourceVendorName the source database vendor
	 * @param targetVendorName the target database vendor
	 * @param triggerFunction code block or procedure name
	 * @return valid trigger function
	 */
	private String translateTriggerFunction(String sourceVendorName, String targetVendorName, String triggerFunction) {
		if (triggerFunction==null)
			triggerFunction = new String ();
		return m_interfaces.get(getDBVendorID(targetVendorName)).translateTriggerFunction(sourceVendorName.toUpperCase(), triggerFunction);
	}

	/**
	 * translate trigger code from source database to target database
	 * @param sourceVendorName the source database vendor
	 * @param targetVendorName the target database vendor
	 * @param actionType inline code or call procedure
	 * @param triggerName the trigger to use
	 * @param triggerFunction code block or procedure name
	 * @return valid trigger code
	 */
	private String translateTriggerCode(String sourceVendorName, String targetVendorName, String actionType, String triggerName, String triggerFunction) {
		return m_interfaces.get(getDBVendorID(targetVendorName)).translateTriggerCode(sourceVendorName.toUpperCase(), actionType, triggerName, triggerFunction);
	}

	// view translations

	/**
	 * translate view definition from source database to target database
	 * @param sourceVendorName the source database vendor
	 * @param sourceSchemaName schema used in source database
	 * @param targetVendorName the target database vendor
	 * @param targetSchemaName schema used in target database
	 * @param viewDefinition text defining the view
	 * @return view definition translated to target database
	 */
	private String translateViewDefinition (String sourceVendorName, String sourceSchemaName, String targetVendorName, String targetSchemaName, String viewDefinition) {
		if (viewDefinition!=null)
			viewDefinition = viewDefinition.trim();

		// full translation if source vendor is same as target vendor
		// or if full translation is selected as option
		if (isAttemptTranslation() || getDBVendorID(sourceVendorName) == getDBVendorID(targetVendorName))
			return m_interfaces.get(getDBVendorID(targetVendorName)).translateViewDefinitionFull(getDBEngine(), sourceVendorName.toUpperCase(), sourceSchemaName, targetSchemaName, viewDefinition);
		else
			// otherwise replace with stub
			return m_interfaces.get(getDBVendorID(targetVendorName)).translateViewDefinitionStub(sourceVendorName.toUpperCase(), viewDefinition);
	}


	// sql compatibility

	/**
	 * gets the database specific SQL command(s) to create a schema
	 * @param step the step number to execute in a multi-step complex function (the first step is 0))
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param passwd the user's password
	 * @return SQL command to create schema (null if no further commands available)
	 */
	public String sqlAdmin_createSchema (int step, String vendorName, String catalogName, String schemaName, String passwd) {
		return m_interfaces.get(getDBVendorID(vendorName)).sqlAdmin_createSchema(step, catalogName, schemaName, passwd);
	}

	/**
	 * gets the database specific SQL command(s) to drop a schema
	 * @param step the step number to execute in a multi-step complex function (the first step is 0))
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return SQL command to drop schema (null if no further commands available)
	 */
	public String sqlAdmin_dropSchema (int step, String vendorName, String catalogName, String schemaName) {
		return m_interfaces.get(getDBVendorID(vendorName)).sqlAdmin_dropSchema(step, catalogName, schemaName);
	}

	/**
	 * gets the database specific SQL command(s) to connect to a schema
	 * @param step the step number to execute in a multi-step complex function (the first step is 0))
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return SQL command to connect to schema (null if no further commands available)
	 */
	public String sqlAdmin_connectSchema (int step, String vendorName, String catalogName, String schemaName) {
		return m_interfaces.get(getDBVendorID(vendorName)).sqlAdmin_connectSchema(step, catalogName, schemaName);
	}

	/**
	 * gets the database specific SQL command(s) to optimize a database
	 * @param step the step number to execute in a multi-step complex function (the first step is 0))
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return SQL command to optimize the database (null if no further commands available)
	 */
	public String sqlAdmin_optimizeDatabase (int step, String vendorName, String catalogName, String schemaName) {
		return m_interfaces.get(getDBVendorID(vendorName)).sqlAdmin_optimizeDatabase(step, catalogName, schemaName);
	}

	/**
	 * gets the database specific SQL command(s) to prepare a database before transfer migration starts
	 * @param step the step number to execute in a multi-step complex function (the first step is 0)
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return SQL command to prepare database (null if no further commands available)
	 */
	public String sqlAdmin_prepareDatabaseForTransfer (int step, String vendorName, String catalogName, String schemaName) {
		return m_interfaces.get(getDBVendorID(vendorName)).sqlAdmin_prepareDatabaseForTransfer(step, catalogName, schemaName);
	}

	/**
	 * gets the database specific SQL command to list available databases
	 * @param vendorName the database vendor
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">SQL command returning</th></tr>
	 * <tr><td><code>DATABASE_NAME</code></td><td>name of database</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>DATABASE_NAME</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_availableDatabases (String vendorName) {
		return m_interfaces.get(getDBVendorID(vendorName)).sqlMetadata_availableDatabases();
	}

	/**
	 * gets the database specific SQL command to create a table for testing the character set of a database<br>
	 * (needed because buggy ORACLE jdbc driver sometimes uses size of BYTE instead of size of CHAR)
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return SQL command to open character set test
	 */
	@SuppressWarnings("static-access")
	public String sqlMetadata_openCharSetTest (String vendorName, String catalogName, String schemaName) {
		return m_interfaces.get(getDBVendorID(vendorName)).sqlMetadata_openCharSetTest(catalogName, schemaName, s_parameters.CHARSETTABLENAME);
	}

	/**
	 * gets the database specific SQL command to drop a table for testing the character set of a database<br>
	 * (needed because buggy ORACLE jdbc driver sometimes uses size of BYTE instead of size of CHAR)
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return SQL command to close character set test
	 */
	@SuppressWarnings("static-access")
	public String sqlMetadata_closeCharSetTest (String vendorName, String catalogName, String schemaName) {
		return m_interfaces.get(getDBVendorID(vendorName)).sqlMetadata_closeCharSetTest(catalogName, schemaName, s_parameters.CHARSETTABLENAME);
	}

	/**
	 * gets the database specific SQL command to find table names
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">SQL command returning</th></tr>
	 * <tr><td><code>OBJECT_NAME</code></td><td>table name</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>OBJECT_NAME</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_tableNames (String vendorName, String catalogName, String schemaName) {
		return m_interfaces.get(getDBVendorID(vendorName)).sqlMetadata_tableNames(catalogName, schemaName);
	}

	/**
	 * gets the database specific SQL command to find columns in a table
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">Prepared statement returning</th></tr>
	 * <tr><td><code>TABLE_NAME</code></td><td>table name</td></tr>
	 * <tr><td><code>COLUMN_SEQUENCE</code></td><td>sequence number of column</td></tr>
	 * <tr><td><code>COLUMN_NAME</code></td><td>column name</td></tr>
	 * <tr><td><code>COLUMN_TYPE</code></td><td>column data type</td></tr>
	 * <tr><td><code>COLUMN_SIZE</code></td><td>column size</td></tr>
	 * <tr><td><code>COLUMN_PRECISION</code></td><td>column scale</td></tr>
	 * <tr><td><code>COLUMN_DEFAULT</code></td><td>column's default value</td></tr>
	 * <tr><td><code>COLUMN_NULLABLE</code></td><td>column is nullable</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">using parameters</th></tr>
	 * <tr><td>1</td><td><code>TABLE_NAME</code></td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>COLUMN_SEQUENCE</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_tableColumns (String vendorName, String catalogName, String schemaName) {
		return m_interfaces.get(getDBVendorID(vendorName)).sqlMetadata_tableColumns(catalogName, schemaName);
	}

	/**
	 * gets the database specific SQL command to find view names
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">SQL command returning</th></tr>
	 * <tr><td><code>OBJECT_NAME</code></td><td>view name</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>OBJECT_NAME</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_viewNames (String vendorName, String catalogName, String schemaName) {
		return m_interfaces.get(getDBVendorID(vendorName)).sqlMetadata_viewNames(catalogName, schemaName);
	}

	/**
	 * gets the database specific SQL command to find view definitions
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">Prepared statement returning</th></tr>
	 * <tr><td><code>VIEW_NAME</code></td><td>view name</td></tr>
	 * <tr><td><code>VIEW_DEFINITION</code></td><td>view definition</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">using parameters</th></tr>
	 * <tr><td>1</td><td><code>VIEW_NAME</code></td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>VIEW_NAME</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_viewDefinitions (String vendorName, String catalogName, String schemaName) {
		return m_interfaces.get(getDBVendorID(vendorName)).sqlMetadata_viewDefinitions(catalogName, schemaName);
	}

	/**
	 * gets the database specific SQL command to find function names
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">SQL command returning</th></tr>
	 * <tr><td><code>OBJECT_NAME</code></td><td>function name</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>OBJECT_NAME</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_functionNames (String vendorName, String catalogName, String schemaName) {
		return m_interfaces.get(getDBVendorID(vendorName)).sqlMetadata_functionNames(catalogName, schemaName);
	}

	/**
	 * gets the database specific SQL command to find function arguments
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">Prepared statement returning</th></tr>
	 * <tr><td><code>FUNC_NAME</code></td><td>function name</td></tr>
	 * <tr><td><code>FUNC_TYPE</code></td><td>function type</td></tr>
	 * <tr><td><code>FUNC_LANG</code></td><td>function language</td></tr>
	 * <tr><td><code>RET_TYPE</code></td><td>function data type</td></tr>
	 * <tr><td><code>SEQ_NUM</code></td><td>sequence number of argument</td></tr>
	 * <tr><td><code>ARG_DIR</code></td><td>argument direction</td></tr>
	 * <tr><td><code>ARG_NAME</code></td><td>argument name</td></tr>
	 * <tr><td><code>ARG_TYPE</code></td><td>argument data type</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">using parameters</th></tr>
	 * <tr><td>1</td><td><code>FUNC_NAME</code></td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>FUNC_NAME</code></td></tr>
	 * <tr><td>2</td><td><code>SEQ_NUM</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_functionArguments (String vendorName, String catalogName, String schemaName) {
		return m_interfaces.get(getDBVendorID(vendorName)).sqlMetadata_functionArguments(catalogName, schemaName);
	}

	/**
	 * gets the database specific SQL command to find function bodies
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">Prepared statement returning</th></tr>
	 * <tr><td><code>FUNC_NAME</code></td><td>function name</td></tr>
	 * <tr><td><code>SEQ_NUM</code></td><td>sequence number of code line in function body</td></tr>
	 * <tr><td><code>FUNC_DEF</code></td><td>function body or code line in body</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">using parameters</th></tr>
	 * <tr><td>1..n</td><td><code>FUNC_NAME</code> (can be used multiple times)</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>SEQ_NUM</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_functionBodies (String vendorName, String catalogName, String schemaName) {
		return m_interfaces.get(getDBVendorID(vendorName)).sqlMetadata_functionBodies(catalogName, schemaName);
	}

	/**
	 * gets the database specific SQL command to find operator names
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">SQL command returning</th></tr>
	 * <tr><td><code>OBJECT_NAME</code></td><td>operator name</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>OBJECT_NAME</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_operatorNames (String vendorName, String catalogName, String schemaName) {
		return m_interfaces.get(getDBVendorID(vendorName)).sqlMetadata_operatorNames(catalogName, schemaName);
	}

	/**
	 * gets the database specific SQL command to find operator signatures
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">Prepared statement returning</th></tr>
	 * <tr><td><code>OPERATOR_NAME</code></td><td>operator name</td></tr>
	 * <tr><td><code>LEFT_ARG</code></td><td>data type of left argument</td></tr>
	 * <tr><td><code>RIGHT_ARG</code></td><td>data type of right argument</td></tr>
	 * <tr><td><code>RETURN_TYPE</code></td><td>data type of return value</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">using parameters</th></tr>
	 * <tr><td>1</td><td><code>OPERATOR_NAME (LEFT_ARG, RIGHT_ARG)</code></td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>OPERATOR_NAME</code></td></tr>
	 * <tr><td>2</td><td><code>LEFT_ARG</code></td></tr>
	 * <tr><td>3</td><td><code>RIGHT_ARG</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_operatorSignatures (String vendorName, String catalogName, String schemaName) {
		return m_interfaces.get(getDBVendorID(vendorName)).sqlMetadata_operatorSignatures(catalogName, schemaName);
	}

	/**
	 * gets the database specific SQL command to find operator definitions
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">Prepared statement returning</th></tr>
	 * <tr><td><code>OPERATOR_NAME</code></td><td>operator name</td></tr>
	 * <tr><td><code>FUNCTION_NAME</code></td><td>name of function to call</td></tr>
	 * <tr><td><code>OP_COMMUTATOR</code></td><td>commutator</td></tr>
	 * <tr><td><code>OP_NEGATOR</code></td><td>negator</td></tr>
	 * <tr><td><code>OP_RESTRICT</code></td><td>restriction selectivity estimator function</td></tr>
	 * <tr><td><code>OP_JOIN</code></td><td>join selectivity estimator function</td></tr>
	 * <tr><td><code>OP_HASHABLE</code></td><td>this operator supports hash joins</td></tr>
	 * <tr><td><code>OP_MERGEABLE</code></td><td>this operator supports merge joins</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">using parameters</th></tr>
	 * <tr><td>1</td><td><code>OPERATOR_NAME (LEFT_ARG, RIGHT_ARG)</code></td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>OPERATOR_NAME</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_operatorDefinitions (String vendorName, String catalogName, String schemaName) {
		return m_interfaces.get(getDBVendorID(vendorName)).sqlMetadata_operatorDefinitions(catalogName, schemaName);
	}

	/**
	 * gets the database specific SQL command to find trigger names
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">SQL command returning</th></tr>
	 * <tr><td><code>OBJECT_NAME</code></td><td>trigger name</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>OBJECT_NAME</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_triggerNames (String vendorName, String catalogName, String schemaName) {
		return m_interfaces.get(getDBVendorID(vendorName)).sqlMetadata_triggerNames(catalogName, schemaName);
	}

	/**
	 * gets the database specific SQL command to find triggered tables
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">Prepared statement returning</th></tr>
	 * <tr><td><code>TRIG_NAME</code></td><td>trigger name</td></tr>
	 * <tr><td><code>TRIG_TYPE</code></td><td>type of trigger (before/after)</td></tr>
	 * <tr><td><code>TRIG_EVENT</code></td><td>triggering event</td></tr>
	 * <tr><td><code>TABLE_NAME</code></td><td>name of triggered table</td></tr>
	 * <tr><td><code>ACTION_TYPE</code></td><td>inline code or call procedure</td></tr>
	 * <tr><td><code>ACTION_ORIENTATION</code></td><td>fire on row or statement</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">using parameters</th></tr>
	 * <tr><td>1</td><td><code>TRIG_NAME</code></td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>TRIG_NAME</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_triggerTables (String vendorName, String catalogName, String schemaName) {
		return m_interfaces.get(getDBVendorID(vendorName)).sqlMetadata_triggerTables(catalogName, schemaName);
	}

	/**
	 * gets the database specific SQL command to find trigger definitions
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">Prepared statement returning</th></tr>
	 * <tr><td><code>TRIG_NAME</code></td><td>trigger name</td></tr>
	 * <tr><td><code>TRIG_BODY</code></td><td>code block or procedure name</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">using parameters</th></tr>
	 * <tr><td>1</td><td><code>TRIG_NAME</code></td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>TRIG_NAME</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_triggerDefinitions (String vendorName, String catalogName, String schemaName) {
		return m_interfaces.get(getDBVendorID(vendorName)).sqlMetadata_triggerDefinitions(catalogName, schemaName);
	}

	/**
	 * gets the database specific SQL command to find sequence names
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">SQL command returning</th></tr>
	 * <tr><td><code>OBJECT_NAME</code></td><td>sequence name</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>OBJECT_NAME</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_sequenceNames (String vendorName, String catalogName, String schemaName) {
		return m_interfaces.get(getDBVendorID(vendorName)).sqlMetadata_sequenceNames(catalogName, schemaName);
	}

	/**
	 * gets the database specific SQL command to find sequence definitions
	 * @param vendorName the database vendor
	 * @param productVersion the product version
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param sequenceName the sequence to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">SQL command returning</th></tr>
	 * <tr><td><code>MIN_VALUE</code></td><td>minimum value</td></tr>
	 * <tr><td><code>MAX_VALUE</code></td><td>maximum value</td></tr>
	 * <tr><td><code>INCREMENT_BY</code></td><td>increment amount</td></tr>
	 * <tr><td><code>IS_CYCLED</code></td><td>sequence is cycled</td></tr>
	 * <tr><td><code>CACHE_SIZE</code></td><td>cache size</td></tr>
	 * <tr><td><code>LAST_VALUE</code></td><td>last used value</td></tr>
	 * </table>
	 */
	public String sqlMetadata_sequenceDefinitions (String vendorName, String productVersion , String catalogName, String schemaName, String sequenceName) {
		return m_interfaces.get(getDBVendorID(vendorName)).sqlMetadata_sequenceDefinitions(productVersion , catalogName, schemaName, sequenceName);
	}

	/**
	 * gets the database specific SQL command to find primary key names
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">SQL command returning</th></tr>
	 * <tr><td><code>OBJECT_NAME</code></td><td>primary key name</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>OBJECT_NAME</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_primaryKeyNames (String vendorName, String catalogName, String schemaName) {
		return m_interfaces.get(getDBVendorID(vendorName)).sqlMetadata_primaryKeyNames(catalogName, schemaName);
	}

	/**
	 * gets the database specific SQL command to find primary key tables
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">Prepared statement returning</th></tr>
	 * <tr><td><code>PK_NAME</code></td><td>primary key name</td></tr>
	 * <tr><td><code>TABLE_NAME</code></td><td>table name</td></tr>
	 * <tr><td><code>IS_DEFERRABLE</code></td><td>primary key is deferrable</td></tr>
	 * <tr><td><code>INITIALLY_DEFERRED</code></td><td>primary key is initially deferred</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">using parameters</th></tr>
	 * <tr><td>1</td><td><code>PK_NAME</code></td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>PK_NAME</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_primaryKeyTables (String vendorName, String catalogName, String schemaName) {
		return m_interfaces.get(getDBVendorID(vendorName)).sqlMetadata_primaryKeyTables(catalogName, schemaName);
	}

	/**
	 * gets the database specific SQL command to find primary key columns
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">Prepared statement returning</th></tr>
	 * <tr><td><code>PK_NAME</code></td><td>primary key name</td></tr>
	 * <tr><td><code>PK_SEQ</code></td><td>sequence number of column</td></tr>
	 * <tr><td><code>TABLE_NAME</code></td><td>table name</td></tr>
	 * <tr><td><code>COLUMN_NAME</code></td><td>column name</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">using parameters</th></tr>
	 * <tr><td>1</td><td><code>PK_NAME</code></td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>PK_SEQ</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_primaryKeyColumns (String vendorName, String catalogName, String schemaName) {
		return m_interfaces.get(getDBVendorID(vendorName)).sqlMetadata_primaryKeyColumns(catalogName, schemaName);
	}

	/**
	 * gets the database specific SQL command to find foreign key names
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">SQL command returning</th></tr>
	 * <tr><td><code>OBJECT_NAME</code></td><td>foreign key name</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>OBJECT_NAME</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_foreignKeyNames (String vendorName, String catalogName, String schemaName) {
		return m_interfaces.get(getDBVendorID(vendorName)).sqlMetadata_foreignKeyNames(catalogName, schemaName);
	}

	/**
	 * gets the database specific SQL command to find foreign key tables
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">Prepared statement returning</th></tr>
	 * <tr><td><code>FK_NAME</code></td><td>foreign key name</td></tr>
	 * <tr><td><code>TABLE_NAME</code></td><td>local table name</td></tr>
	 * <tr><td><code>FTABLE_NAME</code></td><td>foreign table name</td></tr>
	 * <tr><td><code>IS_DEFERRABLE</code></td><td>foreign key is deferrable</td></tr>
	 * <tr><td><code>INITIALLY_DEFERRED</code></td><td>foreign key is initially deferred</td></tr>
	 * <tr><td><code>MATCH_TYPE</code></td><td>match type</td></tr>
	 * <tr><td><code>ON_UPDATE</code></td><td>update action</td></tr>
	 * <tr><td><code>ON_DELETE</code></td><td>delete action</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">using parameters</th></tr>
	 * <tr><td>1</td><td><code>FK_NAME</code></td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>FK_NAME</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_foreignKeyTables (String vendorName, String catalogName, String schemaName) {
		return m_interfaces.get(getDBVendorID(vendorName)).sqlMetadata_foreignKeyTables(catalogName, schemaName);
	}

	/**
	 * gets the database specific SQL command to find foreign key columns
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">Prepared statement returning</th></tr>
	 * <tr><td><code>FK_NAME</code></td><td>foreign key name</td></tr>
	 * <tr><td><code>FK_SEQ</code></td><td>sequence number of column</td></tr>
	 * <tr><td><code>TABLE_NAME</code></td><td>local table name</td></tr>
	 * <tr><td><code>COLUMN_NAME</code></td><td>local column name</td></tr>
	 * <tr><td><code>FTABLE_NAME</code></td><td>foreign table name</td></tr>
	 * <tr><td><code>FCOLUMN_NAME</code></td><td>foreign column name</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">using parameters</th></tr>
	 * <tr><td>1</td><td><code>FK_NAME</code></td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>FK_NAME</code></td></tr>
	 * <tr><td>2</td><td><code>FK_SEQ</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_foreignKeyColumns (String vendorName, String catalogName, String schemaName) {
		return m_interfaces.get(getDBVendorID(vendorName)).sqlMetadata_foreignKeyColumns(catalogName, schemaName);
	}

	/**
	 * gets the database specific SQL command to find check constraint names
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">SQL command returning</th></tr>
	 * <tr><td><code>OBJECT_NAME</code></td><td>check constraint name</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>OBJECT_NAME</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_checkNames (String vendorName, String catalogName, String schemaName) {
		return m_interfaces.get(getDBVendorID(vendorName)).sqlMetadata_checkNames(catalogName, schemaName);
	}

	/**
	 * gets the database specific SQL command to find check constraint tables
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">Prepared statement returning</th></tr>
	 * <tr><td><code>CHECK_NAME</code></td><td>check constraint name</td></tr>
	 * <tr><td><code>TABLE_NAME</code></td><td>table name</td></tr>
	 * <tr><td><code>IS_DEFERRABLE</code></td><td>constraint is deferrable</td></tr>
	 * <tr><td><code>INITIALLY_DEFERRED</code></td><td>constraint is initially deferred</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">using parameters</th></tr>
	 * <tr><td>1</td><td><code>CHECK_NAME</code></td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>CHECK_NAME</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_checkTables (String vendorName, String catalogName, String schemaName) {
		return m_interfaces.get(getDBVendorID(vendorName)).sqlMetadata_checkTables(catalogName, schemaName);
	}

	/**
	 * gets the database specific SQL command to find check constraint rules
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">Prepared statement returning</th></tr>
	 * <tr><td><code>CHECK_NAME</code></td><td>check constraint name</td></tr>
	 * <tr><td><code>TABLE_NAME</code></td><td>table name</td></tr>
	 * <tr><td><code>CHECK_CLAUSE</code></td><td>check constraint clause</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">using parameters</th></tr>
	 * <tr><td>1</td><td><code>CHECK_NAME</code></td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>CHECK_NAME</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_checkRules (String vendorName, String catalogName, String schemaName) {
		return m_interfaces.get(getDBVendorID(vendorName)).sqlMetadata_checkRules(catalogName, schemaName);
	}

	/**
	 * gets the database specific SQL command to find unique constraint names
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">SQL command returning</th></tr>
	 * <tr><td><code>OBJECT_NAME</code></td><td>unique constraint name</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>OBJECT_NAME</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_uniqueNames (String vendorName, String catalogName, String schemaName) {
		return m_interfaces.get(getDBVendorID(vendorName)).sqlMetadata_uniqueNames(catalogName, schemaName);
	}

	/**
	 * gets the database specific SQL command to find unique constraint tables
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">Prepared statement returning</th></tr>
	 * <tr><td><code>UNIQUE_NAME</code></td><td>unique constraint name</td></tr>
	 * <tr><td><code>TABLE_NAME</code></td><td>table name</td></tr>
	 * <tr><td><code>IS_DEFERRABLE</code></td><td>constraint is deferrable</td></tr>
	 * <tr><td><code>INITIALLY_DEFERRED</code></td><td>constraint is initially deferred</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">using parameters</th></tr>
	 * <tr><td>1</td><td><code>UNIQUE_NAME</code></td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>UNIQUE_NAME</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_uniqueTables (String vendorName, String catalogName, String schemaName) {
		return m_interfaces.get(getDBVendorID(vendorName)).sqlMetadata_uniqueTables(catalogName, schemaName);
	}

	/**
	 * gets the database specific SQL command to find unique constraint columns
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">Prepared statement returning</th></tr>
	 * <tr><td><code>UNIQUE_NAME</code></td><td>check constraint name</td></tr>
	 * <tr><td><code>UNIQUE_SEQ</code></td><td>sequence of column</td></tr>
	 * <tr><td><code>TABLE_NAME</code></td><td>table name</td></tr>
	 * <tr><td><code>COLUMN_NAME</code></td><td>column name</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">using parameters</th></tr>
	 * <tr><td>1</td><td><code>UNIQUE_NAME</code></td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>UNIQUE_SEQ</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_uniqueColumns (String vendorName, String catalogName, String schemaName) {
		return m_interfaces.get(getDBVendorID(vendorName)).sqlMetadata_uniqueColumns(catalogName, schemaName);
	}

	/**
	 * gets the database specific SQL command to find index names
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">SQL command returning</th></tr>
	 * <tr><td><code>OBJECT_NAME</code></td><td>index name</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>OBJECT_NAME</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_indexNames (String vendorName, String catalogName, String schemaName) {
		return m_interfaces.get(getDBVendorID(vendorName)).sqlMetadata_indexNames(catalogName, schemaName);
	}

	/**
	 * gets the database specific SQL command to find indexed tables
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">Prepared statement returning</th></tr>
	 * <tr><td><code>INDEX_NAME</code></td><td>index name</td></tr>
	 * <tr><td><code>TABLE_NAME</code></td><td>table name</td></tr>
	 * <tr><td><code>IS_UNIQUE</code></td><td>index is unique</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">using parameters</th></tr>
	 * <tr><td>1</td><td><code>INDEX_NAME</code></td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>INDEX_NAME</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_indexTables (String vendorName, String catalogName, String schemaName) {
		return m_interfaces.get(getDBVendorID(vendorName)).sqlMetadata_indexTables(catalogName, schemaName);
	}

	/**
	 * gets the database specific SQL command to find indexed columns
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">Prepared statement returning</th></tr>
	 * <tr><td><code>INDEX_NAME</code></td><td>index name</td></tr>
	 * <tr><td><code>INDEX_SEQ</code></td><td>sequence number of column</td></tr>
	 * <tr><td><code>TABLE_NAME</code></td><td>table name</td></tr>
	 * <tr><td><code>COLUMN_NAME</code></td><td>column name</td></tr>
	 * <tr><td><code>SORT_ORDER</code></td><td>ascending or descending</td></tr>
	 * <tr><td><code>SORT_NULLS</code></td><td>first or last</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">using parameters</th></tr>
	 * <tr><td>1</td><td><code>INDEX_NAME</code></td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>INDEX_NAME</code></td></tr>
	 * <tr><td>2</td><td><code>INDEX_SEQ</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_indexColumns (String vendorName, String catalogName, String schemaName) {
		return m_interfaces.get(getDBVendorID(vendorName)).sqlMetadata_indexColumns(catalogName, schemaName);
	}

	/**
	 * gets the database specific SQL command to create tables
	 * @param sourceVendorName the source database vendor
	 * @param targetVendorName the target database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param columnNames array of column names as defined in source
	 * @param columnTypes array of column types as defined in source
	 * @param columnSizes array of column sizes as defined in source
	 * @param columnScales array of column scales as defined in source
	 * @param columnNullables array of boolean values whether column is nullable
	 * @param columnDefaults array of column defaults
	 * @return SQL command to create table
	 */
	public String sqlObject_createTable (String sourceVendorName, String targetVendorName, String catalogName, String schemaName, String tableName, ArrayList<String> columnNames, ArrayList<String> columnTypes, ArrayList<Integer> columnSizes, ArrayList<Integer> columnScales, ArrayList<Boolean> columnNullables, ArrayList<String> columnDefaults) {

		// translate table name
		tableName = normalizeIdentifier(targetVendorName, tableName);

		// translate column name, data types, and expressions to target
		ArrayList<String> normalizedColumnNames = new ArrayList<String> ();
		ArrayList<String> translatedColumnTypes = new ArrayList<String> ();
		ArrayList<String> translatedColumnDefaults = new ArrayList<String> ();
		for (int i = 0; i < columnNames.size(); i++) {
			normalizedColumnNames.add(normalizeColumnName(targetVendorName,columnNames.get(i)));
			translatedColumnTypes.add(translateDataType(sourceVendorName, targetVendorName, columnTypes.get(i), columnSizes.get(i), columnScales.get(i)));
			translatedColumnDefaults.add(translateExpression(sourceVendorName, targetVendorName, columnDefaults.get(i)));
		}

		// get SQL command
		return m_interfaces.get(getDBVendorID(targetVendorName)).sqlObject_createTable(catalogName, schemaName, tableName, normalizedColumnNames, translatedColumnTypes, columnNullables, translatedColumnDefaults);
	}


	/**
	 * gets the database specific SQL command to drop tables
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @return SQL command to drop table
	 */
	public String sqlObject_dropTable (String vendorName, String catalogName, String schemaName, String tableName) {
		return m_interfaces.get(getDBVendorID(vendorName)).sqlObject_dropTable(catalogName, schemaName, tableName);
	}


	/**
	 * gets the database specific SQL command to create views
	 * @param sourceVendorName the source database vendor
	 * @param sourceSchemaName schema used in the source database
	 * @param targetVendorName the target database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param viewName the view to use
	 * @param viewDefinition text defining the view
	 * @param isForceStub the view body should be commented out
	 * @return SQL command to create view
	 */
	public String sqlObject_createView (String sourceVendorName, String sourceSchemaName, String targetVendorName, String catalogName, String schemaName, String viewName, String viewDefinition, boolean isForceStub) {

		// translate view name
		viewName = normalizeIdentifier(targetVendorName, viewName);

		// translate view definition to target
		if (isForceStub)
			viewDefinition = m_interfaces.get(getDBVendorID(targetVendorName)).translateViewDefinitionStub(sourceVendorName.toUpperCase(), viewDefinition);
		else
			viewDefinition = translateViewDefinition(sourceVendorName, sourceSchemaName, targetVendorName, schemaName, viewDefinition);

		// get SQL command
		return m_interfaces.get(getDBVendorID(targetVendorName)).sqlObject_createView(catalogName, schemaName, viewName, viewDefinition);
	}



	/**
	 * gets the database specific SQL command to drop views
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param viewName the view to use
	 * @return SQL command to drop view
	 */
	public String sqlObject_dropView (String vendorName, String catalogName, String schemaName, String viewName) {
		return m_interfaces.get(getDBVendorID(vendorName)).sqlObject_dropView(catalogName, schemaName, viewName);
	}

	/**
	 * gets the database specific SQL command to create functions
	 * @param sourceVendorName the source database vendor
	 * @param sourceSchemaName schema used in source database
	 * @param targetVendorName the target database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param functionType type of function
	 * @param functionName the function to use
	 * @param functionReturnType return type of function
	 * @param hasOutParameters the function uses OUT parameters
	 * @param functionLanguage language of function
	 * @param argDirs array of directions of arguments
	 * @param argNames array of argument names
	 * @param argTypes array of argument types
	 * @param bodyText code of function
	 * @param isForceStub the function body should be commented out
	 * @return SQL command to create function
	 */
	public String sqlObject_createFunction (String sourceVendorName, String sourceSchemaName, String targetVendorName, String catalogName, String schemaName, String functionType, String functionName, String functionReturnType, boolean hasOutParameters, String functionLanguage, ArrayList<String> argDirs, ArrayList<String> argNames, ArrayList<String> argTypes, String bodyText, boolean isForceStub) {

		// translate function name
		functionName = normalizeIdentifier(targetVendorName, functionName);

		// translate function type
		String translatedFunctionType = translateFunctionType(sourceVendorName, targetVendorName, functionType, functionReturnType);

		// translate argument types
		ArrayList<String> translatedArgTypes = new ArrayList<String> ();
		for (int i=0; i<argTypes.size(); i++) {

			String argType = translateDataType(sourceVendorName, targetVendorName, argTypes.get(i), 0, 0);
			if (argType != null)
				argType = argType.replaceAll("\\s*\\(.*\\)", "");

			translatedArgTypes.add(argType);
		}

		// translate function language
		String translatedFunctionLanguage = translateFunctionLanguage(sourceVendorName, targetVendorName, functionLanguage);

		// translate function return type
		String translatedFunctionReturnType = translateFunctionReturnType(sourceVendorName, targetVendorName, functionReturnType);
		if (translatedFunctionReturnType != null)
			translatedFunctionReturnType = translatedFunctionReturnType.replaceAll("\\s*\\(.*\\)", "");

		// translate body text
		String translatedBodyText = "";
		if (isForceStub)
			translatedBodyText = m_interfaces.get(getDBVendorID(targetVendorName)).translateFunctionBodyStub(s_dbEngine, sourceVendorName.toUpperCase(), functionLanguage, functionReturnType, bodyText);
		else
			translatedBodyText = translateFunctionBody(sourceVendorName, sourceSchemaName, targetVendorName, functionLanguage, functionReturnType, bodyText);
		// make sure END statements end with ";"
		translatedBodyText = translatedBodyText.replaceAll("(?m)^(\\s*?END\\s*?\\w*?)(\\s*?)$", "$1;$2");

		// get SQL command
		return m_interfaces.get(getDBVendorID(targetVendorName)).sqlObject_createFunction(catalogName, schemaName, translatedFunctionType, functionName, translatedFunctionReturnType, hasOutParameters, translatedFunctionLanguage, argDirs, argNames, translatedArgTypes, translatedBodyText);
	}

	/**
	 * gets the database specific SQL command to drop functions
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param functionType type of function
	 * @param functionName the function to use
	 * @param functionReturnType return type of function
	 * @param functionSignature fully qualified name of function to use
	 * @return SQL command to drop function
	 */
	public String sqlObject_dropFunction (String vendorName, String catalogName, String schemaName, String functionType, String functionName, String functionReturnType, String functionSignature) {

		// translate function type
		String translatedFunctionType = translateFunctionType(vendorName, vendorName, functionType, functionReturnType);

		return m_interfaces.get(getDBVendorID(vendorName)).sqlObject_dropFunction(catalogName, schemaName, translatedFunctionType, functionName, functionSignature);
	}

	/**
	 * gets the database specific SQL command to create operators
	 * @param sourceVendorName the source database vendor
	 * @param targetVendorName the target database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param operatorName the operator to use
	 * @param leftArg data type of left argument
	 * @param rightArg data type of right argument
	 * @param returnType data type of return value
	 * @param functionName name of function to call
	 * @param commutator commutator
	 * @param negator negator
	 * @param restrictor restriction selectivity estimator function
	 * @param joiner join selectivity estimator function
	 * @param isHashable this operator supports hash joins
	 * @param isMergeable this operator supports merge joins
	 * @return SQL command to create operator
	 */
	public String sqlObject_createOperator (String sourceVendorName, String targetVendorName, String catalogName, String schemaName, String operatorName, String leftArg, String rightArg, String returnType, String functionName, String commutator, String negator, String restrictor, String joiner, boolean isHashable, boolean isMergeable) {

		// translate operator name
		if (operatorName!=null)
			operatorName = translateOperator(sourceVendorName, targetVendorName, operatorName);

		// translate data types
		if (leftArg!=null) {
			leftArg = translateDataType(sourceVendorName, targetVendorName, leftArg, 0, 0);
			leftArg = leftArg.replaceAll("\\s*\\(.*\\)", "");
		}
		if (rightArg!=null) {
			rightArg = translateDataType(sourceVendorName, targetVendorName, rightArg, 0, 0);
			rightArg = rightArg.replaceAll("\\s*\\(.*\\)", "");
		}
		if (returnType!=null) {
			returnType = translateDataType(sourceVendorName, targetVendorName, returnType, 0, 0);
			returnType = returnType.replaceAll("\\s*\\(.*\\)", "");
		}

		// get SQL command
		return m_interfaces.get(getDBVendorID(targetVendorName)).sqlObject_createOperator(catalogName, schemaName, operatorName, leftArg, rightArg, returnType, functionName, commutator, negator, restrictor, joiner, isHashable, isMergeable);
	}

	/**
	 * gets the database specific SQL command to drop operators
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param operatorName the operator to use
	 * @param leftArg type of left argument
	 * @param rightArg type of right argument
	 * @return SQL command to drop operator
	 */
	public String sqlObject_dropOperator (String vendorName, String catalogName, String schemaName, String operatorName, String leftArg, String rightArg) {
		return m_interfaces.get(getDBVendorID(vendorName)).sqlObject_dropOperator(catalogName, schemaName, operatorName, leftArg, rightArg);
	}

	/**
	 * gets the database specific SQL command to create triggers
	 * @param sourceVendorName the source database vendor
	 * @param targetVendorName the target database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param triggerName the trigger to use
	 * @param tableName name of triggered table
	 * @param triggerType type of trigger (before/after)
	 * @param triggerEvent triggering event
	 * @param actionOrientation fire on row or statement
	 * @param actionType inline code or call procedure
	 * @param triggerFunction code block or procedure name
	 * @return SQL command to create trigger
	 */
	public String sqlObject_createTrigger (String sourceVendorName, String targetVendorName, String catalogName, String schemaName, String triggerName, String tableName, String triggerType, String triggerEvent, String actionOrientation, String actionType, String triggerFunction) {

		// translate trigger name
		triggerName = normalizeIdentifier(targetVendorName, triggerName);

		// translate trigger type
		triggerType = translateTriggerType(sourceVendorName, targetVendorName, triggerType);

		// translate trigger action orientation
		actionOrientation = translateTriggerActionOrientation(sourceVendorName, targetVendorName, actionOrientation);

		// translate trigger function
		triggerFunction = translateTriggerFunction(sourceVendorName, targetVendorName, triggerFunction);

		// translate trigger code
		String triggerCode = translateTriggerCode(sourceVendorName, targetVendorName, actionType, triggerName, triggerFunction);

		// get SQL command
		return m_interfaces.get(getDBVendorID(targetVendorName)).sqlObject_createTrigger(catalogName, schemaName, triggerName, tableName, triggerType, triggerEvent, actionOrientation, triggerCode);

	}

	/**
	 * gets the database specific SQL command to drop triggers
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param triggerName the trigger to use
	 * @param tableName name name of triggered table
	 * @return SQL command to drop trigger
	 */
	public String sqlObject_dropTrigger (String vendorName, String catalogName, String schemaName, String triggerName, String tableName) {
		return m_interfaces.get(getDBVendorID(vendorName)).sqlObject_dropTrigger(catalogName, schemaName, triggerName, tableName);
	}

	/**
	 * gets the database specific SQL command to create sequences
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param sequenceName the sequence to use
	 * @param min minimum value
	 * @param max maximum value
	 * @param incr sequence increment
	 * @param isCycled wrap around when maximum reached
	 * @param cache cache size
	 * @param start first sequence number
	 * @return SQL command to create sequence
	 */
	public String sqlObject_createSequence (String vendorName, String catalogName, String schemaName, String sequenceName, long min, long max, long incr, boolean isCycled, long cache, long start) {

		// translate sequence name
		sequenceName = normalizeIdentifier(vendorName, sequenceName);

		return m_interfaces.get(getDBVendorID(vendorName)).sqlObject_createSequence(catalogName, schemaName, sequenceName, min, max, incr, isCycled, cache, start);
	}

	/**
	 * gets the database specific SQL command to drop sequences
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param sequenceName the sequence to use
	 * @return SQL command to drop sequence
	 */
	public String sqlObject_dropSequence (String vendorName, String catalogName, String schemaName, String sequenceName) {
		return m_interfaces.get(getDBVendorID(vendorName)).sqlObject_dropSequence(catalogName, schemaName, sequenceName);
	}

	/**
	 * gets the database specific SQL command to create primary keys
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param keyName name of the primary key
	 * @param isDeferrable the constraint is deferrable
	 * @param isDeferred the constraint is initially deferred
	 * @param keyColumns array of columns that constitute the primary key
	 * @return SQL command to create the primary key
	 */
	public String sqlObject_createPrimaryKey (String vendorName, String catalogName, String schemaName, String tableName, String keyName, boolean isDeferrable, boolean isDeferred, ArrayList<String> keyColumns) {

		// translate key name
		keyName = normalizeIdentifier(vendorName, keyName);

		return m_interfaces.get(getDBVendorID(vendorName)).sqlObject_createPrimaryKey(catalogName, schemaName, tableName, keyName, isDeferrable, isDeferred, keyColumns);
	}

	/**
	 * gets the database specific SQL command to create foreign keys
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param keyName the foreign key to use
	 * @param localTable table in which to create the foreign key
	 * @param localColumns array of bound columns in the local table
	 * @param foreignTable foreign table to which the foreign key points
	 * @param foreignColumns array of referenced columns in the foreign table
	 * @param matchType the match type
	 * @param onDelete the delete action
	 * @param onUpdate the update action
	 * @param isDeferrable the constraint is deferrable
	 * @param isDeferred the constraint is initially deferred
	 * @return SQL command to create foreign keys
	 */
	public String sqlObject_createForeignKey (String vendorName, String catalogName, String schemaName, String keyName, String localTable, ArrayList<String> localColumns, String foreignTable, ArrayList<String> foreignColumns, String matchType, String onDelete, String onUpdate, boolean isDeferrable, boolean isDeferred) {

		// translate key name
		keyName = normalizeIdentifier(vendorName, keyName);

		return m_interfaces.get(getDBVendorID(vendorName)).sqlObject_createForeignKey(catalogName, schemaName, keyName, localTable, localColumns, foreignTable, foreignColumns, matchType, onDelete, onUpdate, isDeferrable, isDeferred);
	}

	/**
	 * gets the database specific SQL command to create check constraints
	 * @param sourceVendorName the source database vendor
	 * @param targetVendorName the target database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param constraintName the constraint to use
	 * @param expressions array of expressions to check
	 * @param isDeferrable the constraint is deferrable
	 * @param isDeferred the constraint is initially deferred
	 * @return SQL command to create check constraint
	 */
	public String sqlObject_createCheck (String sourceVendorName, String targetVendorName, String catalogName, String schemaName, String tableName, String constraintName, ArrayList<String> expressions, boolean isDeferrable, boolean isDeferred) {

		// translate constraint name
		constraintName = normalizeIdentifier(targetVendorName, constraintName);

		// translate expressions to target
		ArrayList<String> translatedExpressions = new ArrayList<String> ();
		for (int i = 0; i < expressions.size(); i++) {
			translatedExpressions.add(translateExpression(sourceVendorName, targetVendorName, expressions.get(i)));
		}


		return m_interfaces.get(getDBVendorID(targetVendorName)).sqlObject_createCheck(catalogName, schemaName, tableName, constraintName, translatedExpressions, isDeferrable, isDeferred);
	}

	/**
	 * gets the database specific SQL command to create unique constraints
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param constraintName the constraint to use
	 * @param columns array of columns to be unique
	 * @param isDeferrable the constraint is deferrable
	 * @param isDeferred the constraint is initially deferred
	 * @return SQL command to create unique constraint
	 */
	public String sqlObject_createUnique (String vendorName, String catalogName, String schemaName, String tableName, String constraintName, ArrayList<String> columns, boolean isDeferrable, boolean isDeferred) {

		// translate constraint name
		constraintName = normalizeIdentifier(vendorName, constraintName);

		return m_interfaces.get(getDBVendorID(vendorName)).sqlObject_createUnique(catalogName, schemaName, tableName, constraintName, columns, isDeferrable, isDeferred);
	}

	/**
	 * gets the database specific SQL command to drop constraints
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param constraintName the constraint to use
	 * @param tableName table from which to drop the constraint
	 * @return SQL command to drop constraint
	 */
	public String sqlObject_dropConstraint (String vendorName, String catalogName, String schemaName, String constraintName, String tableName) {
		return m_interfaces.get(getDBVendorID(vendorName)).sqlObject_dropConstraint(catalogName, schemaName, constraintName, tableName);
	}

	/**
	 * gets the database specific SQL command to create indexes
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName table on which to create the index
	 * @param isUnique index is unique
	 * @param indexName the index to use
	 * @param columnNames array of columns to index
	 * @param directions array of ascending or descending directions
	 * @param nullTreatments array of nulls first or last sorting
	 * @return SQL command to create index
	 */
	public String sqlObject_createIndex (String vendorName, String catalogName, String schemaName, String tableName, boolean isUnique, String indexName, ArrayList<String> columnNames, ArrayList<String> directions, ArrayList<String> nullTreatments) {

		// translate index name
		indexName = normalizeIdentifier(vendorName, indexName);

		return m_interfaces.get(getDBVendorID(vendorName)).sqlObject_createIndex(catalogName, schemaName, tableName, isUnique, indexName, columnNames, directions, nullTreatments);
	}

	/**
	 * gets the database specific SQL command to drop indexes
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param indexName the index to use
	 * @return SQL command to drop index
	 */
	public String sqlObject_dropIndex (String vendorName, String catalogName, String schemaName, String indexName) {
		return m_interfaces.get(getDBVendorID(vendorName)).sqlObject_dropIndex(catalogName, schemaName, indexName);
	}

	/**
	 * gets the database specific SQL command to create columns
	 * @param sourceVendorName the source database vendor
	 * @param targetVendorName the target database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param columnName the column to use
	 * @param dataType data type of column
	 * @param size size of column
	 * @param scale scale of column
	 * @param isNullable column is nullable
	 * @param defaultValue default value for the column
	 * @return SQL command to create columns
	 */
	public String sqlObjectDetail_createColumn (String sourceVendorName, String targetVendorName, String catalogName, String schemaName, String tableName, String columnName, String dataType, int size, int scale, boolean isNullable, String defaultValue) {

		// translate column name
		String translatedColumnName = normalizeColumnName(targetVendorName, columnName);

		// translate data type
		String translatedDataType = translateDataType(sourceVendorName, targetVendorName, dataType, size, scale);

		// translate default value
		String translatedDefaultValue = translateExpression(sourceVendorName, targetVendorName, defaultValue);
		if (! isNullable) {
			if (defaultValue==null || defaultValue.length()==0 || defaultValue.equalsIgnoreCase("NULL")) {
				// if no default value (or NULL value) is defined for a NOT NULL column,
				// we need to use a sensible placeholder for rows already existing in the table
				translatedDefaultValue = normalizeColumnValue(targetVendorName, getDataTypeID(sourceVendorName, dataType));
			}
		}

		// get SQL command
		return m_interfaces.get(getDBVendorID(targetVendorName)).sqlObjectDetail_createColumn(catalogName, schemaName, tableName, translatedColumnName, translatedDataType, isNullable, translatedDefaultValue);
	}

	/**
	 * gets the database specific SQL command to drop columns
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param columnName the column to use
	 * @return SQL command to drop column
	 */
	public String sqlObjectDetail_dropColumn (String vendorName, String catalogName, String schemaName, String tableName, String columnName) {
		return m_interfaces.get(getDBVendorID(vendorName)).sqlObjectDetail_dropColumn(catalogName, schemaName, tableName, columnName);
	}

	/**
	 * gets the database specific SQL command to set default column values
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param columnName the column to use
	 * @param defaultValue default value for the column
	 * @return SQL command to set default column value
	 */
	public String sqlObjectDetail_setColumnDefault (String vendorName, String catalogName, String schemaName, String tableName, String columnName, String defaultValue) {

		// normalize column name
		columnName = normalizeColumnName (vendorName, columnName);

		// normalize default value
		if (defaultValue==null)
			defaultValue="NULL";

		// get SQL command
		return m_interfaces.get(getDBVendorID(vendorName)).sqlObjectDetail_setColumnDefault(catalogName, schemaName, tableName, columnName, defaultValue);
	}

	/**
	 * gets the database specific SQL command to drop default column values
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param columnName the column to use
	 * @return SQL command to drop default column value
	 */
	public String sqlObjectDetail_dropColumnDefault (String vendorName, String catalogName, String schemaName, String tableName, String columnName) {

		// normalize column name
		columnName = normalizeColumnName (vendorName, columnName);

		// get SQL command
		return m_interfaces.get(getDBVendorID(vendorName)).sqlObjectDetail_dropColumnDefault(catalogName, schemaName, tableName, columnName);
	}

	/**
	 * gets the database specific SQL command to make columns nullable
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param columnName the column to use
	 * @return SQL command to make column nullable
	 */
	public String sqlObjectDetail_setColumnNullable (String vendorName, String catalogName, String schemaName, String tableName, String columnName) {

		// normalize column name
		columnName = normalizeColumnName (vendorName, columnName);

		// get SQL command
		return m_interfaces.get(getDBVendorID(vendorName)).sqlObjectDetail_setColumnNullable(catalogName, schemaName, tableName, columnName);
	}

	/**
	 * gets the database specific SQL command to make columns not nullable
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param columnName the column to use
	 * @return SQL command to make column not nullable
	 */
	public String sqlObjectDetail_dropColumnNullable (String vendorName, String catalogName, String schemaName, String tableName, String columnName) {

		// normalize column name
		columnName = normalizeColumnName (vendorName, columnName);

		// get SQL command
		return m_interfaces.get(getDBVendorID(vendorName)).sqlObjectDetail_dropColumnNullable(catalogName, schemaName, tableName, columnName);
	}

	/**
	 * gets the database specific SQL command to prepare columns for being not nullable
	 * <p>
	 * When the column of an existing table is set to be not nullable, care must be taken
	 * that no null values pre-exist
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param columnName the column to use
	 * @param dataType data type of column
	 * @param defaultValue default value of column
	 * @return SQL command to prepare column for being not nullable
	 */
	public String sqlObjectDetail_prepareColumnNotNullable (String vendorName, String catalogName, String schemaName, String tableName, String columnName, String dataType, String defaultValue) {

		// normalize column name
		columnName = normalizeColumnName (vendorName, columnName);

		// normalize default value
		if (defaultValue==null || defaultValue.length()==0 || defaultValue.toUpperCase().startsWith("NULL"))
			defaultValue = normalizeColumnValue(vendorName, getDataTypeID(vendorName, dataType));

		// get SQL command
		return m_interfaces.get(getDBVendorID(vendorName)).sqlObjectDetail_prepareColumnNotNullable(catalogName, schemaName, tableName, columnName, dataType, defaultValue);
	}

	/**
	 * gets the database specific SQL command to modify column data types
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param columnName the column to use
	 * @param dataType fully qualified data type of column
	 * @return SQL command to modify column data type
	 */
	public String sqlObjectDetail_modifyColumnType (String vendorName, String catalogName, String schemaName, String tableName, String columnName, String dataType) {

		// normalize column name
		columnName = normalizeColumnName (vendorName, columnName);

		// get SQL command
		return m_interfaces.get(getDBVendorID(vendorName)).sqlObjectDetail_modifyColumnType(catalogName, schemaName, tableName, columnName, dataType);
	}

	/**
	 * gets the database specific SQL command to rename columns
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param columnName the column to use
	 * @param newName the new column name
	 * @return SQL command to rename column
	 */
	public String sqlObjectDetail_renameColumn (String vendorName, String catalogName, String schemaName, String tableName, String columnName, String newName) {

		// normalize column names
		columnName = normalizeColumnName (vendorName, columnName);
		newName = normalizeColumnName (vendorName, newName);

		// get SQL command
		return m_interfaces.get(getDBVendorID(vendorName)).sqlObjectDetail_renameColumn(catalogName, schemaName, tableName, columnName, newName);
	}

	/**
	 * gets the database specific SQL command to create temporary columns
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param dataType fully qualified type of column
	 * @return SQL command to create temporary column
	 */
	public String sqlObjectDetail_createTemporaryColumn (String vendorName, String catalogName, String schemaName, String tableName, String dataType) {
		return m_interfaces.get(getDBVendorID(vendorName)).sqlObjectDetail_createTemporaryColumn(catalogName, schemaName, tableName, getTemporaryColumnName(), dataType);
	}

	/**
	 * gets the database specific SQL command to drop temporary columns
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @return SQL command to drop temporary column
	 */
	public String sqlObjectDetail_dropTemporaryColumn (String vendorName, String catalogName, String schemaName, String tableName) {
		return m_interfaces.get(getDBVendorID(vendorName)).sqlObjectDetail_dropTemporaryColumn(catalogName, schemaName, tableName, getTemporaryColumnName());
	}

	/**
	 * gets the database specific SQL command to save data in temporary columns
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param columnName name of the original column
	 * @param dataType type of column
	 * @return SQL command save data in temporary column
	 */
	public String sqlObjectDetail_saveTemporaryColumn (String vendorName, String catalogName, String schemaName, String tableName, String columnName, String dataType) {
		return m_interfaces.get(getDBVendorID(vendorName)).sqlObjectDetail_saveTemporaryColumn(catalogName, schemaName, tableName, getTemporaryColumnName(), columnName, dataType);
	}

	/**
	 * gets the database specific SQL command to restore data from temporary columns
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param columnName name of the original column
	 * @return SQL command restore data from temporary column
	 */
	public String sqlObjectDetail_restoreTemporaryColumn (String vendorName, String catalogName, String schemaName, String tableName, String columnName) {

		// normalize column name
		columnName = normalizeColumnName (vendorName, columnName);

		// get SQL command
		return m_interfaces.get(getDBVendorID(vendorName)).sqlObjectDetail_restoreTemporaryColumn(vendorName, catalogName, schemaName, tableName, getTemporaryColumnName(), columnName);
	}

	/**
	 * gets the database specific SQL command to erase data from columns
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param columnName the column to use
	 * @return SQL command to erase data from column
	 */
	public String sqlObjectDetail_eraseColumn (String vendorName, String catalogName, String schemaName, String tableName, String columnName) {

		// normalize column name
		columnName = normalizeColumnName (vendorName, columnName);

		// get SQL command
		return m_interfaces.get(getDBVendorID(vendorName)).sqlObjectDetail_eraseColumn(catalogName, schemaName, tableName, columnName);
	}

	/**
	 * gets the database specific SQL command to select records from a table
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param tableAlias alias to use for the table
	 * @param columnNames array of columns to query (null if all columns should be returned)
	 * @param aliasNames array of aliases to use as column names in the query
	 * @param joinTypes array of join types
	 * @param joinTables array of tables to join
	 * @param joinAliases aliases to use for joined tables
	 * @param joinConditions array of join conditions
	 * @param conditions array of where clauses (to be ANDed)
	 * @param sortColumns array of columns by which to sort the query
	 * @param isDistinct only return distinct rows
	 * @return SQL command to select records from a table
	 */
	private String sql_select (String vendorName, String catalogName, String schemaName, String tableName, String tableAlias, ArrayList<String> columnNames, ArrayList<String> aliasNames, ArrayList<String> joinTypes, ArrayList<String> joinTables, ArrayList<String> joinAliases, ArrayList<String> joinConditions, ArrayList<String> conditions, ArrayList<String> sortColumns, boolean isDistinct) {

		// table alias - defaults to t
		if (tableAlias == null)
			tableAlias = "t";

		// join aliases - defaults to t0, t1, t2 ...
		if (joinTables != null) {
			if (joinAliases == null) {
				joinAliases = new ArrayList<String> ();
				for (int i=0; i < joinTables.size(); i++)
					joinAliases.add(new StringBuffer("t").append(i).toString());
			}
		}

		// translate column aliases
		ArrayList<String> translatedAliasNames = null;
		if (columnNames != null && (aliasNames == null || aliasNames.size()!= columnNames.size())) {
			// if not already defined, alias names should be same as (un-normalized) column names
			translatedAliasNames = new ArrayList<String> ();
			for (String columnName : columnNames) {
				translatedAliasNames.add(columnName);
			}
		} else if (aliasNames != null){
			// otherwise just keep the list of defined aliases
			translatedAliasNames = new ArrayList<String> ();
			for (String aliasName : aliasNames) {
				translatedAliasNames.add(aliasName);
			}
		}

		// normalize column names
		ArrayList<String> translatedColumnNames = null;
		if (columnNames != null) {
			translatedColumnNames = new ArrayList<String> ();
			for (String columnName : columnNames) {
				translatedColumnNames.add(normalizeColumnName(vendorName, columnName));
			}
		}

		// aliases are not required if they are same as column name
		if (translatedColumnNames!=null && translatedAliasNames!=null) {
			for (int i=0; i < translatedColumnNames.size(); i++) {
				if (translatedColumnNames.get(i).equals(translatedAliasNames.get(i)))
					translatedAliasNames.set(i, null);
			}
		}

		// safety net: * means all columns
		if (columnNames != null) {
			if (columnNames.get(0).equalsIgnoreCase("*")) {
				translatedColumnNames = null;
				translatedAliasNames = null;
			}
		}

		return m_interfaces.get(getDBVendorID(vendorName)).sql_select(catalogName, schemaName, tableName, tableAlias, translatedColumnNames, translatedAliasNames, joinTypes, joinTables, joinAliases, joinConditions, conditions, sortColumns, isDistinct);
	}


	/**
	 * gets the database specific SQL command to select records from a table
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param tableAlias alias to use for the table
	 * @param columnNames array of columns to query (null if all columns should be returned)
	 * @param aliasNames array of aliases to use as column names in the query
	 * @param conditions array of where clauses (to be ANDed)
	 * @param sortColumns array of columns by which to sort the query
	 * @param isDistinct only return distinct rows
	 * @return SQL command to select records from a table
	 */
	private String sql_select (String vendorName, String catalogName, String schemaName, String tableName, String tableAlias, ArrayList<String> columnNames, ArrayList<String> aliasNames, ArrayList<String> conditions, ArrayList<String> sortColumns, boolean isDistinct) {
		return sql_select(vendorName, catalogName, schemaName, tableName, tableAlias, columnNames, aliasNames, null, null, null, null, conditions, sortColumns, isDistinct);
	}

	/**
	 * gets the database specific SQL command to select records from a table
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use (aliased as t)
	 * @param condition WHERE clause
	 * @return SQL command to select records from a table
	 */
	public String sql_select (String vendorName, String catalogName, String schemaName, String tableName, String condition) {

		ArrayList<String> columnNames = null;
		ArrayList<String> aliasNames = null;
		ArrayList<String> conditions = null;
		ArrayList<String> sortColumns = null;

		if (condition!=null && condition.length()>0) {
			conditions = new ArrayList<String> ();
			conditions.add(condition);
		}

		return sql_select(vendorName, catalogName, schemaName, tableName, null, columnNames, aliasNames, conditions, sortColumns, false);
	}

	/**
	 * gets the database specific SQL command to select records from a table
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use (aliased as t)
	 * @return SQL command to select records from a table
	 */
	public String sql_select (String vendorName, String catalogName, String schemaName, String tableName) {

		ArrayList<String> columnNames = null;
		ArrayList<String> aliasNames = null;
		ArrayList<String> conditions = null;
		ArrayList<String> sortColumns = null;

		return sql_select(vendorName, catalogName, schemaName, tableName, null, columnNames, aliasNames, conditions, sortColumns, false);
	}

	/**
	 * gets the database specific SQL command to select records from a table as prepared statement
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use (aliased as t)
	 * @param whereColumnNames array of column names to use in WHERE clause
	 * @return SQL command to select records from a table as prepared statement
	 */
	public String sql_selectPreparedStatement (String vendorName, String catalogName, String schemaName, String tableName, ArrayList<String> whereColumnNames) {

		ArrayList<String> columnNames = null;
		ArrayList<String> aliasNames = null;
		ArrayList<String> conditions = null;
		ArrayList<String> sortColumns = null;

		if (whereColumnNames != null && whereColumnNames.size()>0) {
			conditions = new ArrayList<String> ();
			for (String whereColumnName : whereColumnNames) {
				conditions.add(new StringBuffer(normalizeColumnName(vendorName, whereColumnName)).append(" = ?").toString());
			}
		}

		return sql_select(vendorName, catalogName, schemaName, tableName, null, columnNames, aliasNames, conditions, sortColumns, false);
	}

	/**
	 * gets the database specific SQL command to update records in a table
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param tableAlias alias to use for the table
	 * @param columnNames array of column names to set
	 * @param values array of values to set
	 * @param conditions array of where clauses (to be ANDed)
	 * @return SQL command to update records in a table
	 */
	public String sql_update (String vendorName, String catalogName, String schemaName, String tableName, String tableAlias, ArrayList<String> columnNames, ArrayList<String> values, ArrayList<String> conditions) {

		// table Alias
		if (tableAlias==null || tableAlias.length()==0)
			tableAlias = "t";

		// normalize column names
		ArrayList<String> translatedColumnNames = null;
		if (columnNames != null) {
			translatedColumnNames = new ArrayList<String> ();
			for (String columnName : columnNames) {
				translatedColumnNames.add(normalizeColumnName(vendorName, columnName));
			}
		}

		// if no values exist, use ? for creating prepared statement
		if (values==null) {
			values = new ArrayList<String> ();
			for (int i=0; i<columnNames.size(); i++) {
				values.add("?");
			}
		}

		return m_interfaces.get(getDBVendorID(vendorName)).sql_update(catalogName, schemaName, tableName, tableAlias, translatedColumnNames, values, conditions);
	}

	/**
	 * gets the database specific SQL command to update records in a table as prepared statement
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param columnNames array of column names to set
	 * @param whereColumnNames array of column names to use in WHERE clause
	 * @return SQL command to update records in a table as prepared statement
	 */
	public String sql_updatePreparedStatement (String vendorName, String catalogName, String schemaName, String tableName, ArrayList<String> columnNames, ArrayList<String> whereColumnNames) {

		// create condition list
		ArrayList<String> conditions = null;
		if (whereColumnNames != null && whereColumnNames.size()>0) {
			conditions = new ArrayList<String> ();
			for (int i = 0; i < whereColumnNames.size(); i++) {
				String colName = normalizeColumnName(vendorName, whereColumnNames.get(i));
				conditions.add(new StringBuffer(colName).append(" = ? ").toString());
			}
		}

		return sql_update(vendorName, catalogName, schemaName, tableName, null, columnNames, null, conditions);
	}

	/**
	 * gets the database specific SQL command to delete records fulfilling condition and of a specified age
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param tableAlias alias to use for the table
	 * @param conditions array of WHERE clauses
	 * @param daysOld minimum age of records to delete
	 * @return SQL command to delete records fulfilling condition and of a specified age
	 */
	public String sql_delete (String vendorName, String catalogName, String schemaName, String tableName, String tableAlias, ArrayList<String> conditions, Integer daysOld) {

		// table Alias
		if (tableAlias==null || tableAlias.length()==0)
			tableAlias = "t";

		// 0 days old means everything updated before tomorrow
		// 1 day old means everything updated before today, etc.
		// since daysOld is subtracted from sysdate, we decrement it by 1
		if (daysOld!=null)
			daysOld = Integer.valueOf(daysOld.intValue() - 1);

		// get SQL command
		return m_interfaces.get(getDBVendorID(vendorName)).sql_delete(catalogName, schemaName, tableName, tableAlias, conditions, daysOld);
	}

	/**
	 * gets the database specific SQL command to delete records fulfilling condition and of a specified age
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param condition WHERE clause
	 * @param daysOld minimum age of records to delete
	 * @return SQL command to delete records fulfilling condition and of a specified age
	 */
	public String sql_delete (String vendorName, String catalogName, String schemaName, String tableName, String condition, Integer daysOld) {
		ArrayList<String> conditions = new ArrayList<String> ();
		if (condition != null && condition.length()>0)
			conditions.add(condition);
		return sql_delete(vendorName, catalogName, schemaName, tableName, null, conditions, daysOld);
	}

	/**
	 * gets the database specific SQL command to delete records from a table
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @return SQL command to delete records from a table
	 */
	public String sql_delete (String vendorName, String catalogName, String schemaName, String tableName) {
		return sql_delete (vendorName, catalogName, schemaName, tableName, (String) null, null);
	}

	/**
	 * gets the database specific SQL command to delete records fulfilling a condition
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param condition WHERE clause
	 * @return SQL command to delete records fulfilling a condition
	 */
	public String sql_deleteByCondition (String vendorName, String catalogName, String schemaName, String tableName, String condition) {
		return sql_delete (vendorName, catalogName, schemaName, tableName, condition, null);
	}

	/**
	 * gets the database specific SQL command to delete records of a specified age
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param daysOld minimum age of records to delete
	 * @return SQL command to delete records of a specified age
	 */
	public String sql_deleteByAge (String vendorName, String catalogName, String schemaName, String tableName, Integer daysOld) {
		return sql_delete (vendorName, catalogName, schemaName, tableName, (String) null, daysOld);
	}

	/**
	 * gets the database specific SQL command to delete records of a condition and specified age
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param condition WHERE clause
	 * @param daysOld minimum age of records to delete
	 * @return SQL command to delete records of a specified age
	 */
	public String sql_deleteByConditionAndAge (String vendorName, String catalogName, String schemaName, String tableName, String condition , Integer daysOld) {
		return sql_delete (vendorName, catalogName, schemaName, tableName, condition, daysOld);
	}

	/**
	 * gets the database specific SQL command to insert records
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param columnNames array of column names to use
	 * @param columnValues array of values to use
	 * @return SQL command to insert records
	 */
	public String sql_insert (String vendorName, String catalogName, String schemaName, String tableName, ArrayList<String> columnNames, ArrayList<String> columnValues) {

		// normalize column names
		ArrayList<String> translatedColumnNames = null;
		if (columnNames != null) {
			translatedColumnNames = new ArrayList<String> ();
			for (String columnName : columnNames) {
				translatedColumnNames.add(normalizeColumnName(vendorName, columnName));
			}
		}

		// if no values exist, use ? for creating prepared statement
		if (columnValues==null) {
			columnValues = new ArrayList<String> ();
			for (int i=0; i<columnNames.size(); i++) {
				columnValues.add("?");
			}
		}

		// get SQL command
		return m_interfaces.get(getDBVendorID(vendorName)).sql_insert(catalogName, schemaName, tableName, translatedColumnNames, columnValues);
	}

	/**
	 * gets the database specific SQL command to insert records as prepared statement
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param columnNames array of column names to use
	 * @return SQL command to insert records as prepared statement
	 */
	public String sql_insertPreparedStatement (String vendorName, String catalogName, String schemaName, String tableName, ArrayList<String> columnNames) {
		return sql_insert(vendorName, catalogName, schemaName, tableName, columnNames, null);
	}

	/**
	 * gets the database specific SQL command to insert records from another table
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param columnNames array of column names to use
	 * @param columnValues array of values to use
	 * @param sourceTableName table from which to fetch records (aliased as t)
	 * @param joinTypes array of join types
	 * @param joinTables array of tables to join (aliased as t0, t1, t2 ...)
	 * @param joinConditions array of join conditions
	 * @param whereClause optional SQL WHERE clause
	 * @return SQL command to insert records from another table
	 */
	public String sql_insertFromTable (String vendorName, String catalogName, String schemaName, String tableName,
			ArrayList<String> columnNames, ArrayList<String> columnValues,
			String sourceTableName,
			ArrayList<String> joinTypes, ArrayList<String> joinTables, ArrayList<String> joinConditions,
			String whereClause) {

		// normalize column names
		ArrayList<String> translatedColumnNames = null;
		if (columnNames != null) {
			translatedColumnNames = new ArrayList<String> ();
			for (String columnName : columnNames) {
				translatedColumnNames.add(normalizeColumnName(vendorName, columnName));
			}
		}

		// get SQL command
		return m_interfaces.get(getDBVendorID(vendorName)).sql_insertFromTable(catalogName, schemaName, tableName, translatedColumnNames, columnValues, sourceTableName, joinTypes, joinTables, joinConditions, whereClause);
	}

	/**
	 * gets the database specific SQL command to find system clients
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return SQL command returning
	 * AD_CLIENT_ID, NAME
	 */
	@SuppressWarnings("static-access")
	public String sqlAD_getSystemClients (String vendorName, String catalogName, String schemaName) {

		// table name
		String tableName = "AD_Client";

		// column names
		ArrayList<String> columnNames = new ArrayList<String> ();
		columnNames.add("AD_Client_ID");
		columnNames.add("Name");

		// aliases
		ArrayList<String> aliasNames = new ArrayList<String> ();
		aliasNames.add("AD_CLIENT_ID");
		aliasNames.add("NAME");

		// conditions
		ArrayList<String> conditions = new ArrayList<String> ();
		conditions.add(new StringBuffer("AD_Client_ID < ").append(s_parameters.MINUSERLEVELID).toString());

		// sort order
		ArrayList<String> sortColumns = new ArrayList<String> ();
		sortColumns.add("1");

		// get SQL command
		return sql_select(vendorName, catalogName, schemaName, tableName, null, columnNames, aliasNames, conditions, sortColumns, false);
	}

	/**
	 * gets the database specific SQL command to find system languages
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return SQL command returning
	 * AD_LANGUAGE
	 */
	public String sqlAD_getSystemLanguages (String vendorName, String catalogName, String schemaName) {

		// table name
		String tableName = "AD_Language";

		// column names
		ArrayList<String> columnNames = new ArrayList<String> ();
		columnNames.add("AD_Language");

		// aliases
		ArrayList<String> aliasNames = new ArrayList<String> ();
		aliasNames.add("AD_LANGUAGE");

		// conditions
		ArrayList<String> conditions = new ArrayList<String> ();
		conditions.add("IsSystemLanguage = 'Y'");

		// sort order
		ArrayList<String> sortColumns = new ArrayList<String> ();
		sortColumns.add("1");

		// get SQL command
		return sql_select(vendorName, catalogName, schemaName, tableName, null, columnNames, aliasNames, conditions, sortColumns, false);
	}

	/**
	 * gets the database specific SQL command to find custom entity Types
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return SQL command returning
	 * ENTITYTYPE
	 */
	public String sqlAD_getCustomEntityTypes (String vendorName, String catalogName, String schemaName) {

		// table name
		String tableName = "AD_EntityType";

		// column names
		ArrayList<String> columnNames = new ArrayList<String> ();
		columnNames.add("EntityType");

		// aliases
		ArrayList<String> aliasNames = new ArrayList<String> ();
		aliasNames.add("ENTITYTYPE");

		// conditions
		ArrayList<String> conditions = new ArrayList<String> ();
		conditions.add("EntityType NOT IN ('C', 'D')");

		// sort order
		ArrayList<String> sortColumns = new ArrayList<String> ();
		sortColumns.add("1");

		// get SQL command
		return sql_select(vendorName, catalogName, schemaName, tableName, null, columnNames, aliasNames, conditions, sortColumns, false);
	}

	/**
	 * gets the database specific SQL command to find custom entity Prefixes
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return SQL command returning
	 * ENTITYTYPE
	 */
	public String sqlAD_getCustomEntityPrefixes (String vendorName, String catalogName, String schemaName) {

		// table name
		String tableName = "AD_EntityType";

		// column names
		ArrayList<String> columnNames = new ArrayList<String> ();
		columnNames.add("EntityType");

		// aliases
		ArrayList<String> aliasNames = new ArrayList<String> ();
		aliasNames.add("ENTITYTYPE");

		// conditions
		ArrayList<String> conditions = new ArrayList<String> ();
		conditions.add("EntityType NOT IN ('C','D','U','A')");

		// sort order
		ArrayList<String> sortColumns = new ArrayList<String> ();
		sortColumns.add("1");

		// get SQL command
		return sql_select(vendorName, catalogName, schemaName, tableName, null, columnNames, aliasNames, conditions, sortColumns, false);
	}

	/**
	 * gets the database specific SQL command to find a table's entity type
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @return SQL command returning
	 * TABLE_NAME, ENTITY_TYPE
	 */
	public String sqlAD_getTableEntityType (String vendorName, String catalogName, String schemaName, String tableName) {

		// table name
		String searchTableName = "AD_Table";

		// column names
		ArrayList<String> columnNames = new ArrayList<String> ();
		columnNames.add("TableName");
		columnNames.add("EntityType");

		// aliases
		ArrayList<String> aliasNames = new ArrayList<String> ();
		aliasNames.add("TABLE_NAME");
		aliasNames.add("ENTITY_TYPE");

		// conditions
		ArrayList<String> conditions = new ArrayList<String> ();
		conditions.add(new StringBuffer("UPPER(t.name) = '").append(tableName.toUpperCase()).append("'").toString());

		// sort order
		ArrayList<String> sortColumns = new ArrayList<String> ();
		sortColumns.add("1");
		sortColumns.add("2");

		// get SQL command
		return sql_select(vendorName, catalogName, schemaName, searchTableName, null, columnNames, aliasNames, conditions, sortColumns, false);
	}

	/**
	 * gets the database specific SQL command to find a columns's entity type
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param columnName the column to use
	 * @return SQL command returning
	 * TABLE_NAME, COLUMN_NAME, ENTITY_TYPE
	 */
	public String sqlAD_getTableColumnEntityType (String vendorName, String catalogName, String schemaName, String tableName, String columnName) {

		// table name
		String searchTableName = "AD_Column";

		// column names
		ArrayList<String> columnNames = new ArrayList<String> ();
		columnNames.add("t0.TableName");
		columnNames.add("t.ColumnName");
		columnNames.add("t.EntityType");

		// aliases
		ArrayList<String> aliasNames = new ArrayList<String> ();
		aliasNames.add("TABLE_NAME");
		aliasNames.add("COLUMN_NAME");
		aliasNames.add("ENTITY_TYPE");

		//joins
		ArrayList<String> joinTypes = new ArrayList<String> ();
		joinTypes.add("INNER JOIN");
		ArrayList<String> joinTables = new ArrayList<String> ();
		joinTables.add("AD_Table");
		ArrayList<String> joinConditions = new ArrayList<String> ();
		joinConditions.add("t.AD_Table_ID = t0.AD_Table_ID");

		// conditions
		ArrayList<String> conditions = new ArrayList<String> ();
		conditions.add(new StringBuffer("UPPER(t.ColumnName) = '").append(columnName.toUpperCase()).append("'").toString());
		conditions.add(new StringBuffer("UPPER(t0.TableName) = '").append(tableName.toUpperCase()).append("'").toString());

		// sort order
		ArrayList<String> sortColumns = new ArrayList<String> ();
		sortColumns.add("1");
		sortColumns.add("2");
		sortColumns.add("3");

		// get SQL command
		return sql_select(vendorName, catalogName, schemaName, searchTableName, null, columnNames, aliasNames, joinTypes, joinTables, null, joinConditions, conditions, sortColumns, false);
	}

	/**
	 * gets the database specific SQL command to find a column's AD_Element_ID
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param columnName the column to use
	 * @return SQL command returning
	 * TABLE_NAME, COLUMN_NAME, COLUMN_ELEMENT
	 */
	public String sqlAD_getTableColumnElement (String vendorName, String catalogName, String schemaName, String tableName, String columnName) {

		// table name
		String searchTableName = "AD_Column";

		// column names
		ArrayList<String> columnNames = new ArrayList<String> ();
		columnNames.add("t0.TableName");
		columnNames.add("t.ColumnName");
		columnNames.add("t.AD_Element_ID");

		// aliases
		ArrayList<String> aliasNames = new ArrayList<String> ();
		aliasNames.add("TABLE_NAME");
		aliasNames.add("COLUMN_NAME");
		aliasNames.add("COLUMN_ELEMENT");

		//joins
		ArrayList<String> joinTypes = new ArrayList<String> ();
		joinTypes.add("INNER JOIN");
		ArrayList<String> joinTables = new ArrayList<String> ();
		joinTables.add("AD_Table");
		ArrayList<String> joinConditions = new ArrayList<String> ();
		joinConditions.add("t.AD_Table_ID = t0.AD_Table_ID");

		// conditions
		ArrayList<String> conditions = new ArrayList<String> ();
		conditions.add(new StringBuffer("UPPER(t.ColumnName) = '").append(columnName.toUpperCase()).append("'").toString());
		conditions.add(new StringBuffer("UPPER(t0.TableName) = '").append(tableName.toUpperCase()).append("'").toString());

		// sort order
		ArrayList<String> sortColumns = new ArrayList<String> ();
		sortColumns.add("1");
		sortColumns.add("2");
		sortColumns.add("3");

		// get SQL command
		return sql_select(vendorName, catalogName, schemaName, searchTableName, null, columnNames, aliasNames, joinTypes, joinTables, null, joinConditions, conditions, sortColumns, false);
	}

	/**
	 * gets the database specific SQL command to find view column entity types
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param viewName the view to use
	 * @return SQL command returning
	 * VIEW_NAME, COLUMN_NAME, ENTITY_TYPE
	 */
	public String sqlAD_getViewColumnEntityType (String vendorName, String catalogName, String schemaName, String viewName) {

		// table name
		String searchTableName = "AD_Column";

		// column names
		ArrayList<String> columnNames = new ArrayList<String> ();
		columnNames.add("t0.TableName");
		columnNames.add("t.ColumnName");
		columnNames.add("t.EntityType");

		// aliases
		ArrayList<String> aliasNames = new ArrayList<String> ();
		aliasNames.add("VIEW_NAME");
		aliasNames.add("COLUMN_NAME");
		aliasNames.add("ENTITY_TYPE");

		//joins
		ArrayList<String> joinTypes = new ArrayList<String> ();
		joinTypes.add("INNER JOIN");
		ArrayList<String> joinTables = new ArrayList<String> ();
		joinTables.add("AD_Table");
		ArrayList<String> joinConditions = new ArrayList<String> ();
		joinConditions.add("t.AD_Table_ID = t0.AD_Table_ID");

		// conditions
		ArrayList<String> conditions = new ArrayList<String> ();
		conditions.add(new StringBuffer("UPPER(t0.TableName) = '").append(viewName.toUpperCase()).append("'").toString());

		// sort order
		ArrayList<String> sortColumns = new ArrayList<String> ();
		sortColumns.add("1");
		sortColumns.add("2");
		sortColumns.add("3");

		// get SQL command
		return sql_select(vendorName, catalogName, schemaName, searchTableName, null, columnNames, aliasNames, joinTypes, joinTables, null, joinConditions, conditions, sortColumns, false);
	}

	/**
	 * gets the database specific SQL command to find sequence counters defined in the application dictionary
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return SQL command returning
	 * SEQ_NAME, SEQ_SYS, SEQ_USER
	 */
	public String sqlAD_getSequences (String vendorName, String catalogName, String schemaName) {

		// table name
		String tableName = "AD_Sequence";

		// column names
		ArrayList<String> columnNames = new ArrayList<String> ();
		columnNames.add("Name");
		columnNames.add("CurrentNextSys");
		columnNames.add("CurrentNext");

		// aliases
		ArrayList<String> aliasNames = new ArrayList<String> ();
		aliasNames.add("SEQ_NAME");
		aliasNames.add("SEQ_SYS");
		aliasNames.add("SEQ_USER");

		// conditions
		ArrayList<String> conditions = new ArrayList<String> ();
		conditions.add("IsTableID = 'Y'");

		// sort order
		ArrayList<String> sortColumns = new ArrayList<String> ();
		sortColumns.add("1");

		// get SQL command
		return sql_select(vendorName, catalogName, schemaName, tableName, null, columnNames, aliasNames, conditions, sortColumns, false);
	}

	/**
	 * gets the database specific SQL command to find the highest system sequence of a table
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param columnName the key column to use
	 * @return SQL command returning
	 * MAX_SEQ
	 */
	@SuppressWarnings("static-access")
	public String sqlAD_getSequenceMaxSystem (String vendorName, String catalogName, String schemaName, String tableName, String columnName) {

		// column names
		ArrayList<String> columnNames = new ArrayList<String> ();
		columnNames.add(new StringBuffer("max(").append(normalizeColumnName(vendorName, columnName)).append(")").toString());

		// aliases
		ArrayList<String> aliasNames = new ArrayList<String> ();
		aliasNames.add("MAX_SEQ");

		// conditions
		ArrayList<String> conditions = new ArrayList<String> ();
		conditions.add(new StringBuffer().append(normalizeColumnName(vendorName, columnName)).append(" < ").append(s_parameters.MINUSERLEVELID).toString());

		// sort order
		ArrayList<String> sortColumns = new ArrayList<String> ();
		sortColumns.add("1");

		// get SQL command
		return sql_select(vendorName, catalogName, schemaName, tableName, null, columnNames, aliasNames, conditions, sortColumns, false);
	}

	/**
	 * gets the database specific SQL command to find the highest user sequence of a table
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param columnName the key column to use
	 * @return SQL command returning
	 * MAX_SEQ
	 */
	public String sqlAD_getSequenceMaxUser (String vendorName, String catalogName, String schemaName, String tableName, String columnName) {

		// column names
		ArrayList<String> columnNames = new ArrayList<String> ();
		columnNames.add(new StringBuffer("max(").append(normalizeColumnName(vendorName, columnName)).append(")").toString());

		// aliases
		ArrayList<String> aliasNames = new ArrayList<String> ();
		aliasNames.add("MAX_SEQ");

		// conditions
		ArrayList<String> conditions = null;

		// sort order
		ArrayList<String> sortColumns = new ArrayList<String> ();
		sortColumns.add("1");

		// get SQL command
		return sql_select(vendorName, catalogName, schemaName, tableName, null, columnNames, aliasNames, conditions, sortColumns, false);
	}

	/**
	 * gets the database specific SQL command to find tables which have no sequence
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return SQL command returning
	 * AD_Client_ID, TableName
	 */
	public String sqlAD_getUnsequencedTables (String vendorName, String catalogName, String schemaName) {

		// table name
		String tableName = "AD_Table";

		// column names
		ArrayList<String> columnNames = new ArrayList<String> ();
		columnNames.add("AD_Client_ID");
		columnNames.add("TableName");

		// aliases
		ArrayList<String> aliasNames = null;

		// conditions
		ArrayList<String> conditions = new ArrayList<String> ();
		conditions.add("t.IsView = 'N'");
		String subQuery =  sql_select (vendorName, catalogName, schemaName, "AD_Sequence", "t0", new ArrayList<String> (Arrays.asList("1")), null, new ArrayList<String> (Arrays.asList("t0.Name = t.TableName", "t0.IsTableID='Y'")), null, false);
		conditions.add(new StringBuffer("NOT EXISTS (").append(subQuery).append(")").toString());

		// sort order
		ArrayList<String> sortColumns = new ArrayList<String> ();
		sortColumns.add("1");
		sortColumns.add("2");

		// get SQL command
		return sql_select(vendorName, catalogName, schemaName, tableName, null, columnNames, aliasNames, conditions, sortColumns, false);
	}

	/**
	 * gets the database specific SQL command to find documents which have no sequence
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return SQL command returning
	 * TableName
	 */
	public String sqlAD_getUnsequencedDocuments (String vendorName, String catalogName, String schemaName) {

		// table name
		String tableName = "AD_Table";

		// column names
		ArrayList<String> columnNames = new ArrayList<String> ();
		columnNames.add("TableName");

		// aliases
		ArrayList<String> aliasNames = null;

		// conditions
		ArrayList<String> conditions = new ArrayList<String> ();
		conditions.add("t.IsView = 'N'");

		String subQuery = sql_select(vendorName, catalogName, schemaName, "AD_Column", "t0", new ArrayList<String> (Arrays.asList("AD_Table_ID")), null, new ArrayList<String> (Arrays.asList("t0.ColumnName LIKE 'DocumentNo' OR t0.ColumnName LIKE 'Value'")), null, false);
		conditions.add(new StringBuffer("AD_Table_ID IN (").append(subQuery).append(")").toString());

		subQuery = sql_select(vendorName, catalogName, schemaName, "AD_Sequence", "t1", new ArrayList<String> (Arrays.asList("Name")), null, new ArrayList<String> (Arrays.asList("t1.AD_Client_ID = ?")), null, false);
		conditions.add(new StringBuffer("'DocumentNo_'||t.TableName NOT IN (").append(subQuery).append(")").toString());

		// sort order
		ArrayList<String> sortColumns = new ArrayList<String> ();
		sortColumns.add("1");

		// get SQL command
		return sql_select(vendorName, catalogName, schemaName, tableName, null, columnNames, aliasNames, conditions, sortColumns, false);
	}

	/**
	 * gets the database specific SQL command to find tables which have translations
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return SQL command returning
	 * TableName
	 */
	public String sqlAD_getTranslatedTables (String vendorName, String catalogName, String schemaName) {

		// table name
		String tableName = "AD_Table";

		// column names
		ArrayList<String> columnNames = new ArrayList<String> ();
		columnNames.add("SUBSTR(TableName, 1, LENGTH(TableName)-4)");

		// aliases
		ArrayList<String> aliasNames = new ArrayList<String> ();
		aliasNames.add("TableName");

		// conditions
		ArrayList<String> conditions = new ArrayList<String> ();
		conditions.add("UPPER(TableName) LIKE '%\\_TRL'");
		conditions.add("IsActive = 'Y'");
		conditions.add("IsView = 'N'");

		// sort order
		ArrayList<String> sortColumns = new ArrayList<String> ();
		sortColumns.add("1");

		// get SQL command
		return sql_select(vendorName, catalogName, schemaName, tableName, null, columnNames, aliasNames, conditions, sortColumns, false);
	}

	/**
	 * gets the database specific SQL command to find columns which have translations
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return database-specific SQL command returning
	 * ColumnName
	 */
	public String sqlAD_getTranslatedColumns (String vendorName, String catalogName, String schemaName) {

		// table name
		String searchTableName = "AD_Column";

		// column names
		ArrayList<String> columnNames = new ArrayList<String> ();
		columnNames.add("t.ColumnName");

		// aliases
		ArrayList<String> aliasNames = null;

		//joins
		ArrayList<String> joinTypes = new ArrayList<String> ();
		joinTypes.add("INNER JOIN");
		ArrayList<String> joinTables = new ArrayList<String> ();
		joinTables.add("AD_Table");
		ArrayList<String> joinConditions = new ArrayList<String> ();
		joinConditions.add("t.AD_Table_ID = t0.AD_Table_ID");

		// conditions
		ArrayList<String> conditions = new ArrayList<String> ();
		conditions.add("t0.TableName LIKE ?");
		conditions.add("t.IsTranslated = 'Y'");
		conditions.add("t.IsActive = 'Y'");

		// sort order
		ArrayList<String> sortColumns = new ArrayList<String> ();
		sortColumns.add("1");

		// get SQL command
		return sql_select(vendorName, catalogName, schemaName, searchTableName, null, columnNames, aliasNames, joinTypes, joinTables, null, joinConditions, conditions, sortColumns, false);
	}

	/**
	 * gets the database specific SQL command to find customizations which must be re-applied
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return SQL command returning
	 * TABLENAME, RECORDID, COLUMNNAME, NEWVALUE, DISPLAYTYPE
	 */
	public String sqlAD_getCustomizationChangeLogs (String vendorName, String catalogName, String schemaName) {

		// table name
		String searchTableName = "AD_ChangeLog";

		// column names
		ArrayList<String> columnNames = new ArrayList<String> ();
		columnNames.add("t0.TableName");
		columnNames.add("t.Record_ID");
		columnNames.add("t1.ColumnName");
		columnNames.add("t.NewValue");
		columnNames.add("t2.Name");

		// aliases
		ArrayList<String> aliasNames =  new ArrayList<String> ();
		aliasNames.add("TABLENAME");
		aliasNames.add("RECORDID");
		aliasNames.add("COLUMNNAME");
		aliasNames.add("NEWVALUE");
		aliasNames.add("DISPLAYTYPE");

		//joins
		ArrayList<String> joinTypes = new ArrayList<String> ();
		ArrayList<String> joinTables = new ArrayList<String> ();
		ArrayList<String> joinConditions = new ArrayList<String> ();

		joinTypes.add("INNER JOIN");
		joinTables.add("AD_Table");
		joinConditions.add("t.AD_Table_ID = t0.AD_Table_ID");

		joinTypes.add("INNER JOIN");
		joinTables.add("AD_Column");
		joinConditions.add("t.AD_Table_ID = t1.AD_Table_ID AND t.AD_Column_ID = t1.AD_Column_ID");

		joinTypes.add("INNER JOIN");
		joinTables.add("AD_Reference");
		joinConditions.add("t1.AD_Reference_ID = t2.AD_Reference_ID");


		// conditions
		ArrayList<String> conditions = new ArrayList<String> ();
		conditions.add("t.IsCustomization = 'Y'");
		conditions.add("t.IsActive = 'Y'");

		// sort order
		ArrayList<String> sortColumns = new ArrayList<String> ();
		sortColumns.add("1");
		sortColumns.add("2");
		sortColumns.add("3");

		// get SQL command
		return sql_select(vendorName, catalogName, schemaName, searchTableName, null, columnNames, aliasNames, joinTypes, joinTables, null, joinConditions, conditions, sortColumns, false);
	}

	/**
	 * gets the database specific SQL command to find system columns which have no base element
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param customEntities array of custom entity types
	 * @return SQL command returning
	 * ColumnName, Name, Description, Help, EntityType
	 */
	public String sqlAD_getSystemColumnsWithoutElement (String vendorName, String catalogName, String schemaName, ArrayList<String> customEntities) {

		// table name
		String tableName = "AD_Column";

		// table alias
		String tableAlias = "c";

		// column names
		ArrayList<String> columnNames = new ArrayList<String> ();
		columnNames.add("ColumnName");
		columnNames.add("Name");
		columnNames.add("Description");
		columnNames.add("Help");
		columnNames.add("EntityType");

		// aliases
		ArrayList<String> aliasNames = null;

		// conditions
		ArrayList<String> conditions = new ArrayList<String> ();
		String subQuery = sql_select(vendorName, catalogName, schemaName, "AD_Element", "e", new ArrayList<String> (Arrays.asList("1")), null, new ArrayList<String> (Arrays.asList("UPPER(c.ColumnName)=UPPER(e.ColumnName)")), null, false);
		conditions.add(new StringBuffer("NOT EXISTS (").append(subQuery).append(")").toString());

		// customization-checking part of WHERE clause
		StringBuffer entityClause = new StringBuffer();
		if (customEntities != null && customEntities.size()>0) {
			for (int i = 0; i < customEntities.size(); i++) {
				if (i==0)
					entityClause.append("UPPER(c.entitytype) NOT IN ("); // Entitytypes are already converted to uppercase
				else
					entityClause.append(", ");
				entityClause.append("'").append(customEntities.get(i).toUpperCase()).append("'");
			}
			entityClause.append(")");
		}
		conditions.add(entityClause.toString());

		// sort order
		ArrayList<String> sortColumns = new ArrayList<String> ();
		sortColumns.add("1");

		// get SQL command
		return sql_select(vendorName, catalogName, schemaName, tableName, tableAlias, columnNames, aliasNames, conditions, sortColumns, true);
	}

	/**
	 * gets the database specific SQL command to find custom columns which have no base element
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param customEntities array of custom entity types
	 * @return SQL command returning
	 * ColumnName, Name, Description, Help, EntityType
	 */
	public String sqlAD_getCustomColumnsWithoutElement (String vendorName, String catalogName, String schemaName, ArrayList<String> customEntities) {

		// table name
		String tableName = "AD_Column";

		// table alias
		String tableAlias = "c";

		// column names
		ArrayList<String> columnNames = new ArrayList<String> ();
		columnNames.add("ColumnName");
		columnNames.add("Name");
		columnNames.add("Description");
		columnNames.add("Help");
		columnNames.add("EntityType");

		// aliases
		ArrayList<String> aliasNames = null;

		// conditions
		ArrayList<String> conditions = new ArrayList<String> ();
		String subQuery = sql_select(vendorName, catalogName, schemaName, "AD_Element", "e", new ArrayList<String> (Arrays.asList("1")), null, new ArrayList<String> (Arrays.asList("UPPER(c.ColumnName)=UPPER(e.ColumnName)")), null, false);
		conditions.add(new StringBuffer("NOT EXISTS (").append(subQuery).append(")").toString());

		// customization-checking part of WHERE clause
		StringBuffer entityClause = new StringBuffer();
		if (customEntities != null && customEntities.size()>0) {
			for (int i = 0; i < customEntities.size(); i++) {
				if (i==0)
					entityClause.append("UPPER(c.entitytype) IN ("); // Entitytypes are already converted to uppercase
				else
					entityClause.append(", ");
				entityClause.append("'").append(customEntities.get(i).toUpperCase()).append("'");
			}
			entityClause.append(")");
		}
		conditions.add(entityClause.toString());

		// sort order
		ArrayList<String> sortColumns = new ArrayList<String> ();
		sortColumns.add("1");

		// get SQL command
		return sql_select(vendorName, catalogName, schemaName, tableName, tableAlias, columnNames, aliasNames, conditions, sortColumns, true);
	}

	/**
	 * gets the database specific SQL command to find system parameters which have no base element
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param customEntities array of custom entity types
	 * @return SQL command returning
	 * ColumnName, Name, Description, Help, EntityType
	 */
	public String sqlAD_getSystemParametersWithoutElement (String vendorName, String catalogName, String schemaName, ArrayList<String> customEntities) {

		// table name
		String tableName = "AD_Process_Para";

		// table alias
		String tableAlias = "c";

		// column names
		ArrayList<String> columnNames = new ArrayList<String> ();
		columnNames.add("ColumnName");
		columnNames.add("Name");
		columnNames.add("Description");
		columnNames.add("Help");
		columnNames.add("EntityType");

		// aliases
		ArrayList<String> aliasNames = null;

		// conditions
		ArrayList<String> conditions = new ArrayList<String> ();
		String subQuery = sql_select(vendorName, catalogName, schemaName, "AD_Element", "e", new ArrayList<String> (Arrays.asList("1")), null, new ArrayList<String> (Arrays.asList("UPPER(c.ColumnName)=UPPER(e.ColumnName)")), null, false);
		conditions.add(new StringBuffer("NOT EXISTS (").append(subQuery).append(")").toString());

		// customization-checking part of WHERE clause
		StringBuffer entityClause = new StringBuffer();
		if (customEntities != null && customEntities.size()>0) {
			for (int i = 0; i < customEntities.size(); i++) {
				if (i==0)
					entityClause.append("UPPER(c.entitytype) NOT IN ("); // Entitytypes are already converted to uppercase
				else
					entityClause.append(", ");
				entityClause.append("'").append(customEntities.get(i).toUpperCase()).append("'");
			}
			entityClause.append(")");
		}
		conditions.add(entityClause.toString());

		// sort order
		ArrayList<String> sortColumns = new ArrayList<String> ();
		sortColumns.add("1");

		// get SQL command
		return sql_select(vendorName, catalogName, schemaName, tableName, tableAlias, columnNames, aliasNames, conditions, sortColumns, true);
	}

	/**
	 * gets the database specific SQL command to find custom parameters which have no base element
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param customEntities array of custom entity types
	 * @return SQL command returning
	 * ColumnName, Name, Description, Help, EntityType
	 */
	public String sqlAD_getCustomParametersWithoutElement (String vendorName, String catalogName, String schemaName, ArrayList<String> customEntities) {

		// table name
		String tableName = "AD_Process_Para";

		// table alias
		String tableAlias = "c";

		// column names
		ArrayList<String> columnNames = new ArrayList<String> ();
		columnNames.add("ColumnName");
		columnNames.add("Name");
		columnNames.add("Description");
		columnNames.add("Help");
		columnNames.add("EntityType");

		// aliases
		ArrayList<String> aliasNames = null;

		// conditions
		ArrayList<String> conditions = new ArrayList<String> ();
		String subQuery = sql_select(vendorName, catalogName, schemaName, "AD_Element", "e", new ArrayList<String> (Arrays.asList("1")), null, new ArrayList<String> (Arrays.asList("UPPER(c.ColumnName)=UPPER(e.ColumnName)")), null, false);
		conditions.add(new StringBuffer("NOT EXISTS (").append(subQuery).append(")").toString());

		// customization-checking part of WHERE clause
		StringBuffer entityClause = new StringBuffer();
		if (customEntities != null && customEntities.size()>0) {
			for (int i = 0; i < customEntities.size(); i++) {
				if (i==0)
					entityClause.append("UPPER(c.entitytype) IN ("); // Entitytypes are already converted to uppercase
				else
					entityClause.append(", ");
				entityClause.append("'").append(customEntities.get(i).toUpperCase()).append("'");
			}
			entityClause.append(")");
		}
		conditions.add(entityClause.toString());

		// sort order
		ArrayList<String> sortColumns = new ArrayList<String> ();
		sortColumns.add("1");

		// get SQL command
		return sql_select(vendorName, catalogName, schemaName, tableName, tableAlias, columnNames, aliasNames, conditions, sortColumns, true);
	}

	/**
	 * gets the database specific SQL command to load Adempiere version information
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return SQL command returning
	 * AD_System_ID, AD_Client_ID, UpdatedBy, Version, ReleaseNo
	 */
	public String sqlAD_getAdempiereVersion (String vendorName, String catalogName, String schemaName) {

		// table name
		String tableName = "AD_System";

		// table alias
		String tableAlias = "t";

		// column names
		ArrayList<String> columnNames = new ArrayList<String> ();
		columnNames.add("AD_System_ID");
		columnNames.add("AD_Client_ID");
		columnNames.add("UpdatedBy");
		columnNames.add("Version");
		columnNames.add("ReleaseNo");

		// aliases
		ArrayList<String> aliasNames = null;

		// conditions
		ArrayList<String> conditions = null;


		// sort order
		ArrayList<String> sortColumns = new ArrayList<String> ();
		sortColumns.add("4");
		sortColumns.add("5");

		// get SQL command
		return sql_select(vendorName, catalogName, schemaName, tableName, tableAlias, columnNames, aliasNames, conditions, sortColumns, true);
	}

	/**
	 * gets the database specific SQL command to check whether a record exists
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName table in which to check for existing record
	 * @param columnName column to use in WHERE clause
	 * @param checkCondition condition to use in WHERE clause
	 * @return SQL command returning the number of records fulfilling checkCondition as "NumberOfRecords"
	 */
	public String sqlAction_checkRecordExists (String vendorName, String catalogName, String schemaName, String tableName, String columnName, String checkCondition) {
		String condition = new StringBuffer(columnName).append("=").append(checkCondition).toString();
		String sql = sql_select(vendorName, catalogName, schemaName, tableName, condition);
		sql=sql.replaceFirst("\\*", new StringBuffer("COUNT(").append(columnName).append(") AS NumberOfRecords").toString());
		return sql;
	}

	/**
	 * gets the database specific SQL command to check whether a dependcy exists
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param localTableName parent table which contains records fulfilling condition
	 * @param localColumnName key column which is referenced by child table
	 * @param checkColumnName column of parent table to use in WHERE clause
	 * @param checkCondition condition to use in WHERE clause
	 * @param foreignTableName child table to check for existing dependencies
	 * @param foreignColumnName column which references parent table
	 * @return SQL command returning the number of dependencies as "NumberOfDependencies" for records fulfilling checkCondition
	 */
	public String sqlAction_checkDependencyExists(String vendorName, String catalogName, String schemaName, String localTableName, String localColumnName, String checkColumnName, String checkCondition, String foreignTableName, String foreignColumnName) {

		// SELECT COUNT(foreignColumnName) FROM foreignTableName frntbl WHERE EXISTS (SELECT 1 FROM localTableName lcltbl WHERE lcltbl.localColumnName = frntbl.foreignColumnName AND lcltbl.checkColumnName=checkCondition)

		// subquery
		// SELECT 1 FROM localTableName lcltbl
		// WHERE lcltbl.localColumnName = frntbl.foreignColumnName AND lcltbl.checkColumnName=checkCondition
		ArrayList<String> columnNames = new ArrayList<String> ();
		columnNames.add("1");
		ArrayList<String> conditions = new ArrayList<String> ();
		conditions.add(new StringBuffer("lcltbl.").append(localColumnName).append("=frntbl.").append(foreignColumnName).toString());
		conditions.add(new StringBuffer("lcltbl.").append(checkColumnName).append("=").append(checkCondition).toString());
		String sqlSubQuery = sql_select(vendorName, catalogName, schemaName, localTableName, "lcltbl", columnNames, null, conditions, null, false);

		// main query
		// SELECT * FROM foreignTableName frntbl WHERE EXISTS (subquery)
		conditions = new ArrayList<String> ();
		conditions.add(new StringBuffer("EXISTS (").append(sqlSubQuery).append(")").toString());
		String sql = sql_select(vendorName, catalogName, schemaName, foreignTableName, "frntbl", null, null, conditions, null, false);

		// return value
		sql=sql.replaceFirst("\\*", new StringBuffer("COUNT(").append(foreignColumnName).append(") AS NumberOfDependencies").toString());
		return sql;
	}

	/**
	 * gets the database specific SQL command to purge system records
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param localTableName local table to purge
	 * @param localColumnNames array of local columns which are referenced by foreign keys
	 * @param foreignTableNames array of foreign tables referencing local columns
	 * @param foreignColumnNames array of foreign columns referencing local columns
	 * @param hasClientID the local table has an AD_Client_ID column
	 * @param customEntities array of custom entities (empty if local table has no EntityType column)
	 * @param specialClause additional restriction to use in WHERE clause
	 * @return SQL command to purge system records
	 */
	@SuppressWarnings("static-access")
	public String sqlADAction_purgeSystemRecords (String vendorName, String catalogName, String schemaName,
						String localTableName, ArrayList<String> localColumnNames,
						ArrayList<String> foreignTableNames, ArrayList<String> foreignColumnNames,
						boolean hasClientID, ArrayList<String> customEntities, String specialClause) {

		ArrayList<String> conditions = new ArrayList<String> ();

		// system client identification part of WHERE clause
		if (hasClientID) {
			conditions.add(new StringBuffer("lcltbl.ad_client_id = ").append(s_parameters.SYSTEMCLIENTID).toString());
		}

		// special clause
		if (specialClause!=null && specialClause.length()>0) {
			conditions.add(specialClause);
		}

		// customization-checking part of WHERE clause
		if (customEntities != null && customEntities.size()>0) {
			StringBuffer entityClause = new StringBuffer();
			for (int i = 0; i < customEntities.size(); i++) {
				if (i==0)
					entityClause.append("UPPER(entitytype) NOT IN (");
				else
					entityClause.append(", ");
				entityClause.append("'").append(customEntities.get(i).toUpperCase()).append("'");
			}
			entityClause.append(") ");
			conditions.add(entityClause.toString());
		}

		// foreign keys part of WHERE clause
		for (int i = 0; i < foreignColumnNames.size(); i++) {
			String foreignTableName = foreignTableNames.get(i);
			String foreignColumnName = normalizeColumnName(vendorName, foreignColumnNames.get(i));
			String localColumnName = normalizeColumnName(vendorName, localColumnNames.get(i));
			String subQuery = sql_select(vendorName, catalogName, schemaName, foreignTableName, "frntbl", new ArrayList<String> (Arrays.asList("1")), null, new ArrayList<String> (Arrays.asList(new StringBuffer("lcltbl.").append(localColumnName).append(" = frntbl.").append(foreignColumnName).toString())), null, false);
			conditions.add(new StringBuffer("NOT EXISTS (").append(subQuery).append(")").toString());
		}

		// get SQL command
		return sql_delete(vendorName, catalogName, schemaName, localTableName, "lcltbl", conditions, null);
	}

	/**
	 * gets the database-specific SQL command to delete unused element translations
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return SQL command to delete unused element translations
	 */
	public String sqlADAction_deleteUnusedElementTranslations (String vendorName, String catalogName, String schemaName) {

		String subQuery2a = sql_select(vendorName, catalogName, schemaName, "AD_Column", "c", new ArrayList<String> (Arrays.asList("1")), null, new ArrayList<String> (Arrays.asList("UPPER(e.ColumnName) = UPPER(c.ColumnName)")), null, false);
		String subQuery2b = sql_select(vendorName, catalogName, schemaName, "AD_Process_Para", "p", new ArrayList<String> (Arrays.asList("1")), null, new ArrayList<String> (Arrays.asList("UPPER(e.ColumnName) = UPPER(p.ColumnName)")), null, false);
		String subQuery1 = sql_select(vendorName, catalogName, schemaName,
				"AD_Element", "e",
				new ArrayList<String> (Arrays.asList("AD_Element_ID")), null,
				new ArrayList<String> (Arrays.asList(new StringBuffer("NOT EXISTS (").append(subQuery2a).append(")").toString(),
													new StringBuffer("NOT EXISTS (").append(subQuery2b).append(")").toString())),
				null, false);

		String condition = new StringBuffer("AD_Element_ID IN (").append(subQuery1).append(")").toString();

		return sql_delete(vendorName, catalogName, schemaName, "AD_Element_Trl", null, new ArrayList<String> (Arrays.asList(condition)), null);
	}

	/**
	 * gets the database-specific SQL command to delete unused elements
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return SQL command to delete unused elements
	 */
	public String sqlADAction_deleteUnusedElements (String vendorName, String catalogName, String schemaName) {

		String subQuery1a = sql_select(vendorName, catalogName, schemaName, "AD_Column", "c", new ArrayList<String> (Arrays.asList("1")), null, new ArrayList<String> (Arrays.asList("UPPER(e.ColumnName) = UPPER(c.ColumnName)")), null, false);
		String subQuery1b = sql_select(vendorName, catalogName, schemaName, "AD_Process_Para", "p", new ArrayList<String> (Arrays.asList("1")), null, new ArrayList<String> (Arrays.asList("UPPER(e.ColumnName) = UPPER(p.ColumnName)")), null, false);

		return sql_delete(vendorName, catalogName, schemaName, "AD_Element", "e",
							new ArrayList<String> (Arrays.asList(new StringBuffer("NOT EXISTS (").append(subQuery1a).append(")").toString(),
																new StringBuffer("NOT EXISTS (").append(subQuery1b).append(")").toString()
							)), null);
	}

	/**
	 * gets the database-specific SQL command to link columns with elements
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return SQL command to link columns with elements
	 */
	public String sqlADAction_updateLinkColumnElement (String vendorName, String catalogName, String schemaName) {

		// columns
		ArrayList<String> columnNames = new ArrayList<String> ();
		columnNames.add("AD_Element_ID");

		// values
		String subQuery = sql_select(vendorName, catalogName, schemaName, "AD_Element", "e", new ArrayList<String> (Arrays.asList("AD_Element_ID")), null, new ArrayList<String> (Arrays.asList("UPPER(c.ColumnName)=UPPER(e.ColumnName)")), null, false);
		ArrayList<String> values = new ArrayList<String> ();
		values.add(new StringBuffer("(").append(subQuery).append(")").toString());

		// conditions
		ArrayList<String> conditions = new ArrayList<String> ();
		conditions.add("c.AD_Element_ID IS NULL");

		// get SQL command
		return sql_update(vendorName, catalogName, schemaName, "AD_Column", "c", columnNames, values, conditions);
	}

	/**
	 * gets the database-specific SQL command to link parameters with elements
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return SQL command to link cparameters with elements
	 */
	public String sqlADAction_updateLinkParameterElement (String vendorName, String catalogName, String schemaName) {

		// columns
		ArrayList<String> columnNames = new ArrayList<String> ();
		columnNames.add("AD_Element_ID");

		// values
		String subQuery = sql_select(vendorName, catalogName, schemaName, "AD_Element", "e", new ArrayList<String> (Arrays.asList("AD_Element_ID")), null, new ArrayList<String> (Arrays.asList("UPPER(c.ColumnName)=UPPER(e.ColumnName)")), null, false);
		ArrayList<String> values = new ArrayList<String> ();
		values.add(new StringBuffer("(").append(subQuery).append(")").toString());

		// conditions
		ArrayList<String> conditions = new ArrayList<String> ();
		conditions.add("c.AD_Element_ID IS NULL");

		// get SQL command
		return sql_update(vendorName, catalogName, schemaName, "AD_Process_Para", "c", columnNames, values, conditions);
	}

	/**
	 * gets the database-specific SQL command to synchronize terminology
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param targetTableName name of the table which holds data to update (aliased as tt)
	 * @param sourceTableName name of the table which holds data to deploy (aliased as ts)
	 * @param targetTranslationName name of the table which holds translated data to update (aliased as ttl)
	 * @param sourceTranslationName name of the table which holds translated data to deploy (aliased as tsl)
	 * @param joinTableNames array of tables joining the target table with the source table (aliased as tj0, tj1, ..., tjn)
	 * @param linkConditions array of conditions to link the target, joined, and source tables
	 * @param extraTableNames array of tables providing additional information for the target table (aliased as tx0, tx1, ..., txn)
	 * @param extraConditions array of conditions to link the extra tables
	 * @param hasCentrallyMaintained the target table has a column to indicate whether it is centrally maintained
	 * @param updateColumns array of columns to be updated
	 * @param updateValues array of values with which to update the columns
	 * @param updateConditions array of conditions (ORed) under which to update the columns
	 * @return SQL command to synchronize terminology
	 */
	public String sqlADAction_updateTerminology (String vendorName, String catalogName, String schemaName,
			String targetTableName, String sourceTableName,
			String targetTranslationName, String sourceTranslationName,
			ArrayList<String> joinTableNames, ArrayList<String> linkConditions,
			ArrayList<String> extraTableNames, ArrayList<String> extraConditions,
			boolean hasCentrallyMaintained,
			ArrayList<String> updateColumns, ArrayList<String> updateValues, ArrayList<String> updateConditions) {
		return m_interfaces.get(getDBVendorID(vendorName)).sqlADAction_updateTerminology(catalogName, schemaName, targetTableName, sourceTableName, targetTranslationName, sourceTranslationName, joinTableNames, linkConditions, extraTableNames, extraConditions, hasCentrallyMaintained, updateColumns, updateValues, updateConditions);
	}

	/**
	 * gets the databse-specific SQL command to add missing translation records
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param columnNames array of columns which must be translated
	 * @return SQL command to add translation records
	 */
	public String sqlADAction_insertTranslation (String vendorName, String catalogName, String schemaName, String tableName, ArrayList<String> columnNames) {

		// ID-Column
		String idColumn = new StringBuffer(tableName).append("_ID").toString();

		// column list
		ArrayList<String> completeColumnNames = new ArrayList<String> ();
		completeColumnNames.add("AD_Language");
		completeColumnNames.add("AD_Client_ID");
		completeColumnNames.add("AD_Org_ID");
		completeColumnNames.add("Created");
		completeColumnNames.add("CreatedBy");
		completeColumnNames.add("Updated");
		completeColumnNames.add("UpdatedBy");
		completeColumnNames.add("IsActive");
		completeColumnNames.add("IsTranslated");
		completeColumnNames.add(idColumn);
		if (columnNames!= null && columnNames.size()>0) {
			for (String columnName : columnNames) {
				completeColumnNames.add(columnName);
			}
		}

		// value list
		ArrayList<String> columnValues = new ArrayList<String> ();
		columnValues.add("t0.AD_Language");
		columnValues.add("t.AD_Client_ID");
		columnValues.add("t.AD_Org_ID");
		columnValues.add(translateExpression("PostgreSQL", vendorName, "now()"));
		columnValues.add("0");
		columnValues.add(translateExpression("PostgreSQL", vendorName, "now()"));
		columnValues.add("0");
		columnValues.add("'Y'");
		columnValues.add("'N'");
		columnValues.add(new StringBuffer("t.").append(idColumn).toString());
		if (columnNames!= null && columnNames.size()>0) {
			for (String columnName : columnNames) {
				columnValues.add(new StringBuffer("t.").append(columnName).toString());
			}
		}

		// target table name
		String targetTableName = new StringBuffer(tableName).append("_Trl").toString();

		// source table name
		String sourceTableName = tableName;

		// join tables
		ArrayList<String> joinTypes = new ArrayList<String> ();
		ArrayList<String> joinTables = new ArrayList<String> ();
		ArrayList<String> joinConditions = new ArrayList<String> ();
		joinTypes.add("INNER JOIN");
		joinTables.add("AD_Language");
		joinConditions.add("t0.IsSystemLanguage='Y'");

		// where clause
		String subQuery = sql_select(vendorName, catalogName, schemaName, targetTableName, "r", new ArrayList<String> (Arrays.asList("1")), null,
				new ArrayList<String> (Arrays.asList(new StringBuffer("r.").append(idColumn).append(" = t.").append(idColumn).toString(),"r.AD_Language = t0.AD_Language")),
				null, false);
		String whereClause = new StringBuffer("NOT EXISTS (").append(subQuery).append(")").toString();

		return sql_insertFromTable(vendorName, catalogName, schemaName, targetTableName, completeColumnNames, columnValues, sourceTableName, joinTypes, joinTables, joinConditions, whereClause);
	}

	/**
	 * get the databse-specific SQL command to purge records with unsatisfied foreign keys
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param localTableName local table in which to search
	 * @param localColumnNames array of columns which reference a foreign table
	 * @param foreignKeyNames array of names of foreign keys
	 * @param foreignTableNames array of foreign tables being referenced
	 * @param foreignColumnNames array of foreign columns being referenced
	 * @return SQL command to purge records with unsatisfied foreign keys
	 */
	public String sqlAction_purgeOrphans (String vendorName, String catalogName, String schemaName,
			String localTableName, ArrayList<String> localColumnNames,
			ArrayList<String> foreignKeyNames, ArrayList<String> foreignTableNames, ArrayList<String> foreignColumnNames) {

		// translate local column names
		ArrayList<String> translatedLocalColumnNames = new ArrayList<String> ();
		for (String columnName : localColumnNames) {
			translatedLocalColumnNames.add(normalizeColumnName(vendorName, columnName));
		}

		// translate foreign column names
		ArrayList<String> translatedForeignColumnNames = new ArrayList<String> ();
		for (String columnName : foreignColumnNames) {
			translatedForeignColumnNames.add(normalizeColumnName(vendorName, columnName));
		}

		return m_interfaces.get(getDBVendorID(vendorName)).sqlAction_purgeOrphans(catalogName, schemaName, localTableName, translatedLocalColumnNames, foreignKeyNames, foreignTableNames, translatedForeignColumnNames);
	}

	/**
	 * gets the database-specific SQL command to update child records
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param foreignTableName the foreign (child) table in which to update records
	 * @param foreignColumnNames array of column names to set (foreign key)
	 * @param localTableName the local (parent) table
	 * @param localColumnNames array of column names to retrieve (primary key)
	 * @param whereColumnNames array of column names to use in WHERE clause (selecting rows in the parent table)
	 * @return SQL command to update record
	 */
	public String sqlAction_updateChildRecord (String vendorName, String catalogName, String schemaName, String foreignTableName, ArrayList<String> foreignColumnNames, String localTableName, ArrayList<String> localColumnNames, ArrayList<String> whereColumnNames) {

		// UPDATE schemaName.foreignTableName
		// SET foreignColName0 = ?, foreignColName1 = ?, foreignColName2 = ? ...
		// WHERE (foreignColName0, foreignColName1, foreignColName2) IN
		// 		(SELECT p.localColName0, p.localColName1, p.localColName2 ... FROM schemaName.localTableName p
		//			WHERE p.whereColumnName0 = ? AND p.whereColumnName1 = ? AND p.whereColumnName2 = ? ...
		//		)

		// subquery - local column names
		ArrayList<String> translatedLocalColumnNames = new ArrayList<String> ();
		for (String columnName : localColumnNames) {
			translatedLocalColumnNames.add(new StringBuffer("p.").append(columnName).toString());
		}
		// subquery - where clause
		ArrayList<String> translatedWhereColumnNames = new ArrayList<String> ();
		for (String columnName : whereColumnNames) {
			translatedWhereColumnNames.add(new StringBuffer("p.").append(columnName).append(" = ?").toString());
		}

		// get subquery
		String subQuery = sql_select(vendorName, catalogName, schemaName, localTableName, "p", translatedLocalColumnNames, null, translatedWhereColumnNames, null, false);

		// main WHERE clause
		StringBuffer condition = null;
		if (foreignColumnNames!=null) {
			condition = new StringBuffer ();
			for (int i=0; i<foreignColumnNames.size(); i++) {
				String foreignColName = normalizeColumnName(vendorName, foreignColumnNames.get(i));
				if (i==0)
					condition.append("(");
				else
					condition.append(", ");
				condition.append(foreignColName);
			}
			condition.append(")");
		}
		ArrayList<String> conditions = new ArrayList<String> ();
		conditions.add(condition.append(" IN (").append(subQuery).append(")").toString());

		return sql_update(vendorName, catalogName, schemaName, foreignTableName, null, foreignColumnNames, null, conditions);
	}

	/**
	 * gets the database-specific SQL command to preserve links to parent records
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param brokenTableName the table for which to preserve links to parent
	 * @param brokenColumnName the column which should contain link to parent
	 * @param brokenDefaultValue the default value which the column will contain if the link to parent is broken
	 * @param brokenCompareColumns array of column which can be used to get a hint for finding the correct parent
	 * @param hintTableName the table which contains hints to find the correct parent
	 * @param hintColumnName the column which contains the link to the correct parent
	 * @param hintCompareColumns array of columns which can be used to give a hint for finding the correct parent
	 * @return SQL command to preserve links to parent records
	 */
	public String sqlAction_preserveParentLinks(String vendorName, String catalogName, String schemaName, String brokenTableName, String brokenColumnName, String brokenDefaultValue, ArrayList<String> brokenCompareColumns, String hintTableName, String hintColumnName, ArrayList<String> hintCompareColumns) {

		// UPDATE schemaName.brokenTableName t
		// SET brokenColumnName = (SELECT MIN(t1.hintColumnName)
		//							FROM schemaName.hintTableName t1
		//							WHERE t1.hintCompareColumnName0 = t.brokenCompareColumnName0
		//							AND   t1.hintCompareColumnName1 = t.brokenCompareColumnName1
		//							AND   t1.hintCompareColumnName2 = t.brokenCompareColumnName2
		//							...
		//							[AND t1.brokenColumnName != brokenDefaultValue]
		//							)
		// WHERE t.brokenColumnName = brokenDefaultValue;
		// (The last AND clause is only used if brokenTableName and hintTableName are the same).

		// SELECT SUBQUERY
		// ---------------

		// the hint column (must be in a list)
		ArrayList<String> hintColumnNames = new ArrayList<String> ();
		hintColumnNames.add(hintColumnName);

		// WHERE clause for the SELECT subquery
		ArrayList<String> selectConditions = new ArrayList<String> ();
		for (int i=0; i<brokenCompareColumns.size(); i++) {
			String brokenCompareColumn = brokenCompareColumns.get(i);
			String hintCompareColumn = hintCompareColumns.get(i);
			selectConditions.add(new StringBuffer("t1.").append(hintCompareColumn).append(" = t.").append(brokenCompareColumn).toString());
		}
		if (brokenTableName.equalsIgnoreCase(hintTableName))
			selectConditions.add(new StringBuffer("t1.").append(brokenColumnName).append(" != ").append(brokenDefaultValue).toString());

		// the SELECT subquery
		String sqlSelect = sql_select(vendorName, catalogName, schemaName, hintTableName, "t1", hintColumnNames, null, selectConditions, null, false);
		sqlSelect = sqlSelect.replaceFirst(hintColumnName, new StringBuffer("MIN(t1.").append(hintColumnName).append(")").toString());

		// UPDATE MAIN QUERY
		// -----------------

		// the broken column (must be in a List)
		ArrayList<String> brokenColumnNames = new ArrayList<String> ();
		brokenColumnNames.add(brokenColumnName);

		// the SET value for the broken column (above SELECT subquery, must be in a list)
		ArrayList<String> fixColumnValues = new ArrayList<String> ();
		fixColumnValues.add(new StringBuffer("(").append(sqlSelect).append(")").toString());

		// WHERE clause for the UPDATE main query
		ArrayList<String> updateConditions = new ArrayList<String> ();
		updateConditions.add(new StringBuffer("t.").append(brokenColumnName).append(" = ").append(brokenDefaultValue).toString());

		// the UPDATE main query
		String sqlUpdate = sql_update(vendorName, catalogName, schemaName, brokenTableName, "t", brokenColumnNames, fixColumnValues, updateConditions);

		return sqlUpdate;
	}

	/**
	 * gets the database-specific SQL command to drop duplicate records
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param keyColumnNames key columns identifying the record
	 * @return SQL command to drop duplicate record
	 */
	public String sqlAction_dropDuplicates(String vendorName, String catalogName, String schemaName, String tableName, ArrayList<String> keyColumnNames) {

		if (keyColumnNames != null && keyColumnNames.size() > 0) {
			// translate column names
			ArrayList<String> translatedColumnNames = new ArrayList<String> ();
			for (String columnName : keyColumnNames) {
				translatedColumnNames.add(normalizeColumnName(vendorName, columnName));
			}

			return m_interfaces.get(getDBVendorID(vendorName)).sqlAction_dropDuplicates(catalogName, schemaName, tableName, translatedColumnNames);
		} else
			// do not attempt to delete anything if there is no primary key
			return null;
	}

	/**
	 * gets the database-specific SQL command to enforce check constraints
	 * <p>
	 * set rows to first value allowed by check constraint if the constraint
	 * would otherwise be violated by the current value.
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param checkExpression the check constraint
	 * @return SQL command to enforce check constraints
	 */
	public String sqlAction_enforceCheckConstraints (String vendorName, String catalogName, String schemaName, String tableName, String checkExpression) {
		if (checkExpression!=null)
			return m_interfaces.get(getDBVendorID(vendorName)).sqlAction_enforceCheckConstraints(catalogName, schemaName, tableName, checkExpression);
		else
			return null;
	}

}
