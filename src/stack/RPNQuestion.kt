package stack

import com.sun.tools.javac.util.StringUtils
import list.CircleSingleLinkedList
import list.SingleLinkedList
import java.util.*

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
            calculationRPN(result)
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
                    // 数字可能是多位数
                    while (i < after.length && after[i].toInt() in 48..57)  {
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
        // 遍历规则：
        // 数字，直接添加到链表中，否则就是运算符
        // 1.左括号或者栈为空，直接入栈
        // 2.右括号，一直出栈，直到遇到左括号，出栈
        // 3.+-*/，与栈内运算符比较优先级，优先级高或栈空直接入栈，否则把栈内运算符添加
        // 到链表中，再判断下个栈内的运算符优先级，直到优先级<=该运算符或栈空，再把运算符入栈
        // 4.遍历栈，把运算符添加到链表中
        private fun mathToRPN(list: SingleLinkedList): SingleLinkedList {
            // 计算运算符优先级
            fun getValue(operator: String?): Int {
                return when (operator) {
                    "+", "-" -> 1
                    "*", "/" -> 2
                    else -> 0
                }
            }

            val rpnList = SingleLinkedList()    // 存储逆波兰表达式
            val stack = LinkStack()             // 临时存储运算符
            list.traverseList {
                val temp = it?.value!! as String
                val isNumber = temp.matches("-?\\d+(\\.\\d+)?".toRegex())   // 正则判断是否为数字
                if (isNumber) {
                    rpnList.tailInsertCreateList(SingleLinkedList.Node(temp))
                } else {
                    when (temp) {
                        "(" -> stack.push(LinkStack.Node(temp))
                        ")" -> {
                            while (stack.peek()?.value != "(") {
                                rpnList.tailInsertCreateList(SingleLinkedList.Node(stack.pop()?.value))
                            }
                            stack.pop() // 左括号出栈
                        }
                        else -> {
                            while (stack.getSize() != 0 && getValue(temp) <= getValue(stack.peek()?.value as? String))
                                rpnList.tailInsertCreateList(SingleLinkedList.Node(stack.pop()?.value))
                            stack.push(LinkStack.Node(temp))
                        }
                    }
                }
            }
            while (stack.getSize() != 0)
                rpnList.tailInsertCreateList(SingleLinkedList.Node(stack.pop()?.value))
            return rpnList
        }

        // 计算逆波兰式结果
        // 数字入栈，否则弹出栈中两个元素进行计算
        private fun calculationRPN(list: SingleLinkedList) {
            val resultStack = LinkStack()    // 存储运算结果
            list.traverseList {
                val temp = it?.value!! as String
                val isNumber = temp.matches("-?\\d+(\\.\\d+)?".toRegex())   // 正则判断是否为数字
                if (isNumber) {
                    resultStack.push(LinkStack.Node(temp.toInt()))
                } else {
                    if (resultStack.getSize() >= 2) {
                        val a = resultStack.pop()
                        val b = resultStack.pop()
                        when (temp) {
                            "+" -> resultStack.push(LinkStack.Node((b!!.value as Int + a!!.value as Int)))
                            "-" -> resultStack.push(LinkStack.Node((b!!.value as Int - a!!.value as Int)))
                            "*" -> resultStack.push(LinkStack.Node((b!!.value as Int * a!!.value as Int)))
                            "/" -> resultStack.push(LinkStack.Node((b!!.value as Int / a!!.value as Int)))
                        }
                    }
                }
            }
            println("\n输出逆波兰式结算结果：${resultStack.pop()?.value}")
        }

    }

}