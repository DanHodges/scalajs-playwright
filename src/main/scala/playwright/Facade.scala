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
  trait LaunchOptions extends js.Object {
    var _videosPath: js.UndefOr[String]
    var args: js.UndefOr[js.Array[String]]
    var chromiumSandbox: js.UndefOr[Boolean]
    var devtools: js.UndefOr[Boolean]
    var downloadsPath: js.UndefOr[String]
    var executablePath: js.UndefOr[String]
    var handleSIGHUP: js.UndefOr[Boolean]
    var handleSIGINT: js.UndefOr[Boolean]
    var handleSIGTERM: js.UndefOr[Boolean]
    var headless: js.UndefOr[Boolean]
    var ignoreDefaultArgs: js.UndefOr[Boolean | js.Array[String]]
    var slowMo: js.UndefOr[Double]  = js.native
    var timeout: js.UndefOr[Double] = js.native
  }

  object LaunchOptions {

    def apply(headless: Boolean, slowMo: Double): LaunchOptions =
      js.Dynamic.literal(headless = headless).asInstanceOf[LaunchOptions]
  }

  @js.native
  trait ViewportSize extends js.Object {
    val height: Double
    val width: Double
  }
  object ViewportSize {
    def apply(height: Double, width: Double): ViewportSize = js.Dynamic.literal(height = height, width = width).asInstanceOf[ViewportSize]
  }

  @js.native
  trait BrowserContextOptions extends js.Object {
    val ignoreHTTPSErrors: js.UndefOr[Boolean]
    val viewportSize: js.UndefOr[ViewportSize]
  }

  object BrowserContextOptions {

    def apply(ignoreHTTPSErrors: Boolean): BrowserContextOptions =
      js.Dynamic.literal(ignoreHTTPSErrors = ignoreHTTPSErrors).asInstanceOf[BrowserContextOptions]

    def apply(ignoreHTTPSErrors: Boolean, viewportSize: ViewportSize): BrowserContextOptions =
      js.Dynamic.literal(ignoreHTTPSErrors = ignoreHTTPSErrors, viewportSize = viewportSize).asInstanceOf[BrowserContextOptions]
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

    @JSName("close")
    def closeJS(): js.Promise[Unit] = js.native

    @JSName("newContext")
    def newContextJS(): js.Promise[BrowserContextJS] = js.native

    @JSName("newContext")
    def newContextJS(contextOptions: BrowserContextOptions): js.Promise[BrowserContextJS] = js.native
  }

  trait Browser {
    def close(): Future[Unit]
    def newContext(): Future[BrowserContext]
    def newContext(contextOptions: BrowserContextOptions): Future[BrowserContext]
  }

  object Browser {

    implicit class Friendly(raw: BrowserJS)(implicit ec: ExecutionContext) extends Browser {
      def close(): Future[Unit] = raw.closeJS().toFuture
      def newContext(): Future[BrowserContext] = raw.newContextJS().map(x => x: BrowserContext)
      def newContext(contextOptions: BrowserContextOptions): Future[BrowserContext] = raw.newContextJS(contextOptions).map(x => x: BrowserContext)
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
    val webkit: BrowserTypeJS = js.native
  }

  @js.native
  trait ElementHandleJS extends js.Object {

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
  trait PageJS extends js.Object {

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
