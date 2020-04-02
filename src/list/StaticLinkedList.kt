package list

/**
 * @Author CoderPig
 * @Desc: 静态链表的代码实现
 * @CreateDate: Create in 2020/4/1 10:27
 */
class StaticLinkedList {
    companion object {
        const val MAXSIZE = 10             // 表的最大长度
        @JvmStatic
        fun main(args: Array<String>) {
            val list = StaticLinkedList()
            list.insertElement("A", 1)
            list.insertElement("B", 2)
            list.insertElement("C", 3)
            list.insertElement("D",4)
            list.insertElement("E",5)
            println("\n插入元素后 ")
            for (i in 0 until list.listSize) {
                println("$i → ${list.elements[i]!!.cursor} → ${list.elements[i]!!.value} ")
            }
            list.removeElement(3)
            list.removeElement(2)
            list.removeElement(1)
            println("\n删除元素后 ")
            for (i in 0 until list.listSize) {
                println("$i → ${list.elements[i]!!.cursor} → ${list.elements[i]!!.value} ")
            }
        }
    }

    private lateinit var elements: Array<Node?>  // 元素列表
    private var length = 0      // 有效结点个数
    private var listSize = 0    // 表的总存储容量

    init {
        initList()
    }

    /* 初始化表 */
    private fun initList() {
        this.elements = Array(MAXSIZE) { null }
        // 每个游标指向下一个下标，如(1→2)
        for (i in 0 until MAXSIZE) {
            this.elements[i] = Node(null, i + 1)
        }
        this.elements[0]?.cursor = 1
        this.elements[MAXSIZE - 1]?.cursor = 0   // 尾元游标设置为0
        this.length = 0 // 重置有效结点个数
        this.listSize = MAXSIZE
    }

    /* 获取备用链表结点下标 */
    private fun getEmpty(): Int {
        val i = this.elements[0]!!.cursor // 首元存放的游标指向第一个空白结点下标
        if (i != 0) {
            this.elements[0]?.cursor = this.elements[i!!]!!.cursor   // 指向空白结点的下一个结点
        }
        return i
    }

    /* 在第pos个位置插入元素 */
    fun insertElement(value: String, pos: Int): Boolean {
        // ① 判断插入位置合法性
        if (pos < 1 || pos > this.length + 1) throw IndexOutOfBoundsException("Index: $pos")
        // ② 获取首个可用结点、空白结点
        var firstUse = this.elements[this.listSize - 1]!!.cursor!!
        val firstEmpty = this.elements[0]!!.cursor!!
        // ④ 下一个空白结点
        val nextEmpty = this.elements[firstEmpty]!!.cursor
        // ⑤ 对插入位置为1的情况进行处理(区分表是否为空)
        if (pos == 1) {
            when (this.length) {
                1 -> {
                    val temp = this.elements[MAXSIZE - 1]!!.cursor!!  // 获取当前第一个结点的游标
                    this.elements[temp]!!.cursor = 0                  // 只有一个元素时，这个结点游标需设置为0
                    this.elements[MAXSIZE - 1]!!.cursor = firstEmpty
                    this.elements[0]!!.cursor = nextEmpty
                    this.elements[firstEmpty]!!.cursor = temp         // 空白结点指向上一个临时结点的游标
                    this.elements[firstEmpty]!!.value = value
                }
                else -> {
                    this.elements[MAXSIZE - 1]!!.cursor = firstEmpty    // 尾元指向新空白结点
                    this.elements[0]!!.cursor = nextEmpty               // 首元指向新的空白结点
                    this.elements[firstEmpty]!!.cursor = firstUse       // 空白结点指向上个结点
                    this.elements[firstEmpty]!!.value = value           // 空白结点赋值
                }
            }
        } else {
            // 移动到插入位置
            for (i in 1 until pos - 1) firstUse = this.elements[firstUse]!!.cursor!!
            val temp = elements[firstUse]!!.cursor         // 临时存储后一个结点的游标
            this.elements[0]!!.cursor = nextEmpty
            this.elements[firstUse]!!.cursor = firstEmpty
            this.elements[firstEmpty]!!.cursor = temp
            this.elements[firstEmpty]!!.value = value
        }
        this.length++
        return true
    }

    /* 移除pos个位置的元素 */
    fun removeElement(pos: Int): Node? {
        // ① 判断删除位置合法性
        if (pos < 1 || pos > this.length + 1) throw IndexOutOfBoundsException("Index: $pos")
        // ② 获取首个可用结点
        var firstUse = this.elements[this.listSize - 1]!!.cursor!!
        // ③ 对删除位置为1的情况进行处理(区分表是否为空)
        if (pos == 1) {
            when (this.length) {
                1 -> {
                    this.elements[this.listSize - 1]!!.cursor = 0
                    // 释放结点
                    this.elements[firstUse]!!.cursor = pos + 1
                    this.elements[firstUse]!!.value = null
                    this.elements[0]!!.cursor = firstUse
                }
                else -> {
                    val temp = this.elements[firstUse]!!.cursor
                    this.elements[this.listSize - 1]!!.cursor = temp
                    // 释放结点
                    this.elements[firstUse]!!.cursor = pos + 1
                    this.elements[firstUse]!!.value = null
                    this.elements[0]!!.cursor = firstUse
                }
            }
        } else {
            for (i in 1 until pos - 1) {
                firstUse = this.elements[firstUse]!!.cursor!!
            }
            val deleteUse = this.elements[firstUse]!!.cursor!!  // 删除游标
            val nextUse = this.elements[deleteUse]!!.cursor     // 删除游标的后一项
            this.elements[firstUse]!!.cursor = nextUse          // 删除前一项指向删除后一项
            this.elements[deleteUse]!!.value = null
            this.elements[0]!!.cursor = deleteUse               // 回收结点
        }
        this.length--
        return null
    }

    data class Node(
            var value: String? = null,
            var cursor: Int? = null
    )
}