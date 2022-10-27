package nuc.zm.service;

import nuc.zm.mapper.UserMapper;
import nuc.zm.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication

class UserServiceTest {

    @Autowired
    private UserMapper mapper;

    @Test
    private void test() {
        User userByPhone = mapper.getUserByPhone("13453369894");
        System.out.println(userByPhone);
    }
}