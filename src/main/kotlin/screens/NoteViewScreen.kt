package screens

import NavigationAction
import data.Note

/**
 * Экран просмотра заметки
 *
 * Отображает название экрана
 * Принимает на вход заметку
 * Отрисовывает заметку и меню
 * Обрабатывает пользовательский ввод
 *
 * Умеет обрабатывать 1 действие и выбрасывает колбэк с соответствующим NavigationAction:
 * - выход NavigationAction.Back
 *
 */
class NoteViewScreen(val note: Note) : Screen() {

    override fun start(onNavigate: (NavigationAction) -> Unit) {
        showMenu()
        readUserInput(onNavigate)
    }

    private fun showMenu() {
        println("Заметка: ${note.name}")
        println(note.text)
        println("Для выхода введите 0")
    }

    private fun readUserInput(onNavigate: (NavigationAction) -> Unit) {
        while(true) {
            val input = readln().trim()
            if(input.isNotEmpty()) {
                onNavigate(NavigationAction.Back)
            }
        }
    }
}