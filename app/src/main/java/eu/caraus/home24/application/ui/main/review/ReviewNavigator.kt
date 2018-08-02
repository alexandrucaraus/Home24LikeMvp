package eu.caraus.home24.application.ui.main.review

import eu.caraus.home24.application.ui.main.znav.MainNavigation

class ReviewNavigator (private val navigation: MainNavigation) : ReviewContract.Navigator {



    override fun goBack() {
        navigation.goBack()
    }

}