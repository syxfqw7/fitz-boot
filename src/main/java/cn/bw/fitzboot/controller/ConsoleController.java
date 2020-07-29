/**
 * FileName: ConsoleController
 * Author:   jack.xue
 * Date:     2018/12/5 17:27
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * jack.xue           2018/12/5           1.0.0              描述
 */
package cn.bw.fitzboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 〈TODO〉<br>
 *
 * @author jack.xue
 * @create 2018/12/5
 * @since 1.0.0^
 */
@Controller
public class ConsoleController {

    @RequestMapping("/console")
    public String console() {
        return "console";
    }

    @RequestMapping("/dbView")
    public String dbView() {
        return "dbView/dbView";
    }
}