package br.com.felipefaustini.mesanews.presentation.signup

import androidx.appcompat.widget.Toolbar
import br.com.felipefaustini.mesanews.R
import br.com.felipefaustini.mesanews.presentation.BaseFragment
import br.com.felipefaustini.mesanews.utils.extensions.showOrGoneInCondition
import br.com.felipefaustini.mesanews.utils.extensions.textChanged
import kotlinx.android.synthetic.main.fragment_signup.*
import kotlinx.android.synthetic.main.ly_loading_default.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpFragment: BaseFragment(R.layout.fragment_signup) {

    private val signUpViewModel: SignUpViewModel by viewModel()

    override fun setupViews() {
        setupToolbarWithCloseButton()
    }

    override fun getToolbar(): Toolbar? {
        return toolbar
    }

    override fun setupActions() {
        input_name.textChanged {
            signUpViewModel.name = it
        }

        input_email.textChanged {
            signUpViewModel.email = it
        }

        input_password.textChanged {
            signUpViewModel.password = it
        }

        btn_sign_up.setOnClickListener {
            signUpViewModel.signUp()
        }
    }

    override fun setupObservables() {
        signUpViewModel.loadingLiveData.observe(viewLifecycleOwner) {
            container_loading.showOrGoneInCondition(it)
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