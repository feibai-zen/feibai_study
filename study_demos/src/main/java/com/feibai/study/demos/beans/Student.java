package com.feibai.study.demos.beans;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    private Long id;
    private String name;
    private int age;
    private String address;

}
