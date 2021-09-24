package br.com.felipefaustini.mesanews.presentation.signin

import androidx.appcompat.app.AppCompatActivity
import br.com.felipefaustini.mesanews.R
import br.com.felipefaustini.mesanews.presentation.BaseFragment
import kotlinx.android.synthetic.main.fragment_signin.*

class SignInFragment: BaseFragment(R.layout.fragment_signin) {
    
    override fun setupViews() {
        toolbar.apply {
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

    override fun setupActions() {
        
    }

    override fun setupObservables() {
        
    }
    
}