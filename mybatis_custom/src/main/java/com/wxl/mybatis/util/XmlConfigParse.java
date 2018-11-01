package com.wxl.mybatis.util;

import cn.wanghaomiao.xpath.model.JXDocument;
import cn.wanghaomiao.xpath.model.JXNode;
import com.wxl.mybatis.Annoation.Select;
import com.wxl.mybatis.config.Configuration;
import com.wxl.mybatis.config.Mapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 解析主配置文件和映射文件(非注解方式)
 */
public class XmlConfigParse {

    /**
     * 解析主配置文件(driver,url,username,password)
     * @param in
     * @return
     */
    public static Configuration LoadMasterConfig(InputStream in) {
        //1.创建配置文件对象
        Configuration cfg = new Configuration();
        try {
            //2.创建解析工具对象
            Document document = Jsoup.parse(in, "utf-8","");
            JXDocument jxDocument = new JXDocument(document);
            //3.解析和封装属性对象
            List<JXNode> jxNodes = jxDocument.selN("//configuration/environments/environment/datasource/property");
            if(jxNodes == null){
                throw new NullPointerException();
            }
            for (JXNode jxNode : jxNodes) {
                if(jxNode.sel("@name").get(0).toString().equals("driver")){
                        cfg.setDriver(jxNode.sel("@value").get(0).toString());
                } else if(jxNode.sel("@name").get(0).toString().equals("url")){
                    cfg.setUrl(jxNode.sel("@value").get(0).toString());
                } else if(jxNode.sel("@name").get(0).toString().equals("username")){
                    cfg.setUsername(jxNode.sel("@value").get(0).toString());
                } else if(jxNode.sel("@name").get(0).toString().equals("password")){
                    cfg.setPassword(jxNode.sel("@value").get(0).toString());
                }
            }

            //4.解析映射路径(mapper(class,resource),packege)，这里只分析前一种mapper方式
            //  4.1 获取所有mapper对象,并进行解析封装
            List<JXNode> mappers = jxDocument.selN("//configuration/mappers/mapper");
            cfg.setMappers(loadMapperConfig(mappers));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //5.遍历映射路径，获取映射参数
        //6.封装对象
        return cfg;
    }

    /**
     * 解析所有mapper文件，并把结果保存到map中
     * @param mappers
     * @return
     */
    private static Map<String,Mapper> loadMapperConfig(List<JXNode> mappers){
        // key:namespace+id -- mapper
         Map<String,Mapper> map = new HashMap<String,Mapper>();
        for (JXNode mapper : mappers) {
            try {
                List<JXNode> source = mapper.sel("@resource");
                if( source == null){
                    // 未采用XML的方式进行注册
                    throw new RuntimeException("Not XML mapper");
                }else {
                    // 获取mapper路径
                    String url = source.get(0).toString();
                    String path = XmlConfigParse.class.getClassLoader().getResource(url).getPath();
                    // 创建解析工具
                    Document document = Jsoup.parse(new File(path), "utf-8");
                    JXDocument jxDocument = new JXDocument(document);
                    // 获取namespace
                    String namespace = jxDocument.selN("//mapper").get(0).sel("@namespace").get(0).toString();
                    // 获取所有select
                    List<JXNode> selects = jxDocument.selN("//mapper/select");
                    for (JXNode select : selects) {
                        Mapper mapper1 =new Mapper();
                        // sql语句
                        String querySql = select.sel("/text()").get(0).toString();
                        // 返回结果类型
                        String resultType = select.sel("@resultType").get(0).toString();
                        // key值
                        String key = namespace +"."+ select.sel("@id").get(0).toString();
                        mapper1.setQuerySql(querySql);
                        mapper1.setResultType(resultType);
                        map.put(key,mapper1);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return map;
    }

    /**
     * 注解方式进行解析，解析类名称+方法名称，sql语句，返回值结果类型
     * @param mapperClassPath
     * @return
     */
    private static Map<String,Mapper> loadMapperAnnoation(String mapperClassPath){
        HashMap<String,Mapper> map = new HashMap<String, Mapper>();
        Mapper mapper = new Mapper();
        // 1.加载字节码文件
        try {
            Class<?> mapperClass = Class.forName(mapperClassPath);
            // 2.获取当前类的所有方法
            Method[] methods = mapperClass.getMethods();
            String mapperClassName = mapperClass.getDeclaringClass().getName();
            // 3.遍历方法，筛选出含有注解的方法
            for (Method method : methods) {
                // 根据不同的注解执行不同的解析方法，这里只测试@select
                boolean isSelect = method.isAnnotationPresent(Select.class);
                if(isSelect){
                    // 4.获取method的方法名称
                    String methodName = method.getName();
                    String key = mapperClassName +"."+methodName;
                    // 5.获取sql语句
                    Select annotation = method.getAnnotation(Select.class);
                    String sql = annotation.value();
                    mapper.setQuerySql(sql);
                    // 6 获取返回值类型
                    Type type = method.getReturnType();
                    // 参数化类型，指的是集合collection<Type>中的type
                    if(type instanceof ParameterizedType){
                        ParameterizedType paramType =(ParameterizedType)type;
                        // 获取真实的返回值类型
                        Class domainClass = (Class) paramType.getActualTypeArguments()[0];
                        String resultType = domainClass.getName();
                        mapper.setResultType(resultType);
                    }
                    // 添加到map中
                    map.put(key,mapper);
                }

            }
            return map;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
