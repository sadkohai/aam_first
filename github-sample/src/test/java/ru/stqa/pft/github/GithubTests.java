package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {

  @Test
  public void testCommits() throws IOException {
    Github github = new RtGithub("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");
    RepoCommits commits = github.repos().get(new Coordinates.Simple("sadkohai", "aam_first")).commits();
    for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build()))
      System.out.println(new RepoCommit.Smart(commit).message());
  }
}