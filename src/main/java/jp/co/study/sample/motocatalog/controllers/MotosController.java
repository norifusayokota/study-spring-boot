package jp.co.study.sample.motocatalog.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.study.sample.motocatalog.model.Brand;
import jp.co.study.sample.motocatalog.model.Motorcycle;
import jp.co.study.sample.motocatalog.model.SearchCondition;
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

    @GetMapping("/motos")
    public String motos(Model model) {
        // ブランド
        List<Brand> brands = new ArrayList<>();
        brands = motosService.getBrand();

        // バイク
        List<Motorcycle> motorcycles = new ArrayList<>();
        SearchCondition condition = new SearchCondition();
        motorcycles = motosService.getMotorcycleList(condition);

        model.addAttribute("brands", brands);
        model.addAttribute("motorcycles", motorcycles);

        // @Slf4jアノテーションのおかげでlogという変数名を使用することができる
        log.debug("motorcycles: {}", motorcycles);

        return "moto_list";
    }
}
