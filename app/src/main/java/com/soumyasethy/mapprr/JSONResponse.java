package com.soumyasethy.mapprr;

import com.soumyasethy.mapprr.model.contributers.Contributer;
import com.soumyasethy.mapprr.model.repository.Repository;

public class JSONResponse {
  private Repository[] items;
  private Contributer[] contributions;

  public Repository[] getItems() {
    return items;
  }

  public Contributer[] getContributer() {
    return contributions;
  }
}
