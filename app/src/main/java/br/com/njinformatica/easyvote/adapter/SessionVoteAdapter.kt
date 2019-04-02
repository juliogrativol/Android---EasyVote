package br.com.njinformatica.easyvote.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.njinformatica.easyvote.R
import br.com.njinformatica.easyvote.model.Candidate
import br.com.njinformatica.easyvote.model.Session

class SessionVoteAdapter(val onItemClick: ((candidate: Candidate)->Unit)? = null) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var items = listOf<Candidate>()

    override fun getItemCount() = items.size

    fun setData(list: List<Candidate>){
        items = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val card = LayoutInflater.from(parent.context)
                .inflate(R.layout.vote_card, parent,false)
        return SessionCandidateViewHolder(card)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val candidate = items[position]

        if (holder is SessionCandidateViewHolder){
            holder.candidateTextView.text = candidate.nome
        }
    }

    inner class SessionCandidateViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val candidateTextView = itemView.findViewById<TextView>(R.id.text_candidate)

        init {
            itemView.setOnClickListener {
                val candidate = items[adapterPosition]
                onItemClick?.invoke(candidate)
            }
        }
    }
}