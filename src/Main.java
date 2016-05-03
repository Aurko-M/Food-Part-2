
import java.util.ArrayList;
import java.util.logging.LogManager;



public class Main {

	public static void main(String[] args) {
		/*
		 * Testing client Client(name, age, wieght)
		 */
		Client johnGuy = new Client();
		Client someGuy = new Client("someGuy", 99, 999.9);

		Mediator m = new Mediator();
  
		// LogManager logManager = new LogManager();
		/*
		 * Testing food Food(id, name, calories, carbs, fat, protein)
		 */
		
		m.createFood('a', "Ham", 100, 10, 25, 150);
		m.createFood('b', "Cheese", 100, 10, 25, 150);
		m.createFood('c', "Bread", 150, 10, 25, 200);
		m.createFood('d', "Beef", 25, 25, 10, 0);
		m.createFood('e', "chicken", 25, 25, 10, 0);
		
		
		m.createRecipe('z', "Ham Sandwich", "a1b2c3d4");
		
		m.addExercise("walk", "4.5MPH", 7);
		m.addExercise("Bike", "15MPH", 15);
		m.addExercise("Jump", "mod", 5);
		m.addExercise("Run", "intense", 12);
		
		for(int i = 0;i<m.getConsumables().size(); i++){
			System.out.println(m.getConsumables().get(i));
		}
		Log log = new Log();
        //log.BufReader();

        try {
			GUIManager frame = new GUIManager(m);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
//		System.out.println("Is ham a food? " + ham.isAFood());
//		System.out.println("Is ham a recipe? " + ham.isARecipe());
//		System.out.println("Is ham same as pork " + pork.equals(ham));
//		System.out.println("Is ham same as beef " + ham.equals(beef));
//		System.out.println("Is client same food " + someGuy.equals(ham));
//		System.out.println(johnGuy.toString());
//		System.out.println(someGuy.toString());
//		System.out.println(beef.toString());
//		System.out.println(pork.toString());
//		System.out.println(ham.toString());

		// logManager.log.write("f",ham.toString());
	}

}
