package com.github.zhangkaitao;

import org.junit.Test;
import org.springframework.util.Base64Utils;

import junit.framework.Assert;

/**
 * User: zhangkaitao
 * Date: 14-8-1
 * Time: ����1:57
 * Version: 1.0
 */
public class Base64UtilsTest {

    @Test
    public void test() {
        String str = "123";
        String str2 = new String(Base64Utils.decodeFromString(Base64Utils.encodeToString(str.getBytes())));
        Assert.assertEquals(str, str2);
    }

}
