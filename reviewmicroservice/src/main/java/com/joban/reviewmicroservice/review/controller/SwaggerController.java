package com.joban.reviewmicroservice.review.controller;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class SwaggerController {
    // to reterive the value of context path of api
    @Value("${server.servlet.context-path}")
    private String contextPath;
    // redirect to swagger ui on a request to home page
    @Hidden //to default routes
    @RequestMapping(value = "/") // here / means ${server.servlet.context-path}
    public void redirect(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.sendRedirect(contextPath+"/swagger-ui/index.html");
    }
}
