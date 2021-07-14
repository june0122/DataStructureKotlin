package stack

import base.Stack
import java.util.*

class StackImpl<T : Any> : Stack<T> {
    companion object {
        fun <T : Any> create(items: Iterable<T>): Stack<T> {
            val stack = StackImpl<T>()
            for (item in items) {
                stack.push(item)
            }
            return stack
        }
    }

    private val storage = arrayListOf<T>()

    override fun push(element: T) {
        storage.add(element)
    }

    override fun pop(): T? {
        if (isEmpty) {
            return null
        }
        return storage.removeAt(storage.size - 1)
    }

    override fun peek(): T? {
        return storage.lastOrNull()
    }

    override val count: Int
        get() = storage.size

    override fun toString() = buildString {
        appendLine("----top----")
        storage.asReversed().forEach {
            appendLine("$it")
        }
        appendLine("-----------")
    }
}

fun <T : Any> stackOf(vararg elements: T): Stack<T> {
    return StackImpl.create(elements.asList())
}

fun <T : Any> LinkedList<T>.printInReverse() {
    val stack = StackImpl<T>()

    for (node in this) {
        stack.push(node)
    }

    var node = stack.pop()
    while (node != null) {
        println(node)
        node = stack.pop()
    }
}

fun String.checkParentheses(): Boolean {
    val stack = StackImpl<Char>()

    for (char in this) {
        when (char) {
            '(' -> stack.push(char)
            ')' -> {
                if (stack.isEmpty) return false
                else stack.pop()
            }
        }
    }
    return stack.isEmpty
}