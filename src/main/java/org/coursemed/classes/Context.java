package org.coursemed.classes;

import java.util.Stack;

// A class created to transfer objects between JavaFX windows
public class Context {
    private static final Stack<Object> contextStack=new Stack<>();

    private Context() {
    }

    public static Object popContext() {
        return contextStack.pop();
    }

    public static void pushContext(Object context) {
        contextStack.push(context);
    }
}
