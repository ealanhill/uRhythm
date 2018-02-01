package me.ealanhill.urhythm.recyclerview

import android.support.annotation.LayoutRes
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import me.ealanhill.urhythm.util.inflate

abstract class AbstractAdapter<T> constructor(
        protected var itemList: List<T>,
        @LayoutRes private val layoutResId: Int)
    : RecyclerView.Adapter<AbstractAdapter.Holder>() {

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun getItemCount(): Int = itemList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = parent inflate layoutResId
        return Holder(view).apply {
            itemView.setOnClickListener {
                val adapterPosition = this.adapterPosition
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    onItemClick(itemView, adapterPosition)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: Holder, position: Int) =
            holder.itemView.bind(itemList[position])

    fun update(items: List<T>) = updateAdapterWithDiffResult(calculateDiff(items))

    fun add(item: T) {
        itemList.toMutableList().add(item)
        notifyItemInserted(itemList.size)
    }

    fun remove(position: Int) {
        itemList.toMutableList().removeAt(position)
        notifyItemRemoved(position)
    }

    final override fun onViewRecycled(holder: Holder) {
        super.onViewRecycled(holder)
        onViewRecycled(holder.itemView)
    }

    private fun updateAdapterWithDiffResult(result: DiffUtil.DiffResult) =
            result.dispatchUpdatesTo(this)

    private fun calculateDiff(newItems: List<T>) =
            DiffUtil.calculateDiff(DiffUtilCallback(itemList, newItems))

    protected open fun View.bind(item :T) {}

    protected open fun onItemClick(itemView: View, position: Int) {}

    protected open fun onViewRecycled(itemView: View) {}
}