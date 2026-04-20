import java.util.Scanner

/**
 * Экран списка архивов
 *
 * Отображает название экрана
 * Принимает на вход список архивов
 * Отрисовывает список архивов и меню
 * Обрабатывает пользовательский ввод (на вход так же принимает сканер)
 *
 * Умеет обрабатывать 3 действия и выбрасывает соответствующие колбэки:
 * 1) выбор архива onShowElement()
 * 2) создание нового архива onCreateElement()
 * 3) выход с экрана onExit()
 *
 */
class ArchiveListScreen(val scanner: Scanner, val archives: MutableList<Archive>): Screen() {

    override fun start(onNavigate: (NavigationAction) -> Unit) {
        showMenu()
        readUserInput(onNavigate)
    }

    private fun showMenu() {
        println("Список архивов:")
        println("0. Создать архив")
        showArchiveList()
        println("${archives.size+1}. Выход")
    }

    private fun showArchiveList() {
        for (i in archives.indices) {
            println("${i+1}. ${archives[i].name}")
        }
    }

    private fun readUserInput(onNavigate: (NavigationAction) -> Unit) {
        while(true) {
            val input = scanner.nextLine().trim()
            when(input) {
                "0" -> {
                    onNavigate(NavigationAction.CreateArchive)
                    break
                }
                "${archives.size+1}" -> {
                    onNavigate(NavigationAction.Exit)
                    break
                }
                else -> {
                    onNavigate(NavigationAction.OpenArchive(input))
                    break
                    //обработать негативные сценарии
                }
            }
        }
    }
}