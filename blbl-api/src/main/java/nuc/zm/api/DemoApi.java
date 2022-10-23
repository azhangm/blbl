package nuc.zm.api;

import nuc.zm.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 演示api
 *
 * @author zm
 * @date 2022/10/23
 */
@RestController
public class DemoApi {

    @Autowired
    private DemoService demoService;

    @GetMapping("/query")
    public Map<String , Object> query(String  name) {
        return demoService.query(name);
    }

}
