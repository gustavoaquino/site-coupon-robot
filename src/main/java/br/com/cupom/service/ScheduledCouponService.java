package br.com.cupom.service;

import br.com.cupom.crawler.to.CouponGenericTO;
import br.com.cupom.crawler.worker.CouponGenericWorker;
import br.com.cupom.crawler.worker.pageobject.CouponGenericPageObject;
import br.com.cupom.domain.Company;
import br.com.cupom.domain.Coupon;
import br.com.cupom.exception.GenericWorkerException;
import br.com.cupom.exception.SeleniumException;
import br.com.cupom.properties.LoginProperties;
import br.com.cupom.selenium.ChromeDriverBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ScheduledCouponService {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CouponService couponService;

    @Autowired
    private CouponGenericWorker couponGenericWorker;

    @Autowired
    private LoginProperties loginProperties;

    public void executeFindCoupon(String uriCompany) throws GenericWorkerException, SeleniumException {
        List<Company> companyList;

        if(uriCompany != null) {
            companyList = Collections.singletonList(this.companyService.findCompanyByName(uriCompany).get());
        }else{
            companyList = this.companyService.findAllCompanies();
        }

        final List<String> couponDescriptionList = new ArrayList<>();

        final ChromeDriverBuilder builder = new ChromeDriverBuilder();
        final CouponGenericPageObject pageObject = new CouponGenericPageObject(builder, loginProperties);

        try {
            pageObject.login(); //TODO SEPARAR PAGEOBJECT DA LOGICA DO SCHEDULED

            for (Company company : companyList) {
                final List<Coupon> couponList = this.couponService.findAllCouponByCompanyIdAndDateExpiredMoreThanNow(company.getId());

                for (Coupon coupon : couponList) {
                    couponDescriptionList.add(coupon.getDescription());
                }

                final List<CouponGenericTO> couponGenericTOList = this.couponGenericWorker.executeCrawler(company, couponDescriptionList, builder, pageObject);

                if (couponGenericTOList.isEmpty()) {
                    couponDescriptionList.clear();
                    continue;
                }

                this.couponService.saveCouponList(Coupon.parseCouponGenericTOList(couponGenericTOList, company));
                couponDescriptionList.clear();
            }
        }finally {
            builder.getDriver().quit();
        }
    }
}
