/**
 * FileName: RedisTests
 * Author:   jack.xue
 * Date:     2019/11/12 15:37
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * jack.xue           2019/11/12           1.0.0              描述
 */
package cn.bw.fitzboot;

import cn.bw.fitzboot.redis.lock.DistributedLock;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 〈TODO〉<br> 
 *
 * @author jack.xue
 * @create 2019/11/12
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RedisTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private final String DisKey = "news_doc:repeat_distributed_key_test";



    @Test
    public void doDistributedTest(){
        ExecutorService es = Executors.newFixedThreadPool(20);
        List<Future<Boolean>> list = new ArrayList<>();
        for(int i=0; i< 3; i++){
            Future<Boolean> future = es.submit(() -> {
                DistributedLock distributedLock = new DistributedLock(stringRedisTemplate, DisKey, 10 * 6000);
                if(distributedLock.isLocked()){
                    log.info(">>>> Lock is locked");
                    return false;
                }else if(distributedLock.tryLock()){

                    log.info("####### do something happy... #######");
                    return true;
                }else{
                    log.info(">>>> tryLock -> Lock is locked");
                    return false;
                }
            });
            list.add(future);
        }
        list.forEach(future -> {
            boolean f = false;
            try {
                f = future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            log.info("f: {}" ,f);
        });

    }



}
