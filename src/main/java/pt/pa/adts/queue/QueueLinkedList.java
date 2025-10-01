package pt.pa.adts.queue;

/**
 * This class must implement the Queue interface
 * @param <T>
 */
public class QueueLinkedList<T> implements Queue<T> {

    private ListNode header, trailer;
    private int size;

    public QueueLinkedList() {
        this.trailer = null;
        this.size = 0;
        this.header = null;
    }

    @Override
    public void enqueue(T elem) throws QueueFullException, NullPointerException {
        if (elem == null) {
            throw new NullPointerException("Element cannot be null.");
        }

        ListNode newNode = new ListNode(elem, trailer, null);

        if (isEmpty()) {
            header = newNode;
        } else {
            trailer.next = newNode;
        }

        trailer = newNode;
        size++;
    }

    @Override
    public T dequeue() throws QueueEmptyException {
        if (isEmpty()) {
            throw new QueueEmptyException("Queue is empty.");
        }

        T element = header.element;
        header = header.next;

        if (header != null) {
            header.prev = null;
        } else {
            // Fila ficou vazia
            trailer = null;
        }

        size--;
        return element;
    }

    @Override
    public T front() throws QueueEmptyException {
        if (isEmpty()) {
            throw new QueueEmptyException("Queue is empty.");
        }
        return header.element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        header = null;
        trailer = null;
        size = 0;
    }

    private class ListNode {
        private T element;
        private ListNode next;
        private ListNode prev;

        public ListNode(T element, ListNode prev, ListNode next) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }
}
