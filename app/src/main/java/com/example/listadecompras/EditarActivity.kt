package com.example.listadecompras

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_editar.*
import org.jetbrains.anko.db.parseSingle
import org.jetbrains.anko.db.rowParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.db.update
import org.jetbrains.anko.toast

class EditarActivity : AppCompatActivity() {

    val COD_IMAGE = 171
    var imageBitMap: Bitmap? = null
    lateinit var produto: Produto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar)

        tv_id.text = idProdutoEdicao.toString()

        buscarItem(idProdutoEdicao)

        btn_atualizar.setOnClickListener() {
            val nome = et_nome_prod.text.toString()
            val qtd = et_qtd_prod.text.toString()
            val valor = et_valor_prod.text.toString()

            if (nome.isNotEmpty() && qtd.isNotEmpty() && valor.isNotEmpty()) {

                database.use {
                    update(
                        "produtos",
                        "nome" to nome,
                        "quantidade" to qtd,
                        "valor" to valor,
                        "foto" to imageBitMap?.toByteArray()
                    )
                        .whereArgs(
                            "id = {idUser}",
                            "idUser" to idProdutoEdicao
                        ).exec()

                    toast("Dados atualizados com sucesso!")
                }
            } else {
                et_nome_prod.error =
                    if (et_nome_prod.text.isEmpty()) "Preencha o nome do produto" else null
                et_qtd_prod.error =
                    if (et_qtd_prod.text.isEmpty()) "Preencha a quantidade do produto" else null
                et_valor_prod.error =
                    if (et_valor_prod.text.isEmpty()) "Preencha o valor do produto" else null
            }
        }

        iv_ic_camera.setOnClickListener(){
            abrirGaleria()
        }
    }

    fun buscarItem(id: Int) {
        database.use {
            select("produtos")
                .whereArgs("id = {idUser}", "idUser" to id).exec {
                    //Criando o parser que montará o objeto produto
                    val parser = rowParser { id: Int,
                                             nome: String,
                                             quantidade: Int,
                                             valor: Double,
                                             foto: ByteArray? ->
                        //colunas do banco de dados

                        //montagem do obj Produto com as colunas do banco
                        Produto(id, nome, quantidade, valor, foto?.toBitMap())
                    }

                    //criando a lista de produtos com dados do banco
                    produto = parseSingle(parser)
                    Log.d("RESULTADO", produto.toString())
                }
        }

        et_nome_prod.setText(produto.nome)
        et_qtd_prod.setText(produto.quantidade.toString())
        et_valor_prod.setText(produto.valor.toString())

        if (produto.foto != null) {
            iv_ic_camera.setImageBitmap(produto.foto)
        } else {
            iv_ic_camera.setImageResource(R.drawable.ic_produto_sem_foto)
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
