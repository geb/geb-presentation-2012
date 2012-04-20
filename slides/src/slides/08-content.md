# Content DSL

Reuse FTW

## Content DSL

    class GoogleResultsPage extends Page {
        static content = {
            results { $("li.g") }
            result { i -> results[i] }
            resultLink { i -> result(i).find("a.l", 0) }
            firstResultLink { resultLink(0) }
        }
    }

Content definitions can *build* upon each other.

Content definitions are actually *templates*.

## Optional Content

    class OptionalPage extends Page {
        static content = {
            errorMsg(required: false) { $("p.errorMsg") }
        }
    }

By default, Geb will error if the content you select doesn't exist.

The “`required`” option disables this check.

## Dynamic Content

    class DynamicPage extends Page {
        static content = {
            errorMsg(wait: true) { $("p.errorMsg") }
        }
    }

Geb will wait for some time for this content to appear.

By default, it will look for it every 100ms for 5s before giving up. This is highly configurable.

Same semantics as the `waitFor {}` method that can be used anywhere.