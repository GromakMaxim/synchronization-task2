package org.example;

// Класс покупатель
class Consumer implements Runnable {

    Store store;
    final int PURCHASED_GOODS = 2;//количество покупаемых товаров каждым покупателем

    Consumer(Store store) {
        this.store = store;
    }

    public void run() {
        for (int i = 0; i < PURCHASED_GOODS; i++) {
            store.get();
        }
    }
}
