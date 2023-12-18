package org.goit.criteria;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.goit.entities.Ownership;
import org.goit.entities.Person;

import java.util.List;

public class CriteriaExecutor implements AutoCloseable {
    private final EntityManagerFactory factory = Persistence.createEntityManagerFactory("hw9");
    EntityManager manager = factory.createEntityManager();

    public void getNeededResidents() {

        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Integer> personIdcriteriaQuery = builder.createQuery(Integer.class);

        Root<Ownership> ownershipRoot = personIdcriteriaQuery.from(Ownership.class);

        personIdcriteriaQuery.select(
                        ownershipRoot.get("person").get("id"))
                .groupBy(ownershipRoot.get("person"))
                .having(builder.le(builder.count(ownershipRoot.get("flat")), 1));
        List<Integer> ownersIds = manager.createQuery(personIdcriteriaQuery).getResultList();


        CriteriaQuery<Person> personCriteriaQuery = builder.createQuery(Person.class);

        Root<Person> personRoot = personCriteriaQuery.from(Person.class);
        Predicate predicate = personRoot.get("id").in(ownersIds);
        Predicate predicate1 = builder.equal(personRoot.get("parkingRight"), 1);
        Predicate predicate2 = builder.gt(personRoot.get("residentialFlat").get("id"), 0);

        personCriteriaQuery
                .where(builder.and(predicate, predicate1, predicate2));


        List<Person> resultList = manager.createQuery(personCriteriaQuery).getResultList();
        for (Person person : resultList) {
            System.out.println(person);
        }
        manager.close();
    }

    @Override
    public void close() {
        factory.close();
        manager.close();
    }
}

