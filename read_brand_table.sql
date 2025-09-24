-- ブランドマスタのテーブル作成
CREATE TABLE
    m_brand (
        brand_id VARCHAR(2) NOT NULL PRIMARY KEY COMMENT 'ブランドID',
        brand_name VARCHAR(20) COMMENT 'ブランド名'
    ) COMMENT 'ブランドマスタ';

-- ブランドのデータ
INSERT INTO
    m_brand (brand_id, brand_name)
values
    ("01", "HONDA"),
    ("02", "SUZUKI"),
    ("03", "YAMAHA"),
    ("04", "KAWASAKI");