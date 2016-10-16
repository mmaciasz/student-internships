package pl.com.pollub.db.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by mateusz on 16.10.16.
 */
@Entity
public class MyEntity {

    @Id
    private Integer id;

    /**
     * Default constructor is necessary for all entity classes
     */
    public MyEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
