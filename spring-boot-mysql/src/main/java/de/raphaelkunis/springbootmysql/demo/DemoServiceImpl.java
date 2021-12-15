package de.raphaelkunis.springbootmysql.demo;

import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl implements DemoService {

    @Override
    public String emptyPage() {
        return "";
    }    

    @Override
    public String sayHello() {
        return "Hello";
    }
}
