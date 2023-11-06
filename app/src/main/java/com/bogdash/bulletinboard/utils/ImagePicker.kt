package com.bogdash.bulletinboard.utils

import androidx.appcompat.app.AppCompatActivity
import io.ak1.pix.models.Flash
import io.ak1.pix.models.Mode
import io.ak1.pix.models.Options
import io.ak1.pix.models.Ratio

object ImagePicker {
    const val REQUEST_CODE_GET_IMAGES = 999
    fun getImages(context: AppCompatActivity) {

        val options = Options().apply{
            ratio = Ratio.RATIO_AUTO                                    //Image/video capture ratio
            count = 3                                                   //Number of images to restrict selection count
            spanCount = 3                                               //Number for columns in grid
            path = "Pix/Camera"                                         //Custom Path For media Storage
            isFrontFacing = false                                       //Front Facing camera on start
            mode = Mode.Picture                                           //Option to select only pictures or videos or both
            flash = Flash.Auto                                          //Option to select flash type
        }
    }
}