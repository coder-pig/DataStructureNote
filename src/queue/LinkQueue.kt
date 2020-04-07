package queue

/**
 * @Author CoderPig
 * @Desc: 链队列的代码实现
 * @CreateDate: Create in 2020/4/7 11:49
 */
class LinkQueue {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val queue = LinkQueue()
            for (i in 1..11) queue.insertQueue(Node("$i"))
            print("批量入队列后：")
            queue.traverseQueue { print("${it?.value} → ") }
            println()
            for (i in 1..5) println("当前出队列元素：${queue.deleteQueue()?.value}")
            print("批量出队列后：")
            queue.traverseQueue { print("${it?.value} → ") }
        }
    }

    var frontNode: Node? = null  // 队头指针
    var rearNode: Node? = null  // 队尾指针
    private var size: Int = 0          // 栈中元素个数

    init {
        initQueue()
    }

    /* 初始化(队头和队尾指针都指向头结点) */
    fun initQueue() {
        frontNode = Node()
        rearNode = frontNode
        size = 0
    }

    /* 入队列 */
    fun insertQueue(node: Node) {
        rearNode?.nextNode = node
        node.nextNode = null
        rearNode = node
        size++
    }

    /* 出队列 */
    fun deleteQueue(): Node? {
        if(size < 0) return null
        val temp = frontNode?.nextNode  // 指向第一个结点
        frontNode = frontNode?.nextNode
        size--
        return temp
    }

    /* 遍历队列 */
    fun traverseQueue(visit: (Node?) -> Unit) {
        var temp = frontNode?.nextNode
        for(i in 0 until size) {
            visit(temp)
            temp = temp?.nextNode
        }
    }

    /* 清空队列 */
    fun clearQueue(node: Node) {
        frontNode?.nextNode = null
        rearNode = frontNode
        size = 0
    }

    fun isEmpty() = size == 0

    data class Node(
            var value: Any? = null,
            var nextNode: Node? = null
    )

}