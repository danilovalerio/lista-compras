package com.example.listadecompras

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

/**
 * ProdutoAdapter recebe contexto como parâmetro
 * Recebe a extensão de ArrayAdapter com o tipo Produto
 */
class ProdutoAdapter(contexto: Context) : ArrayAdapter<Produto>(contexto, 0){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        //guardar o layout do item
        val v: View
        //se a convertView não for nula inflaremos o layout
        if(convertView != null){
            v = convertView
        } else {
            //inflar o layout
            v = LayoutInflater.from(context).inflate(R.layout.list_view_item_pers, parent, false)
        }

        val item = getItem(position)

        val txt_produto = v.findViewById<TextView>(R.id.tv_item_prod)
        val txt_qtd = v.findViewById<TextView>(R.id.tv_item_qtd)
        val txt_valor = v.findViewById<TextView>(R.id.tv_item_valor)
        val img_produto = v.findViewById<ImageView>(R.id.img_item_foto)

        txt_qtd.text = item?.quantidade.toString()
        txt_produto.text = item?.nome
        txt_valor.text = item?.valor.toString()

        if(item?.foto != null){
            img_produto.setImageBitmap(item?.foto)
        }

        return v
    }
}