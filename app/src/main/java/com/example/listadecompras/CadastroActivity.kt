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

            if(produto.isNotEmpty()){
                //adiciona item na lista

                //limpa a caixa de texto
                et_nome_prod.text.clear()
            } else {
                et_nome_prod.error = "Preencha um valor"
            }
        }
    }
}
