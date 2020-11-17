package playwright

import scala.concurrent.{ExecutionContext, Future}
import scala.scalajs.js
import scala.scalajs.js.JSConverters.JSRichIterableOnce
import scala.scalajs.js.Thenable.Implicits.thenable2future
import scala.scalajs.js.annotation.{JSImport, JSName}
import scala.scalajs.js.|

object Facade {

  @js.native
  trait ConnectOptions extends js.Object {
    var headless: js.UndefOr[Boolean]
    var slowMo: js.UndefOr[Double]
    var timeout: js.UndefOr[Double]
    var wsEndpoint: String
  }

  object ConnectOptions {

    def apply(headless: Boolean, slowMo: Double, timout: Double, wsEndpoint: String): LaunchOptions =
      js.Dynamic.literal(headless = headless).asInstanceOf[LaunchOptions]
  }

  @js.native
  trait MouseUpDownOptions extends js.Object {
    var button: String // "left", "right", "middle"
    var clickCount: Double
  }

  object MouseUpDownOptions {
    val Default = js.Dynamic.literal(button = "left", clickCount = 1).asInstanceOf[MouseUpDownOptions]

    def apply(button: String, clickCount: Double): MouseUpDownOptions =
      js.Dynamic.literal(button = button, clickCount = clickCount).asInstanceOf[MouseUpDownOptions]
  }

  @js.native
  trait MouseClickOptions extends js.Object {
    var button: String // "left", "right", "middle"
    var clickCount: Double
    var delay: Double
  }

  object MouseClickOptions {
    val Default = js.Dynamic.literal(button = "left", clickCount = 1, delay = 0).asInstanceOf[MouseClickOptions]

    def apply(button: String, clickCount: Double, delay: Double): MouseClickOptions =
      js.Dynamic.literal(button = button, clickCount = clickCount, delay = delay).asInstanceOf[MouseClickOptions]
  }

  @js.native
  trait MouseDoubleClickOptions extends js.Object {
    var button: String // "left", "right", "middle"
    var delay: Double
  }

  object MouseDoubleClickOptions {
    val Default = js.Dynamic.literal(button = "left", delay = 0).asInstanceOf[MouseDoubleClickOptions]

    def apply(button: String, clickCount: Double, delay: Double): MouseDoubleClickOptions =
      js.Dynamic.literal(button = button, delay = delay).asInstanceOf[MouseDoubleClickOptions]
  }

  @js.native
  trait MouseMoveOptions extends js.Object {
    var steps: Double
  }

  object MouseMoveOptions {
    val Default = js.Dynamic.literal(steps = 1).asInstanceOf[MouseMoveOptions]

    def apply(steps: Double): MouseMoveOptions =
      js.Dynamic.literal(steps = 1).asInstanceOf[MouseMoveOptions]

  }

  @js.native
  trait MouseJS extends js.Object {

    @JSName("click")
    def clickJS(x: Double, y: Double, options: MouseClickOptions): js.Promise[Unit] = js.native

    @JSName("dblclick")
    def dblclickJS(x: Double, y: Double, options: MouseDoubleClickOptions): js.Promise[Unit] = js.native

    @JSName("down")
    def downJS(options: MouseUpDownOptions): js.Promise[Unit] = js.native

    @JSName("move")
    def moveJS(x: Double, y: Double, options: MouseMoveOptions): js.Promise[Unit] = js.native

    @JSName("up")
    def upJS(options: MouseUpDownOptions): js.Promise[Unit] = js.native
  }

  trait Mouse {
    def click(x: Double, y: Double, options: MouseClickOptions): Future[Unit]
    def dblclick(x: Double, y: Double, options: MouseDoubleClickOptions): Future[Unit]
    def down(options: MouseUpDownOptions): Future[Unit]
    def move(x: Double, y: Double, options: MouseMoveOptions): Future[Unit]
    def up(options: MouseUpDownOptions): Future[Unit]
  }

  object Mouse {
    implicit class Friendly(raw: MouseJS) extends Mouse {
      def click(x: Double, y: Double): Future[Unit]                                      = raw.clickJS(x, y, MouseClickOptions.Default)
      def click(x: Double, y: Double, options: MouseClickOptions): Future[Unit]          = raw.clickJS(x, y, options)
      def dblclick(x: Double, y: Double): Future[Unit]                                   = raw.dblclickJS(x, y, MouseDoubleClickOptions.Default)
      def dblclick(x: Double, y: Double, options: MouseDoubleClickOptions): Future[Unit] = raw.dblclickJS(x, y, options)
      def down(): Future[Unit]                                                           = raw.downJS(MouseUpDownOptions.Default)
      def down(options: MouseUpDownOptions): Future[Unit]                                = raw.downJS(options)
      def move(x: Double, y: Double): Future[Unit]                                       = raw.moveJS(x, y, MouseMoveOptions.Default)
      def move(x: Double, y: Double, options: MouseMoveOptions): Future[Unit]            = raw.moveJS(x, y, options)
      def up(): Future[Unit]                                                             = raw.upJS(MouseUpDownOptions.Default)
      def up(options: MouseUpDownOptions): Future[Unit]                                  = raw.upJS(options)
    }
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

    def apply(height: Double, width: Double): ViewportSize =
      js.Dynamic.literal(height = height, width = width).asInstanceOf[ViewportSize]
  }

  @js.native
  trait BrowserContextOptions extends js.Object {
    val ignoreHTTPSErrors: js.UndefOr[Boolean]
    val viewport: js.UndefOr[ViewportSize]
  }

  object BrowserContextOptions {

    def apply(ignoreHTTPSErrors: Boolean): BrowserContextOptions =
      js.Dynamic.literal(ignoreHTTPSErrors = ignoreHTTPSErrors).asInstanceOf[BrowserContextOptions]

    def apply(ignoreHTTPSErrors: Boolean, viewport: ViewportSize): BrowserContextOptions =
      js.Dynamic
        .literal(ignoreHTTPSErrors = ignoreHTTPSErrors, viewport = viewport)
        .asInstanceOf[BrowserContextOptions]
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
      def close(): Future[Unit]                = raw.closeJS()
      def newContext(): Future[BrowserContext] = raw.newContextJS().map(x => x: BrowserContext)

      def newContext(contextOptions: BrowserContextOptions): Future[BrowserContext] =
        raw.newContextJS(contextOptions).map(x => x: BrowserContext)
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
    val firefox: BrowserTypeJS  = js.native
    val webkit: BrowserTypeJS   = js.native
  }

  @js.native
  trait ElementHandleJS extends js.Object {

    def $(s: String): js.Promise[js.UndefOr[ElementHandleJS]] = js.native

    @JSName("innerHTML")
    def innerHTMLJS(): js.Promise[String] = js.native

    @JSName("innerText")
    def innerTextJS(): js.Promise[String] = js.native

    @JSName("textContent")
    def textContentJS(): js.Promise[String] = js.native

    @JSName("textContent")
    def textContentJS(selector: String): js.Promise[String] = js.native

    @JSName("getAttribute")
    def getAttributeJS(name: String): js.Promise[js.UndefOr[String]] = js.native

    @JSName("click")
    def clickJS(selector: String): js.Promise[Unit] = js.native

    @JSName("fill")
    def fillJS(selector: String, content: String): js.Promise[Unit] = js.native

    @JSName("waitForSelector")
    def waitForSelectorJS(selector: String): js.Promise[js.UndefOr[ElementHandleJS]] = js.native

    @JSName("waitForSelector")
    def waitForSelectorJS(selector: String, fill: String): js.Promise[js.UndefOr[ElementHandleJS]] = js.native

  }

  @js.native
  trait ResponseJS extends js.Object {
//    @JSName("body")
//    def bodyJS(): js.Promise[Buffer] = js.native
//    @JSName("finished")
//    def finishedJS(): js.Promise[js.UndefOr[Error]]

//    @JSName("json")
//    def jsonJS(): js.Promise[Serializable]

    def ok(): Boolean = js.native

    def status(): Double = js.native

    def statusText(): String = js.native

    def url(): String = js.native
  }

  trait Response {
    def ok: Boolean
    def status: Double
    def statusText: String
    def url: String
  }

  object Response {
    implicit class Friendly(raw: ResponseJS) extends Response {
      def ok: Boolean        = raw.ok()
      def status: Double     = raw.status()
      def statusText: String = raw.statusText()
      def url: String        = raw.url()
    }
  }

  trait ElementHandle {

    def find(s: String): Future[Option[ElementHandle]]
    def innerText(): Future[String]
    def innerHTML(): Future[String]
    def textContent(): Future[String]
    def textContent(selector: String): Future[String]
    def getAttribute(name: String): Future[Option[String]]
    def click(selector: String): Future[Unit]
    def fill(selector: String, content: String): Future[Unit]
    def waitForSelector(selector: String): Future[Option[ElementHandle]]
    def waitForSelector(selector: String, fill: String): Future[Option[ElementHandle]]
  }

  object ElementHandle {

    implicit class Friendly(raw: ElementHandleJS)(implicit ec: ExecutionContext) extends ElementHandle {
      def find(s: String): Future[Option[ElementHandle]] = raw.$(s).map(x => x.toOption.map(x => x: ElementHandle))
      def innerHTML(): Future[String]                    = raw.innerHTMLJS().map(x => x: String)
      def innerText(): Future[String]                    = raw.innerTextJS().map(x => x: String)
      def textContent(): Future[String]                  = raw.textContentJS().map(x => x: String)
      def textContent(selector: String): Future[String]  = raw.textContentJS(selector).map(x => x: String)

      def getAttribute(name: String): Future[Option[String]] =
        raw.getAttributeJS(name).map(x => if (x == null) None else x.toOption)

      def click(selector: String): Future[Unit]                 = raw.clickJS(selector)
      def fill(selector: String, content: String): Future[Unit] = raw.fillJS(selector, content)

      def waitForSelector(selector: String): Future[Option[ElementHandle]] =
        raw.waitForSelectorJS(selector).map(x => if (x == null) None else x.toOption.map(x => x: ElementHandle))

      def waitForSelector(selector: String, fill: String): Future[Option[ElementHandle]] =
        raw.waitForSelectorJS(selector, fill).map(x => if (x == null) None else x.toOption.map(x => x: ElementHandle))
    }
  }

  @js.native
  trait PageJS extends js.Object {

    def $(s: String): js.Promise[js.UndefOr[ElementHandleJS]] = js.native

    @JSName("fill")
    def fillJS(selector: String, value: String): js.Promise[Unit] = js.native

    @JSName("click")
    def clickJS(selector: String): js.Promise[Unit] = js.native

    @JSName("goto")
    def gotoJS(selector: String): js.Promise[js.UndefOr[ResponseJS]] = js.native

    @JSName("title")
    def titleJS(): js.Promise[String] = js.native

    @JSName("getAttribute")
    def getAttributeJS(selector: String, name: String): js.Promise[js.UndefOr[String]] = js.native

    @JSName("textContent")
    def textContentJS(): js.Promise[String] = js.native

    @JSName("textContent")
    def textContentJS(selector: String): js.Promise[String] = js.native

    @JSName("waitForSelector")
    def waitForSelectorJS(selector: String): js.Promise[js.UndefOr[ElementHandleJS]] = js.native

    @JSName("waitForSelector")
    def waitForSelectorJS(selector: String, fill: String): js.Promise[js.UndefOr[ElementHandleJS]] = js.native

    @JSName("selectOption")
    def selectOptionJS(selector: String, value: String): js.Promise[js.Array[String]] = js.native

    @JSName("selectOption")
    def selectOptionJS(selector: String, values: js.Array[String]): js.Promise[js.Array[String]] = js.native

    @JSName("selectOption")
    def selectOptionJS(selector: String, label: js.Dynamic): js.Promise[js.Array[String]] = js.native

    @JSName("type")
    def `type`(selector: String, text: String): js.Promise[Unit] = js.native

    def press(selector: String, text: String): js.Promise[Unit] = js.native

    def mouse(): Mouse = js.native
  }

  trait Page {

    def find(s: String): Future[Option[ElementHandle]]

    def fill(selector: String, value: String): Future[Unit]

    def click(selector: String): Future[Unit]

    def textContent(): Future[String]

    def mouse(): Mouse

    def textContent(selector: String): Future[String]

    def goto(path: String): Future[Option[Response]]

    def getAttribute(selector: String, name: String): Future[Option[String]]

    def title(): Future[String]

    def waitForSelector(selector: String): Future[Option[ElementHandle]]

    def waitForSelector(selector: String, fill: String): Future[Option[ElementHandle]]

    def selectOption(selector: String, value: String): Future[Seq[String]]

    def selectMultipleOptions(selector: String, values: Seq[String]): Future[Seq[String]]

    def selectOptionByLabel(selector: String, label: String): Future[Seq[String]]

    def `type`(selector: String, text: String): Future[Unit]

    def press(selector: String, text: String): Future[Unit]

  }

  object Page {

    implicit class Friendly(raw: PageJS)(implicit ec: ExecutionContext) extends Page {

      def find(s: String): Future[Option[ElementHandle]] =
        raw.$(s).map(x => if (x == null) None else x.toOption.map(x => x: ElementHandle))

      def fill(selector: String, value: String): Future[Unit] = raw.fillJS(selector, value)

      def click(selector: String): Future[Unit] = raw.clickJS(selector)

      def textContent(): Future[String] = raw.textContentJS().map(x => x: String)

      def textContent(selector: String): Future[String] = raw.textContentJS(selector).map(x => x: String)

      def goto(path: String): Future[Option[Response]] =
        raw.gotoJS(path).map(x => if (x == null) None else x.toOption.map(r => r: Response))

      def getAttribute(selector: String, name: String): Future[Option[String]] =
        raw.getAttributeJS(selector, name).map(x => if (x == null) None else x.toOption)

      def selectOption(selector: String, value: String): Future[Seq[String]] =
        raw.selectOptionJS(selector, value).map(_.toSeq)

      def selectMultipleOptions(selector: String, values: Seq[String]): Future[Seq[String]] =
        raw.selectOptionJS(selector, values.toJSArray).map(_.toSeq)

      def selectOptionByLabel(selector: String, label: String): Future[Seq[String]] =
        raw.selectOptionJS(selector, js.Dynamic.literal(label = label)).map(_.toSeq)

      def `type`(selector: String, text: String): Future[Unit] = raw.`type`(selector, text)

      def press(selector: String, text: String): Future[Unit] = raw.press(selector, text)

      def title(): Future[String] = raw.titleJS().map(x => x: String)

      def waitForSelector(selector: String): Future[Option[ElementHandle]] =
        raw.waitForSelectorJS(selector).map(x => if (x == null) None else x.toOption.map(x => x: ElementHandle))

      def waitForSelector(selector: String, fill: String): Future[Option[ElementHandle]] =
        raw.waitForSelectorJS(selector, fill).map(x => if (x == null) None else x.toOption.map(x => x: ElementHandle))

      def mouse(): Mouse = raw.mouse()
    }
  }
}
