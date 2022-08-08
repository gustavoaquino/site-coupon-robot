package br.com.cupom.controller;

import br.com.cupom.controller.to.CouponViewTO;
import br.com.cupom.domain.Company;
import br.com.cupom.domain.Coupon;
import br.com.cupom.service.CompanyService;
import br.com.cupom.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CouponService couponService;

    @GetMapping("/")
    public String getIndex(Model model) {
        final List<Company> companyList = companyService.findAllTop6OrderByIdAsc();
        final List<Coupon> couponList = couponService.findAllCouponPrincipalPage();

        final List<CouponViewTO> couponViewTOList = CouponViewTO.parseCouponList(couponList);

        for(Company company : companyList){
            for(CouponViewTO coupon : couponViewTOList){
                 if(company.getId().equals(coupon.getCompany().getId())){
                     coupon.setCompany(company);
                 }
            }
        }

        final int qtdCoupon = couponService.findQtdCoupon();

        model.addAttribute("companyList", companyList);
        model.addAttribute("qtdCoupon", qtdCoupon);
        model.addAttribute("couponList", couponViewTOList);

        return "index";
    }

}
