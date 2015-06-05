package ru.bellintegrator.services;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by DOrdynskiy on 05.06.2015.
 */
public class LogParser {
    private static final Date begin = new Date();
    private static String path = new LogParser().getClass().getProtectionDomain().getCodeSource().getLocation().getPath().replace("bin/", "");
    private static List<String> detectAnchors = new ArrayList<>();
    private static Set<String> foundAnchors = new HashSet<>();
    private static String searchWord;
    private static String badWord;
    private static List<String> badWords;

    public static void main(String args[]) {
        init();
        writeResult();
    }

    public static void init() {
        detectAnchors.add("(_ThreadID=)([^;]+)(;)");
        detectAnchors.add("(.*)([0-9]{15,15}\\-[0-9]{4,4}\\-[0-9]{18,18})(.*)");
        detectAnchors.add("(RequestID=)([a-z0-9\\-]+)(\\;)");
        detectAnchors.add("([^0-9a-z\\:\\.])([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\:[0-9a-z\\:\\.]+)(\\:\\-)");

        try (InputStream f1 = new FileInputStream(path + "searchWord.txt")) {
            int size;
            size = f1.available();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < size; i++) {
                sb.append((char) f1.read());
            }
            searchWord = sb.toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try (InputStream f1 = new FileInputStream(path + "badWord.txt")) {
            int size;
            size = f1.available();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < size; i++) {
                sb.append((char) f1.read());
            }
            badWord = sb.toString();
            badWords = Arrays.asList(badWord.split(";"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void writeResult() {
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
                            if(logsCashe.toString().length() > 1000000) {
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
    }

    private static void searchAnchors(String logRecord) {
        for (String anchor : detectAnchors) {
            Pattern p = Pattern.compile(anchor);
            Matcher m = p.matcher(logRecord);
            if (m.find()) {
                if (anchor.contains("_ThreadID")) {
                    foundAnchors.add(m.group());
                } else
                    foundAnchors.add(m.group(2));
            }
        }
    }

    private static boolean detectAnchor(String logRecord) {
        if (logRecord.contains(searchWord)) {
            searchAnchors(logRecord);
            return true;
        }
        for (String anchor : foundAnchors) {
            if (logRecord.contains(anchor)) {
                searchAnchors(logRecord);
                return true;
            }
        }
        return false;
    }
}
