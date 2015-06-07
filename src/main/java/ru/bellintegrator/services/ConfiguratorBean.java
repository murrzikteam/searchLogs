package ru.bellintegrator.services;

import java.util.*;

/**
 * Created by DOrdynskiy on 05.06.2015.
 */
public class ConfiguratorBean {

    public ConfiguratorBean() {
        logsFilePath = "";
        resultsFilePath = "";
        searchKeywords = new HashSet<>();
        badWords = new HashSet<>();
        casheInMb = 1;
        logsEndAnchor = "|#]";
        regExpressionsList = new ArrayList<>();
        regExpressionsList.add("(_ThreadID=)([^;]+)(;)");
        regExpressionsList.add("(.*)([0-9]{15,15}\\-[0-9]{4,4}\\-[0-9]{18,18})(.*)");
        regExpressionsList.add("(RequestID=)([a-z0-9\\-]+)(\\;)");
        regExpressionsList.add("([^0-9a-z\\:\\.])([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\:[0-9a-z\\:\\.]+)(\\:\\-)");
        foundAnchorsSet = new HashSet<>();
    }

    private String logsFilePath;
    private String resultsFilePath;
    private Set<String> searchKeywords;
    private Set<String> badWords;
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

    public Set<String> getSearchKeywords() {
        return searchKeywords;
    }

    public Set<String> getBadWords() {
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
