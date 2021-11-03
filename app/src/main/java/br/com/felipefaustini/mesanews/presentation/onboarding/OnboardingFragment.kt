package br.com.felipefaustini.mesanews.presentation.onboarding

import android.view.View
import br.com.felipefaustini.mesanews.R
import br.com.felipefaustini.mesanews.databinding.FragmentOnboardingBinding
import br.com.felipefaustini.mesanews.presentation.BaseFragment

class OnboardingFragment: BaseFragment<FragmentOnboardingBinding>(R.layout.fragment_onboarding) {

    override fun setupViews() {
        binding?.view = this
    }

    override fun setupActions() {

    }

    override fun setupObservables() {

    }

    fun onClickSignIn(view: View) {
        navigate(R.id.action_onboardingFragment_to_signInFragment)
    }

    fun onClickRegister(view: View) {
        navigate(R.id.action_onboardingFragment_to_signUpFragment)
    }

}