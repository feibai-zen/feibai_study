package com.feibai.study.demos.good_practice.pageresult;

public class Result<T> {

  private int code;
  private String msg;
  private T data;

  public static <T> Result<T> error(ErrorMsg errorMsg) {
    return new Result(errorMsg);
  }

  public static <T> Result<T> success(int code, T data) {
    return new Result(code, "ok", data);
  }

  public Result(ErrorMsg errorMsg) {

    if(null == errorMsg){
      return;
    }
    this.code = errorMsg.getCode();
    this.msg = errorMsg.getMsg();
  }

  private Result(int code, String msg, T data) {
    this.code = code;
    this.msg = msg;
    this.data = data;
  }

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

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}
