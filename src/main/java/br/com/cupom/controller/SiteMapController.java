package br.com.cupom.controller;

import br.com.cupom.controller.to.sitemap.XmlUrl;
import br.com.cupom.controller.to.sitemap.XmlUrlSet;
import br.com.cupom.domain.Company;
import br.com.cupom.service.CompanyService;
import cz.jiripinkas.jsitemapgenerator.WebPage;
import cz.jiripinkas.jsitemapgenerator.generator.SitemapGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SiteMapController {
    private static final String DOMAIN = "https://www.cupomparceiro.com.br";

    @Autowired
    private CompanyService companyService;

    @GetMapping(value = {"/sitemap.xml", "/Sitemap.xml"}, produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public String main() {
        final SitemapGenerator sitemapGenerator = SitemapGenerator.of(DOMAIN);

        final List<Company> companyList = this.companyService.findAllComapaniesOrderByName();

        final List<String> urlList = new ArrayList<>();
        urlList.add("/");
        urlList.add("/lojas");
        urlList.add("/ajuda");

        final List<String> companyUrlSiteMapList = new ArrayList<>();
        for(Company company : companyList){
            companyUrlSiteMapList.add("/lojas/" + company.getUriSocialSoul());
        }

        urlList.addAll(companyUrlSiteMapList);


        for (String url : urlList) {
            sitemapGenerator.addPage(url);
            sitemapGenerator.addPage(WebPage.builder().name(url).maxPriorityRoot().changeFreqDaily().lastModNow().build());
        }



//        final List<String> urlList = new ArrayList<>();
//        urlList.add("/");
//        urlList.add( "/lojas");
//        urlList.add( "/ajuda");
//
//        final List<Company> companyList = this.companyService.findAllComapaniesOrderByName();
//
//        final List<String> companyUrlSiteMapList = new ArrayList<>();
//
//        for(Company company : companyList){
//            companyUrlSiteMapList.add("/lojas/" + company.getUriSocialSoul());
//        }
//
//        urlList.addAll(companyUrlSiteMapList);
//
//        XmlUrlSet xmlUrlSet = new XmlUrlSet();
//        for (String eachLink : urlList) {
//            create(xmlUrlSet, eachLink, XmlUrl.Priority.HIGH);
//        }

        return sitemapGenerator.toString();
    }

    private void create(XmlUrlSet xmlUrlSet, String link, XmlUrl.Priority priority) {
        xmlUrlSet.addUrl(new XmlUrl(DOMAIN + link, priority));
    }

}
