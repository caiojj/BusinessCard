package br.com.dio.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.dio.data.BusinessCard
import br.com.dio.databinding.ItemBusinessCardBinding

class BusinessCardAdapter : ListAdapter<BusinessCard, BusinessCardAdapter.ViewHolder>(DiffCallback()){
    var listenerShere: (View) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBusinessCardBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    // Evento de rolagem executado automaticamente pelo sistema
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemBusinessCardBinding

    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BusinessCard) {
            binding.tvNome.text = item.nome
            binding.tvEmail.text = item.email
            binding.tvTelefone.text = item.telefone
            binding.tvNomeEmpresa.text = item.empresa
            binding.mcvContent.setCardBackgroundColor(Color.parseColor("#" + item.fundoPersonalizado))

            binding.mcvContent.setOnClickListener {
                listenerShere(it)
            }
        }
    }
}

class DiffCallback: DiffUtil.ItemCallback<BusinessCard>() {

    override fun areItemsTheSame(oldItem: BusinessCard, newItem: BusinessCard): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: BusinessCard, newItem: BusinessCard): Boolean =
        oldItem.id == newItem.id

}