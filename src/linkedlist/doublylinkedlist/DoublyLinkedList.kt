package linkedlist.doublylinkedlist

class DoublyLinkedList<T: Any> {
    private var head: Node<T>? = null
    private var tail: Node<T>? = null
    private var size = 0

    val first : Node<T>?
        get() = nodeAt(0)

    fun isEmpty(): Boolean {
        return size == 0
    }

    fun push(value: T): DoublyLinkedList<T> {
        val newNode = Node(value = value)

        if (head != null) {
            newNode.next = head
            head?.prev = newNode
        }
        head = newNode
        if (head?.next == null) {
            tail = head
        }
        size += 1

        return this
    }

    fun append(value: T): DoublyLinkedList<T> {
        if (isEmpty()) {
            push(value)
            return this
        }
        tail?.next = Node(value = value, prev = tail, next = null)
        tail = tail?.next
        size += 1
        return this
    }

    fun nodeAt(index: Int): Node<T>? {
        return if (index < size / 2) {
            var node = head
            for (i in 0 until index) {
                node = node?.next
            }
            node
        } else {
            var node = tail
            for (i in size - 1 downTo index + 1) {
                node = node?.prev
            }
            node
        }
    }

    fun insert(value: T, afterNode: Node<T>): Node<T> {
        if (tail == afterNode) {
            append(value)
            return tail!!
        }
        val newNode = Node(value = value, prev = afterNode, next = afterNode.next)
        afterNode.next?.prev = newNode
        afterNode.next = newNode
        size += 1
        return newNode
    }

    fun pop(): T? {
        if (!isEmpty()) size -= 1
        val result = head?.value
        head?.next?.prev = null
        head = head?.next

        if (isEmpty()) tail = null

        return result
    }

    fun removeLast(): T? {
        val head = head ?: return null
        if (head.next == null) return pop()

        val removeNode = tail
        tail = null
        tail = removeNode?.prev
        tail?.next = null
        size -= 1

        return removeNode?.value
    }

    fun remove(index: Int): T? {
        if (isEmpty()) return null

        when (index) {
            0 -> return pop()
            size - 1 -> return removeLast()
            else -> {
                val removeNode = nodeAt(index)
                val prevNode = removeNode?.prev
                val nextNode = removeNode?.next

                prevNode?.next = nextNode
                nextNode?.prev = prevNode

                size -= 1

                return removeNode?.value
            }
        }
    }

    fun removeAfter(node: Node<T>): T? {
        val result = node.next?.value

        if (node.next == tail) tail = node
        if (node.next != null) size -= 1

        node.next = node.next?.next
        node.next?.prev = node
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