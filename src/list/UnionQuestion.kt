package list

/**
 * @Author CoderPig
 * @Desc: 求并集
 * @CreateDate: Create in 2020/3/28 11:19
 */

class UnionQuestion {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val listA = SeqList()
            val listB = SeqList()
            // 构造模拟数据
            for (i in 0..30 step 5) {
                listA.insertElement(SeqList.Node(i + (0..5).random()), listA.listLength())
            }
            for (i in 0..50 step 5) {
                listB.insertElement(SeqList.Node(i + (0..5).random()), listB.listLength())
            }
            print("构造后的A顺序表：")
            listA.traverseList { print("${it?.value} → ") }
            print("\n构造后的A顺序表：")
            listB.traverseList { print("${it?.value} → ") }
            println()
            unionList(listA, listB)
        }

        private fun unionList(la: SeqList, lb: SeqList): SeqList {
            val lc = SeqList()
            // Tip: 这一步可以不做，只是我在构造方法里初始化过默认容量，这里销毁下~
            lc.destroyList()
            // 构造列表C的初始长度为：A + B的长度
            val aLength = la.listLength()
            val bLength = lb.listLength()
            lc.initList(aLength + bLength)
            var aPos = 0   // 指向列表A的游标
            var bPos = 0   // 指向列表B的游标
            while (aPos in 0 until aLength && bPos in 0 until bLength) {
                if (la.getElement(aPos)!!.value < lb.getElement(bPos)!!.value) {
                    lc.insertElement(la.getElement(aPos++)!!, lc.listLength())
                } else {
                    lc.insertElement(lb.getElement(bPos++)!!, lc.listLength())
                }
            }
            // 假如有剩余元素，要么是A，要么是B
            while (aPos < aLength) {
                lc.insertElement(la.getElement(aPos++)!!, lc.listLength())
            }
            while (bPos < bLength) {
                lc.insertElement(lb.getElement(bPos++)!!, lc.listLength())
            }
            print("合并后的C顺序表：")
            lc.traverseList { print("${it?.value} → ") }
            // 去重，因为列表是排序过的，准备两枚下标即可
            var frontPos = 0    // 前面的下标
            var behindPos = 0   // 后面的下标
            while (frontPos < lc.listLength() - 1) {
                if (lc.getElement(frontPos)!!.value == lc.getElement(++behindPos)!!.value) {
                    lc.removeElement(behindPos)
                } else {
                    frontPos++
                    behindPos = frontPos
                }
            }
            print("\n去重后的C顺序表：")
            lc.traverseList { print("${it?.value} → ") }
            return lc
        }
    }
}