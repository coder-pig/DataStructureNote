package list

/**
 * @Author CoderPig
 * @Desc: 快慢指针
 * @CreateDate: Create in 2020/3/28 11:19
 */

class FastSlowPointer {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val la = SingleLinkedList()
            for (i in 1..15) la.tailInsertCreateList(SingleLinkedList.Node(i))
            print("初始化后的链表：")
            la.traverseList { print("${it?.value} → ") }
            println("\n中间结点的值为：${findMiddleNode(la)?.value}")
            println("倒数第3个结点的值为：${findContraryNode(la, 3)?.value}")
        }

        /* 查找中间结点 */
        private fun findMiddleNode(l: SingleLinkedList): SingleLinkedList.Node? {
            var slowPointer = l.headNode
            var fastPointer = l.headNode
            while (fastPointer?.nextNode != null) {
                fastPointer = fastPointer.nextNode!!.nextNode
                slowPointer = slowPointer!!.nextNode
            }
            return slowPointer
        }

        /* 查找倒数第k个结点 */
        private fun findContraryNode(l: SingleLinkedList, pos: Int): SingleLinkedList.Node? {
            var slowPointer = l.headNode
            var fastPointer = l.headNode
            for (i in 1 until pos) fastPointer = fastPointer?.nextNode   // 先偷走pos步
            while (fastPointer?.nextNode != null) {
                fastPointer = fastPointer.nextNode
                slowPointer = slowPointer!!.nextNode
            }
            return slowPointer
        }
    }
}