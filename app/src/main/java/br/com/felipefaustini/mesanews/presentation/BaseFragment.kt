package br.com.felipefaustini.mesanews.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.WindowManager
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.annotation.MenuRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import br.com.felipefaustini.mesanews.R

abstract class BaseFragment(
    @LayoutRes layoutRes: Int = 0,
    @MenuRes private val menuResId: Int = 0
): Fragment(layoutRes) {

    private var toolbar: Toolbar? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (menuResId != 0) setHasOptionsMenu(true)
        setupViews()
        setupActions()
        setupObservables()
    }

    protected open fun setupToolbar() {
        toolbar = getToolbar()
        toolbar?.apply {
            (activity as? AppCompatActivity)?.setSupportActionBar(this)
        }
    }

    protected open fun setupToolbarWithCloseButton() {
        toolbar = getToolbar()
        toolbar?.apply {
            (activity as? AppCompatActivity)?.setSupportActionBar(this)
            (activity as? AppCompatActivity)?.supportActionBar?.let {
                it.setHomeAsUpIndicator(R.drawable.toolkit_ic_white_close_24)
                it.setDisplayShowHomeEnabled(true)
                it.setDisplayHomeAsUpEnabled(true)
            }
            setNavigationOnClickListener {
                requireActivity().onBackPressed()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        if (menuResId != 0) {
            inflater.inflate(menuResId, menu)
        } else {
            super.onCreateOptionsMenu(menu, inflater)
        }
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
            navigate(resId, args/*, options.build()*/)
        }
    }

    protected abstract fun setupViews()

    protected abstract fun setupActions()

    protected abstract fun setupObservables()

    protected open fun getToolbar(): Toolbar? {
        return null
    }

    protected fun blockUserInteraction(block: Boolean) {
        if (block) {
            activity?.window?.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            )
        } else {
            activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        }
    }

    protected enum class NavigateEnterAnimFrom {
        RIGHT,
        BOTTOM
    }

}