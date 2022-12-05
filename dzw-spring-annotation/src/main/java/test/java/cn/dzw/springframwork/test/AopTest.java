package test.java.cn.dzw.springframwork.test;

import cn.dzw.springframwork.aop.AdvisedSupport;
import cn.dzw.springframwork.aop.MethodMatcher;
import cn.dzw.springframwork.aop.TargetSource;
import cn.dzw.springframwork.aop.aspectj.AspectJExpressionPointcut;
import cn.dzw.springframwork.aop.framework.Cglib2AopProxy;
import cn.dzw.springframwork.aop.framework.JdkDynamicAopProxy;
import cn.dzw.springframwork.aop.framework.ReflectiveMethodInvocation;
import cn.dzw.springframwork.context.support.ClassPathXmlApplicationContext;
import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Test;
import test.java.cn.dzw.springframwork.test.aop.MyMethodInterceptor;
import test.java.cn.dzw.springframwork.test.bean.IPeopleService;
import test.java.cn.dzw.springframwork.test.bean.PeopleService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Classname AopTest
 * @Description aop 的测试类
 * @Date 2022/11/5 11:26
 * @Created by dongzhiwei
 */
public class AopTest {


    /**
     * aop 的核心逻辑
     */
    @Test
    public void test_aop() {
        Object peopleService = new PeopleService();
        IPeopleService o = (IPeopleService)Proxy.newProxyInstance(getClass().getClassLoader(), peopleService.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //根据规则获取匹配器
                MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* test.java.cn.dzw.springframwork.test.bean.IPeopleService.*(..))");
                //如果方法是在上面的规则中的 就进去
                if (methodMatcher.methodMatcher(method,peopleService.getClass())) {
//                    method.invoke(peopleService, args);
                    //这里为什么不直接 method.invoke  而是用方法拦截器  方便扩展吧  我认为的是分两步 将逻辑抽象到 拦截器中 反射只用 invoke 就行了
                    MethodInterceptor methodInterceptor = invocation -> {
                        long start = System.currentTimeMillis();
                        try {
                            return invocation.proceed();
                        } finally {
                            System.out.println("监控 - Begin By AOP");
                            System.out.println("方法名称："+ invocation.getMethod().getName());
                            System.out.println("方法耗时：" +(System.currentTimeMillis() - start));
                            System.out.println("监控 - End\r\n");
                        }
                    };

                    methodInterceptor.invoke(new ReflectiveMethodInvocation(peopleService, method, args));
                }
                return method.invoke(peopleService,args);
            }
        });
        System.out.println(o.getUserName());
    }


    /**
     * 自己封装参数 然后调用 代理类进行代理
     */
    @Test
    public void test2() {
        // 构建 AdvisedSupport
        IPeopleService service = new PeopleService();
        AdvisedSupport support = new AdvisedSupport();
        support.setTargetSource(new TargetSource(service));
        support.setMethodInterceptor(new MyMethodInterceptor());
        support.setMethodMatcher(new AspectJExpressionPointcut("execution(* test.java.cn.dzw.springframwork.test.bean.IPeopleService.*(..))"));

        IPeopleService jdkProxy = (IPeopleService)new JdkDynamicAopProxy(support).getProxy();
        System.out.println("jdk代理结果："+jdkProxy.queryUserInfo());
        IPeopleService proxy = (IPeopleService)new Cglib2AopProxy(support).getProxy();
        System.out.println("cg代理结果："+proxy.queryUserInfo());
    }

    /**
     * 用aop 代理类
     */
    @Test
    public void test3() {
        // 构建 AdvisedSupport
        IPeopleService service = new PeopleService();
        AdvisedSupport support = new AdvisedSupport();
        // 三个属性 表明 对什么规则的 什么对象 进行怎么样的代理
        support.setTargetSource(new TargetSource(service));
        support.setMethodInterceptor(new MyMethodInterceptor());
        support.setMethodMatcher(new AspectJExpressionPointcut("execution(* test.java.cn.dzw.springframwork.test.bean.IPeopleService.*(..))"));

        IPeopleService jdkProxy = (IPeopleService)new JdkDynamicAopProxy(support).getProxy();
        System.out.println("jdk代理结果："+jdkProxy.queryUserInfo());
        IPeopleService proxy = (IPeopleService)new Cglib2AopProxy(support).getProxy();
        System.out.println("cg代理结果："+proxy.queryUserInfo());
    }

    /**
     * xml 形式用 aop   整合进spring# createBean # resolveBeforeInstantiation
     */
    @Test
    public void test4() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:springAop.xml");
        IPeopleService peopleService = applicationContext.getBean("peopleService", IPeopleService.class);
        peopleService.queryUserInfo();
    }






}
