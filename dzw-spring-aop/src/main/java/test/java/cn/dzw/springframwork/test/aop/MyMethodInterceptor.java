package test.java.cn.dzw.springframwork.test.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @Classname MyMethodInterceptor
 * @Description 自定义的 方法拦截器
 * @Date 2022/11/9 17:12
 * @Created by dongzhiwei
 */
public class MyMethodInterceptor implements MethodInterceptor {


    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            // 正在执行方法
            return methodInvocation.proceed();
        }finally {
            System.out.println("监控 - Begin By AOP");
            System.out.println("方法名称："+ methodInvocation.getMethod().getName());
            System.out.println("方法耗时：" +(System.currentTimeMillis() - start));
            System.out.println("监控 - End\r\n");
        }
    }
}
