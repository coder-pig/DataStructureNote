package stack

/**
 * @Author CoderPig
 * @Desc: 链栈的代码实现
 * @CreateDate: Create in 2020/4/2 14:53
 */
class LinkStack {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val stack = LinkStack()
            for (i in 1..11) stack.push(Node("$i"))
            print("批量入栈后：")
            stack.traverseStack { print("${it?.value} → ") }
            println()
            for (i in 1..5) println("当前出栈元素：${stack.pop()?.value}")
            print("批量出栈后：")
            stack.traverseStack { print("${it?.value} → ") }
        }
    }

    private var topNode: Node? = null  // 栈顶指针
    private var size: Int = 0          // 栈中元素个数

    init {
        initStack()
    }

    /* 初始化 */
    private fun initStack() {
        topNode = Node()
        size = 0
    }

    /* 入栈 */
    fun push(node: Node) {
        node.nextNode = topNode
        topNode = node
        size++
    }

    /* 出栈 */
    fun pop(): Node? {
        if(size < 0) return null
        val temp = topNode
        topNode = topNode!!.nextNode
        size--
        return temp
    }

    /* 清空栈 */
    fun clearStack() {
        topNode = null  // 直接将头指针置空即可
    }

    /* 遍历栈 */
    fun traverseStack(visit: (Node?) -> Unit) {
        var temp = topNode
        for(i in 0 until size) {
            visit(temp)
            temp = temp!!.nextNode
        }
    }

    /* 获取元素个数 */
    fun getSize() = size

    /* 获取栈顶元素的下一个元素 */
    fun peek(): Node? {
        return topNode
    }

    data class Node (
        var value: Any? = null,
        var nextNode: Node? = null
    )
}