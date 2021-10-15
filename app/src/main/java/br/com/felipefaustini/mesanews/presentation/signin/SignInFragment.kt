package br.com.felipefaustini.mesanews.presentation.signin

import androidx.appcompat.widget.Toolbar
import br.com.felipefaustini.mesanews.R
import br.com.felipefaustini.mesanews.presentation.BaseFragment
import br.com.felipefaustini.mesanews.utils.extensions.showOrGoneInCondition
import br.com.felipefaustini.mesanews.utils.extensions.textChanged
import kotlinx.android.synthetic.main.fragment_signin.*
import kotlinx.android.synthetic.main.ly_loading_default.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInFragment: BaseFragment(R.layout.fragment_signin) {

    private val signInViewModel: SignInViewModel by viewModel()
    
    override fun setupViews() {

    }

    override fun getToolbar(): Toolbar? {
        return toolbar
    }

    override fun setupActions() {
        input_email.textChanged {
            signInViewModel.email = it
        }

        input_password.textChanged {
            signInViewModel.password = it
        }

        btn_sign_in.setOnClickListener {
            signInViewModel.signIn()
        }

        btn_create_account.setOnClickListener {
            navigate(R.id.action_signInFragment_to_signUpFragment)
        }
    }

    override fun setupObservables() {
        signInViewModel.loadingLiveData.observe(viewLifecycleOwner) {
            container_loading.showOrGoneInCondition(it)
            blockUserInteraction(it)
        }

        signInViewModel.errorMessageLiveData.observe(viewLifecycleOwner) {
            println("Error: $it")
        }

        signInViewModel.signInGoHomeLiveData.observe(viewLifecycleOwner) {
            navigate(R.id.action_signInFragment_to_homeFragment)
        }
    }
    
}