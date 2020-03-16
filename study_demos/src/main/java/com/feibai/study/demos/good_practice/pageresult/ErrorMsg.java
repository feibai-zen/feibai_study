package com.feibai.study.demos.good_practice.pageresult;

public class ErrorMsg {

  private int code;
  private String msg;

  public static ErrorMsg ERROR_TYPE1 = new ErrorMsg(400100, "服务端异常");


  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  private ErrorMsg(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }
}
