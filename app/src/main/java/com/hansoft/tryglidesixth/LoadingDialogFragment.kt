package com.hansoft.tryglidesixth

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.text.format.DateUtils
import androidx.annotation.NonNull
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import java.lang.ref.WeakReference

class LoadingDialogFragment : DialogFragment() {
    private val handler: LoadingHandler = LoadingHandler(this)
    var isDone = false
    @NonNull
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        handler.sendEmptyMessageDelayed(LoadingHandler.MSG_DISMISS, DELAY)
        return android.app.AlertDialog.Builder(getActivity())
                .setTitle("loading")
                .setMessage("wait")
                .create()
    }

    override fun onDestroyView() {
        handler.removeMessages(LoadingHandler.MSG_DISMISS)
        super.onDestroyView()
    }


    private class LoadingHandler(fragment: DialogFragment) : Handler(Looper.getMainLooper(),) {
        private val ref: WeakReference<DialogFragment> = WeakReference<DialogFragment>(fragment)
        override fun handleMessage(msg: Message) {
            val fragment: DialogFragment? = ref.get()
            if (fragment != null) {
                fragment.dismiss()
                val activity: Activity = fragment.getActivity() as Activity
                if (activity is AsyncActivity2) {
                    (activity as AsyncActivity2).onLoadingFinished()
                }
            }
        }

        companion object {
            const val MSG_DISMISS = 0
        }

    }

    companion object {
        const val TAG = "Loading"
        private const val DELAY: Long = DateUtils.SECOND_IN_MILLIS * 3
    }

}