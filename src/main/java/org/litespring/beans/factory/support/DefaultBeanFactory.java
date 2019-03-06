package org.litespring.beans.factory.support;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.BeanDefinitionStoreException;
import org.litespring.beans.factory.BeanFactory;
import org.litespring.util.ClassUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.prefs.BackingStoreException;

/**
 * All rights Reserved, Designed By SARAAD
 *
 * @version V1.0
 * @Title: DefaultBeanFactory
 * @Package:org.litespring.beans.factory.support
 * @Description:
 * @author: saraad
 * @date: 2019/3/7 1:29
 * @Copyright: 2019  Inc. All rights reserved.
 * PROJECT FOR PRACTICE
 */
public class DefaultBeanFactory implements BeanFactory {

    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>(64);
    private ClassLoader beanClassLoader;

    public final static String ID_ATTRIBUTE = "id";
    public final static String CLASS_ATTRIBUTE = "class";

    public DefaultBeanFactory(String fieldName) {
        loadBeanDefinition(fieldName);
    }

    private void loadBeanDefinition(String fieldName) {
        //使用dom4j解析配置文件
        InputStream is = null;
        try {
            ClassLoader c1 = getBeanClassLoader();
            is = c1.getResourceAsStream(fieldName);
            SAXReader reader = new SAXReader();
            Document doc = reader.read(is);
            //解析dom树
            Element root = doc.getRootElement();
            Iterator<Element> iterator = root.elementIterator();
            while (iterator.hasNext()){
                Element ele = iterator.next();
                String id = ele.attributeValue(ID_ATTRIBUTE);
                String beanClassName = ele.attributeValue(CLASS_ATTRIBUTE);
                BeanDefinition bd = new GenericBeanDefinition(id, beanClassName);
                this.beanDefinitionMap.put(id,bd);
            }
        }catch (DocumentException e){
            throw new BeanDefinitionStoreException("IOException parsing XML document from ",e);
//            throw new RuntimeException(e);
        }finally {
            if (is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //获取bean实例
    public Object getBean(String beanId) {
        //获取beanDefinition
        BeanDefinition bd = this.getBeanDefinition(beanId);
        if (bd == null)
            return null;
        //获取类加载器加载类对象
        ClassLoader c1 = getBeanClassLoader();
        String beanClassName = bd.getBeanClassName();
        try {
            Class<?> clz = c1.loadClass(beanClassName);
            //TODO 暂时只返回含有无参构造的类实例
            return clz.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new BeanCreationException("create bean for "+ beanClassName +" failed",e);
        }
    }

    public BeanDefinition getBeanDefinition(String beanId) {
        return this.beanDefinitionMap.get(beanId);
    }

    public void setBeanClassLoader(ClassLoader beanClassLoader) {
        this.beanClassLoader = beanClassLoader;
    }

    public ClassLoader getBeanClassLoader() {
        return (this.beanClassLoader != null ? this.beanClassLoader : ClassUtils.getDefaultClassLoader());
    }

    public DefaultBeanFactory() {
    }
}
