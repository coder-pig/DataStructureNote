package queue

import java.util.*

/**
 * @Author CoderPig
 * @Desc: 银行业务队列简单模拟
 * @CreateDate: Create in 2020/4/7 15:39
 */
class SimpleBankQuestion {
    companion object {
        private val read = Scanner(System.`in`)

        @JvmStatic
        fun main(args: Array<String>) {
            print("请输入排队人数：")
            val count = read.nextInt()
            handle(count)
        }

        private fun handle(c: Int) {
            val qA = LinkQueue()
            val qB = LinkQueue()
            for (i in 0 until c) {
                print("输入第${i + 1}个客户的编号：")
                val temp = read.nextInt()
                // A放奇数，B放偶数
                if (temp % 2 != 0) {
                    qA.insertQueue(LinkQueue.Node(temp))
                } else {
                    qB.insertQueue(LinkQueue.Node(temp))
                }
            }
            var flag = 0    // 标记
            print("输出结果：")
            while (!qA.isEmpty() || !qB.isEmpty()) {
                flag++
                if (flag % 2 != 0) {
                    if (!qA.isEmpty()) {
                        print(" ${qA.frontNode?.nextNode?.value}")
                        qA.deleteQueue()
                    }
                } else {
                    if (!qA.isEmpty()) {
                        print(" ${qA.frontNode?.nextNode?.value}")
                        qA.deleteQueue()
                    }
                    if (!qB.isEmpty()) {
                        print(" ${qB.frontNode?.nextNode?.value}")
                        qB.deleteQueue()
                    }
                }
            }
        }
    }
}