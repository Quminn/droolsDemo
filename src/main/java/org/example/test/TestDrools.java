package org.example.test;

import org.example.entity.Order;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;


public class TestDrools {
    @Test
    public void drltest1(){
        KieServices kss = KieServices.Factory.get();
        KieContainer kc = kss.getKieClasspathContainer();
        KieSession ks = kc.newKieSession("simpleRuleKSession");
//        Order order = new Order();
//        order.setAmount(150);
//        Integer amount = 150;
        //插入数据
        String state = "no";
        ks.insert(state);
        //配置全局变量
        StringBuilder  res = new StringBuilder();
        ks.setGlobal("res", res);
        //执行规则
        ks.fireAllRules();
        //取出全局变量
        System.out.println("结果为：" + res);
        ks.dispose();
    }
    @Test
    public void ruleStringTest() throws Exception {

        String myRule = "import org.example.entity.Order\n" +
                "\n" +
                "dialect  \"mvel\"\n" +
                "\n" +
                "rule \"age\"\n" +
                "    when\n" +
                "        $order:Order(amount > 100 && amount < 500);\n" +
                "    then\n" +
                "        $order.setScore($order.getScore() + 100);\n" +
                "        System.out.println(\"金额大于100，小于500(基于动态加载)\");\n" +
                "        System.out.println(\"积分为：\" + $order.getScore());\n" +
                "end\n";

        KieHelper helper = new KieHelper();

        helper.addContent(myRule, ResourceType.DRL);

        KieSession ksession = helper.build().newKieSession();

        Order order = new Order();
        order.setAmount(150);
        ksession.insert(order);
        ksession.fireAllRules();
        ksession.dispose();
    }
}
