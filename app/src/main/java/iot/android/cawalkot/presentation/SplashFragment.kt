package iot.android.cawalkot.presentation

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import iot.android.cawalkot.R
import iot.android.cawalkot.presentation.util.navigateTo

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Handler().postDelayed({
            navigateTo(SplashFragmentDirections.actionSplashFragment2ToLoginFragment())
        }, 1500)

        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).hideBottomNavigation()
        (activity as MainActivity).hideStatusBar()
    }

    override fun onDetach() {
        (activity as MainActivity).showStatusBar()
        super.onDetach()
    }
}
