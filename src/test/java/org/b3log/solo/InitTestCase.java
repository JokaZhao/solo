package org.b3log.solo;

import org.testng.annotations.Test;

/**
 * Created on 2019/11/14 20:50.
 *
 * @author zhaozengjie
 * Description :
 */
@Test(suiteName = "service")
public class InitTestCase extends AbstractTestCase {

    @Test
    public void i() throws Exception {
        init();
    }

}
