package screens

import NavigationAction
import data.Note
import java.util.Scanner

/**
 * Экран просмотра заметки
 *
 * Отображает название экрана
 * Принимает на вход заметку
 * Отрисовывает заметку и меню
 * Обрабатывает пользовательский ввод (для этого на вход так же принимает сканер)
 *
 * Умеет обрабатывать 1 действие и выбрасывает колбэк с соответствующим NavigationAction:
 * - выход NavigationAction.Back
 *
 */
class NoteViewScreen(val scanner: Scanner, val note: Note) : Screen() {

    override fun start(onNavigate: (NavigationAction) -> Unit) {
        showMenu()
        readUserInput(onNavigate)
    }

    private fun showMenu() {
        println("Заметка: ${note.name}")
        println(note.text)
        println("Для выхода введите любой символ")
    }

    private fun readUserInput(onNavigate: (NavigationAction) -> Unit) {
        while(true) {
            val input = scanner.nextLine().trim()
            if(input.isNotEmpty()) {
                onNavigate(NavigationAction.Back)
            }
        }
    }
}