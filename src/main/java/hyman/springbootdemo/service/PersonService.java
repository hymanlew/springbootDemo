package hyman.springbootdemo.service;

import hyman.springbootdemo.dao.PersonDaoAnotation;
import hyman.springbootdemo.entity.Person;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@CacheConfig(cacheNames = "person")
public class PersonService {

    @Resource(name = "personDaoAnotaion")
    private PersonDaoAnotation personDaoAnotation;

    @Cacheable(key = "#id",condition = "#id>0",unless = "#result == null")
    public Person getById(Integer id) {
        return personDaoAnotation.getOne(id);
    }
}
