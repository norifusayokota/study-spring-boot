package jp.co.study.sample.motocatalog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MotosController {

    @RequestMapping("/test")
    public String test(@RequestParam String name, Model model) {
        // @RequestParamで指定した変数名は、URLのリクエストパラメータ名と一致させる必要がある
        // model.addAttribute()：Spring BootのコントローラーからHTMLテンプレートにデータを渡すためのメソッド
        model.addAttribute("name", name);
        return "test";
    }
}
