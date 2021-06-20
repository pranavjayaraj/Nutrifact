package com.zenmobi.nutrifact.base

import android.os.Build
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.zenmobi.data.checkAndDispose
import com.zenmobi.nutrifact.common.ViewModelFactory
import com.zenmobi.nutrifact.utils.GlideDelegate
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseActivity<VM: ViewModel,Binding: ViewBinding>: DaggerAppCompatActivity(){

    protected lateinit var viewModel: VM

    lateinit var b : Binding

    lateinit var compositeDisposable: CompositeDisposable

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    val glideUtil : GlideDelegate by lazy {
        GlideDelegate(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val statusBarColor = getStatusBarColor()
        if (statusBarColor != -1 && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = statusBarColor
        }
        b = getBindings()
        compositeDisposable = CompositeDisposable()
        viewModel = getViewModelInstance()
        val view = b.root
        setContentView(view)
        onViewCreated()
    }

    open fun getStatusBarColor(): Int{
        return -1
    }


    open fun handleErrorMsg(msg: String): Boolean{
        return false
    }

    abstract fun getViewModelInstance(): VM


    abstract fun getBindings():Binding

    @Deprecated("layout setup is similar to fragment",ReplaceWith("getLayoutResId(): Int? , onViewCreated()"))
    open fun setUpView(resId: Int){
        setContentView(resId)
    }

    open fun initializeAdapter()
    {

    }

    open fun initializeObservers()
    {

    }

    open fun initializeClickListeners()
    {

    }

    open fun onViewCreated()
    {
        initializeAdapter()
        initializeObservers()
        initializeClickListeners()
    }

    @Deprecated("use extension function",ReplaceWith("Context.toast(msg:String)"))
    fun showToast(string: String) {
        Toast.makeText(this,string, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        compositeDisposable.checkAndDispose()
        super.onDestroy()
    }
}