package iot.android.cawalkot.presentation.login

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import iot.android.cawalkot.R
import iot.android.cawalkot.data.vo.HttpResult
import iot.android.cawalkot.data.vo.LoadResult
import iot.android.cawalkot.databinding.FragmentLoginBinding
import iot.android.cawalkot.external.base.BaseFragment
import iot.android.cawalkot.presentation.util.navigateTo
import iot.android.cawalkot.presentation.util.snackBar

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {

    override fun getLayoutResourceId(): Int = R.layout.fragment_login
    override fun getViewModelClass(): Class<LoginViewModel> = LoginViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkLoginState()
        observeLoginResult()

        binding.fabLogin.setOnClickListener { onButtonLoginPressed() }
    }

    private fun checkLoginState() {
        if (vm.isLoggedIn()) {
            snackBar("Already logged in")
        }
    }

    private fun observeLoginResult() {
        vm.login.observe(viewLifecycleOwner, Observer {
            when (it) {
                is LoadResult.Loading -> {

                }
                is LoadResult.Success -> {
                    snackBar("Login success")
                }
                is LoadResult.Error -> {
                    when (it.cause) {
                        HttpResult.NO_CONNECTION -> noConnectionAlertBottomSheet(retryAction = { onButtonLoginPressed() })
                        HttpResult.TIMEOUT -> timeoutAlertBottomSheet(retryAction = { onButtonLoginPressed() })
                        HttpResult.CLIENT_ERROR -> clientErrorAlertBottomSheet(retryAction = { onButtonLoginPressed() })
                        HttpResult.BAD_RESPONSE -> unknownAlertBottomSheet(retryAction = { onButtonLoginPressed() })
                        HttpResult.SERVER_ERROR -> serverErrorAlertBottomSheet(retryAction = { onButtonLoginPressed() })
                        HttpResult.NOT_DEFINED -> unknownAlertBottomSheet(retryAction = { onButtonLoginPressed() })
                    }

                }
            }
        })
    }

    private fun onButtonLoginPressed() {
//        vm.login("username", "password")
        navigateTo(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
    }
}
