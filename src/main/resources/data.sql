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
        'GB350',
        800,
        1,
        '空冷',
        550000,
        'エンジンのトコトコ音がいい。',
        '01',
        1,
        -- 「now ()」にするとエラーになるので注意！！
        now(),
        null
    ),
    (
        2,
        'Z900RS',
        800,
        4,
        '水冷',
        1260000,
        'エンジン音が低く神社的。',
        '02',
        1,
        now(),
        null
    ),
    (
        3,
        'W800 CAFE',
        790,
        2,
        '水冷',
        1100000,
        'エンジン音は思ったよりかっこいい。スポーツタイプで姿勢はしんどい。',
        '02',
        1,
        now(),
        null
    ),
    (
        4,
        'YZF-R1',
        100,
        4,
        '水冷',
        1500000,
        '見た目がかっこいい。いかにもスーパースポーツ。',
        '03',
        1,
        now(),
        null
    ),
    (
        5,
        'Rebel250',
        690,
        1,
        '水冷',
        599500,
        '見た目はクルーザー音はめっちゃ軽い。単気筒のトコトコもない。',
        '01',
        1,
        now(),
        null
    ),
    (
        6,
        'Rebel500',
        690,
        2,
        '水冷',
        799700,
        'まだよくわからない。',
        '01',
        1,
        now(),
        null
    ),
    (
        7,
        'SR400 Final Edition',
        790,
        1,
        '空冷',
        650800,
        'エンジンのドドド音が渋い。',
        '03',
        1,
        now(),
        null
    ),
    (
        8,
        'Z900RS CAFE',
        820,
        4,
        '水冷',
        1290000,
        '見た目カッコイイけど後部シート小さい。',
        '02',
        1,
        now(),
        null
    ),
    (
        9,
        'V7 III Racer 10th ANNIVERSARY',
        770,
        2,
        '空冷',
        1375000,
        '珍しい見た目。',
        '05',
        1,
        now(),
        null
    ),
    (
        10,
        'Ninja ZX-25R',
        785,
        2,
        '水冷',
        1000000,
        'スタイリッシュ。',
        '04',
        1,
        now(),
        null
    );

-- ブランドのデータ
INSERT INTO
    m_brand (brand_id, brand_name)
values
    ("01", "Honda"),
    ("02", "Kawasaki"),
    ("03", "Yamaha"),
    ("04", "Suzuki"),
    ("05", "moto guzzi");

-- ユーザーのデータ
INSERT INTO
    m_user (user_name, password)
values
    ("testUser", "$2a$10$sgSc.gASfPBQDVn5K3ljNuZOB5dr6H6WTVTlv40vrn8Sa3ssq20xW"),
    ("aaa", "$2a$10$sgSc.gASfPBQDVn5K3ljNuZOB5dr6H6WTVTlv40vrn8Sa3ssq20xW");    