package com.wxl.Test;

import com.wxl.mybatis.config.Configuration;
import com.wxl.mybatis.util.XmlConfigParse;
import org.junit.Test;

import java.io.InputStream;

/**
 * 工具类测试
 */
public class utilTest {


    /**
     * XML解析器测试
     */
    @Test
    public void testXmlConfigParse(){
        InputStream path = utilTest.class
                .getClassLoader()
                .getResourceAsStream("SqlMapConfig.xml");

        Configuration configuration = XmlConfigParse.LoadMasterConfig(path);
        System.out.println(configuration);
    }
}
