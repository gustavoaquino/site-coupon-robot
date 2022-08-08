package br.com.cupom.crawler.worker.pageobject;

import br.com.cupom.domain.Company;
import br.com.cupom.exception.ExceptionUtils;
import br.com.cupom.exception.GenericWorkerException;
import br.com.cupom.properties.LoginProperties;
import br.com.cupom.selenium.ChromeDriverBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class CouponGenericPageObject {

    private static final String URL_LOGIN = "https://www.socialsoul.com.vc/pt_br/login/";
    private static final String URL_COUPON = "https://www.socialsoul.com.vc/dashboard/#/advertiser";
    private static final String URL_BASE = "https://www.socialsoul.com.vc";
    private final ChromeDriverBuilder builder;
    private final LoginProperties loginProperties;
    private final Actions actions;

    public CouponGenericPageObject(ChromeDriverBuilder builder, LoginProperties loginProperties) {
        this.builder = builder;
        this.loginProperties = loginProperties;
        this.actions = new Actions(builder.getDriver());
    }

    public void login() throws GenericWorkerException {
        try {
            builder.access(URL_LOGIN);

            Thread.sleep(2000);

            builder.click(By.xpath("//*[@id=\"login-type-document\"]"));

            Thread.sleep(2000);

            new Select(builder.getDriver().findElement(By.xpath("//*[@id=\"documentType\"]"))).selectByValue("1");

            Thread.sleep(2000);

            final By email = By.xpath("//*[@id=\"email\"]");
            final By password = By.xpath("//*[@id=\"password\"]");

            builder.sendKeys(email, this.loginProperties.getName());

            Thread.sleep(2000);

            builder.sendKeys(password, this.loginProperties.getPassword());

            Thread.sleep(2000);

            builder.click(By.xpath("//*[@id=\"btn_submit\"]"));

            Thread.sleep(2000);
        }catch (Exception e){
            throw new GenericWorkerException(ExceptionUtils.LOGIN_FAILED + " Exception: " + e.getLocalizedMessage());
        }
    }

    public void openCouponPage(Company company) throws GenericWorkerException {
        try {
            builder.access(URL_COUPON + "/" + company.getUriSocialSoul());
            Thread.sleep(2000);

            if(builder.getDriver().findElement(By.xpath("//*[@id=\"modalOnboarding\"]/div/div")).isDisplayed()){
                builder.click(By.xpath("//*[@id=\"btn-fechar\"]"));
            }

            Thread.sleep(2000);
        }catch (Exception e){
            throw new GenericWorkerException(ExceptionUtils.COUPON_FAILED + " Exception: " + e.getLocalizedMessage());
        }
    }

    public void openAllOffersCompany() throws InterruptedException {
        Document document = Jsoup.parse(builder.getDriver().getPageSource());
        final String urlAllOffers = document.getElementById("btn-see-all-coupons").attr("ng-href");
        this.builder.access(URL_BASE + urlAllOffers);
        Thread.sleep(2000);
    }

    public void generateCouponByPosition(By buttonXpath) throws InterruptedException {
       this.actions.moveToElement(builder.getDriver().findElement(buttonXpath)).click().build().perform();

       Thread.sleep(2000);

       this.actions.moveToElement(builder.getDriver().findElement(By.xpath("//*[@id=\"buildLomadeeLinkBtn\"]")))
               .click()
               .build()
               .perform();

       Thread.sleep(2000);

       final By xpathNewsLetter = By.xpath("//*[@id=\"onesignal-slidedown-cancel-button\"]");
       if(this.builder.getDriver().findElements(xpathNewsLetter).size() != 0){
           this.builder.getDriver().findElement(xpathNewsLetter).click();
       }

       Thread.sleep(1500);

       //Personalizar
       this.builder.click(By.xpath("//*[@id=\"ComponentModalLomadeezador\"]/div/div/div[1]/button"));

       Thread.sleep(1500);
    }



}
