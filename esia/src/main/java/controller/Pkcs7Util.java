package controller;


import configuration.KeyStoreConfig;
import org.bouncycastle.cert.jcajce.JcaCertStore;
import org.bouncycastle.cms.*;
import org.bouncycastle.cms.jcajce.JcaSignerInfoGeneratorBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder;
import org.bouncycastle.util.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * Magic from cryptography
 */
@Component
public final class Pkcs7Util implements CertificateUtil {

    @Autowired
    private KeyStoreConfig keyStore;

    @Override
    public String getUrlSafeSign(final String content) {
        try {
            byte[] signedBytes = signPkcs7(content.getBytes("UTF-8"), setUpProvider(loadKeyStore()));
            return new String(Base64.getUrlEncoder().encode(signedBytes));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private KeyStore loadKeyStore()
            throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException {
        KeyStore keystore = KeyStore.getInstance("JKS");
        InputStream is = new FileInputStream(keyStore.getPathToKeysStore());
        keystore.load(is, keyStore.getKeyStorePassword().toCharArray());
        return keystore;
    }

    private CMSSignedDataGenerator setUpProvider(final KeyStore keystore)
            throws Exception {
        Security.addProvider(new BouncyCastleProvider());
        Certificate[] certificateChain = keystore.getCertificateChain(keyStore.getKeyAlias());
        final List<Certificate> certificates = new ArrayList<>();

        for (int i = 0, length = certificateChain == null ? 0 : certificateChain.length; i < length; i++) {
            certificates.add(certificateChain[i]);
        }

        Store store = new JcaCertStore(certificates);
        Certificate cert = keystore.getCertificate(keyStore.getKeyAlias());
        ContentSigner signer = new JcaContentSignerBuilder(keyStore.getSignatureAlg()).setProvider("BC").
                build((PrivateKey) (keystore.getKey(keyStore.getKeyAlias(), keyStore.getKeyStorePassword().toCharArray())));

        CMSSignedDataGenerator generator = new CMSSignedDataGenerator();
        generator.addSignerInfoGenerator(new JcaSignerInfoGeneratorBuilder(new JcaDigestCalculatorProviderBuilder().setProvider("BC").
                build()).build(signer, (X509Certificate) cert));

        generator.addCertificates(store);

        return generator;
    }

    private byte[] signPkcs7(final byte[] content, final CMSSignedDataGenerator generator) throws CMSException, IOException {
        CMSTypedData cmsTypedData = new CMSProcessableByteArray(content);
        CMSSignedData signedData = generator.generate(cmsTypedData, true);
        return signedData.getEncoded();
    }

}
