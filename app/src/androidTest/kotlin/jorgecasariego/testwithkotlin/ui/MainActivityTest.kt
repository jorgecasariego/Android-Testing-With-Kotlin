package jorgecasariego.testwithkotlin.ui

import jorgecasariego.testwithkotlin.R
import jorgecasariego.testwithkotlin.ui.framework.AcceptanceTest
import org.junit.Test

/**
 * Created by jorgecasariego on 2/8/17.
 */
class MainActivityTest : AcceptanceTest<MainActivity>(MainActivity::class.java) {

    @Test
    fun shouldOpenHelloWorldScreen() {
        events.clickOnView(R.id.btn_hello_world)
        checkThat.nextOpenActivityIs(HelloWorldActivity::class.java)
    }

    @Test
    fun shouldDisplayAction() {
        events.clickOnView(R.id.fab)
        checkThat.viewIsVisibleAndContainsText(R.string.action)
    }
}