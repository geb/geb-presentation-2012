# Groovy

[http://groovy-lang.org](http://groovy-lang.org)

## Dynamic JVM Language 

Groovy is…

* Compiled, never interpreted
* Dynamic, optionally typed
* 99% Java syntax compatible
* Concise, clear and pragmattic
* Great for DSLs
* A comfortable Java alternative for most

## Required Groovy Knowledge

You don't need to be a Groovy ninja to use Geb.

Groovy knowledge can definitely help when things go wrong though.

## Geb & Groovy

Geb uses Groovy's dynamism to remove boilerplate.

    import geb.*
    
    Browser.drive {
        to GoogleHomePage
        at GoogleHomePage
        search.forTerm "wikipedia"
        at GoogleResultsPage
        assert firstResultLink.text() == "Wikipedia"
        firstResultLink.click()
        waitFor { at WikipediaPage }
    }

\* As of Geb 0.7.0 also uses Groovy's compile time transfomations.

# Demo

Google for Wikipedia