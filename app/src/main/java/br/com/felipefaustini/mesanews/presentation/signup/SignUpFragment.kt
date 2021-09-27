package br.com.felipefaustini.mesanews.presentation.signup

import androidx.appcompat.widget.Toolbar
import br.com.felipefaustini.mesanews.R
import br.com.felipefaustini.mesanews.presentation.BaseFragment
import br.com.felipefaustini.mesanews.utils.extensions.showOrGoneInCondition
import kotlinx.android.synthetic.main.fragment_signup.*
import kotlinx.android.synthetic.main.ly_loading_default.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpFragment: BaseFragment(R.layout.fragment_signup) {

    private val signUpViewModel: SignUpViewModel by viewModel()

    override fun setupViews() {
        
    }

    override fun getToolbar(): Toolbar? {
        return toolbar
    }

    override fun setupActions() {
        
    }

    override fun setupObservables() {
        signUpViewModel.loadingLiveData.observe(viewLifecycleOwner) {
            container_loading.showOrGoneInCondition(it)
            blockUserInteraction(it)
        }
    }

}