package queue

/**
 * @Author CoderPig
 * @Desc: 扑克牌问题
 * @CreateDate: Create in 2020/4/8 11:55
 */
class PokerQuestion {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            handle(13)
        }

        private fun handle(n: Int) {
            val queue = CircleQueue()
            queue.initQueue(n)
            val temp = IntArray(n)  // 索引数组
            for (i in 0 until n) {
                queue.enqueue(CircleQueue.Node(i))
            }
            for (i in 0 until n) {
                temp[i] = queue.dequeue()?.value as Int
                val tempNode = queue.dequeue()
                tempNode?.let { queue.enqueue(it) }
            }
            // 索引与数字交换
            val result = IntArray(n)  // 保存结果的数组
            for (i in 0 until n) {
                result[temp[i]] = i
            }
            // 输出初始牌组
            print("输出初始牌组：")
            result.forEach { print("${it + 1} → ") }
        }
    }
}