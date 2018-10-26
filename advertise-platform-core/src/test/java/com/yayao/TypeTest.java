package com.yayao;

import com.yayao.util.HttpClientUtil;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.function.Function;

public class TypeTest {
     void callback(int i){
         System.out.println("origin callback"+i);
     };
    void range(int number1, int number, int step, Callback cc){
        for (int i=number1;i<number;i+=step){
            cc.callback(i);
        }
    }
    public static void main(String[] args) throws Exception {
        TypeTest tt = new TypeTest();
        tt.range(2, 10, 2, new Callback() {
            @Override
            public void callback(int i) {
                System.out.println(i);
            }
        });

        //String ss = HttpClientUtil.doGet("https://mp.weixin.qq.com/s/YmCdeP2nV0573FakwOaxfw");
        //System.out.println(ss);

        Integer i1=new Integer(3);
        Integer i2=3;
        Integer i3=new Integer(3);
        Integer i4=Integer.valueOf(3);
        System.out.println(i1>=i2);
        System.out.println(i1==i3);
        System.out.println(i1==i4);
        System.out.println(i2==i4);
    }
}
interface  Callback{
    void callback(int i);
}
