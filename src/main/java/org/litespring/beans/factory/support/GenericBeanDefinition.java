package org.litespring.beans.factory.support;

import org.litespring.beans.BeanDefinition;

/**
 * All rights Reserved, Designed By SARAAD
 *
 * @version V1.0
 * @Title: GenericBeanDefinition
 * @Package:org.litespring.beans.factory.support
 * @Description:
 * @author: saraad
 * @date: 2019/3/7 2:19
 * @Copyright: 2019  Inc. All rights reserved.
 * PROJECT FOR PRACTICE
 */
public class GenericBeanDefinition implements BeanDefinition {

    private String id;
    private String beanClassName;

    public String getBeanClassName() {
        return this.beanClassName;
    }

    public GenericBeanDefinition(String id, String beanClassName) {
        this.id = id;
        this.beanClassName = beanClassName;
    }

}
