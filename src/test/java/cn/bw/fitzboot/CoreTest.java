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
import cn.bw.fitzboot.controller.BootController;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * 〈TODO〉<br>
 *
 * @author jack.xue
 * @create 2018/12/4
 * @since 1.0.0
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class CoreTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception{
        //MockMvcBuilders.webAppContextSetup(WebApplicationContext context)：指定WebApplicationContext，将会从该上下文获取相应的控制器并得到相应的MockMvc；
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();//建议使用这种
    }
    @Test
    public void getHello() throws Exception{

        /**
         * 1、mockMvc.perform执行一个请求。
         * 2、MockMvcRequestBuilders.get("XXX")构造一个请求。
         * 3、ResultActions.param添加请求传值
         * 4、ResultActions.accept(MediaType.TEXT_HTML_VALUE))设置返回类型
         * 5、ResultActions.andExpect添加执行完成后的断言。
         * 6、ResultActions.andDo添加一个结果处理器，表示要对结果做点什么事情
         *   比如此处使用MockMvcResultHandlers.print()输出整个响应结果信息。
         * 5、ResultActions.andReturn表示执行完成后返回相应的结果。
         */

        MvcResult mvcResult= mockMvc.perform(MockMvcRequestBuilders.get("/queryMsg")
                .param("msg","123")
                .accept(MediaType.TEXT_HTML_VALUE))
                // .andExpect(MockMvcResultMatchers.status().isOk())             //等同于Assert.assertEquals(200,status);
                // .andExpect(MockMvcResultMatchers.content().string("hello lvgang"))    //等同于 Assert.assertEquals("hello lvgang",content);
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status=mvcResult.getResponse().getStatus();                 //得到返回代码
        String content=mvcResult.getResponse().getContentAsString();    //得到返回结果
        log.info("status: {},content: {}",status, content);
        Assert.assertEquals(200,status);                        //断言，判断返回代码是否正确
        //Assert.assertEquals("hello lvgang",content);            //断言，判断返回的值是否正确
    }

    @Test
    public void logCoreTimeTest() {

        String timeNow = DateUtil.getCurrentDateStr();
        System.out.println("sout:" + timeNow);
        log.info("timeNow: {}", timeNow);
        log.debug("debug msg");
        log.warn("warn msg");
        log.error("error msg");
    }

    @Test
    public void autoLogTest() {
        log.info("this is testLog from fitz-boot");
    }
}