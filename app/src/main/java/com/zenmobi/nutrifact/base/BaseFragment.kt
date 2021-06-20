package com.zenmobi.nutrifact.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.zenmobi.data.checkAndDispose
import com.zenmobi.nutrifact.common.ViewModelFactory
import com.zenmobi.nutrifact.utils.GlideDelegate
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseFragment<VM: BaseViewModel>: DaggerFragment(), View.OnClickListener{

    protected lateinit var viewModel: VM

    @Inject
    lateinit var viewModelFactory: ViewModelFactory


    lateinit var disposables: CompositeDisposable


    lateinit var mContext: Context

    protected val glideUtil : GlideDelegate by lazy {
        GlideDelegate(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val a = getLayoutResId()
        return inflater.inflate(a, container, false)
    }

    fun setDataToArguments(bundle: Bundle){
        arguments = bundle
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        disposables = CompositeDisposable()
        viewModel = getViewModelInstance()
        mContext= context
    }

    fun showToast(string: String) {
        Toast.makeText(mContext,string, Toast.LENGTH_LONG).show()
    }


    abstract fun getLayoutResId(): Int

    abstract fun getViewModelInstance(): VM

    override fun onClick(viewClicked: View?) {

    }

    fun isViewLive(): Boolean{
        return isAdded && view != null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(!isViewLive()){
            onFragmentNotAddedAndViewCreated()
            return
        }
        onFragmentAddedAndViewCreated()
    }

    open fun onFragmentNotAddedAndViewCreated(){

    }

    open fun onFragmentAddedAndViewCreated(){
        initializeAdapters()
        initializeObservers()
        initializeClickListeners()
    }

    open fun refreshData(){

    }

    open fun initializeClickListeners()
    {

    }

    open fun initializeObservers()
    {

    }

    open fun initializeAdapters()
    {

    }

    override fun onDetach() {
        disposables.checkAndDispose()
        super.onDetach()
    }

}