package me.zhengjie.modules.blog.utils;

import junit.framework.TestCase;
import org.junit.Assert;

/**
 * @author lijiaxing
 * @date 2020/12/8
 */
public class DateFormatUtilsTest extends TestCase {

    public void testFormatDate() {
        String s = "Sun Jul 15 09:34:01 +0800 2018";
        Long aLong = DateFormatUtils.formatDate(s);
        Assert.assertNotNull(aLong);
    }
}
