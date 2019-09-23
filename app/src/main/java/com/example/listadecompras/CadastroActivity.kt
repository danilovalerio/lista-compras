package com.example.listadecompras

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_cadastro.*

class CadastroActivity : AppCompatActivity() {

    val COD_IMAGE = 171

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        btn_inserir.setOnClickListener(){
            val produto = et_nome_prod.text.toString()
            val qtd = et_qtd_prod.text.toString()
            val valor = et_valor_prod.text.toString()

            if(produto.isNotEmpty() && qtd.isNotEmpty() && valor.isNotEmpty()){
                //adiciona item na lista
                val prod = Produto(produto, qtd.toInt(), valor.toDouble())
                produtosGlobal.add(prod)

                //limpa as edit's text's de texto
                et_nome_prod.text.clear()
                et_qtd_prod.text.clear()
                et_valor_prod.text.clear()
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

        if(resultCode == COD_IMAGE && resultCode == Activity.RESULT_OK){
            if(data != null){
                //nesse ponto podemos acessar a imagem escolhida através da variável "data"

            }
        }
    }

    fun abrirGaleria(){
        //definindo a ação de conteúdo
        intent.type = "image/*"

        //inicializando a activity com resultado
        startActivityForResult(Intent.createChooser(intent,"Selecione uma imagem"), COD_IMAGE)
    }
}
