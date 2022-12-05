package cn.dzw.springframwork.aop.framework.autoProxy;

import cn.dzw.springframwork.aop.AdvisedSupport;
import cn.dzw.springframwork.aop.Advisor;
import cn.dzw.springframwork.aop.ClassFilter;
import cn.dzw.springframwork.aop.TargetSource;
import cn.dzw.springframwork.aop.aspectj.AspectJExpressionPointcutAdvisor;
import cn.dzw.springframwork.aop.framework.ProxyFactory;
import cn.dzw.springframwork.aop.framework.adapter.MethodBeforeAdviceInterceptor;
import cn.dzw.springframwork.beans.PropertyValues;
import cn.dzw.springframwork.beans.factory.BeanFactory;
import cn.dzw.springframwork.beans.factory.BeanFactoryAware;
import cn.dzw.springframwork.beans.factory.config.InstantiationAwareBeanPostProcessor;
import cn.dzw.springframwork.beans.factory.support.DefaultListableBeanFactory;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Collection;

/**
 * @Classname DefaultAdvisorAutoProxyCreator
 * @Description 自动创建代理类, 将 aop 融入到 spring 里面的核心类,
 *          因为变相继承了 beanPostProcess 所有可以对所有的 bean 进行规则判断并代理
 * @Date 2022/11/22 16:51
 * @Created by dongzhiwei
 */
public class DefaultAdvisorAutoProxyCreator implements BeanFactoryAware, InstantiationAwareBeanPostProcessor {


    private MethodInterceptor methodInterceptor;

    private ProxyFactory proxyFactory;


    private DefaultListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }


    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) {
        return null;
    }

    /**
     * 实例化
     * @param beanClass
     * @param beanName
     * @return
     */
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) {
        if (isInfrastructureClass(beanClass)) {
            return null;
        }
        // 获取 所有的配置类
        Collection<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeansOfType(AspectJExpressionPointcutAdvisor.class).values();
        for (AspectJExpressionPointcutAdvisor advisor : advisors) {
            ClassFilter classFilter = advisor.getPointCut().getClassFilter();
            if (!classFilter.matches(beanClass)) {
                continue;
            }
            AdvisedSupport advisedSupport = new AdvisedSupport();
            TargetSource targetSource = null;
            try {
                targetSource = new TargetSource(beanClass.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
            advisedSupport.setTargetSource(targetSource);
            advisedSupport.setMethodMatcher(advisor.getPointCut().getMethodMatcher());
            //这个是怎么转的 没搞懂
            advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            advisedSupport.setProxyTargetClass(false);
            return new ProxyFactory(advisedSupport).getProxy();
        }
        return null;
    }

    private boolean isInfrastructureClass(Class<?> beanClass) {
        return Advice.class.isAssignableFrom(beanClass) || Pointcut.class.isAssignableFrom(beanClass) || Advisor.class.isAssignableFrom(beanClass);
    }

    @Override
    public Object postProcessBeforeInitialization(Object result, String beanName) {
        return result;
    }

    @Override
    public Object postProcessAfterInitialization(Object result, String beanName) {
        return result;
    }
}
