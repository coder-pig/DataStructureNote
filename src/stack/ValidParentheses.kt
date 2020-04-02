package stack

/**
 * @Author CoderPig
 * @Desc: 有效的括号
 * @CreateDate: Create in 2020/4/2 16:28
 */
class ValidParentheses {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(analysis("([])"))
            println(analysis("()()"))
            println(analysis("([)]"))
            println(analysis("()[}{)"))
        }

        private fun analysis(content: String): Boolean {
            val stack = LinkStack()
            content.forEach {
                when(it) {
                    '(', '[', '{' -> {
                        stack.push(LinkStack.Node(it))
                    }
                    ')', ']', '}' -> {
                        when(stack.pop()?.value) {
                            '(' -> if (it != ')') return false
                            '[' -> if (it != ']') return false
                            '{' -> if (it != '}') return false
                        }
                    }
                }
            }
            return true
        }
    }
}