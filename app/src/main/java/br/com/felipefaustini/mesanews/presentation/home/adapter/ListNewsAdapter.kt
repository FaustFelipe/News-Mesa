package br.com.felipefaustini.mesanews.presentation.home.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.felipefaustini.domain.models.NewsItem
import br.com.felipefaustini.mesanews.R
import br.com.felipefaustini.mesanews.utils.extensions.inflate
import org.jetbrains.annotations.NotNull

class ListNewsAdapter: ListAdapter<NewsItem, RecyclerView.ViewHolder>(
    getNewsDiffUtilCallback()
) {

    fun setData(data: List<NewsItem>) {
        this.submitList(data.toMutableList())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            0 -> getHeaderViewHolder(parent)
            else -> getBodyViewHolder(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when(holder) {
            is HeaderViewHolder -> {
                holder.txtHeader.text = getStringsForHeader(holder.itemView.context, position)
            }
            is BodyViewHolder -> {

            }
        }
    }

    private fun getStringsForHeader(context: Context, position: Int): String {
        return context.getString(
            if (position == 0) {
                R.string.home_highlights
            } else {
                R.string.home_latest_news
            }
        )
    }

    override fun getItemViewType(position: Int): Int {
        return when(position) {
            0 or 2 -> 0
            else -> 1
        }
    }

    @NotNull
    private fun getHeaderViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return HeaderViewHolder(parent.inflate(R.layout.ly_list_news_header_item))
    }

    @NotNull
    private fun getBodyViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return BodyViewHolder(parent.inflate(R.layout.ly_list_news_body_item))
    }

    private inner class HeaderViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val txtHeader = itemView.findViewById<AppCompatTextView>(R.id.txt_header)
    }

    private inner class BodyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

}

fun getNewsDiffUtilCallback(): DiffUtil.ItemCallback<NewsItem> {
    return object : DiffUtil.ItemCallback<NewsItem>() {
        override fun areItemsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
            return false
        }

        override fun areContentsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
            return false
        }
    }
}