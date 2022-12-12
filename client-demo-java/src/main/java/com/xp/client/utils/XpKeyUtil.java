package com.xp.client.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.IoUtil;
import com.xp.client.enums.EncryptAlgorithmEnum;
import com.xp.client.enums.EncryptKeyTypeEnum;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

public class XpKeyUtil {

    public XpKeyUtil() {
    }

    public static SecretKey genSymmetricKey(final String algorithm, final int bits, final String seed) throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance(algorithm);
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        if (StringUtils.isNotBlank(seed)) {
            random.setSeed(seed.getBytes());
        }

        random.nextBytes(new byte[8]);
        keyGen.init(bits, random);
        return keyGen.generateKey();
    }

    public static String genSymmetricKeyBase64(final String algorithm, final int bits, final String seed) throws NoSuchAlgorithmException {
        SecretKey key = genSymmetricKey(algorithm, bits, seed);
        return genSymmetricKeyBase64(key);
    }

    public static String genSymmetricKeyBase64(final SecretKey key) {
        return encodeBase64(key.getEncoded());
    }

    public static PublicKey getPublicKeyFromX509(final String algorithm, final InputStream in) {
        if (StringUtils.isBlank(algorithm)) {
            throw new RuntimeException("加密算法为空");
        } else if (in == null) {
            throw new RuntimeException("输入流为空");
        } else {
            try {
                KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
                StringWriter writer = new StringWriter();
                StreamUtil.io(new InputStreamReader(in), writer);
                byte[] keyEncoded = writer.toString().getBytes();
                byte[] keyDecoded = Base64.decode(keyEncoded);
                return keyFactory.generatePublic(new X509EncodedKeySpec(keyDecoded));
            } catch (IOException | InvalidKeySpecException | NoSuchAlgorithmException var6) {
                throw new RuntimeException(var6.getLocalizedMessage(), var6);
            }
        }
    }

    public static PrivateKey getPrivateKeyFromPKCS8(final String algorithm, final InputStream in) {
        if (StringUtils.isBlank(algorithm)) {
            throw new RuntimeException("加密算法为空");
        } else if (in == null) {
            throw new RuntimeException("输入流为空");
        } else {
            try {
                KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
                byte[] keyEncoded = StreamUtil.readText(in).getBytes();
                byte[] keyDecoded = Base64.decode(keyEncoded);
                return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(keyDecoded));
            } catch (IOException | InvalidKeySpecException | NoSuchAlgorithmException var5) {
                throw new RuntimeException(var5.getLocalizedMessage(), var5);
            }
        }
    }

    public static RSAPublicKey getRsaPublicKeyFromCer(final String cerPath) {
        FileInputStream cerFile = null;

        RSAPublicKey var4;
        try {
            cerFile = new FileInputStream(new File(cerPath));
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            X509Certificate x509cert = (X509Certificate) cf.generateCertificate(cerFile);
            var4 = (RSAPublicKey) x509cert.getPublicKey();
        } catch (CertificateException | FileNotFoundException var8) {
            throw new RuntimeException(var8.getLocalizedMessage(), var8);
        } finally {
            IOUtils.closeQuietly(cerFile);
        }

        return var4;
    }

    public static RSAPrivateCrtKey getRsaPrivateKeyFromPfx(final String pfxPath, final String pfxPassword) {
        FileInputStream keyFile = null;

        Enumeration myEnum;
        try {
            keyFile = new FileInputStream(new File(pfxPath));
            KeyStore ks = KeyStore.getInstance("PKCS12");
            ks.load(keyFile, pfxPassword.toCharArray());
            myEnum = ks.aliases();
            RSAPrivateCrtKey prikey = null;

            while (true) {
                if (myEnum.hasMoreElements()) {
                    String keyAlias = (String) myEnum.nextElement();
                    if (!ks.isKeyEntry(keyAlias)) {
                        continue;
                    }

                    prikey = (RSAPrivateCrtKey) ks.getKey(keyAlias, pfxPassword.toCharArray());
                }

                RSAPrivateCrtKey var7 = prikey;
                return var7;
            }
        } catch (CertificateException | KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException | IOException var11) {
            myEnum = null;
        } finally {
            IoUtil.close(keyFile);
        }

        return null;
    }

    public static String getRsaPublicKey(final RSAPublicKey rsaPublicKey) {
        return null == rsaPublicKey ? null : encodeBase64(rsaPublicKey.getEncoded());
    }

    public static String getRsaPrivateKey(final RSAPrivateCrtKey rsaPrivateCrtKey) {
        return null == rsaPrivateCrtKey ? null : encodeBase64(rsaPrivateCrtKey.getEncoded());
    }

    public static Map<String, String> generateKey(final EncryptAlgorithmEnum asymAlgorithm, final int keySize) {
        try {
            KeyPairGenerator keygen = KeyPairGenerator.getInstance(asymAlgorithm.getValue());
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            keygen.initialize(keySize, random);
            KeyPair keyPair = keygen.genKeyPair();
            String publicKey = encodeBase64(keyPair.getPublic().getEncoded());
            String privateKey = encodeBase64(keyPair.getPrivate().getEncoded());
            Map<String, String> mapKeyPair = new HashMap();
            mapKeyPair.put(EncryptKeyTypeEnum.ASYM_PUBLIC.getValue(), publicKey);
            mapKeyPair.put(EncryptKeyTypeEnum.ASYM_PRIVATE.getValue(), privateKey);
            return mapKeyPair;
        } catch (NoSuchAlgorithmException var8) {
            throw new RuntimeException(MessageFormat.format("加密算法[{0}]初始化密钥对异常：{1}", asymAlgorithm.getValue(), var8.getMessage()));
        }
    }

    public static String encodeBase64(final byte[] data) {
        return Base64.encode(data);
    }
}
