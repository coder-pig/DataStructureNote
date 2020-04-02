package stack

import com.sun.tools.javac.util.StringUtils
import list.CircleSingleLinkedList
import list.SingleLinkedList

/**
 * @Author CoderPig
 * @Desc: 逆波兰式的代码实现
 * @CreateDate: Create in 2020/4/2 17:55
 */
class RPNQuestion {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var result = stringToMath("8 * ( 1  + 2) / ( 2 +  4 )")
            print("输出转换后的中缀表达式：")
            result.traverseList { print("${it?.value} ") }
            println()
            result = mathToRPN(result)
            print("输出转换后的逆波兰式：")
            result.traverseList { print("${it?.value} ") }
        }

        // 字符串转数学(中缀)表达式
        private fun stringToMath(content: String): SingleLinkedList {
            val after = content.replace(" ", "")  // 去掉空格
            val temp = SingleLinkedList()
            var i = 0
            while (i < after.length) {
                // 48代表0，57代表9，数字在这个范围内
                if (after[i].toInt() in 48..57) {
                    var str = ""
                    while (i < after.length && after[i].toInt() in 48..57) {
                        str += after[i]
                        i++
                    }
                    temp.tailInsertCreateList(SingleLinkedList.Node(str))
                    i--
                } else {
                    temp.tailInsertCreateList(SingleLinkedList.Node("${after[i]}"))
                }
                i++
            }
            return temp
        }

        // 数学表达式转成逆波兰式
        private fun mathToRPN(list: SingleLinkedList): SingleLinkedList {
            val rpnList = SingleLinkedList()    // 存储逆波兰表达式
            val stack = LinkStack()             // 临时存储运算符
            list.traverseList {
                val temp = it?.value!! as String
                val isNumber = temp.matches("-?\\d+(\\.\\d+)?".toRegex())   // 正则判断是否为数字
                if (isNumber) {
                    rpnList.tailInsertCreateList(SingleLinkedList.Node(temp))
                } else {
                    when (temp) {
                        // 右括号，弹出栈中字符，直到遇到左括号
                        ")" -> {
                            var oldValue: String?
                            do {
                                oldValue = stack.pop()?.value as? String
                            } while ("(" != oldValue)
                        }
                        // 栈空直接压栈，否则要做下判断
                        "+", "-" -> {
                            if (stack.getSize() == 0) {
                                stack.push(LinkStack.Node(temp))
                            } else {
                                var oldValue: String?
                                do {
                                    oldValue = stack.pop()?.value as? String
                                    if("(" == oldValue) {
                                        stack.push(LinkStack.Node(temp))
                                    } else {
                                        rpnList.tailInsertCreateList(SingleLinkedList.Node(temp))
                                    }
                                } while (stack.getSize() > 0 && "(" != temp)
                                stack.push(LinkStack.Node(temp))
                            }
                        }
                        // 乘除和左括号，直接压栈
                        "*","/","(" -> {
                            stack.push(LinkStack.Node(temp))
                        }
                    }
                }
            }
            return rpnList
        }
    }
}