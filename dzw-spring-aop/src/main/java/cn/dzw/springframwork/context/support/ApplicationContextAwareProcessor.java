package cn.dzw.springframwork.context.support;

import cn.dzw.springframwork.beans.factory.config.BeanPostProcessor;
import cn.dzw.springframwork.context.ApplicationContext;
import cn.dzw.springframwork.context.ApplicationContextAware;

/**
 * @Classname ApplicationContextAwareProcessor
 * @Description 扩展类 来给需要applicationcontext 的用户类 设置 applicationcontext
 * @Date 2022/11/1 14:52
 * @Created by dongzhiwei
 */
public class ApplicationContextAwareProcessor  implements BeanPostProcessor {

    private ApplicationContext applicationContext;


    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object result, String beanName) {
        //如果需要的就给
        if (result instanceof ApplicationContextAware) {
            ((ApplicationContextAware) result).setApplicationContext(applicationContext);
        }
        return result;
    }

    @Override
    public Object postProcessAfterInitialization(Object result, String beanName) {
        return result;
    }

}
