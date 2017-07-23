package by.afanasyeu.avtoxam.security;

/**
 * Created by Afanasyeu Alexei on 23.07.2017.
 */
public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
