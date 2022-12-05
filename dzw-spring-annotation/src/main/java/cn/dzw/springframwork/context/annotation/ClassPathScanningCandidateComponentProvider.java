package cn.dzw.springframwork.context.annotation;

import cn.dzw.springframwork.beans.factory.config.BeanDefinition;
import cn.dzw.springframwork.stereotype.Component;
import cn.dzw.springframwork.utils.ClassUtils;
import cn.hutool.core.util.ClassUtil;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @Classname ClassPathScanningCandidateComponentProvider
 * @Description 加载类扫描 路径提供者
 * @Date 2022/11/23 16:49
 * @Created by dongzhiwei
 */
public class ClassPathScanningCandidateComponentProvider {


    /**
     * 查询路径下面的所有带有 @Component 注解的类
     * @param basePackage
     * @return
     */
    public Set<BeanDefinition> findCandidateComponents(String basePackage){
        Set<BeanDefinition> candidates = new LinkedHashSet<>();
        Set<Class<?>> classSet = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);
        for (Class<?> aClass : classSet) {
            candidates.add(new BeanDefinition(aClass));
        }
        return candidates;
    }


}
