package org.litespring.test.v1;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanDefinitionStoreException;
import org.litespring.beans.factory.BeanFactory;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.service.v1.PetStoreService;

/**
 * All rights Reserved, Designed By SARAAD
 *
 * @version V1.0
 * @Title: BeanFactoryTest
 * @Package:org.litespring.test.v1
 * @Description:
 * @author: saraad
 * @date: 2019/3/7 0:56
 * @Copyright: 2019  Inc. All rights reserved.
 * PROJECT FOR PRACTICE
 */
public class BeanFactoryTest {

    @Test
    public void testGetBean(){
        //获取beanFactory
        BeanFactory beanFactory = new DefaultBeanFactory("petstore-v1.xml");
        //获取bean的定义
        BeanDefinition bd = beanFactory.getBeanDefinition("petStore");
        //判断获取到的beanDefinition
        assertEquals("org.litespring.service.v1.PetStoreService",bd.getBeanClassName());
        //从beanfactory中获取bean实例
        PetStoreService petStore = (PetStoreService) beanFactory.getBean("petStore");
        assertNotNull(petStore);
    }

    //测试异常
    @Test
    public void testException(){
        BeanFactory beanFactory = new DefaultBeanFactory("petstore-v1.xml");
        try {
            beanFactory.getBean("invalidBean");
        } catch (Exception e) {
            return;
        }
        Assert.fail("test fail");
    }
    @Test
    public void testInvalidXML(){

        try{
            new DefaultBeanFactory("xxx-v1.xml");
        }catch(BeanDefinitionStoreException e){
            return;
        }
        Assert.fail("expect BeanDefinitionStoreException ");
    }

}
