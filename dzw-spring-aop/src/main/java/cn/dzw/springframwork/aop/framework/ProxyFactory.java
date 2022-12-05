package cn.dzw.springframwork.aop.framework;

import cn.dzw.springframwork.aop.AdvisedSupport;

/**
 * @Classname ProxyFactory
 * @Description 代理工厂
 * @Date 2022/11/23 10:56
 * @Created by dongzhiwei
 */
public class ProxyFactory {


    private AdvisedSupport advisedSupport;

    public ProxyFactory(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    /**
     * 获取代理对象
     * @return
     */
    public Object getProxy() {
        return createAopProxy().getProxy();
    }

    public AopProxy createAopProxy() {
        if (advisedSupport.isProxyTargetClass()) {
            return new Cglib2AopProxy(advisedSupport);
        }
        return new JdkDynamicAopProxy(advisedSupport);
    }

}
