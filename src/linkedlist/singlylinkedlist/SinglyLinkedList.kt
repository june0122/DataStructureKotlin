package linkedlist.singlylinkedlist

class SinglyLinkedList<T> {
    private var head: Node<T>? = null
    private var tail: Node<T>? = null
    private var size = 0

    fun isEmpty(): Boolean {
        return size == 0
    }

    fun push(value: T): SinglyLinkedList<T> {
        head = Node(value = value, next = head)
        if (tail == null) {
            tail = head
        }
        size += 1
        return this
    }

    fun append(value: T): SinglyLinkedList<T> {
        if (isEmpty()) {
            push(value)
            return this
        }
        tail?.next = Node(value = value)
        tail = tail?.next
        size += 1
        return this
    }

    fun nodeAt(index: Int): Node<T>? {
        var currentNode = head
        var currentIndex = 0

        while (currentNode != null && currentIndex < index) {
            currentNode = currentNode.next
            currentIndex += 1
        }

        return currentNode
    }

    fun insert(value: T, afterNode: Node<T>): Node<T> {
        if (tail == afterNode) {
            append(value)
            return tail!!
        }
        val newNode = Node(value = value, next = afterNode.next)
        afterNode.next = newNode
        size += 1
        return newNode
    }

    fun pop(): T? {
        if (!isEmpty()) size -= 1
        val result = head?.value
        head = head?.next

        if (isEmpty()) tail = null

        return result
    }

    fun removeLast(): T? {
        val head = head ?: return null
        if (head.next == null) return pop()
        size -= 1

        var prev = head
        var current = head

        var next = current.next
        while (next != null) {
            prev = current
            current = next
            next = current.next
        }
        prev.next = null
        tail = prev
        return current.value
    }

    fun removeAfter(node: Node<T>): T? {
        val result = node.next?.value

        if (node.next == tail) tail = node
        if (node.next != null) size -= 1

        node.next = node.next?.next
        return result
    }

    override fun toString(): String {
        return if (isEmpty()) {
            "Empty list"
        } else {
            head.toString()
        }
    }
}