package nuc.zm.service;

import nuc.zm.mapper.DemoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 演示服务
 *
 * @author zm
 * @date 2022/10/23
 */
@Service
public class DemoService {

    @Autowired
    private DemoMapper demoMapper;

    public Map<String ,Object> query(String name) {
        HashMap<String , Object> map = new HashMap<>();
        Long query = demoMapper.query(name);
        map.put("name",query);
        return map;
    }

}
