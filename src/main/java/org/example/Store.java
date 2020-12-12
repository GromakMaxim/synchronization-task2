package org.example;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Класс Магазин, хранящий произведенные товары
class Store {
    private int product = 0;
    Lock locker;
    Condition condition;
    final int SLEEP_TIME = 1000;
    int NEW_GOODS_QUANTITY = 3;//производитель добавляет заданное количество товара за раз

    public Store() {
        locker = new ReentrantLock(true); // создаем блокировку
        condition = locker.newCondition(); // получаем условие, связанное с блокировкой
    }

    //покупка товара
    public synchronized void get() {
        try {
            locker.lockInterruptibly();
            while (product < 1) {//если на складе нет товаров
                System.out.println(Thread.currentThread().getName() + ": приходит на склад. А где весь товар? (Товаров на складе: " + product + ")");
                condition.signal();
                condition.await();
            }
            product--;
            System.out.println(Thread.currentThread().getName() + ": купил 1 товар. (Товаров на складе: " + product + ")");
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            locker.unlock();
        }
    }

    //добавить товары на склад
    public void put() {
        try {
            locker.lockInterruptibly();
            Thread.sleep(SLEEP_TIME);
            while (product > 0) {
                condition.await();
            }
            product = product + NEW_GOODS_QUANTITY;
            System.out.println(Thread.currentThread().getName() + ": добавил 3 товара. (Товаров на складе: " + product + ")");
            condition.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            locker.unlock();
        }
    }
}
