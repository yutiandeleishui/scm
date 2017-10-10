package com.banggood.scm.message;

import com.banggood.scm.enums.ResulEnum;

/**
 * Created by Administrator on 2017/8/29.
 * 用于数据结果集返回操作
 */
public class ResultInfo {

    // 操作结果
    private ResulEnum resultCode;

    // 错误信息
    private String errorInfo;

    // 附属对象
    private Object object;

    public ResultInfo() {}
    public ResultInfo( Object object) {
        super();
        this.resultCode = ResulEnum.SUCCESS;
        this.errorInfo="";
        this.object = object;
    }

    public ResultInfo(String errorInfo) {
        super();
        this.resultCode = ResulEnum.FAIL;
        this.errorInfo = errorInfo;
        this.object = null;
    }

    public ResultInfo(ResulEnum resultCode, String errorInfo) {
        super();
        this.resultCode = resultCode;
        this.errorInfo = errorInfo;
    }

    public ResultInfo(ResulEnum resultCode, String errorInfo, Object object) {
        super();
        this.resultCode = resultCode;
        this.errorInfo = errorInfo;
        this.object = object;
    }

    public ResulEnum getResultCode() {
        return resultCode;
    }

    public void setResultCode(ResulEnum resultCode) {
        this.resultCode = resultCode;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "ResultInfo{" +
                "resultCode=" + resultCode +
                ", errorInfo='" + errorInfo + '\'' +
                ", object=" + object +
                '}';
    }
}
