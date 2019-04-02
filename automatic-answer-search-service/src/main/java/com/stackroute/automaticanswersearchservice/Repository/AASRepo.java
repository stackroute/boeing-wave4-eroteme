package com.stackroute.automaticanswersearchservice.Repository;

import com.stackroute.automaticanswersearchservice.model.Question;

import java.util.List;

public interface AASRepo {

    void save(List<Question> question);

    List<Question> findAll();

}
