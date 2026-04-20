sealed class NavigationAction {
    object Back : NavigationAction()
    object Exit : NavigationAction()
    data class OpenArchive(val archiveName: String) : NavigationAction()
    object CreateArchive : NavigationAction()
    data class OpenNote(val noteName: String) : NavigationAction()
    object CreateNote : NavigationAction()
}