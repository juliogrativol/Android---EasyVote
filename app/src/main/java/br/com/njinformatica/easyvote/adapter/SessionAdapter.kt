package br.com.njinformatica.easyvote.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.njinformatica.easyvote.R
import br.com.njinformatica.easyvote.model.Session

class SessionAdapter(val onItemClick: ((session: Session)->Unit)? = null) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var items = listOf<Session>()

    override fun getItemCount() = items.size

    fun setData(list: List<Session>){
        items = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val card = LayoutInflater.from(parent.context)
                .inflate(R.layout.session_card, parent,false)
        return NewsViewHolder(card)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val session = items[position]

        if (holder is NewsViewHolder){
            holder.titleTextView.text = session.titulo
            holder.quantidadeVagasTextView.text = session.quantidade_vagas.toString()
            holder.idSessionTextView.text = session.id
        }
    }

    inner class NewsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val titleTextView = itemView.findViewById<TextView>(R.id.text_candidate)
        val quantidadeVagasTextView = itemView.findViewById<TextView>(R.id.text_quantidade_vagas)
        val idSessionTextView = itemView.findViewById<TextView>(R.id.text_session)

        init {
            itemView.setOnClickListener {
                val session = items[adapterPosition]
                onItemClick?.invoke(session)
            }
        }
    }
}