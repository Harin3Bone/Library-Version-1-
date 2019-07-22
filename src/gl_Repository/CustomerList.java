package gl_Repository;

import gl_Object.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CustomerList {
    // Create variable
    private List<Customer> customers = new ArrayList<>();

    // Getter & Setter
    public List<Customer> getCustomers() {

        return customers;
    }

    public void setCustomers(List<Customer> customers) {

        this.customers = customers;
    }

    // Example customer data
    public void DataCustomerList (CustomerList customers){
        customers.getCustomers().add(new Customer(UUID.randomUUID(),"Honoka","Kousaka","1","1"));
        customers.getCustomers().add(new Customer(UUID.randomUUID(),"Umi","Sonoda","2","2"));
        customers.getCustomers().add(new Customer(UUID.randomUUID(),"Kotori","Minami","3","3"));
        customers.getCustomers().add(new Customer(UUID.randomUUID(),"Hanayo","Koizumi","4","4"));
        customers.getCustomers().add(new Customer(UUID.randomUUID(),"Rin","Hoshizora","5","5"));
        customers.getCustomers().add(new Customer(UUID.randomUUID(),"Maki","Nishikino","6","6"));
        customers.getCustomers().add(new Customer(UUID.randomUUID(),"Nico","Yazawa","7","7"));
        customers.getCustomers().add(new Customer(UUID.randomUUID(),"Nozomi","Tojo","8","8"));
        customers.getCustomers().add(new Customer(UUID.randomUUID(),"Eli","Ayase","9","9"));
    }

}
