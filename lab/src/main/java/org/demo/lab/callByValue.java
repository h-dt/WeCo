package org.demo.lab;

import net.minidev.json.JSONUtil;


class A{
    public int value;

    public A(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return
                "value=" + value;
    }
}

public class callByValue {

    public static void main(String[] args) {
        A a1 = new A(1);
        A a2 = new A(2);

        run(a1,a2);
        System.out.println("a1 : "+a1);
        System.out.println("a2 : "+a2);
    }
    public static void run(A arg1,A arg2){
        arg1.value = 111;
        arg2 = new A(20);
    }


}

