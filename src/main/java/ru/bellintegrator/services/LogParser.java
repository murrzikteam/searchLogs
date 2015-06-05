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
    public List<String> searchAnchors(List<String> anchorsRegExpressions, String textToProcess) {
        if (anchorsRegExpressions == null || anchorsRegExpressions.isEmpty() || textToProcess == null || textToProcess.isEmpty())
            return new LinkedList<>();
        List<String> result = new LinkedList<>();
        for (String anchorExp : anchorsRegExpressions) {
            Pattern p = Pattern.compile(anchorExp);
            Matcher m = p.matcher(textToProcess);
            if (m.find()) {
                if (anchorExp.contains("ThreadID")) {
                    result.add(m.group());
                } else
                    result.add(m.group(2));
            }
        }
        return result;
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
}
