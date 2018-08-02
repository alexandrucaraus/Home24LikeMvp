package eu.caraus.home24.application.ui.main.review

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import eu.caraus.home24.R
import eu.caraus.home24.application.data.domain.home24.ArticlesItem
import eu.caraus.home24.application.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_ratings.view.*


import javax.inject.Inject
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class ReviewFragment : BaseFragment(), ReviewContract.View {


    companion object {

        val TAG = ReviewFragment::class.java.simpleName

        const val REVIEW_LIST = "REVIEW_LIST"
        const val LIKED_LIST = "LIKED_LIST"

        fun newInstance( items : List<ArticlesItem?>, liked : List<String> ) : ReviewFragment {

            val fragment = ReviewFragment()
            val bundle = Bundle()

            bundle.putSerializable( REVIEW_LIST , Gson().toJson(items))
            bundle.putSerializable( LIKED_LIST  , Gson().toJson(liked))

            fragment.arguments = bundle

            return fragment
        }

    }

    @Inject
    lateinit var presenter : ReviewContract.Presenter

    lateinit var adapter   : ReviewAdapter

    var list  : List<ArticlesItem>? = null
    var liked : List<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver( presenter )
        setHasOptionsMenu(true)
    }

    override fun onResume() {
        presenter.onViewAttached(this)
        super.onResume()
    }

    override fun onPause() {
        presenter.onViewDetached(true)
        super.onPause()
    }

    override fun onDestroy() {
        lifecycle.removeObserver( presenter )
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_ratings, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when ( item?.itemId ) {
            android.R.id.home -> {
                presenter.goBack()
            }
        }
        return true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_ratings, null)

        arguments?.getString(REVIEW_LIST)?.let {
            list = Gson().fromJson( it , toType<List<ArticlesItem?>>())
        }

        arguments?.getString(LIKED_LIST)?.let {
            liked = Gson().fromJson( it, toType<List<String>?>())
        }


        init( view, list!!, liked )

        return view
    }

    private fun init(view : View, articles : List<ArticlesItem?> , liked: List<String>? ){

        view.rvReviewItems.layoutManager = LinearLayoutManager( context )

        adapter = ReviewAdapter( articles , liked )
        adapter.registerAdapterDataObserver( object : RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                super.onChanged()
                checkEmptyAdapter()
            }
        })

        view.rvReviewItems.adapter = adapter

    }

    private fun checkEmptyAdapter() {
        when( adapter.itemCount ){
            0 -> {}
            else -> {}
        }
    }

    inline fun <reified T> toType() = object: TypeToken<T>() {}.type

}