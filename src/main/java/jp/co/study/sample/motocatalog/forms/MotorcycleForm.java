package jp.co.study.sample.motocatalog.forms;

import java.time.LocalDateTime;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @NotBlank
    @Size(min = 1, max = 50)
    private String motorcycleName;
    // シート高
    @Min(0)
    @Max(1000)
    private Integer seatHeight;
    @Min(1)
    @Max(4)
    // シリンダー
    private Integer cylinders;
    // 冷却
    @Size(max = 10)
    private String cooling;
    // 価格
    @Min(100000)
    private Integer price;
    // コメント
    @Size(max = 200)
    private String comment;
    // ブランド
    @Valid
    private Brand brand;
    // バージョン
    private Integer version;
    // 登録日時
    private LocalDateTime insertDate;
    // 更新日時
    private LocalDateTime updateDate;
}
