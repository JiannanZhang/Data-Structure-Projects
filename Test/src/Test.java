import java.util.ArrayList;

class Animal {
	public int Age = 0;
	int weight = 10;
    public Animal (int age, int weight) {
    	Age = age;
    }

    public void breathe()
    {
        System.out.println("animal breathes");
    }
}

class Pig extends Animal {

    public Pig(int age,int weight) {
    	super(age,weight);
    }
    public void breathe()
    {
        System.out.println("pig breathes");
    }
    public int getAge(){
    	return Age;
    }
    
}

class Bird extends Animal {

    public Bird() {

    }
}
public class Test {
   public static void main(String[] args) {
       /* Animal animal = new Bird(); */
       /* animal.breathe(); */
	   Bird bird1 = new Bird();
       bird1.breathe();
       Animal pig1 = new Pig(0,8);
       pig1.breathe();
       
   }
}
