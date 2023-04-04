package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * A general base fragment that can be extended
 */
@AndroidEntryPoint
abstract class GeneralBaseFragment : Fragment() {

    private var compositeDisposable = CompositeDisposable()

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