package nuc.zm.pojo;

/**
 * json响应
 *
 * @author zm
 * @date 2022/10/23
 */
public class JsonResponse<T> {

    private String code;

    private String msg;

    private T data;


    public JsonResponse(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public JsonResponse( T data) {
        code = "0";
        msg = "success";
        this.data = data;
    }

//    不需要数据 但请求成功的信息
    public static JsonResponse<String> success () {
        return new JsonResponse<>(null);
    }
//    需要给前端返回参数。
    public static JsonResponse<String> success(String data) {
        return new JsonResponse<>(data);
    }

//    适用于 固定失败信息 不需要定制化的
    public static JsonResponse<String> fail() {
        return new JsonResponse<>("1","failed");
    }

//        适用于 失败 提示信息 和 特定状态码
    public static JsonResponse<String> fail(String code,String msg) {
        return new JsonResponse<>(code,msg);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
