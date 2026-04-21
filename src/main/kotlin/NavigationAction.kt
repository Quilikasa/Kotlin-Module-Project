import data.Archive
import data.Note

sealed class NavigationAction {
    object Back : NavigationAction()
    object Exit : NavigationAction()
    data class OpenArchive(val archiveIndex: Int) : NavigationAction()
    object CreateArchive : NavigationAction()
    data class OpenNote(val note: Note) : NavigationAction()
    object CreateNote : NavigationAction()
    data class SaveNoteAndBack(val note: Note) : NavigationAction()
    data class SaveArchiveAndBack(val archive: Archive) : NavigationAction()
}