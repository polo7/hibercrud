package dev.lesechko.hibercrud;

import dev.lesechko.hibercrud.utils.HibernateConnectionUtils;
import dev.lesechko.hibercrud.view.MainView;


public class AppRunner {
    public static void main(String[] args) {
        HibernateConnectionUtils.getNewSession().close();
        new MainView().show();
    }
}