package by.afanasyeu.avtoxam.security;

/**
 * @see by.afanasyeu.avtoxam.security.SecurityServiceImpl
 * @author Afanasyeu Alexei
 */
public interface SecurityService {
    String getLoggedInUsername();
    Long getLoggedInUserId();
}
