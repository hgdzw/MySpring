package cn.dzw.springframwork.core.io;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @Classname ClassPathResource
 * @Description 根据类路径查找资源
 * @Date 2022/8/30 14:31
 * @Created by dongzhiwei
 */
public class ClassPathResource implements Resource{

    private String path;

    public ClassPathResource(String path) {
        this.path = path;
    }

    @Override
    public InputStream getInputStream() throws FileNotFoundException {
        return FileUtil.getInputStream(path);
//        return new FileInputStream(new File(path));
    }
}
