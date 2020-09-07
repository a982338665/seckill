package pers.lish.girl.aoplearning.dynamicproxy_cglib;

import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.proxy.Enhancer;
import pers.lish.girl.aoplearning.staticproxy.RealClass;
import pers.lish.girl.aoplearning.staticproxy.Subject;

/**
 * create by lishengbo on 2017-12-22 17:26
 * 利用继承来实现的动态代理
 * cglib
 */
public class CglibTest {
    public static void main(String[] args) {

        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\go-20191030\\master\\DOCRecord-master\\DOCRecord\\girl\\src\\main\\java\\pers\\lish\\girl\\aoplearning\\dynamicproxy_cglib");
        Enhancer enhancer=new Enhancer();
        //要代理的类
        enhancer.setSuperclass(RealClass.class);
        //代理类
        enhancer.setCallback(new DemoMethodintercepptor());
        //创建代理对象
        Subject subject= (Subject) enhancer.create();
        System.err.println(subject.getClass().getName());
        subject.request();
        subject.hello();

    }
}
