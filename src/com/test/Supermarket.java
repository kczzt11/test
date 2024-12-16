package com.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Supermarket {


    //是否超过100优惠计算
    public static void discount(Consumer consumer) {
        if (consumer != null) {
            BigDecimal originalTotal = consumer.getTotal();
            if (originalTotal.compareTo(new BigDecimal(100)) >= 0) {
                BigDecimal multiple = originalTotal.divide(new BigDecimal(100), 0, BigDecimal.ROUND_DOWN);
                BigDecimal subCount = multiple.multiply(new BigDecimal(10));
                originalTotal = originalTotal.subtract(subCount);
                consumer.setTotal(originalTotal);
            }
        }
    }

    //计算商品总价
    public static void calculateTotal(Consumer consumer) {
        if (consumer != null) {
            for (Item item : consumer.getItems()) {
                BigDecimal quantity = new BigDecimal(item.getQuantity());
                BigDecimal price = new BigDecimal(item.getPrice());
                BigDecimal total = quantity.multiply(price);
                if (item.getDiscount().compareTo(new BigDecimal(0.00)) > 0) {
                    total = total.multiply(item.getDiscount());
                }
                consumer.addProduct(total.setScale(2, BigDecimal.ROUND_HALF_EVEN));
            }
        }
    }

    //A客户总价
    public static void getCustATotalPrice(int appleWeight, int strawberryWeight) {
        Consumer consumer = new Consumer();
        consumer.addItem(new Item(appleWeight, 8, new BigDecimal(0.00)));
        consumer.addItem(new Item(strawberryWeight, 13, new BigDecimal(0.00)));
        calculateTotal(consumer);
        System.out.println("A客户购买水果总计: " + consumer.getTotal() + " 元");
    }

    //B客户总价
    public static void getCustBTotalPrice(int appleWeight, int strawberryWeight, int mangoWeight) {
        Consumer consumer = new Consumer();
        consumer.addItem(new Item(appleWeight, 8, new BigDecimal(0.00)));
        consumer.addItem(new Item(strawberryWeight, 13, new BigDecimal(0.00)));
        consumer.addItem(new Item(mangoWeight, 20, new BigDecimal(0.00)));
        calculateTotal(consumer);
        System.out.println("B客户购买水果总计: " + consumer.getTotal() + " 元");
    }

    //C客户总价
    public static void getCustCTotalPrice(int appleWeight, int strawberryWeight, int mangoWeight) {
        Consumer consumer = new Consumer();
        consumer.addItem(new Item(appleWeight, 8, new BigDecimal(0.00)));
        consumer.addItem(new Item(strawberryWeight, 13, new BigDecimal(0.80)));
        consumer.addItem(new Item(mangoWeight, 20, new BigDecimal(0.00)));
        calculateTotal(consumer);
        System.out.println("C客户购买水果总计: " + consumer.getTotal() + " 元");
    }

    //D客户总价
    public static void getCustDTotalPrice(int appleWeight, int strawberryWeight, int mangoWeight) {
        Consumer consumer = new Consumer();
        consumer.addItem(new Item(appleWeight, 8, new BigDecimal(0.00)));
        consumer.addItem(new Item(strawberryWeight, 13, new BigDecimal(0.80)));
        consumer.addItem(new Item(mangoWeight, 20, new BigDecimal(0.00)));
        calculateTotal(consumer);
        discount(consumer);
        System.out.println("D客户购买水果总计: " + consumer.getTotal() + " 元");
    }

    public static void main(String[] args) {
        //A客户买了10斤苹果20斤草莓
        getCustATotalPrice(10, 20);
        //B客户买了10斤苹果20斤草莓10斤芒果
        getCustBTotalPrice(10, 20, 10);
        //C客户买了10斤苹果20斤草莓10斤芒果
        getCustCTotalPrice(10, 20, 10);
        //D客户买了10斤苹果20斤草莓10斤芒果
        getCustDTotalPrice(10, 20, 10);
    }

    public static class Consumer {

        private BigDecimal total;

        private List<Item> items = new ArrayList<>();

        public void setTotal(BigDecimal total) {
            this.total = total;
        }

        public List<Item> getItems() {
            return items;
        }

        public void setItems(List<Item> items) {
            this.items = items;
        }

        public BigDecimal getTotal() {
            return this.total;
        }

        //添加购买水果种类
        public void addItem(Item item) {
            items.add(item);
        }

        public Consumer() {
            this.total = BigDecimal.ZERO;
        }

        public void addProduct(BigDecimal price) {
            this.total = this.total.add(price);
        }
    }


    public static class Item {

        public Item(int quantity, int price, BigDecimal discount) {
            this.quantity = quantity;
            this.price = price;
            this.discount = discount;
        }

        private int quantity;

        private int price;

        private BigDecimal discount;

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public BigDecimal getDiscount() {
            return discount;
        }

        public void setDiscount(BigDecimal discount) {
            this.discount = discount;
        }
    }
}