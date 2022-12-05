package cn.dzw.springframwork.core.io;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @Classname Resource
 * @Description 资源
 * @Date 2022/8/30 14:30
 * @Created by dongzhiwei
 */
public interface Resource {

    public InputStream getInputStream() throws FileNotFoundException;
}
