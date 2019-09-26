package com.example.listadecompras

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_cadastro.*
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.toast


class CadastroActivity : AppCompatActivity() {

    val COD_IMAGE = 171
    var imageBitMap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        btn_inserir.setOnClickListener(){
            val produto = et_nome_prod.text.toString()
            val qtd = et_qtd_prod.text.toString()
            val valor = et_valor_prod.text.toString()

            if(produto.isNotEmpty() && qtd.isNotEmpty() && valor.isNotEmpty()){
                database.use {
                    val idProduto = insert(
                        "Produtos","nome" to produto,
                        "quantidade" to qtd,
                        "valor" to valor.toDouble(),
                        "foto" to imageBitMap?.toByteArray())//chamada da função imgBitmap para ByteArray

                    if(idProduto != -1L){
                        toast("Inserido com sucesso")
                        //limpa as edit's text's de texto
                        et_nome_prod.text.clear()
                        et_qtd_prod.text.clear()
                        et_valor_prod.text.clear()
                        iv_ic_camera.setImageResource(R.drawable.ic_produto_sem_foto)
                    } else {
                        toast("Erro ao inserir no banco de dados")
                    }

                }
            } else {
                et_nome_prod.error = if(et_nome_prod.text.isEmpty()) "Preencha o nome do produto" else null
                et_qtd_prod.error = if(et_qtd_prod.text.isEmpty())"Preencha a quantidade do produto" else null
                et_valor_prod.error = if(et_valor_prod.text.isEmpty()) "Preencha o valor do produto" else null
            }
        }

        iv_ic_camera.setOnClickListener(){
            abrirGaleria()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == COD_IMAGE && resultCode == Activity.RESULT_OK){
            if(data != null){
                //lendo a uri da imagem selecionada
                val inputStream = contentResolver.openInputStream(data.data!!)

                //transf resultado em bitmap
                imageBitMap = BitmapFactory.decodeStream(inputStream)

                //exibir a imagem no app
                iv_ic_camera.setImageBitmap(imageBitMap)
            }
        }
    }

    fun abrirGaleria(){
        //definindo a ação de conteúdo
        val intent = Intent(Intent.ACTION_GET_CONTENT)

        //definindo a ação de conteúdo
        intent.type = "image/*"

        //inicializando a activity com resultado
        startActivityForResult(Intent.createChooser(intent,"Selecione uma imagem"), COD_IMAGE)
    }
}
