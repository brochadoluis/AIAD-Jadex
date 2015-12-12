package agents;

import jadex.commons.future.IFuture;

public interface WorldService {
  public IFuture<Void> serviceFire(int[] pos, boolean f);
}