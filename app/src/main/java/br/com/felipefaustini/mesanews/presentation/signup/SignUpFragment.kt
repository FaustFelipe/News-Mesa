package br.com.felipefaustini.mesanews.presentation.signup

import android.view.View
import androidx.appcompat.widget.Toolbar
import br.com.felipefaustini.mesanews.R
import br.com.felipefaustini.mesanews.databinding.FragmentSignupBinding
import br.com.felipefaustini.mesanews.presentation.BaseFragment
import kotlinx.android.synthetic.main.fragment_signup.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpFragment: BaseFragment<FragmentSignupBinding>(R.layout.fragment_signup) {

    private val signUpViewModel: SignUpViewModel by viewModel()

    override fun setupViews() {
        binding?.view = this
        binding?.viewModel = signUpViewModel
        setupToolbarWithCloseButton()
    }

    override fun getToolbar(): Toolbar? {
        return toolbar
    }

    override fun setupActions() {

    }

    fun onClickRegister(view: View) {
        signUpViewModel.signUp()
    }

    override fun setupObservables() {
        signUpViewModel.loadingLiveData.observe(viewLifecycleOwner) {
            blockUserInteraction(it)
        }

        signUpViewModel.errorMessageLiveData.observe(viewLifecycleOwner) {
            println("Erro")
        }

        signUpViewModel.signUpGoHomeLiveData.observe(viewLifecycleOwner) {
            navigate(R.id.action_signUpFragment_to_homeFragment)
        }
    }

}