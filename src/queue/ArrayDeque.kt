package queue

/**
 * @Author CoderPig
 * @Desc: 基于数组的双端队列
 * @CreateDate: Create in 2020/4/11 15:57
 */
class ArrayDeque : IDeque<ArrayDeque.Node?> {
    companion object {
        const val MIN_INITIAL_CAPACITY = 8             // 队列的默认长度

        @JvmStatic
        fun main(args: Array<String>) {
            val deque = ArrayDeque()
            for (i in 1..5) deque.addFirst(Node(i))
            print("队头入列5个元素，当前队列：")
            deque.foreachFirst { print("${if (it == null) it else it.value} → ") }
            deque.removeFirst()
            deque.removeFirst()
            print("\n队头出列2个元素，当前队列：")
            deque.foreachFirst { print("${if (it == null) it else it.value} → ") }
            for (i in 10..15) deque.addLast(Node(i))
            print("\n队尾入列5个元素，当前队列：")
            deque.foreachFirst { print("${if (it == null) it else it.value} → ") }
            deque.removeLast()
            deque.removeLast()
            deque.removeLast()
            print("\n队尾出列3个元素，当前队列：")
            deque.foreachFirst { print("${if (it == null) it else it.value} → ") }
            println("\n当前队头：${deque.peekFirst()}")
            println("当前队尾：${deque.peekLast()}")
        }
    }

    private var length: Int = 0                 // 队列的当前长度
    private var queueSize: Int = 0              // 队列的总存储容量
    private var elements: Array<Node?>          // 队列元素
    private var front = 0                       // 队头下标
    private var rear = 0                        // 队尾下标

    init {
        elements = Array(MIN_INITIAL_CAPACITY) { null }
        length = 0
        queueSize = MIN_INITIAL_CAPACITY
    }

    /* 扩容 */
    fun doubleCapacity() {
        val length = this.elements.size
        val front = this.front
        val behind = length - front
        val newCapacity = length.shl(1) // 左移一位
        val newElements = Array<Node?>(newCapacity) { null }
        System.arraycopy(elements, front, newElements, 0, behind)
        System.arraycopy(elements, 0, newElements, behind, front)
        this.elements = newElements
        this.queueSize = newCapacity
        this.front = 0
        this.rear = length
    }

    override fun addFirst(newEntry: Node?) {
        front = front - 1 and queueSize - 1
        elements[front] = newEntry
        length++
        if (front == rear)
            doubleCapacity()
    }

    override fun removeFirst(): Node? {
        val oldEntry = elements[front]
        elements[front] = null
        length--
        front = front + 1 and queueSize - 1
        return oldEntry
    }

    override fun peekFirst(): Node? {
        return elements[front]
    }

    override fun addLast(newEntry: Node?) {
        elements[rear] = newEntry
        length++
        rear += 1
        if (rear and queueSize - 1 == front)
            doubleCapacity()
    }

    override fun removeLast(): Node? {
        val temp = rear - 1 and queueSize - 1
        val oldEntry = elements[temp]
        elements[temp] = null
        length--
        rear = temp
        return oldEntry
    }

    override fun isEmpty(): Boolean {
        return length == 0
    }

    override fun peekLast(): Node? {
        return elements[rear - 1 and queueSize - 1]
    }

    override fun size(): Int {
        return length
    }

    override fun clear() {
        var temp = front
        do {
            elements[temp] = null
            temp = ++temp and (queueSize - 1)
        } while (temp != rear)
    }

    override fun foreachFirst(visit: (Node?) -> Unit) {
        var temp = front
        do {
            visit(elements[temp])
            temp = ++temp and (queueSize - 1)
        } while (temp != front)
    }

    override fun foreachLast(visit: (Node?) -> Unit) {
        var temp = rear
        do {
            visit(elements[temp])
            temp = ++temp and (queueSize - 1)
        } while (temp != rear)
    }

    /* 结点元素 */
    data class Node(
            var value: Any?
    )

}