package TCP_chat_room.model.protocol;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 基本消息返回类
 */

public class Response<T> implements Serializable {
    public Response() {
        this.message="success";
        this.ret=1;
    }

    public Response(int ret) {
        this.ret = ret;
    }
    private int ret;
    private String message;
    private Object data;
    private T content;

    /**
     * ret=0请求失败（原因未知）
     * 1请求成功
     * 2没有权限
     * 3没有数据
     * 4提交参数异常
     * 5暂无
     * 8帐号已被注册
     */
    public static final int ERROR_CODE_UNKNOWN = 0;
    public static final int ERROR_CODE_SUCCESS = 1;
    public static final int ERROR_CODE_UNAUTHORIZED = 2;
    public static final int ERROR_CODE_NO_DATA = 3;
    public static final int ERROR_CODE_PARAMETER_ERROR = 4;
    public static final int ERROR_CODE_NULL = 5;
    public static final int ERROR_CODE_USER_ALREADY_EXIST = 8;
    
    //带返回对象创建并返回一个BaseResponse
    public static <T> Response<T> createBySuccess(String message,T content){
        Response baseResponse = new Response();
        baseResponse.setMessage(message);
        baseResponse.setRet(1);
        baseResponse.setData(content);
        return  baseResponse;
    }

    //未知情况下的错误，通过message描述错误原因
    public static Response createByErrorMessage(String message){
        Response baseResponse = new Response();
        baseResponse.setMessage(message);
        baseResponse.setRet(0);
        return  baseResponse;
    }
  //已知情况下的错误，通过ret描述错误原因
    public static Response createByErrorCode(Integer errorCode){
       Response baseResponse = new Response();
        switch(errorCode){
            case ERROR_CODE_UNKNOWN:{
                baseResponse.setRet(ERROR_CODE_UNKNOWN);
                baseResponse.setMessage("unknown error");
                return baseResponse;
            }
            case ERROR_CODE_SUCCESS:{
                baseResponse.setRet(ERROR_CODE_SUCCESS);
                baseResponse.setMessage("success");
                return baseResponse;
            }
            case ERROR_CODE_UNAUTHORIZED:{
                baseResponse.setRet(ERROR_CODE_UNAUTHORIZED);
                baseResponse.setMessage("unauthorized");
                return baseResponse;
            }
            case ERROR_CODE_NO_DATA:{
                baseResponse.setRet(ERROR_CODE_NO_DATA);
                baseResponse.setMessage("no data");
                return baseResponse;
            }
            case ERROR_CODE_PARAMETER_ERROR:{
                baseResponse.setRet(ERROR_CODE_PARAMETER_ERROR);
                baseResponse.setMessage("parameter error");
                return baseResponse;
            }
            case ERROR_CODE_NULL:{
                baseResponse.setRet(ERROR_CODE_NULL);
                baseResponse.setMessage("unknown error");
                return baseResponse;
            }
            case ERROR_CODE_USER_ALREADY_EXIST:{
                baseResponse.setRet(ERROR_CODE_USER_ALREADY_EXIST);
                baseResponse.setMessage("user already exist");
                return baseResponse;
            }
            default:{
                baseResponse.setRet(-1);
                baseResponse.setMessage("unknown error code");
                return baseResponse;
            }
        }
    }

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }	
    
}
