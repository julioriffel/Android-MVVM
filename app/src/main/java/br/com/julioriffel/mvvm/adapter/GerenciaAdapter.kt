package br.com.julioriffel.mvvm.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.julioriffel.mvvm.databinding.ItemGerenciaBinding
import br.com.julioriffel.mvvm.model.Gerencia

class GerenciaAdapter :
    androidx.recyclerview.widget.ListAdapter<Gerencia, GerenciaAdapter.ViewHolder>(DiffCallback()) {
    var listenerClick: (Gerencia) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemGerenciaBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemGerenciaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Gerencia) {
            binding.tvNome.text = item.nome
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Gerencia>() {
    override fun areItemsTheSame(oldItem: Gerencia, newItem: Gerencia) = oldItem == newItem
    override fun areContentsTheSame(oldItem: Gerencia, newItem: Gerencia) =
        oldItem.id == newItem.id
}
