package eu.caraus.home24.application.ui.main.znav

import android.content.Context
import android.support.annotation.IdRes

import android.support.v4.app.FragmentManager
import eu.caraus.home24.application.data.domain.home24.ArticlesItem

import eu.caraus.home24.application.ui.base.BaseActivity
import eu.caraus.home24.application.ui.base.BaseFragment
import eu.caraus.home24.application.ui.main.review.ReviewFragment
import eu.caraus.home24.application.ui.main.selection.SelectionFragment
import java.lang.ref.WeakReference

class MainNavigation( activity: BaseActivity, @param:IdRes @field:IdRes private val containerId: Int) {

    private val refContext: WeakReference<Context> = WeakReference( activity )

    private val fragmentManager: FragmentManager
        get() = (context() as BaseActivity).supportFragmentManager


    fun navigateToSelectionFragment() {
        loadFragmentWithTag( SelectionFragment.newInstance(), SelectionFragment.TAG )
    }

    fun navigateToReviewFragment( reviewedItems : HashMap<ArticlesItem?,Boolean> ){
        loadFragmentWithTag( ReviewFragment.newInstance( reviewedItems), ReviewFragment.TAG )
    }

    fun goBack() : Boolean {
        if( fragmentManager.backStackEntryCount <= 1 )
            return true
        else
            fragmentManager.popBackStackImmediate()
        return false
    }

    private fun loadFragmentWithTag(fragment: BaseFragment, tag : String) {

        val currentFragment = fragmentManager.findFragmentById( containerId )

        val transaction = fragmentManager.beginTransaction()

        currentFragment?.let {
            transaction.hide(it)
        }

        transaction.add( containerId, fragment)
        transaction.addToBackStack( null )
        transaction.commit()

    }

    private fun context(): Context? {
        return refContext.get()
    }

}