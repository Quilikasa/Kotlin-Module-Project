package screens

import NavigationAction

abstract class ListScreen<T>(val list: List<T>) : Screen() {

    protected abstract val screenTitle: String
    protected abstract val actionCreate: NavigationAction
    protected abstract val actionExit: NavigationAction

    protected abstract fun getActionForChoice(choice: Int): NavigationAction

    protected abstract fun getMenuList(): List<String>

    override fun showMenu() {
        println(screenTitle)
        val listMenu = getMenuList()
        listMenu.forEachIndexed { index, value ->
            println("$index. $value")
        }
    }

    override fun readUserInput(onNavigate: (NavigationAction) -> Unit) {
        while(true) {
            var input = -1
            try {
                input = readln().trim().toInt()
            } catch (e: NumberFormatException) {
                println("Следует вводить цифры")
                continue
            }

            when(input) {
                0 -> {
                    onNavigate(actionCreate)
                    break
                }
                in 1..list.size -> {
                    onNavigate(getActionForChoice(input))
                    break
                }
                list.size+1 -> {
                    onNavigate(actionExit)
                    break
                }
                else -> {
                    println("Нет такого пункта меню")
                }
            }
        }
    }
}