package com.wxl.mybatis.config;

/**
 * 映射文件参数
 */
public class Mapper {
    private String querySql;
    private String resultType;

    public String getQuerySql() {
        return querySql;
    }

    public void setQuerySql(String querySql) {
        this.querySql = querySql;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }
}
