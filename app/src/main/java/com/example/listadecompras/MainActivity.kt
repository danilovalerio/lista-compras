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

        lv_lista_produtos.setOnItemLongClickListener{
            adapterView, view, i, l ->
            //buscando o item clicado
            val item = produtosAdapter.getItem(i)

            //removendo o item clicado da lista
            produtosAdapter.remove(item)

            //retorno indicando que o click foi realizado com sucesso
            true
        }


    }
}
