package iot.android.cawalkot.external.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.AndroidSupportInjection
import iot.android.cawalkot.external.view.AlertBottomSheetFragment
import javax.inject.Inject

abstract class BaseFragment<out B : ViewDataBinding, V : ViewModel> : Fragment() {

    private lateinit var viewDataBinding: B
    val binding: B
        get() = viewDataBinding

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    lateinit var vm: V

    @LayoutRes
    abstract fun getLayoutResourceId(): Int
    abstract fun getViewModelClass(): Class<V>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, getLayoutResourceId(), container, false)
        vm = ViewModelProvider(this, factory).get(getViewModelClass())
        viewDataBinding.lifecycleOwner = this
        viewDataBinding.executePendingBindings()
        return viewDataBinding.root
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    fun alertDialog(
        title: String,
        message: String,
        positiveButtonText: String,
        onPositiveButtonClicked: () -> Unit = {}
    ) {
        if (isAdded) {
            AlertDialog.Builder(requireActivity())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveButtonText) { dialog, _ ->
                    dialog.dismiss()
                    onPositiveButtonClicked()
                }
                .show()
        }
    }

    fun alertDialog(
        title: String,
        message: String,
        positiveButtonText: String,
        negativeButtonText: String,
        onPositiveButtonClicked: () -> Unit = {},
        onNegativeButtonClicked: () -> Unit = {}
    ) {
        if (isAdded) {
            AlertDialog.Builder(requireActivity())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveButtonText) { dialog, _ ->
                    dialog.dismiss()
                    onPositiveButtonClicked()
                }
                .setNegativeButton(negativeButtonText) { dialog, _ ->
                    dialog.dismiss()
                    onNegativeButtonClicked()
                }
                .show()
        }
    }

    fun noConnectionAlertBottomSheet(retryAction: () -> Unit = {}) {
        val message = "Tidak ada koneksi internet. Mohon periksa kembali koneksi internet Anda"
        val alert = AlertBottomSheetFragment.newInstance(message)
        alert.setRetryAction(retryAction)
        alert.show(childFragmentManager, tag)
    }

    fun serverErrorAlertBottomSheet(retryAction: () -> Unit = {}) {
        val message = "Terjadi kendala pada server. Mohon coba beberapa saat lagi"
        val alert = AlertBottomSheetFragment.newInstance(message)
        alert.setRetryAction(retryAction)
        alert.show(childFragmentManager, tag)
    }

    fun clientErrorAlertBottomSheet(retryAction: () -> Unit = {}) {
        val message = "Terjadi kesalahan pada aplikasi. Mohon coba beberapa saat lagi"
        val alert = AlertBottomSheetFragment.newInstance(message)
        alert.setRetryAction(retryAction)
        alert.show(childFragmentManager, tag)
    }

    fun timeoutAlertBottomSheet(retryAction: () -> Unit = {}) {
        val message = "Koneksi timeout. Mohon coba beberapa saat lagi"
        val alert = AlertBottomSheetFragment.newInstance(message)
        alert.setRetryAction(retryAction)
        alert.show(childFragmentManager, tag)
    }

    fun unknownAlertBottomSheet(retryAction: () -> Unit = {}) {
        val message = "Terjadi kesalahan yang tak terduga. Mohon hubungi customer service kami untuk bantuan lebih lanjut"
        val alert = AlertBottomSheetFragment.newInstance(message)
        alert.setRetryAction(retryAction)
        alert.show(childFragmentManager, tag)
    }
}
