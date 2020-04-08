package queue

/**
 * @Author CoderPig
 * @Desc: 循环队列的代码实现
 * @CreateDate: Create in 2020/4/8 10:51
 */
class CircleQueue {
    companion object {
        const val DEFAULT_SIZE = 5             // 队列的默认长度

        @JvmStatic
        fun main(args: Array<String>) {
            val queue = CircleQueue()
            queue.initQueue()
            for (i in 1..5) {
                print("入列：")
                queue.enqueue(Node((1..100).random()))
                queue.traverse { print("${it?.value} → ") }
                println()
            }
            for (i in 1..2) {
                print("元素${queue.dequeue()?.value} 出列：")
                queue.traverse { print("${it?.value} → ") }
                println()
            }
            for (i in 1..2) {
                print("入列：")
                queue.enqueue(Node((1..100).random()))
                queue.traverse { print("${it?.value} → ") }
                println()
            }
        }
    }

    private var length: Int = 0                 // 队列的当前长度
    private var queueSize: Int = 0              // 队列的总存储容量
    private lateinit var elements: Array<Node?> // 队列元素
    private var front = 0                       // 队头下标
    private var rear = 0                        // 队尾下标

    /* 初始化队列 */
    fun initQueue(max: Int = DEFAULT_SIZE) {
        elements = Array(max) { null }
        length = 0
        queueSize = max
    }

    /* 入队列 */
    fun enqueue(node: Node): Boolean {
        if (length < queueSize) {
            elements[rear] = node
            rear = (rear + 1) % queueSize
            length++
            return true
        }
        return false
    }

    /* 出队列 */
    fun dequeue(): Node? {
        if (length == 0) return null
        val temp = elements[front]
        elements[front] = null
        front = (front + 1) % queueSize
        length--
        return temp
    }

    /* 遍历队列 */
    fun traverse(visit: (Node?) -> Unit) {
        for (i in 0 until queueSize) {
            visit(this.elements[i])
        }
    }

    /* 销毁队列 */
    fun destroy() {
        elements = emptyArray()
        length = 0
        queueSize = 0
        front = 0
        rear = 0
    }


    /* 结点元素 */
    data class Node(
            var value: Any?
    )
}