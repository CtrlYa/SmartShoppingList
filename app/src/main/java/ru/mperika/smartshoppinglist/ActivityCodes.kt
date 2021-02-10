package ru.mperika.smartshoppinglist

enum class ActivityCodes(val id: Int, val codeName: String) {
    CREATE(1, "Создать"),
    EDIT(2, "Редактировать");

    fun getById(id: Int): ActivityCodes {
        return ActivityCodes.values().filter { it.id == id }.get(0)
    }
}