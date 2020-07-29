/**
 * FileName: SimpleTest
 * Author:   jack.xue
 * Date:     2019/7/5 10:26
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * jack.xue           2019/7/5           1.0.0              描述
 */
package cn.bw.fitzboot;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.CopyUtils;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;

/**
 * 〈TODO〉<br> 
 *
 * @author jack.xue
 * @create 2019/7/5
 * @since 1.0.0
 */
@Slf4j
public class SimpleTest {


    private static <T> List<List<T>> averageAssign(List<T> source, int n) {
        List<List<T>> result = new ArrayList<>();
        int remaider = source.size() % n;  //(先计算出余数)
        int number = source.size() / n;  //然后是商
        int offset = 0;//偏移量
        for (int i = 0; i < n; i++) {
            List<T> value;
            if (remaider > 0) {
                value = source.subList(i * number + offset, (i + 1) * number + offset + 1);
                remaider--;
                offset++;
            } else {
                value = source.subList(i * number + offset, (i + 1) * number + offset);
            }
            result.add(value);
        }
        return result;
    }

    @Test
    public void test(){
        ExecutorService executorService = new ThreadPoolExecutor(2, 2, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), new ThreadFactoryBuilder().setNameFormat(Optional.of("refresh-thread") + "-pool-%d").build());
        //List<String> list1 = Lists.newArrayList("A","B","C","D","E","F");
        //List<String> list2 = Lists.newArrayList("A2","B2","C2","D2","E2","F2");
        //List<List<String>> list = Lists.newArrayList(list1,list2);
        List<String> list3 = Lists.newArrayList("A","B","C","D","E","F","A2","B2","C2","D2","E2","F2");
        List<List<String>> list = averageAssign(list3, 2);
        /*list.forEach(lt->{
            log.info("lt-before: {}",lt);
            try{
                Iterator<String> it = lt.iterator();
                while(it.hasNext()){
                    String value = it.next();
                    log.info("value: {}",value);
                    Thread.sleep(100);
                    it.remove();
                }
            }catch (Exception e){
                log.error("error",e);
            }
            log.info("lt-after: {}",lt);
        });*/

        CountDownLatch countDownLatch = new CountDownLatch(2);
        list.forEach(lt->{
            executorService.submit(new MyRunnable(lt,countDownLatch));
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            log.error("thread is interrupted", e);
        }
        list.forEach(lt->{
            log.info("lt-after: {}",lt);
        });
    }

    class MyRunnable implements Runnable{

        private List<String> list;
        private CountDownLatch countDownLatch;

        MyRunnable(List<String> list,CountDownLatch countDownLatch){
            this.list = list;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            Iterator<String> it = list.iterator();
            try {

                while(it.hasNext()){
                    String value = it.next();
                    log.info("value: {}",value);
                    Thread.sleep(100);
                    it.remove();
                }
                countDownLatch.countDown();
            }catch (Exception e){
                log.error("error",e);
            }


        }
    }
}