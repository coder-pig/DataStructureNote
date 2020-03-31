package list

/**
 * @Author CoderPig
 * @Desc: 顺序表的代码实现
 * @CreateDate: Create in 2020/3/28 11:19
 */

class SeqList {
    companion object {
        const val DEFAULT_SIZE = 10             // 表的默认长度

        @JvmStatic
        fun main(args: Array<String>) {
            val list = SeqList()
            println("列表为空：${list.listEmpty()}")
            // 插入10个元素
            for (i in 0..25) list.insertElement(Node(i), i)
            print("当前列表：")
            list.traverseList { print("${it?.value} → ") }
            // 在下标为6处插入元素
            list.insertElement(Node(100), 6)
            print("\n插入后的列表：")
            list.traverseList { print("${it?.value} ") }
            // 删除下标为5处的元素
            val oldValue = list.removeElement(5)
            print("\n删除元素：$oldValue\n移除元素后的列表：")
            list.traverseList { print("${it?.value} ") }
            // 查找值为15的元素的直接前驱
            println("\n值为15的元素的直接前驱：${list.priorElement(Node(15))}")
            // 查找值为15的元素的直接后继
            println("值为15的元素的直接后继：${list.nextElement(Node(15))}")
            // 查找值为23的元素下标
            println("查找值为23的元素下标：${list.locateElement { it?.value == 23 }}")
        }
    }

    private var length: Int = 0                 // 表的当前长度
    private var listSize: Int = 0               // 表的总存储容量
    private lateinit var elements: Array<Node?> // 表元素

    init {
        initList(DEFAULT_SIZE)
    }

    /* 初始化表 */
    fun initList(max: Int) {
        this.elements = Array(max) { null }
        this.length = 0
        this.listSize = max
    }

    /* 初始化表 */
    fun clearList() {
        for (i in 0 until this.length) {
            this.elements[i] = null
        }
    }

    /* 判断是否为空表 */
    fun listEmpty() = this.length == 0

    /* 判断表是否满 */
    private fun listFull() = this.length == this.listSize

    /* 遍历表 */
    fun traverseList(visit: (Node?) -> Unit) {
        for (i in 0 until this.length) {
            visit(this.elements[i])
        }
    }

    /* 销毁表 */
    fun destroyList() {
        this.elements = emptyArray()
        this.length = 0
        this.listSize = 0
    }

    /* 获取表长 */
    fun listLength() = this.length

    /* 获得容量 */
    fun listSize() = this.listSize

    /* 根据下标获取结点 */
    fun getElement(index: Int): Node? {
        if (index < 0 || index > this.length)
            throw IndexOutOfBoundsException("Index: $index")
        return this.elements[index]
    }

    /* 根据条件定位元素下标 */
    fun locateElement(compare: (Node?) -> Boolean): Int {
        for (i in 0 until this.length) {
            if (compare(this.elements[i])) return i
        }
        return -1
    }

    /* 返回结点直接前驱 */
    fun priorElement(Node: Node): Node? {
        for (i in 0 until this.length) {
            if (this.elements[i] == Node && i > 1) return this.elements[i - 1]
        }
        return null
    }

    /* 返回结点直接后继 */
    fun nextElement(Node: Node): Node? {
        for (i in 0 until this.length) {
            if (this.elements[i] == Node && i < this.length - 1) return this.elements[i + 1]
        }
        return null
    }

    /* 插入元素 */
    fun insertElement(Node: Node, pos: Int): Boolean {
        // ① 判断插入位置合法性
        if (pos < 0 || pos > this.length) throw IndexOutOfBoundsException("Index: $pos")
        // ② 判断表是否初始化
        if (this.listSize == 0) initList(DEFAULT_SIZE)
        // ③ 判断是否需要扩容
        if (listFull()) {
            this.listSize = 2 * this.listSize
            this.elements = this.elements.copyOf(this.listSize)
        }
        // ④ 从后往前移动元素，直到下标等于pos
        for (i in this.length - 1 downTo pos) {
            this.elements[i + 1] = this.elements[i]
        }
        // ⑤ 设置插入元素
        this.elements[pos] = Node
        // ⑥ 长度+1
        this.length++
        return true
    }

    /* 删除元素 */
    fun removeElement(pos: Int): Node? {
        // ① 判断删除位置合法性
        if (pos < 0 || pos > this.length - 1) throw IndexOutOfBoundsException("Index: $pos")
        // ② 保存删除元素
        val oldValue = this.elements[pos]
        // ③ 从pos处从后往前移动元素，直到下标等于长度-1
        for (i in pos until this.length - 1) {
            this.elements[i] = this.elements[i + 1]
        }
        this.length--
        return oldValue
    }

    /* 结点元素 */
    data class Node(
            var value: Int
    )
}