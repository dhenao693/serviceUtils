package co.com.sqa.exceptions;

import static co.com.sqa.utils.constans.ConstantException.JSONTOMODELFAILURE;

public class JsonToModelException extends RuntimeException {

    public JsonToModelException(Throwable cause) {
        super(JSONTOMODELFAILURE);
    }

}
