package iot.android.cawalkot.external.abstraction

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.android.AndroidInjection
import iot.android.cawalkot.external.view.AlertBottomSheetFragment
import javax.inject.Inject

abstract class BaseActivity<B : ViewDataBinding, V : ViewModel> : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewDataBinding: B
    private lateinit var viewModel: V

    val binding: B
        get() = viewDataBinding
    val vm: V
        get() = viewModel

    @LayoutRes
    abstract fun getLayoutResourceId(): Int
    abstract fun getViewModelClass(): Class<V>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutResourceId())
        viewDataBinding.lifecycleOwner = this
        viewModel = ViewModelProvider(this, viewModelFactory).get(getViewModelClass())
    }

    fun alertDialog(
        title: String,
        message: String,
        positiveButtonText: String,
        onPositiveButtonClicked: () -> Unit = {}
    ) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(positiveButtonText) { dialog, _ ->
                dialog.dismiss()
                onPositiveButtonClicked()
            }
            .show()
    }

    fun alertDialog(
        title: String,
        message: String,
        positiveButtonText: String,
        negativeButtonText: String,
        onPositiveButtonClicked: () -> Unit = {},
        onNegativeButtonClicked: () -> Unit = {}
    ) {
        AlertDialog.Builder(this)
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

    fun noConnectionAlertBottomSheet(retryAction: () -> Unit = {}) {
        val message = "Tidak ada koneksi internet. Mohon periksa kembali koneksi internet Anda"
        val alert = AlertBottomSheetFragment.newInstance(message)
        alert.setRetryAction(retryAction)
        alert.show(supportFragmentManager, alert.tag)
    }

    fun serverErrorAlertBottomSheet(retryAction: () -> Unit = {}) {
        val message = "Terjadi kendala pada server. Mohon coba beberapa saat lagi"
        val alert = AlertBottomSheetFragment.newInstance(message)
        alert.setRetryAction(retryAction)
        alert.show(supportFragmentManager, alert.tag)
    }

    fun clientErrorAlertBottomSheet(retryAction: () -> Unit = {}) {
        val message = "Terjadi kesalahan pada aplikasi. Mohon coba beberapa saat lagi"
        val alert = AlertBottomSheetFragment.newInstance(message)
        alert.setRetryAction(retryAction)
        alert.show(supportFragmentManager, alert.tag)
    }

    fun timeoutAlertBottomSheet(retryAction: () -> Unit = {}) {
        val message = "Koneksi timeout. Mohon coba beberapa saat lagi"
        val alert = AlertBottomSheetFragment.newInstance(message)
        alert.setRetryAction(retryAction)
        alert.show(supportFragmentManager, alert.tag)
    }

    fun unknownAlertBottomSheet(retryAction: () -> Unit = {}) {
        val message = "Terjadi kesalahan yang tak terduga. Mohon hubungi customer service kami untuk bantuan lebih lanjut"
        val alert = AlertBottomSheetFragment.newInstance(message)
        alert.setRetryAction(retryAction)
        alert.show(supportFragmentManager, alert.tag)
    }
}
