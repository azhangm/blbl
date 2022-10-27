package nuc.zm.support;

import nuc.zm.exception.ConditonException;
import nuc.zm.utils.TokenUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户支持
 *
 * @author zm
 * @date 2022/10/24
 */
@Component
public class UserSupport {

    public Long getCurrentId() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String token = request.getHeader("token");
        Long userId = TokenUtil.verifyToken(token);
        if (userId < 0) {
            throw new ConditonException("非法用户");
        }
        return  userId;
    }

}
