package com.example.demo;

import com.example.demo.Controllers.CryptoController;
import com.example.demo.Controllers.OrderController;
import com.example.demo.Controllers.UserController;
import com.example.demo.services.CryptoService;
import com.example.demo.services.OrderService;
import com.example.demo.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({UserController.class, CryptoController.class, OrderController.class})
public class MainControllersTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private OrderService orderService;

    @MockBean
    private CryptoService cryptoService;

    @Test
    void testGetCrypto() throws Exception {
        mockMvc.perform(get("/crypto/getAll"))
                .andExpect(status().isOk())
                .andExpect(view().name("crypto-list"));
    }
    @Test
    void testAddOrder() throws Exception {
        mockMvc.perform(post("/order/add")
                .param("user", "Roman")
                .param("orderType", "Buy")
                .param("crypto", "BTC")
                .param("amount", "250")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/order/getAll"));
    }
    @Test
    void testGetOrders() throws Exception {
        mockMvc.perform(get("/order/getAll"))
                .andExpect(status().isOk())
                .andExpect(view().name("order-list"));
    }

    @Test
    void testDeleteOrder() throws Exception {
        mockMvc.perform(post("/order/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/order/getAll"));
    }

    @Test
    void testGetUser() throws Exception {
        mockMvc.perform(get("/user/getAll"))
                .andExpect(status().isOk())
                .andExpect(view().name("user-list"));
    }

    @Test
    void testAddUser() throws Exception {
        mockMvc.perform(post("/user/add")
                        .param("username", "Roman Chechinev")
                        .param("password", "11111"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/getAll"));
    }

    @Test
    void testDeleteUser() throws Exception {
        mockMvc.perform(post("/user/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/getAll"));
    }

}

