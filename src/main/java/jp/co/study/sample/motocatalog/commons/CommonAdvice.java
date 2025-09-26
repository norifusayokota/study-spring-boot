package jp.co.study.sample.motocatalog.commons;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * リクエストの空白文字をnullに変換する共通処理
 * →このコードによって、全てのリクエストパラメータのString型フィールドに対して、空白だけの値がnullに変換されるようになる
 */
@ControllerAdvice // 全ての @Controller に対して共通の処理を提供する
public class CommonAdvice {

    @InitBinder // リクエストパラメータをJavaオブジェクトにバインドする際の前処理を定義し、このメソッド内でWebDataBinderに対してカスタムエディタを登録することで、特定の型（ここではString）に対する変換ルールを定義できる
    public void initBinder(WebDataBinder binder) {
        /*
         * registerCustomEditor
         * →SpringのWebDataBinderクラスのメソッドで、特定の型に対してカスタムな変換処理（プロパティエディタ）を登録するためのもの
         * 
         * StringTrimmerEditor(true)
         * →Springが提供するクラスで、文字列の前後の空白をトリム（削除）し、引数をtrueにすることで、空文字列（""）を null に変換する
         */
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
}
