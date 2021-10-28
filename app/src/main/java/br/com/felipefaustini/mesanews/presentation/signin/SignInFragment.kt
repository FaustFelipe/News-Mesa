package br.com.felipefaustini.mesanews.presentation.signin

import android.view.View
import androidx.appcompat.widget.Toolbar
import br.com.felipefaustini.mesanews.R
import br.com.felipefaustini.mesanews.databinding.FragmentSigninBinding
import br.com.felipefaustini.mesanews.presentation.BaseFragment
import kotlinx.android.synthetic.main.fragment_signin.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInFragment: BaseFragment<FragmentSigninBinding>(R.layout.fragment_signin) {

    private val signInViewModel: SignInViewModel by viewModel()
    
    override fun setupViews() {
        binding?.view = this
        binding?.viewModel = signInViewModel
        setupToolbarWithCloseButton()
    }

    override fun getToolbar(): Toolbar? {
        return toolbar
    }

    override fun setupActions() {

    }

    fun onClickSignIn(view: View) {
        signInViewModel.signIn()
    }

    fun onClickRegister(view: View) {
        navigate(R.id.action_signInFragment_to_signUpFragment)
    }

    override fun setupObservables() {
        signInViewModel.loadingLiveData.observe(viewLifecycleOwner) {
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