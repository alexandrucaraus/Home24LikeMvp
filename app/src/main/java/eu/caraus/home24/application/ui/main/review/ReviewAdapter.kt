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

class ReviewAdapter( private val map : HashMap<ArticlesItem?,Boolean> ) : RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {

    companion object {
        const val LIST = 0
        const val GRID = 1
    }

    private val list = map.keys.toMutableList()

    private var isDisplayAsList = true

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int): ViewHolder
            = when( viewType ) {
                LIST -> ViewHolder(LayoutInflater.from(parent.context)
                        .inflate(R.layout.article_list_item,
                                parent, false))
                else -> ViewHolder(LayoutInflater.from(parent.context)
                        .inflate(R.layout.article_grid_item,
                                parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int = if( isDisplayAsList ) LIST else GRID

    fun changeViewType( asList : Boolean ){

        isDisplayAsList = asList
        notifyDataSetChanged()

    }

    override fun onBindViewHolder( holder : ViewHolder, position : Int ) {

        val subject = list[ position ]

        if( isDisplayAsList )  bindList( holder, subject ) else bindGrid( holder, subject )

    }

    private fun bindList( holder: ViewHolder, item : ArticlesItem? ){

        item?.let { article ->

            val isLiked = map[ article ]

            article.title?.let {
                holder.articleTitle?.text = it
            }

            article.price?.let {
                holder.articlePrice?.text = "${it.amount} ${it.currency}"
            }

            if( isLiked == true )
                holder.articleLiked?.setImageResource( R.drawable.star )
            else
                holder.articleLiked?.visibility = View.GONE

            Picasso.with( holder.itemView.context )
                    .load( Uri.parse( article.media?.get(0)?.uri ))
                    .error( R.drawable.image_broken)
                    .fit()
                    .centerInside()
                    .into( holder.articleImage )
        }

    }


    private fun bindGrid( holder: ViewHolder, item : ArticlesItem? ){

        item?.let { article ->

            val isLiked = map[ article ]

            if( isLiked == true )
                holder.articleLiked?.setImageResource( R.drawable.star )
            else
                holder.articleLiked?.visibility = View.GONE

            Picasso.with( holder.itemView.context )
                    .load( Uri.parse( article.media?.get(0)?.uri ))
                    .error( R.drawable.image_broken)
                    .fit()
                    .centerInside()
                    .into( holder.articleImage )
        }

    }

    class ViewHolder( view : View ) : RecyclerView.ViewHolder( view ) {
        var articleImage : ImageView? = view.ivArticleImage
        var articleTitle : TextView?  = view.tvArticleTitle
        var articlePrice : TextView?  = view.tvArticlePriceValue
        var articleLiked : ImageView? = view.ivArticleStar
    }

}