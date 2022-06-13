package org.demo.lab;

public class subString {
    public static void main(String[] args) {

        String email = "jyyoun1022@naver.com";

        String str = email.substring(0, email.indexOf("@"));
        System.out.println(str);
    }

}
