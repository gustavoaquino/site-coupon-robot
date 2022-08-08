package br.com.cupom.crawler.worker;

import br.com.cupom.crawler.to.CouponGenericTO;
import br.com.cupom.crawler.worker.pageobject.CouponGenericPageObject;
import br.com.cupom.domain.Company;
import br.com.cupom.domain.LogErrorCoupon;
import br.com.cupom.exception.DangerException;
import br.com.cupom.properties.LoginProperties;
import br.com.cupom.selenium.ChromeDriverBuilder;
import br.com.cupom.service.ErrorService;
import br.com.cupom.utils.DateUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CouponGenericWorker implements CompanyManager {

    @Autowired
    private LoginProperties loginProperties;

    @Autowired
    private ErrorService errorService;

    @Override
    public List<CouponGenericTO> executeCrawler(Company company, List<String> couponExistingDescriptionList, ChromeDriverBuilder builder, CouponGenericPageObject pageObject) {
        final List<CouponGenericTO> newCouponGenericTOList = new ArrayList<>();
        final List<String> newDescriptionCouponGenericTOList = new ArrayList<>();

        try {
            pageObject.openCouponPage(company);

            pageObject.openAllOffersCompany();

            Document document = Jsoup.parse(builder.getDriver().getPageSource());

            final Elements elements  = document.getElementsByAttributeValue("ng-repeat", "coupon in coupons");

            int positionCoupon = 1;

            for(Element element : elements) {
                final String description = element
                        .getElementsByClass("desc-oferta ng-binding")
                        .text().trim();

                //caso já tenhamos a descrição, então pulamos pra não duplicar a oferta
                if(couponExistingDescriptionList.contains(description) || newDescriptionCouponGenericTOList.contains(description)){
                    positionCoupon++;
                    continue;
                }

                String dateExpiration = StringUtils
                        .substringAfter(element
                        .getElementsByClass("desconto ng-binding")
                        .text(), "Válido até")
                        .trim();

                final Date dateExpirationFormated = DateUtils.formatarData(dateExpiration);

                pageObject.generateCouponByPosition(By.xpath("//*[@id=\"couponsDiv\"]/div[" + positionCoupon +  "]/button"));

                document = Jsoup.parse(builder.getDriver().getPageSource());

                final String link = document.getElementById("linkLomadeezador").val();
                final String couponCode = document.getElementById("couponLomadeezadorCode").val();

                final CouponGenericTO couponGenericTO = new CouponGenericTO(description, dateExpirationFormated, link, couponCode, company.getId());
                newCouponGenericTOList.add(couponGenericTO);
                newDescriptionCouponGenericTOList.add(couponGenericTO.getDescription());

                positionCoupon++;
            }


            return newCouponGenericTOList;
        }catch (Exception e) {
            this.errorService.saveErrorGenerateCoupon(new LogErrorCoupon(company.getId(), new Date(), e.getMessage()));
            //throw new DangerException("Danger Exception " + company.getNameCompany());
        }

        return newCouponGenericTOList;
    }
}
