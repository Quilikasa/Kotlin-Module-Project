package screens

import NavigationAction

abstract class Screen {

    fun start(onNavigate : (NavigationAction) -> Unit) {
        showMenu()
        readUserInput(onNavigate)
    }

    protected abstract fun showMenu()

    protected abstract fun readUserInput(onNavigate : (NavigationAction) -> Unit)
}