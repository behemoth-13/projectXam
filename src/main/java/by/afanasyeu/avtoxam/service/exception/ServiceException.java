package by.afanasyeu.avtoxam.service.exception;

/**
 * Created by Afanasyeu Alexei on 09.07.2017.
 */
public class ServiceException extends Exception{
    public ServiceException(String msg) {
        super(msg);
    }
    public ServiceException(String msg, Throwable e) {
        super(msg, e);
    }
}
