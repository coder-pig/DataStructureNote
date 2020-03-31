package list

import trueLet

/**
 * @Author CoderPig
 * @Desc: 约瑟夫问题
 * @CreateDate: Create in 2020/3/28 18:10
 */
class YsfQuestion {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val count = 41
            val step = 3
            firstMethod(count, step)
            secondMethod(count, step)
            println("${count}人玩耍 → 第${step}个人报数 → 最后幸存者：${thirdMethod(count, step) + 1}")
        }

        /* 标记数组解法 */
        private fun firstMethod(n: Int, m: Int) {
            var last = n    // 剩余人数
            var count = 1   // 报数
            val tag = IntArray(1000)    // 初始化一个标记数组
            for (i in 0 until n) tag[i] = 1  // 初始化活人标记
            var index = 0   // 数组下标索引
            // 开始报数
            while (last > 1) {
                // 不直接用index的原因是，可能出现n少于m的情况
                if (tag[index % n] == 0) {
                    index++
                    continue
                }
                if (count == m) {
                    count = 1
                    tag[index % n] = 0
                    println("第${index % n + 1}个人出局")
                    last--
                } else count++
                index++
            }
            for (i in 0 until n) {
                if (tag[i] == 1) println("第${i % n + 1}人是幸存者！")
            }
        }

        /* 循环链表解法  */
        private fun secondMethod(n: Int, m: Int) {
            val list = CircleSingleLinkedList()
            for (i in 1..n) list.tailInsertCreateList(CircleSingleLinkedList.Node(i))
            println("排队顺序：")
            list.traverseList { print("${it?.value} ") }
            var head = list.headNode       // 指向头结点
            var temp: CircleSingleLinkedList.Node?   // 临时存储
            val mReset = m % n             //为防止报数的人大于参与的人，求余可以理解为重头开始
            println("\n出局：")
            while (list.length > 1) {
                // 获取出局者的前一个结点
                for (i in 1 until mReset) {
                    head = head?.nextNode
                }
                print("${head?.nextNode?.value} ")
                temp = head?.nextNode               // 指向删除结点
                head?.nextNode = temp?.nextNode     // 前一个结点指向删除的后一个结点
                list.length--
            }
            println("\n剩下第${head?.nextNode?.value}是幸存者!")
        }

        /* 数学推导解法 */
        private fun thirdMethod(n: Int, m: Int): Int {
            return if (n == 1) 0 else (thirdMethod(n - 1, m) + m) % n
        }
    }
}