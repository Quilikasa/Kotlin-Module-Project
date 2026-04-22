package screens

import NavigationAction

abstract class CreateScreen() : Screen() {

    abstract val screenTitle: String

    abstract fun getActionForInput(input: String): NavigationAction?

    override fun showMenu() {
        println(screenTitle)
        println("0. Выход")
    }

    override fun readUserInput(onNavigate: (NavigationAction) -> Unit) {
        while(true) {
            val input = readln().trim()
            when (input) {
                "0" -> {
                    onNavigate(NavigationAction.Back)
                    break
                }
                "" -> {
                    println("Введите название или текст")
                }
                else -> {
                    val action = getActionForInput(input)
                    if(action != null) {
                        onNavigate(action)
                        break
                    }
                }
            }
        }
    }
}