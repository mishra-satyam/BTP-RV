package net.vatri.ecommerce.aspect;

import net.vatri.ecommerce.cart.CartItem;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

@Aspect
@Component
public class MyAspect {

    DataOutputStream outputStream;

    public MyAspect() throws IOException {
        Socket s = new Socket("localhost",6666);
        System.out.println("connection to the server made");
        outputStream = new DataOutputStream(s.getOutputStream());
    }

    @AfterThrowing("execution(* net.vatri.ecommerce.cart.CartServiceImpl.addProduct(*, *))")
    public void afterThrowing(JoinPoint joinPoint) throws IOException {
        String msgString = "";
//        System.out.println("Method: " + joinPoint.toString());
        msgString += "Method: " + joinPoint.toString() + "\n";
//        System.out.println("args: ");
        msgString += "args: " + "\n";
        for (Object o:
                joinPoint.getArgs()) {
//            System.out.println(o.toString());
            if (o instanceof CartItem) {
                CartItem cartItem = (CartItem) o;
                msgString += "product Id: " + cartItem.getProductId() + "\n";
                msgString += "quantity Id: " + cartItem.getQuantity() + "\n";
                msgString += "variant Id: " + cartItem.getVariantId() + "\n";
            }
            else {
                msgString += o.toString() + "\n";
            }
        }
        outputStream.writeUTF(msgString);
        outputStream.flush();
    }

    @Before("execution(* net.vatri.ecommerce.cart.CartService.addProduct(*, *))")
    public void printBefore() throws IOException {
//        System.out.println("MY FUNCTION STARTED .......");
        outputStream.writeUTF("MY FUNCTION STARTED .......");
        outputStream.flush();
    }
}
