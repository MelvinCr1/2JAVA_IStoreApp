package IStoreApp.service;

import IStoreApp.model.Employee;
import IStoreApp.dataAccess.EmployeeAccess;

public class EmployeeManager {
    private static final EmployeeAccess employeeAccess = new EmployeeAccess();

    // Méthode pour créer un nouvel employé
    public static void createEmployee(Employee employee){
        // Vérification si l'employé existe déja
        if (getEmployeeById(employee.getId()) != null){
            System.out.println("Erreur : Un employé avec cet ID existe déja.");
            return;
        }
        IStoreApp.dataAccess.EmployeeAccess.createEmployee(employee);
        System.out.println("Employé créé avec succès !");
    }

    public static Employee getEmployeeById(int employeeId){
        return IStoreApp.dataAccess.EmployeeAccess.getEmployeeById(employeeId);
    }

    // Méthode pour mettre à jours un employé
    public static void updateEmployee(Employee employee){
        Employee existingEmployee = getEmployeeById(employee.getId());
        if (existingEmployee == null){
            System.out.println("Erreur : Cet employé n'existe pas.");
            return;
        }
        employeeAccess.updateEmployee(employee);
        System.out.println("Employé mis à jour avec succès !");
    }

    // Méthode pour supprimer un employé
    public static void deleteEmployee(Employee employee){
        Employee existingEmployee = getEmployeeById(employee.getId());
        if (existingEmployee == null){
            System.out.println("Erreur : Cet employé n'existe pas");
            return;
        }
        employeeAccess.deleteEmployee(employee);
        System.out.println("Employé supprimé avec succès !");
    }
}
