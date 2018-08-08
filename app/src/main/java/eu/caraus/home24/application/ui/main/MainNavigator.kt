package eu.caraus.home24.application.ui.main

import eu.caraus.home24.application.ui.main.znav.MainScreenFlow

/**
 *  MainNavigator - a class to hold all possible flow directions of the MainActivity,
 *  @param - navigation [MainScreenFlow]
 */

class MainNavigator( private val navigation : MainScreenFlow ) : MainContract.Navigator {

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