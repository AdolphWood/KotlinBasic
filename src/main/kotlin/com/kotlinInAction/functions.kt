@file:JvmName("StringFunctions")

// 注解指定类名
package com.kotlinInAction

import javax.print.attribute.standard.MediaSize

fun main(args: Array<String>) {
    // 在Kotlin中创建集合
    val set = hashSetOf(1, 7, 53)
    val list = arrayListOf(1, 7, 53)
    val map = hashMapOf(1 to "one", 7 to "seven", 53 to "fifty-three")
    // Kotlin中采用标准的Java集合类
    println(set.javaClass)
    println(list.javaClass)
    println(map.javaClass)
    // 但是Kotlin的集合类更强大
    val strings = listOf("first", "second", "fourteenth")
    println(strings.last())
    val numbers = setOf(1, 14, 2)
    println(numbers.max())

    // 让函数更好调用
    val list1 = listOf(1, 2, 3)
    println(joinTOString(list1, "; ", "(", ")"))
    // 默认参数值
    println(joinTOString(list1))
    println(joinTOString(list1, separator = ";", prefix = "# "))

    println("Kotlin".lastChar())
    // 扩展函数不会自动地在项目范围内生效，需要导入

    val list2 = listOf(1, 2, 3)
    println(list2.joinToString(separator = "; ", prefix = "(", postfix = ")"))

    // 扩展函数即是静态函数
    val view: View = Button()
    // 调用运行时类型的方法
    view.click()
    val view1: View = Button()
    // 调用静态类型的方法
    view1.showOff()

    // 扩展属性
    println("Kotlin".lastChar())
    val sb = StringBuilder("Kotlin?")
    sb.lastChar = '!'
    println(sb)

    // 中缀调用
    val (number, name) = 1 to "one"

    // 分割字符串
    println("12.345-6.A".split("\\.|-".toRegex()))
    println("12.345-6.A".split(".","-"))

    parsePath("/Users/yole/kotlin-book/chapter.adoc")
}

fun <T> joinTOString(collection: Collection<T>, separator: String = ", ", prefix: String = "", postfix: String = ""): String {
    val result = StringBuilder(prefix)
    for ((index, element) in collection.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}

// 给别人的类添加方法：扩展函数和属性
// 接收者类型：要扩展的类或者接口的名称（String）；
// 接收者对象：用来调用这个扩展函数的对象（this）；
fun String.lastChar(): Char = this.get(this.length - 1)

// 作为扩展函数的工具函数
fun <T> Collection<T>.joinToString(separator: String = ", ", prefix: String = "", postfix: String = ""): String {
    val result = StringBuilder(prefix)
    for ((index, element) in this.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}

// 重写成员函数
open class View {
    open fun click() = println("View clicked")
}

class Button: View() {
    override fun click() = println("Button clicked")
}
// 不能重写扩展函数
fun View.showOff() = println("I'm a view!")
fun Button.showOff() = println("I'm a button!")

// 扩展属性
// 声明一个扩展属性
var StringBuilder.lastChar: Char
    get() = get(length - 1)
    set(value: Char) {
        this.setCharAt(length - 1, value)
    }

// 处理集合：可变参数、中缀调用和库的支持

// 要允许使用中缀符号调用函数，需要使用infix修饰符来标记它
infix fun Any.to(other: Any) = Pair(this, other)

// 使用String的扩展函数来解析文件路径
fun parsePath(path: String) {
    val directory = path.substringBeforeLast("/")
    val fullName = path.substringAfterLast("/")
    val fileName = fullName.substringBeforeLast(".")
    val extension = fullName.substringAfterLast(".")
    println("Dir: $directory, name: $fileName, ext: $extension")
}




