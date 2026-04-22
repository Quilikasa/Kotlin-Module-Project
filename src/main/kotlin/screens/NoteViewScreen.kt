package screens

import NavigationAction
import data.Note

/**
 * Экран просмотра заметки
 *
 * Отображает название экрана и заметки
 * Принимает на вход заметку
 * Отрисовывает заметку и меню
 * Обрабатывает пользовательский ввод
 *
 * Умеет обрабатывать 1 действие и выбрасывает колбэк с соответствующим NavigationAction:
 * - выход NavigationAction.Back
 *
 */
class NoteViewScreen(val note: Note) : Screen() {

    override fun showMenu() {
        println("Заметка: ${note.name}")
        println(note.text)
        println("0. Выход")
    }

    override fun readUserInput(onNavigate: (NavigationAction) -> Unit) {
        while(true) {
            val input = readln().trim()
            if(input.equals("0")) {
                onNavigate(NavigationAction.Back)
            } else {
                println("Для выхода с экрана введите 0")
            }
        }
    }
}