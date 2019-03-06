package org.litespring.beans;

/**
 * All rights Reserved, Designed By SARAAD
 *
 * @version V1.0
 * @Title: BeanException
 * @Package:org.litespring.beans
 * @Description:
 * @author: saraad
 * @date: 2019/3/7 2:34
 * @Copyright: 2019  Inc. All rights reserved.
 * PROJECT FOR PRACTICE
 */
public class BeansException extends RuntimeException {
    public BeansException(String msg) { super(msg);	}

    public BeansException(String msg, Throwable cause) {
        super(msg, cause);
    }


}
