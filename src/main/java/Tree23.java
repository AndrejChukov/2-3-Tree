import java.util.ArrayList;
import java.util.List;

// 3. 2-3 дерево
public class Main {
    public static void main(String[] args) {

    }
}

class Tree23 {
    private Node23 root; // Самая верхняя комната

    // 1. Поиск числа
    public boolean find(int key) {
        return find(root, key);
    }

    private boolean find(Node23 node, int key) {
        if (node == null) return false;

        // Ищем число прямо в текущей комнате
        if (node.keys.contains(key)) return true;

        // Если мы в самом низу и не нашли - значит числа нет
        if (node.isLeaf()) return false;

        // Решаем, в какую дверь спускаться
        if (key < node.keys.get(0)) {
            return find(node.children.get(0), key); // В левую дверь
        } else if (node.keys.size() == 1 || key < node.keys.get(1)) {
            return find(node.children.get(1), key); // В среднюю (или правую для 1 числа)
        } else {
            return find(node.children.get(2), key); // В правую дверь
        }
    }

    // 2. Вставка (упрощенная логика)
    public void insert(int key) {
        if (root == null) {
            root = new Node23();
            root.keys.add(key);
            return;
        }
        // В реальном коде тут была бы сложная логика "взрывов" (split)
        System.out.println("Пытаемся положить число " + key + " в дерево...");
        // В учебных целях здесь обычно показывают процесс перестроения
    }

    public static void main(String[] args) {
        Tree23 tree = new Tree23();

        // Пример использования
        System.out.println("Создаем дерево...");
        tree.insert(10);
        tree.insert(20);

        System.out.println("Есть ли в дереве 10? " + tree.find(10));
        System.out.println("Есть ли в дереве 50? " + tree.find(50));
    }
}
