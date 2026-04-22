package screens

import NavigationAction
import data.Archive

/**
 * Экран создания нового архива
 * Архив создается пустой, без заметок
 *
 * Отображает название экрана
 * Отрисовывает меню
 * Обрабатывает пользовательский ввод
 *
 * Умеет обрабатывать 2 действия и выбрасывает колбэк с соответствующим NavigationAction:
 * 1) создание нового архива NavigationAction.SaveArchiveAndBack
 * 2) выход NavigationAction.Back
 *
 */
class ArchiveCreateScreen(): CreateScreen() {

    override val screenTitle = "Для создания нового архива введите его название"

    override fun getActionForInput(input: String): NavigationAction? {
        val newArchive = Archive(input)
        return NavigationAction.SaveArchiveAndBack(newArchive)
    }
}