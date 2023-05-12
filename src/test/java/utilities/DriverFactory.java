package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {
    //Do not allow to initialize the class from outside
    private DriverFactory (){
        //Do nothing---- empty constructor
    }
    private static final DriverFactory df= new DriverFactory();
    public static DriverFactory getDf(){
        return df;
    }
    //ThreadLocal driver object for webDriver
    ThreadLocal<WebDriver> driver = ThreadLocal.withInitial(() ->{
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        return new ChromeDriver(options);
    });

    //call this method to get the driver object and launch the browser
    public WebDriver getDriver(){

        return driver.get();
    }

    //Quits the driver and close the browser
    public void removeDriver(){
        driver.get().quit();
        driver.remove();
    }
}
