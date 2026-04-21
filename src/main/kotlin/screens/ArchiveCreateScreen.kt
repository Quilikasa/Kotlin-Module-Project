package screens

import NavigationAction
import data.Archive
import java.util.Scanner

/**
 * Экран создания нового архива
 * Новый архив создается пустой, без заметок
 *
 * Отображает название экрана
 * Отрисовывает меню
 * Обрабатывает пользовательский ввод (для этого на вход так же принимает сканер)
 *
 * Умеет обрабатывать 2 действия и выбрасывает колбэк с соответствующим NavigationAction:
 * 1) создание нового архива NavigationAction.SaveArchiveAndBack
 * 2) выход NavigationAction.Back
 *
 */
class ArchiveCreateScreen(val scanner: Scanner): Screen() {

    override fun start(onNavigate: (NavigationAction) -> Unit) {
        showMenu()
        readUserInput(onNavigate)
    }

    private fun showMenu() {
        println("Для создания нового архива введите его название")
        println("Введите 0 для выхода")
    }

    private fun readUserInput(onNavigate: (NavigationAction) -> Unit) {
        while(true) {
            val input = scanner.nextLine().trim()
            when(input) {
                "0" -> {
                    onNavigate(NavigationAction.Back)
                    break
                }
                else -> {
                    val newArchive = Archive(input, mutableListOf())
                    onNavigate(NavigationAction.SaveArchiveAndBack(newArchive))
                    break
                    //TODO обработать негативные сценарии
                }
            }
        }
    }
}