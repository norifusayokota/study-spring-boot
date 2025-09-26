package jp.co.study.sample.motocatalog.forms;

import java.time.LocalDateTime;

import jp.co.study.sample.motocatalog.model.Brand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 更新画面のバイク情報の入力内容
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MotorcycleForm {
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
