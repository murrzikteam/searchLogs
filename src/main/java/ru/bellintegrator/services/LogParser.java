package ru.bellintegrator.services;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by DOrdynskiy on 05.06.2015.
 */
public class LogParser {
    /**
     * Method search anchors by list of regular expressions
     *
     * @param anchorsRegExpressions Regular expressions for anchors
     * @param textToProcess         source text with anchors
     * @return List of founded anchors
     */
    public void searchAnchors(List<String> anchorsRegExpressions, String textToProcess, Set<String> destSet) {
        if (anchorsRegExpressions == null || anchorsRegExpressions.isEmpty() || textToProcess == null || textToProcess.isEmpty())
            return;
        for (String anchorExp : anchorsRegExpressions) {
            Pattern p = Pattern.compile(anchorExp);
            Matcher m = p.matcher(textToProcess);
            if (m.find()) {
                if (anchorExp.contains("ThreadID")) {
                    destSet.add(m.group());
                } else
                    destSet.add(m.group(2));
            }
        }
    }

    /**
     * Search anchors in text
     *
     * @param textToProcess text to search anchors
     * @param anchors       set with anchors
     * @param ignoreCase    if true - method will ignore symbols case in searching
     * @return true if text contains one of anchors
     */
    public boolean detectAnchor(String textToProcess, Set<String> anchors, boolean ignoreCase) {
        if (textToProcess == null || textToProcess.isEmpty() || anchors == null || anchors.isEmpty()) {
            return false;
        }
        if (ignoreCase) {
            for (String anchor : anchors) {
                if (textToProcess.toLowerCase().contains(anchor.toLowerCase())) return true;
            }
        } else {
            for (String anchor : anchors) {
                if (textToProcess.contains(anchor)) return true;
            }
        }
        return false;
    }

    public static void test(ConfiguratorBean config) {
        FileProcessor fProcessor = new FileProcessor();
        LogParser lParser = new LogParser();

        try (InputStream f1 = fProcessor.getFileInputStream(config.getLogsFilePath())) {
            String logRecord = "";
            fProcessor.writeTextToFile(logRecord, config.getResultsFilePath(), false);
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
                    logRecord = "";
                }
            }
            if (!logsCashe.toString().isEmpty()) fProcessor.writeTextToFile(logsCashe.toString(), config.getResultsFilePath(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
