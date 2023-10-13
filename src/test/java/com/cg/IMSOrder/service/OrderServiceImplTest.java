package com.cg.IMSOrder.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.cg.IMSOrder.dto.CategoryDto;
import com.cg.IMSOrder.exception.IdNotFoundException;
import com.cg.IMSOrder.exception.IngredientsNotAvailable;
import com.cg.IMSOrder.pojos.Orders;
import com.cg.IMSOrder.repository.OrderRepository;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@ContextConfiguration(classes = {OrderServiceImpl.class})
@ExtendWith(SpringExtension.class)
class OrderServiceImplTest {
    @MockBean
    private OrderRepository orderRepository;

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @MockBean
    private RestTemplate restTemplate;

    /**
     * Method under test: {@link OrderServiceImpl#addOrder(Orders)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddOrder() throws IdNotFoundException, IngredientsNotAvailable, RestClientException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.http.ResponseEntity.getBody()" because "responseCategory" is null
        //       at com.cg.IMSOrder.service.OrderServiceImpl.addOrder(OrderServiceImpl.java:34)
        //   See https://diff.blue/R013 to resolve this issue.

        when(restTemplate.getForEntity(Mockito.<String>any(), Mockito.<Class<CategoryDto>>any(), isA(Object[].class)))
                .thenReturn(null);
        orderServiceImpl.addOrder(new Orders(1, "42", "42", "Customer Name"));
    }

    /**
     * Method under test: {@link OrderServiceImpl#addOrder(Orders)}
     */
    @Test
    void testAddOrder2() throws IdNotFoundException, IngredientsNotAvailable, RestClientException {
        ResponseEntity<CategoryDto> responseEntity = mock(ResponseEntity.class);
        when(responseEntity.getBody()).thenReturn(new CategoryDto(1, "Category Name"));
        when(restTemplate.getForEntity(Mockito.<String>any(), Mockito.<Class<CategoryDto>>any(), isA(Object[].class)))
                .thenReturn(responseEntity);
        assertThrows(IdNotFoundException.class,
                () -> orderServiceImpl.addOrder(new Orders(1, "42", "42", "Customer Name")));
        verify(restTemplate, atLeast(1)).getForEntity(Mockito.<String>any(), Mockito.<Class<Object>>any(),
                isA(Object[].class));
        verify(responseEntity, atLeast(1)).getBody();
    }

    /**
     * Method under test: {@link OrderServiceImpl#addOrder(Orders)}
     */
    @Test
    void testAddOrder3() throws IdNotFoundException, IngredientsNotAvailable, RestClientException {
        ResponseEntity<CategoryDto> responseEntity = mock(ResponseEntity.class);
        when(responseEntity.getBody()).thenReturn(null);
        when(restTemplate.getForEntity(Mockito.<String>any(), Mockito.<Class<CategoryDto>>any(), isA(Object[].class)))
                .thenReturn(responseEntity);
        assertThrows(IdNotFoundException.class,
                () -> orderServiceImpl.addOrder(new Orders(1, "42", "42", "Customer Name")));
        verify(restTemplate, atLeast(1)).getForEntity(Mockito.<String>any(), Mockito.<Class<Object>>any(),
                isA(Object[].class));
        verify(responseEntity, atLeast(1)).getBody();
    }

    /**
     * Method under test: {@link OrderServiceImpl#addOrder(Orders)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddOrder4() throws IdNotFoundException, IngredientsNotAvailable, RestClientException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.cg.IMSOrder.pojos.Orders.getCategoryId()" because "orders" is null
        //       at com.cg.IMSOrder.service.OrderServiceImpl.addOrder(OrderServiceImpl.java:32)
        //   See https://diff.blue/R013 to resolve this issue.

        ResponseEntity<CategoryDto> responseEntity = mock(ResponseEntity.class);
        when(responseEntity.getBody()).thenReturn(new CategoryDto(1, "Category Name"));
        when(restTemplate.getForEntity(Mockito.<String>any(), Mockito.<Class<Object>>any(), isA(Object[].class)))
                .thenReturn(null);
        when(restTemplate.getForEntity(Mockito.<String>any(), Mockito.<Class<CategoryDto>>any(), isA(Object[].class)))
                .thenReturn(responseEntity);
        orderServiceImpl.addOrder(null);
    }

    /**
     * Method under test: {@link OrderServiceImpl#updateOrder(Orders)}
     */
    @Test
    void testUpdateOrder() throws IdNotFoundException {
        Orders orders = new Orders(1, "42", "42", "Customer Name");

        when(orderRepository.save(Mockito.<Orders>any())).thenReturn(orders);
        when(orderRepository.findById(Mockito.<Integer>any()))
                .thenReturn(Optional.of(new Orders(1, "42", "42", "Customer Name")));
        assertSame(orders, orderServiceImpl.updateOrder(new Orders(1, "42", "42", "Customer Name")));
        verify(orderRepository).save(Mockito.<Orders>any());
        verify(orderRepository).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link OrderServiceImpl#updateOrder(Orders)}
     */
    @Test
    void testUpdateOrder2() throws IdNotFoundException {
        when(orderRepository.findById(Mockito.<Integer>any())).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class,
                () -> orderServiceImpl.updateOrder(new Orders(1, "42", "42", "Customer Name")));
        verify(orderRepository).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link OrderServiceImpl#updateOrder(Orders)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateOrder3() throws IdNotFoundException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.cg.IMSOrder.pojos.Orders.getOrderId()" because "orders" is null
        //       at com.cg.IMSOrder.service.OrderServiceImpl.updateOrder(OrderServiceImpl.java:67)
        //   See https://diff.blue/R013 to resolve this issue.

        when(orderRepository.save(Mockito.<Orders>any())).thenReturn(new Orders(1, "42", "42", "Customer Name"));
        when(orderRepository.findById(Mockito.<Integer>any()))
                .thenReturn(Optional.of(new Orders(1, "42", "42", "Customer Name")));
        orderServiceImpl.updateOrder(null);
    }

    /**
     * Method under test: {@link OrderServiceImpl#getOrder(int)}
     */
    @Test
    void testGetOrder() throws IdNotFoundException {
        Orders orders = new Orders(1, "42", "42", "Customer Name");

        when(orderRepository.findById(Mockito.<Integer>any())).thenReturn(Optional.of(orders));
        assertSame(orders, orderServiceImpl.getOrder(1));
        verify(orderRepository).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link OrderServiceImpl#getOrder(int)}
     */
    @Test
    void testGetOrder2() throws IdNotFoundException {
        when(orderRepository.findById(Mockito.<Integer>any())).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class, () -> orderServiceImpl.getOrder(1));
        verify(orderRepository).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link OrderServiceImpl#deleteOrder(int)}
     */
    @Test
    void testDeleteOrder() throws IdNotFoundException {
        doNothing().when(orderRepository).delete(Mockito.<Orders>any());
        Orders orders = new Orders(1, "42", "42", "Customer Name");

        when(orderRepository.findById(Mockito.<Integer>any())).thenReturn(Optional.of(orders));
        assertSame(orders, orderServiceImpl.deleteOrder(1));
        verify(orderRepository).findById(Mockito.<Integer>any());
        verify(orderRepository).delete(Mockito.<Orders>any());
    }

    /**
     * Method under test: {@link OrderServiceImpl#deleteOrder(int)}
     */
    @Test
    void testDeleteOrder2() throws IdNotFoundException {
        when(orderRepository.findById(Mockito.<Integer>any())).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class, () -> orderServiceImpl.deleteOrder(1));
        verify(orderRepository).findById(Mockito.<Integer>any());
    }
}

