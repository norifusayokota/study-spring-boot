package jp.co.study.sample.motocatalog.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jp.co.study.sample.motocatalog.model.Brand;
import jp.co.study.sample.motocatalog.model.Motorcycle;
import jp.co.study.sample.motocatalog.model.SearchCondition;
import jp.co.study.sample.motocatalog.sevices.MotosService;

@SpringBootTest
public class MotosServiceTest {

    @Autowired
    MotosService service;

    // @Test
    // void バイク情報を全件検索できる() {
    //     SearchCondition condition = new SearchCondition();
    //     List<Motorcycle> motorcycleList = service.getMotorcycle(condition);

    //     // 検索結果の件数確認
    //     assertThat(motorcycleList.size()).isEqualTo(4);

    //     // 検索結果の1件目に関して、各項目の値確認
    //     Motorcycle motorcycle = motorcycleList.get(0);
    //     assertThat(motorcycle.getMotorcycleNo()).isEqualTo(1);
    //     assertThat(motorcycle.getMotorcycleName()).isEqualTo("GB350");
    //     // assertThat(motorcycle.getSeatHeight()).isEqualTo(760); // DBから取得しなくなった項目のためコメントアウト
    //     assertThat(motorcycle.getCylinders()).isEqualTo(2);
    //     assertThat(motorcycle.getCooling()).isEqualTo("空冷");
    //     assertThat(motorcycle.getPrice()).isEqualTo(550000);
    //     assertThat(motorcycle.getComment()).isEqualTo("ホンダの人気モデル");
    //     // assertThat(motorcycle.getVersion()).isEqualTo(1); // DBから取得しなくなった項目のためコメントアウト
    //     assertThat(motorcycle.getBrand().getBrandName()).isEqualTo("HONDA");
    // }

    @Test
    void ブランド情報を全件検索できる() {
        List<Brand> brandList = service.getBrand();

        // 検索結果の件数確認
        assertThat(brandList.size()).isEqualTo(4);

        Brand brand = brandList.get(0);
        assertThat(brand.getBrandId()).isEqualTo("01");
        assertThat(brand.getBrandName()).isEqualTo("HONDA");
    }

    // バイク一覧取得済み 条件： ブランドID
    @DisplayName("バイク一覧取得済み 条件： ブランドID")
    @Test
    void test001() {
        SearchCondition condition = new SearchCondition();
        condition.setBrandId("01");

        List<Motorcycle> motorcycleList = service.getMotorcycle(condition);

        for (Motorcycle motorcycle : motorcycleList) {
            assertThat(motorcycle.getBrand().getBrandName()).isEqualTo("HONDA");
        }
    }
}
