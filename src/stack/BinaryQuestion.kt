package stack

/**
 * @Author CoderPig
 * @Desc: 顺序栈实现进制转换
 * @CreateDate: Create in 2020/4/2 11:59
 */
class BinaryQuestion {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            binaryExchange(20, 2)
            binaryExchange(20, 4)
            binaryExchange(20, 8)
            binaryExchange(20, 16)
        }

        private fun binaryExchange(num: Int, type: Int) {
            val stack = SeqStack()
            var n = num
            while (n != 0) {
                stack.push(SeqStack.Node(n % type))
                n /= type
            }
            print("输出十进制转${type}进制后的值：")
            for (i in 0 until stack.getSize()) {
                print(stack.pop()!!.value)
            }
            println()
        }
    }
}