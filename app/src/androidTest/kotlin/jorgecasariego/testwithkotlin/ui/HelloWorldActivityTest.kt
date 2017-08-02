package jorgecasariego.testwithkotlin.ui

import jorgecasariego.testwithkotlin.R
import jorgecasariego.testwithkotlin.ui.framework.AcceptanceTest
import org.junit.Test

/**
 * Created by jorgecasariego on 2/8/17.
 */
class HelloWorldActivityTest : AcceptanceTest<HelloWorldActivity>(HelloWorldActivity::class.java) {

    @Test
    fun shouldSayHelloWorld() {
        checkThat.viewContainsText(R.id.hello, R.string.hello)
    }
}