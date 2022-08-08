package br.com.cupom.crawler.worker;

import br.com.cupom.crawler.to.CouponGenericTO;
import br.com.cupom.crawler.worker.pageobject.CouponGenericPageObject;
import br.com.cupom.domain.Company;
import br.com.cupom.exception.DangerException;
import br.com.cupom.selenium.ChromeDriverBuilder;

import java.util.List;

public interface CompanyManager {

    List<CouponGenericTO> executeCrawler(Company company, List<String> couponExistingDescriptionList, ChromeDriverBuilder builder, CouponGenericPageObject pageObject) throws DangerException;

}
