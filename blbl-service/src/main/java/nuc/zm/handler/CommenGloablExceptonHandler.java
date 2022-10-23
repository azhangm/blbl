package nuc.zm.handler;

import nuc.zm.exception.ConditonException;
import nuc.zm.pojo.JsonResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 参考次环球excepton处理程序
 *
 * @author zm
 * @date 2022/10/23
 */

@ControllerAdvice
// 排在第一位 所有都要过我这个
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CommenGloablExceptonHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonResponse<String> commonExceptionHandler(HttpRequest request,Exception e) {
        String msg = e.getMessage();
        if (e instanceof ConditonException) {
            String code = ((ConditonException) e).getCode();
            return new JsonResponse<>(code,msg);
        }else {
            return new JsonResponse<>("500",msg);
        }
    }
}
