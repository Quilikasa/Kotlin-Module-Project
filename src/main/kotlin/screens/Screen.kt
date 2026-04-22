package screens

import NavigationAction

abstract class Screen {

    fun start(onNavigate : (NavigationAction) -> Unit) {
        showMenu()
        readUserInput(onNavigate)
    }

    abstract fun showMenu()

    abstract fun readUserInput(onNavigate : (NavigationAction) -> Unit)
}