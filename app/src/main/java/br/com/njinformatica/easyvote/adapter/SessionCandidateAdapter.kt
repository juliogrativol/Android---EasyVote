package br.com.njinformatica.easyvote.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.njinformatica.easyvote.R
import br.com.njinformatica.easyvote.model.Candidate

class SessionCandidateAdapter(val onItemClick: ((candidate: Candidate)->Unit)? = null) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var items = listOf<Candidate>()

    override fun getItemCount() = items.size

    fun setData(list: List<Candidate>){
        items = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val card = LayoutInflater.from(parent.context)
                .inflate(R.layout.candidate_card, parent,false)
        return SessionCandidateViewHolder(card)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val candidate = items[position]

        if (holder is SessionCandidateViewHolder){
            holder.candidateTextView.text = candidate.nome
            holder.votesTextView.text = candidate.votos.toString()
        }
    }

    inner class SessionCandidateViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val candidateTextView = itemView.findViewById<TextView>(R.id.text_candidate)
        val votesTextView = itemView.findViewById<TextView>(R.id.text_votes)

        init {
            itemView.setOnClickListener {
                val candidate = items[adapterPosition]
                onItemClick?.invoke(candidate)
            }
        }
    }
}