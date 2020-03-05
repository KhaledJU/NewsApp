package com.example.newsapp.UI.viewHolders

import android.content.Context
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.icu.util.TimeZone
import android.net.Uri
import android.os.Bundle
import android.text.format.DateUtils
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.models.Article
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.single_article.view.*

class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val title = itemView.title
    val description = itemView.description
    val date = itemView.date
    val img = itemView.img
    val card = itemView.articleCard

    fun bind(article: Article, context: Context){
        title.text = article.title
        description.text = article.description
        date.text = getRelationTime(article.date)

        Picasso.get()
            .load(article.imgUrl)
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_background)
            .fit()
            .centerCrop()
            .into(img);

        card.setOnClickListener {
            val url = article.url
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            ContextCompat.startActivity(context, intent, Bundle())
        }
    }
    private fun getRelationTime(inputDate: String): String? {
        val sdf = SimpleDateFormat("yyyy-mm-dd'T'HH:mm:ss'Z'")
        sdf.timeZone = TimeZone.getTimeZone("GMT")
        val time = sdf.parse(inputDate).time
        val now = System.currentTimeMillis() - DateUtils.DAY_IN_MILLIS * 31
        val resolution: Long = DateUtils.MINUTE_IN_MILLIS
        return DateUtils.getRelativeTimeSpanString(time, now, resolution).toString()
    }
}