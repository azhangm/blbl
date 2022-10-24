package nuc.zm.config;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;
import java.util.List;

/**
 * json转换配置http消息
 *
 * @author zm
 * @date 2022/10/23
 */
@Configuration
public class JsonHttpMessageConvertConfig {

//    自定义 json 转换
    @Bean
    @Primary
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setDateFormat("yyyy-MM-DD HH:mm:ss");
        fastJsonConfig.setSerializerFeatures(
//                json 格式化 输出
                SerializerFeature.PrettyFormat,
//                返回空字段。
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty,
//               按照升序排序
                SerializerFeature.MapSortField,
//              禁用循环 引用 （数据转换 一个很重要的概念） 不关闭的 化 所有展现一次 并不是前端想要的。
//                fastjson  一定要关这个
                SerializerFeature.DisableCircularReferenceDetect
        );
         fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        return new HttpMessageConverters(fastJsonHttpMessageConverter);
    }

    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();
        Object o = new Object();
        list.add(o);
        list.add(o);
        System.out.println(list.size());
        System.out.println(JSONObject.toJSONString(list));
        System.out.println(JSONObject.toJSONString(list,SerializerFeature.DisableCircularReferenceDetect));
    }

}
