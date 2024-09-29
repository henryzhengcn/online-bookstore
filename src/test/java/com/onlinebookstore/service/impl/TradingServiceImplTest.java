package com.onlinebookstore.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.onlinebookstore.common.model.entity.Book;
import com.onlinebookstore.common.model.entity.ShoppingCart;
import com.onlinebookstore.repository.BookRepository;
import com.onlinebookstore.repository.ShoppingCartRepository;
import com.onlinebookstore.service.TradingService;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TradingServiceImplTest {

    @InjectMocks
    TradingService tradingService = new TradingServiceImpl();

    @Mock
    ShoppingCartRepository shoppingCartRepository;

    @Mock
    BookRepository bookRepository;

    @Test
    public void test_checkout_3_shopping_items_3_normal_books() {
        Optional<Book> book101 = Optional.of(new Book(101,"book101","author101",10.1,"history", new Date(),null));
        Optional<Book> book102 = Optional.of(new Book(102,"book102","author102",10.2,"history", new Date(),null));
        Optional<Book> book103 = Optional.of(new Book(103,"book103","author103",10.3,"history", new Date(),null));
        Mockito.when(bookRepository.findById(101)).thenReturn(book101);
        Mockito.when(bookRepository.findById(102)).thenReturn(book102);
        Mockito.when(bookRepository.findById(103)).thenReturn(book103);

        Optional<ShoppingCart> cart201 = Optional.of(new ShoppingCart(201, 101, 11.0, new Date(),null));
        Optional<ShoppingCart> cart202 = Optional.of(new ShoppingCart(202, 102, 12.0, new Date(),null));
        Optional<ShoppingCart> cart203 = Optional.of(new ShoppingCart(203, 103, 13.0, new Date(),null));
        Mockito.when(shoppingCartRepository.findById(201)).thenReturn(cart201);
        Mockito.when(shoppingCartRepository.findById(202)).thenReturn(cart202);
        Mockito.when(shoppingCartRepository.findById(203)).thenReturn(cart203);

        JSONObject result = tradingService.checkout(Arrays.asList(201, 202, 203));

        assertThat("The method result is null", result, CoreMatchers.notNullValue());

        Object totalObj = result.get("Checkout");
        Object shoppingCartsObj = result.get("ShoppingCarts");
        Object invalidShoppingCartsObj = result.get("InvalidShoppingCarts");
        Object boughtGoodsObj = result.get("BoughtGoods");

        assertThat("Checkout result is null", totalObj, CoreMatchers.notNullValue());
        assertThat("Checkout type must be map", totalObj, CoreMatchers.instanceOf(Map.class));
        assertThat("totalQuantity <> 36", ((Map)totalObj).get("totalQuantity"), CoreMatchers.is(36D));
        assertThat("totalAmount <> 367.4", ((Map)totalObj).get("totalAmount"), CoreMatchers.is(367.4D));

        assertThat("Type of ShoppingCarts must be list", shoppingCartsObj, CoreMatchers.instanceOf(List.class));
        assertThat("Quantity of ShoppingCarts <> 3", ((List)shoppingCartsObj).size(), CoreMatchers.is(3));

        assertThat("Type of InvalidShoppingCarts must be list", invalidShoppingCartsObj, CoreMatchers.instanceOf(List.class));
        assertThat("Quantity of InvalidShoppingCarts <> 0", ((List)invalidShoppingCartsObj).size(), CoreMatchers.is(0));

        assertThat("Type of BoughtGoods must be list", boughtGoodsObj, CoreMatchers.instanceOf(List.class));
        assertThat("Quantity of BoughtGoods <> 3", ((List)boughtGoodsObj).size(), CoreMatchers.is(3));
    }

    @Test
    public void test_checkout_3_shopping_items_2_normal_books_1_expired_book() {
        Optional<Book> book101 = Optional.of(new Book(101,"book101","author101",10.1,"history", new Date(),null));
        Optional<Book> book102 = Optional.of(new Book(102,"book102","author102",10.2,"history", new Date(),null));
        Optional<Book> book103 = Optional.of(new Book(103,"book103","author103",10.3,"history", new Date(),null));
        Mockito.when(bookRepository.findById(101)).thenReturn(book101);
        Mockito.when(bookRepository.findById(102)).thenReturn(book102);
        Mockito.when(bookRepository.findById(103)).thenReturn(book103);

        Optional<ShoppingCart> cart201 = Optional.of(new ShoppingCart(201, 101, 11.0, new Date(),null));
        Optional<ShoppingCart> cart202 = Optional.of(new ShoppingCart(202, 102, 12.0, new Date(),null));
        // The book added to the shopping cart has expired, this book is invalid, so it cannot be settled.
        Optional<ShoppingCart> cart203 = Optional.of(new ShoppingCart(203, 103333, 13.0, new Date(),null));
        Mockito.when(shoppingCartRepository.findById(201)).thenReturn(cart201);
        Mockito.when(shoppingCartRepository.findById(202)).thenReturn(cart202);
        Mockito.when(shoppingCartRepository.findById(203)).thenReturn(cart203);

        JSONObject result = tradingService.checkout(Arrays.asList(201, 202, 203));

        assertThat("The method result is null", result, CoreMatchers.notNullValue());

        Object totalObj = result.get("Checkout");
        Object shoppingCartsObj = result.get("ShoppingCarts");
        Object invalidShoppingCartsObj = result.get("InvalidShoppingCarts");
        Object boughtGoodsObj = result.get("BoughtGoods");

        assertThat("Checkout result is null", totalObj, CoreMatchers.notNullValue());
        assertThat("Checkout type must be map", totalObj, CoreMatchers.instanceOf(Map.class));
        assertThat("totalQuantity <> 23", ((Map)totalObj).get("totalQuantity"), CoreMatchers.is(23D));
        assertThat("totalAmount <> 233.5", ((Map)totalObj).get("totalAmount"), CoreMatchers.is(233.5D));

        assertThat("Type of ShoppingCarts must be list", shoppingCartsObj, CoreMatchers.instanceOf(List.class));
        assertThat("Quantity of ShoppingCarts <> 2", ((List)shoppingCartsObj).size(), CoreMatchers.is(2));

        assertThat("Type of InvalidShoppingCarts must be list", invalidShoppingCartsObj, CoreMatchers.instanceOf(List.class));
        assertThat("Quantity of InvalidShoppingCarts <> 1", ((List)invalidShoppingCartsObj).size(), CoreMatchers.is(1));

        assertThat("Type of BoughtGoods must be list", boughtGoodsObj, CoreMatchers.instanceOf(List.class));
        assertThat("Quantity of BoughtGoods <> 2", ((List)boughtGoodsObj).size(), CoreMatchers.is(2));
    }

    @Test
    public void test_checkout_3_shopping_items_0_normal_books_3_expired_book() {
        Optional<Book> book101 = Optional.of(new Book(101,"book101","author101",10.1,"history", new Date(),null));
        Optional<Book> book102 = Optional.of(new Book(102,"book102","author102",10.2,"history", new Date(),null));
        Optional<Book> book103 = Optional.of(new Book(103,"book103","author103",10.3,"history", new Date(),null));
        Mockito.when(bookRepository.findById(101)).thenReturn(book101);
        Mockito.when(bookRepository.findById(102)).thenReturn(book102);
        Mockito.when(bookRepository.findById(103)).thenReturn(book103);

        // All the books added to the shopping cart has expired, so they shouldn't be settled.
        Optional<ShoppingCart> cart201 = Optional.of(new ShoppingCart(201, 101333, 11.0, new Date(),null));
        Optional<ShoppingCart> cart202 = Optional.of(new ShoppingCart(202, 102333, 12.0, new Date(),null));
        Optional<ShoppingCart> cart203 = Optional.of(new ShoppingCart(203, 103333, 13.0, new Date(),null));
        Mockito.when(shoppingCartRepository.findById(201)).thenReturn(cart201);
        Mockito.when(shoppingCartRepository.findById(202)).thenReturn(cart202);
        Mockito.when(shoppingCartRepository.findById(203)).thenReturn(cart203);

        JSONObject result = tradingService.checkout(Arrays.asList(201, 202, 203));

        assertThat("The method result is null", result, CoreMatchers.notNullValue());

        Object totalObj = result.get("Checkout");
        Object shoppingCartsObj = result.get("ShoppingCarts");
        Object invalidShoppingCartsObj = result.get("InvalidShoppingCarts");
        Object boughtGoodsObj = result.get("BoughtGoods");

        assertThat("Checkout result is null", totalObj, CoreMatchers.notNullValue());
        assertThat("Checkout type must be map", totalObj, CoreMatchers.instanceOf(Map.class));
        assertThat("totalQuantity <> 0", ((Map)totalObj).get("totalQuantity"), CoreMatchers.is(0D));
        assertThat("totalAmount <> 0", ((Map)totalObj).get("totalAmount"), CoreMatchers.is(0D));

        assertThat("Type of ShoppingCarts must be list", shoppingCartsObj, CoreMatchers.instanceOf(List.class));
        assertThat("Quantity of ShoppingCarts <> 0", ((List)shoppingCartsObj).size(), CoreMatchers.is(0));

        assertThat("Type of InvalidShoppingCarts must be list", invalidShoppingCartsObj, CoreMatchers.instanceOf(List.class));
        assertThat("Quantity of InvalidShoppingCarts <> 3", ((List)invalidShoppingCartsObj).size(), CoreMatchers.is(3));

        assertThat("Type of BoughtGoods must be list", boughtGoodsObj, CoreMatchers.instanceOf(List.class));
        assertThat("Quantity of BoughtGoods <> 0", ((List)boughtGoodsObj).size(), CoreMatchers.is(0));
    }

}