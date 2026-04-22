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
    val archives: MutableList<Archive> = getTestData()

    //стартовый экран
    var currentScreen: Screen = ArchiveListScreen(archives)
    var currentArchiveIndex = 0

    //логика навигации и запуска экранов
    fun navigateTo(action: NavigationAction) {
        currentScreen = when(action) {
            is NavigationAction.Back -> {
                when(currentScreen) {
                    is NoteListScreen -> ArchiveListScreen(archives)
                    is NoteViewScreen -> NoteListScreen(archives[currentArchiveIndex].notes)
                    is ArchiveCreateScreen -> ArchiveListScreen(archives)
                    is NoteCreateScreen -> NoteListScreen(archives[currentArchiveIndex].notes)
                    else -> ArchiveListScreen(archives)
                }
            }
            is NavigationAction.Exit -> {
                exitProcess(0)
            }
            is NavigationAction.CreateArchive -> ArchiveCreateScreen()
            is NavigationAction.OpenArchive -> {
                currentArchiveIndex = action.archiveIndex
                NoteListScreen(archives[currentArchiveIndex].notes)
            }
            is NavigationAction.CreateNote -> NoteCreateScreen()
            is NavigationAction.OpenNote -> NoteViewScreen(action.note)
            is NavigationAction.SaveArchiveAndBack -> {
                archives.add(action.archive)
                ArchiveListScreen(archives)
            }
            is NavigationAction.SaveNoteAndBack -> {
                archives[currentArchiveIndex].notes.add(action.note)
                NoteListScreen(archives[currentArchiveIndex].notes)
            }
        }
        currentScreen.start(::navigateTo)
    }
    currentScreen.start(::navigateTo)
}

private fun getTestData() : MutableList<Archive> {
    val archives: MutableList<Archive> = mutableListOf()
    val notes: MutableList<Note> = mutableListOf()
    notes.add(Note("Заметка 1", "На работе платят бабло"))
    notes.add(Note("Заметка 2", "Но работать надо на ней"))
    notes.add(Note("Заметка 3", "Я не против первого но"))
    notes.add(Note("Заметка 4", "Без второго мне веселей"))
    archives.add(Archive("Работа", notes))
    return archives
}