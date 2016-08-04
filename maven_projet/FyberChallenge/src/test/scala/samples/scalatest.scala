package samples

import org.scalatest.FunSuite
import com.data.test.fyber.App

/**
 * @author Kumar
 *
 */
class Test extends FunSuite {
  val period=60
  test("Test for 1st Record") {
    assert(App.processRecord("1355270609	1.80215", period) == "1355270609	1.80215	1	1.80215	1.80215	1.80215")
  }
  
  test("Test for 2nd Record") {
    assert(App.processRecord("1355270621	1.80185", period) == "1355270621	1.80185	2	3.604	1.80185	1.80215")
  }
  
  test("Test for 3rd Record") {
    assert(App.processRecord("1355270646	1.80195", period) == "1355270646	1.80195	3	5.40595	1.80185	1.80215")
  }
  
  test("Test for 4th Record") {
    assert(App.processRecord("1355270702	1.80225", period) == "1355270702	1.80225	2	3.6042	1.80195	1.80225")
  }
  
  test("Test for 5th Record") {
    assert(App.processRecord("1355270702	1.80215", period) == "1355270702	1.80215	3	5.40635	1.80195	1.80225")
  }
}