package org.coursemed;

import org.coursemed.tools.CustomDbTools;

import javax.net.ssl.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

public class Main {
    public static void main(String[] args) {
        System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
        CustomDbTools.initialize("coursemed.db");
        App.main(args);
    }
}
