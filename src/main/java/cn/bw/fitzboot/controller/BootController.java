/**
 * FileName: BootController
 * Author:   jack.xue
 * Date:     2018/11/30 13:45
 * Description: bootController
 * History:
 * <author>          <time>          <version>          <desc>
 * jack.xue           2018/11/30           1.0.0              描述
 */
package cn.bw.fitzboot.controller;

import cn.bw.core.DateUtil;
import cn.bw.fitzboot.enity.User;
import cn.bw.fitzboot.repository.UserRepository;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 〈TODO〉<br> 
 *
 * @author jack.xue
 * @create 2018/11/30
 * @since 1.0.0
 */
@RestController
@Slf4j
public class BootController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = {"/index"})
    public ModelAndView index(){
        String timeNow = DateUtil.getCurrentDateStr();
        log.info("welcome to Fitz-boot, timeNow: {}", timeNow);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }

    @RequestMapping(value = {"/login"})
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        return mv;
    }

    @ResponseBody
    @RequestMapping("/queryMsg")
    public String queryMsg(@RequestParam String msg){
        if(StringUtils.isNotBlank(msg)){
            String res = "";
            log.info("msg:"+msg);
            List<User> all = userRepository.findAll();
            res =  JSON.toJSONString(all);
            return res;
        }else{
            return "msg can't be blank";
        }

    }

    @RequestMapping("/deleteUserById")
    @ResponseBody
    public String deleteUserById(@RequestParam Long id){
        try{
            userRepository.delete(id);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "fail,"+e.getMessage();
        }

    }

}