package nuc.zm;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * blbl应用
 * 项目入口类的编写
 * @author zm
 * @date 2022/10/23
 */
// 标注 这个应用 是一个 spirng boot 应用
@SpringBootApplication
public class BlblApp {
    public static void main(String[] args) {
        SpringApplication.run(BlblApp.class,args);
    }

}
