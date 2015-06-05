package ru.bellintegrator.services;

import java.util.*;

/**
 * Created by DOrdynskiy on 05.06.2015.
 */
public class ConfiguratorBean {

    public ConfiguratorBean() {
        logsFilePath = "";
        resultsFilePath = "";
        searchKeywords = new ArrayList<>();
        badWords = new ArrayList<>();
        casheInMb = 1;
        logsEndAnchor = "|#]";
        regExpressionsList = new ArrayList<>();
        foundAnchorsSet = new HashSet<>();
    }

    private String logsFilePath;
    private String resultsFilePath;
    private List<String> searchKeywords;
    private List<String> badWords;
    private int casheInMb;
    private String logsEndAnchor;
    private List<String> regExpressionsList;
    private Set<String> foundAnchorsSet;

    public String getLogsFilePath() {
        return logsFilePath;
    }

    public void setLogsFilePath(String logsFilePath) {
        this.logsFilePath = logsFilePath;
    }

    public String getResultsFilePath() {
        return resultsFilePath;
    }

    public void setResultsFilePath(String resultsFilePath) {
        this.resultsFilePath = resultsFilePath;
    }

    public List<String> getSearchKeywords() {
        return searchKeywords;
    }

    public List<String> getBadWords() {
        return badWords;
    }


    public int getCasheInMb() {
        return casheInMb;
    }

    public void setCasheInMb(int casheInMb) {
        this.casheInMb = casheInMb;
    }

    public String getLogsEndAnchor() {
        return logsEndAnchor;
    }

    public void setLogsEndAnchor(String logsEndAnchor) {
        this.logsEndAnchor = logsEndAnchor;
    }

    public List<String> getRegExpressionsList() {
        return regExpressionsList;
    }

    public Set<String> getFoundAnchorsSet() {
        return foundAnchorsSet;
    }
}
