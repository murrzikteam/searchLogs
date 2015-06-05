package ru.bellintegrator.core;

import ru.bellintegrator.services.ConfiguratorBean;
import ru.bellintegrator.services.FileProcessor;
import ru.bellintegrator.services.LogParser;

import java.util.*;

/**
 * Created by DOrdynskiy on 05.06.2015.
 */
public class Main {
    public static void main(String[] args) {
        ConfiguratorBean config = new ConfiguratorBean();
        config.setLogsFilePath(new Main().getClass().getProtectionDomain().getCodeSource().getLocation().getPath().replace("bin/", "") + "log.txt");
        config.setResultsFilePath(new Main().getClass().getProtectionDomain().getCodeSource().getLocation().getPath().replace("bin/", "") + "searchResults.txt");
        config.getSearchKeywords().add("callBack");
        config.getBadWords().add("trouble");
        config.setCasheInMb(10);
        config.getRegExpressionsList().add("(_ThreadID=)([^;]+)(;)");
        config.getRegExpressionsList().add("(.*)([0-9]{15,15}\\-[0-9]{4,4}\\-[0-9]{18,18})(.*)");
        config.getRegExpressionsList().add("(RequestID=)([a-z0-9\\-]+)(\\;)");
        config.getRegExpressionsList().add("([^0-9a-z\\:\\.])([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\:[0-9a-z\\:\\.]+)(\\:\\-)");

        FileProcessor fProcessor = new FileProcessor();
        LogParser lParser = new LogParser();

        System.out.println("Application started normally! Press any letter and hit Enter to Exit...");
        Scanner in = new Scanner(System.in);
        in.next();
    }
}
/*private static void writeResult() {
    try (InputStream f1 = new FileInputStream(path + "log.txt")) {
        try (FileWriter writer = new FileWriter(path + "results/searchResults - " + searchWord + ".txt", false)) {
            writer.append("");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        String logRecord = "";
        int av = f1.available();
        try (FileWriter writer = new FileWriter(path + "results/searchResults - " + searchWord + ".txt", true)) {
            StringBuilder logsCashe = new StringBuilder();
            for (int i = 0; i < av; i++) {
                logRecord += (char) f1.read();
                if (logRecord.contains("|#]")) {
                    for (String bad : badWords) {
                        if (logRecord.toLowerCase().contains(bad.toLowerCase())) {
                            logRecord = "";
                            continue;
                        }
                    }
                    if (detectAnchor(logRecord)) {
                        logsCashe.append(logRecord);
                        if (logsCashe.toString().length() > 1000000) {
                            writer.append(logsCashe.toString());
                            logsCashe = new StringBuilder();
                        }
                    }
                    logRecord = "";
                }
            }
            writer.append(logsCashe.toString());
        }
        System.out.println("\n\nAnchors count = " + foundAnchors.size());
        for (String text : foundAnchors) {
            System.out.println(text);
        }
        System.out.print("\nSearch time - " + (new Date().getTime() - begin.getTime()) / 1000 / 60 + " minutes\nPress some letter on keyboard and press ENTER_ ");
        Scanner in = new Scanner(System.in);
        in.next();
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
}*/