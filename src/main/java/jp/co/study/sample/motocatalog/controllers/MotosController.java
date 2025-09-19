package jp.co.study.sample.motocatalog.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.study.sample.model.Brand;
import jp.co.study.sample.model.Motorcycle;

@Controller
public class MotosController {

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

        return "moto_list";
    }
}
