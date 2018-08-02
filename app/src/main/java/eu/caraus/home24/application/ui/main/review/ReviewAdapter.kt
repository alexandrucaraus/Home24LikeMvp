package eu.caraus.home24.application.ui.main.review

import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import eu.caraus.home24.R
import eu.caraus.home24.application.data.domain.home24.ArticlesItem
import kotlinx.android.synthetic.main.article_list_item.view.*

class ReviewAdapter( private val list  : List<ArticlesItem?> ,
                     private val liked : List<String>?       ) : RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {



    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int): ViewHolder =
         ViewHolder( LayoutInflater.from( parent.context )
                                   .inflate( R.layout.article_list_item,
                                             parent, false ))


    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder( holder: ViewHolder, position: Int ) {

        val subject = list[ position ]

        subject?.let {

            it.title?.let {
                holder.articleTitle?.text = it
            }

            it.description?.let {
                holder.articleDescr?.text = it.toString()
            }

            Picasso.with( holder.itemView.context )
                    .load( Uri.parse( it.media?.get(0)?.uri ))
                    .fit()
                    .centerCrop()
                    .into( holder.articleImage )
        }

    }

    class ViewHolder( view : View ) : RecyclerView.ViewHolder( view ) {
        var articleImage : ImageView? = view.ivArticleImage
        var articleTitle : TextView?  = view.tvArticleTitle
        var articleDescr : TextView?  = view.tvArticleDescr
        var articleLiked : ImageView? = view.ivArticleStar
    }

}