package javatuning.ch2.vo;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IOrderManager extends Remote {

    Order getOrder(int id) throws RemoteException;

    String getClientName(int id) throws RemoteException;

    String getProdName(int id) throws RemoteException;

    int getNumber(int id) throws RemoteException;

}
