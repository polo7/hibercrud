package dev.lesechko.hibercrud;

import dev.lesechko.hibercrud.view.MainView;
import org.hibernate.SessionFactory;

public class AppRunner {
    public static void main(String[] args) {
        new MainView().show();
    }
}