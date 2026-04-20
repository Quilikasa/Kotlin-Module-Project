import data.Archive
import data.Note
import screens.ArchiveListScreen
import screens.NoteListScreen
import screens.NoteViewScreen
import screens.Screen
import java.util.Scanner

fun main(args: Array<String>) {
    //инициализация сканера
    val scanner = Scanner(System.`in`)

    //инициализация хранилища
    val archives: MutableList<Archive> = mutableListOf()
    //заполнение хранилища тестовыми данными
    val notes: MutableList<Note> = mutableListOf()
    notes.add(Note("Заметка 1", "На работе платят бабло"))
    notes.add(Note("Заметка 2", "Но работать надо на ней"))
    notes.add(Note("Заметка 3", "Я не против первого но"))
    notes.add(Note("Заметка 4", "Без второго мне веселей"))

    archives.add(Archive("Работа", notes))
    archives.add(Archive("Дом", mutableListOf()))
    archives.add(Archive("Спорт", mutableListOf()))
    archives.add(Archive("Хобби", mutableListOf()))

    //стартовый экран
    var currentScreen: Screen = ArchiveListScreen(scanner, archives)
    var currentArchive: Int = 0

    //логика навигации и запуска экранов
    fun navigateTo(action: NavigationAction) {
        currentScreen = when(action) {
            is NavigationAction.Back -> {
                when(currentScreen) {
                    is NoteListScreen -> ArchiveListScreen(scanner, archives)
                    is NoteViewScreen -> NoteListScreen(scanner, archives[currentArchive].notes)
                    else -> ArchiveListScreen(scanner, archives)
                }
            }
            is NavigationAction.Exit -> return
            is NavigationAction.CreateArchive -> TODO()
            is NavigationAction.OpenArchive -> {
                currentArchive = action.archiveIndex
                NoteListScreen(scanner, archives[currentArchive].notes)
            }
            is NavigationAction.CreateNote -> TODO()
            is NavigationAction.OpenNote -> NoteViewScreen(scanner, action.note)
        }

        currentScreen.start(::navigateTo)
    }

    currentScreen.start(::navigateTo)
}