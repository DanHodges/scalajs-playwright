package playwright

import scala.concurrent.{ExecutionContext, Future}
import scala.scalajs.js
import scala.scalajs.js.Thenable.Implicits.thenable2future
import scala.scalajs.js.annotation.{JSImport, JSName}
import scala.scalajs.js.|
import org.scalajs.dom.Element

object Facade {

  @js.native
  trait ConnectOptions extends js.Object {
    var slowMo: js.UndefOr[Double]
    var headless: js.UndefOr[Boolean]
    var timeout: js.UndefOr[Double]
    var wsEndpoint: String
  }

  object ConnectOptions {

    def apply(headless: Boolean, slowMo: Double, timout: Double, wsEndpoint: String): LaunchOptions =
      js.Dynamic.literal(headless = headless).asInstanceOf[LaunchOptions]
  }

  @js.native
  @JSImport("playwright", "LaunchOptions")
  class LaunchOptions extends js.Object {
    var _videosPath: js.UndefOr[String]      = js.native
    var args: js.UndefOr[js.Array[String]]   = js.native
    var chromiumSandbox: js.UndefOr[Boolean] = js.native
    var devtools: js.UndefOr[Boolean]        = js.native
    var downloadsPath: js.UndefOr[String]    = js.native
    //    var env: js.UndefOr[StringDictionary[String | Double | Boolean]]              = js.native
    var executablePath: js.UndefOr[String] = js.native
    //    var firefoxUserPrefs: js.UndefOr[StringDictionary[String | Double | Boolean]] = js.native
    var handleSIGHUP: js.UndefOr[Boolean]                         = js.native
    var handleSIGINT: js.UndefOr[Boolean]                         = js.native
    var handleSIGTERM: js.UndefOr[Boolean]                        = js.native
    var headless: js.UndefOr[Boolean]                             = js.native
    var ignoreDefaultArgs: js.UndefOr[Boolean | js.Array[String]] = js.native
    //    var logger: js.UndefOr[Logger]                                                = js.native
    //    var proxy: js.UndefOr[Bypass]                                                 = js.native
    var slowMo: js.UndefOr[Double]  = js.native
    var timeout: js.UndefOr[Double] = js.native
  }

  object LaunchOptions {

    def apply(headless: Boolean, slowMo: Double): LaunchOptions =
      js.Dynamic.literal(headless = headless).asInstanceOf[LaunchOptions]
  }

  @js.native
  trait BrowserTypeJS extends js.Object {
    def name(): String = js.native

    @JSName("connect")
    def connectJS(options: ConnectOptions): js.Promise[BrowserJS] = js.native

    @JSName("launch")
    def launchJS(): js.Promise[BrowserJS] = js.native

    @JSName("launch")
    def launchJS(options: LaunchOptions): js.Promise[BrowserJS] = js.native
  }

  trait BrowserType {
    def name(): String
    def connect(options: ConnectOptions): Future[Browser]
    def launch(): Future[Browser]
    def launch(options: LaunchOptions): Future[Browser]
  }

  object BrowserType {

    implicit class Friendly(raw: BrowserTypeJS)(implicit ec: ExecutionContext) extends BrowserType {
      def name(): String                                    = raw.name()
      def connect(options: ConnectOptions): Future[Browser] = raw.connectJS(options).map(x => x: Browser)

      def launch(): Future[Browser]                       = raw.launchJS().map(x => x: Browser)
      def launch(options: LaunchOptions): Future[Browser] = raw.launchJS(options).map(x => x: Browser)
    }
  }

  trait CDPSession extends js.Object
  trait Categories extends js.Object
  trait Buffer     extends js.Object

  @js.native
  trait BrowserJS extends js.Object {

    @JSName("newContext")
    def newContextJS(): js.Promise[BrowserContextJS] = js.native
  }

  trait Browser {
    def newContext(): Future[BrowserContext]
  }

  object Browser {

    implicit class Friendly(raw: BrowserJS)(implicit ec: ExecutionContext) extends Browser {
      def newContext(): Future[BrowserContext] = raw.newContextJS().map(x => x: BrowserContext)
    }
  }

  @js.native
  trait BrowserContextJS extends js.Object {

    @JSName("newPage")
    def newPageJS(): js.Promise[PageJS] = js.native
  }

  trait BrowserContext {
    def newPage(): Future[Page]
  }

  object BrowserContext {

    implicit class Friendly(raw: BrowserContextJS)(implicit ec: ExecutionContext) extends BrowserContext {
      def newPage(): Future[Page] = raw.newPageJS().map(page => page: Page)
    }
  }

  @js.native
  @JSImport("playwright", JSImport.Namespace)
  object browsers extends js.Object {
    val chromium: BrowserTypeJS = js.native
    val firefox: BrowserTypeJS = js.native
    val safari: BrowserTypeJS = js.native
  }

  @js.native
  @JSImport("playwright", "ElementHandle")
  class ElementHandleJS extends js.Object {

    @JSName("click")
    def clickJS(selector: String): js.Promise[Unit] = js.native

    @JSName("fill")
    def fillJS(selector: String, content: String): js.Promise[Unit] = js.native

    @JSName("waitForSelector")
    def waitForSelectorJS(selector: String, fill: String): js.Promise[Unit] = js.native

  }

  trait ElementHandle {

    def click(selector: String): Future[Unit]
    def fill(selector: String, content: String): Future[Unit]
    def waitForSelector(selector: String, fill: String): Future[Unit]
  }

  object ElementHandle {

    implicit class Friendly(raw: ElementHandleJS)(implicit ec: ExecutionContext) extends ElementHandle {
      def click(selector: String): Future[Unit]                         = raw.clickJS(selector).toFuture
      def fill(selector: String, content: String): Future[Unit]         = raw.fillJS(selector, content).toFuture
      def waitForSelector(selector: String, fill: String): Future[Unit] = raw.waitForSelectorJS(selector, fill).toFuture
    }
  }

  @js.native
  @JSImport("playwright", "Page")
  class PageJS extends js.Object {

    def $(s: String): js.UndefOr[Element] = js.native

    @JSName("fill")
    def fillJS(selector: String, value: String): js.Promise[Unit] = js.native

    @JSName("click")
    def clickJS(selector: String): js.Promise[Unit] = js.native

    @JSName("waitForSelector")
    def waitForSelectorJS(selector: String): js.Promise[ElementHandleJS] = js.native

    @JSName("goto")
    def gotoJS(selector: String): js.Promise[Unit] = js.native

    @JSName("title")
    def titleJS(): js.Promise[String] = js.native
  }

  trait Page {

    def find(s: String): Option[Element]

    def fill(selector: String, value: String): Future[Unit]

    def click(selector: String): Future[Unit]

    def waitForSelector(selector: String): Future[ElementHandle]

    def goto(path: String): Future[Unit]

    def title(): Future[String]

  }

  object Page {

    implicit class Friendly(raw: PageJS)(implicit ec: ExecutionContext) extends Page {

      def find(s: String): Option[Element] = raw.$(s).toOption

      def fill(selector: String, value: String): Future[Unit] = raw.fillJS(selector, value).toFuture

      def click(selector: String): Future[Unit] = raw.clickJS(selector).toFuture

      def waitForSelector(selector: String): Future[ElementHandle] =
        raw.waitForSelectorJS(selector).map(x => x: ElementHandle)

      def goto(path: String): Future[Unit] = raw.gotoJS(path).toFuture

      def title(): Future[String] = raw.titleJS().toFuture
    }
  }
}
