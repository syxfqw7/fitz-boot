/**
 * FileName: CoreTest
 * Author:   jack.xue
 * Date:     2018/12/4 17:37
 * Description: SimpleCoreTest
 * History:
 * <author>          <time>          <version>          <desc>
 * jack.xue           2018/12/4           1.0.0              描述
 */
package cn.bw.fitzboot;

import cn.bw.core.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 〈TODO〉<br> 
 *
 * @author jack.xue
 * @create 2018/12/4
 * @since 1.0.0
 */
@Slf4j
@SpringBootTest
public class CoreTest {

    @Test
    public void logCoreTimeTest(){

        String timeNow = DateUtil.getCurrentDateStr();
        System.out.println("sout:"+timeNow);
        log.info("timeNow: {}", timeNow);
        log.debug("debug msg");
        log.warn("warn msg");
        log.error("error msg");
    }

    @Test
    public void autoLogTest(){
        log.info("this is testLog from fitz-boot");
    }
}