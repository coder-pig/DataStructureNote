package queue

/**
 * @Author CoderPig
 * @Desc: 双端队列方法接口
 * @CreateDate: Create in 2020/4/11 15:54
 */
interface IDeque<T> {
    fun addFirst(newEntry: T)   //队列头部增加元素
    fun removeFirst(): T        //队列头部移除元素
    fun peekFirst(): T          //窥视队头元素(不移除)
    fun addLast(newEntry: T)    //队列尾部增加元素
    fun removeLast(): T         //队列尾部移除元素
    fun isEmpty(): Boolean      //队列是否为空
    fun peekLast(): T           //窥视队尾元素(不移除)
    fun size(): Int             //队列中元素的个数
    fun clear()                 //清空队列
    fun foreachFirst(visit: (T) -> Unit)          //对列头部遍历队列
    fun foreachLast(visit: (T) -> Unit)           //对列尾部遍历队列
}