package list

/**
 * @Author CoderPig
 * @Desc: 魔术师发牌问题
 * @CreateDate: Create in 2020/3/30 17:58
 */
class MagicianQuestion {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            licensing()
        }

        private fun licensing() {
            val cards = CircleSingleLinkedList()
            for (i in 1..13) cards.tailInsertCreateList(CircleSingleLinkedList.Node(0))
            print("初始牌组：")
            cards.traverseList { print("${it?.value} → ") }
            var countNumber = 2   // 记录当前到哪张牌
            var head = cards.headNode!!.nextNode
            head!!.value = 1      // 第一张牌肯定是1
            while (countNumber < 14) {
                var count = 0     // 计数
                // 定位到插入位置
                while (count < countNumber) {
                    head = head!!.nextNode
                    // 不为0说明此处有牌了
                    if(head!!.value != 0) {
                        count--
                    }
                    count++
                }
                if(head!!.value == 0) {
                    head.value = countNumber
                    countNumber++
                }
                print("\n当前牌组：")
                cards.traverseList { print("${it?.value} → ") }
            }
            print("\n排的放置顺序：")
            cards.traverseList { print("黑桃${it?.value} → ") }
        }
    }
}