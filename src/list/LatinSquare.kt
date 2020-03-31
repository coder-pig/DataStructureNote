package list

/**
 * @Author CoderPig
 * @Desc: 打印拉丁方阵
 * @CreateDate: Create in 2020/3/30 22:38
 */
class LatinSquare {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            printSquare(10)
        }

        private fun printSquare(num: Int) {
            val latin = CircleSingleLinkedList()
            for (i in 1..num) latin.tailInsertCreateList(CircleSingleLinkedList.Node(i))
            print("初始化循环单链表：")
            latin.traverseList { print("${it?.value}  ") }
            println("\n=> 打印输出 $num * $num 拉丁方阵：")
            var head = latin.headNode!!.nextNode  // 指向第一个结点
            for(i in 0 until num) {
                val temp = head      // 存储起始结点，如果和head相等，说明走完一轮
                while (true) {
                    print("${head!!.value}  ")
                    head = head.nextNode
                    if(head == temp) break
                }
                head = head!!.nextNode  // 指向下一个结点
                println()
            }
        }
    }
}