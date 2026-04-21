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
class NoteListScreen(val notes: MutableList<Note>) : Screen() {

    override fun start(onNavigate: (NavigationAction) -> Unit) {
        showMenu()
        readUserInput(onNavigate)
    }

    private fun showMenu() {
        println("Список заметок:")
        println("0. Создать заметку")
        showNoteList()
        println("${notes.size+1}. Выход")
    }

    private fun showNoteList() {
        for (i in notes.indices) {
            println("${i+1}. ${notes[i].name}")
        }
    }

    private fun readUserInput(onNavigate: (NavigationAction) -> Unit) {
        while(true) {
            var input = -1
            try {
                input = readln().trim().toInt()
            } catch (e: NumberFormatException) {
                println("Следует вводить цифры")
                continue
            }

            when(input) {
                0 -> {
                    onNavigate(NavigationAction.CreateNote)
                    break
                }
                in 1..notes.size -> {
                    onNavigate(NavigationAction.OpenNote(notes[input-1]))
                    break
                }
                notes.size+1 -> {
                    onNavigate(NavigationAction.Back)
                    break
                }
                else -> {
                    println("Нет такого пункта меню")
                    break
                }
            }
        }
    }
}