package screens

import NavigationAction
import data.Archive

/**
 * Экран создания нового архива
 * Архив создается пустой, без заметок
 *
 * Отображает название экрана
 * Отрисовывает меню
 * Обрабатывает пользовательский ввод
 *
 * Умеет обрабатывать 2 действия и выбрасывает колбэк с соответствующим NavigationAction:
 * 1) создание нового архива NavigationAction.SaveArchiveAndBack
 * 2) выход NavigationAction.Back
 *
 */
class ArchiveCreateScreen(): Screen() {

    override fun showMenu() {
        println("Для создания нового архива введите его название")
        println("0. Выход")
    }

    override fun readUserInput(onNavigate: (NavigationAction) -> Unit) {
        while(true) {
            val input = readln().trim()
            when(input) {
                "0" -> {
                    onNavigate(NavigationAction.Back)
                    break
                }
                else -> {
                    if(input.isNotEmpty()) {
                        val newArchive = Archive(input)
                        onNavigate(NavigationAction.SaveArchiveAndBack(newArchive))
                        break
                    }
                }
            }
        }
    }
}