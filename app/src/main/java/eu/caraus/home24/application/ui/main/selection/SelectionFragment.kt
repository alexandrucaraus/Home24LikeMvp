package eu.caraus.home24.application.ui.main.selection


import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.*
import com.squareup.picasso.Callback
import eu.caraus.home24.R
import kotlinx.android.synthetic.main.fragment_selection.view.*
import javax.inject.Inject
import com.squareup.picasso.Picasso
import eu.caraus.home24.application.data.domain.home24.ArticlesItem
import eu.caraus.home24.application.ui.base.BaseActivity
import eu.caraus.home24.application.ui.base.BaseFragment

import kotlinx.android.synthetic.main.fragment_selection.*


class SelectionFragment : BaseFragment(), SelectionContract.View {

    companion object {

        val TAG = SelectionFragment::class.java.simpleName!!

        fun newInstance(): SelectionFragment {

            val fragment = SelectionFragment()

            val bundle = Bundle()

            fragment.arguments = bundle

            return fragment
        }

    }

    @Inject
    lateinit var presenter : SelectionContract.Presenter

    lateinit var animations: SelectionAnimations

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        animations = SelectionAnimations( context!! )
        lifecycle.addObserver( presenter )
    }

    override fun onDestroy() {
        lifecycle.removeObserver( presenter)
        super.onDestroy()
    }

    override fun onResume() {
        presenter.onViewAttached(this)
        super.onResume()
    }

    override fun onPause() {
        presenter.onViewDetached(true)
        super.onPause()
    }

    override fun onPrepareOptionsMenu(menu: Menu?) {
        ( activity as BaseActivity).apply {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            supportActionBar?.setDisplayShowHomeEnabled(true)
            supportActionBar?.setIcon(R.drawable.home_circle)
            invalidateOptionsMenu()
        }
        super.onPrepareOptionsMenu(menu)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate( R.layout.fragment_selection, null)
        init(view)
        return view
    }

    private fun init( view: View ) {

        view.ivLike?.setOnClickListener {
            if( !presenter.isInReview() ) {
                    animations.slideRight(view.ivArticle) {
                        presenter.likeArticle()
                    }
            }
        }

        view.ivDislike?.setOnClickListener {
            if( !presenter.isInReview()) {
                    animations.slideLeft(view.ivArticle) {
                        presenter.disLikeArticle()
                    }
            }
        }

        view.btReview?.setOnClickListener {
            presenter.review()
        }

    }

    override fun showArticle( article: ArticlesItem ) {

        view?.ivLoading?.visibility = View.VISIBLE
        view?.ivArticle?.visibility = View.GONE
        view?.ivArticle?.clearAnimation()
        view?.ivLoading?.setImageResource(R.drawable.progress_animation)

        Picasso.with( context )
                .load( Uri.parse( article.media!![0]?.uri) )
                .fit()
                .centerInside()
                .error( R.drawable.image_broken)
                .into( view?.ivArticle, object : Callback {
                    override fun onSuccess() {
                        view?.ivLoading?.visibility = View.GONE
                        view?.ivArticle?.visibility = View.VISIBLE
                    }
                    override fun onError() {
                        view?.ivLoading?.visibility = View.GONE
                        view?.ivArticle?.visibility = View.VISIBLE
                    }
                })

    }

    override fun hideArticle() {
        ivArticle.visibility = View.GONE
    }

    override fun hideReview() {
        btReview.visibility = View.GONE
    }

    override fun showReview() {
        btReview.visibility = View.VISIBLE
        ivArticle.clearAnimation()
    }

    override fun showLikesCount( likedCount : String) {
        if( tvLiked?.text?.toString()?.equals( likedCount ) == false ) {
            tvLiked?.text = likedCount
            animations.expandStar(  ivLikeStar ){}
        }
    }

    override fun showTotalCount( totalCount : String) {
        tvTotal?.text = totalCount
    }

    override fun showError( error: String ) {

        view?.ivLoading?.visibility = View.VISIBLE
        view?.ivArticle?.visibility = View.GONE
        view?.ivLoading?.setImageResource(R.drawable.progress_animation)

        Picasso.with( context )
                .load( Uri.parse( error) )
                .fit()
                .centerInside()
                .error( R.drawable.image_broken)
                .into( view?.ivArticle, object : Callback {
                    override fun onSuccess() {
                        view?.ivLoading?.visibility = View.GONE
                        view?.ivArticle?.visibility = View.VISIBLE
                    }
                    override fun onError() {
                        view?.ivLoading?.visibility = View.GONE
                        view?.ivArticle?.visibility = View.VISIBLE
                    }
                })

        snack( error )

    }

    override fun showLoading() {
        view?.ivLoading?.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        view?.ivLoading?.visibility = View.GONE
    }

    private fun snack( message : String ){
        view?.clRoot?.let {
            Snackbar.make( it, message, Snackbar.LENGTH_LONG).show()
        }
    }

}