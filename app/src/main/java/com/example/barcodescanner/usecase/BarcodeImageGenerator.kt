package com.example.barcodescanner.usecase

import android.graphics.Bitmap
import com.example.barcodescanner.model.Barcode
import com.example.barcodescanner.model.ParsedBarcode
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.maxicode.MaxiCodeReader
import com.journeyapps.barcodescanner.BarcodeEncoder

object BarcodeImageGenerator {
    private val encoder = BarcodeEncoder()

    fun generateImage(barcode: Barcode, width: Int, height: Int, margin: Int = 0): Bitmap {
        return generateImage(barcode.text, barcode.format, barcode.errorCorrectionLevel, width, height, margin)
    }

    fun generateImage(barcode: ParsedBarcode, width: Int, height: Int, margin: Int = 0): Bitmap {
        return generateImage(barcode.text, barcode.format, barcode.errorCorrectionLevel, width, height, margin)
    }

    private fun generateImage(text: String, format: BarcodeFormat, errorCorrectionLevel: String?, width: Int, height: Int, margin: Int = 0): Bitmap {
        val params = mapOf(
            EncodeHintType.CHARACTER_SET to "utf-8",
            EncodeHintType.MARGIN to margin
        )

        if (errorCorrectionLevel != null) {
            params.plus(EncodeHintType.ERROR_CORRECTION to errorCorrectionLevel)
        }

        return encoder.encodeBitmap(text, format, width, height, params)
    }
}