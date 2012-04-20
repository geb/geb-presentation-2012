package demo1

import geb.*
import content.*

Browser.drive {
    to GoogleHomePage
    logonToWirelessIfNecessary(delegate)
    at GoogleHomePage
    search.forTerm "wikipedia"
    at GoogleResultsPage
    assert firstResultLink.text() == "Wikipedia"
    firstResultLink.click()
    waitFor { at WikipediaPage }
}.quit()

def browser = new Browser()
browser.to GoogleHomePage
logonToWirelessIfNecessary(browser)
browser.at GoogleHomePage
browser.search.forTerm "wikipedia"
browser.at GoogleResultsPage
browser.assert firstResultLink.text() == "Wikipedia"
browser.firstResultLink.click()
browser.waitFor { at WikipediaPage }

def logonToWirelessIfNecessary(browser) {
    browser.with {
        if (title == "internet hotspot > login") {
            find("form", name: "login").with {
                username = "epam-your-mind"
                find("input[type=submit]", text: iContains("login")).click()
            }

        }
    }
}
