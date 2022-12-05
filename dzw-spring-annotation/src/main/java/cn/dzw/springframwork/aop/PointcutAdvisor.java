package cn.dzw.springframwork.aop;

/**
 * @Classname PointcutAdvisor
 * @Description TODO
 * @Date 2022/11/22 17:25
 * @Created by dongzhiwei
 */
public interface PointcutAdvisor  extends Advisor{

    /**
     * 获取point cut
     * @return
     */
    Pointcut getPointCut();


}
