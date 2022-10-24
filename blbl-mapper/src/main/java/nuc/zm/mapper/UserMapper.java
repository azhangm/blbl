package nuc.zm.mapper;

import nuc.zm.pojo.User;
import nuc.zm.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户映射器
 *
 * @author zm
 * @date 2022/10/24
 */
@Mapper
public interface UserMapper {

    User getUserByPhone();

    Integer addUser(User user);

    Integer addUserInfo(UserInfo userInfo);

    User getUserByPassword(String password);
}
