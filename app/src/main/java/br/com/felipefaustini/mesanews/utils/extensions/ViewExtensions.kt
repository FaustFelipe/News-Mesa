package br.com.felipefaustini.mesanews.utils.extensions

import android.view.View

fun View.makeVisible() {
    visibility = View.VISIBLE
}

fun View.makeGone() {
    visibility = View.GONE
}

fun View.makeInvisible() {
    visibility = View.INVISIBLE
}

fun View.showOrGoneInCondition(condition: Boolean) {
    if (condition) makeVisible()
    else makeGone()
}
