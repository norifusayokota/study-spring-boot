package jp.co.study.sample.motocatalog.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.study.sample.motocatalog.model.Brand;
import jp.co.study.sample.motocatalog.model.Motorcycle;
import jp.co.study.sample.motocatalog.model.SearchForm;
import jp.co.study.sample.motocatalog.sevices.MotosService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j // ログ部品を使えるようにする
public class MotosController {

    @Autowired
    MotosService motosService;

    // ログ部品を使えるようにする
    // private static final Logger log =
    // LoggerFactory.getLogger(MotosController.class);
    // →上記が定型的な書き方だが、Lombokの@Slf4jアノテーションをクラスに付与することで、log変数が自動生成される

    @RequestMapping("/test")
    public String test(@RequestParam String name, Model model) {
        // @RequestParamで指定した変数名は、URLのリクエストパラメータ名と一致させる必要がある
        // model.addAttribute()：Spring BootのコントローラーからHTMLテンプレートにデータを渡すためのメソッド
        model.addAttribute("name", name);
        return "test";
    }

    /**
     * バイク情報の一覧を検索する
     * 
     * @param searchForm 検索条件
     * @param model      Model
     * @return 遷移先（HTMLファイル）
     */
    @GetMapping("/motos")
    public String motos(@Validated SearchForm searchForm, BindingResult result, Model model) {
        // searchFormのフィールド名と合致しているHTMLのname属性の内容が自動的に入る
        log.info("検索内容: {}", searchForm);

        if (result.hasErrors()) {
            // 入力チェックエラーがある場合
            return "moto_list";
        }

        // ブランド一覧の準備
        this.setBrands(model);

        // バイク
        List<Motorcycle> motorcycles = new ArrayList<>();
        motorcycles = motosService.getMotorcycleList(searchForm);

        model.addAttribute("motorcycles", motorcycles);
        model.addAttribute("dateTime", LocalDateTime.now());

        // @Slf4jアノテーションのおかげでlogという変数名を使用することができる
        log.debug("motorcycles: {}", motorcycles);

        return "moto_list";
    }

    /**
     * 検索条件をクリアする
     * 
     * @param searchForm 検索条件
     * @param model      Model
     * @return 遷移先（HTMLファイル）
     */
    @GetMapping("/motos/reset")
    public String reset(SearchForm searchForm, Model model) {
        // ブランド一覧の準備
        this.setBrands(model);

        // 検索条件をクリア
        searchForm = new SearchForm();
        return "moto_list";
    }

    /**
     * ブランドの一覧をModelにセットする
     * 
     * @param model Model
     */
    private void setBrands(Model model) {
        List<Brand> brands = new ArrayList<>();
        brands = motosService.getBrand();

        model.addAttribute("brands", brands);
    }
}
