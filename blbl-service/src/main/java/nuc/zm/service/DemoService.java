package nuc.zm.service;

import nuc.zm.mapper.DemoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Long query(String name) {
        return demoMapper.query(name);
    }

}
