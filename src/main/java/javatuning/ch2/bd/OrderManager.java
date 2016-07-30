package javatuning.ch2.bd;

import javatuning.ch2.vo.Order;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class OrderManager extends UnicastRemoteObject implements IOrderManager {

    private static final long serialVersionUID = -1717013007581295639L;

    public OrderManager() throws RemoteException {
        super();
    }

    @Override
    public Order getOrder(int id) throws RemoteException {
        Order o = new Order();
        o.setClientName("billy");
        o.setNumber(20);
        o.setProdunctName("desk");
        return o;
    }

    @Override
    public boolean checkUser(int userid) throws RemoteException {
        return true;
    }

    @Override
    public boolean updateOrder(Order order) throws RemoteException {
        return true;
    }

}
