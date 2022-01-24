package com.example.calculator


class Stack{
    private var elements:MutableList<Any> = mutableListOf()

    fun isEmpty():Boolean = elements.isEmpty()

    fun push(item:Any) = elements.add(item)

    fun peek():Any? = elements.lastOrNull()

    fun pop(): Any {
        val item = elements.lastOrNull()
        if(!this.isEmpty()){
            elements.removeLast()
        }
        return item?:0
    }

    override fun toString(): String = elements.toString()
}

class Expression(private var expression:String) {

    private var result:ArrayList<String> = arrayListOf()

    private fun precedence(text: String): Int {
        if (text == "+" || text == "-")
            return 1
        else if (text == "*" || text == "/")
            return 2
        return -1
    }

    //Infix to postfix
    fun build(){
        val input = expression.split(' ')
        val length = input.size
        val st = Stack()
        var i = 0
        while(i<length){
            if(input[i].matches("-?\\d+(\\.\\d+)?".toRegex())){
                result.add(input[i])
            }
            else {
                if (st.isEmpty() || input[i] == "(") {
                    st.push(input[i])
                }
                else if (input[i] == ")") {
                    while (!st.isEmpty() && st.peek() != "(") {
                        result.add(st.peek().toString())
                        st.pop()
                    }
                    if (st.peek() == "(") {
                        st.pop()
                    }
                }
                else {
                    while (!st.isEmpty() && this.precedence(input[i]) <= this.precedence(st.peek().toString())) {
                        result.add(st.peek().toString())
                        st.pop()
                    }
                    st.push(input[i])
                }
            }
            i+=1
        }
        while(!st.isEmpty())
        {
            result.add(st.peek().toString())
            st.pop()
        }
    }

    //Postfix solving
    fun evaluate():Any{
        val stack = Stack()
        for (exp in result) {
            if(exp.isEmpty()) continue
            if (exp[exp.length-1].isDigit())
                stack.push(exp.toDouble())
            else {
                val b = stack.pop()
                val a = stack.pop()
                when (exp[0]) {
                    '+' -> stack.push(a as Double + b as Double)
                    '-' -> stack.push(a as Double - b as Double)
                    '*' -> stack.push(a as Double * b as Double)
                    else -> stack.push(a as Double / b as Double)
                }
            }
        }
        return stack.pop()
    }
}