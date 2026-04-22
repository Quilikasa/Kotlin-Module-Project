package screens

import NavigationAction

abstract class ListScreen<T>(val list: MutableList<T>) : Screen() {

    abstract val screenTitle: String
    abstract val actionCreate: NavigationAction
    abstract val actionExit: NavigationAction

    abstract fun getActionForChoice(choice: Int): NavigationAction

    abstract fun getMenuList(): List<String>

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