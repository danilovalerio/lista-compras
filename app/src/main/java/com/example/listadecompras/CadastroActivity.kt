package com.example.listadecompras

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_cadastro.*

class CadastroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        btn_inserir.setOnClickListener(){
            val produto = et_nome_prod.text.toString()
            val qtd = et_qtd_prod.text.toString()
            val valor = et_valor_prod.text.toString()

            if(produto.isNotEmpty() && qtd.isNotEmpty() && valor.isNotEmpty()){
                //adiciona item na lista

                //limpa a caixa de texto
                et_nome_prod.text.clear()
            } else {
                et_nome_prod.error = if(et_nome_prod.text.isEmpty()) "Preencha o nome do produto" else null
                et_qtd_prod.error = if(et_qtd_prod.text.isEmpty())"Preencha a quantidade do produto" else null
                et_valor_prod.error = if(et_valor_prod.text.isEmpty()) "Preencha o valor do produto" else null
            }
        }
    }
}
