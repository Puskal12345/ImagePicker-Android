package com.puskal.imagepicker.ui.photogallery

import android.net.Uri
import android.util.Log
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import com.puskal.imagepicker.data.AppContract.DEFAULT_IMAGE_LIST_SIZE
import com.puskal.imagepicker.data.local.MySharedPreference
import com.puskal.imagepicker.data.local.MySharedPreference.Companion.IMAGE_LIST_SIZE
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhotoGalleryViewModel @Inject constructor(private val sharedPreference: MySharedPreference) :
    ViewModel() {

    fun generateImageList(originalList: Array<String>): List<Uri> {
        val generatedList = arrayListOf<Uri>()
        val requiredSize = sharedPreference.getIntValue(
            key = IMAGE_LIST_SIZE,
            defaultValue = DEFAULT_IMAGE_LIST_SIZE
        )
        var triangularItemValue = 1
        var triangularPosition= 1
        for (i in 1 ..  requiredSize){
           if(triangularItemValue==i){
               generatedList.add(originalList[0].toUri())
               triangularPosition+=1
               triangularItemValue+=triangularPosition
           }
            else{
               generatedList.add(originalList[1].toUri())
           }
        }
        return generatedList
    }
}