package stack

/**
 * @Author CoderPig
 * @Desc: 递归问题求解
 * @CreateDate: Create in 2020/4/16 11:00
 */
class RecursionQuestion {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val question = RecursionQuestion()
            println(question.cinema(10))
            println(question.factorial(5))
            question.hanoi(4, 'A', 'C', 'B')
            println("${question.stepsCount(4)}")
            question.flipNum(123)
            println("\n${question.stepsCount(5)}")

        }
    }

    var num = 0
    var middleMap = HashMap<Int, Int>()

    /* 电影院问题 */
    private fun cinema(count: Int): Int {
        return if (count == 1) 1 else cinema(count - 1) + 1
    }

    /* 求阶乘 */
    private fun factorial(count: Int): Int {
        return if (count == 1) 1
        else factorial(count - 1) * count
    }

    /* 斐波那契数 */
    private fun fibonacci(count: Int): Int {
        return if (count <= 1) count
        else fibonacci(count - 1) + fibonacci(count - 2)
    }

    /* 数字翻转 */
    private fun flipNum(count: Int) {
        print(count % 10)
        if (count > 10) flipNum(count / 10)
    }

    /* 求台阶数 */
    private fun stepsCount(count: Int): Int {
        if (count == 1) return 1
        if (count == 2) return 2
        // 递归实现，空间换时间
//        return if (middleMap.containsKey(count)) {
//            middleMap[count]!!
//        } else {
//            num++
//            print("计算【${count}】\t")
//            if (num % 5 == 0) println()
//            val result = stepsCount(count - 1) + stepsCount(count - 2)
//            middleMap[count] = result
//            result
//        }
            var result = 0
            var pre = 1
            var next = 2
            for (i in 3..count) {
                result = pre + next
                pre = next
                next = result
            }
            return result
        }

        /* 汉诺塔问题，将n个盘从a经过b放到c */
        /* 参数依次：汉诺塔层数、承载最初圆盘的柱子、移动到目标柱子、中转作用的柱子*/
        private fun hanoi(n: Int, start: Char, end: Char, mid: Char) {
            if (n <= 0) return
            hanoi(n - 1, start, mid, end)   // 将A柱从上到下n-1个盘移到B柱上
            println("$start → $end")           // 把A柱上的盘放到C柱上
            hanoi(n - 1, mid, end, start)   // 将B柱上n-1个盘子移动到C柱上
        }

    }
