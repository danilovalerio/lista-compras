package com.example.listadecompras

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream

//uma lista mutável do tipo Produto
val produtosGlobal = mutableListOf<Produto>()

//Extensão que recebe um bitmap e converte para bytearray
fun Bitmap.toByteArray(): ByteArray {
    //fará a transformação
    val stream = ByteArrayOutputStream()

    //compressão da imagem no objeto
    this.compress(android.graphics.Bitmap.CompressFormat.PNG, 0, stream)

    //retorno da função
    return stream.toByteArray()
}

//Extensão que converte ByteArray para Bitmap
fun ByteArray.toBitMap() : Bitmap{
    return BitmapFactory.decodeByteArray(this, 0, this.size)
}

//item que será editado
var idProdutoEdicao: Int = 0