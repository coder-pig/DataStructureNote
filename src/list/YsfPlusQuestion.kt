package list

import trueLet

/**
 * @Author CoderPig
 * @Desc: 约瑟夫问题增强版
 * @CreateDate: Create in 2020/3/29 20:52
 */
class YsfPlusQuestion {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val question = YsfPlusQuestion()
            for (i in 1..10) question.tailInsertCreateList(Node(i, (1..5).random()))
            println("初始化后的列表：")
            question.traverseList { print("${it?.value} (${it?.key})→ ") }
            var head = question.headNode       // 指向头结点
            var temp: Node?   // 临时存储
            println("\n出局：")
            var curCount = head?.nextNode?.key  // 获取第一个key
            while (question.length > 1) {
                // 获取出局者的前一个结点
                for (i in 1 until curCount!!) {
                    head = head?.nextNode
                }
                print("${head?.nextNode?.value!!} ")
                temp = head.nextNode               // 指向删除结点
                curCount = temp?.key                // 获取删除节点保存的key
                head.nextNode = temp?.nextNode     // 前一个结点指向删除的后一个结点
                question.length--
            }
            println("\n剩下第${head?.nextNode?.value}是幸存者!")
        }
    }

    var headNode: Node? = null  // 尾指针
    var tailNode: Node? = null  // 尾指针
    var length = 0              // 表长度

    init {
        initList()
    }

    private fun initList() {
        this.headNode = Node()
        this.headNode!!.nextNode = headNode  // 头结点指向自己
        this.tailNode = this.headNode        // 尾指针指向头结点
        this.length = 0                      // 表长为1
    }

    fun tailInsertCreateList(node: Node) {
        if (this.headNode == null) initList()
        (this.headNode!!.nextNode == headNode)  // 自己指自己，说明只有一个头结点
                .trueLet {
                    node.nextNode = this@YsfPlusQuestion.headNode
                    this@YsfPlusQuestion.headNode?.nextNode = node
                    tailNode = node
                }
                .elseLet {
                    tailNode?.nextNode = node
                    node.nextNode = this@YsfPlusQuestion.headNode!!.nextNode
                    tailNode = node
                }
        ++length
    }

    fun traverseList(visit: (Node?) -> Unit) {
        if (this.headNode == null) initList()
        var head = this.headNode
        do {
            head = head!!.nextNode
            visit(head)
        } while (head != tailNode)
    }

    data class Node(
            var value: Int? = null, // 序号
            var key: Int? = null,  // 密码
            var nextNode: Node? = null
    )
}