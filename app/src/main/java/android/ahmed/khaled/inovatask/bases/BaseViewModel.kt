package android.ahmed.khaled.inovatask.bases

import android.ahmed.khaled.inovatask.common.SingleLiveEvent
import android.app.Application
import android.content.Intent
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel


open class BaseViewModel() : AndroidViewModel(Application()), Observable {

    private val registry = PropertyChangeRegistry()

    val showLoadingDialog: SingleLiveEvent<Boolean> by lazy {
        SingleLiveEvent<Boolean>()
    }

    val showMessage: SingleLiveEvent<String> by lazy {
        SingleLiveEvent<String>()
    }

    val moveToIntent: SingleLiveEvent<Intent> by lazy {
        SingleLiveEvent<Intent>()
    }


    protected fun showServerMessage(message: String) {
        showMessage.value = message
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        registry.add(callback)

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        registry.add(callback)
    }

    protected fun notifyDataChanged() {
        registry.notifyCallbacks(this, 0, null)
    }
}

