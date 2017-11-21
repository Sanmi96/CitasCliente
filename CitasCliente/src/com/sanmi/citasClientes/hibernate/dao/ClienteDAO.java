package com.sanmi.citasClientes.hibernate.dao;

import java.util.List;

import com.sanmi.citasClientes.hibernate.bean.Cliente;

public class ClienteDAO extends AbstractDao {

    public ClienteDAO() {
        super();
    }

    /**
     * Insert a new Event into the database.
     * 
     * @param event
     */
    public void create(Cliente cliente) throws DataAccessLayerException {
        super.saveOrUpdate(cliente);
    }

    /**
     * Delete a detached Event from the database.
     * 
     * @param event
     */
    public void delete(int id) throws DataAccessLayerException {
        super.delete(Cliente.class, id);
    }

    /**
     * Find an Event by its primary key.
     * 
     * @param id
     * @return
     */
    public Cliente find(Long id) throws DataAccessLayerException {
        return (Cliente) super.find(Cliente.class, id);
    }

    /**
     * Updates the state of a detached Event.
     *
     * @param event
     */
    public void update(Cliente cliente) throws DataAccessLayerException {
        super.saveOrUpdate(cliente);
    }

    /**
     * Finds all Events in the database.
     * 
     * @return
     */
    public List findAll() throws DataAccessLayerException {
        return super.findAll(Cliente.class);
    }

    public List findAllClientsByName(String nombre, String primer_apellido, String segundo_apellido) {
        return super.findAllClientsByName(Cliente.class, nombre, primer_apellido, segundo_apellido);
    }

}
