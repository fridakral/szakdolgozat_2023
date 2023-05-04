package hr.mvc.core;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public abstract class CoreRepository<T> {


    @PersistenceContext
    private EntityManager em;


    public List<T> findAll(){
        return em.createQuery("select n from " + getManagedClass().getSimpleName() + " n", getManagedClass()).getResultList();
    }

    public T save(T entity){


        em.persist(entity);

        return entity;
    }

    public T update(T data){
        return em.merge(data);
    }

    public void delete(Long id){
        em.remove(findById(id));
    }

    public T findById(Long id){
        T entity= (T) em.find(getManagedClass(), id);
        if(entity==null){
            throw new NullPointerException();
        }
        return entity;
    }

    protected abstract Class<T> getManagedClass();
}
