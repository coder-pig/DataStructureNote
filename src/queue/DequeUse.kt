package queue

/**
 * @Author CoderPig
 * @Desc: 双端队列的应用示例：击鼓传花 与 字符串回文验证
 * @CreateDate: Create in 2020/4/15 18:41
 */
class DequeUse {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val dequeUse = DequeUse()
            dequeUse.hotPotato(10, 3)
            println()
            println("\n输出校验结果：${dequeUse.palindromeChecker("abcdedcba")}")
            println("\n输出校验结果：${dequeUse.palindromeChecker("abcdsdfsdjkfd")}")
            println("\n输出校验结果：${dequeUse.palindromeChecker("A man, a plan, a canal: Panama")}")
            println("\n输出校验结果：${dequeUse.palindromeChecker("race a car")}")
        }
    }

    /* 击鼓传花问题模拟 */
    fun hotPotato(count: Int, num: Int) {
        val deque = LinkDeque()
        println("击鼓传花游戏开始!!! => 人数 x $count ~ 报数 x  $num")
        for (i in 1..count) deque.addLast(LinkDeque.Node(i))
        print("初始队列：")
        deque.foreachFirst { print("${if (it == null) it else it.value} → ") }
        print("\n出列顺序：")
        while (deque.size() > 1) {
            for (i in 0 until num) {
                val temp = deque.removeFirst()
                deque.addLast(temp)
            }
            print("${deque.removeFirst()?.value} → ")
        }
        println("\n胜出玩家为：${deque.removeFirst()?.value}")
    }

    /* 字符串回文校验 */
    fun palindromeChecker(content: String): Boolean {
        val deque = ArrayDeque()
        println("字符串回文校验开始!!! => 校验字符串：【$content】")
        content.forEach {
            if(it in '0'..'9' || it in 'a'..'z' || it in 'A'..'Z')
                deque.addFirst(ArrayDeque.Node(it.toLowerCase()))
        }
        print("初始队列：")
        deque.foreachFirst { it?.value?.let { c -> print("$c → ") } }
        while(deque.size() > 1) {
            if(deque.removeFirst()?.value != deque.removeLast()?.value) return false
        }
        return true
    }

}