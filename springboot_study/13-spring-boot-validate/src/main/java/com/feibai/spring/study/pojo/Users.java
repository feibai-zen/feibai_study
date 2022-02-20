package com.feibai.spring.study.pojo;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class Users {

  /**
   * Hibernate.validator中的校验规则使用说明：
   * 1）可以为一个字段添加多个校验规则
   * 2）message为校验失败后的提示信息
   */
  @NotBlank(message = "用户名不能为空") // 非空校验，一个属性上面可以添加多个校验规则
  @Length(min = 2, max = 6, message = "最小长度为2位，最大长度为6位")
  private String name;
  @NotEmpty
  private String password;
  @Min(value = 15)
  private Integer age;
  @Email // 这里还可以通过添加正则校验的方式
  private String email;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  @Override
  public String toString() {
    return "Users [name=" + name + ", password=" + password + ", age=" + age + "]";
  }

}
