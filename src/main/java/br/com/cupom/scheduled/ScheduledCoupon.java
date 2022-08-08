package br.com.cupom.scheduled;

import br.com.cupom.crawler.worker.CouponGenericWorker;
import br.com.cupom.domain.LogJobFindCoupon;
import br.com.cupom.service.CompanyService;
import br.com.cupom.service.CouponService;
import br.com.cupom.service.LogJobService;
import br.com.cupom.service.ScheduledCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class ScheduledCoupon {

    @Autowired
    private ScheduledCouponService scheduledCouponService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CouponService couponService;

    @Autowired
    private CouponGenericWorker couponGenericWorker;

    @Autowired
    private LogJobService logJobFindCoupon;

//    @Scheduled(cron = "0 0/1 * * * *")
//    @Scheduled(fixedRate = 1)
//    public void executeJobFindCoupon() {
//
//        logJobFindCoupon.saveLogJobFindCoupon(new LogJobFindCoupon(new Date(), "Successfully started"));
//
//        try {
//            scheduledCouponService.executeFindCoupon();
//            logJobFindCoupon.saveLogJobFindCoupon(new LogJobFindCoupon(new Date(), "finished with successfully "));
//        }catch (Exception e){
//            logJobFindCoupon.saveLogJobFindCoupon(new LogJobFindCoupon(new Date(), "finished with error"));
//        }
//
//        return;
//    }

}
