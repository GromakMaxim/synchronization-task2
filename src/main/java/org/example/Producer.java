package org.example;

// класс Производитель
class Producer implements Runnable {
    Store store;
    final int DELIVERIES_QUANTITY= 2;//сколько раз производитель совершит поставку нового товара

    Producer(Store store) {
        this.store = store;
    }

    public void run() {
        for (int i = 0; i < DELIVERIES_QUANTITY; i++) {
            store.put();
        }
    }
}
