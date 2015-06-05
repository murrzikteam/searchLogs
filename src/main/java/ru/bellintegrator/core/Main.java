package ru.bellintegrator.core;

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
        ConfiguratorBean config = new ConfiguratorBean();
        config.setLogsFilePath(new Main().getClass().getProtectionDomain().getCodeSource().getLocation().getPath().replace("bin/", "") + "log.txt");
        config.setResultsFilePath(new Main().getClass().getProtectionDomain().getCodeSource().getLocation().getPath().replace("bin/", "") + "searchResults.txt");
        config.getSearchKeywords().add("CallBack");
        config.getBadWords().add("trouble");
        config.setCasheInMb(10);
        config.getRegExpressionsList().add("(_ThreadID=)([^;]+)(;)");
        config.getRegExpressionsList().add("(.*)([0-9]{15,15}\\-[0-9]{4,4}\\-[0-9]{18,18})(.*)");
        config.getRegExpressionsList().add("(RequestID=)([a-z0-9\\-]+)(\\;)");
        config.getRegExpressionsList().add("([^0-9a-z\\:\\.])([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\:[0-9a-z\\:\\.]+)(\\:\\-)");

        FileProcessor fProcessor = new FileProcessor();
        LogParser lParser = new LogParser();

        try (InputStream f1 = fProcessor.getFileInputStream(config.getLogsFilePath())) {
            String logRecord = "";
            int av = f1.available();
            StringBuilder logsCashe = new StringBuilder();
            for (int i = 0; i < av; i++) {
                logRecord += (char) f1.read();
                if (logRecord.contains(config.getLogsEndAnchor())) {
                    if(lParser.detectAnchor(logRecord, config.getBadWords(), true)) {
                        logRecord = "";
                        continue;
                    }
                    if(lParser.detectAnchor(logRecord, config.getSearchKeywords(), false) || lParser.detectAnchor(logRecord, config.getFoundAnchorsSet(), false)){
                        lParser.searchAnchors(config.getRegExpressionsList(), logRecord, config.getFoundAnchorsSet());
                        logsCashe.append(logRecord);
                        logRecord = "";
                        if(logsCashe.toString().length() > config.getCasheInMb()) {
                            fProcessor.writeTextToFile(logsCashe.toString(), config.getResultsFilePath(), true);
                            logsCashe = new StringBuilder();
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Press any letter and hit Enter to Exit...");
        Scanner in = new Scanner(System.in);
        in.next();
    }
}