package br.com.cupom.controller;


import br.com.cupom.controller.request.JobTO;
import br.com.cupom.domain.LogJobFindCoupon;
import br.com.cupom.properties.SecurityUUIDProperties;
import br.com.cupom.service.LogJobService;
import br.com.cupom.service.ScheduledCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class JobRestController {
    @Autowired
    private SecurityUUIDProperties securityUUIDProperties;

    @Autowired
    private LogJobService logJobService;

    @Autowired
    private ScheduledCouponService scheduledCouponService;

    @PostMapping(value = "/job", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String postJobOn(@RequestBody JobTO jobTO) {

        try {
            if (securityUUIDProperties.getSecurityUuid().equals(jobTO.getSecurityUuid())) {

                logJobService.saveLogJobFindCoupon(new LogJobFindCoupon(new Date(), "Successfully started by Api "));

                try {
                    scheduledCouponService.executeFindCoupon(jobTO.getCompanyUri());
                    logJobService.saveLogJobFindCoupon(new LogJobFindCoupon(new Date(), "finished with successfully by Api "));
                    return "Finalizado com sucesso!";
                } catch (Exception e) {
                    logJobService.saveLogJobFindCoupon(new LogJobFindCoupon(new Date(), "finished with error by Api"));
                    return "Finalizado através da exceção";
                }
            }
        }catch (Exception e){
            logJobService.saveLogJobFindCoupon(new LogJobFindCoupon(new Date(), "finished with error by Api, uuid recevied" + jobTO.getSecurityUuid()));
        }

        return "Finalizado com erro";
    }



}
