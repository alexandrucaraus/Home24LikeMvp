package eu.caraus.home24.application.ui.main

import android.os.Bundle
import eu.caraus.home24.R

import eu.caraus.home24.application.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View {

    companion object {
        val TAG = MainActivity::class.java.simpleName!!
    }

    @Inject
    lateinit var presenter : MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycle.addObserver( presenter )

        setContentView( R.layout.activity_main )

        setSupportActionBar( toolbar )

    }

    override fun onStart() {
        super.onStart()
        presenter.onViewAttached(this)
    }

    override fun onStop() {
        presenter.onViewDetached(false)
        super.onStop()
    }

    override fun onDestroy() {
        lifecycle.removeObserver( presenter )
        super.onDestroy()
    }

    override fun onBackPressed() {
        if( presenter.onBack() )
        else
            finish()
    }

}
