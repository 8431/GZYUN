package com.gz.medicine.gzyun.health.utilTaskTime;

import com.gz.medicine.gzyun.health.bean.HealthyOrder;
import com.gz.medicine.gzyun.health.service.HealthConsultationService;
import com.gz.medicine.gzyun.health.service.HealthOrderService;
import com.gz.medicine.gzyun.push.service.PushMessageService;
import com.gz.medicine.yun.common.service.SendMessageService;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @version V1.0
 * @Author fendo
 * @ClassName aminuteOutDateOrderRefundTask
 * @PackageName com.gz.medicine.gzyun.health.utilTaskTime
 * @Description 1小时扫描一次----付款成功->订单关闭(自动退款)(医生24小时未回复)(最终状态)-更改订单状态为6退款中
 * @Data 2017-11-01 10:47
 **/
@Service("aminuteOutDateOrderRefundTask")
public class AminuteOutDateOrderRefundTask {

    public static final Logger LOGGER = Logger.getLogger(AminuteOutDateOrderRefundTask.class);

    @Autowired
    private HealthOrderService healthOrderService;

    @Autowired
    private HealthConsultationService healthConsultationService;


    @Autowired
    private SendMessageService sendMessageService;

    @Autowired
    private PushMessageService pushMessageService;



    /**
     * 医生24小时未回复-订单关闭-自动退款
     */
    public void SendMessage(){
        LOGGER.info("AminuteOutDateOrderRefundTask start ....");
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MINUTE, -10);// 10分钟之前的时间
            String defaultEndDate = df.format(calendar.getTime());
            LOGGER.info(" 当前的时间是: " + defaultEndDate);
            List<HealthyOrder> healthyOrderList = healthOrderService.OutDateOrderRefundList(defaultEndDate);
            if(healthyOrderList.size() > 0) {
                for (HealthyOrder healthorder : healthyOrderList) {
                    HealthyOrder healthyOrder = new HealthyOrder();
                    BeanUtils.copyProperties(healthorder,healthyOrder);
                    //更改订单的状态为5(退款审核中),并记录日志
                    healthyOrder.setOrderstate("6");
                    LOGGER.info("医生24小时未回复-订单关闭-自动退款 -- 更改状态 start ....");
                    LOGGER.info("医生24小时未回复-订单关闭-自动退款 -- 更改状态--------订单信息 start ...." + healthyOrder.toString());
                    healthOrderService.UpdateOrderAndLog(healthyOrder, "医生24小时未回复,订单状态更改更改为退款审核中");
                }
            }

        }catch (Exception e){
            LOGGER.error("医生24小时未回复-订单关闭-自动退款: " + e.getMessage() + e);
        }
        LOGGER.info("aminuteOutDateOrderTask end ....");
    }


}
