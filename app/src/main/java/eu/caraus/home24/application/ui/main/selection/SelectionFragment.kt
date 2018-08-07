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
import eu.caraus.home24.application.ui.main.selection.SelectionContract.Presenter.Companion.MODE_LEFT
import eu.caraus.home24.application.ui.main.selection.SelectionContract.Presenter.Companion.MODE_RIGHT

import kotlinx.android.synthetic.main.fragment_selection.*

/**
 *  SelectionFragment - this class is responsible, for displaying
 *  articles and let users express their opinions about the displayed articles by
 *  liking or disliking them
 *
 */

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
        (activity as BaseActivity).apply {
            invalidateOptionsMenu()
        }
        super.onResume()
    }

    override fun onPause() {
        presenter.onViewDetached(true)
        super.onPause()
    }

    override fun onPrepareOptionsMenu(menu: Menu?) {
        ( activity as BaseActivity).apply {
            supportActionBar?.setDisplayHomeAsUpEnabled( false )
            supportActionBar?.setDisplayShowHomeEnabled( true  )
            supportActionBar?.setIcon( R.drawable.home_circle)
        }

        super.onPrepareOptionsMenu(menu)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate( R.layout.fragment_selection, null)
        init(view)
        return view
    }

    /**
     *  initialization of clickable objects
     */
    private fun init( view: View ) {

        view.ivLike?.setOnClickListener {
            if( !presenter.isInReview()) presenter.likeArticle()
        }

        view.ivDislike?.setOnClickListener {
            if( !presenter.isInReview()) presenter.disLikeArticle()
        }

        view.btReview?.setOnClickListener {
            presenter.review()
        }

    }

    /**
     *  this method displays article image in an ImageView
     *  is called by the presenter
     */
    override fun showArticle( article: ArticlesItem , mode : Int ) {

        when( mode ){
            MODE_LEFT  -> { swipeLeft(  article) ; return }
            MODE_RIGHT -> { swipeRight( article) ; return }
        }

        loadImage( article )

    }

    private fun swipeRight( article: ArticlesItem) {
        animations.slideRight( ivArticle) {
            ivArticle.clearAnimation()
            loadImage( article )
        }
    }

    private fun swipeLeft( article: ArticlesItem) {
        animations.slideLeft( ivArticle ){
            ivArticle.clearAnimation()
            loadImage( article )
        }
    }

    private fun loadImage( article: ArticlesItem){
        Picasso.with( context )
                .load( Uri.parse( article.media!![0]?.uri) )
                .fit()
                .centerInside()
                .error( R.drawable.image_broken)
                .into( view?.ivArticle, object : Callback {
                    override fun onSuccess() { ivArticle.visibility = View.VISIBLE }
                    override fun onError()   { ivArticle.visibility = View.VISIBLE }
                })
    }

    /**
     *  this method hides article imageView
     */
    override fun hideArticle() {
        ivArticle.visibility = View.GONE
    }

    /**
     *  this method hides review button
     */
    override fun hideReview() {
        btReview.visibility = View.GONE
    }

    /**
     *  this method shows review button
     */
    override fun showReview() {
        btReview.visibility = View.VISIBLE
        ivArticle.clearAnimation()
    }

    /**
     *  this method receives number of liked articles,
     *  and updates if there was a change in the value
     *  also showing a lame animation after the value is updated
     */
    override fun showLikesCount( likesCount : String) {
        if( tvLiked?.text?.toString()?.equals( likesCount ) == false ) {
            tvLiked?.text = likesCount
            animations.expandStar(  ivLikeStar ){}
        }
    }

    /**
     *  this method will update the total number of viewed articles
     */
    override fun showTotalCount( totalCount : String) {
        tvTotal?.text = totalCount
    }

    /**
     * this method will display an error, if there will be some network error
     *
     */
    override fun showError( error: String ) {

        view?.ivLoading?.visibility = View.VISIBLE
        view?.ivArticle?.visibility = View.GONE

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

    /**
     *
     *  this will show while data is loading
     *
     */
    override fun showLoading() {
        view?.ivLoading?.visibility = View.VISIBLE
    }

    /**
     *  this will show while data is loading
     *
     */
    override fun hideLoading() {
        view?.ivLoading?.visibility = View.GONE
    }

    /**
     *  this will show a snack message
     *
     */
    private fun snack( message : String ){
        view?.clRoot?.let {
            Snackbar.make( it, message, Snackbar.LENGTH_LONG).show()
        }
    }

}