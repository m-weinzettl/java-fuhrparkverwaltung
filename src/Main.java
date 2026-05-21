
import Controller.ParkManager;
import Model.VehicleFactory;
import View.Menu;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

class Main{
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("FuhrparkverwaltungDB");
        EntityManager entityManager = entityManagerFactory.createEntityManager();


        Menu menu = new Menu(new ParkManager(entityManager, new VehicleFactory()));
        menu.showMenu(new VehicleFactory(), entityManager);

        entityManager.close();
        entityManagerFactory.close();
    }
}