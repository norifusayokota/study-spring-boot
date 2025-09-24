package jp.co.study.sample.motocatalog.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.study.sample.motocatalog.model.Brand;
import jp.co.study.sample.motocatalog.model.Motorcycle;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j // ログ部品を使えるようにする
public class MotosController {

    // ログ部品を使えるようにする
    // private static final Logger log = LoggerFactory.getLogger(MotosController.class);
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
        brands.add(new Brand("01", "HONDA"));
        brands.add(new Brand("02", "SUZUKI"));
        brands.add(new Brand("03", "YAMAHA"));
        brands.add(new Brand("04", "KAWASAKI"));

        // バイク
        List<Motorcycle> motorcycles = new ArrayList<>();
        motorcycles.add(new Motorcycle(1, "GB350", 760, 2, "空冷", 550000, "ホンダの人気モデル", brands.get(0), 1, null, null));
        motorcycles.add(new Motorcycle(2, "GSX250R", 790, 1, "水冷", 600000, "スズキの人気モデル", brands.get(1), 1, null, null));
        motorcycles.add(new Motorcycle(3, "MT-25", 780, 2, "水冷", 650000, "ヤマハの人気モデル", brands.get(2), 1, null, null));
        motorcycles.add(
                new Motorcycle(4, "Ninja ZX-25R", 785, 4, "水冷", 1000000, "カワサキの人気モデル", brands.get(3), 1, null, null));

        model.addAttribute("brands", brands);
        model.addAttribute("motorcycles", motorcycles);

        // @Slf4jアノテーションのおかげでlogという変数名を使用することができる
        log.debug("motorcycles: {}", motorcycles);

        return "moto_list";
    }
}
