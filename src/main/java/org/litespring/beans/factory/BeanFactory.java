package org.litespring.beans.factory;

import org.litespring.beans.BeanDefinition;

/**
 * All rights Reserved, Designed By SARAAD
 *
 * @version V1.0
 * @Title: BeanFactory
 * @Package:org.litespring.beans.factory
 * @Description:
 * @author: saraad
 * @date: 2019/3/7 1:26
 * @Copyright: 2019  Inc. All rights reserved.
 * PROJECT FOR PRACTICE
 */
public interface BeanFactory {

    Object getBean(String beanId);

    BeanDefinition getBeanDefinition(String beanId);
}
