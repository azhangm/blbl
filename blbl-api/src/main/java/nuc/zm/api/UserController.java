package nuc.zm.api;

import nuc.zm.pojo.JsonResponse;
import nuc.zm.pojo.User;
import nuc.zm.service.UserService;
import nuc.zm.utils.RSAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public JsonResponse<String> login(@RequestBody User user) {
        String login = userService.login(user);
        return JsonResponse.success(login);
    }
}
