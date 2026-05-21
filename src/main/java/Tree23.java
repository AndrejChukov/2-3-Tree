import java.util.*;

public class Tree23 {
    private Node root;
    public long operations = 0; // Счетчик операций (сравнений)

    private class Node {
        List<Integer> keys = new ArrayList<>();
        List<Node> children = new ArrayList<>();

        boolean isLeaf() { return children.isEmpty(); }
    }

    public boolean find(int key) {
        return find(root, key);
    }

    private boolean find(Node node, int key) {
        if (node == null) return false;
        operations++;
        for (int i = 0; i < node.keys.size(); i++) {
            operations++;
            if (key == node.keys.get(i)) return true;
            if (key < node.keys.get(i)) return find(node.isLeaf() ? null : node.children.get(i), key);
        }
        return find(node.isLeaf() ? null : node.children.get(node.children.size() - 1), key);
    }

    public void insert(int key) {
        if (root == null) {
            root = new Node();
            root.keys.add(key);
            return;
        }
        Node extra = insert(root, key);
        if (extra != null) {
            Node newRoot = new Node();
            newRoot.keys.add(extra.keys.get(0));
            newRoot.children.add(root);
            newRoot.children.add(extra.children.get(0));
            root = newRoot;
        }
    }

    private Node insert(Node node, int key) {
        operations++;
        if (node.isLeaf()) {
            node.keys.add(key);
            Collections.sort(node.keys);
        } else {
            int i = 0;
            while (i < node.keys.size() && key > node.keys.get(i)) {
                i++;
                operations++;
            }
            Node extra = insert(node.children.get(i), key);
            if (extra != null) {
                node.keys.add(i, extra.keys.get(0));
                node.children.add(i + 1, extra.children.get(0));
            }
        }

        if (node.keys.size() > 2) {
            return split(node);
        }
        return null;
    }

    private Node split(Node node) {
        Node newNode = new Node();
        int midKey = node.keys.get(1);
        newNode.keys.add(midKey);

        Node rightSibling = new Node();
        rightSibling.keys.add(node.keys.get(2));
        if (!node.isLeaf()) {
            rightSibling.children.add(node.children.get(2));
            rightSibling.children.add(node.children.get(3));
            node.children.remove(3);
            node.children.remove(2);
        }

        node.keys.remove(2);
        node.keys.remove(1);

        newNode.children.add(rightSibling);
        return newNode;
    }

    public void delete(int key) {
        root = delete(root, key);
    }

    private Node delete(Node node, int key) {
        if (node == null) return null;
        operations++;
        node.keys.remove(Integer.valueOf(key)); // Упрощенно: удаляем и "схлопываем"
        if (node.keys.isEmpty() && !node.isLeaf()) return node.children.get(0);
        return node;
    }
}