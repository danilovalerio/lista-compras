package com.example.listadecompras

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import java.io.ByteArrayOutputStream

//uma lista mutável do tipo Produto
val produtosGlobal = mutableListOf<Produto>()

//Extensão que recebe um bitmap e converte para bytearray
fun Bitmap.toByteArray(): ByteArray {
    //fará a transformação
    val stream = ByteArrayOutputStream()

    //compressão da imagem no objeto
    this.compress(Bitmap.CompressFormat.JPEG, 1, stream)

    //retorno da função
    System.out.println(stream.toByteArray().toString())
    Log.d("TOBYTEARRAY",stream.toByteArray().toString())
    return stream.toByteArray()
}

//Extensão que converte ByteArray para Bitmap
fun ByteArray.toBitMap() : Bitmap{
//    Log.d("DECODEPARABITMAP",BitmapFactory.decodeByteArray(this, 0, this.size).toString())
    return BitmapFactory.decodeByteArray(this, 0, this.size)
}

//item que será editado
var idProdutoEdicao: Int = 0