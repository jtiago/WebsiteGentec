package br.inf.gentec.site.security;

import org.apache.commons.codec.binary.Base64;

public class CriptografiaBase64
{

    public CriptografiaBase64()
    {
    }

    public static final String encrypt(String text)
    {
        return new String(Base64.encodeBase64(text.getBytes()));
    }

    public static final String decrypt(String text)
    {
        return new String(Base64.decodeBase64(text.getBytes()));
    }

    public static void main(String args[])
    {
        System.out.println(decrypt("I0dlbnRlYzMyMDIq"));
        System.out.println(encrypt("sa"));
    }
}
