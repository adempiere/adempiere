CREATE TABLE ad_package_imp_backup
(
  ad_package_imp_backup_id  NUMBER(10)          NOT NULL,
  ad_client_id              NUMBER(10)          NOT NULL,
  ad_org_id                 NUMBER(10)          NOT NULL,
  isactive                  CHAR(1 BYTE)        DEFAULT 'Y'                   NOT NULL,
  created                   DATE                DEFAULT SYSDATE               NOT NULL,
  createdby                 NUMBER(10)          NOT NULL,
  updated                   DATE                DEFAULT SYSDATE               NOT NULL,
  updatedby                 NUMBER(10)          NOT NULL,
  ad_package_imp_id         NUMBER(10)          NOT NULL,
  ad_package_imp_detail_id  NUMBER(10)          NOT NULL,
  ad_table_id               NUMBER(10),
  ad_column_id              NUMBER(10),
  ad_reference_id           NUMBER(10),
  ad_package_imp_bck_dir    NVARCHAR2(255),
  ad_package_imp_org_dir    NVARCHAR2(255),
  colvalue                  NVARCHAR2(2000),
  uninstall                 CHAR(1 BYTE)
);


CREATE TABLE ad_package_imp
(
  ad_package_imp_id  NUMBER(10)                 NOT NULL,
  ad_client_id       NUMBER(10)                 NOT NULL,
  ad_org_id          NUMBER(10)                 NOT NULL,
  isactive           CHAR(1 BYTE)               DEFAULT 'Y'                   NOT NULL,
  created            DATE                       DEFAULT SYSDATE               NOT NULL,
  createdby          NUMBER(10)                 NOT NULL,
  updated            DATE                       DEFAULT SYSDATE               NOT NULL,
  updatedby          NUMBER(10)                 NOT NULL,
  NAME               NVARCHAR2(60)              NOT NULL,
  pk_status          NVARCHAR2(22),
  releaseno          NVARCHAR2(20),
  pk_version         NVARCHAR2(20),
  VERSION            NVARCHAR2(20),
  description        NVARCHAR2(1000)            NOT NULL,
  email              NVARCHAR2(60),
  processed          CHAR(1 BYTE)               DEFAULT 'N',
  processing         CHAR(1 BYTE)               DEFAULT 'N'                   NOT NULL,
  creator            NVARCHAR2(60),
  creatorcontact     NVARCHAR2(255),
  createddate        NVARCHAR2(25),
  updateddate        NVARCHAR2(25),
  uninstall          CHAR(1 BYTE)
);


CREATE TABLE ad_package_imp_inst
(
  ad_package_imp_inst_id  NUMBER(10)            NOT NULL,
  ad_client_id            NUMBER(10),
  ad_org_id               NUMBER(10),
  isactive                CHAR(1 BYTE)          DEFAULT 'Y',
  created                 DATE                  DEFAULT SYSDATE,
  createdby               NUMBER(10),
  updated                 DATE                  DEFAULT SYSDATE,
  updatedby               NUMBER(10),
  NAME                    NVARCHAR2(240),
  pk_status               NVARCHAR2(44),
  releaseno               NVARCHAR2(40),
  pk_version              NVARCHAR2(40),
  VERSION                 NVARCHAR2(40),
  description             NVARCHAR2(2000),
  email                   NVARCHAR2(120),
  processed               CHAR(1 BYTE)          DEFAULT 'N',
  processing              CHAR(1 BYTE)          DEFAULT 'N',
  creator                 NVARCHAR2(120),
  creatorcontact          NVARCHAR2(510),
  createddate             NVARCHAR2(50),
  updateddate             NVARCHAR2(50),
  uninstall               CHAR(1 BYTE)
);


CREATE TABLE ad_package_imp_detail
(
  ad_package_imp_detail_id  NUMBER(10)          NOT NULL,
  ad_client_id              NUMBER(10)          NOT NULL,
  ad_org_id                 NUMBER(10)          NOT NULL,
  isactive                  CHAR(1 BYTE)        DEFAULT 'Y'                   NOT NULL,
  created                   DATE                DEFAULT SYSDATE               NOT NULL,
  createdby                 NUMBER(10)          NOT NULL,
  updated                   DATE                DEFAULT SYSDATE               NOT NULL,
  updatedby                 NUMBER(10)          NOT NULL,
  NAME                      NVARCHAR2(60),
  ad_package_imp_id         NUMBER(10)          NOT NULL,
  ad_original_id            NUMBER(10)          NOT NULL,
  ad_backup_id              NUMBER(10),
  action                    NVARCHAR2(20),
  success                   NVARCHAR2(20),
  TYPE                      NVARCHAR2(60),
  tablename                 NVARCHAR2(60),
  ad_table_id               NUMBER(10),
  uninstall                 CHAR(1 BYTE)
);


CREATE TABLE ad_package_exp
(
  ad_package_exp_id  NUMBER(10)                 NOT NULL,
  ad_client_id       NUMBER(10)                 NOT NULL,
  ad_org_id          NUMBER(10)                 NOT NULL,
  isactive           CHAR(1 BYTE)               DEFAULT 'Y'                   NOT NULL,
  created            DATE                       DEFAULT SYSDATE               NOT NULL,
  createdby          NUMBER(10)                 NOT NULL,
  updated            DATE                       DEFAULT SYSDATE               NOT NULL,
  updatedby          NUMBER(10)                 NOT NULL,
  ad_package_type    NVARCHAR2(1),
  email              NVARCHAR2(30)              NOT NULL,
  instructions       NVARCHAR2(1000)            NOT NULL,
  pk_name            NVARCHAR2(60)              NOT NULL,
  processed          CHAR(1 BYTE),
  releaseno          NVARCHAR2(20)              NOT NULL,
  VERSION            NVARCHAR2(20)              NOT NULL,
  username           NVARCHAR2(30)              NOT NULL,
  processing         CHAR(1 BYTE)               NOT NULL,
  pk_version         NVARCHAR2(20)              NOT NULL,
  file_directory     NVARCHAR2(255)             NOT NULL,
  description        NVARCHAR2(1000)            NOT NULL
);


CREATE TABLE ad_package_exp_detail
(
  ad_package_exp_detail_id  NUMBER(10)          NOT NULL,
  ad_client_id              NUMBER(10)          NOT NULL,
  ad_org_id                 NUMBER(10)          NOT NULL,
  isactive                  CHAR(1 BYTE)        DEFAULT 'Y'                   NOT NULL,
  created                   DATE                DEFAULT SYSDATE               NOT NULL,
  createdby                 NUMBER              NOT NULL,
  updated                   DATE                DEFAULT SYSDATE               NOT NULL,
  updatedby                 NUMBER              NOT NULL,
  ad_form_id                NUMBER(10),
  ad_impformat_id           NUMBER(10),
  ad_menu_id                NUMBER(10),
  ad_process_id             NUMBER(10),
  ad_role_id                NUMBER(10),
  ad_window_id              NUMBER(10),
  ad_workflow_id            NUMBER(10),
  file_directory            NVARCHAR2(255),
  filename                  NVARCHAR2(255),
  destination_filename      NVARCHAR2(255),
  destination_directory     NVARCHAR2(255),
  description               NVARCHAR2(1000)     NOT NULL,
  dbtype                    NVARCHAR2(22),
  TYPE                      NVARCHAR2(10)       NOT NULL,
  target_directory          NVARCHAR2(255),
  sqlstatement              NVARCHAR2(2000),
  releaseno                 NVARCHAR2(20),
  processing                CHAR(1 BYTE)        NOT NULL,
  processed                 CHAR(1 BYTE),
  pk_name                   NVARCHAR2(60)       NOT NULL,
  name2                     NVARCHAR2(60),
  line                      NUMBER,
  ad_workbench_id           NUMBER(10),
  ad_table_id               NUMBER(10),
  ad_reportview_id          NUMBER(10),
  ad_package_exp_id         NUMBER(10)          NOT NULL,
  ad_package_code_new       NVARCHAR2(2000),
  ad_package_code_old       NVARCHAR2(2000)
);


CREATE TABLE ad_package_exp_common
(
  ad_package_exp_common_id  NUMBER(10)          NOT NULL,
  ad_client_id              NUMBER(10)          NOT NULL,
  ad_org_id                 NUMBER(10)          NOT NULL,
  isactive                  CHAR(1 BYTE)        DEFAULT 'Y'                   NOT NULL,
  created                   DATE                DEFAULT SYSDATE               NOT NULL,
  createdby                 NUMBER(10)          NOT NULL,
  updated                   DATE                DEFAULT SYSDATE               NOT NULL,
  updatedby                 NUMBER(10)          NOT NULL,
  ad_form_id                NUMBER(10),
  ad_impformat_id           NUMBER(10),
  ad_reportview_id          NUMBER(10),
  ad_table_id               NUMBER(10),
  ad_workbench_id           NUMBER(10),
  dbtype                    NVARCHAR2(22),
  processed                 CHAR(1 BYTE),
  pk_name                   NVARCHAR2(60),
  name2                     NVARCHAR2(60),
  line                      NUMBER,
  file_directory            NVARCHAR2(255),
  filename                  NVARCHAR2(255),
  destination_directory     NVARCHAR2(255),
  description               NVARCHAR2(1000),
  TYPE                      NVARCHAR2(10),
  target_directory          NVARCHAR2(255),
  sqlstatement              NVARCHAR2(255),
  processing                CHAR(1 BYTE),
  ad_workflow_id            NUMBER(10),
  ad_window_id              NUMBER(10),
  ad_role_id                NUMBER(10),
  ad_process_id             NUMBER(10),
  ad_menu_id                NUMBER(10)
);


CREATE TABLE ad_package_imp_proc
(
  ad_package_imp_proc_id  NUMBER(10)            NOT NULL,
  ad_client_id            NUMBER(10)            NOT NULL,
  ad_org_id               NUMBER(10)            NOT NULL,
  isactive                CHAR(1 BYTE)          DEFAULT 'Y'                   NOT NULL,
  created                 DATE                  DEFAULT SYSDATE               NOT NULL,
  createdby               NUMBER(10)            NOT NULL,
  updated                 DATE                  DEFAULT SYSDATE               NOT NULL,
  updatedby               NUMBER(10)            NOT NULL,
  ad_override_dict        CHAR(1 BYTE),
  ad_package_dir          NVARCHAR2(255),
  ad_package_source       NVARCHAR2(255),
  ad_package_source_type  NVARCHAR2(10)         NOT NULL,
  processing              CHAR(1 BYTE)
);


ALTER TABLE ad_package_imp_backup ADD (
  PRIMARY KEY
 (ad_package_imp_backup_id));


ALTER TABLE ad_package_imp ADD (
  PRIMARY KEY
 (ad_package_imp_id));


ALTER TABLE ad_package_imp_inst ADD (
  PRIMARY KEY
 (ad_package_imp_inst_id));


ALTER TABLE ad_package_imp_detail ADD (
  PRIMARY KEY
 (ad_package_imp_detail_id));


ALTER TABLE ad_package_exp ADD (
  PRIMARY KEY
 (ad_package_exp_id));


ALTER TABLE ad_package_exp_detail ADD (
  PRIMARY KEY
 (ad_package_exp_detail_id));


ALTER TABLE ad_package_exp_common ADD (
  PRIMARY KEY
 (ad_package_exp_common_id));


ALTER TABLE ad_package_imp_proc ADD (
  PRIMARY KEY
 (ad_package_imp_proc_id));
