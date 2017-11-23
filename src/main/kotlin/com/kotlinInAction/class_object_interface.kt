package com.kotlinInAction


fun main(args: Array<String>) {

    ClickableButton().click()

}

// 4.1 定义类继承结构
// 4.1.1 Kotlin的接口：包括抽象方法的定义以及非抽象方法的实现，不能包含任何状态；
interface Clickable {
    fun click()     // 抽象方法
    // 接口方法的默认实现
    fun showOff() = println("I'm clickable!")
}

// 实现接口
class ClickableButton : Clickable {
    override fun click() = println("I was clicked")
}

interface Focusable {
    fun setFocus(b: Boolean) = println("I ${if (b) "got" else "lost"} focus.")
    fun showOff() = println("I'm focusable!")
}

// 调用继承自接口方法的实现
class ClickableFocusableButton : Clickable, Focusable {
    override fun click() = println("I was clicked")
    // 如果同样的继承成员有不止一个实现，必须提供一个显示实现；
    // 使用尖括号加上父类型名字的“super”表明了你想要调用哪一个父类的方法；
    override fun showOff() {
        super<Clickable>.showOff()
        super<Focusable>.showOff()
    }
}

// 4.1.2 open、final和abstract修饰符：默认为final
// 所有没有特别需要在子类中被重写的类和方法应该被显式地标注为final
// Java的类和方法默认是open的。而Kotlin中默认是final的

// 声明一个带一个open方法的open类
open class RichButton : Clickable {     // 一个open类，其他类可以继承它
    fun disable() {}                    // final的函数，不能在子类中重写它
    open fun animate() {}               // open的函数，可以在子类中重写
    final override fun click() {}       // 重写了一个open函数，也是open的。可以显式的标准为final，禁止重写
}

// 声明一个抽象类（抽象成员始终是 open 的）
abstract class Animated {               // 抽象类，不能创建实例
    abstract fun animate()              // 抽象方法，不必须被子类重写
    open fun stopAnimating(){}          // 抽象类中的非抽象函数并不是默认open的，但可以标准为open的
    fun animateTwice(){}
}

// 在接口中，不能使用final、open或者是abstract。接口成员始终是open的，不能将其声明为final的。如果没有函数体就是abstract的，但此关键字可以省略；

// 4.1.3 可见性修饰符：默认为public
// public、internal（模块中可见）、protected（子类可见）、private（类中可见）

internal open class TalkativeButton : Focusable {
    private fun yell() = println("Hey!")
    protected fun whisper() = println("Let's talk!")
}
// fun TalkativeButton.giveSpeech() {       // public成员暴露了其internal接收者类型TalkativeButton
//     yell()                               // 不能访问private的yell
//     whisper()                            // 不能访问protected的whisper
// }

internal fun TalkativeButton.giveSpeech() {
    // yell()
    // whisper()
}




