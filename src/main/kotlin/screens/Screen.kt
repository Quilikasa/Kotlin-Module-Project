package screens

import NavigationAction

abstract class Screen {
    abstract fun start(onNavigate : (NavigationAction) -> Unit)
}