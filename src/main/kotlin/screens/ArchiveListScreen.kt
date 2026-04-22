package screens

import NavigationAction
import data.Archive

/**
 * Экран списка архивов - стартовый
 *
 * Отображает название экрана
 * Принимает на вход список архивов
 * Отрисовывает список архивов и меню
 * Обрабатывает пользовательский ввод
 *
 * Умеет обрабатывать 3 действия и выбрасывает колбэк с соответствующим NavigationAction:
 * 1) выбор архива NavigationAction.OpenArchive
 * 2) создание нового архива NavigationAction.CreateArchive
 * 3) выход NavigationAction.Exit
 *
 */
class ArchiveListScreen(val archives: MutableList<Archive>): ListScreen<Archive>(archives) {

    override val screenTitle = "Список архивов:"
    override val actionCreate = NavigationAction.CreateArchive
    override val actionExit = NavigationAction.Exit

    override fun getActionForChoice(choice: Int): NavigationAction {
        return NavigationAction.OpenArchive(choice-1)
    }

    override fun getMenuList(): List<String> {
        val listMenu = mutableListOf<String>()
        listMenu.add("Создать архив")
        archives.forEach { archive -> listMenu.add(archive.name) }
        listMenu.add("Выход")
        return listMenu
    }
}