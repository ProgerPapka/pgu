package controller;

import org.springframework.stereotype.Component;

/**
 * Interface to get client secret
 */
@Component
public interface CertificateUtil {
    String getUrlSafeSign(String content);
}
