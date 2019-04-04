package de.adorsys.docusafe2.business.impl.bucketpathencryption;

import de.adorsys.docusafe2.business.api.cmsencryption.CMSEncryptionService;
import de.adorsys.docusafe2.business.api.keystore.KeyStoreService;
import de.adorsys.docusafe2.business.api.keystore.types.*;
import de.adorsys.docusafe2.business.api.types.DocumentContent;
import de.adorsys.docusafe2.business.impl.cmsencryption.services.CMSEncryptionServiceImpl;
import de.adorsys.docusafe2.business.impl.keystore.KeyStoreServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.cms.CMSEnvelopedData;
import org.junit.Test;

import java.security.KeyStore;
import java.security.PublicKey;

import static org.junit.Assert.assertArrayEquals;

@Slf4j
public class CmsEncryptionServiceTest {

        private CMSEncryptionService cmsEncryptionService = new CMSEncryptionServiceImpl();
        private KeyStoreService keyStoreService = new KeyStoreServiceImpl();

        @Test
        public void cmsEnvelopeEncryptAndDecryptTest() {

            ReadKeyPassword readKeyPassword = new ReadKeyPassword("readkeypassword");
            ReadStorePassword readStorePassword = new ReadStorePassword("readstorepassword");
            KeyStoreAuth keyStoreAuth = new KeyStoreAuth(readStorePassword, readKeyPassword);

            KeyStoreCreationConfig config = new KeyStoreCreationConfig(1, 0, 1);
            KeyStore keyStore = keyStoreService.createKeyStore(keyStoreAuth, KeyStoreType.DEFAULT, config);
            KeyStoreAccess keyStoreAccess = new KeyStoreAccess(keyStore, keyStoreAuth);

            KeySourceAndKeyID forPublicKey = keyStoreService.getKeySourceAndKeyIDForPublicKey(keyStoreAccess);
            KeyID keyID = forPublicKey.getKeyID();
            PublicKey publicKey = (PublicKey) forPublicKey.getKeySource().readKey(keyID);

            DocumentContent origMessage = new DocumentContent("message content".getBytes());
            CMSEnvelopedData encrypted  = cmsEncryptionService.encrypt(origMessage, publicKey, keyID);
            DocumentContent decrypted = cmsEncryptionService.decrypt(encrypted, keyStoreAccess);

            assertArrayEquals(origMessage.getValue(), decrypted.getValue());
            log.debug("en and decrypted successfully");
        }
    }
