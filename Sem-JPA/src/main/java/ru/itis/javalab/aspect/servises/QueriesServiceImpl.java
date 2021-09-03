package ru.itis.javalab.aspect.servises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.models.Query;
import ru.itis.javalab.repositories.QueriesRepository;

@Service
public class QueriesServiceImpl implements QueriesService{
    @Autowired
    QueriesRepository queriesRepository;

    @Override
    public void IncreaseNumberOfUsing(String query) {
        Query query1 = queriesRepository.findQueryByQuery(query);
        query1 = (query1!=null)? query1 : Query.builder().query(query).number(0L).build();
        query1.setNumber(query1.getNumber()+1);
        queriesRepository.save(query1);
    }
}
