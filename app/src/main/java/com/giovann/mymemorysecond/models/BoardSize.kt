package com.giovann.mymemorysecond.models

enum class BoardSize (val size: Int) {
    EASY(8),
    MEDIUM(18),
    HARD(24);

    companion object {
        fun getByValue(value: Int) = values().first { it.size == value }
    }

    fun getWidth(): Int {
        return when (this) {
            EASY -> 2
            MEDIUM -> 3
            HARD -> 4
        }
    }

    fun getHeight(): Int {
        return size / getWidth()
    }

    fun getNumPairs(): Int {
        return size / 2
    }
}