package cn.dzw.springframwork.aop.aspectj;

import cn.dzw.springframwork.aop.Pointcut;
import cn.dzw.springframwork.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;

/**
 * @Classname AspectJExpressionPointcutAdvisor
 * @Description 吧切面 方法 具体拦截表达式 放在一起
 * @Date 2022/11/22 17:25
 * @Created by dongzhiwei
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    /**
     * 切面
     */
    private AspectJExpressionPointcut pointcut;

    private Advice advice;

    /**
     * 表达式
     */
    private String expression;


    public void setExpression(String expression) {
        this.expression = expression;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    @Override
    public Pointcut getPointCut() {
        if (null == pointcut) {
            return new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

}
