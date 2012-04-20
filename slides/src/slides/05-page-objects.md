# Page Objects

**The** key to not pulling your hair out when dealing with web tests.

## What are they?

In a phrase: Domain Modelling.

By modelling and creating *abstractions*, we can isolate implementation detail.

    $("input[name=username]").value("user")
    $("input[name=username]").value("password")
    $("input[type=submit]").click()

Is far more fragile than this…

    void login(String username, String password) {
        $("input[name=username]").value(username)
        $("input[name=username]").value(password)
        $("input[type=submit]").click()
    }

    login("user", "password")

## Just good programming

It's the application of trusted principles; encapsulation and reuse.

Not new at all, but new to the world of web testing/automation.

Not just about modelling “pages”. It's about modelling all kinds of things in the domain of a user's actions online.

Just giving symbolic names to page content is a great start.

## Browser has-a Page

    Browser.drive {
        to GoogleHomePage
        at GoogleHomePage
        search.forTerm "wikipedia"
        at GoogleResultsPage
        assert firstResultLink.text() == "Wikipedia"
        firstResultLink.click()
        waitFor { at WikipediaPage }
    }

The `to()` and `click()` methods are changing the underlying page.

You can refer to the current page's content and methods just by name.

---

This is the first introduction to all of the dynamic dispatch stuff. Might deserve a better treatment.

## Geb's Page Objects

Geb builds the Page Object pattern directly into the framework (though it is optional).

    import geb.*

    class GoogleHomePage extends Page {
        static url = "http://google.com/ncr"
        static at = { title == "Google" }
        static content = {
            search { module GoogleSearchModule }
        }
    }

## Geb's Page Objects

Features the “Content DSL” for naming content in a dynamic and powerful way.

    import geb.*

    class GoogleResultsPage extends Page {
        static at = { waitFor { title.endsWith("Google Search") } }
        static content = {
            search { module GoogleSearchModule }
            results { $("li.g") }
            result { i -> results[i] }
            resultLink { i -> result(i).find("a.l", 0) }
            firstResultLink { resultLink(0) }
        }
    }

## Geb's Page Objects

Very lightweight, minimum requirements are low.

    import geb.*

    class WikipediaPage extends Page {
        static at = { title == "Wikipedia" }
    }

## Modules

Modules are repeating and/or reappearing content.

    import geb.*

    class GoogleSearchModule extends Module {
        static content = {
            field { $("input", name: "q") }
            button(to: GoogleResultsPage) { btnG() }
        }

        void forTerm(term) {
            field.value term
            waitFor { button.displayed }
            button.click()
        }
    }

## Modules

    <table id="book-results">
      <thead>
        <tr>
          <th>Title</th><th>Author</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>Zero History</td><td>William Gibson</td>
        </tr>
        <tr>
          <td>The Evolutionary Void</td><td>Peter F. Hamilton</td>
        </tr>
      </tbody>
    </table>

Modules can be used for repeating content.

## Modules

Modules have a *base*, from which all content lookups are relative.

    class BooksPage extends Page {
      static content = {
        bookResults { 
          module BookRow, $("table#book-results tbody tr", it) 
        }
      }
    }

    class BookRow extends Module {
      static content = {
        cell { $("td", it) }
        title { cell(0).text() }
        author { cell(1).text() }
      }
    }

## Modules

We now have a model for a row in our table.

    expect: 
    bookResults(0).title == "Zero History" 
    bookResults(1).author == "Peter F. Hamilton

Can be used for any reused/repeating content.

**Note:** talking about domain objects, not HTML tables and rows.

## Inheritance

Pages (and modules) can be arranged in inheritance hierarchies.

    class Footer extends Module {
        static content = {
            copyright { $("p.copyright") }
        }
    }
    class StandardPage extends Page {
        static content = {
            footer { module Footer }
        }
    }
    class FrontPage extends StandardPage {}

The front page will *inherit* the “footer” content definition.
