package iot.android.cawalkot.external.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import iot.android.cawalkot.R
import kotlinx.android.synthetic.main.fragment_bottom_sheet_alert.*

class AlertBottomSheetFragment : BottomSheetDialogFragment() {

    private var retryAction: () -> Unit = {}

    companion object {
        const val KEY_ERROR_MESSAGE = "error_message"

        fun newInstance(errorMessage: String): AlertBottomSheetFragment {
            val bundle = Bundle()
            bundle.putString(KEY_ERROR_MESSAGE, errorMessage)

            val fragment = AlertBottomSheetFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.Theme_MaterialComponents_BottomSheetDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bottom_sheet_alert, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val errorMessage = arguments?.getString(KEY_ERROR_MESSAGE, null) ?: ""
        tvError.text = errorMessage

        fabRetry.setOnClickListener {
            retryAction()
            dismiss()
        }
    }

    fun setRetryAction(retryAction: () -> Unit) {
        this.retryAction = retryAction
    }
}
