package com.example.listadecompras

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bt_inserir.setOnClickListener(){
            //Intent explícita
            val intent = Intent(this, CadastroActivity::class.java)

            //iniciando a activity
            startActivity(intent)
        }

//        //criado o adapter para exibir os dados na lista
//        val produtosAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1)
//
//        //definindo o adaptador na lista
//        lv_lista_produtos.adapter = produtosAdapter
//
//        lv_lista_produtos.setOnItemLongClickListener{
//            adapterView, view, i, l ->
//            //buscando o item clicado
//            val item = produtosAdapter.getItem(i)
//
//            //removendo o item clicado da lista
//            produtosAdapter.remove(item)
//
//            //retorno indicando que o click foi realizado com sucesso
//            true
//        }


    }
}
