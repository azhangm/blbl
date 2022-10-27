package nuc.zm.api;

import nuc.zm.pojo.JsonResponse;
import nuc.zm.pojo.User;
import nuc.zm.pojo.UserInfo;
import nuc.zm.service.UserService;
import nuc.zm.support.UserSupport;
import nuc.zm.utils.RSAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户控制器
 *
 * @author zm
 * @date 2022/10/24
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserSupport userSupport;

    @GetMapping("/rsa-pks")
    public JsonResponse<String> getRsaPublicKeys() {
        String publicKeyStr = RSAUtil.getPublicKeyStr();
        return new JsonResponse<>(publicKeyStr);
    }

    @PostMapping("/users")
    public JsonResponse<String> addUser(@RequestBody User user) {
         userService.addUser(user);
         return JsonResponse.success();
    }

    @PostMapping("/user-tokens")
    public JsonResponse<String> login(@RequestBody User user) throws Exception {
        String login = userService.login(user);
        return JsonResponse.success(login);
    }

    @GetMapping("/users")
    public JsonResponse<User> getUser() {
        Long currentId = userSupport.getCurrentId();
        User u = userService.getUserInfo(currentId);
        return new JsonResponse<>(u);
    }

    @PutMapping("/update-info")
    public JsonResponse<String> updateUserInfo(@RequestBody UserInfo userInfo){
//        一般从 token 拿到 不是前端 直接传的  如果前端直接传的话 可能被 利用接口 就算被拦截 token 有 有效期
        Long currentId = userSupport.getCurrentId();
        userInfo.setUserId(currentId);
        userService.updateUserInfos(userInfo);
        return JsonResponse.success();
    }
}
