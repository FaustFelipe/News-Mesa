package br.com.felipefaustini.mesanews.presentation.binding

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import br.com.felipefaustini.mesanews.utils.extensions.showOrGoneInCondition

object ViewBinding {

    @JvmStatic
    @BindingAdapter("android:visibility")
    fun changeVisibility(view: View, condition: LiveData<Boolean>) {
        if (condition.value != null) {
            view.showOrGoneInCondition(condition.value!!)
        }
    }

    @JvmStatic
    @BindingAdapter("app:invisibility")
    fun changeInvisiblity(view: View, condition: LiveData<Boolean>) {
        if (condition.value != null) {
            view.showOrGoneInCondition(!condition.value!!)
        }
    }

}