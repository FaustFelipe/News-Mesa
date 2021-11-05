package br.com.felipefaustini.mesanews.presentation.home

import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import br.com.felipefaustini.mesanews.R
import br.com.felipefaustini.mesanews.databinding.FragmentHomeBinding
import br.com.felipefaustini.mesanews.presentation.BaseFragment
import br.com.felipefaustini.mesanews.presentation.home.adapter.ListHighlightsAdapter
import br.com.felipefaustini.mesanews.presentation.home.adapter.ListNewsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment: BaseFragment<FragmentHomeBinding>(R.layout.fragment_home, R.menu.menu_home) {

    private val viewModel: HomeViewModel by viewModel()

    private val highlightsAdapter = ListHighlightsAdapter(::onNewsClicked)
    private val newsAdapter = ListNewsAdapter(::onNewsClicked)

    override fun getToolbar(): Toolbar? {
        return binding?.toolbar
    }

    override fun setupViews() {
        binding?.viewModel = viewModel

        setupToolbar()

        binding?.recyclerHighlights?.adapter = highlightsAdapter

        binding?.recyclerNews?.adapter = newsAdapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_filter -> {

                true
            }
            R.id.action_logout -> {
                viewModel.signOut()
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

        viewModel.signOutSucceedLiveData.observe(viewLifecycleOwner) {
            if (it) navigate(R.id.action_homeFragment_to_onboardingFragment)
        }

        viewModel.listHighlights()

        viewModel.listNews()
    }

    private fun onNewsClicked() {
        navigate(R.id.action_homeFragment_to_detailsFragment)
    }

}