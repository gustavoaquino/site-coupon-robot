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
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class ListCompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CouponService couponService;

    @GetMapping("/lojas/{name}")
    public String getStoreByNameCompany(@PathVariable("name") String nameCompany,  Model model) {
        final Optional<Company> company = companyService.findCompanyByName(nameCompany);

        if(company.isEmpty()){
            return "redirect:/";
        }

        final List<Coupon> couponList = this.couponService.findAllCouponByCompanyIdAndDateExpiredMoreThanNow(company.get().getId());

        model.addAttribute("couponList", CouponViewTO.parseCouponList(couponList));
        model.addAttribute("company", company.get());

        return "lojas-detalhe";
    }


    @GetMapping("/lojas")
    public String getStoreList(Model model) {
        final List<Company> companyList = companyService.findAllComapaniesOrderByName();
        model.addAttribute("companyList", companyList);
        return "lojas";
    }


}
