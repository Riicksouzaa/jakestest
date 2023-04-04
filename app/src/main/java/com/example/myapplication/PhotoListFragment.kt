package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentPhotoListBinding
import com.example.myapplication.fragments.GeneralBaseFragment

class PhotoListFragment : GeneralBaseFragment() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: FragmentPhotoListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhotoListBinding.inflate(inflater, container, false)
        binding.photoRecyclerView.layoutManager = LinearLayoutManager(context)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.photos.observe(viewLifecycleOwner) { photos ->
            binding.photoRecyclerView.adapter = PhotoListAdaptor(photos)
        }
    }
}