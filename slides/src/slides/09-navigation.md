# Navigation

Getting around

## The to() method

    class GoogleHomePage extends Page {
        static url = "http://google.com/ncr"
    }
    
Pages can define a url that defines the page location.

The `to()` method sends the browser there and sets that as the current page object.

    to GoogleHomePage

The page url can be relative (will be resolved against a config driven base).

There are advanced parameterisation options too.

## Content based navigation

    class FrontPage {
        static content = {
            aboutUsLink(to: AboutUsPage) { 
                $("div#nav ul li a", text: iStartsWith("About Us"))
            }
        }
    }

When this content is clicked, the underlying page will be changed implicitly.

    to FrontPage
    aboutUsLink.click()
    page instanceof AboutUsPage