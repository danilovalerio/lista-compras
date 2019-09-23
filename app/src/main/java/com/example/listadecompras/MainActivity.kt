package com.example.listadecompras

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.NumberFormat
import java.util.*

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

        //Implementação do adaptador
        val produtosAdapter = ProdutoAdapter(this)

        produtosAdapter.addAll(produtosGlobal)

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

    /**
     * onResume é chamado quando a tela vai aparecer independentemente se está voltando ou criando
     */
    override fun onResume(){
        super.onResume()

        val adapter = lv_lista_produtos.adapter as ProdutoAdapter

        adapter.clear()
        adapter.addAll(produtosGlobal)

        //substitui o for com a var abaixo
        val soma = produtosGlobal.sumByDouble { it.valor * it.quantidade }

        val f = NumberFormat.getCurrencyInstance(Locale("pt","br"))

        tv_total.text = "TOTAL: ${f.format(soma)}"
    }
}
