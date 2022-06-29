package org.example.test;

import org.example.entity.Order;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;


public class TestDrools {
    @Test
    public void test1(){
        KieServices kss = KieServices.Factory.get();
        KieContainer kc = kss.getKieClasspathContainer();
        System.out.println("开始执行规则");
        KieSession ks = kc.newKieSession("simpleRuleKSession");
        Order order = new Order();
        order.setAmount(150);
        ks.insert(order);
        int count = ks.fireAllRules();
        System.out.println("总执行了" + count + "条规则");
        System.out.println("积分为：" + order.getScore());
        ks.dispose();
    }
}
