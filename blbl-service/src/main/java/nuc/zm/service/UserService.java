package nuc.zm.service;

import com.mysql.cj.util.StringUtils;
import jdk.nashorn.internal.parser.Token;
import nuc.zm.Constant.UserConstant;
import nuc.zm.exception.ConditonException;
import nuc.zm.mapper.UserMapper;
import nuc.zm.pojo.User;
import nuc.zm.pojo.UserInfo;
import nuc.zm.utils.MD5Util;
import nuc.zm.utils.RSAUtil;
import nuc.zm.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 用户服务
 *
 * @author zm
 * @date 2022/10/24
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;


    public void addUser(User user) {
        String phone = user.getPhone();
        if (StringUtils.isNullOrEmpty(phone)) {
            throw new ConditonException("手机号不能为空");
        }
        User userByPhone = getUserByPhone(phone);
        if (userByPhone != null) {
            throw new ConditonException("改手机号已经被注册");
        }
        Date now = new Date();
        String salt = String.valueOf(now.getTime());
        String password = user.getPassword();
        try {
            RSAUtil.decrypt(password);
        } catch (Exception e) {
            throw new ConditonException("密码解密失败");
        }
        String sign = MD5Util.sign(password, salt, "utf-8");
        user.setSalt(salt);
        user.setPassword(sign);
        user.setCreateTime(now);
        user.setUpdateTime(now);
        userMapper.addUser(user);
        // 添加用户信息
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(user.getId());
        userInfo.setNick(UserConstant.DEFAULT_NIKC);
        userInfo.setBrith(UserConstant.DEFAULT_BIRTH);
        userInfo.setGender(UserConstant.GENDER_UNKNOW);
        userInfo.setCreateTime(now);
        userInfo.setUpdateTime(now);
        userMapper.addUserInfo(userInfo);
    }

    private User getUserByPhone(String phone) {
        return userMapper.getUserByPhone();
    }

    public String login(User user) {
        String phone = user.getPhone();
        if (StringUtils.isNullOrEmpty(phone)) {
            throw new ConditonException("手机号不能为空");
        }
        User userByPhone = getUserByPhone(phone);
        if (userByPhone == null) {
            throw new ConditonException("该用户不存在~");
        }
        String password = user.getPassword();

        try {
           password = RSAUtil.decrypt(password);
        } catch (Exception e) {
            throw new ConditonException("解密失败");
        }

        String salt = userByPhone.getSalt();
        password = MD5Util.sign(password,salt,"utf-8");
        if (password.equals(userByPhone.getPassword())) {
            throw new ConditonException("密码错误");
        }
       return TokenUtil.generateToken(userByPhone.getId());

    }
}
