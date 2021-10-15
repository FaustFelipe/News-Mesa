package br.com.felipefaustini.mesanews.presentation.home

import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import br.com.felipefaustini.mesanews.R
import br.com.felipefaustini.mesanews.presentation.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment: BaseFragment(R.layout.fragment_home, R.menu.menu_home) {

    private val viewModel: HomeViewModel by viewModel()

    override fun getToolbar(): Toolbar? {
        return toolbar
    }

    override fun setupViews() {
        setupToolbar()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_filter -> {

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun setupActions() {
        
    }

    override fun setupObservables() {
        viewModel.listNews()
    }

}