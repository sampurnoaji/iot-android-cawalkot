package iot.android.cawalkot.presentation.home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import iot.android.cawalkot.R
import iot.android.cawalkot.presentation.MainActivity

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).showStatusBar()
        (activity as MainActivity).showBottomNavigation()
    }
}
