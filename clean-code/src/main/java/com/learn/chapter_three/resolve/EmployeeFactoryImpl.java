package com.learn.chapter_three.resolve;

/**
 * Create by liguo on 2022/8/16
 **/
public class EmployeeFactoryImpl implements EmployeeFactory{
    @Override
    public Employee makeEmployee(EmployeeRecord r) {
        switch (r.getType()) {
            case COMMISSIONED:
                return new CommissionedEmployee(r);
            case HOURLY:
                return new HourlyEmployee(r);
            case SALARIED:
                return new SalariedEmployee(r);
            default:
                throw new RuntimeException(r.getType().getName());
        }
    }
}
