package br.com.felipefaustini.mesanews.presentation

import android.os.Bundle
import android.view.*
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.annotation.MenuRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import br.com.felipefaustini.mesanews.R

abstract class BaseFragment<A : ViewDataBinding>(
    @LayoutRes private val layoutRes: Int = 0,
    @MenuRes private val menuResId: Int = 0
) : Fragment(layoutRes) {

    private var toolbar: Toolbar? = null

    protected var binding: A? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        binding?.lifecycleOwner = viewLifecycleOwner
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (menuResId != 0) setHasOptionsMenu(true)
        setupViews()
        setupActions()
        setupObservables()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        if (menuResId != 0) {
            inflater.inflate(menuResId, menu)
        } else {
            super.onCreateOptionsMenu(menu, inflater)
        }
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

    protected open fun navigate(@IdRes resId: Int, args: Bundle? = null) {
        val options = NavOptions.Builder()
        with(findNavController()) {
            currentDestination?.getAction(resId)?.navOptions?.let {
                options.setPopUpTo(it.popUpTo, it.isPopUpToInclusive)
            }
            navigate(resId, args)
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

}