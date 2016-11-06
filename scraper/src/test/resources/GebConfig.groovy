import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.openqa.selenium.phantomjs.PhantomJSDriver

environments {

    chrome {
        driver = { new ChromeDriver() }
    }

    firefox {
        driver = { new FirefoxDriver() }
    }

    phantomJs {
        driver = { new PhantomJSDriver() }
    }

    htmlUnit {
        driver = { new HtmlUnitDriver() }
    }
}

baseUrl = 'http://2017.greachconf.com'
