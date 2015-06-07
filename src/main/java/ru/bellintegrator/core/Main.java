package ru.bellintegrator.core;

import ru.bellintegrator.gui.MainWindow;
import ru.bellintegrator.services.ConfiguratorBean;
import ru.bellintegrator.services.FileProcessor;
import ru.bellintegrator.services.LogParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by DOrdynskiy on 05.06.2015.
 */
public class Main {
    public static void main(String[] args) {
        MainWindow window = new MainWindow();
        window.init(args);
    }
}