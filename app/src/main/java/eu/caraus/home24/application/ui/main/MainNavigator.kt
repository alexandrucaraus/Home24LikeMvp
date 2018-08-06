package eu.caraus.home24.application.ui.main

import eu.caraus.home24.application.ui.main.znav.MainNavigation

/**
 *  MainNavigator - a class to hold all possible flow directions of the MainActivity,
 *  @param - navigation [MainNavigation]
 */

class MainNavigator( private val navigation : MainNavigation ) : MainContract.Navigator {

    /**
     *  this method instructs navigation to load selection screen
     */
    override fun showSelection() {
        navigation.navigateToSelectionFragment()
    }

    /**
     *  this method is used for back navigation in MainActivity
     */
    override fun goBack(): Boolean {
        return navigation.goBack()
    }

}