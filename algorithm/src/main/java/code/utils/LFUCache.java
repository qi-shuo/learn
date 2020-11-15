package code.utils;

import sun.misc.LRUCache;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author QiShuo
 * @version 1.0
 * @create 2020-04-05 19:12
 */
public class LFUCache {
    Map<Integer, Node> map = new HashMap<>();
    Map<Integer, LinkedList<Node>> countMap = new HashMap<>();
    int capacity;
    int minCount;

    public LFUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (capacity == 0) {
            return -1;
        }
        if (map.containsKey(key)) {
            Node node = map.get(key);
            int oldCount = node.count;
            LinkedList<Node> linkedList = countMap.get(oldCount);
            linkedList.remove(node);
            if (linkedList.size() == 0) {
                countMap.remove(oldCount);
            }
            int newCount = oldCount + 1;
            if (minCount == oldCount) {
                if (!countMap.containsKey(oldCount)) {
                    minCount = newCount;
                }
            }
            node.count = newCount;
            if (countMap.containsKey(newCount)) {
                countMap.get(newCount).addFirst(node);
            } else {
                LinkedList<Node> linked = new LinkedList<>();
                linked.addFirst(node);
                countMap.put(newCount, linked);
            }
            return node.val;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        if (map.containsKey(key)) {
            Node node = map.get(key);
            int oldCount = node.count;
            LinkedList<Node> linkedList = countMap.get(oldCount);
            linkedList.remove(node);
            if (linkedList.size() == 0) {
                countMap.remove(oldCount);
            }
            int newCount = oldCount + 1;
            node.count = newCount;
            if (minCount == oldCount) {
                if (!countMap.containsKey(oldCount)) {
                    minCount = newCount;
                }
            }
            node.val = value;
            if (countMap.containsKey(newCount)) {
                countMap.get(newCount).addFirst(node);
            } else {
                LinkedList<Node> linked = new LinkedList<>();
                linked.addFirst(node);
                countMap.put(newCount, linked);
            }
        } else if (map.size() < capacity) {
            Node node = new Node(key, value);
            map.put(key, node);
            if (countMap.containsKey(node.count)) {
                countMap.get(node.count).addFirst(node);
            } else {
                LinkedList<Node> linked = new LinkedList<>();
                linked.addFirst(node);
                countMap.put(node.count, linked);
            }
            minCount = 1;
        } else {
            LinkedList<Node> minLinked = countMap.get(minCount);
            Node removeNode = minLinked.removeLast();
            map.remove(removeNode.key);
            if (minLinked.size() == 0) {
                countMap.remove(minCount);
            }
            Node node = new Node(key, value);
            map.put(key, node);
            if (countMap.containsKey(node.count)) {
                countMap.get(node.count).addFirst(node);
            } else {
                LinkedList<Node> linked = new LinkedList<>();
                linked.addFirst(node);
                countMap.put(node.count, linked);
            }
            minCount = 1;
        }
    }

    class Node {
        int key;
        int val;
        int count = 1;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
}
