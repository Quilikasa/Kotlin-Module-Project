import java.util.Scanner

/**
 * Экран списка заметок
 *
 * Отображает название экрана
 * Принимает на вход список заметок
 * Отрисовывает список заметок и меню
 * Обрабатывает пользовательский ввод (на вход так же принимает сканер)
 *
 * Умеет обрабатывать 3 действия и выбрасывает соответствующие колбэки:
 * 1) выбор заметки onShowElement()
 * 2) создание новой заметки onCreateElement()
 * 3) выход с экрана onExit()
 *
 */
class NoteListScreen(val scanner: Scanner, val notes: MutableList<Note>) : Screen() {

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
            val input = scanner.nextLine().trim()
            when(input) {
                "0" -> {
                    onNavigate(NavigationAction.CreateNote)
                    break
                }
                "${notes.size+1}" -> {
                    onNavigate(NavigationAction.Back)
                    break
                }
                else -> {
                    onNavigate(NavigationAction.OpenNote(input))
                    break
                    //обработать негативные сценарии
                }
            }
        }
    }
}