package br.com.felipefaustini.mesanews.presentation.home.adapter

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.felipefaustini.domain.models.News
import br.com.felipefaustini.mesanews.R
import br.com.felipefaustini.mesanews.utils.extensions.inflate
import br.com.felipefaustini.mesanews.utils.extensions.loadImage
import org.jetbrains.annotations.NotNull

class ListNewsAdapter(
    private val onItemClickListener: () -> Unit
): ListAdapter<News, RecyclerView.ViewHolder>(
    getNewsDiffUtilCallback()
) {

    fun setData(data: List<News>) {
        this.submitList(data.toMutableList())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return getNewsViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when(holder) {
            is NewsViewHolder -> {
                holder.itemView.setOnClickListener { onItemClickListener.invoke() }
                holder.imgNews.loadImage(item.imageUrl)
                holder.txtDateNews.text = item.publishedAt
                holder.txtTitle.text = item.title
                holder.txtDesc.text = item.description
                holder.btnBookmarkNews.setOnClickListener {  }
            }
        }
    }

    @NotNull
    private fun getNewsViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return NewsViewHolder(parent.inflate(R.layout.ly_list_news_item))
    }

    private inner class NewsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imgNews = itemView.findViewById<AppCompatImageView>(R.id.img_news)
        val btnBookmarkNews = itemView.findViewById<AppCompatImageButton>(R.id.btn_bookmark_news)
        val txtDateNews = itemView.findViewById<AppCompatTextView>(R.id.txt_date_news)
        val txtTitle = itemView.findViewById<AppCompatTextView>(R.id.txt_title)
        val txtDesc = itemView.findViewById<AppCompatTextView>(R.id.txt_desc)
    }

}

fun getNewsDiffUtilCallback(): DiffUtil.ItemCallback<News> {
    return object : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem == newItem
        }
    }
}