-- モーターサイクルマスタのテーブルの削除
DROP TABLE IF EXISTS m_motorcycle;

-- モーターサイクルマスタのテーブル作成
CREATE TABLE
    m_motorcycle (
        motorcycle_no INT NOT NULL PRIMARY KEY COMMENT 'バイク番号',
        motorcycle_name VARCHAR(100) COMMENT 'バイク名',
        seat_height INT COMMENT 'シート高',
        cylinders INT COMMENT 'シリンダー',
        cooling VARCHAR(20) COMMENT '冷却',
        price INT COMMENT '価格',
        comment VARCHAR(200) COMMENT 'コメント',
        brand_id VARCHAR(2) COMMENT 'ブランドID',
        version INT COMMENT 'バージョン',
        insert_date DATETIME COMMENT '登録日時',
        update_date DATETIME COMMENT '更新日時'
    ) COMMENT 'モーターサイクルマスタ';

-- ブランドマスタのテーブルの削除
DROP TABLE IF EXISTS m_brand;

-- ブランドマスタのテーブル作成
CREATE TABLE
    m_brand (
        brand_id VARCHAR(2) NOT NULL PRIMARY KEY COMMENT 'ブランドID',
        brand_name VARCHAR(20) COMMENT 'ブランド名'
    ) COMMENT 'ブランドマスタ';