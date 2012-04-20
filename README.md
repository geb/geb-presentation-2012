# Geb Presentation

Still in development.

## Slides

### Building

    ./gradlew slideshow

This will create the slideshow @ `slides/build/slideshow` and you can open index.html in there.

### Presenting

The following keys performing functions when presenting:

* `→` - forward slide
* `←` - back slide
* `n` - show presenter notes
* `c` - clone (creates a new window that changes slides with the master)
* `r` - change target resolution

### Content

The content is built from the mardkdown files in `slides/src/slides`. These files are concatenated together in alphabetical order and converted to html.

The initial transforms are handled by `slides/gradle/enhanceHtml.gradle`, which adds some presentation like structure. 

It is then transformed further to use the slideshow display technology by `slides/gradle/slideshow.gradle`.

The head template for the slideshow is @ `slides/src/slideshow.html`.

All content in `slides/src/slideshow/content` gets included in the packaged slideshow.

#### Syntax Highlighting

All code blocks are syntax highlighted. Right now it's hardcoded to Groovy but we can make it smarter if need be.

#### Styling

The contents of the style directory get copied to the `style` directory of the packaged slideshow. All `*.less` files in `src/style` get pumped through [LessCSS](http://lesscss.org/) and output with an additional `.css` extension.  

### Downloads

This build downloads some additional tools that it needs and stores them in `slides/downloads`. These don't get deleted by `clean`. You can run `cleanDownloads` to remove them.