package com.puskal.imagepicker.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.puskal.imagepicker.data.AppContract.DEFAULT_IMAGE_LIST_SIZE
import com.puskal.imagepicker.data.local.MySharedPreference
import com.puskal.imagepicker.data.local.MySharedPreference.Companion.IMAGE_LIST_SIZE
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val sharedPreference: MySharedPreference) :
    ViewModel() {
    private val _listSize = MutableLiveData<Int>()
    val listSize: LiveData<Int> = _listSize

    init {
        _listSize.value =
            sharedPreference.getIntValue(IMAGE_LIST_SIZE, defaultValue = DEFAULT_IMAGE_LIST_SIZE)
    }

    fun onChangeSizeEntry(value: Int) {
        sharedPreference.save(IMAGE_LIST_SIZE, value)
    }
}