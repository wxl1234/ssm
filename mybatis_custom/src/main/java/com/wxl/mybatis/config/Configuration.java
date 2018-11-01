package com.wxl.mybatis.config;

import java.util.HashMap;
import java.util.Map;

/**
 * 配置文件类，接受xml配置文件连接数据和映射对象数据
 */
public class Configuration {
    private String url;
    private String driver;
    private String username;
    private String password;

    // 接受映射对象的参数

    private Map<String,Mapper> mappers = new HashMap<String,Mapper>();

    public Map<String, Mapper> getMappers() {
        return mappers;
    }

    public void setMappers(Map<String, Mapper> mappers) {
        this.mappers = mappers;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "url='" + url + '\'' +
                ", driver='" + driver + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", mappers=" + mappers +
                '}';
    }
}
