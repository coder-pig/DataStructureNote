package stack

/**
 * @Author CoderPig
 * @Desc: 顺序表的代码实现
 * @CreateDate: Create in 2020/4/2 11:19
 */
class SeqStack {
    companion object {
        const val DEFAULT_SIZE = 10             // 栈的默认容量
        const val INCREASE_SIZE = 10            // 栈容量不足每次增长的长度

        @JvmStatic
        fun main(args: Array<String>) {
            val stack = SeqStack()
            for (i in 1..11) stack.push(Node(i))
            print("批量入栈后：")
            stack.traverseStack { print("${it?.value} → ") }
            for (i in 1..5) println("当前出栈元素：${stack.pop()?.value}")
            print("批量出栈后：")
            stack.traverseStack { print("${it?.value} → ") }
        }
    }

    private var size = 0     // 栈中元素个数
    private var capacity = 0 // 栈的实际容量
    private lateinit var elements: Array<Node?> // 栈列表

    init {
        initStack()
    }

    /* 构造空栈 */
    fun initStack(size: Int = DEFAULT_SIZE) {
        capacity = size
        elements = Array(size) { null }
    }

    /* 入栈 */
    fun push(node: Node) {
        // 栈容量满，扩容
        if (size + 1 > capacity) {
            capacity += INCREASE_SIZE
            elements = elements.copyOf(capacity)
            println("超出容量，自动扩容，扩容后容量为：$capacity")
        }
        elements[size] = node
        size++
    }

    /* 出栈 */
    fun pop(): Node? {
        if (size == 0) return null  // 栈空
        val oldValue = elements[size - 1]
        elements[size - 1] = null   // 元素置空
        size--
        return oldValue
    }

    /* 清空栈 */
    fun clearStack() {
        for (i in 0 until size) elements[i] = null
        size = 0
    }

    /* 遍历栈 */
    fun traverseStack(visit: (Node?) -> Unit) {
        for (i in 0 until size) visit(elements[i])
    }

    /* 获取元素个数 */
    fun getSize() = size

    data class Node(
            var value: Int? = null
    )
}