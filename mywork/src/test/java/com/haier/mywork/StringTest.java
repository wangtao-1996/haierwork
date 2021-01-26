package com.haier.mywork;

import org.junit.jupiter.api.Test;

public class StringTest {

    @Test
    public void test1(){
        String str = "西宁小微";
        str = str.replace("小微","");
        System.out.println(str);
    }
}
