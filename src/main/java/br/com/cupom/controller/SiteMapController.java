package br.com.cupom.controller;

import br.com.cupom.controller.to.sitemap.XmlUrl;
import br.com.cupom.controller.to.sitemap.XmlUrlSet;
import br.com.cupom.domain.Company;
import br.com.cupom.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class SiteMapController {
    private static final String DOMAIN = "https://www.cupomparceiro.com.br";

    @Autowired
    private CompanyService companyService;

    @GetMapping(value = "/sitemap.xml")
    @ResponseBody
    public XmlUrlSet main() {
        final List<String> urlList = new ArrayList<>();
        urlList.add("/");
        urlList.add( "/lojas");
        urlList.add( "/ajuda");

        final List<Company> companyList = this.companyService.findAllComapaniesOrderByName();

        final List<String> companyUrlSiteMapList = new ArrayList<>();

        for(Company company : companyList){
            companyUrlSiteMapList.add("/lojas/" + company.getUriSocialSoul());
        }

        urlList.addAll(companyUrlSiteMapList);

        XmlUrlSet xmlUrlSet = new XmlUrlSet();
        for (String eachLink : urlList) {
            create(xmlUrlSet, eachLink, XmlUrl.Priority.HIGH);
        }

        return xmlUrlSet;
    }

    private void create(XmlUrlSet xmlUrlSet, String link, XmlUrl.Priority priority) {
        xmlUrlSet.addUrl(new XmlUrl(DOMAIN + link, priority));
    }

}
