package list

import trueLet

/**
 * @Author CoderPig
 * @Desc: 单链表的代码实现
 * @CreateDate: Create in 2020/3/28 11:19
 */

class SingleLinkedList {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val list = SingleLinkedList()
            // 头插法插入5个元素
            for (i in 0..4) list.headInsertCreateList(Node((0..100).random()))
            print("头插法插入元素后的列表（当前表长：${list.length()}）")
            list.traverseList { print("${it?.value} → ") }

            // 尾插法插入5个元素
            for (i in 0..4) list.tailInsertCreateList(Node((0..100).random()))
            print("\n尾插法插入元素后的列表（当前表长：${list.length()}）")
            list.traverseList { print("${it?.value} → ") }

            println("\n获得第4个结点的值：${list.getElement(4)?.value}")
            println("查找第一个大于50的结点：${list.locateElement { (it?.value!! as Int) > 50 }?.value}")

            print("在第6个节点处插入结点：\n插入后的列表：")
            list.insertElement(Node(666), 6)
            list.traverseList { print("${it?.value} → ") }

            print("\n删除第8个结点：\n删除后的列表：")
            list.removeElement(8)
            list.traverseList { print("${it?.value} → ") }
        }
    }

    init {
        initList()
    }

    var headNode: Node? = null  // 头结点

    /* 构造空表（生成一个无数据的头结点）*/
    private fun initList() {
        this.headNode = Node()
    }

    /* 表置空(头结点指针域置空) */
    private fun clearList() {
        var head = this.headNode?.nextNode
        this.headNode?.nextNode = null
        while (head?.nextNode != null) {
            head = head.nextNode
        }
    }

    /* 销毁表（头结点置空） */
    fun destroyList() {
        var head = this.headNode?.nextNode
        this.headNode = null
        while (head?.nextNode != null) {
            var temp = head
            head = head.nextNode
            temp = null
        }
    }

    /* 头插法建表(先插结点排前面) */
    fun headInsertCreateList(node: Node) {
        (this.headNode == null) trueLet { initList() }
        var head = this.headNode     // 指向头结点的头指针
        var temp: Node?         // 临时存储引用
        (head!!.nextNode == null)
                .trueLet { head!!.nextNode = node }
                .elseLet {
                    temp = head!!.nextNode
                    node.nextNode = temp
                    head!!.nextNode = node
                }
        head = null
    }

    /* 查找尾结点 */
    private fun searchTailNode(): Node {
        var head = this.headNode
        while (head!!.nextNode != null) {
            head = head.nextNode
        }
        return head
    }

    /* 尾插法建表(先插结点排后面)  */
    fun tailInsertCreateList(node: Node) {
        if (this.headNode == null) initList()
        // 链表为空直接插入
        (this.headNode!!.nextNode == null)
                .trueLet { this@SingleLinkedList.headNode?.nextNode = node }
                .elseLet {
                    val tailNode = searchTailNode()     // 查找尾结点
                    tailNode.nextNode = node
                }
    }

    /* 遍历表 */
    fun traverseList(visit: (Node?) -> Unit) {
        if (this.headNode == null) initList()
        var head = this.headNode
        do {
            head = head!!.nextNode
            visit(head)
        } while (head?.nextNode != null)
        head = null
    }

    /* 判断表是否为空 */
    fun isEmpty(): Boolean = this.headNode?.nextNode == null

    /* 获得表长度 */
    fun length(): Int {
        var head = this.headNode
        var count = 0
        while (head?.nextNode != null) {
            ++count
            head = head.nextNode
        }
        return count
    }

    /* 获得第pos个元素，从0开始算 */
    fun getElement(pos: Int): Node? {
        var node = this.headNode?.nextNode
        // 判断是否越界
        (pos < 0 || pos > length() - 1)
                .trueLet { throw IndexOutOfBoundsException("Index: $pos") }
                .elseLet {
                    for (i in 0 until pos) {
                        node = node?.nextNode
                    }
                }
        return node
    }

    /* 查找满足条件的某个元素 */
    fun locateElement(compare: (Node?) -> Boolean): Node? {
        if (this.headNode == null) initList()
        var head = this.headNode?.nextNode
        for (i in 0 until length()) {
            if (compare(head)) {
                return head
            } else {
                head = head?.nextNode
            }
        }
        head = null
        return null
    }

    /* 在第pos个位置插入元素 */
    fun insertElement(node: Node, pos: Int): Boolean {
        var head = this.headNode?.nextNode
        // 判断插入位置是否合法
        (pos < 0 || pos > length() - 1)
                .trueLet { throw IndexOutOfBoundsException("Index: $pos") }
                .elseLet {
                    // 指向插入位置
                    for (i in 0 until pos) head = head?.nextNode
                    node.nextNode = head?.nextNode
                    head?.nextNode = node
                }
        return true
    }

    /* 删除第pos个位置的元素 */
    fun removeElement(pos: Int) {
        var head = this.headNode?.nextNode
        // 判断插入位置是否合法
        (pos < 0 || pos > length() - 1)
                .trueLet { throw IndexOutOfBoundsException("Index: $pos") }
                .elseLet {
                    // 指向删除结点
                    for (i in 0 until pos) head = head?.nextNode
                    // 临时存储删除节点下一个结点的引用
                    val temp = head?.nextNode
                    // 把删除节点的后继丢给删除节点的前驱
                    head?.nextNode = temp?.nextNode
                }
    }

    data class Node(
            var value: Any? = null,
            var nextNode: Node? = null
    )

}