package com.feibai.study.demos.optional;

import java.util.Optional;

public class OptionalDemos {

    public static void main(String[] args) {
        String a = null;

        String s = Optional.ofNullable(a).get();


    }

}
