package ro.axonsoft.internship.main;

public class A {
    public A(){
        System.out.print("A");
    }
    public void something(){
        try{
            throw new RuntimeException("thrown");
        }catch (RuntimeException e){
            System.out.println("caught something");
            System.exit(0);
        }
        finally {
            System.out.println("Here in finally");
        }
    }
}
