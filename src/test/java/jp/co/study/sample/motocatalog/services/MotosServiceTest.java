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

import jp.co.study.sample.motocatalog.model.Motorcycle;
import jp.co.study.sample.motocatalog.model.SearchCondition;
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
        SearchCondition condition = new SearchCondition();
        condition.setBrandId(brandId);

        List<Motorcycle> motorcycleList = service.getMotorcycleList(condition); // テスト対象

        assertThat(motorcycleList.size()).isGreaterThanOrEqualTo(1);
        motorcycleList.stream()
                .forEach(motorcycle -> assertThat(motorcycle.getBrand().getBrandName()).isEqualTo(brandName));
    }

    @DisplayName("バイク一覧取得済み 条件： ブランドIDの該当なし")
    @Test
    void test002() {
        SearchCondition condition = new SearchCondition();
        condition.setBrandId("99");

        List<Motorcycle> motorcycleList = service.getMotorcycleList(condition); // テスト対象

        assertThat(motorcycleList.size()).isEqualTo(0);
    }

    @DisplayName("バイク一覧取得済み 条件： バイク名完全一致")
    @ParameterizedTest
    @CsvSource({ "Z900RS", "Rebel250", "Z900RS CAFE", "Ninja ZX-25R" })
    void test003(String motorcycleName) {
        SearchCondition condition = new SearchCondition();
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
        SearchCondition condition = new SearchCondition();
        condition.setKeyword(keyword);

        List<Motorcycle> motorcycleList = service.getMotorcycleList(condition); // テスト対象

        assertThat(motorcycleList.size()).isGreaterThanOrEqualTo(1);
        motorcycleList.stream()
                .forEach(motorcycle -> assertThat(motorcycle.getMotorcycleName()).isEqualTo(motorcycleName));
    }

    @DisplayName("バイク一覧取得済み 条件： バイク名の該当なし")
    @Test
    void test005() {
        SearchCondition condition = new SearchCondition();
        condition.setKeyword("aaa");

        List<Motorcycle> motorcycleList = service.getMotorcycleList(condition); // テスト対象

        assertThat(motorcycleList.size()).isGreaterThanOrEqualTo(0);
    }

    @DisplayName("バイク一覧取得済み 条件： ブランドID、バイク名完全一致")
    @ParameterizedTest
    @CsvSource({ "02, W800 CAFE", "01, Rebel250", })
    void test006(String brandId, String motorcycleName) {
        SearchCondition condition = new SearchCondition();
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
        SearchCondition condition = new SearchCondition();
        condition.setBrandId(brandId);
        condition.setKeyword(motorcycleName);

        List<Motorcycle> motorcycleList = service.getMotorcycleList(condition); // テスト対象

        assertThat(motorcycleList.size()).isEqualTo(0);
    }

    @DisplayName("バイク一覧取得済み 条件： なしの場合、全件該当")
    @Test
    void test008() {
        SearchCondition condition = new SearchCondition();

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
        ;
    }
}