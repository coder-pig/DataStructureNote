package queue

import trueLet

/**
 * @Author CoderPig
 * @Desc: 基于链表的双端队列
 * @CreateDate: Create in 2020/4/15 16:40
 */
class LinkDeque : IDeque<LinkDeque.Node?> {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val deque = LinkDeque()
            for (i in 1..5) deque.addFirst(Node(i))
            print("队头入列5个元素，当前队列：")
            deque.foreachFirst { print("${if (it == null) it else it.value} → ") }
            deque.removeFirst()
            deque.removeFirst()
            print("\n队头出列2个元素，当前队列：")
            deque.foreachFirst { print("${if (it == null) it else it.value} → ") }
            for (i in 10..14) deque.addLast(Node(i))
            print("\n队尾入列5个元素，当前队列：")
            deque.foreachFirst { print("${if (it == null) it else it.value} → ") }
            deque.removeLast()
            deque.removeLast()
            deque.removeLast()
            print("\n队尾出列3个元素，当前队列：")
            deque.foreachFirst { print("${if (it == null) it else it.value} → ") }
            println("\n当前队头：${deque.peekFirst()!!.value}")
            println("当前队尾：${deque.peekLast()!!.value}")
        }
    }

    var headNode: Node? = null  // 队头结点
    var tailNode: Node? = null  // 队尾结点
    var length = 0              // 表长度

    override fun addFirst(newEntry: Node?) {
        (headNode == null)
                .trueLet {
                    headNode = newEntry
                    tailNode = headNode
                }
                .elseLet {
                    headNode!!.priorNode = newEntry
                    newEntry!!.nextNode = headNode
                }
        length++
        headNode = newEntry
    }

    override fun removeFirst(): Node? {
        val temp = headNode
        (headNode?.nextNode == null)
                .trueLet { tailNode = null }
                .elseLet { headNode!!.nextNode!!.priorNode = null }
        headNode = headNode?.nextNode
        length--
        return temp
    }

    override fun peekFirst(): Node? {
        return headNode
    }

    override fun addLast(newEntry: Node?) {
        (tailNode == null)
                .trueLet {
                    tailNode = newEntry
                    headNode = tailNode
                }
                .elseLet {
                    tailNode!!.nextNode = newEntry
                    newEntry!!.priorNode = tailNode
                }
        length++
        tailNode = newEntry
    }

    override fun removeLast(): Node? {
        val temp = tailNode
        (tailNode?.priorNode == null)
                .trueLet { headNode = null }
                .elseLet { tailNode!!.priorNode!!.nextNode = null }
        tailNode = tailNode?.priorNode
        length--
        return temp
    }

    override fun isEmpty() = length == 0

    override fun peekLast(): Node? {
        return tailNode
    }

    override fun size() = length

    override fun clear() {
        while (headNode != null) {
            val temp = headNode
            headNode = null
            headNode = temp!!.nextNode
        }
    }

    override fun foreachFirst(visit: (Node?) -> Unit) {
        var temp = headNode
        while (temp != null) {
            visit(temp)
            temp = temp.nextNode
        }
    }

    override fun foreachLast(visit: (Node?) -> Unit) {
        var temp = tailNode
        while (temp != null) {
            visit(temp)
            temp = temp.priorNode
        }
    }

    data class Node(
            var value: Int? = null,
            var priorNode: Node? = null,    // 前驱引用
            var nextNode: Node? = null      // 后继引用
    )
}