package eu.caraus.home24.application.ui.main

import eu.caraus.home24.application.ui.main.znav.MainNavigation

class MainNavigator( private val navigation : MainNavigation ) : MainContract.Navigator {

    override fun showSelection() {
        navigation.navigateToSelectionFragment()
    }
    override fun goBack(): Boolean {
        return navigation.goBack()
    }

}