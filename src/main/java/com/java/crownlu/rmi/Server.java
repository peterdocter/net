package com.java.crownlu.rmi;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;

/**
 * Created by crownlu on 17/1/11.
 */
public class Server {

    /**
     * 服务端暴露给客户端的接口声明，必须继承Remote接口用以标示该接口是rmi接口
     */
    interface IService extends Remote {

        String service(String req, Map<String, String> context) throws RemoteException;
    }

    /**
     * rmi的服务实际提供者，除了需要实现暴露给客户端的服务接口之外，还需要继承UnicastRemoteObject类，才能将方法导出给客户端
     * UnicastRemoteObject的构造方法会抛出RemoteException，因此需要在实现他的子类当中重新声明构造方法，并继续抛出该异常
     */
    static class ServiceImpl extends UnicastRemoteObject implements IService {
        public ServiceImpl() throws RemoteException {
        }

        @Override
        public String service(String req, Map<String, String> context) {
            return context.get(req);
        }
    }

    /**
     * 注册服务
     * @param args
     * @throws Exception
     */
    public static void main(String [] args) throws Exception {
        IService service = new ServiceImpl();
        Context context = new InitialContext();
        context.rebind("rmi://127.0.0.1:8811/service", service);
    }
}
