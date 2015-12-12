package agents;

import jadex.commons.future.IFuture;

public interface FireAlertService {
	public IFuture<Void> dispatchFighters(int[] pos, boolean f);
  /*public IFuture<Void> serviceAcc(int value, int e);
  public IFuture<Void> serviceWea(int value, int e);
  public IFuture<Void> serviceLei(int lei, String leiNodeName);*/
}