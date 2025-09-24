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

-- バイクのデータ
INSERT INTO
    m_motorcycle (
        motorcycle_no,
        motorcycle_name,
        seat_height,
        cylinders,
        cooling,
        price,
        comment,
        brand_id,
        version,
        insert_date,
        update_date
    )
values
    (
        1,
        "GB350",
        760,
        2,
        "空冷",
        550000,
        "ホンダの人気モデル",
        "01",
        1,
        null,
        null
    ),
    (
        2,
        "GSX250R",
        790,
        1,
        "水冷",
        600000,
        "スズキの人気モデル",
        "02",
        1,
        null,
        null
    ),
    (
        3,
        "MT-25",
        780,
        2,
        "水冷",
        650000,
        "ヤマハの人気モデル",
        "03",
        1,
        null,
        null
    ),
    (
        4,
        "Ninja ZX-25R",
        785,
        4,
        "水冷",
        1000000,
        "カワサキの人気モデル",
        "04",
        1,
        null,
        null
    )