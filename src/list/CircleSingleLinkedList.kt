package list

import trueLet

/**
 * @Author CoderPig
 * @Desc: 循环单链表的代码实现
 * @CreateDate: Create in 2020/3/28 11:19
 */
class CircleSingleLinkedList {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val list = CircleSingleLinkedList()
            // 头插法插入5个元素
            for (i in 0..4) list.headInsertCreateList(Node((0..100).random()))
            print("头插法插入元素后的列表（当前表长：${list.length}）")
            list.traverseList { print("${it?.value} → ") }

            // 尾插法插入5个元素
            for (i in 0..4) list.tailInsertCreateList(Node((0..100).random()))
            print("\n尾插法插入元素后的列表（当前表长：${list.length}）")
            list.traverseList { print("${it?.value} → ") }

            println("\n获得第4个结点的值：${list.getElement(4)?.value}")
            println("查找第一个大于50的结点：${list.locateElement { it?.value!! > 50 }?.value}")

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

    var headNode: Node? = null  // 尾指针
    var tailNode: Node? = null  // 尾指针
    var length = 0              // 表长度

    /* 构造空表（头结点自己指向自己）*/
    private fun initList() {
        this.headNode = Node()
        this.headNode!!.nextNode = headNode  // 头结点指向自己
        this.tailNode = this.headNode        // 尾指针指向头结点
        this.length = 0                      // 表长为1
    }

    /* 表置空 */
    private fun clearList() {
        var head = this.headNode?.nextNode  // 指向第一个结点
        while (head?.nextNode != head) {
            var temp = head
            head = head?.nextNode
            temp = null
        }
        this.headNode?.nextNode = this.headNode // 头结点指向自己
        this.tailNode = this.headNode        // 尾指针指向头结点
        this.length = 0                      // 表长为1
    }

    /* 销毁表（头结点置空） */
    fun destroyList() {
        var head = this.headNode?.nextNode  // 指向第一个结点
        while (head?.nextNode != head) {
            var temp = head
            head = head?.nextNode
            temp = null
        }
        this.headNode = null  // 头结点置空
        this.tailNode = null  // 尾指针置空
        this.length = 0                      // 表长为1
    }

    /* 头插法建表(先插结点排前面) */
    fun headInsertCreateList(node: Node) {
        (this.headNode == null) trueLet { initList() }
        var head = this.headNode     // 指向头结点的头指针
        var temp: Node?              // 临时存储引用
        (head!!.nextNode == head)    // 自己指自己，说明只有一个头结点
                .trueLet {
                    head!!.nextNode = node
                    tailNode = node     // 头插法第一个插入的结点是尾结点
                }
                .elseLet {
                    temp = head!!.nextNode
                    node.nextNode = temp
                    head!!.nextNode = node
                }
        ++length
        head = null
    }

    /* 尾插法建表(先插结点排后面)  */
    fun tailInsertCreateList(node: Node) {
        if (this.headNode == null) initList()
        (this.headNode!!.nextNode == headNode)  // 自己指自己，说明只有一个头结点
                .trueLet {
                    node.nextNode = this@CircleSingleLinkedList.headNode
                    this@CircleSingleLinkedList.headNode?.nextNode = node
                    tailNode = node
                }
                .elseLet {
                    tailNode?.nextNode = node
                    node.nextNode = this@CircleSingleLinkedList.headNode!!.nextNode
                    tailNode = node
                }
        ++length
    }

    /* 遍历表 */
    fun traverseList(visit: (Node?) -> Unit) {
        if (this.headNode == null) initList()
        var head = this.headNode
        do {
            head = head!!.nextNode
            visit(head)
        } while (head !== tailNode)
    }

    /* 获得第pos个元素，从0开始算 */
    fun getElement(pos: Int): Node? {
        var node = this.headNode?.nextNode
        // 判断是否越界
        (pos < 0 || pos > this.length - 1)
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
        for (i in 0 until this.length) {
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
        (pos < 0 || pos > this.length - 1)
                .trueLet { throw IndexOutOfBoundsException("Index: $pos") }
                .elseLet {
                    // 指向插入位置
                    for (i in 0 until pos) head = head?.nextNode
                    // 如果插入位置在表尾
                    (pos == this@CircleSingleLinkedList.length - 1)
                            .trueLet {
                                tailNode?.nextNode = node
                                node.nextNode = headNode
                                tailNode = node
                            }
                            .elseLet {
                                node.nextNode = head?.nextNode
                                head?.nextNode = node
                            }
                    ++length
                }
        return true
    }

    /* 删除第pos个位置的元素 */
    fun removeElement(pos: Int) {
        var head = this.headNode    // 指向头结点
        // 判断删除位置是否合法
        (pos < 0 || pos > length - 1)
                .trueLet { throw IndexOutOfBoundsException("Index: $pos") }
                .elseLet {
                    // 指向删除节点的前一个结点
                    for (i in 0 until pos) head = head?.nextNode
                    // 如果删除位置在表尾
                    (pos == this@CircleSingleLinkedList.length - 1)
                            .trueLet {
                                val temp = head         // 待删除结点的上一个结点
                                temp?.nextNode = this@CircleSingleLinkedList.headNode   // 指向头结点
                                tailNode = temp
                            }
                            .elseLet {
                                val temp = head         // 待删除结点的上一个结点
                                head = head?.nextNode   // 要删除的结点
                                temp?.nextNode = head?.nextNode
                            }
                    --length
                }
    }


    data class Node(
            var value: Int? = null,
            var nextNode: Node? = null
    )
}