package cn.dzw.springframwork.context.support;

/**
 * @Classname ClassPathXmlApplicationContext
 * @Description 具体的 路径加载xml 进来的  给用户用的类
 * @Date 2022/10/24 17:34
 * @Created by dongzhiwei
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {

    /**
     * 加载bean 的列表
     */
    private String[] configLocations;


    public ClassPathXmlApplicationContext() {
    }

    public ClassPathXmlApplicationContext(String configLocations) {
        this(new String[]{configLocations});
    }

    public ClassPathXmlApplicationContext(String[] configLocations) {
        this.configLocations = configLocations;
        // 刷新上下文
        refresh();
    }

    @Override
    protected String[] getConfigLocations() {
        return configLocations;
    }

}
