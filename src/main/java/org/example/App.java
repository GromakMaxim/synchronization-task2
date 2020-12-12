package org.example;

public class App {
    public static void main(String[] args) {

        Store store = new Store();
        Producer producer = new Producer(store);
        Consumer consumer = new Consumer(store);

        new Thread(consumer, "Покупашка1").start();
        new Thread(consumer, "Покупашка2").start();
        new Thread(consumer, "Покупашка3").start();
        new Thread(producer, "Рабочий").start();


    }
}
