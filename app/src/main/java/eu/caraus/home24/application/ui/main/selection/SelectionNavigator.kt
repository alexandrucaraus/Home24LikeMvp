package eu.caraus.home24.application.ui.main.selection

import eu.caraus.home24.application.data.domain.home24.ArticlesItem
import eu.caraus.home24.application.ui.main.znav.MainNavigation

class SelectionNavigator( private val navigation : MainNavigation ) : SelectionContract.Navigator {

    override fun navigateToReviewScreen(list: List<ArticlesItem?>, liked : List<String>) {
        navigation.navigateToReviewFragment(list,liked)
    }

}