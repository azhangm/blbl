package nuc.zm.exception;

import java.io.Serializable;

public class ConditonException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code;

    public ConditonException(String code,String name) {
        super(name);
        this.code = code;
    }
    public ConditonException(String name) {
        super(name);
//        常规错误处理
        this.code = "500";
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
