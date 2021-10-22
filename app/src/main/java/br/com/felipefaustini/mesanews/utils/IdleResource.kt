package br.com.felipefaustini.mesanews.utils

import androidx.test.espresso.idling.CountingIdlingResource

object IdleResource {
    val instanceSignUp = CountingIdlingResource("SIGNUP")
    val instanceSignIn = CountingIdlingResource("SIGNIN")
    val instanceHighlights = CountingIdlingResource("HIGHLIGHTS")
    val instanceNews = CountingIdlingResource("NEWS")
}