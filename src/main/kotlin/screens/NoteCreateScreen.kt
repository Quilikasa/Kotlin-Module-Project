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
class NoteCreateScreen() : CreateScreen() {

    private var noteName: String? = null

    override val screenTitle = "Для создания новой заметки сначала введите ее название"

    override fun getActionForInput(input: String): NavigationAction? {
        if(noteName == null) {
            noteName = input
            println("Теперь введите текст заметки")
            return null
        } else {
            return NavigationAction.SaveNoteAndBack(Note(noteName ?: "Название", input))
        }
    }
}