package br.com.felipefaustini.mesanews.presentation.home.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.felipefaustini.domain.models.News
import br.com.felipefaustini.mesanews.R
import br.com.felipefaustini.mesanews.databinding.LyListNewsItemBinding
import br.com.felipefaustini.mesanews.utils.IdleResource
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
                holder.binding?.news = item
                holder.binding?.root?.setOnClickListener { onItemClickListener.invoke() }
                holder.binding?.btnBookmarkNews?.setOnClickListener {  }
                holder.binding?.btnBookmarkNews?.setImageDrawable(
                    getImageForBookmarkedNews(holder.binding.root.context)
                )
                holder.binding?.executePendingBindings()
            }
        }
    }

    private fun getImageForBookmarkedNews(context: Context): Drawable? {
        val bookmarked = false
        return if (bookmarked) {
            ContextCompat.getDrawable(context, R.drawable.ic_bookmark_black_24)
        } else {
            ContextCompat.getDrawable(context, R.drawable.ic_bookmark_border_black_24)
        }
    }

    @NotNull
    private fun getNewsViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return NewsViewHolder(parent.inflate(R.layout.ly_list_news_item))
    }

    private inner class NewsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val binding = DataBindingUtil.bind<LyListNewsItemBinding>(itemView)
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