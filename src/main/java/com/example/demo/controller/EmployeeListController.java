//package com.example.demo.controller;
//
//import com.example.demo.controller.UserController;
//import com.example.demo.repository.MapRepositoryUser;
//import com.example.demo.repository.UserService;
//import lombok.Data;
//import org.springframework.web.servlet.ModelAndView;
//import javax.servlet.http.*;
//import java.util.List;
//
//public class EmployeeListController extends UserController {
//    private UserService ;
//    public static final String MAP_KEY = "employeelist";
//    private ApplicationSecurityManager applicationSecurityManager;
//    private final int CLINIC_ID = 1;
//    private String successView;
//
//    public EmployeeListController(UserService userService, MapRepositoryUser mapRepository) {
//        super(userService, mapRepository);
//    }
//
//    public ModelAndView handleRequest(
//            HttpServletRequest request,
//            HttpServletResponse response) throws Exception
//    {
//        Doctors doctors = (Doctors)applicationSecurityManager.getDoctor(request);
//
//        Clinic clinic = doctors.getClinic();
//
//        List<?> employeelist = doctorsManager.getDoctors(clinic.getId());
//        return new ModelAndView(getSuccessView(), MAP_KEY, employeelist);
//    }
//}
