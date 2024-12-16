package com.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Supermarket {

    //是否优惠
    private static final String IS_DISCOUNT = "1";


    //是否超过100优惠计算
    public static void discount(Consumer consumer) {
        if (consumer != null) {
            if (IS_DISCOUNT.equals(consumer.getIsDiscount())) {
                BigDecimal originalTotal = consumer.getTotal();
                if (originalTotal.compareTo(new BigDecimal(100)) >= 0) {
                    BigDecimal multiple = originalTotal.divide(new BigDecimal(100), 0, BigDecimal.ROUND_DOWN);
                    BigDecimal subCount = multiple.multiply(new BigDecimal(10));
                    originalTotal = originalTotal.subtract(subCount);
                    consumer.setTotal(originalTotal);
                }
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
        discount(consumer);
        System.out.println("顾客" + consumer.getConsumerName() + "购买水果总计: " + consumer.getTotal() + " 元");
    }

    //客户购买商品
    public static void consumerBuyItems(Consumer consumer, int quantity, int price, BigDecimal disc, String name) {
        BigDecimal discount = new BigDecimal(0);
        if (disc != null) {
            if (disc.compareTo(new BigDecimal(10)) < 0 && disc.compareTo(new BigDecimal(0)) > 0) {
                discount = disc.divide(new BigDecimal(10), 2, BigDecimal.ROUND_DOWN);
            }
        }
        consumer.addItem(new Item(quantity, price, discount, name));
    }

    //A顾客买了10斤苹果10斤草莓
    public static void consumerATotalPrice() {
        Consumer consumer = new Consumer("A", "0");
        consumerBuyItems(consumer, 10, 8, new BigDecimal(0), "Apple");
        consumerBuyItems(consumer, 10, 13, new BigDecimal(0), "Strawberry");
        calculateTotal(consumer);
    }

    //B顾客买了10斤苹果10斤草莓10斤芒果
    public static void consumerBTotalPrice() {
        Consumer consumer = new Consumer("B", "0");
        consumerBuyItems(consumer, 10, 8, new BigDecimal(0), "Apple");
        consumerBuyItems(consumer, 10, 13, new BigDecimal(0), "Strawberry");
        consumerBuyItems(consumer, 10, 20, new BigDecimal(0), "Mango");
        calculateTotal(consumer);
    }

    //C客户买了10斤苹果10斤草莓10斤芒果
    public static void consumerCTotalPrice() {
        Consumer consumer = new Consumer("C", "0");
        consumerBuyItems(consumer, 10, 8, new BigDecimal(0), "Apple");
        consumerBuyItems(consumer, 10, 13, new BigDecimal(8), "Strawberry");
        consumerBuyItems(consumer, 10, 20, new BigDecimal(0), "Mango");
        calculateTotal(consumer);
    }

    //D客户买了10斤苹果120斤草莓10斤芒果
    public static void consumerDTotalPrice() {
        Consumer consumer = new Consumer("D", "1");
        consumerBuyItems(consumer, 10, 8, new BigDecimal(0), "Apple");
        consumerBuyItems(consumer, 10, 13, new BigDecimal(8), "Strawberry");
        consumerBuyItems(consumer, 10, 20, new BigDecimal(0), "Mango");
        calculateTotal(consumer);
    }

    public static void main(String[] args) {
        //A顾客买了10斤苹果10斤草莓
        consumerATotalPrice();
        //B顾客买了10斤苹果10斤草莓10斤芒果
        consumerBTotalPrice();
        //C客户买了10斤苹果10斤草莓10斤芒果
        consumerCTotalPrice();
        //D客户买了10斤苹果120斤草莓10斤芒果
        consumerDTotalPrice();
    }

    public static class Consumer {

        private BigDecimal total;

        private String consumerName;

        private String isDiscount;
        private List<Item> items = new ArrayList<>();

        public Consumer(String consumerName, String isDiscount) {
            this.consumerName = consumerName;
            this.isDiscount = isDiscount;
            this.total = BigDecimal.ZERO;
        }

        public String getIsDiscount() {
            return isDiscount;
        }

        public void setIsDiscount(String isDiscount) {
            this.isDiscount = isDiscount;
        }

        public String getConsumerName() {
            return consumerName;
        }

        public void setConsumerName(String consumerName) {
            this.consumerName = consumerName;
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

        public void setTotal(BigDecimal total) {
            this.total = total;
        }

        //添加购买水果种类
        public void addItem(Item item) {
            items.add(item);
        }

        public void addProduct(BigDecimal price) {
            this.total = this.total.add(price);
        }
    }


    public static class Item {

        private int quantity;
        private int price;
        private BigDecimal discount;
        private String name;

        public Item(int quantity, int price, BigDecimal discount, String name) {
            this.quantity = quantity;
            this.price = price;
            this.discount = discount;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

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