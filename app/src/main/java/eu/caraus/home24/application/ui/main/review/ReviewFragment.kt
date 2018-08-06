package eu.caraus.home24.application.ui.main.review

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import eu.caraus.home24.R
import eu.caraus.home24.application.data.domain.home24.ArticlesItem
import eu.caraus.home24.application.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_ratings.view.*


import javax.inject.Inject
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import eu.caraus.home24.application.ui.base.BaseActivity
import kotlinx.android.synthetic.main.fragment_ratings.*


class ReviewFragment : BaseFragment(), ReviewContract.View {


    companion object {

        val TAG = ReviewFragment::class.java.simpleName

        const val REVIEWED_ITEMS_MAP = "REVIEWED_ITEMS_MAP"

        fun newInstance( reviewedItems : HashMap<ArticlesItem?,Boolean>  ) : ReviewFragment {

            val fragment = ReviewFragment()

            val bundle = Bundle()

            val gson = GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting().create()

            bundle.putSerializable( REVIEWED_ITEMS_MAP , gson.toJson( reviewedItems ))

            fragment.arguments = bundle

            return fragment
        }

    }

    @Inject
    lateinit var presenter : ReviewContract.Presenter

    private lateinit var adapter   : ReviewAdapter

    private var map  : HashMap<ArticlesItem?,Boolean>? = null

    private var isShowAsList = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver( presenter )
        setHasOptionsMenu(true)
    }

    override fun onResume() {
        activity?.invalidateOptionsMenu()
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

    override fun onPrepareOptionsMenu(menu: Menu?) {
        ( activity as BaseActivity).apply {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(false)
        }

        menu?.findItem( R.id.menu_view )
                ?.setIcon( if( isShowAsList ) R.drawable.view_grid
                                  else        R.drawable.view_list )

        super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when ( item?.itemId ) {

            android.R.id.home ->  presenter.goBack()

            R.id.menu_view -> {
                    isShowAsList = !isShowAsList
                    setRecycleViewLayoutManager( isShowAsList )
                    activity?.invalidateOptionsMenu()
            }

        }
        return true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_ratings, null)

        arguments?.getString(REVIEWED_ITEMS_MAP)?.let {

            map = GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting().create()
                            .fromJson( it , toType<HashMap<ArticlesItem?,Boolean>>())

        }

        // TODO : check this
        init( view, map!! )

        return view
    }

    private fun init(view : View, map : HashMap<ArticlesItem?,Boolean> ){

        (activity as BaseActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        view.rvReviewItems.layoutManager =
                if( isShowAsList == true )
                    LinearLayoutManager( context )
                else
                    GridLayoutManager( context , 2 )

        adapter = ReviewAdapter( map)
        adapter.registerAdapterDataObserver( object : RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                super.onChanged()
                checkEmptyAdapter()
            }
        })

        view.rvReviewItems.adapter = adapter

    }

    private fun setRecycleViewLayoutManager( asList : Boolean ){

        val visibleItemPosition = ( rvReviewItems.layoutManager as LinearLayoutManager )
                .findFirstCompletelyVisibleItemPosition()

        if( asList )
            rvReviewItems?.layoutManager = LinearLayoutManager( context )
        else
            rvReviewItems?.layoutManager = GridLayoutManager( context, 2)

        adapter.changeViewType( asList )

        rvReviewItems?.adapter = adapter

        rvReviewItems?.scrollToPosition( visibleItemPosition)

    }

    private fun checkEmptyAdapter() { //TODO: check this

        when( adapter.itemCount ){
            0 -> {}
            else -> {}
        }
    }

    inline fun <reified T> toType() = object : TypeToken<T>() {}.type

}