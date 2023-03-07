package net.vatri.ecommerce.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {
    @AfterThrowing("execution(* net.vatri.ecommerce.cart.CartServiceImpl.addProduct(*, *))")
    public void afterThrowing(JoinPoint joinPoint) {
        System.out.println("Method: " + joinPoint.toString());
        System.out.println("args: ");
        for (Object o:
                joinPoint.getArgs()) {
            System.out.println(o.toString());
        }
    }

//    @Before("execution(* net.vatri.ecommerce.controllers.CartController.addProduct(*, *))")
    @Before("execution(* net.vatri.ecommerce.cart.CartService.addProduct(*, *))")
    public void printBefore() {
        System.out.println("MY FUNCTION STARTED .......");
    }
}
