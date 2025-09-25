package jp.co.study.sample.motocatalog.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * バイク
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Motorcycle {
    // バイク番号
    private Integer motorcycleNo;
    // バイク名
    private String motorcycleName;
    // シート高
    private Integer seatHeight;
    // シリンダー
    private Integer cylinders;
    // 冷却
    private String cooling;
    // 価格
    private Integer price;
    // コメント
    private String comment;
    // ブランド
    private Brand brand;
    // バージョン
    private Integer version;
    // 登録日時
    private LocalDateTime insertDate;
    // 更新日時
    private LocalDateTime updateDate;
}
