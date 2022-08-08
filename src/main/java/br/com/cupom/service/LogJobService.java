package br.com.cupom.service;

import br.com.cupom.domain.LogJobFindCoupon;
import br.com.cupom.repository.LogJobFindCouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogJobService {

    @Autowired
    private LogJobFindCouponRepository logJobFindCouponRepository;

    public void saveLogJobFindCoupon(LogJobFindCoupon logJobFindCoupon){
        this.logJobFindCouponRepository.save(logJobFindCoupon);
    }


}
