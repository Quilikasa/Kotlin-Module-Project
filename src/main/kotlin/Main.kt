import java.util.Scanner

fun main(args: Array<String>) {
    //инициализация сканера
    val scanner = Scanner(System.`in`)

    //инициализация хранилища
    val archives: MutableList<Archive> = mutableListOf()
    //заполнение хранилища тестовыми данными
    val notes: MutableList<Note> = mutableListOf()
    notes.add(Note("Заметка 1", "Текст 1"))
    notes.add(Note("Заметка 2", "Текст 2"))
    notes.add(Note("Заметка 3", "Текст 3"))
    notes.add(Note("Заметка 4", "Текст 4"))
    notes.add(Note("Заметка 5", "Текст 5"))

    archives.add(Archive("Работа", notes))
    archives.add(Archive("Дом", mutableListOf()))
    archives.add(Archive("Спорт", mutableListOf()))
    archives.add(Archive("Хобби", mutableListOf()))

    //логика навигации и запуска экранов
    var currentScreen: Screen = ArchiveListScreen(scanner, archives)

    fun navigateTo(action: NavigationAction) {
        currentScreen = when(action) {
            is NavigationAction.Back -> ArchiveListScreen(scanner, archives)
            is NavigationAction.Exit -> return
            is NavigationAction.CreateArchive -> TODO()
            is NavigationAction.OpenArchive -> NoteListScreen(scanner, archives[0].notes)
            is NavigationAction.CreateNote -> TODO()
            is NavigationAction.OpenNote -> TODO()
        }

        currentScreen.start(::navigateTo)
    }


    currentScreen.start(::navigateTo)
}