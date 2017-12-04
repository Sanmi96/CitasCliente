package com.sanmi.citasClientes.hibernate.dao;

import java.util.List;

import com.sanmi.citasClientes.hibernate.bean.Cita;

public class CitaDAO extends AbstractDao {

    public CitaDAO() {
        super();
    }

    /**
     * Insert a new Event into the database.
     * 
     * @param event
     */
    public void create(Cita cita) throws DataAccessLayerException {
        super.saveOrUpdate(cita);
    }

    /**
     * Delete a detached Event from the database.
     * 
     * @param event
     */
    public void delete(int id) throws DataAccessLayerException {
        super.delete(Cita.class, id);
    }

    /**
     * Find an Event by its primary key.
     * 
     * @param id
     * @return
     */
    public Cita find(Long id) throws DataAccessLayerException {
        return (Cita) super.find(Cita.class, id);
    }

    /**
     * Updates the state of a detached Event.
     *
     * @param event
     */
    public void update(Cita cita) throws DataAccessLayerException {
        super.saveOrUpdate(cita);
    }

    /**
     * Finds all Events in the database.
     * 
     * @return
     */
    public List findAll() throws DataAccessLayerException {
        return super.findAll(Cita.class);
    }
    
    public List findCitesOrderedByData(int i) {
        return super.findCitesOrderedByData(Cita.class, i);
    }


    public List findAllCitesOrderedByData(String[] s) {
        return super.findAllCitesOrderedByData(Cita.class, s);
    }

}
