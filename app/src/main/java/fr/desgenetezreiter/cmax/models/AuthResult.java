package fr.desgenetezreiter.cmax.models;

import java.util.Arrays;

public class AuthResult {
    public final static int LOGIN = 0;
    public final static int REGISTER = 1;

    public int type;
    public boolean success;

    public String token;
    public long expires;
    public UserModel user;

    public int resCode;
    public String code;
    public String message;
    public String[] fields;

    public AuthResult(){

    }

    public AuthResult(int type, int resCode) {
        this.type = type;
        this.resCode = resCode;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpires() {
        return expires;
    }

    public void setExpires(long expires) {
        this.expires = expires;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public int getResCode() {
        return resCode;
    }

    public void setResCode(int resCode) {
        this.resCode = resCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String[] getFields() {
        return fields;
    }

    public void setFields(String[] fields) {
        this.fields = fields;
    }

    @Override
    public String toString() {
        return "AuthResult{" +
                "type=" + type +
                ", success=" + success +
                ", token='" + token + '\'' +
                ", expires=" + expires +
                ", user=" + user +
                ", resCode=" + resCode +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", fields=" + Arrays.toString(fields) +
                '}';
    }
}
