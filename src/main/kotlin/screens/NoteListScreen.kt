package screens

import NavigationAction
import data.Note

/**
 * Экран списка заметок
 *
 * Отображает название экрана
 * Принимает на вход список заметок
 * Отрисовывает список заметок и меню
 * Обрабатывает пользовательский ввод
 *
 * Умеет обрабатывать 3 действия и выбрасывает колбэк с соответствующим NavigationAction:
 * 1) выбор заметки NavigationAction.OpenNote
 * 2) создание новой заметки NavigationAction.CreateNote
 * 3) выход NavigationAction.Back
 *
 */
class NoteListScreen(val notes: MutableList<Note>) : ListScreen<Note>(notes) {

    override val screenTitle = "Список заметок:"
    override val actionCreate = NavigationAction.CreateNote
    override val actionExit = NavigationAction.Back

    override fun getActionForChoice(choice: Int): NavigationAction {
        return NavigationAction.OpenNote(notes[choice-1])
    }

    override fun getMenuList(): List<String> {
        val listMenu = mutableListOf<String>()
        listMenu.add("Создать заметку")
        notes.forEach { note -> listMenu.add(note.name) }
        listMenu.add("Выход")
        return listMenu
    }
}