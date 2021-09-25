package br.com.felipefaustini.mesanews.presentation.onboarding

import br.com.felipefaustini.mesanews.R
import br.com.felipefaustini.mesanews.presentation.BaseFragment
import kotlinx.android.synthetic.main.fragment_onboarding.*

class OnboardingFragment: BaseFragment(R.layout.fragment_onboarding) {

    override fun setupViews() {
        
    }

    override fun setupActions() {
        btn_sign_in.setOnClickListener {
            navigate(R.id.action_splashFragment_to_signInFragment)
        }

        btn_create_account.setOnClickListener {
            navigate(
                R.id.action_splashFragment_to_signUpFragment,
                enterFrom = NavigateEnterAnimFrom.BOTTOM
            )
        }
    }

    override fun setupObservables() {
        
    }

}