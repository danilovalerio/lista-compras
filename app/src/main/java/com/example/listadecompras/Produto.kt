package com.example.listadecompras

import android.graphics.Bitmap

/**
 * Classe de dados do produto e que aceita valor nulo na propriedade foto
 */
data class Produto(val nome:String, val quantidade:Int, val valor:Double, val foto: Bitmap? = null)