package com.example.camera

import android.Manifest
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {

    companion object {
        const val CAMERA_REQUEST_CODE = 1
        const val CAMERA_PERMISSION_REQUEST_CODE = 2
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "カメラのパーミッションが許可されています。")

            //カメラ起動
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
                addCategory(Intent.CATEGORY_DEFAULT)
            }

            startActivityForResult(intent, CAMERA_REQUEST_CODE)




        } else {
            Log.d(TAG, "カメラのパーミッションが許可されていません。")
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA), CAMERA_PERMISSION_REQUEST_CODE
            )
        }
    }
}