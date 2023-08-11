package com.puskal.imagepicker.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.text.isDigitsOnly
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.puskal.imagepicker.R
import com.puskal.imagepicker.databinding.FragmentHomeBinding
import com.puskal.imagepicker.utils.showShortToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        with(binding) {
            etImageSize.setText(homeViewModel.listSize.value.toString())
            etImageSize.doOnTextChanged { text, start, before, count ->
                text?.takeIf { it.isDigitsOnly() }?.toString()?.let {
                    if (it.isEmpty() || it.toInt() < 3) {
                        etImageSize.setText("3")
                        context?.showShortToast(getString(R.string.minimum_allowed_size))
                    } else if (it.toInt() > 1000) {
                        etImageSize.setText("1000")
                        context?.showShortToast(getString(R.string.maximum_allowed_size))
                    } else {
                        homeViewModel.onChangeSizeEntry(it.toInt())
                    }
                }
            }

            pickMedia()
        }

        return binding.root
    }


    private fun FragmentHomeBinding.pickMedia() {
        val pickMedia =
            registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia(2)) { uri ->
                uri.takeIf { it.isNotEmpty() }?.let {
                    if (uri.size == 2) {
                        findNavController().navigate(
                            HomeFragmentDirections.actionNavHomeToNavGallery(
                                uri.map { it.toString() }.toTypedArray()
                            )
                        )
                    } else {
                        context?.showShortToast(getString(R.string.select_two_images))
                    }
                }
            }

        cvPickImage.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
    }


}