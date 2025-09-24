package jp.co.study.sample.motocatalog.sevices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.study.sample.motocatalog.mappers.MotorcycleMapper;
import jp.co.study.sample.motocatalog.model.Motorcycle;

@Service
public class MotosService {

    /*
    SpringのDIコンテナ（アプリ起動時にSpringフレームワークが特定のオブジェクトをインスタンス化しており、そのインスタンスを保管してある場所）から、インスタンスを注入するための指定
    @Component、@Controller、@Serviceなどのアノテーションがついたクラスはコードのなかでnew()でインスタンス化することはなく、DIの仕組みによって呼び出し元クラスへ注入される
    */ 
    @Autowired
    MotorcycleMapper motorcycleMapper;

    public List<Motorcycle> getMotorcycle() {
        return motorcycleMapper.selectAll();
    }
}
