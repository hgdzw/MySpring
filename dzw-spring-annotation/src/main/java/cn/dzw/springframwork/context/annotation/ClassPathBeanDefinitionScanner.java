package cn.dzw.springframwork.context.annotation;

import cn.dzw.springframwork.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import cn.dzw.springframwork.beans.factory.config.BeanDefinition;
import cn.dzw.springframwork.beans.factory.support.BeanDefinitionRegistry;
import cn.dzw.springframwork.stereotype.Component;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;

import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * @Classname ClassPathBeanDefinitionScanner
 * @Description xml 加载 的扫描路径下 的bean 注册到 容器中
 * @Date 2022/11/23 16:59
 * @Created by dongzhiwei
 */
public class ClassPathBeanDefinitionScanner extends ClassPathScanningCandidateComponentProvider {

    private BeanDefinitionRegistry registry;

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    /**
     * 真正扫描进去的
     * @param basePackages
     */
    public void doScan(String... basePackages) {
        for (String basePackage : basePackages) {
            Set<BeanDefinition> components = findCandidateComponents(basePackage);
            for (BeanDefinition component : components) {
                String scope = resolveBeanScope(component);
                if (StrUtil.isNotEmpty(scope)) {
                    component.setScope(scope);
                }
                registry.registryBeanDefinition(determineBeanName(component), component);
            }
        }
        //这里注册一个 扫描注解的beanFactoryProcess 进去
        registry.registryBeanDefinition("internalAutowiredAnnotationProcessor",new BeanDefinition(AutowiredAnnotationBeanPostProcessor.class));
    }

    /**
     * 查看bean 的 单例还是什么
     * @param beanDefinition
     * @return
     */
    private String resolveBeanScope(BeanDefinition beanDefinition) {
        Class beanClass = beanDefinition.getBeanClass();
        Scope scope = (Scope) beanClass.getAnnotation(Scope.class);
        if (null != scope) return scope.value();
        return StrUtil.EMPTY;
    }

    /**
     * 看看bean 有没有指定名称
     * @param beanDefinition
     * @return
     */
    private String determineBeanName(BeanDefinition beanDefinition) {
        Class beanClass = beanDefinition.getBeanClass();
        Component component = (Component) beanClass.getAnnotation(Component.class);
        String value = component.value();
        if (StrUtil.isEmpty(value)) {
            value = StrUtil.lowerFirst(beanClass.getSimpleName());
        }
        return value;
    }


}
