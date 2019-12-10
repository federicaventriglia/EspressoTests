package com.pan.idcheck;

import com.android.tools.lint.client.api.IssueRegistry;
import com.android.tools.lint.detector.api.Issue;

import java.util.Arrays;
import java.util.List;

/*
 * The list of issues that will be checked when running lint.
 */

public class CheckIdIssueRegistry extends IssueRegistry {

    private List<Issue> idIssues = Arrays.asList(
            CheckIdDetector.IDISSUE,
            CheckIdDetector.CONTDESCISSUE,
            CheckIdDetector.BOTHISSUE
    );

    public CheckIdIssueRegistry() {
    }

    @Override
    public List<Issue> getIssues() {
        return idIssues;
    }

    @Override public int getApi() {
        return com.android.tools.lint.detector.api.ApiKt.CURRENT_API;
    }

}

