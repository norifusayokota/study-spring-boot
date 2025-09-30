package jp.co.study.sample.motocatalog.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import jp.co.study.sample.motocatalog.model.Brand;
import jp.co.study.sample.motocatalog.model.Motorcycle;
import jp.co.study.sample.motocatalog.model.SearchForm;
import jp.co.study.sample.motocatalog.sevices.MotosService;

@SpringBootTest
public class MotosServiceTest {

    // ※処理の時間差でassertFailになるため、分までをフォーマットする
    DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

    @Autowired
    MotosService service;

    @DisplayName("バイク一覧取得済み 条件： ブランドID")
    @ParameterizedTest
    @CsvSource({ "01, Honda", "02, Kawasaki", "03, Yamaha", "04, Suzuki", "05, moto guzzi" })
    void test001(String brandId, String brandName) {
        SearchForm condition = new SearchForm();
        condition.setBrandId(brandId);

        List<Motorcycle> motorcycleList = service.getMotorcycleList(condition); // テスト対象

        assertThat(motorcycleList.size()).isGreaterThanOrEqualTo(1);
        motorcycleList.stream()
                .forEach(motorcycle -> assertThat(motorcycle.getBrand().getBrandName()).isEqualTo(brandName));
    }

    @DisplayName("バイク一覧取得済み 条件： ブランドIDの該当なし")
    @Test
    void test002() {
        SearchForm condition = new SearchForm();
        condition.setBrandId("99");

        List<Motorcycle> motorcycleList = service.getMotorcycleList(condition); // テスト対象

        assertThat(motorcycleList.size()).isEqualTo(0);
    }

    @DisplayName("バイク一覧取得済み 条件： バイク名完全一致")
    @ParameterizedTest
    @CsvSource({ "Z900RS", "Rebel250", "Z900RS CAFE", "Ninja ZX-25R" })
    void test003(String motorcycleName) {
        SearchForm condition = new SearchForm();
        condition.setKeyword(motorcycleName);

        List<Motorcycle> motorcycleList = service.getMotorcycleList(condition); // テスト対象

        assertThat(motorcycleList.size()).isGreaterThanOrEqualTo(1);
        motorcycleList.stream()
                .forEach(motorcycle -> assertThat(motorcycle.getMotorcycleName()).isEqualTo(motorcycleName));
    }

    @DisplayName("バイク一覧取得済み 条件： バイク名の「前方一致」「後方一致」「部分一致」")
    @ParameterizedTest
    @CsvSource({ "W80, W800 CAFE", "el250, Rebel250", "0RS CA, Z900RS CAFE" })
    void test004(String keyword, String motorcycleName) {
        SearchForm condition = new SearchForm();
        condition.setKeyword(keyword);

        List<Motorcycle> motorcycleList = service.getMotorcycleList(condition); // テスト対象

        assertThat(motorcycleList.size()).isGreaterThanOrEqualTo(1);
        motorcycleList.stream()
                .forEach(motorcycle -> assertThat(motorcycle.getMotorcycleName()).isEqualTo(motorcycleName));
    }

    @DisplayName("バイク一覧取得済み 条件： バイク名の該当なし")
    @Test
    void test005() {
        SearchForm condition = new SearchForm();
        condition.setKeyword("aaa");

        List<Motorcycle> motorcycleList = service.getMotorcycleList(condition); // テスト対象

        assertThat(motorcycleList.size()).isGreaterThanOrEqualTo(0);
    }

    @DisplayName("バイク一覧取得済み 条件： ブランドID、バイク名完全一致")
    @ParameterizedTest
    @CsvSource({ "02, W800 CAFE", "01, Rebel250", })
    void test006(String brandId, String motorcycleName) {
        SearchForm condition = new SearchForm();
        condition.setBrandId(brandId);
        condition.setKeyword(motorcycleName);

        List<Motorcycle> motorcycleList = service.getMotorcycleList(condition); // テスト対象

        assertThat(motorcycleList.size()).isGreaterThanOrEqualTo(1);
        motorcycleList.stream()
                .forEach(motorcycle -> {
                    assertThat(motorcycle.getBrand().getBrandId()).isEqualTo(brandId);
                    assertThat(motorcycle.getMotorcycleName()).isEqualTo(motorcycleName);
                });
    }

    @DisplayName("バイク一覧取得済み 条件： ブランドID、バイク名の該当なし")
    @ParameterizedTest
    @CsvSource({ "aa, bb" })
    void test007(String brandId, String motorcycleName) {
        SearchForm condition = new SearchForm();
        condition.setBrandId(brandId);
        condition.setKeyword(motorcycleName);

        List<Motorcycle> motorcycleList = service.getMotorcycleList(condition); // テスト対象

        assertThat(motorcycleList.size()).isEqualTo(0);
    }

    @DisplayName("バイク一覧取得済み 条件： なしの場合、全件該当")
    @Test
    void test008() {
        SearchForm condition = new SearchForm();

        List<Motorcycle> motorcycleList = service.getMotorcycleList(condition); // テスト対象

        assertThat(motorcycleList.size()).isEqualTo(10);
    }

    @DisplayName("バイク一覧取得済み 条件： バイクの番号")
    @ParameterizedTest
    @CsvSource({ "1, GB350", "5, Rebel250", "8, Z900RS CAFE" })
    void test009(int motorcycleNo, String motorcycleName) {
        Motorcycle motorcycle = service.getMotorcycle(motorcycleNo); // テスト対象

        assertThat(motorcycle.getMotorcycleName()).isEqualTo(motorcycleName);
    }

    @DisplayName("バイク一覧取得済み 条件： バイクの番号に従った全項目の確認")
    @Test
    void test010() {
        Motorcycle motorcycle = service.getMotorcycle(1); // テスト対象

        assertThat(motorcycle.getMotorcycleNo()).isEqualTo(1);
        assertThat(motorcycle.getMotorcycleName()).isEqualTo("GB350");
        assertThat(motorcycle.getSeatHeight()).isEqualTo(800);
        assertThat(motorcycle.getCylinders()).isEqualTo(1);
        assertThat(motorcycle.getCooling()).isEqualTo("空冷");
        assertThat(motorcycle.getPrice()).isEqualTo(550000);
        assertThat(motorcycle.getComment()).isEqualTo("エンジンのトコトコ音がいい。");
        assertThat(motorcycle.getBrand().getBrandId()).isEqualTo("01");
        assertThat(motorcycle.getVersion()).isEqualTo(1);
        assertThat(motorcycle.getInsertDate().format(dtFormatter)).isEqualTo(LocalDateTime.now().format(dtFormatter));
        assertThat(motorcycle.getUpdateDate()).isNull();
    }

    @DisplayName("バイク情報の更新")
    @ParameterizedTest
    @CsvSource({ "バイク情報の更新のための名前" })
    @Transactional // トランザクション内でテストが実行され、デフォルトでは、テストの完了後に自動的にロールバックされるようになる（SQLのデータが下記のテストで書き換わったままになることを防ぐことができる）
    @Rollback
    void test011(String updateoMtorcycleName) {
        Motorcycle before = service.getMotorcycle(1);
        before.setMotorcycleName(updateoMtorcycleName);

        service.save(before); // 更新（保存）

        Motorcycle after = service.getMotorcycle(1); // 変更後のバイク情報の取得

        assertThat(after.getMotorcycleName()).isEqualTo(updateoMtorcycleName);
        assertThat(after.getVersion()).isEqualTo(before.getVersion() + 1);
    }

    @DisplayName("バイク情報の登録")
    @Test
    @Transactional
    @Rollback
    void test012() {
        Motorcycle before = new Motorcycle();

        before.setMotorcycleName("登録テスト");
        before.setSeatHeight(999);
        before.setCylinders(9);
        before.setCooling("テスト");
        before.setPrice(9999999);
        before.setComment("登録テスト、登録テスト、登録テスト。");
        before.setBrand(new Brand("01", "Honda"));
        before.setVersion(1);
        before.setInsertDate(LocalDateTime.now());

        service.save(before); // 登録（保存）

        Motorcycle after = service.getMotorcycle(11); // 登録後のバイク情報の取得

        assertThat(after.getMotorcycleNo()).isEqualTo(11);
        assertThat(after.getMotorcycleName()).isEqualTo("登録テスト");
        assertThat(after.getSeatHeight()).isEqualTo(999);
        assertThat(after.getCylinders()).isEqualTo(9);
        assertThat(after.getCooling()).isEqualTo("テスト");
        assertThat(after.getPrice()).isEqualTo(9999999);
        assertThat(after.getComment()).isEqualTo("登録テスト、登録テスト、登録テスト。");
        assertThat(after.getBrand().getBrandId()).isEqualTo("01");
        assertThat(after.getVersion()).isEqualTo(1);
        assertThat(after.getInsertDate().format(dtFormatter)).isEqualTo(LocalDateTime.now().format(dtFormatter));
        assertThat(after.getUpdateDate()).isNull();
    }

    @DisplayName("バイク情報の削除")
    @Test
    @Transactional
    @Rollback
    void test013() {
        Motorcycle before = service.getMotorcycle(1);

        service.delete(before); // 削除

        Motorcycle after = service.getMotorcycle(1); // 削除後のバイク情報の取得

        assertThat(after).isNull();
    }
}