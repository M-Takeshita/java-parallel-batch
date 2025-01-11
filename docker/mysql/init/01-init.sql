USE PBD;

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