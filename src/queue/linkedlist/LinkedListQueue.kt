package queue.linkedlist

import base.Queue
import linkedlist.doublylinkedlist.DoublyLinkedList

class LinkedListQueue<T : Any> : Queue<T> {
    private val list = DoublyLinkedList<T>()

    private var size = 0

    override val count: Int
        get() = size

    override fun enqueue(element: T): Boolean {
        list.append(element)
        size += 1
        return true
    }

    override fun dequeue(): T? = list.pop()

    override fun peek(): T? = list.first?.value

    override fun toString(): String = list.toString()
}