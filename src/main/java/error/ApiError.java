
package error;

import com.google.gson.annotations.SerializedName;

public class ApiError {

    public String message;
    @SerializedName("cod")
    private String code;

    public ApiError(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public ApiError() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ApiError(String message) {
        this.message = message;
    }

    public boolean hasErrorMsg() {
        return !message.isEmpty();
    }
}