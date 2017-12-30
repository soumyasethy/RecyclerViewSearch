
package com.soumyasethy.mapprr.model;

import java.io.Serializable;
import java.util.List;

public class GitData implements Serializable {

  private Integer totalCount;
  private Boolean incompleteResults;
  private List<Repository> items;

  public Integer getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(Integer totalCount) {
    this.totalCount = totalCount;
  }

  public Boolean getIncompleteResults() {
    return incompleteResults;
  }

  public void setIncompleteResults(Boolean incompleteResults) {
    this.incompleteResults = incompleteResults;
  }

  public List<Repository> getRepositories() {
    return items;
  }

  public void setRepositories(List<Repository> repositories) {
    this.items = repositories;
  }

}
