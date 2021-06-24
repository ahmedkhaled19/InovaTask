package android.ahmed.khaled.inovatask.bases

import android.ahmed.khaled.inovatask.utils.UiUtils
import android.ahmed.khaled.movieapp.utils.UiUtils
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
import androidx.lifecycle.Observer

/**
 * Created by Ahmed Khaled on 11/12/20.
 */

abstract class BaseActivity : AppCompatActivity() {

    private var baseViewModel: BaseViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_FOLLOW_SYSTEM)
        baseViewModel = getBaseViewModel()
        setupObserver()
    }

    private fun setupObserver() {
        if (baseViewModel == null) return

        baseViewModel!!.showMessage.observe(this, Observer { message ->
            UiUtils.showSnackBar(getActivityBinding(), message)
        })
    }

    protected fun moveToActivity(targetClass: Class<*>) {
        startActivity(Intent(this, targetClass))
    }

    abstract fun getBaseViewModel(): BaseViewModel?

    abstract fun getActivityBinding(): View
}