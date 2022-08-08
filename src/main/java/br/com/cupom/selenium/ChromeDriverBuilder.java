package br.com.cupom.selenium;

import br.com.cupom.exception.SeleniumException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverBuilder {
    private WebDriver driver;

    public ChromeDriverBuilder() throws SeleniumException {

       try {
           System.setProperty("webdriver.chrome.driver", System.getenv("CHROMEDRIVER"));
           final ChromeOptions options = new ChromeOptions();
           options.setBinary(System.getenv("CHROMEPATH"));
           options.addArguments("--no-sandbox");
           options.addArguments("--headless");
           options.addArguments("--window-size=1920,1080");
           this.driver = new ChromeDriver(options);
       }catch (Exception e){
           if(this.driver != null){
               this.driver.quit();
           }
           throw new SeleniumException("Failed to open selenium");
       }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void access(String url){
        this.driver.get(url);
    }

    public void click(By element){
        this.driver.findElement(element).click();
    }

    public void sendKeys(By element, String keys) throws InterruptedException {
        for(char c : keys.toCharArray()) {
            Thread.sleep(500);
            this.driver.findElement(element).sendKeys(String.valueOf(c));
        }
    }
}
