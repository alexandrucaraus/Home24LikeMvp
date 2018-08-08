package eu.caraus.home24.application.ui.main.selection

import eu.caraus.home24.application.data.domain.home24.ArticlesItem
import eu.caraus.home24.application.ui.main.znav.MainScreenFlow

/**
 *  SelectionNavigator - this class holds all the places where one can navigate from
 *  selection screen
 *
 */

class SelectionNavigator( private val navigation : MainScreenFlow ) : SelectionContract.Navigator {

    override fun navigateToReviewScreen( reviewedItems : HashMap<ArticlesItem?,Boolean> ) {
        navigation.navigateToReviewFragment( reviewedItems )
    }

}