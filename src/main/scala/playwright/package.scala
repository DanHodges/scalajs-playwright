import playwright.Playwright.{BrowserTypeJS, browsers}

package object playwright {
  val chromium: BrowserTypeJS = browsers.chromium
  val firefox: BrowserTypeJS = browsers.firefox
  val safari: BrowserTypeJS = browsers.safari
}
