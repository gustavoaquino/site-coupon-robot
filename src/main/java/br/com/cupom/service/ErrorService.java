package br.com.cupom.service;


import br.com.cupom.domain.LogErrorCoupon;
import br.com.cupom.repository.LogErrorGenerateCouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ErrorService {

    @Autowired
    private LogErrorGenerateCouponRepository logErrorGenerateCouponRepository;

    public void saveErrorGenerateCoupon(LogErrorCoupon logErrorCoupon){
        this.logErrorGenerateCouponRepository.save(logErrorCoupon);
    }


}
