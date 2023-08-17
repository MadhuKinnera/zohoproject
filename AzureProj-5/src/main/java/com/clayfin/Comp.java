/*
 * package com.clayfin;
 * 
 * import org.apache.catalina.Manager; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.boot.CommandLineRunner; import
 * org.springframework.stereotype.Component;
 * 
 * import com.clayfin.model.Employee; import com.clayfin.model.Role; import
 * com.clayfin.model.User; import com.clayfin.repository.UserRepo;
 * 
 * @Component public class Comp implements CommandLineRunner {
 * 
 * @Autowired UserRepo userRepo;
 * 
 * @Override public void run(String... args) throws Exception { loadUserData();
 * }
 * 
 * private void loadUserData() { if (userRepo.count() == 0) { User manager = new
 * User((Integer) 1, "kathiresan", "kathiresan@gmail.com", "password",
 * "ROLE_MANAGER"); userRepo.save(manager);
 * 
 * }
 * 
 * if (employeeRepo.count() == 0) { Manager manager =
 * managerRepo.findById(1).get(); System.out.println(manager.getId()); Role role
 * = new Role((Integer) 1, "admin,user", "ADMIN"); Employee emp1 = new
 * Employee((Integer) 1, "karun", "kkarun711@gmail.com", "kumar", "ROLE_EMP",
 * manager);
 * 
 * employeeRepo.save(emp1); System.out.println("employees count " +
 * employeeRepo.count()); System.out.println("Managers count " +
 * managerRepo.count());
 * 
 * } } }
 */