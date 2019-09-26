package com.example.listadecompras

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.db.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bt_inserir.setOnClickListener() {
            startActivity<CadastroActivity>()
        }

        //Implementação do adaptador
        val produtosAdapter = ProdutoAdapter(this)

        produtosAdapter.addAll(produtosGlobal)

        //definindo o adaptador na lista
        lv_lista_produtos.adapter = produtosAdapter

        lv_lista_produtos.setOnItemLongClickListener { adapterView, view, i, l ->
            //buscando o item clicado
            val item = produtosAdapter.getItem(i)

            //removendo o item clicado da lista
            produtosAdapter.remove(item)

            //deletando do banco de dados
            if (item != null) {
                deletarProduto(item.id)
            }

            toast("Item deletado com sucesso!")

            //retorno indicando que o click foi realizado com sucesso
            true
        }

        lv_lista_produtos.setOnItemClickListener { adapterView, view, i, l ->
            val item = produtosAdapter.getItem(i)

            toast("Clique efetuado id: "+item!!.id.toString())

            idProdutoEdicao = item.id

            startActivity<EditarActivity>()

            true
        }
    }

    /**
     * onResume é chamado quando a tela vai aparecer independentemente se está voltando ou criando
     */
    override fun onResume() {
        super.onResume()

        val adapter = lv_lista_produtos.adapter as ProdutoAdapter

        database.use {
            //efetuar a consulta no banco de dados
            select("produtos").exec {
                //Criando o parser que montará o objeto produto
                val parser = rowParser { id: Int,
                                         nome: String,
                                         quantidade: Int,
                                         valor: Double,
                                         foto: ByteArray? ->
                    //colunas do banco de dados

                    //montagem do obj Produto com as colunas do banco
                    Log.d("PRODUTO","Id: "+id+" Tam. foto:"+foto?.size.toString())
                    Produto(id, nome, quantidade, valor, foto?.toBitMap())
                }

//                Log.d("PARSER",parseList(parser).toString())

                //criando a lista de produtos com dados do banco
                var listaProdutos = parseList(parser)


                //limpando os dados da lista e carregando as novas informações
                adapter.clear()
                adapter.addAll(listaProdutos)

                //efetuando a multiplicação e soma da quantidade e valor
                val soma = listaProdutos.sumByDouble {
                    it.valor*it.quantidade
                }

                //formatando em formato moeda
                val f = NumberFormat.getCurrencyInstance(Locale("pt","br"))
                tv_total.text = "TOTAL: ${f.format(soma)}"
            }
        }
    }

    fun deletarProduto(idProd:Int){
        database.use {
            delete("produtos","id = {id}", "id" to idProd)
        }

        val adapter = lv_lista_produtos.adapter as ProdutoAdapter

        database.use {
            //efetuar a consulta no banco de dados
            select("produtos").exec {
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
                var listaProdutos = parseList(parser)

                //limpando os dados da lista e carregando as novas informações
                adapter.clear()
                adapter.addAll(listaProdutos)

                //efetuando a multiplicação e soma da quantidade e valor
                val soma = listaProdutos.sumByDouble {
                    it.valor*it.quantidade
                }

                //formatando em formato moeda
                val f = NumberFormat.getCurrencyInstance(Locale("pt","br"))
                tv_total.text = "TOTAL: ${f.format(soma)}"
            }
        }
    }
}