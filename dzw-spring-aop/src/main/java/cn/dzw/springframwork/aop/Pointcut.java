package cn.dzw.springframwork.aop;

/**
 * @Classname Pointcut
 * @Description 切点的顶层抽象
 * @Date 2022/11/7 16:01
 * @Created by dongzhiwei
 */
public interface Pointcut {

    /**
     * Return the ClassFilter for this pointcut.
     *
     * @return the ClassFilter (never <code>null</code>)
     */
    ClassFilter getClassFilter();

    /**
     * Return the MethodMatcher for this pointcut.
     *
     * @return the MethodMatcher (never <code>null</code>)
     */
    MethodMatcher getMethodMatcher();
}
