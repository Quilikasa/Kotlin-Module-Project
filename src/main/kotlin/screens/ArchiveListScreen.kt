package screens

import NavigationAction
import data.Archive
import java.util.Scanner

/**
 * Экран списка архивов - стартовый
 *
 * Отображает название экрана
 * Принимает на вход список архивов
 * Отрисовывает список архивов и меню
 * Обрабатывает пользовательский ввод (для этого на вход так же принимает сканер)
 *
 * Умеет обрабатывать 3 действия и выбрасывает колбэк с соответствующим NavigationAction:
 * 1) выбор архива NavigationAction.OpenArchive
 * 2) создание нового архива NavigationAction.CreateArchive
 * 3) выход NavigationAction.Exit
 *
 */
class ArchiveListScreen(val scanner: Scanner, val archives: MutableList<Archive>): Screen() {

    override fun start(onNavigate: (NavigationAction) -> Unit) {
        showMenu()
        readUserInput(onNavigate)
    }

    private fun showMenu() {
        println("Список архивов:")
        println("0. Создать архив")
        showArchiveList()
        println("${archives.size+1}. Выход")
    }

    private fun showArchiveList() {
        for (i in archives.indices) {
            println("${i+1}. ${archives[i].name}")
        }
    }

    private fun readUserInput(onNavigate: (NavigationAction) -> Unit) {
        while(true) {
            val input = scanner.nextLine().trim()
            when(input) {
                "0" -> {
                    onNavigate(NavigationAction.CreateArchive)
                    break
                }
                "${archives.size+1}" -> {
                    onNavigate(NavigationAction.Exit)
                    break
                }
                else -> {
                    onNavigate(NavigationAction.OpenArchive(input.toInt()-1))
                    break
                    //TODO обработать негативные сценарии
                }
            }
        }
    }
}