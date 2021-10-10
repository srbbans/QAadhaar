package bans.qaadhar.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bans.qaadhar.databinding.CardItemBinding
import bans.qaadhar.models.AadharCard

class CardsAdapter(
    context: Context, private var dataSet: ArrayList<AadharCard>,
    private val onItemClicked: (AadharCard) -> Unit
) : RecyclerView.Adapter<CardsAdapter.DataObjectHolder>() {

    private val lF: LayoutInflater = LayoutInflater.from(context)

    inner class DataObjectHolder(
        private val binding: CardItemBinding,
        onItemClicked: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onItemClicked(bindingAdapterPosition)
            }
        }

        fun bind(card: AadharCard) {
            binding.aadhar = card
            binding.executePendingBindings()
        }
    }

    fun addItem(item: AadharCard) {
        dataSet.add(item)
        notifyItemInserted(dataSet.size - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataObjectHolder {
        val binding = CardItemBinding.inflate(lF, parent, false)
        return DataObjectHolder(binding) {
            onItemClicked(dataSet[it])
        }
    }

    override fun onBindViewHolder(holder: DataObjectHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

}