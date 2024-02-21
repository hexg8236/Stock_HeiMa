package top.newhand.stock.listener;

import com.github.benmanes.caffeine.cache.Cache;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.newhand.stock.service.StockService;

import java.util.Date;

/**
 * @ClassName MqListener
 * @Author HeXianGang
 * @Date 2024/2/20 10:22
 * @Version 1.0
 * @Description Mq 监听股票变化消息
 **/

@Slf4j
@Component
public class MqListener {

    @Autowired
    private Cache<String,Object> caffeineCache;

    @Autowired
    private StockService stockService;


    @RabbitListener
    public void acceptInnerMarketInfo(Date date) {
        long diffTime = DateTime.now().getMillis() - new DateTime(date).getMillis();
        // 查过一分钟告警
        if (diffTime > 60000) {
            log.error("采集国内大盘时间点：{},同步超时：{}ms",new DateTime(date).toString("yyyy-MM-dd HH:mm:ss"),diffTime);
        }
        //将缓存置为失效删除
        caffeineCache.invalidate("innerMarketInfosKey");
        //调用服务更新缓存
        stockService.getNewestInnerMarketInfos();
    }
}
