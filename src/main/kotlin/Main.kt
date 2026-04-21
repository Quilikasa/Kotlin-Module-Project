import data.Archive
import data.Note
import screens.ArchiveCreateScreen
import screens.ArchiveListScreen
import screens.NoteCreateScreen
import screens.NoteListScreen
import screens.NoteViewScreen
import screens.Screen
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    //инициализация хранилища
    val archives: MutableList<Archive> = mutableListOf()
    //заполнение хранилища тестовыми данными
    val notes: MutableList<Note> = mutableListOf()
    notes.add(Note("Заметка 1", "На работе платят бабло"))
    notes.add(Note("Заметка 2", "Но работать надо на ней"))
    notes.add(Note("Заметка 3", "Я не против первого но"))
    notes.add(Note("Заметка 4", "Без второго мне веселей"))

    archives.add(Archive("Работа", notes))
    archives.add(Archive("Дом"))
    archives.add(Archive("Спорт"))
    archives.add(Archive("Хобби"))

    //стартовый экран
    var currentScreen: Screen = ArchiveListScreen(archives)
    var currentArchive: Int = 0

    //логика навигации и запуска экранов
    fun navigateTo(action: NavigationAction) {
        currentScreen = when(action) {
            is NavigationAction.Back -> {
                when(currentScreen) {
                    is NoteListScreen -> ArchiveListScreen(archives)
                    is NoteViewScreen -> NoteListScreen(archives[currentArchive].notes)
                    is ArchiveCreateScreen -> ArchiveListScreen(archives)
                    is NoteCreateScreen -> NoteListScreen(archives[currentArchive].notes)
                    else -> ArchiveListScreen(archives)
                }
            }
            is NavigationAction.Exit -> {
                exitProcess(0)
            }
            is NavigationAction.CreateArchive -> ArchiveCreateScreen()
            is NavigationAction.OpenArchive -> {
                currentArchive = action.archiveIndex
                NoteListScreen(archives[currentArchive].notes)
            }
            is NavigationAction.CreateNote -> NoteCreateScreen()
            is NavigationAction.OpenNote -> NoteViewScreen(action.note)
            is NavigationAction.SaveArchiveAndBack -> {
                archives.add(action.archive)
                ArchiveListScreen(archives)
            }
            is NavigationAction.SaveNoteAndBack -> {
                archives[currentArchive].notes.add(action.note)
                NoteListScreen(archives[currentArchive].notes)
            }
        }

        currentScreen.start(::navigateTo)
    }

    currentScreen.start(::navigateTo)
}