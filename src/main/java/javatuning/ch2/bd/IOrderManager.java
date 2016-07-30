package javatuning.ch2.bd;

import javatuning.ch2.vo.Order;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IOrderManager extends Remote {

    Order getOrder(int id) throws RemoteException;

    boolean checkUser(int userid) throws RemoteException;

    boolean updateOrder(Order order) throws RemoteException;

}
