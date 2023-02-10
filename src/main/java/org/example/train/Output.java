package org.example.train;

import java.util.ArrayList;
import java.util.List;

public class Output {
   private List<Customer> customerSummaries=new ArrayList<>();

    public List<Customer> getCustomerSummaries() {
        return customerSummaries;
    }

    public void setCustomerSummaries(List<Customer> customerSummaries) {
        this.customerSummaries = customerSummaries;
    }
}
