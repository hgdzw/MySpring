package cn.dzw.springframwork.aop;

import org.aopalliance.aop.Advice;

/**
 * @Classname Advisor
 * @Description 定义访问者
         * Advisor 承担了 Pointcut 和 Advice 的组合，Pointcut 用于获取 JoinPoint，而
         * Advice 决定于 JoinPoint 执行什么操作。
 * @Date 2022/11/22 17:17
 * @Created by dongzhiwei
 */
public interface Advisor {

    Advice getAdvice();
}
