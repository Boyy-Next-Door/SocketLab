package TCP_chat_room.model.protocol;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * ������Ϣ������
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
     * ret=0����ʧ�ܣ�ԭ��δ֪��
     * 1����ɹ�
     * 2û��Ȩ��
     * 3û������
     * 4�ύ�����쳣
     * 5����
     * 8�ʺ��ѱ�ע��
     */
    public static final int ERROR_CODE_UNKNOWN = 0;
    public static final int ERROR_CODE_SUCCESS = 1;
    public static final int ERROR_CODE_UNAUTHORIZED = 2;
    public static final int ERROR_CODE_NO_DATA = 3;
    public static final int ERROR_CODE_PARAMETER_ERROR = 4;
    public static final int ERROR_CODE_NULL = 5;
    public static final int ERROR_CODE_USER_ALREADY_EXIST = 8;
    
    //�����ض��󴴽�������һ��BaseResponse
    public static <T> Response<T> createBySuccess(String message,T content){
        Response baseResponse = new Response();
        baseResponse.setMessage(message);
        baseResponse.setRet(1);
        baseResponse.setData(content);
        return  baseResponse;
    }

    //δ֪����µĴ���ͨ��message��������ԭ��
    public static Response createByErrorMessage(String message){
        Response baseResponse = new Response();
        baseResponse.setMessage(message);
        baseResponse.setRet(0);
        return  baseResponse;
    }
  //��֪����µĴ���ͨ��ret��������ԭ��
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
