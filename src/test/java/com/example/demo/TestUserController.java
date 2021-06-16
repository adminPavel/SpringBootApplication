//package com.example.demo;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.hamcrest.Matchers.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import static org.mockito.BDDMockito.given;
//
//import com.example.demo.dto.UserDto;
//import com.example.demo.entity.User;
//import com.example.demo.repository.MapRepositoryUser;
//import com.example.demo.repository.UserService;
//import com.example.demo.service.UserConverter;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import lombok.NonNull;
//import org.junit.jupiter.api.Test;
//
//import org.mockito.BDDMockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.*;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@NoArgsConstructor
//public class TestUserController {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private UserService userService;
//
//    @MockBean
//    private MapRepositoryUser mapRepositoryUser;
//
//    @MockBean
//    private UserConverter userConverter;
//
//    @Test
//    public void givenEmployees_whenGetEmployees_thenReturnJsonArray() throws Exception {
//        System.out.println(UUID.randomUUID().toString() + "@example.com" +"-------------------------------------");
//        User alex = new User("123", "123", "123213", "12345", "123456@gmail.com");
//
//        List<User> allEmployees = Arrays.asList(alex);
//        List<UserDto> usersDto = userConverter.fromUserListToUserDtoList(allEmployees);
//        System.out.println("---------------------------------------------------------------------");
//        System.out.println("usersDto.size(): " + usersDto.size());
//        System.out.println("allEmployees.size(): " + allEmployees.size());
//        for (int i = 0; i < usersDto.size(); i++) {
//            System.out.println("User Alex: " + usersDto.get(i));
//        }
//        System.out.println("---------------------------------------------------------------------");
//
//        given(mapRepositoryUser.findAll()).willReturn(allEmployees);
//        System.out.println("1-----------------------------");
//        mockMvc.perform(get("/users/findAll")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].id", is(alex.getId())))
//                .andExpect(jsonPath("$[0].name", is(alex.getName())));
//
//    }
//
//    @NonNull
//    private User getUser() {
//        User alex = new User();
//        alex.setName("Alex");
//        alex.setSurname("Ivanov");
//        alex.setEmail("12345@gmail.com");
//        alex.setLogin("fjsdfjsdfnjsdf");
//        alex.setPassword("1234567");
//        return alex;
//    }
//}
//
//
