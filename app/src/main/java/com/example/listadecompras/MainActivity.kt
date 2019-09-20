package com.example.listadecompras

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //criado o adapter para exibir os dados na lista
        val produtosAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1)

        //definindo o adaptador na lista
        lv_lista_produtos.adapter = produtosAdapter

        bt_inserir.setOnClickListener(){
            val produto = et_item.text.toString()

            if(produto.isNotEmpty()){
                //adiciona item na lista
                produtosAdapter.add(produto)

                //limpa a caixa de texto
                et_item.text.clear()
            } else {
                et_item.error = "Preencha um valor"
            }

        }


    }
}
