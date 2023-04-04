package com.example.myapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * A general base fragment that can be extended
 */
abstract class GeneralBaseFragment : Fragment() {

    private var compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val backPressed = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                TODO("Not yet implemented")
            }
        }
    }


    override fun onDestroyView() {
        compositeDisposable.dispose()
        super.onDestroyView()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (compositeDisposable.isDisposed) {
            compositeDisposable = CompositeDisposable()
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }
}