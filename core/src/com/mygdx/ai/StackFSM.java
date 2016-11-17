package com.mygdx.ai;


import java.util.Stack;

public class StackFSM {
    private Stack stack;

    public StackFSM() {
        stack = new Stack();
    }

    public String getCurrentState() {
        if(!stack.isEmpty())
            return (String)stack.peek();
        else
            return null;
    }

    public String popState() {
        return (String)stack.pop();
    }

    public void pushState(String state) {
        if (getCurrentState() != state)
            stack.push(state);
    }
}
