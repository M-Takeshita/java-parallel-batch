USE PBD;

CREATE TABLE BATCH_JOB_INSTANCE  (
	JOB_INSTANCE_ID BIGINT  NOT NULL PRIMARY KEY ,
	VERSION BIGINT ,
	JOB_NAME VARCHAR(100) NOT NULL,
	JOB_KEY VARCHAR(32) NOT NULL,
	constraint JOB_INST_UN unique (JOB_NAME, JOB_KEY)
) ENGINE=InnoDB;

CREATE TABLE BATCH_JOB_EXECUTION  (
	JOB_EXECUTION_ID BIGINT  NOT NULL PRIMARY KEY ,
	VERSION BIGINT  ,
	JOB_INSTANCE_ID BIGINT NOT NULL,
	CREATE_TIME DATETIME(6) NOT NULL,
	START_TIME DATETIME(6) DEFAULT NULL ,
	END_TIME DATETIME(6) DEFAULT NULL ,
	STATUS VARCHAR(10) ,
	EXIT_CODE VARCHAR(2500) ,
	EXIT_MESSAGE VARCHAR(2500) ,
	LAST_UPDATED DATETIME(6),
	constraint JOB_INST_EXEC_FK foreign key (JOB_INSTANCE_ID)
	references BATCH_JOB_INSTANCE(JOB_INSTANCE_ID)
) ENGINE=InnoDB;

CREATE TABLE BATCH_JOB_EXECUTION_PARAMS  (
	JOB_EXECUTION_ID BIGINT NOT NULL ,
	PARAMETER_NAME VARCHAR(100) NOT NULL ,
	PARAMETER_TYPE VARCHAR(100) NOT NULL ,
	PARAMETER_VALUE VARCHAR(2500) ,
	IDENTIFYING CHAR(1) NOT NULL ,
	constraint JOB_EXEC_PARAMS_FK foreign key (JOB_EXECUTION_ID)
	references BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
) ENGINE=InnoDB;

CREATE TABLE BATCH_STEP_EXECUTION  (
	STEP_EXECUTION_ID BIGINT  NOT NULL PRIMARY KEY ,
	VERSION BIGINT NOT NULL,
	STEP_NAME VARCHAR(100) NOT NULL,
	JOB_EXECUTION_ID BIGINT NOT NULL,
	CREATE_TIME DATETIME(6) NOT NULL,
	START_TIME DATETIME(6) DEFAULT NULL ,
	END_TIME DATETIME(6) DEFAULT NULL ,
	STATUS VARCHAR(10) ,
	COMMIT_COUNT BIGINT ,
	READ_COUNT BIGINT ,
	FILTER_COUNT BIGINT ,
	WRITE_COUNT BIGINT ,
	READ_SKIP_COUNT BIGINT ,
	WRITE_SKIP_COUNT BIGINT ,
	PROCESS_SKIP_COUNT BIGINT ,
	ROLLBACK_COUNT BIGINT ,
	EXIT_CODE VARCHAR(2500) ,
	EXIT_MESSAGE VARCHAR(2500) ,
	LAST_UPDATED DATETIME(6),
	constraint JOB_EXEC_STEP_FK foreign key (JOB_EXECUTION_ID)
	references BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
) ENGINE=InnoDB;

CREATE TABLE BATCH_STEP_EXECUTION_CONTEXT  (
	STEP_EXECUTION_ID BIGINT NOT NULL PRIMARY KEY,
	SHORT_CONTEXT VARCHAR(2500) NOT NULL,
	SERIALIZED_CONTEXT TEXT ,
	constraint STEP_EXEC_CTX_FK foreign key (STEP_EXECUTION_ID)
	references BATCH_STEP_EXECUTION(STEP_EXECUTION_ID)
) ENGINE=InnoDB;

CREATE TABLE BATCH_JOB_EXECUTION_CONTEXT  (
	JOB_EXECUTION_ID BIGINT NOT NULL PRIMARY KEY,
	SHORT_CONTEXT VARCHAR(2500) NOT NULL,
	SERIALIZED_CONTEXT TEXT ,
	constraint JOB_EXEC_CTX_FK foreign key (JOB_EXECUTION_ID)
	references BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
) ENGINE=InnoDB;

CREATE TABLE BATCH_STEP_EXECUTION_SEQ (
	ID BIGINT NOT NULL,
	UNIQUE_KEY CHAR(1) NOT NULL,
	constraint UNIQUE_KEY_UN unique (UNIQUE_KEY)
) ENGINE=InnoDB;

INSERT INTO BATCH_STEP_EXECUTION_SEQ (ID, UNIQUE_KEY) select * from (select 0 as ID, '0' as UNIQUE_KEY) as tmp where not exists(select * from BATCH_STEP_EXECUTION_SEQ);

CREATE TABLE BATCH_JOB_EXECUTION_SEQ (
	ID BIGINT NOT NULL,
	UNIQUE_KEY CHAR(1) NOT NULL,
	constraint UNIQUE_KEY_UN unique (UNIQUE_KEY)
) ENGINE=InnoDB;

INSERT INTO BATCH_JOB_EXECUTION_SEQ (ID, UNIQUE_KEY) select * from (select 0 as ID, '0' as UNIQUE_KEY) as tmp where not exists(select * from BATCH_JOB_EXECUTION_SEQ);

CREATE TABLE BATCH_JOB_SEQ (
	ID BIGINT NOT NULL,
	UNIQUE_KEY CHAR(1) NOT NULL,
	constraint UNIQUE_KEY_UN unique (UNIQUE_KEY)
) ENGINE=InnoDB;

INSERT INTO BATCH_JOB_SEQ (ID, UNIQUE_KEY) select * from (select 0 as ID, '0' as UNIQUE_KEY) as tmp where not exists(select * from BATCH_JOB_SEQ);

DROP TABLE IF EXISTS `M_Genders`;
create table IF not exists `M_Genders`
(
  `id`               INT(2) PRIMARY KEY AUTO_INCREMENT,
  `gender`           VARCHAR(3) NOT NULL,
  `created_at`       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at`       TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

DROP TABLE IF EXISTS `M_Products`;
create table IF not exists `M_Products`
(
  `id`               VARCHAR(36) PRIMARY KEY NOT NULL DEFAULT (UUID()),
  `name`             VARCHAR(255) NOT NULL,
  `description`      VARCHAR(1000) NOT NULL,
  `unit_amount`      DECIMAL(10, 2) NOT NULL,
  `tax_rate`         DECIMAL(5, 2) NOT NULL,
  `created_at`       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at`       TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

DROP TABLE IF EXISTS `M_Users`;
create table IF not exists `M_Users`
(
  `id`                  VARCHAR(36) PRIMARY KEY NOT NULL DEFAULT (UUID()),
  `first_name`          VARCHAR(50) NOT NULL,
  `last_name`           VARCHAR(50) NOT NULL,
  `first_name_kana`     VARCHAR(50) NOT NULL,
  `last_name_kana`      VARCHAR(50) NOT NULL,
  `age`                 INT(3) NOT NULL,
  `gender_id`           INT(2) NOT NULL,
  `created_at`          TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at`          TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (gender_id) REFERENCES M_Genders(id)
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

DROP TABLE IF EXISTS `T_DailySalesTotals`;
CREATE TABLE T_DailySalesTotals (
  `id`                VARCHAR(36) PRIMARY KEY NOT NULL DEFAULT (UUID()),            -- 売上集計ID
  `sale_date`         TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 売上日時
  `product_id`        VARCHAR(36) NOT NULL,                -- 商品ID
  `total_quantity`    INT(10) NOT NULL,                 -- 商品の数量
  `total_amount`      DECIMAL(10, 2) NOT NULL,           -- 合計金額
  `total_tax_amount`  DECIMAL(10, 2) NOT NULL,             -- 税額
  `created_at`        TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- レコード作成日時
  `updated_at`        TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 更新日時
  FOREIGN KEY (product_id) REFERENCES M_Products(id)  -- 商品IDと対応するテーブル
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

DROP TABLE IF EXISTS `T_Sales`;
CREATE TABLE T_Sales (
  `id`                VARCHAR(36) PRIMARY KEY NOT NULL DEFAULT (UUID()),              -- 売上ID
  `sale_date`         TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 売上日時
  `user_id`           VARCHAR(36) NOT NULL,                   -- ユーザーID（顧客ID等）
  `product_id`        VARCHAR(36) NOT NULL,                -- 商品ID
  `quantity`          INT(5) NOT NULL,                       -- 商品の数量
  `unit_amount`       DECIMAL(10, 2) NOT NULL,            -- 商品単価
  `total_amount`      DECIMAL(10, 2) NOT NULL,           -- 合計金額
  `tax_amount`        DECIMAL(10, 2) NOT NULL,             -- 税額
  `payment_method_id` INT(2) NOT NULL,              -- 支払い方法ID
  `status`            VARCHAR(20) NOT NULL,                    -- 売上状態（完了、キャンセルなど）
  `store_id`          INT(1) NOT NULL DEFAULT(1),            -- 店舗ID
  `created_at`        TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- レコード作成日時
  `updated_at`        TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 更新日時
  FOREIGN KEY (user_id) REFERENCES M_Users(id),     -- ユーザーIDと対応するテーブル（例：`users` テーブル）
  FOREIGN KEY (product_id) REFERENCES M_Products(id)  -- 商品IDと対応するテーブル（例：`products` テーブル）
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;