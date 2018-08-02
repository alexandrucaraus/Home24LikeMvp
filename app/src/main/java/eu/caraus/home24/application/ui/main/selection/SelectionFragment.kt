package eu.caraus.home24.application.ui.main.selection

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import eu.caraus.home24.R
import eu.caraus.home24.application.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_selection.view.*
import javax.inject.Inject
import com.squareup.picasso.Picasso
import eu.caraus.home24.application.data.domain.home24.ArticlesItem

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate( R.layout.fragment_selection, null)
        init(view)
        return view
    }

    private fun init( view: View ) {

        deactivateReview()

        view.btReview.setOnClickListener {
            presenter.review()
        }

    }

    override fun showArticle( article: ArticlesItem ) {

        Picasso.with( context )
                .load( Uri.parse( article.media!![0]?.uri) )
                .fit()
                .centerCrop()
                .into(view?.ivArticle)

        view?.ivLike?.setOnClickListener {
            presenter.likeArticle( article )
        }

        view?.ivDislike?.setOnClickListener {
            presenter.disLikeArticle( article )
        }

    }

    override fun showLikesCount( likedCount : String) {
        view?.tvLiked?.text = likedCount
    }

    override fun showTotalCount( viewdCount : String) {
        view?.tvTotal?.text = viewdCount
    }

    override fun activateReview() {
        view?.btReview?.visibility = View.VISIBLE
    }

    override fun deactivateReview() {
        view?.btReview?.visibility = View.GONE
    }

}