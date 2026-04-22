package screens

import NavigationAction
import data.Note

/**
 * Экран создания новой заметки
 * Необходимо ввести название и текст
 *
 * Отображает название экрана
 * Отрисовывает меню
 * Обрабатывает пользовательский ввод
 *
 * Умеет обрабатывать 2 действия и выбрасывает колбэк с соответствующим NavigationAction:
 * 1) создание новой заметки NavigationAction.SaveNoteAndBack
 * 2) выход NavigationAction.Back
 *
 */
class NoteCreateScreen() : Screen() {

    override fun showMenu() {
        println("Для создания новой заметки сначала введите ее название")
        println("0. Выход")
    }

    override fun readUserInput(onNavigate: (NavigationAction) -> Unit) {
        var noteName: String? = null
        while(true) {
            val input = readln().trim()
            when(input) {
                "0" -> {
                    onNavigate(NavigationAction.Back)
                    break
                }
                else -> {
                    if(noteName == null) {
                        noteName = input
                        println("Теперь введите текст заметки")
                    } else {
                        onNavigate(NavigationAction.SaveNoteAndBack(Note(noteName, input)))
                        break
                    }
                }
            }
        }
    }
}