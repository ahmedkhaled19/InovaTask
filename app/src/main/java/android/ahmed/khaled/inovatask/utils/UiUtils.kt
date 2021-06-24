package android.ahmed.khaled.inovatask.utils

import android.ahmed.khaled.inovatask.R
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

/**
 * Created by Ahmed Khaled on 11/12/20.
 */

object UiUtils {

    fun showSnackBar(view: View, msg: String?) {
        val snackbar = Snackbar.make(view, msg!!, Snackbar.LENGTH_LONG)
        val sbView = snackbar.view
        sbView.setBackgroundColor(
            ContextCompat.getColor(view.context, R.color.snackBar_background_color)
        )
        val textView = sbView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        textView.textAlignment = View.TEXT_ALIGNMENT_TEXT_START
        textView.setTextColor(ContextCompat.getColor(view.context, R.color.snackBar_text_color))
        snackbar.duration = 3000
        snackbar.show()
    }

    fun hideKeyBoard(context: Context, view: View) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}