package com.hit.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Classname Cart
 * @Description TODO
 * @Author DELL
 * @Date 2021/7/31 16:00
 */
public class Cart {
    //     private Integer totalCount;
//     private BigDecimal totalPrice;
    //key是商品编号，value是商品信息
    private Map<Integer, CartItem> items = new LinkedHashMap<Integer, CartItem>();

    public Cart() {
    }

    public Cart(Map<Integer, CartItem> items) {
//        this.totalCount = totalCount;
//        this.totalPrice = totalPrice;
        this.items = items;
    }

    public Integer getTotalCount() {
        Integer totalCount = 0;
        for (Map.Entry<Integer, CartItem> integerCartItemEntry : items.entrySet()) {
            totalCount += integerCartItemEntry.getValue().getCount();
        }
        return totalCount;
    }


    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);

        for (Map.Entry<Integer, CartItem> integerCartItemEntry : items.entrySet()) {
            totalPrice = totalPrice.add(integerCartItemEntry.getValue().getTotalPrice());

        }

        return totalPrice;
    }


    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }

    /**
     * @Author DELL
     * @Description 添加商品项
     * @Date 2021/7/31 16:02
     * @Param []
     * @Return void
     * @Version 1.0
     */
    public void addItem(CartItem cartItem) {
        // 先查看购物车中是否已经添加过此商品，如果已添加，则数量累加，总金额更新，如果没有添加过，直接放到 集合中即可
        CartItem cartItem1 = items.get(cartItem.getId());

        if (cartItem1 == null) {
            //之前没添加过
            items.put(cartItem.getId(), cartItem);
        } else {
            //已经添加过的情况
            cartItem1.setCount(cartItem1.getCount() + 1);
            cartItem1.setTotalPrice(cartItem1.getPrice().multiply(new BigDecimal(cartItem1.getCount()))); //更新总金额
        }

    }

    /**
     * @Author DELL
     * @Description 删除商品项
     * @Date 2021/7/31 16:03
     * @Param [cartItem]
     * @Return void
     * @Version 1.0
     */
    public void deleteItem(Integer id) {
        items.remove(id);
    }

    /**
     * @Author DELL
     * @Description 清空购物车
     * @Date 2021/7/31 16:04
     * @Param []
     * @Return void
     * @Version 1.0
     */
    public void clear() {
        items.clear();
    }

    /**
     * @Author DELL
     * @Description 修改商品数量
     * @Date 2021/7/31 16:04
     * @Param [id, count]
     * @Return void
     * @Version 1.0
     */
    public void updateCount(Integer id, Integer count) {
        // 先查看购物车中是否有此商品。如果有，修改商品数量，更新总金额
        CartItem cartItem = items.get(id);
        if (cartItem != null) {
            cartItem.setCount(count);// 修改商品数量
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount()))); // 更新总金额
        }
    }

}
