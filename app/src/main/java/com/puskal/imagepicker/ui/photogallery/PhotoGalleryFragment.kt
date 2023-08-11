package com.puskal.imagepicker.ui.photogallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.puskal.imagepicker.databinding.FragmentPhotoGalleryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoGalleryFragment : Fragment() {
    private lateinit var binding: FragmentPhotoGalleryBinding
    private lateinit var viewModel: PhotoGalleryViewModel
    private val args: PhotoGalleryFragmentArgs by navArgs()
    private val photoAdapter: PhotoAdapter by lazy {
        PhotoAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            ViewModelProvider(this).get(PhotoGalleryViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhotoGalleryBinding.inflate(inflater, container, false)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        args.uris.apply {
            photoAdapter.setImageUris(viewModel.generateImageList(this))
        }
        binding.rvPhotos.adapter = photoAdapter
        return binding.root
    }

}