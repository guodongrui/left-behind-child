package org.lbchild.util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Base64Content {

    public static String decode(String content) throws UnsupportedEncodingException {

        byte[] decordedValue = Base64.getDecoder().decode(content);
        String s = new String(decordedValue, "utf-16LE");

        return s;
    }

    public static String encode(String content) throws Exception {

        byte[] encodedValue = Base64.getEncoder().encode(content.getBytes("UTF-16LE"));
        String t = new String(encodedValue);

        return t;
    }

    public static void main(String[] args) throws Exception {
        String content = "PABoAHQAbQBsAD4APABiAG8AZAB5AD4APABQAD4AV1P9kK+LsIsFgMSeYE4fT1l1iFs/UeV6L2bRj3ReZWcATipOCGE+ZoF6+lGEdu6VmJga/9ZO7E6EdjZyzWs6ToZOH3WhixZZ+lFTYuVdDP9GT1xPOk5QW3NZhHbWTuxOdFNZdShXhk6cUVFntlvMkQz/1k7sToR2EGJ/lQ5OWWWygO6VmJgVX9FTf17bbMVi518M/0ZPiF8RXAlnuk5VXABfB1KeW0yIqFICMABO9Hb9j0JsAU8aTg5OPnkaT4xUEIzRU1VchHadW2yafWxmjwz/Y2soV55bvWUATnmYc1HobFl1iFs/UeV6hHYcIKZg+4uhixJSHSAM/xqQx4/Efsd+z34AlUZVjFQxcsNfZo87TlBjYI3+VmZODP/Vi/5Wnlg6Xz55Gk/5W1l1iFs/UeV6hHZzUehsjFR1VKRiDP/zgcpO8l3fUsaW/lZmTjEANAA0ADAAMACMUQIwDQAKADwAUAA+ADEAMgAIZzIANADlZS0AMgA2AOVlDP9CAE0AVwAcIOtfUE4FlvuLaFQrZx0gO22oUmVnMFKKf85Xf17eXQz/GpDHj1BjYI0xcsNf/lZmTiAAATBaU2lyhpnCU8KJSXswTsxbGllpX4R2O22oUoVRuVsM/41Rpl5HbFqABVPsYs9+AJVGVQEwMXLDX2aPO05JeyhXhVGEdp1bbJoxcsNfJ1m2W61ehHabUs+RDP86Tn9e3l0CXsSe1Fc6UwxUwU5mWyFohHYxADgADVRZdYhbP1HleiZeu1NzUTFyjFR1VKRiDP8uXqlSaVtQW+xOKFfTW1llvGVQToR2uWUPXwtO73mBZ3Js1lPld8aLDP+rjsNfZVC3XjBX619QThBif5UCMA0ACgA8AFAAPgAKTmhUK2edW2yaHCDrX1BOBZb7i2hUK2cdIAxU9mUoVylZJW0BMApOd22MVH+JiVszACdZzlcCXgpOFG8CMABOLGcsZ39ifY9AdzFyw1+Edv5WZk4M/yhX2Y8qTqxRKVkM/zpO0Y9+dg1UWXWIWz9R5XomXrtThk7gZVCWhHZzUTFyAjBkloZO/lZmTiAAUGNgjQz/aVtQW+xO2I8oVzFyw19mjztOylMMVISfD1wZTzRPhHZqljRPC04M/0V1OG5aU2lyhpkBMJJjFG8cIP6LLGdnUh0gATAoVyJr8FgRe+2L9JUATgxUImumXiNX3ouCggz/Ok7CUw5OdlEtToR2z2sATipOuk79kFl1C06GTo5/fVneVsZfAjANAAoAPABQAD4APAAvAFAAPgA8AC8AYgBvAGQAeQA+ADwALwBoAHQAbQBsAD4A";

        String text = Base64Content.decode(content);
        Base64Content.encode(text);
    }
}
