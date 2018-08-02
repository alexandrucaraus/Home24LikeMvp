package eu.caraus.home24.application.ui.main

import eu.caraus.home24.application.ui.main.znav.MainNavigation

class MainNavigator(private val navigation : MainNavigation) : MainContract.Navigator {

    override fun showCourseList() {
        navigation.navigateToSelectionFragment()
    }

    override fun showCourseDetails(courseId: String) {
        //navigation.navigateToCourseDetails( courseId )
    }

    override fun goBack(): Boolean {
        return navigation.goBack()
    }

}