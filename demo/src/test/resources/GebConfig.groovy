import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.remote.DesiredCapabilities

driver = "firefox" // firefox is default

reportsDir = new File("build/geb-reports")

waiting {
	timeout = 2
}

environments {

    htmlunit = {
        def driver = new HtmlUnitDriver()
        driver.javascriptEnabled = true
        driver
    }

	chrome {
		driver = "chrome"
	}
	
	firefox {
		driver = { new FirefoxDriver() }
	}

    ie {
        driver = {
            def server = new URL("http://172.16.229.131:4444/wd/hub")
            def browserProfile = DesiredCapabilities.internetExplorer()
            new RemoteWebDriver(server, browserProfile)
        }
    }

}
