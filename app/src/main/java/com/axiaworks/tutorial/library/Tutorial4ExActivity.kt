package com.axiaworks.tutorial.library

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.util.AndroidRuntimeException
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.axiaworks.tutorial.R
import com.axiaworks.tutorial.databinding.ActivityTutorial4ExBinding
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter
import kotlin.collections.HashMap


class Tutorial4ExActivity : AppCompatActivity() {
    companion object {
        fun callingIntent(context: Context) = Intent(context, Tutorial4ExActivity::class.java)
    }

    private lateinit var binding: ActivityTutorial4ExBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityTutorial4ExBinding>(
            this,
            R.layout.activity_tutorial4_ex
        ).apply {
            //ボタンのインスタンスをとってくるｰ>qrButton
            // そのボタンにイベントリスナーを設定ｰ>
            // 実行する処理を記述
            qrButton.setOnClickListener{
                //editTextをもとに生成したQRコードをもってくる
                //imageViewのインスタンスをとる
                //そのimageViewにQRコードをセットする
                val bitmap = createQrCode(editText.text.toString(), 240)
                imageQrView.setImageBitmap(bitmap)
            }
        }
    }

    private fun createQrCode (data: String, size: Int):Bitmap? {
        try {
            val qrCodeWriter = QRCodeWriter()
            val hints = HashMap<EncodeHintType,String>()
            hints[EncodeHintType.CHARACTER_SET] = "UTF-8"

            //QRコードをBitmapで作成
            val bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, size, size, hints)
            val width = bitMatrix.width
            val height = bitMatrix.height
            val pixels = IntArray(width * height)

            for (y in 0 until height) {
                val offset = y * width
                for (x in 0 until width) {
                    pixels[offset + x] = if (bitMatrix.get(x, y)) Color.RED else Color.WHITE
                }
            }
            val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            bitmap.setPixels(pixels, 0, width, 0, 0, width, height)

            return bitmap

        } catch (e: WriterException) {
            throw AndroidRuntimeException("Barcode Error.", e)
        }
    }

}