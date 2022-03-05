package edu.ucalgary.ensf409;

public class MyMain {
    public static void main(String[] args) {
        System.out.println("-------------Testing EmergVet-------------");
        EmergVet vet1 = new EmergVet("Sally Jones", "4031231234");
        System.out.println(vet1.getName() + "\t" + vet1.getPhoneNum());

        Client owner1 = new Client("Joe Smith", "5879998888", "123 Main Street SW");

        {
        Pet pet1 = new Pet("Fluffy", "cat", "Maine coon", "orange", owner1);
        pet1.setVet(vet1);
        System.out.println(pet1.getVet().getName() + "\t" + pet1.getVet().getPhoneNum());

        System.out.println();

        System.out.println("-------------Testing Pet/Owner-------------");
        Client pet1Owner = pet1.getOwner();
        System.out.println(pet1Owner.getName() + "\t" + pet1Owner.getPhoneNumber() + "\t" + pet1Owner.getAddress());
        }

        System.out.println(owner1.getName() + "\t" + owner1.getPhoneNumber() + "\t" + owner1.getAddress());
        System.out.println();

        System.out.println("-------------Testing Client/Rewards-------------");
        System.out.println(owner1.getRewardsNumber());
        System.out.println(owner1.enrollRewards("12345"));
        System.out.println(owner1.getRewardsPoints());
        owner1.updatePoints(15);
        System.out.println(owner1.getRewardsPoints());
        System.out.println();

        System.out.println("-------------Testing Employee/Pet-------------");
        Pet pet2 = new Pet("Spotty", "dog", "Golden Retriever", "yellow", owner1);
        Employee employee1 = new Employee("Mary Jane", "5678");
        Booking booking1 = new Booking(pet2, employee1, "Feb14", "Feb20");
        System.out.println(booking1.getCaregiver().getName());
        System.out.println(booking1.getBookedPet().getName());
        System.out.println();

        System.out.println("-------------Testing ReportCard-------------");
        ReportCard report = new ReportCard(booking1);
        System.out.println(report.printReport());
        System.out.println();

        System.out.println("-------------Testing Care Profile-------------");
        String[] med1 = {"pulmacort", "omnaris"};
        pet2.setCare(med1, "Take each once a day", "feed twice a day");
        System.out.println(pet2.getCareSummary());
        System.out.println();
        
        System.out.println("-------------Testing Employee-------------");
        Employee manager1 = new Employee("Suzy Smith", "2834");
        Employee employee2 = new Employee("James Park", "01892", manager1.getIDNumber());
        System.out.println(employee1.getManagerID());
        System.out.println(employee2.getManagerID());
        manager1.addEmployee(employee1);
        manager1.addEmployee(employee2);
        Employee[] array = manager1.getEmployees();

        for(Employee each: array) {
            if(each != null) {
                System.out.print(each.getName() + "\t");
            }
        }
    }
    
}
