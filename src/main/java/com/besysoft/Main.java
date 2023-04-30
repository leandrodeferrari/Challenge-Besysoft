package com.besysoft;

import com.besysoft.service.IMenuService;
import com.besysoft.service.impl.MenuServiceImpl;

public class Main {
    public static void main(String[] args) {

        IMenuService menu = new MenuServiceImpl();
        menu.iniciar();

    }
}