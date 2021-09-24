package br.com.felipefaustini.mesanews.presentation

import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import br.com.felipefaustini.mesanews.R

abstract class BaseFragment(
    @LayoutRes layoutRes: Int = 0
): Fragment(layoutRes) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupActions()
        setupObservables()
    }

    protected open fun navigate(
        @IdRes resId: Int,
        args: Bundle? = null,
        enterFrom: NavigateEnterAnimFrom = NavigateEnterAnimFrom.RIGHT
    ) {
        val options = NavOptions.Builder()
            .setEnterAnim(
                if (enterFrom == NavigateEnterAnimFrom.RIGHT) R.anim.toolkit_slide_in_right
                else R.anim.toolkit_slide_in_down
            )
            .setExitAnim(
                if (enterFrom == NavigateEnterAnimFrom.RIGHT) R.anim.toolkit_slide_out_left
                else R.anim.toolkit_slide_out_down
            )
            .setPopEnterAnim(
                if (enterFrom == NavigateEnterAnimFrom.RIGHT) R.anim.toolkit_slide_in_left
                else R.anim.toolkit_slide_pop_out_down
            )
            .setPopExitAnim(
                if (enterFrom == NavigateEnterAnimFrom.RIGHT) R.anim.toolkit_slide_out_right
                else R.anim.toolkit_slide_pop_in_down
            )
        with(findNavController()) {
            currentDestination?.getAction(resId)?.navOptions?.let {
                options.setPopUpTo(it.popUpTo, it.isPopUpToInclusive)
            }
            navigate(resId, args, options.build())
        }
    }

    abstract fun setupViews()

    abstract fun setupActions()

    abstract fun setupObservables()

    protected enum class NavigateEnterAnimFrom {
        RIGHT,
        BOTTOM
    }

}