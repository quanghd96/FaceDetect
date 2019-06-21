package com.quang.facedetect

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.FirebaseApp
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.anh)

        val image = FirebaseVisionImage.fromBitmap(bitmap)
        val detector = FirebaseVision.getInstance().visionFaceDetector
        detector.detectInImage(image)
            .addOnSuccessListener { faces ->
                Log.d("FaceDetect", "Detected")
                for (face in faces) {
                    val rect = face.boundingBox
                    val resultBmp =
                        Bitmap.createBitmap(bitmap, rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top)
                    imv.setImageBitmap(resultBmp)
                }
            }
            .addOnFailureListener { e ->
                e.printStackTrace()
            }
    }
}
