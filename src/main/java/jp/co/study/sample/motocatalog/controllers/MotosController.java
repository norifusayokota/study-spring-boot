package jp.co.study.sample.motocatalog.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.study.sample.motocatalog.forms.MotorcycleForm;
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

        // ブランド一覧の準備
        this.setBrands(model);

        model.addAttribute("dateTime", LocalDateTime.now());

        if (result.hasErrors()) {
            // 入力チェックエラーがある場合
            return "moto_list";
        }

        // バイク
        List<Motorcycle> motorcycles = new ArrayList<>();
        motorcycles = motosService.getMotorcycleList(searchForm);

        model.addAttribute("motorcycles", motorcycles);

        // @Slf4jアノテーションのおかげでlogという変数名を使用することができる
        log.debug("motorcycles: {}", motorcycles);

        return "moto_list";
    }

    /**
     * バイク情報を更新するための初期画面
     * 
     * @param motorcycleNo   バイク番号
     * @param motorcycleForm 入力内容
     * @param model          Model
     * @return 遷移先（HTMLファイル）
     */
    @GetMapping("/motos/{motorcycleNo}")
    /*
     * @PathVariable
     * →リクエストURLのルーティング定義（@GetMapping, @PostMapping など）で {} で囲まれた部分を変数として受け取ることができる
     * 
     * @ModelAttribute
     * →リクエストのパラメータ（POST や GET）を、指定したオブジェクトのフィールドに自動的にセットする
     * →バインディング対象のクラスには、getter/setterが必要
     */
    public String initUpdate(@PathVariable int motorcycleNo, @ModelAttribute MotorcycleForm motorcycleForm, Model model) {
        // ブランド一覧の準備
        this.setBrands(model);

        // 更新日時用
        model.addAttribute("dateTime", LocalDateTime.now());

        // バイク番号に従ったバイク情報を取得
        Motorcycle motorcycle = motosService.getMotorcycle(motorcycleNo);
        // 検索結果を入力内容に詰め替える
        BeanUtils.copyProperties(motorcycle, motorcycleForm); // 同じ名前のプロパティを持つフィールドに対して、値をコピーする（第一引数を元に第二引数のフィールドにコピーする）

        return "moto";
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
     * バイク情報を更新する
     * 
     * @param motorcycleForm 入力内容
     * @param result         MotosServiceのsaveメソッドの結果
     * @return 遷移先（リダイレクト）
     */
    /*
     * BindingResult
     * →フォームの入力値をオブジェクトにバインド（結びつけ）する際に発生したバリデーションエラーや変換エラーの情報を保持するためのオブジェクト
     */
    @PostMapping("/motos/save")
    public String save(@ModelAttribute MotorcycleForm motorcycleForm, BindingResult result) {
        try {
            Motorcycle motorcycle = new Motorcycle();
            // 入力内容を詰め替える
            BeanUtils.copyProperties(motorcycleForm, motorcycle);

            // バイク情報を更新する
            motosService.save(motorcycle);

            // リダイレクト（バイク情報の一覧へ遷移）
            // 「redirect:」の後ろにすぐパスを記述しないとエラーになるので注意！！
            return "redirect:/motos";
        } catch (OptimisticLockingFailureException e) {
            result.addError(new ObjectError("global", e.getMessage()));

            return "moto";
        }
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
