package com.sanmi.citasClientes.hibernate.dao;

import java.util.List;

import com.sanmi.citasClientes.hibernate.bean.UserBean;

public class UserDAO extends AbstractDao {

    public UserDAO() {
        super();
    }

    protected List findUserByNamePw(String nombre, String password) {
        // TODO Auto-generated method stub
        return super.findUserByNamePw(UserBean.class, nombre, password);
    }

    public boolean isUserValid(String nombre, String password) {
        if (this.findUserByNamePw(nombre, password).size() == 0) {
            return false;
        }
        return true;
    }

}