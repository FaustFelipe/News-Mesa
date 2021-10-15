package br.com.felipefaustini.mesanews.presentation.splash

import android.os.Handler
import android.os.Looper
import br.com.felipefaustini.mesanews.R
import br.com.felipefaustini.mesanews.presentation.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment: BaseFragment(R.layout.fragment_splash) {

    private val viewModel: SplashViewModel by viewModel()

    override fun setupViews() {

    }

    override fun setupActions() {
        
    }

    override fun setupObservables() {
        viewModel.navigateToHome.observe(viewLifecycleOwner) {
            navigate(R.id.action_splashFragment_to_homeFragment)
        }

        viewModel.navigateToOnboarding.observe(viewLifecycleOwner) {
            navigate(R.id.action_splashFragment_to_onboardingFragment)
        }

        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.openHome()
        }, 2_000L)
    }

}