package br.com.felipefaustini.mesanews.presentation.home

import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import br.com.felipefaustini.mesanews.R
import br.com.felipefaustini.mesanews.presentation.BaseFragment
import br.com.felipefaustini.mesanews.presentation.home.adapter.ListHighlightsAdapter
import br.com.felipefaustini.mesanews.presentation.home.adapter.ListNewsAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment: BaseFragment(R.layout.fragment_home, R.menu.menu_home) {

    private val viewModel: HomeViewModel by viewModel()

    private val highlightsAdapter = ListHighlightsAdapter()
    private val newsAdapter = ListNewsAdapter()

    override fun getToolbar(): Toolbar? {
        return toolbar
    }

    override fun setupViews() {
        setupToolbar()

        recycler_highlights.adapter = highlightsAdapter

        recycler_news.adapter = newsAdapter
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
        viewModel.listHighlightsLiveData.observe(viewLifecycleOwner) {
            highlightsAdapter.setData(it)
        }

        viewModel.listNewsLiveData.observe(viewLifecycleOwner) {
            newsAdapter.setData(it)
        }

        viewModel.listHighlights()

        viewModel.listNews()
    }

}