package data_structures;

import java.text.MessageFormat;

@SuppressWarnings({"DesignForExtension", "LocalVariableOfConcreteClass", "InstanceVariableOfConcreteClass", "InstanceVariableNamingConvention", "unused", "MultipleTopLevelClassesInFile", "ClassNamingConvention"})
public class Stack<E> {

    @SuppressWarnings("PackageVisibleField")
    private StackElement<E> top;

    @SuppressWarnings("LocalCanBeFinal")
    void push(E item) {
        top = new StackElement<>(item, top);
    }

    E pop() {
        final StackElement<E> oldTop = top;
        top = top.getNext();
        return oldTop.getData();
    }

    boolean empty() {
        return top == null;
    }

    @Override
    public String toString() {
        return MessageFormat.format("Stack<{0}>", top.toString());
    }
}

@SuppressWarnings({"DesignForExtension", "MultipleTopLevelClassesInFile", "ClassNameDiffersFromFileName"})
class StackElement<E> {

    private StackElement<E> next;
    private E data;

    StackElement(final E data, final StackElement<E> next) {
        this.data = data;
        this.next = next;
    }

    StackElement(final E data) {
        this.data = data;
    }

    StackElement<E> getNext() {
        return next;
    }

    public void setNext(final StackElement<E> next) {
        this.next = next;
    }

    E getData() {
        return data;
    }

    public void setData(final E data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return MessageFormat.format("StackElement<{0}>", data.toString());
    }
}
