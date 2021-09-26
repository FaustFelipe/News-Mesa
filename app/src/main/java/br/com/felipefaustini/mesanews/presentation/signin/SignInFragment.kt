package br.com.felipefaustini.mesanews.presentation.signin

import androidx.appcompat.app.AppCompatActivity
import br.com.felipefaustini.mesanews.R
import br.com.felipefaustini.mesanews.presentation.BaseFragment
import br.com.felipefaustini.mesanews.utils.textChanged
import kotlinx.android.synthetic.main.fragment_signin.*
import org.koin.android.ext.android.inject

class SignInFragment: BaseFragment(R.layout.fragment_signin) {

    private val signInViewModule: SignInViewModule by inject()
    
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
        input_email.textChanged {
            signInViewModule.email = it
        }

        input_password.textChanged {
            signInViewModule.password = it
        }

        btn_sign_in.setOnClickListener {
            signInViewModule.signIn()
        }
    }

    override fun setupObservables() {
        signInViewModule.loadingLiveData.observe(viewLifecycleOwner) {
            println("Loading: $it")
        }

        signInViewModule.errorMessageLiveData.observe(viewLifecycleOwner) {
            println("Error: $it")
        }
    }
    
}