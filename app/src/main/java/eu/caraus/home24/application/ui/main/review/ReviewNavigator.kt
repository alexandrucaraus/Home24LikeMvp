package eu.caraus.home24.application.ui.main.review

import eu.caraus.home24.application.ui.main.znav.MainScreenFlow

/**
 *  Navigator for the review screen
 */

class ReviewNavigator (private val navigation: MainScreenFlow) : ReviewContract.Navigator {

    override fun goBack() {
        navigation.goBack()
    }

}