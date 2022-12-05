package cn.dzw.springframwork.core.io;

import java.io.InputStream;

/**
 * @Classname FileSystemResource
 * @Description 系统文件
 * @Date 2022/8/30 14:32
 * @Created by dongzhiwei
 */
public class FileSystemResource implements Resource {

    private String filePath;


    @Override
    public InputStream getInputStream() {
        return null;
    }
}
