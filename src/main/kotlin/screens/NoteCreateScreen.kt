package screens

import NavigationAction
import data.Archive
import data.Note
import java.util.Scanner

/**
 * Экран создания новой заметки
 * Необходимо ввести название и текст
 *
 * Отображает название экрана
 * Отрисовывает меню
 * Обрабатывает пользовательский ввод (для этого на вход так же принимает сканер)
 *
 * Умеет обрабатывать 2 действия и выбрасывает колбэк с соответствующим NavigationAction:
 * 1) создание новой заметки NavigationAction.SaveNoteAndBack
 * 2) выход NavigationAction.Back
 *
 */
class NoteCreateScreen(val scanner: Scanner) : Screen() {

    override fun start(onNavigate: (NavigationAction) -> Unit) {
        showMenu()
        readUserInput(onNavigate)
    }

    private fun showMenu() {
        println("Для создания новой заметки сначала введите ее название, а потом сам текст")
        println("Введите 0 для выхода")
    }

    private fun readUserInput(onNavigate: (NavigationAction) -> Unit) {
        var newNote: Note? = null
        while(true) {
            val input = scanner.nextLine().trim()
            when(input) {
                "0" -> {
                    onNavigate(NavigationAction.Back)
                    break
                }
                else -> {
                    if(newNote == null) {
                        newNote = Note(input, null)
                    } else {
                        newNote.text = input
                        onNavigate(NavigationAction.SaveNoteAndBack(newNote))
                        break
                    }
                    //TODO обработать негативные сценарии
                }
            }
        }
    }
}